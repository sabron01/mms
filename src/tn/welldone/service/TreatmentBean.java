package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.TreatmentRepository;
import tn.welldone.model.Doctor;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.Tache;
import tn.welldone.model.Treatment;
import tn.welldone.model.Tache.TacheState;

@Local
@Stateless
@Named
public class TreatmentBean implements Serializable {

	private static final long serialVersionUID = 6142015140605519219L;

	@Inject
	TreatmentRepository treatmentRepository;

	public Treatment addTreatment(Treatment reservation) {

		treatmentRepository.add(reservation);

		return reservation;

	}

	public Treatment addTreatment(Treatment treatment,
			MedicalJourney medicalJourney, ServiceProvider clinic, Doctor doctor) {
		Treatment t = treatmentRepository.add(treatment, medicalJourney,
				clinic, doctor);
		return t;
	}

	public Treatment editTreatment(Treatment treatment) {
		treatmentRepository.edit(treatment);
		return treatment;

	}

	public Treatment deleteTreatment(Treatment reservation) {
		treatmentRepository.delete(reservation);
		return reservation;

	}

	public Treatment getTreatmentById(int id) {
		return treatmentRepository.getTreatmentById(id);

	}

	public List<Treatment> getAllTreatments() {

		return treatmentRepository.getNonDeletedTreatments();

	}

	public List<Treatment> getListByMedicalJourney(MedicalJourney m) {
		return treatmentRepository.getListByMedicalJourney(m);
	}

}
