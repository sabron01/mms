package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.BookingRepository;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Booking;
import tn.welldone.model.ServiceProvider;

@Local
@Stateless
@Named
public class BookingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6142015140605519219L;
	
	@Inject
	BookingRepository bookingRepository;

	public Booking addBooking(Booking booking) {

		bookingRepository.add(booking);

		return booking;

	}

	public Booking addBooking(Booking booking, ServiceProvider lodgingHost, Location location, MedicalJourney medicalJourney) {
		Booking t = bookingRepository.add(booking,lodgingHost,location,medicalJourney);
		return t;
	}
	
	public Booking editBooking(Booking booking) {
		bookingRepository.edit(booking);
		return booking;

	}

	public Booking deleteBooking(Booking booking) {
		bookingRepository.delete(booking);
		return booking;

	}

	public Booking getBookingById(int id) {
		return bookingRepository.getBookingById(id);

	}

	public List<Booking> getAllBookings() {

		return bookingRepository.getNonDeletedBookings();

	}

	
	
	public List<Booking> getListByMedicalJourney(MedicalJourney m) {
		return bookingRepository.getListByMedicalJourney(m);
	}

}
