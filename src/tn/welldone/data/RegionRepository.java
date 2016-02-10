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
import tn.welldone.model.Region;
import tn.welldone.model.User;

@Stateless
@Local
public class RegionRepository {
	
	@PersistenceContext(unitName = "mmsPU")
    private EntityManager entityManager;
	
	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Region region, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			region.setAddedBy(user);
			region.setCreatedAt(new Date());
			region.setUpdateAt(new Date());
			break;

		case UPDATE:
			region.setEditedBy(user);
			region.setUpdateAt(new Date());
			break;

		case DELETE:
			region.setDeletedBy(user);
			region.setDeletedAt(new Date());
			break;
		}

	}

	
	public void add(Region region) {
		setTransactionDetails(region, Action.CREATE);
		region.setIsDeleted(false);
		entityManager.persist(region);
	}
	
	public Region getRegionById(int id) {
		return entityManager.getReference(Region.class, id);
	}
	
	public List<Region> getRegions() {
		return entityManager.createQuery(
				"select r from Region r where r.isDeleted = 'false'")
				.getResultList();
	}
	
	public List<Region> getRegions(Country country) {
		return entityManager.createQuery(
				"select r from Region r where r.isDeleted = 'false' AND r.country.id ="+ country.getId())
				.getResultList();
	}

	public List<Region> getRegions(String country_id) {
		return entityManager.createQuery(
				"select r from Region r where r.isDeleted = 'false' AND r.country.id ="+ country_id)
				.getResultList();
	}
	
	public List<Region> getDefaultRegions(Country c) {
		return entityManager.createQuery(
				"select r from Region r where r.isDeleted = 'false' AND country.id ="+ c.getId())
				.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Region edit(Region region) {
		setTransactionDetails(region, Action.UPDATE);
		region.setIsDeleted(false);
		Region r = entityManager.merge(region);
		entityManager.flush();
		return r;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Region r) {
		setTransactionDetails(r, Action.DELETE);
		// Option 1
		r.setIsDeleted(true);
		Region a = entityManager.merge(r);
	
		// Option 2
		// entityManager.remove(r);
	
		entityManager.flush();
	}
}
