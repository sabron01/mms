package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import tn.welldone.interceptors.TaskInterceptor;
import tn.welldone.model.City;
import tn.welldone.model.Consultation;
import tn.welldone.model.Country;
import tn.welldone.model.Doctor;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Region;
import tn.welldone.model.Tache;
import tn.welldone.model.Treatment;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.service.ConsultationBean;
import tn.welldone.service.DataService;
import tn.welldone.service.MedicalJourneyBean;

@Named("ConsultationController")
@ViewScoped
@RequestScoped
public class ConsultationController implements Serializable {

	private static final long serialVersionUID = 4158328378543554775L;

	@EJB
	private ConsultationBean consultationBean;

	@EJB
	private DataService service;

	@Inject
	TaskController taskController;

	@EJB
	private MedicalJourneyBean medicalJourneyBean;

	private Consultation consultation = new Consultation();

	private Consultation selectedConsultation = new Consultation();

	private MedicalJourney medicalJourney = new MedicalJourney();

	private Doctor doctor = new Doctor();

	private Location location = new Location();

	private List<Country> countries;

	private List<Region> regions;

	private List<City> cities;

	private List<Consultation> list;

	private Float oldConsultationAmount = 0f;

	@javax.annotation.PostConstruct
	public void init() {
		setList(consultationBean.getAllConsultations());
		this.setRegions(service.getRegions());
		this.setCities(service.getCities());
		this.setCountries(service.getCountries());
	}

	public String createNewConsultation() {
		return "addConsultation.faces";
	}

	public String createNewTaskConsultation(Tache tache) {
		taskController.setTache(tache);
		Consultation c = consultationBean.getConsultationById(tache.getId());
		setConsultation(c);
		return "/consultation/addTaskConsultation.faces";
	}
	
	public String createTaskConsultation() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		oldConsultationAmount = consultation.getAmount();
		if (oldConsultationAmount == null)
			oldConsultationAmount = 0f;
		Float newAmount = oldAmount - oldConsultationAmount
				+ consultation.getAmount();
		System.out.println("MedicalJourney ID "+medicalJourney.getId());
		medicalJourney.setAmount(newAmount);
		consultation.setAction(taskController.getTache().getAction());
		consultation.setMedicalJourney(medicalJourney);
		consultation.setDoctor(doctor);
		consultation.setCodeTache(taskController.getTache().getCodeTache());
		consultation.setOwnerAgent(taskController.getTache().getOwnerAgent());
		consultation.setAddress(location);
		consultation.setTacheState(TacheState.PLANNED);
		consultationBean.editConsultation(consultation);
		taskController.setTache(new Tache());
		return "listConsultations.faces?faces-redirect=true";
	}

	public String createConsultation() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		if (consultation.getAmount() == null)
			consultation.setAmount(0f);
		Float newAmount = oldAmount + consultation.getAmount();
		medicalJourney.setAmount(newAmount);
		consultation.setAddress(location);
		consultation = consultationBean.addConsultation(consultation,
				medicalJourney, doctor, location);
		return "listConsultations.faces?faces-redirect=true";
	}

	public String showEditConsultation(int id) {
		Consultation consultation = consultationBean.getConsultationById(id);
		if (consultation.getAmount() == null)
			consultation.setAmount(0f);
		oldConsultationAmount = consultation.getAmount();
		setSelectedConsultation(consultation);
		setDoctor(consultation.getDoctor());
		setLocation(consultation.getAddress());
		setMedicalJourney(consultation.getMedicalJourney());
		return "showEditConsultation.faces";
	}

	public String deleteConsultation(int id) {
		Consultation d = consultationBean.getConsultationById(id);
		consultationBean.deleteConsultation(d);
		return "listConsultations.faces?faces-redirect=true";
	}

	public String editConsultation() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		if (oldConsultationAmount == null)
			oldConsultationAmount = 0f;
		if (selectedConsultation.getAmount() == null)
			selectedConsultation.setAmount(0f);
		Float newAmount = oldAmount - oldConsultationAmount
				+ selectedConsultation.getAmount();
		selectedConsultation.setAmount(newAmount);
		selectedConsultation.setMedicalJourney(medicalJourney);
		selectedConsultation.setDoctor(doctor);
		selectedConsultation.setAddress(location);
		consultationBean.editConsultation(selectedConsultation);
		return "listConsultations.faces?faces-redirect=true";
	}

	public String listConsultations() {
		return "listConsultations.faces?faces-redirect=true";
	}

	public List<Consultation> getList() {
		return list;
	}

	public void setList(List<Consultation> list) {
		this.list = list;
	}

	public ConsultationBean getConsultationBean() {
		return consultationBean;
	}

	public void setConsultationBean(ConsultationBean consultationBean) {
		this.consultationBean = consultationBean;
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public Consultation getSelectedConsultation() {
		return selectedConsultation;
	}

	public void setSelectedConsultation(Consultation selectedConsultation) {
		this.selectedConsultation = selectedConsultation;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Float getOldConsultationAmount() {
		return oldConsultationAmount;
	}

	public void setOldConsultationAmount(Float oldConsultationAmount) {
		this.oldConsultationAmount = oldConsultationAmount;
	}

}
