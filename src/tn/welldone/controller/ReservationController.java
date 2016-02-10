package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Reservation;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.Tache;
import tn.welldone.model.Treatment;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.service.ReservationBean;

@Named("ReservationController")
@ViewScoped
@RequestScoped
public class ReservationController implements
		Serializable {

	private static final long serialVersionUID = 4158328378543554775L;

	@EJB
	private ReservationBean reservationBean;
	
	@Inject
	TaskController taskController;

	private Reservation reservation = new Reservation();

	private Reservation selectedReservation = new Reservation();
	
	private MedicalJourney medicalJourney = new MedicalJourney();

	private ServiceProvider lodgingHost = new ServiceProvider();

	private Location location = new Location();

	private Float oldReservationAmount = 0f;

	private List<Reservation> list;

	@javax.annotation.PostConstruct
	public void init() {
		setList(reservationBean.getAllReservations());
	}

	public String createNewReservation() {
		return "addReservation.faces";
	}
	
	public String createNewTaskReservation(Tache tache) {
		taskController.setTache(tache);
		Reservation r = reservationBean.getReservationById(tache.getId());
		setReservation(r);
		return "/reservation/addTaskReservation.faces";
	}
	
	public String createTaskRservation() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		oldReservationAmount = reservation.getAmount();
		if (oldReservationAmount == null)
			oldReservationAmount = 0f;
		Float newAmount = oldAmount + oldReservationAmount;
		medicalJourney.setAmount(newAmount);
		reservation.setAction(taskController.getTache().getAction());
		reservation.setMedicalJourney(medicalJourney);
		reservation.setCodeTache(taskController.getTache().getCodeTache());
		reservation.setOwnerAgent(taskController.getTache().getOwnerAgent());
		selectedReservation.setLodgingHost(lodgingHost);
		selectedReservation.setLocation(location);
		reservation.setTacheState(TacheState.PLANNED);
		reservationBean.editReservation(reservation);
		taskController.setTache(new Tache());
		return "listReservations.faces?faces-redirect=true";
	}

	public String createReservation() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		Float newAmount = oldAmount + reservation.getAmount();
		medicalJourney.setAmount(newAmount);
		// reservation.setMedicalJourney(medicalJourney);
		// reservation.setLodgingHost(lodgingHost);
		reservation.setLocation(location);
		reservation = reservationBean.addReservation(reservation, lodgingHost,
				location, medicalJourney);
		return "listReservations.faces?faces-redirect=true";
	}

	public String showEditReservation(int id) {
		Reservation reservation = reservationBean.getReservationById(id);
		oldReservationAmount = reservation.getAmount();
		setSelectedReservation(reservation);
		setLodgingHost(reservation.getLodgingHost());
		setLocation(reservation.getLocation());
		return "showEditReservation.faces";
	}

	public String deleteReservation(int id) {
		Reservation d = reservationBean.getReservationById(id);
		reservationBean.deleteReservation(d);
		return "listReservations.faces?faces-redirect=true";
	}

	public String editReservation() {
		Float oldAmount = medicalJourney.getAmount();
		Float newAmount = oldAmount - oldReservationAmount
				+ selectedReservation.getAmount();
		selectedReservation.setAmount(newAmount);
		selectedReservation.setMedicalJourney(medicalJourney);
		selectedReservation.setLodgingHost(lodgingHost);
		selectedReservation.setLocation(location);
		reservationBean.editReservation(selectedReservation);
		return "listReservations.faces?faces-redirect=true";
	}

	public String listReservations() {
		return "listReservations.faces?faces-redirect=true";
	}

	public List<Reservation> getList() {
		return list;
	}

	public void setList(List<Reservation> list) {
		this.list = list;
	}

	public ReservationBean getReservationBean() {
		return reservationBean;
	}

	public void setReservationBean(ReservationBean reservationBean) {
		this.reservationBean = reservationBean;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Reservation getSelectedReservation() {
		return selectedReservation;
	}

	public void setSelectedReservation(Reservation selectedReservation) {
		this.selectedReservation = selectedReservation;
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

	public Float getOldReservationAmount() {
		return oldReservationAmount;
	}

	public void setOldReservationAmount(Float oldReservationAmount) {
		this.oldReservationAmount = oldReservationAmount;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

}
