package tn.welldone.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Treatment extends Tache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 314174806258069153L;
	private String codeTreatment;
	private ServiceProvider clinic;
	private Calendar startDate;
	private Calendar endDate;
	private String descriptionTreatment;
	private Doctor responsableDoctor;

	public String getCodeTreatment() {
		return codeTreatment;
	}

	public void setCodeTreatment(String codeTreatment) {
		this.codeTreatment = codeTreatment;
	}

	@ManyToOne
	public ServiceProvider getClinic() {
		return clinic;
	}

	public void setClinic(ServiceProvider clinic) {
		this.clinic = clinic;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	@ManyToOne
	public Doctor getResponsableDoctor() {
		return responsableDoctor;
	}

	public void setResponsableDoctor(Doctor responsableDoctor) {
		this.responsableDoctor = responsableDoctor;
	}

	public String getDescriptionTreatment() {
		return descriptionTreatment;
	}

	public void setDescriptionTreatment(String descriptionTreatment) {
		this.descriptionTreatment = descriptionTreatment;
	}

}