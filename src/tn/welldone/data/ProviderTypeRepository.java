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
import tn.welldone.model.ProviderType;
import tn.welldone.model.User;

@Stateless
@Local
public class ProviderTypeRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;
	
	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(ProviderType providerType,
			Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			providerType.setAddedBy(user);
			providerType.setCreatedAt(new Date());
			providerType.setUpdateAt(new Date());
			break;

		case UPDATE:
			providerType.setEditedBy(user);
			providerType.setUpdateAt(new Date());
			break;

		case DELETE:
			providerType.setDeletedBy(user);
			providerType.setDeletedAt(new Date());
			break;
		}

	}


	public void add(ProviderType providerType) {
		setTransactionDetails(providerType, Action.CREATE);
		providerType.setIsDeleted(false);
		entityManager.persist(providerType);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(ProviderType providerType) {
		setTransactionDetails(providerType, Action.UPDATE);
		providerType.setIsDeleted(false);
		entityManager.merge(providerType);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(ProviderType providerType) {
		setTransactionDetails(providerType, Action.DELETE);
		// Option 1
		providerType.setIsDeleted(true);
		ProviderType p = entityManager.merge(providerType);
		// Option 2
		// entityManager.remove(service);
		entityManager.flush();
	}

	List<ProviderType> getList() {
		return entityManager.createQuery("select p from ProviderType p")
				.getResultList();
	}

	public List<ProviderType> getNonDeletedProviders() {
		return entityManager.createQuery(
				"select p from ProviderType p where p.isDeleted = 'false' ")
				.getResultList();
	}

	public ProviderType getProviderTypeById(int id) {
		return entityManager.getReference(ProviderType.class, id);
	}

}
