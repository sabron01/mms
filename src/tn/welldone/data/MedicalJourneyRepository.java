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
import javax.transaction.Transactional;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Contract;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.MedicalJourneyEmployeeService;
import tn.welldone.model.User;

@Stateless
@Local
public class MedicalJourneyRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
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

	public void add(MedicalJourney medicalJourney) {
		setTransactionDetails(medicalJourney, Action.CREATE);
		medicalJourney.setIsDeleted(false);
		entityManager.persist(medicalJourney);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(MedicalJourney medicalJourney) {
		medicalJourney.setIsDeleted(false);
		setTransactionDetails(medicalJourney, Action.UPDATE);
		MedicalJourney m = entityManager.merge(medicalJourney);
		entityManager.flush();
	}

	public void update(MedicalJourney medicalJourney) {
		medicalJourney.setIsDeleted(false);
		setTransactionDetails(medicalJourney, Action.UPDATE);
		MedicalJourney m = entityManager.merge(medicalJourney);
		entityManager.flush();
	}

	public void edit(MedicalJourney medicalJourney, Contract contract) {
		setTransactionDetails(medicalJourney, Action.UPDATE);
		medicalJourney.setIsDeleted(false);
		entityManager.merge(medicalJourney);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(MedicalJourney medicalJourney) {
		setTransactionDetails(medicalJourney, Action.DELETE);
		medicalJourney.setIsDeleted(true);
		MedicalJourney m = entityManager.merge(medicalJourney);
		entityManager.flush();
	}

	List<MedicalJourney> getList() {
		return entityManager.createQuery("select m from MedicalJourney m")
				.getResultList();

	}

	public List<MedicalJourney> getNonDeletedMedicalJourneys() {
		return entityManager.createQuery(
				"select m from MedicalJourney m where m.isDeleted = 'false' ")
				.getResultList();

	}

	public MedicalJourney getMedicalJourneyById(int id) {
		return entityManager.getReference(MedicalJourney.class, id);
	}

	public Collection<MedicalJourney> getAvailableMedicalJourneys() {
		return entityManager.createQuery(
				"select m from MedicalJourney m where m.isDeleted = 'false' ")
				.getResultList();
	}

	public Collection<MedicalJourney> getAffectedMedicalJourneys() {
		return session.getUser().getEmployee().getManagedMedicalJourneys();
	}

	public Collection<MedicalJourney> getCurrentMedicalJourneys() {
		return entityManager.createQuery(
				"select m from MedicalJourney m where m.isDeleted = 'false' and m.closeDate > current_date ")
				.getResultList();
	}

	public void removeAffectedEmployee(MedicalJourneyEmployeeService mse) {
		entityManager.remove(mse);
		entityManager.flush();
	}

	public void affectEmployee(
			MedicalJourneyEmployeeService medicalJourneyEmployeeService) {
		entityManager.persist(medicalJourneyEmployeeService);
		entityManager.flush();
		
	}

}
