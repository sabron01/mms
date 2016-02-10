package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.PrescriptionRepository;
import tn.welldone.model.Doctor;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Prescription;

@Local
@Stateless
public class PrescriptionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5287718575753875509L;

	@Inject
	PrescriptionRepository prescriptionRepository;

	public Prescription addPrescription(Prescription prescription) {
		prescriptionRepository.add(prescription);
		return prescription;

	}

	public Prescription addPrescription(Prescription prescription,
			MedicalJourney medicalJourney, Doctor doctor) {
		prescriptionRepository.add(prescription, medicalJourney, doctor);
		return null;
	}

	public Prescription editPrescription(Prescription prescription) {
		prescriptionRepository.edit(prescription);
		return prescription;

	}

	public Prescription deletePrescription(Prescription prescription) {
		prescriptionRepository.delete(prescription);
		return prescription;

	}

	public Prescription getPrescriptionById(int id) {
		return prescriptionRepository.getPrescriptionById(id);

	}

	public List<Prescription> getAllPrescriptions() {

		return prescriptionRepository.getNonDeletedPrescriptions();

	}

	public List<Prescription> getListByMedicalJourney(MedicalJourney m) {
		return prescriptionRepository.getListByMedicalJourney(m);
	}

}
