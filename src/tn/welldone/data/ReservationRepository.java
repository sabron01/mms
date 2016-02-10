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
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Reservation;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.User;

@Stateless
@Local
public class ReservationRepository {
	
	@PersistenceContext(unitName = "mmsPU")
    private EntityManager entityManager;
	
	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Reservation reservation, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			reservation.setAddedBy(user);
			reservation.setCreatedAt(new Date());
			reservation.setUpdateAt(new Date());
			break;

		case UPDATE:
			reservation.setEditedBy(user);
			reservation.setUpdateAt(new Date());
			break;

		case DELETE:
			reservation.setDeletedBy(user);
			reservation.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Reservation reservation){ 
		setTransactionDetails(reservation, Action.CREATE);
		reservation.setIsDeleted(false);
		entityManager.persist(reservation);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Reservation add(Reservation reservation, ServiceProvider lodgingHost,Location location, MedicalJourney medicalJourney) {
		setTransactionDetails(reservation, Action.CREATE);
		reservation.setIsDeleted(false);
		entityManager.persist(reservation);
		reservation.setLodgingHost(lodgingHost);
		reservation.setMedicalJourney(medicalJourney);
		Reservation r =entityManager.merge(reservation);
		entityManager.merge(medicalJourney);
		entityManager.flush();		
		return r;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Reservation reservation){
		setTransactionDetails(reservation, Action.UPDATE);
		reservation.setIsDeleted(false);
		Reservation d= entityManager.merge(reservation);
		entityManager.flush();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Reservation reservation){
		setTransactionDetails(reservation, Action.DELETE);
		reservation.setIsDeleted(true);
		Reservation m = entityManager.merge(reservation);
		entityManager.flush();	
	}
	

     List<Reservation> getList(){
		return entityManager.createQuery("select r from Reservation r").getResultList();
		
	}
	
	public List<Reservation> getNonDeletedReservations(){
		return entityManager.createQuery("select r from Reservation r where r.isDeleted = 'false' ").getResultList();
	}
	
	public Reservation getReservationById(int id){
		return entityManager.getReference(Reservation.class, id);
	}

	
	public List<Reservation> getListByMedicalJourney(MedicalJourney m) {
		return entityManager.createQuery("select r from Reservation r where r.isDeleted = 'false' AND r.medicalJourney.id ="+ m.getId()).getResultList();
	}

}
