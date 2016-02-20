package tn.welldone.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Groupe;
import tn.welldone.model.Permission;
import tn.welldone.model.User;

@Stateless
@Local
public class UserRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(User u, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			u.setAddedBy(user);
			u.setCreatedAt(new Date());
			u.setUpdateAt(new Date());
			break;

		case UPDATE:
			u.setEditedBy(user);
			u.setUpdateAt(new Date());
			break;

		case DELETE:
			u.setDeletedBy(user);
			u.setDeletedAt(new Date());
			break;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Permission> getPermissions(User user) {

		Collection<Permission> permissions = new ArrayList<Permission>();
		for (Groupe groupe : user.getGroupes()) {
			
			for (Permission permission : groupe.getPermissions()) {
				permissions.add(permission);
			}
		}
		return permissions;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(User user) {
		setTransactionDetails(user, Action.CREATE);
		user.setIsDeleted(false);
		entityManager.persist(user);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(User user) {
		setTransactionDetails(user, Action.UPDATE);
		user.setIsDeleted(false);
		entityManager.merge(user);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(User user) {
		setTransactionDetails(user, Action.DELETE);
		user.setIsDeleted(true);
		User u = entityManager.merge(user);

		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<User> getList() {
		return entityManager.createQuery("select u from User u")
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User getUserById(int id) {
		return entityManager.getReference(User.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User getUserByLogin(String login) {
		return (User) entityManager.createQuery(
				"select u from User u where u.login = '" + login + "'")
				.getSingleResult();
	}

	public Collection<User> getAllUsers() {
		return entityManager.createQuery(
				"select u from User u where u.isDeleted = 'false' ")
				.getResultList();
	}
	
	public Collection<User> getUsersByGroupe(int groupe) {
		
		return (Collection<User>) entityManager.getReference(Groupe.class, groupe).getUsers();
		
	}

}
