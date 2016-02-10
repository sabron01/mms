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
import tn.welldone.model.SystemResource;
import tn.welldone.model.User;

@Stateless
@Local
public class SystemResourceRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;
	
	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(SystemResource systemResource, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			systemResource.setAddedBy(user);
			systemResource.setCreatedAt(new Date());
			systemResource.setUpdateAt(new Date());
			break;

		case UPDATE:
			systemResource.setEditedBy(user);
			systemResource.setUpdateAt(new Date());
			break;

		case DELETE:
			systemResource.setDeletedBy(user);
			systemResource.setDeletedAt(new Date());
			break;
		}

	}

	public void add(SystemResource systemResource) {
		setTransactionDetails(systemResource, Action.CREATE);
		systemResource.setIsDeleted(false);
		entityManager.persist(systemResource);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(SystemResource systemResource) {
		setTransactionDetails(systemResource, Action.UPDATE);
		systemResource.setIsDeleted(false);
		SystemResource s = entityManager.merge(systemResource);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(SystemResource systemResource) {
		setTransactionDetails(systemResource, Action.DELETE);
		// Option 1
		systemResource.setIsDeleted(true);
		entityManager.merge(systemResource);
		// Option 2
		// entityManager.remove(systemResource);
		entityManager.flush();
	}

	public List<SystemResource> getList() {
		return entityManager.createQuery("select s from SystemResource s")
				.getResultList();
	}

	public List<SystemResource> getNonDeletedSystemResources() {
		return entityManager.createQuery(
				"select s from SystemResource s where s.isDeleted = 'false' ")
				.getResultList();
	}

	public SystemResource getSystemResourceById(int id) {
		return entityManager.getReference(SystemResource.class, id);
	}

}
