package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import tn.welldone.interceptors.AuthorizationSecurityInterceptor;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Booking;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.Tache;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.service.BookingBean;

@Named("BookingController")
@ViewScoped
@RequestScoped
@RolesAllowed("Hebergement")
public class BookingController implements
		Serializable {

	private static final long serialVersionUID = 4158328378543554775L;

	@EJB
	private BookingBean bookingBean;
	
	@Inject
	TaskController taskController;

	private Booking booking = new Booking();

	private Booking selectedBooking = new Booking();
	
	private MedicalJourney medicalJourney = new MedicalJourney();

	private ServiceProvider lodgingHost = new ServiceProvider();

	private Location location = new Location();

	private Float oldBookingAmount = 0f;

	private List<Booking> list;

	@javax.annotation.PostConstruct
	public void init() {
		setList(bookingBean.getAllBookings());
	}

	public String createNewBooking() {
		return "addBooking.faces";
	}
	
	public String createNewTaskBooking(Tache tache) {
		taskController.setTache(tache);
		Booking r = bookingBean.getBookingById(tache.getId());
		setBooking(r);
		return "/booking/addTaskBooking.faces";
	}
	
	public String createTaskRservation() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		oldBookingAmount = booking.getAmount();
		if (oldBookingAmount == null)
			oldBookingAmount = 0f;
		Float newAmount = oldAmount + oldBookingAmount;
		medicalJourney.setAmount(newAmount);
		booking.setAction(taskController.getTache().getAction());
		booking.setMedicalJourney(medicalJourney);
		booking.setCodeTache(taskController.getTache().getCodeTache());
		booking.setOwnerAgent(taskController.getTache().getOwnerAgent());
		selectedBooking.setLodgingHost(lodgingHost);
		selectedBooking.setLocation(location);
		booking.setTacheState(TacheState.PLANNED);
		bookingBean.editBooking(booking);
		taskController.setTache(new Tache());
		return "listBookings.faces?faces-redirect=true";
	}

	public String createBooking() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		Float newAmount = oldAmount + booking.getAmount();
		medicalJourney.setAmount(newAmount);
		booking.setMedicalJourney(medicalJourney);
		booking.setLodgingHost(lodgingHost);
		booking.setLocation(location);
		booking = bookingBean.addBooking(booking);
		return "listBookings.faces?faces-redirect=true";
	}

	public String showEditBooking(int id) {
		Booking booking = bookingBean.getBookingById(id);
		oldBookingAmount = booking.getAmount();
		setSelectedBooking(booking);
		setLodgingHost(booking.getLodgingHost());
		setLocation(booking.getLocation());
		return "showEditBooking.faces";
	}

	public String deleteBooking(int id) {
		Booking d = bookingBean.getBookingById(id);
		bookingBean.deleteBooking(d);
		return "listBookings.faces?faces-redirect=true";
	}

	public String editBooking() {
		Float oldAmount = medicalJourney.getAmount();
		Float newAmount = oldAmount - oldBookingAmount
				+ selectedBooking.getAmount();
		selectedBooking.setAmount(newAmount);
		selectedBooking.setMedicalJourney(medicalJourney);
		selectedBooking.setLodgingHost(lodgingHost);
		selectedBooking.setLocation(location);
		bookingBean.editBooking(selectedBooking);
		return "listBookings.faces?faces-redirect=true";
	}

	public String listBookings() {
		return "listBookings.faces?faces-redirect=true";
	}

	public List<Booking> getList() {
		return list;
	}

	public void setList(List<Booking> list) {
		this.list = list;
	}

	public BookingBean getBookingBean() {
		return bookingBean;
	}

	public void setBookingBean(BookingBean bookingBean) {
		this.bookingBean = bookingBean;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Booking getSelectedBooking() {
		return selectedBooking;
	}

	public void setSelectedBooking(Booking selectedBooking) {
		this.selectedBooking = selectedBooking;
	}

	public ServiceProvider getLodgingHost() {
		return lodgingHost;
	}

	public void setLodgingHost(ServiceProvider lodgingHost) {
		this.lodgingHost = lodgingHost;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Float getOldBookingAmount() {
		return oldBookingAmount;
	}

	public void setOldBookingAmount(Float oldBookingAmount) {
		this.oldBookingAmount = oldBookingAmount;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

}
