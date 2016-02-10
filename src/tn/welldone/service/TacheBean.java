package tn.welldone.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.TacheRepository;
import tn.welldone.model.Consultation;
import tn.welldone.model.Displacement;
import tn.welldone.model.Employee;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.MedicalJourneyEmployee;
import tn.welldone.model.Prescription;
import tn.welldone.model.Reservation;
import tn.welldone.model.Service;
import tn.welldone.model.Tache;
import tn.welldone.model.Tache.Action;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.model.Treatment;

@Local
@Stateless
@Named
public class TacheBean implements Serializable {

	private static final long serialVersionUID = 3972230447894034250L;

	@Inject
	TacheRepository tacheRepository;

	@Inject
	private transient Logger logger;

	public Tache addTache(Tache tache) {

		tacheRepository.add(tache);
		return tache;

	}

	private String generateNumber() {
		SecureRandom random = new SecureRandom();
		String rand = new BigInteger(130, random).toString(32);
		return rand;
	}

	public Tache generateTacheForEmployee(MedicalJourney medicalJourney,
			Employee employee, Service service) {
		Tache tache = instanciateTaskByService(service);
		tache.setOwnerAgent(employee.getUser());
		tache.setMedicalJourney(medicalJourney);
		tache.setAction(Action.CREATE);
		tache.setTacheState(TacheState.CREATED);
		tache.setCodeTache("T" + generateNumber());
		
		tacheRepository.add(tache);
		return tache;
	}

	public void createTache(MedicalJourney medicalJourney, Employee employee) {

	}

	public void createTaches(MedicalJourney medicalJourney) {
		for (Employee u : medicalJourney.getAffectedEmployees()) {
			Tache tache = new Tache();
			tache.setOwnerAgent(u.getUser());
			tache.setMedicalJourney(medicalJourney);
			tache.setAction(Action.CREATE);
			tache.setTacheState(TacheState.CREATED);
			tache.setCodeTache("");
			tacheRepository.add(tache);
		}
	}

	public Collection<Tache> getCurrentUserTasks() {
		return tacheRepository.getCurrentUserTasks();
	}

	public Tache editTache(Tache tache) {
		tacheRepository.edit(tache);
		return tache;

	}

	public Tache deleteTache(Tache tache) {
		tacheRepository.delete(tache);
		return tache;

	}

	public Tache getTacheById(int id) {
		return tacheRepository.getTacheById(id);

	}

	public Collection<Tache> getAllTaches() {

		return tacheRepository.getNonDeletedTaches();

	}

	public List<Tache> getListByMedicalJourney(MedicalJourney m) {
		return tacheRepository.getListByMedicalJourney(m);
	}

	public Tache instanciateTaskByService(Service service) {
		switch (service.getId()) {
		case 1:
			return new Treatment();
		case 2:
			return new Reservation();
		case 3:
			return new Displacement();
		case 4:
			return new Consultation();
		case 5:
			return new Displacement();
		case 6:
			return new Prescription();
		default:
			return null;
		}
	}

	public Tache instanciateTaskByEmployee(Employee employee) {
		switch (employee.getFunction().getId()) {

		case 3:
			return new Consultation();
		case 5:
			return new Treatment();
		case 6:
			return new Reservation();
		case 7:
			return new Prescription();
		case 8:
			return new Displacement();
		default:
			return null;

		}
	}

	public Tache instanciateTask(Tache tache) {
		switch (tache.getOwnerAgent().getEmployee().getFunction().getId()) {
		case 3:
			return new Consultation();
		case 5:
			return new Treatment();
		case 6:
			return new Reservation();
		case 7:
			return new Prescription();
		case 8:
			return new Displacement();
		default:
			return null;

		}
	}

}
