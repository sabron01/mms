package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.DoctorRepository;
import tn.welldone.model.Doctor;
import tn.welldone.model.ServiceProvider;

@Stateless
@Local
public class DoctorBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DoctorRepository doctorRepository;

	public Doctor addDoctor(Doctor doctor) {
		doctorRepository.add(doctor);
		return doctor;
	}
	
	public Doctor addDoctor(Doctor doctor,ServiceProvider clinic) {
		Doctor sp = doctorRepository.add(doctor,clinic);		
		return sp;

	}
	
	public Doctor editDoctor(Doctor doctor) {
		doctorRepository.edit(doctor);
		return doctor;
	}
	
	public Doctor deleteDoctor(Doctor doctor) {
		doctorRepository.delete(doctor);
		return doctor;
	}
	
	public Doctor getDoctorById(int id){
		return doctorRepository.getDoctorById( id);
	}
	
	public List<Doctor> getDoctors() {
				
		return doctorRepository.getDoctors();

	}

	public DoctorRepository getDoctorRepository() {
		return doctorRepository;
	}

	public void setDoctorRepository(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}


}
