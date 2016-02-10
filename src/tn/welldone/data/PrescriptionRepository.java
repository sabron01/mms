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

import org.jboss.logging.Logger;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Doctor;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Prescription;
import tn.welldone.model.User;

@Stateless
@Local
public class PrescriptionRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;
	
	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Prescription prescription,
			Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			prescription.setAddedBy(user);
			prescription.setCreatedAt(new Date());
			prescription.setUpdateAt(new Date());
			break;

		case UPDATE:
			prescription.setEditedBy(user);
			prescription.setUpdateAt(new Date());
			break;

		case DELETE:
			prescription.setDeletedBy(user);
			prescription.setDeletedAt(new Date());
			break;
		}

	}


	public void add(Prescription prescription) {
		setTransactionDetails(prescription, Action.CREATE);
		prescription.setIsDeleted(false);
		entityManager.persist(prescription);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Prescription add(Prescription prescription,
			MedicalJourney medicalJourney, Doctor doctor) {
		setTransactionDetails(prescription, Action.CREATE);
		prescription.setIsDeleted(false);
		entityManager.persist(prescription);
		prescription.setMedicalJourney(medicalJourney);
		prescription.setResponsableDoctor(doctor);
		Prescription c = entityManager.merge(prescription);
		entityManager.merge(medicalJourney);
		entityManager.flush();
		return c;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Prescription prescription) {
		setTransactionDetails(prescription, Action.UPDATE);
		prescription.setIsDeleted(false);
		Prescription d = entityManager.merge(prescription);
		entityManager.flush();

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Prescription prescription) {
		setTransactionDetails(prescription, Action.DELETE);
		prescription.setIsDeleted(true);
		Prescription m = entityManager.merge(prescription);
		entityManager.flush();
	}

	List<Prescription> getList() {
		return entityManager.createQuery("select d from Prescription d")
				.getResultList();

	}

	public List<Prescription> getNonDeletedPrescriptions() {

		Logger logger = Logger.getLogger(this.getClass());
		logger.info("Prescription List Selected !!");
		return entityManager.createQuery(
				"select d from Prescription d where d.isDeleted = 'false' ")
				.getResultList();

	}

	public Prescription getPrescriptionById(int id) {
		return entityManager.getReference(Prescription.class, id);
	}

	public List<Prescription> getListByMedicalJourney(MedicalJourney m) {
		return entityManager
				.createQuery(
						"select p from Prescription p where p.isDeleted = 'false' AND p.medicalJourney.id ="
								+ m.getId()).getResultList();
	}
}
