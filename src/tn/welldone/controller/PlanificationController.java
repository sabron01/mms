package tn.welldone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import tn.welldone.model.Consultation;
import tn.welldone.model.Contract;
import tn.welldone.model.Displacement;
import tn.welldone.model.Employee;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Patient;
import tn.welldone.model.Prescription;
import tn.welldone.model.Booking;
import tn.welldone.model.Service;
import tn.welldone.model.Tache;
import tn.welldone.model.Treatment;
import tn.welldone.service.ConsultationBean;
import tn.welldone.service.DisplacementBean;
import tn.welldone.service.MedicalJourneyBean;
import tn.welldone.service.NotificationBean;
import tn.welldone.service.PrescriptionBean;
import tn.welldone.service.BookingBean;
import tn.welldone.service.ServiceBean;
import tn.welldone.service.TreatmentBean;


@Named("PlanificationController")
@RequestScoped
public class PlanificationController implements Serializable{

	
	private static final long serialVersionUID = 6934318577686945595L;
    
	@EJB
	private MedicalJourneyBean medicalJourneyBean;
	
	@EJB
	private NotificationBean notificationBean;
	
	@EJB
	private ConsultationBean consultationBean;
	
	@EJB
	private DisplacementBean displacementBean;
	
	@EJB
	private BookingBean bookingBean;
	
	@EJB
	private PrescriptionBean prescriptionBean;
	
	@EJB
	private TreatmentBean treatmentBean;
	
	@EJB
	private ServiceBean serviceBean;
	   
	private MedicalJourney medicalJourney = new MedicalJourney();
	
	private MedicalJourney selectedMedicalJourney = new MedicalJourney();
	
	private Service service = new Service();
	
	private Employee employee = new Employee();
	
	private Contract contract = new Contract();
	
	private Patient patient = new Patient();
	
	private Tache jsTache = new Tache();
	
	private List<MedicalJourney> list;
	
	private List<Treatment> listTreatments;
	
	private List<Displacement> listDisplacements;
	
	private List<Booking> listBookings;
	
	private List<Consultation> listConsultations;
	
	private List<Prescription> listPrescriptions;
	
	private List<Employee> employees;
	
	@javax.annotation.PostConstruct
	public void init() {
		setList(medicalJourneyBean.getAllMedicalJourneys());
	}	
	
	public void updateListEmployee(AjaxBehaviorEvent event) {
		HtmlSelectOneMenu htmlServiceSelect = ((HtmlSelectOneMenu) event
				.getSource());
		int service_id = Integer.parseInt((String) htmlServiceSelect
				.getSubmittedValue());
		Service serviceSelected = serviceBean.getServiceById(service_id);
		List<Employee> fetchedEmployees = new ArrayList<Employee>(serviceSelected.getEmployees());
		setEmployees(fetchedEmployees);
	}
	
	public String createNewMedicalJourney() {
		return "addMedicalJourney.faces";
	}
	
	public String createMedicalJourney() {
		medicalJourney.setContract(contract);
		medicalJourney.setPatient(patient);
		String identifier = "M-"+contract.getCodeContract()+"-"+patient.getCodePatient();
		medicalJourney.setIdentifier(identifier);
		medicalJourneyBean.addMedicalJourney(medicalJourney);
		return "listMedicalJourneys.faces?faces-redirect=true";
	}
	
	public String showDetailsMedicalJourney(int id) {
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(id);
		setSelectedMedicalJourney(m);
		setListTreatments(treatmentBean.getListByMedicalJourney(m));
		setListConsultations(consultationBean.getListByMedicalJourney(m));
		setListDisplacements(displacementBean.getListByMedicalJourney(m));
		setListBookings(bookingBean.getListByMedicalJourney(m));
		setListPrescriptions(prescriptionBean.getListByMedicalJourney(m));
		return "showDetailsMedicalJourney.faces";
	}

	public String showEditMedicalJourney(int id) {
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(id);
		setSelectedMedicalJourney(m);
		setPatient(m.getPatient());
		setContract(m.getContract());
		return "showEditMedicalJourney.faces";
	}
	
	public String deleteMedicalJourney(int id) {
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(id);
		medicalJourneyBean.deleteMedicalJourney(m);
		return "listMedicalJourneys.faces?faces-redirect=true";
	}

	public String editMedicalJourney() {
		selectedMedicalJourney.setContract(contract);
		selectedMedicalJourney.setPatient(patient);
		medicalJourneyBean.editMedicalJourney(selectedMedicalJourney);
		return "listMedicalJourneys.faces?faces-redirect=true";
	}

	public String listMedicalJourneys() {
		return "listMedicalJourneys.faces?faces-redirect=true";
	}

	public MedicalJourneyBean getMedicalJourneyBean() {
		return medicalJourneyBean;
	}

	public void setMedicalJourneyBean(MedicalJourneyBean medicalJourneyBean) {
		this.medicalJourneyBean = medicalJourneyBean;
	}

	public List<MedicalJourney> getList() {
		return list;
	}

	public void setList(List<MedicalJourney> list) {
		this.list = list;
	}


	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	public MedicalJourney getSelectedMedicalJourney() {
		return selectedMedicalJourney;
	}

	public void setSelectedMedicalJourney(MedicalJourney selectedMedicalJourney) {
		this.selectedMedicalJourney = selectedMedicalJourney;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Treatment> getListTreatments() {
		return listTreatments;
	}

	public void setListTreatments(List<Treatment> listTreatments) {
		this.listTreatments = listTreatments;
	}

	public List<Displacement> getListDisplacements() {
		return listDisplacements;
	}

	public void setListDisplacements(List<Displacement> listDisplacements) {
		this.listDisplacements = listDisplacements;
	}

	public List<Booking> getListBookings() {
		return listBookings;
	}

	public void setListBookings(List<Booking> listBookings) {
		this.listBookings = listBookings;
	}

	public List<Consultation> getListConsultations() {
		return listConsultations;
	}

	public void setListConsultations(List<Consultation> listConsultations) {
		this.listConsultations = listConsultations;
	}

	public List<Prescription> getListPrescriptions() {
		return listPrescriptions;
	}

	public void setListPrescriptions(List<Prescription> listPrescriptions) {
		this.listPrescriptions = listPrescriptions;
	}

	public Tache getJsTache() {
		return jsTache;
	}

	public void setJsTache(Tache jsTache) {
		this.jsTache = jsTache;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
