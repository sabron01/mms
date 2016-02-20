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
import tn.welldone.model.Booking;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.User;

@Stateless
@Local
public class BookingRepository {
	
	@PersistenceContext(unitName = "mmsPU")
    private EntityManager entityManager;
	
	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Booking booking, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			booking.setAddedBy(user);
			booking.setCreatedAt(new Date());
			booking.setUpdateAt(new Date());
			break;

		case UPDATE:
			booking.setEditedBy(user);
			booking.setUpdateAt(new Date());
			break;

		case DELETE:
			booking.setDeletedBy(user);
			booking.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Booking booking){ 
		setTransactionDetails(booking, Action.CREATE);
		booking.setIsDeleted(false);
		entityManager.persist(booking);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Booking add(Booking booking, ServiceProvider lodgingHost,Location location, MedicalJourney medicalJourney) {
		setTransactionDetails(booking, Action.CREATE);
		booking.setIsDeleted(false);
		entityManager.persist(booking);
		booking.setLodgingHost(lodgingHost);
		booking.setMedicalJourney(medicalJourney);
		Booking r =entityManager.merge(booking);
		entityManager.merge(medicalJourney);
		entityManager.flush();		
		return r;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Booking booking){
		setTransactionDetails(booking, Action.UPDATE);
		booking.setIsDeleted(false);
		Booking d= entityManager.merge(booking);
		entityManager.flush();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Booking booking){
		setTransactionDetails(booking, Action.DELETE);
		booking.setIsDeleted(true);
		Booking m = entityManager.merge(booking);
		entityManager.flush();	
	}
	

     List<Booking> getList(){
		return entityManager.createQuery("select r from Booking r").getResultList();
		
	}
	
	public List<Booking> getNonDeletedBookings(){
		return entityManager.createQuery("select r from Booking r where r.isDeleted = 'false' ").getResultList();
	}
	
	public Booking getBookingById(int id){
		return entityManager.getReference(Booking.class, id);
	}

	
	public List<Booking> getListByMedicalJourney(MedicalJourney m) {
		return entityManager.createQuery("select r from Booking r where r.isDeleted = 'false' AND r.medicalJourney.id ="+ m.getId()).getResultList();
	}

}
