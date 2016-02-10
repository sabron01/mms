package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.ReservationRepository;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Reservation;
import tn.welldone.model.ServiceProvider;

@Local
@Stateless
@Named
public class ReservationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6142015140605519219L;
	
	@Inject
	ReservationRepository reservationRepository;

	public Reservation addReservation(Reservation reservation) {

		reservationRepository.add(reservation);

		return reservation;

	}

	public Reservation addReservation(Reservation reservation, ServiceProvider lodgingHost, Location location, MedicalJourney medicalJourney) {
		Reservation t = reservationRepository.add(reservation,lodgingHost,location,medicalJourney);
		return t;
	}
	
	public Reservation editReservation(Reservation reservation) {
		reservationRepository.edit(reservation);
		return reservation;

	}

	public Reservation deleteReservation(Reservation reservation) {
		reservationRepository.delete(reservation);
		return reservation;

	}

	public Reservation getReservationById(int id) {
		return reservationRepository.getReservationById(id);

	}

	public List<Reservation> getAllReservations() {

		return reservationRepository.getNonDeletedReservations();

	}

	
	
	public List<Reservation> getListByMedicalJourney(MedicalJourney m) {
		return reservationRepository.getListByMedicalJourney(m);
	}

}
