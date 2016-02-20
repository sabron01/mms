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
import tn.welldone.model.Displacement;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.User;

@Stateless
@Local
public class DisplacementRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Displacement displacement, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			displacement.setAddedBy(user);
			displacement.setCreatedAt(new Date());
			displacement.setUpdateAt(new Date());
			break;

		case UPDATE:
			displacement.setEditedBy(user);
			displacement.setUpdateAt(new Date());
			break;

		case DELETE:
			displacement.setDeletedBy(user);
			displacement.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Displacement displacement) {
		setTransactionDetails(displacement, Action.CREATE);
		displacement.setIsDeleted(false);
		entityManager.persist(displacement);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Displacement add(Displacement displacement,
			MedicalJourney medicalJourney, ServiceProvider agency,
			Location startLocation, Location endLocation) {
		displacement.setIsDeleted(false);
		entityManager.persist(endLocation);
		entityManager.persist(startLocation);
		setTransactionDetails(displacement, Action.CREATE);
		entityManager.persist(displacement);
		displacement.setMedicalJourney(medicalJourney);
		displacement.setProvider(agency);
		displacement.setStartPoint(startLocation);
		displacement.setEndPoint(endLocation);
		Displacement d = entityManager.merge(displacement);
		entityManager.merge(medicalJourney);
		entityManager.flush();
		return d;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Displacement displacement) {
		setTransactionDetails(displacement, Action.UPDATE);
		entityManager.merge(displacement.getMedicalJourney());
		//entityManager.merge(displacement.getEndPoint());
		//entityManager.merge(displacement.getStartPoint());
		
		displacement.setIsDeleted(false);
		Displacement d = entityManager.merge(displacement);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Displacement displacement) {
		setTransactionDetails(displacement, Action.DELETE);
		displacement.setIsDeleted(true);
		Displacement m = entityManager.merge(displacement);
		entityManager.flush();

	}

	List<Displacement> getList() {
		return entityManager.createQuery("select d from Displacement d")
				.getResultList();

	}

	public List<Displacement> getNonDeletedDisplacements() {
		return entityManager.createQuery(
				"select d from Displacement d where d.isDeleted = 'false' ")
				.getResultList();
	}

	public Displacement getDisplacementById(int id) {
		return entityManager.getReference(Displacement.class, id);
	}

	public List<Displacement> getListByMedicalJourney(MedicalJourney m) {
		return entityManager
				.createQuery(
						"select d from Displacement d where d.isDeleted = 'false' AND d.medicalJourney.id ="
								+ m.getId()).getResultList();
	}

}
