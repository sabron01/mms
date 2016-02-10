package tn.welldone.data;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Country;
import tn.welldone.model.User;

@Stateless
@Local
public class CountryRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Country country, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			country.setAddedBy(user);
			country.setCreatedAt(new Date());
			country.setUpdateAt(new Date());
			break;

		case UPDATE:
			country.setEditedBy(user);
			country.setUpdateAt(new Date());
			break;

		case DELETE:
			country.setDeletedBy(user);
			country.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Country country) {
		setTransactionDetails(country, Action.CREATE);
		country.setIsDeleted(false);
		entityManager.persist(country);
	}

	public Country getCountryById(int id) {
		return entityManager.getReference(Country.class, id);
	}

	public List<Country> getCountries() {
		return entityManager.createQuery(
				"select c from Country c where c.isDeleted = 'false' ")
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Country edit(Country country) {
		setTransactionDetails(country, Action.UPDATE);
		country.setIsDeleted(false);
		Country c = entityManager.merge(country);
		entityManager.flush();
		return c;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Country c) {
		
		setTransactionDetails(c, Action.DELETE);
		// Option 1
		c.setIsDeleted(true);
		Country a = entityManager.merge(c);

		// Option 2
		// entityManager.remove(service);

		entityManager.flush();
	}

}
