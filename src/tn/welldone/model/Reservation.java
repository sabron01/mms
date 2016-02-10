package tn.welldone.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Reservation extends Tache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2039758655769130289L;
	private ServiceProvider lodgingHost;
	private int personCount;
	private String desription;
	private Calendar startDate;
	private Calendar endDate;
	private Location location;

//	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@ManyToOne
	public ServiceProvider getLodgingHost() {
		return lodgingHost;
	}

	public void setLodgingHost(ServiceProvider lodgingHost) {
		this.lodgingHost = lodgingHost;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public String getDesription() {
		return desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

//	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@ManyToOne
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}