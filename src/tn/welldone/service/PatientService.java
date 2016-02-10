package tn.welldone.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.PatientRepository;
import tn.welldone.model.Location;
import tn.welldone.model.Patient;
import tn.welldone.model.PhoneNumber;

@Stateless
@Local
@Named
public class PatientService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998111900854981915L;

	@Inject
	PatientRepository patientReposotiry;

	public List<Patient> getPatients() {
		return patientReposotiry.getCountries();
	}

	public Patient getPatientById(int id) {
		return patientReposotiry.getPatientById(id);
	}

	public Patient addPatient(Patient country) {
		patientReposotiry.add(country);
		return country;
	}
	
	public Patient addPatient(Patient patient, Collection<Location> addresses,
			Collection<PhoneNumber> phoneNumbers) {
		patientReposotiry.add(patient,addresses,phoneNumbers);
		return patient;
	}
	
	public Patient editPatient(Patient country) {
		return patientReposotiry.edit(country);

	}
	
	public Patient editPatient(Patient patient,
			Collection<Location> addresses, Collection<PhoneNumber> phoneNumbers) {
		patientReposotiry.edit(patient,addresses,phoneNumbers);
		return patient;
	}

	public Patient deletePatient(Patient c) {
		patientReposotiry.delete(c);
		return c;
	}







}
