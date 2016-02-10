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
import tn.welldone.model.Service;
import tn.welldone.model.User;

@Stateless
@Local
public class ServiceRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;
	
	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Service service, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			service.setAddedBy(user);
			service.setCreatedAt(new Date());
			service.setUpdateAt(new Date());
			break;

		case UPDATE:
			service.setEditedBy(user);
			service.setUpdateAt(new Date());
			break;

		case DELETE:
			service.setDeletedBy(user);
			service.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Service service) {
		setTransactionDetails(service, Action.CREATE);
		service.setIsDeleted(false);
		entityManager.persist(service);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Service service) {
		setTransactionDetails(service, Action.UPDATE);
		service.setIsDeleted(false);
		Service s = entityManager.merge(service);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Service service) {
		
		setTransactionDetails(service, Action.DELETE);
		// Option 1
		service.setIsDeleted(true);
		entityManager.merge(service);
		// Option 2
		// entityManager.remove(service);
		entityManager.flush();
	}

	public List<Service> getList() {
		return entityManager.createQuery("select s from Service s")
				.getResultList();
	}

	public List<Service> getNonDeletedServices() {
		return entityManager.createQuery(
				"select s from Service s where s.isDeleted = 'false' ")
				.getResultList();
	}

	public Service getServiceById(int id) {
		return entityManager.getReference(Service.class, id);
	}

}
