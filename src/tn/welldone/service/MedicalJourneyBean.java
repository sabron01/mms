package tn.welldone.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.MedicalJourneyRepository;
import tn.welldone.model.Contract;
import tn.welldone.model.Employee;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.MedicalJourneyEmployeeService;
import tn.welldone.model.Service;
import tn.welldone.model.Tache;

@Local
@Stateless
@Named
public class MedicalJourneyBean implements Serializable {

	private static final long serialVersionUID = 9045986005423917507L;

	@Inject
	MedicalJourneyRepository medicalJourneyRepository;

	@Inject
	NotificationBean notificationBean;

	@Inject
	TacheBean tacheBean;

	public MedicalJourney getMedicalJourneyById(int id) {
		return medicalJourneyRepository.getMedicalJourneyById(id);

	}

	public List<MedicalJourney> getAllMedicalJourneys() {

		return medicalJourneyRepository.getNonDeletedMedicalJourneys();

	}

	public void addMedicalJourney(MedicalJourney medicalJourney) {
		medicalJourneyRepository.add(medicalJourney);
		notificationBean.addNotification(medicalJourney);
	}
	
	public void updateMedicalJourneyEmployees(
			MedicalJourney selectedMedicalJourney, Employee employee) {
	}

	public Tache generateTacheForEmployee(MedicalJourney medicalJourney,
			Employee employee, Service service) {
		return tacheBean.generateTacheForEmployee(medicalJourney, employee,service);
	}

	public void updateAffectedEmployees(MedicalJourney medicalJourney) {
		tacheBean.createTaches(medicalJourney);
	}
	
	public void updateMedicalJourney(MedicalJourney medicalJourney) {
		medicalJourneyRepository.update(medicalJourney);
	}

	public MedicalJourney editMedicalJourney(MedicalJourney medicalJourney) {
		medicalJourneyRepository.edit(medicalJourney);
		return medicalJourney;
	}

	public MedicalJourney editMedicalJourney(MedicalJourney medicalJourney,
			Contract contract) {
		medicalJourneyRepository.edit(medicalJourney, contract);
		return medicalJourney;
	}
	
	public void removeAffectedEmployee(MedicalJourneyEmployeeService mse) {
		medicalJourneyRepository.removeAffectedEmployee(mse);
	}

	public void affectEmployee(
			MedicalJourneyEmployeeService medicalJourneyEmployeeService) {
		medicalJourneyRepository.affectEmployee(medicalJourneyEmployeeService);
		
	}
	
	public MedicalJourney deleteMedicalJourney(MedicalJourney medicalJourney) {
		medicalJourneyRepository.delete(medicalJourney);
		return medicalJourney;

	}

	public Collection<MedicalJourney> getAffectedMedicalJourneys() {
		return medicalJourneyRepository.getAffectedMedicalJourneys();
	}

	public Collection<MedicalJourney> getCurrentMedicalJourneys() {
		return medicalJourneyRepository.getCurrentMedicalJourneys();
	}






}
