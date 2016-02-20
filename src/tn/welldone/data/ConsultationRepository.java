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
import tn.welldone.model.Consultation;
import tn.welldone.model.Doctor;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.User;

@Stateless
@Local
public class ConsultationRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Consultation consultation, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			consultation.setAddedBy(user);
			consultation.setCreatedAt(new Date());
			consultation.setUpdateAt(new Date());
			break;

		case UPDATE:
			consultation.setEditedBy(user);
			consultation.setUpdateAt(new Date());
			break;

		case DELETE:
			consultation.setDeletedBy(user);
			consultation.setDeletedAt(new Date());
			break;
		}

	}

	public List<Consultation> getListByMedicalJourney(MedicalJourney m) {
		return entityManager
				.createQuery(
						"select c from Consultation c where c.isDeleted = 'false' AND c.medicalJourney.id ="
								+ m.getId()).getResultList();
	}

	public void add(Consultation consultation) {

		consultation.setIsDeleted(false);
		entityManager.persist(consultation);
		Logger logger = Logger.getLogger(this.getClass());
		logger.info("consultation Saved !!");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Consultation add(Consultation consultation,
			MedicalJourney medicalJourney, Doctor doctor, Location location) {
		entityManager.persist(location);
		consultation.setIsDeleted(false);
		consultation.setAddress(location);
		entityManager.persist(consultation);
		consultation.setMedicalJourney(medicalJourney);
		consultation.setDoctor(doctor);
		Consultation c = entityManager.merge(consultation);
		entityManager.merge(medicalJourney);
		entityManager.flush();
		return c;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Consultation consultation) {
		consultation.setIsDeleted(false);
		setTransactionDetails(consultation, Action.UPDATE);
		Consultation d = entityManager.merge(consultation);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Consultation consultation) {
		consultation.setIsDeleted(true);
		Consultation m = entityManager.merge(consultation);
		entityManager.flush();
	}

	List<Consultation> getList() {
		return entityManager.createQuery("select d from Consultation d")
				.getResultList();

	}

	public List<Consultation> getNonDeletedConsultations() {

		Logger logger = Logger.getLogger(this.getClass());
		logger.info("Consultation List Selected !!");
		return entityManager.createQuery(
				"select d from Consultation d where d.isDeleted = 'false' ")
				.getResultList();

	}

	public Consultation getConsultationById(int id) {
		return entityManager.getReference(Consultation.class, id);
	}

}
