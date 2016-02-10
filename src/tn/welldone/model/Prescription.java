package tn.welldone.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Prescription extends Tache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7751358829037571636L;
	private String codePrescription;
	private Doctor responsableDoctor;
	private Calendar date;
	private String description;

	public String getCodePrescription() {
		return codePrescription;
	}

	public void setCodePrescription(String codePrescription) {
		this.codePrescription = codePrescription;
	}

//	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@ManyToOne
	public Doctor getResponsableDoctor() {
		return responsableDoctor;
	}

	public void setResponsableDoctor(Doctor responsableDoctor) {
		this.responsableDoctor = responsableDoctor;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}