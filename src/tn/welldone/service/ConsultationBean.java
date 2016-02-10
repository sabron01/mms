package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.ConsultationRepository;
import tn.welldone.model.Consultation;
import tn.welldone.model.Doctor;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;

@Local
@Stateless
@Named
public class ConsultationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4285266479745894576L;

	@Inject
	ConsultationRepository consultationRepository;

	public List<Consultation> getListByMedicalJourney(MedicalJourney m) {
		return consultationRepository.getListByMedicalJourney(m);
	}

	public Consultation addConsultation(Consultation consultation) {
		consultationRepository.add(consultation);
		return consultation;

	}

	public Consultation addConsultation(Consultation consultation,MedicalJourney medicalJourney, Doctor doctor, Location location) {
		consultationRepository.add(consultation, medicalJourney, doctor,location);
		return null;
	}

	public Consultation editConsultation(Consultation consultation) {
		consultationRepository.edit(consultation);
		return consultation;

	}

	public Consultation deleteConsultation(Consultation consultation) {
		consultationRepository.delete(consultation);
		return consultation;

	}

	public Consultation getConsultationById(int id) {
		return consultationRepository.getConsultationById(id);

	}

	public List<Consultation> getAllConsultations() {

		return consultationRepository.getNonDeletedConsultations();

	}

}
