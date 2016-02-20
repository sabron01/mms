package tn.welldone.data;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Doctor;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.model.Treatment;
import tn.welldone.model.User;
import tn.welldone.webSockets.WBTimeEvent;

@Stateless
@Local
public class TreatmentRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;
	
	@Inject  @WBTimeEvent Event<Treatment> treatmentEvent;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Treatment treatment, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			treatment.setAddedBy(user);
			treatment.setCreatedAt(new Date());
			treatment.setUpdateAt(new Date());
			break;

		case UPDATE:
			treatment.setEditedBy(user);
			treatment.setUpdateAt(new Date());
			break;

		case DELETE:
			treatment.setDeletedBy(user);
			treatment.setDeletedAt(new Date());
			break;
		}
	}

	public void add(Treatment treatment) {
		setTransactionDetails(treatment, Action.CREATE);
		treatment.setIsDeleted(false);
		entityManager.persist(treatment);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Treatment add(Treatment treatment, MedicalJourney medicalJourney,
			ServiceProvider clinic, Doctor doctor) {
		setTransactionDetails(treatment, Action.CREATE);
		treatment.setIsDeleted(false);
		entityManager.persist(treatment);
		treatment.setMedicalJourney(medicalJourney);
		treatment.setClinic(clinic);
		treatment.setResponsableDoctor(doctor);
		treatment.setTacheState(TacheState.ACHIEVED);
		Treatment t = entityManager.merge(treatment);
		entityManager.flush();
		return t;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Treatment treatment) {
		setTransactionDetails(treatment, Action.UPDATE);
		treatment.setIsDeleted(false);
		Treatment t = entityManager.merge(treatment);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Treatment treatment) {
		setTransactionDetails(treatment, Action.DELETE);
		treatment.setIsDeleted(true);
		Treatment m = entityManager.merge(treatment);
		entityManager.flush();
	}

	List<Treatment> getList() {
		return entityManager.createQuery("select r from Treatment r")
				.getResultList();
	}

	public List<Treatment> getNonDeletedTreatments() {

		return entityManager.createQuery(
				"select r from Treatment r where r.isDeleted = 'false' ")
				.getResultList();
	}

	public Treatment getTreatmentById(int id) {
		return entityManager.getReference(Treatment.class, id);
	}

	public List<Treatment> getListByMedicalJourney(MedicalJourney m) {
		return entityManager
				.createQuery(
						"select t from Treatment t where t.isDeleted = 'false' AND t.medicalJourney.id ="
								+ m.getId()).getResultList();
	}

}
