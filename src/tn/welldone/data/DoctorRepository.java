package tn.welldone.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.model.Doctor;
import tn.welldone.model.ServiceProvider;


@Stateless
@Local
public class DoctorRepository implements Serializable {

	private static final long serialVersionUID = 6786686639547576419L;
	
	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Doctor doctor) {
		doctor.setIsDeleted(false);
		entityManager.persist(doctor);
		entityManager.flush();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Doctor add(Doctor doctor,ServiceProvider clinic) {
		doctor.setIsDeleted(false);
		entityManager.persist(doctor);
		doctor.setClinic(clinic);
		Doctor sp =entityManager.merge(doctor);
		entityManager.flush();		
		return sp;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Doctor doctor) {
		doctor.setIsDeleted(false);
		Doctor d = entityManager.merge(doctor);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Doctor doctor) {
		// Option 1
		doctor.setIsDeleted(true);
		Doctor d = entityManager.merge(doctor);
		entityManager.flush();
	}

	public List<Doctor> getList() {
		return entityManager.createQuery("select d from Doctor d")
				.getResultList();

	}

	public List<Doctor> getDoctors() {
		return entityManager.createQuery(
				"select d from Doctor d where d.isDeleted = 'false' ")
				.getResultList();

	}

	public Doctor getDoctorById(int id) {
		return entityManager.getReference(Doctor.class, id);
	}

}
