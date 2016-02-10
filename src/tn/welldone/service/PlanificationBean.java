package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.MedicalJourneyRepository;
import tn.welldone.data.NotificationRepository;
import tn.welldone.model.Contract;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Notification;
import tn.welldone.model.Notification.Type;

@Remote
@Stateless
@Named
public class PlanificationBean implements Serializable {

	private static final long serialVersionUID = 9045986005423917507L;

	@Inject
	MedicalJourneyRepository medicalJourneyRepository;
	
	@Inject
	NotificationRepository notificationRepository;

	public MedicalJourney getMedicalJourneyById(int id) {
		return medicalJourneyRepository.getMedicalJourneyById(id);

	}

	public List<MedicalJourney> getAllMedicalJourneys() {

		return medicalJourneyRepository.getNonDeletedMedicalJourneys();

	}

	public MedicalJourney addMedicalJourney(MedicalJourney medicalJourney) {
		medicalJourneyRepository.add(medicalJourney);
		Notification notification = new Notification();
		notification.setMedicalJourney(medicalJourney);
		notification.setType(Type.Notification);
		notification.setText("Un séjour a été crée");
		notification.setSubject("");
		notification.setStatus("");
		notificationRepository.add(notification);
		return medicalJourney;
	}

	public MedicalJourney editMedicalJourney(MedicalJourney medicalJourney) {
		medicalJourneyRepository.edit(medicalJourney);
		return medicalJourney;
	}

	public MedicalJourney editMedicalJourney(MedicalJourney medicalJourney,Contract contract) {
		medicalJourneyRepository.edit(medicalJourney,contract);
		return medicalJourney;
	}

	public MedicalJourney deleteMedicalJourney(MedicalJourney medicalJourney) {
		medicalJourneyRepository.delete(medicalJourney);
		return medicalJourney;

	}

}
