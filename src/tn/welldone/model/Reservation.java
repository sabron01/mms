package tn.welldone.model;


import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Reservation extends Tache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4299507500012558021L;
	private ServiceProvider travelAgency;
	private int personCount;
	private String isRoundTrip; 
	private String description;
	private Calendar departureDate;
	private Calendar returnDate;
	private ServiceProvider startAirport;
	private ServiceProvider destinationAirport;

	@ManyToOne
	public ServiceProvider getTravelAgency() {
		return travelAgency;
	}

	public void setTravelAgency(ServiceProvider travelAgency) {
		this.travelAgency = travelAgency;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	@ManyToOne
	public ServiceProvider getStartAirport() {
		return startAirport;
	}

	public void setStartAirport(ServiceProvider startAirport) {
		this.startAirport = startAirport;
	}

	@ManyToOne	
	public ServiceProvider getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(ServiceProvider destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
	
	public Boolean getIsRoundTrip() {
		if (isRoundTrip == null)
			return null;
		return isRoundTrip == "Y" ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsRoundTrip(Boolean active) {
		if (active == null) {
			this.isRoundTrip = null;
		} else {
			this.isRoundTrip = active == true ? "Y" : "N";
		}
	}

	public Calendar getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Calendar departureDate) {
		this.departureDate = departureDate;
	}

	public Calendar getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Calendar returnDate) {
		this.returnDate = returnDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
