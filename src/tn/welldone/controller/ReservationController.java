package tn.welldone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.model.Displacement;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Reservation;
import tn.welldone.model.Service;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.Tache;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.service.DataService;
import tn.welldone.service.DisplacementBean;
import tn.welldone.service.MedicalJourneyBean;
import tn.welldone.service.NotificationBean;
import tn.welldone.service.PartnerAgencyBean;
import tn.welldone.service.ReservationBean;

@Named("ReservationController")
@ViewScoped
@RequestScoped
public class ReservationController implements Serializable {

	private static final long serialVersionUID = 6579502554225500450L;

	@EJB
	ReservationBean reservationBean;
	
	@EJB
	PartnerAgencyBean partnerAgencyBean;
	
	@EJB
	DisplacementBean displacementBean;
	
	@EJB
	NotificationBean notificationBean;

	@EJB
	private DataService service;

	@Inject
	TaskController taskController;

	@EJB
	private MedicalJourneyBean medicalJourneyBean;

	private Reservation reservation = new Reservation();

	private Reservation selectedReservation = new Reservation();

	private MedicalJourney medicalJourney = new MedicalJourney();

	private ServiceProvider travelAgency = new ServiceProvider();

	private ServiceProvider startAirport = new ServiceProvider();

	private ServiceProvider destinationAirport = new ServiceProvider();
	
	private boolean isRoundTrip = false;

	private Float oldReservationAmount = 0f;
	
	private HtmlSelectBooleanCheckbox selectBooleanCheckboxRoundTrip;

	private List<Reservation> list;

	@javax.annotation.PostConstruct
	public void init() {
		System.out.println("Init Called");
		setList(reservationBean.getAllReservations());
		System.out.println("isRoundTrip Value : "+isRoundTrip);
	}

	public void checkRoundTrip(ValueChangeEvent event) {
	}
	
	public void activeHtmlReturDate(AjaxBehaviorEvent event) {
	}
	
	
	public String createNewReservation() {
		return "addReservation.faces";
	}

	public String createNewTaskReservation(Tache task) {
		taskController.setTache(task);
		Reservation d = reservationBean.getReservationById(task.getId());
		setReservation(d);
		return "/reservation/addTaskReservation.faces";
	}

	public String createTaskReservation() {
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
		reservation.setTravelAgency(travelAgency);
		reservation.setStartAirport(startAirport);
		reservation.setDestinationAirport(destinationAirport);
		reservation.setCodeTache(taskController.getTache().getCodeTache());
		reservation.setOwnerAgent(taskController.getTache().getOwnerAgent());
		reservation.setTacheState(TacheState.PLANNED);
		reservationBean.editReservation(reservation);
		Displacement d =displacementBean.activeMedicalJourneyTaskDisplacement(taskController.getTache().getMedicalJourney());
		if (d!=null)
		notificationBean.addCustomNotification(d.getOwnerAgent(), d.getMedicalJourney(),"Displacement Active","Your Displacement Task for "+d.getMedicalJourney().getIdentifier()+" is available");
		taskController.setTache(new Tache());
		return "listReservations.faces?faces-redirect=true";
	}

	public String createReservation() {
		System.out.println("createReservation called !!!");
		System.out.println("Return Date Value : "+reservation.getReturnDate());
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		if (reservation.getAmount() == null)
			reservation.setAmount(0f);
		Float newAmount = oldAmount + reservation.getAmount();
		medicalJourney.setAmount(newAmount);
		reservation.setTravelAgency(travelAgency);
		reservation.setStartAirport(startAirport);
		reservation.setDestinationAirport(destinationAirport);
		reservation.setMedicalJourney(medicalJourney);
		reservationBean.addReservation(reservation);
		return "listReservations.faces?faces-redirect=true";
	}

	public String showEditReservation(int id) {
		Reservation d = reservationBean.getReservationById(id);
		oldReservationAmount = d.getAmount();
		if(d.getReturnDate() != null)
			isRoundTrip = true;
		setSelectedReservation(d);
		setMedicalJourney(d.getMedicalJourney());
		setTravelAgency(d.getTravelAgency());
		setStartAirport(d.getStartAirport());
		setDestinationAirport(d.getDestinationAirport());
		return "showEditReservation.faces";
	}

	public String deleteReservation(int id) {
		Reservation d = reservationBean.getReservationById(id);
		reservationBean.deleteReservation(d);
		return "listReservations.faces?faces-redirect=true";
	}

	public String editReservation() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		if (oldReservationAmount == null)
			oldReservationAmount = 0f;
		if (selectedReservation.getAmount() == null)
			selectedReservation.setAmount(0f);
		Float newAmount = oldAmount - oldReservationAmount
				+ selectedReservation.getAmount();
		medicalJourney.setAmount(newAmount);
		selectedReservation.setMedicalJourney(medicalJourney);
		selectedReservation.setTravelAgency(travelAgency);
		selectedReservation.setStartAirport(startAirport);
		selectedReservation.setDestinationAirport(destinationAirport);
		reservationBean.editReservation(selectedReservation);
		return "listReservations.faces?faces-redirect=true";
	}

	public String listReservations() {
		return "listReservations.faces?faces-redirect=true";
	}

	public ReservationBean getReservationBean() {
		return reservationBean;
	}

	public void setReservationBean(ReservationBean reservationBean) {
		this.reservationBean = reservationBean;
	}

	public List<Reservation> getList() {
		return list;
	}

	public void setList(List<Reservation> list) {
		this.list = list;
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

	public MedicalJourneyBean getMedicalJourneyBean() {
		return medicalJourneyBean;
	}

	public void setMedicalJourneyBean(MedicalJourneyBean medicalJourneyBean) {
		this.medicalJourneyBean = medicalJourneyBean;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	public Float getOldReservationAmount() {
		return oldReservationAmount;
	}

	public void setOldReservationAmount(Float oldReservationAmount) {
		this.oldReservationAmount = oldReservationAmount;
	}

	public DataService getService() {
		return service;
	}

	public void setService(DataService service) {
		this.service = service;
	}

	public ServiceProvider getTravelAgency() {
		return travelAgency;
	}

	public void setTravelAgency(ServiceProvider travelAgency) {
		this.travelAgency = travelAgency;
	}

	public ServiceProvider getStartAirport() {
		return startAirport;
	}

	public void setStartAirport(ServiceProvider startAirport) {
		this.startAirport = startAirport;
	}

	public ServiceProvider getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(ServiceProvider destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public HtmlSelectBooleanCheckbox getSelectBooleanCheckboxRoundTrip() {
		return selectBooleanCheckboxRoundTrip;
	}

	public void setSelectBooleanCheckboxRoundTrip(
			HtmlSelectBooleanCheckbox selectBooleanCheckboxRoundTrip) {
		this.selectBooleanCheckboxRoundTrip = selectBooleanCheckboxRoundTrip;
	}

	public boolean getIsRoundTrip() {
		return isRoundTrip;
	}

	public void setIsRoundTrip(boolean isRoundTrip) {
		this.isRoundTrip = isRoundTrip;
	}

}
