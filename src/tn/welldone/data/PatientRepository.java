package tn.welldone.data;

import java.util.ArrayList;
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

import tn.welldone.controller.SessionUser;
import tn.welldone.data.EmployeeRepository.Action;
import tn.welldone.model.Employee;
import tn.welldone.model.Location;
import tn.welldone.model.Patient;
import tn.welldone.model.PhoneNumber;
import tn.welldone.model.User;

@Stateless
@Local
public class PatientRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Patient patient, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			patient.setAddedBy(user);
			patient.setCreatedAt(new Date());
			patient.setUpdateAt(new Date());
			break;

		case UPDATE:
			patient.setEditedBy(user);
			patient.setUpdateAt(new Date());
			break;

		case DELETE:
			patient.setDeletedBy(user);
			patient.setDeletedAt(new Date());
			break;
		}

	}

	public void setTransactionDetails(PhoneNumber phone, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			phone.setAddedBy(user);
			phone.setCreatedAt(new Date());
			phone.setUpdateAt(new Date());
			break;

		case UPDATE:
			phone.setEditedBy(user);
			phone.setUpdateAt(new Date());
			break;

		case DELETE:
			phone.setDeletedBy(user);
			phone.setDeletedAt(new Date());
			break;
		}

	}

	public void setTransactionDetails(Location location, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			location.setAddedBy(user);
			location.setCreatedAt(new Date());
			location.setUpdateAt(new Date());
			break;

		case UPDATE:
			location.setEditedBy(user);
			location.setUpdateAt(new Date());
			break;

		case DELETE:
			location.setDeletedBy(user);
			location.setDeletedAt(new Date());
			break;
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Patient patient) {
		setTransactionDetails(patient, Action.CREATE);
		patient.setIsDeleted(false);
		entityManager.persist(patient);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Patient add(Patient patient, Collection<Location> addresses,
			Collection<PhoneNumber> phoneNumbers) {
		setTransactionDetails(patient, Action.CREATE);
		patient.setIsDeleted(false);
		for (PhoneNumber phone : phoneNumbers) {
			setTransactionDetails(phone, Action.CREATE);
			entityManager.persist(phone);
		}
		for (Location location : addresses) {
			setTransactionDetails(location, Action.CREATE);
			entityManager.persist(location);
		}
		entityManager.persist(patient);
		entityManager.flush();
		return patient;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Patient edit(Patient patient, Collection<Location> addresses,
			Collection<PhoneNumber> phoneNumbers) {
		

		Collection<PhoneNumber> phoneNumber = new ArrayList<PhoneNumber>();
		Collection<Location> locations = new ArrayList<Location>();
		setTransactionDetails(patient, Action.UPDATE);
		for(PhoneNumber phone : phoneNumbers) {
			setTransactionDetails(phone, Action.UPDATE);
			PhoneNumber p = entityManager.merge(phone);
			phoneNumber.add(p);
		}
		for(Location location : addresses) {
			setTransactionDetails(location, Action.UPDATE);
			Location l =entityManager.merge(location);
			locations.add(l);
		}
		patient.setIsDeleted(false);
		patient.setAddresses(locations);
		patient.setPhoneNumbers(phoneNumber);
		Patient p = entityManager.merge(patient);
		entityManager.flush();
		return p;
		
		
		
	}


	public Patient getPatientById(int id) {
		return entityManager.getReference(Patient.class, id);
	}

	public List<Patient> getCountries() {
		return entityManager.createQuery(
				"select c from Patient c where c.isDeleted = 'false' ")
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Patient edit(Patient patient) {
		setTransactionDetails(patient, Action.UPDATE);
		patient.setIsDeleted(false);
		Patient c = entityManager.merge(patient);
		entityManager.flush();
		return c;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Patient patient) {
		setTransactionDetails(patient, Action.DELETE);
		// Option 1
		patient.setIsDeleted(true);
		Patient a = entityManager.merge(patient);

		// Option 2
		// entityManager.remove(service);

		entityManager.flush();
	}

}
