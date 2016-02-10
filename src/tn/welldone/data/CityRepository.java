package tn.welldone.data;

import java.io.Serializable;
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
import tn.welldone.model.City;
import tn.welldone.model.User;

@Stateless
@Local
public class CityRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8132070522521962850L;

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(City city, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			city.setAddedBy(user);
			city.setCreatedAt(new Date());
			city.setUpdateAt(new Date());
			break;

		case UPDATE:
			city.setEditedBy(user);
			city.setUpdateAt(new Date());
			break;

		case DELETE:
			city.setDeletedBy(user);
			city.setDeletedAt(new Date());
			break;
		}

	}

	public void add(City city) {
		setTransactionDetails(city, Action.CREATE);
		city.setIsDeleted(false);
		entityManager.persist(city);
	}

	public City getCityById(int id) {
		return entityManager.getReference(City.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public City edit(City city) {
		setTransactionDetails(city, Action.UPDATE);
		city.setIsDeleted(false);
		City c = entityManager.merge(city);
		entityManager.flush();
		return c;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(City c) {
		setTransactionDetails(c, Action.DELETE);
		// Option 1
		c.setIsDeleted(true);
		City a = entityManager.merge(c);

		// Option 2
		// entityManager.remove(c);

		entityManager.flush();
	}

	public List<City> getCities() {
		return entityManager.createQuery(
				"select c from City c where c.isDeleted = 'false'")
				.getResultList();
	}

	public List<City> getCities(String region_id) {
		return entityManager.createQuery(
				"select r from City r where r.isDeleted = 'false' AND r.region.id ="
						+ region_id).getResultList();
	}

}
