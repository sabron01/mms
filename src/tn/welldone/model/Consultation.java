package tn.welldone.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Consultation extends Tache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2567457414471137962L;
	private String codeConsultation;
	private Calendar dateConsultation;
	private Location address;
	private Doctor doctor;
	private String details;

	
	public String getCodeConsultation() {
		return codeConsultation;
	}

	public void setCodeConsultation(String codeConsultation) {
		this.codeConsultation = codeConsultation;
	}

	public Calendar getDateConsultation() {
		return dateConsultation;
	}

	public void setDateConsultation(Calendar dateConsultation) {
		this.dateConsultation = dateConsultation;
	}

	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

//	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@ManyToOne
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}