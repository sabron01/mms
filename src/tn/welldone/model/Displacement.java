package tn.welldone.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Displacement extends Tache implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -990774325623029847L;
	private Calendar startDate;
	private Calendar endDate;
	private Location startPoint;
	private Location endPoint;
	private ServiceProvider provider;
	private String description;
	private Employee responsibleAgent;

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

	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	public Location getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Location startPoint) {
		this.startPoint = startPoint;
	}

	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	public Location getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Location endPoint) {
		this.endPoint = endPoint;
	}

//	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@ManyToOne
	public ServiceProvider getProvider() {
		return provider;
	}

	public void setProvider(ServiceProvider provider) {
		this.provider = provider;
	}

	public Employee getResponsibleAgent() {
		return responsibleAgent;
	}

	public void setResponsibleAgent(Employee responsibleAgent) {
		this.responsibleAgent = responsibleAgent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}