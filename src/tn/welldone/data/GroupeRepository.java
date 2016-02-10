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
import tn.welldone.model.Groupe;
import tn.welldone.model.User;

@Stateless
@Local
public class GroupeRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;
	
	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Groupe groupe, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			groupe.setAddedBy(user);
			groupe.setCreatedAt(new Date());
			groupe.setUpdateAt(new Date());
			break;

		case UPDATE:
			groupe.setEditedBy(user);
			groupe.setUpdateAt(new Date());
			break;

		case DELETE:
			groupe.setDeletedBy(user);
			groupe.setDeletedAt(new Date());
			break;
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Groupe add(Groupe groupe) {
		setTransactionDetails(groupe, Action.CREATE);
		groupe.setIsDeleted(false);
		entityManager.persist(groupe);
		Groupe d =entityManager.merge(groupe);
		entityManager.flush();		
		return d;		
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Groupe edit(Groupe groupe) {
		setTransactionDetails(groupe, Action.UPDATE);
		groupe.setIsDeleted(false);
		Groupe g = entityManager.merge(groupe);
		entityManager.flush();
		return g;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Groupe groupe) {
		setTransactionDetails(groupe, Action.DELETE);
		// Option 1
		groupe.setIsDeleted(true);
		entityManager.merge(groupe);
		// Option 2
		// entityManager.remove(service);
		entityManager.flush();
		System.out.println("Am hear :)");
	}

	public Groupe getGroupeById(int id) {
		return entityManager.getReference(Groupe.class, id);
	}
	
	List<Groupe> getList() {
		return entityManager.createQuery("select g from Groupe g")
				.getResultList();
	}

	public List<Groupe> getAllGroupes() {
		return entityManager.createQuery(
				"select g from Groupe g where g.isDeleted = 'false' ")
				.getResultList();
	}

	public List<Groupe> getGroupes() {
		return entityManager.createQuery(
				"select g from Groupe g where g.isDeleted = 'false' ")
				.getResultList();
	}

}
