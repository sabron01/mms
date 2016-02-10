package tn.welldone.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Doctor extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1392652592247328315L;
	private String speciality;
	private ServiceProvider clinic;

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@ManyToOne
	public ServiceProvider getClinic() {
		return clinic;
	}

	public void setClinic(ServiceProvider clinic) {
		this.clinic = clinic;
	}
}
