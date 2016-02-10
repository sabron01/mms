package tn.welldone.data;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Tache;
import tn.welldone.model.User;

@Stateless
@Local
public class TacheRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Tache tache, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			tache.setAddedBy(user);
			tache.setCreatedAt(new Date());
			tache.setUpdateAt(new Date());
			break;

		case UPDATE:
			tache.setEditedBy(user);
			tache.setUpdateAt(new Date());
			break;

		case DELETE:
			tache.setDeletedBy(user);
			tache.setDeletedAt(new Date());
			break;
		}

	}

	public void setTransactionDetails(MedicalJourney medicalJourney,
			Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			medicalJourney.setAddedBy(user);
			medicalJourney.setCreatedAt(new Date());
			medicalJourney.setUpdateAt(new Date());
			break;

		case UPDATE:
			medicalJourney.setEditedBy(user);
			medicalJourney.setUpdateAt(new Date());
			break;

		case DELETE:
			medicalJourney.setDeletedBy(user);
			medicalJourney.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Tache tache) {
		tache.setIsDeleted(false);
		setTransactionDetails(tache, Action.CREATE);
		entityManager.persist(tache);
	}

	public void add(Tache tache, MedicalJourney medicalJourney) {
		tache.setIsDeleted(false);
		setTransactionDetails(tache, Action.CREATE);
		entityManager.persist(tache);
		tache.setMedicalJourney(medicalJourney);
		entityManager.merge(tache);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Tache tache) {
		tache.setIsDeleted(false);
		Tache d = entityManager.merge(tache);
		entityManager.flush();

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Tache tache) {

		tache.setIsDeleted(true);
		Tache m = entityManager.merge(tache);
		entityManager.flush();

	}

	List<Tache> getList() {
		return entityManager.createQuery("select d from Tache d")
				.getResultList();

	}

	public Collection<Tache> getNonDeletedTaches() {

		return entityManager.createQuery(
				"select t from Tache t where t.isDeleted = 'false' ")
				.getResultList();

	}

	public Tache getTacheById(int id) {
		return entityManager.getReference(Tache.class, id);
	}

	public List<Tache> getListByMedicalJourney(MedicalJourney m) {
		return entityManager.createQuery(
				"select d from Tache d where d.isDeleted = 'false' AND d.medicalJourney.id ="
						+ m.getId()).getResultList();
	}

	public Collection<Tache> getCurrentUserTasks() {
		return entityManager.createQuery(
				"select t from Tache t where t.isDeleted = 'false' AND t.ownerAgent ="
						+ session.getUser().getId()).getResultList();
	}

}
