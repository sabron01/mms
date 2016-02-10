package tn.welldone.data;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Permission;
import tn.welldone.model.User;

@Stateless
@Local
@Named
public class PermissionRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Permission permission,
			Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			permission.setAddedBy(user);
			permission.setCreatedAt(new Date());
			permission.setUpdateAt(new Date());
			break;

		case UPDATE:
			permission.setEditedBy(user);
			permission.setUpdateAt(new Date());
			break;

		case DELETE:
			permission.setDeletedBy(user);
			permission.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Permission permission) {
		setTransactionDetails(permission, Action.CREATE);
		permission.setIsDeleted(false);
		entityManager.persist(permission);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Permission permission) {
		setTransactionDetails(permission, Action.UPDATE);
		permission.setIsDeleted(false);
		entityManager.merge(permission);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Permission permission) {
		setTransactionDetails(permission, Action.DELETE);
		// Option 1
		permission.setIsDeleted(true);
		entityManager.merge(permission);
		// Option 2
		// entityManager.remove(service);
		entityManager.flush();
	}

	public Permission getPermissionById(int id) {
		return entityManager.getReference(Permission.class, id);
	}

	public List<Permission> getAllPermissions() {
		return entityManager.createQuery(
				"select p from Permission p where p.isDeleted = 'false' ")
				.getResultList();
	}

	public List<Permission> getGroupedPermissionsByResource() {
		return entityManager.createQuery(
				"select p from Permission p where p.isDeleted = 'false' ")
				.getResultList();
	}

}
