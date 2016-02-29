package tn.welldone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.model.Consultation;
import tn.welldone.model.Contract;
import tn.welldone.model.Displacement;
import tn.welldone.model.Employee;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.MedicalJourneyEmployeeService;
import tn.welldone.model.Patient;
import tn.welldone.model.Prescription;
import tn.welldone.model.Booking;
import tn.welldone.model.Reservation;
import tn.welldone.model.Service;
import tn.welldone.model.Tache;
import tn.welldone.model.Treatment;
import tn.welldone.service.ConsultationBean;
import tn.welldone.service.DisplacementBean;
import tn.welldone.service.EmployeeService;
import tn.welldone.service.FirebaseService;
import tn.welldone.service.MedicalJourneyBean;
import tn.welldone.service.NotificationBean;
import tn.welldone.service.PrescriptionBean;
import tn.welldone.service.BookingBean;
import tn.welldone.service.ReservationBean;
import tn.welldone.service.ServiceBean;
import tn.welldone.service.TreatmentBean;

@Named("MedicalJourneyController")
@RequestScoped
public class MedicalJourneyController implements Serializable {

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
	private ReservationBean reservationBean;

	@EJB
	private PrescriptionBean prescriptionBean;

	@EJB
	private TreatmentBean treatmentBean;

	@EJB
	private EmployeeService employeeService;
	
	@EJB
	FirebaseService fireBase;

	@EJB
	private ServiceBean serviceBean;
	
	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private transient Logger logger;

	private MedicalJourney medicalJourney = new MedicalJourney();

	private MedicalJourney selectedMedicalJourney = new MedicalJourney();

	private Contract contract = new Contract();

	private Service service = new Service();

	private Patient patient = new Patient();

	private Collection<MedicalJourney> list;

	private Collection<MedicalJourney> listAffectedMedicalJourneys;

	private Collection<MedicalJourney> listCurrentMedicalJourneys;

	private List<Treatment> listTreatments;

	private List<Displacement> listDisplacements;
	
	private List<Reservation> listReservations;

	private List<Booking> listBookings;

	private List<Consultation> listConsultations;

	private List<Prescription> listPrescriptions;

	private List<Service> services = new ArrayList<Service>();

	private List<Service> checkedServices = new ArrayList<Service>();

	private List<Employee> employees;
	
	private MedicalJourneyEmployeeService medicalJourneyEmployeeService = new MedicalJourneyEmployeeService();
	 
	private  List<MedicalJourneyEmployeeService> mesList = new ArrayList<MedicalJourneyEmployeeService>();

	private Employee employee = new Employee();
	
	private Employee selectedEmployee = new Employee();

	private HtmlSelectOneMenu htmlServiceSelect;

	private HtmlSelectManyCheckbox htmlSelectManyCheckboxService;

	private Map<String, Service> availableItems;

	private List<Service> selectedItems;

	private Service selectedItem;

	private boolean selectedItemRemoved;
	
	@ManagedProperty("#{param.medicalJourneyId}")
	private int medicalJourneyId;

	@javax.annotation.PostConstruct
	public void init() {
		
		System.out.println("AM IN INIT");

		setList(medicalJourneyBean.getAllMedicalJourneys());
		setListAffectedMedicalJourneys(medicalJourneyBean
				.getAffectedMedicalJourneys());
		setListCurrentMedicalJourneys(medicalJourneyBean
				.getCurrentMedicalJourneys());
		this.setEmployees(employeeService.getEmployees());
		
		
		String s_medicalJourneyId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("medicalJourneyId");
		if(s_medicalJourneyId != null){
			System.out.println("F:param ID : "+s_medicalJourneyId);
			System.out.println("ActionListener ID : "+medicalJourneyId);			
			int mId= Integer.parseInt(s_medicalJourneyId);
			setMedicalJourneyId(mId);
			MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(mId);
			setMesList(new ArrayList<MedicalJourneyEmployeeService>(m.getMedicalJourneyEmployeeServices()));
			setSelectedMedicalJourney(m);
		}


	}

	public void updateCheckedService(ValueChangeEvent event) {
		setCheckedServices((List<Service>) event.getNewValue());
		List<Service> oldValue = (List<Service>) event.getOldValue();
		List<Service> newValue = (List<Service>) event.getNewValue();

		if (oldValue == null) {
			oldValue = Collections.emptyList();
		}
		if (oldValue.size() > newValue.size()) {
			oldValue = new ArrayList<Service>(oldValue);
			oldValue.removeAll(newValue);
			selectedItem = oldValue.iterator().next();
			selectedItemRemoved = true;
		} else {
			newValue = new ArrayList<Service>(newValue);
			newValue.removeAll(oldValue);
			selectedItem = newValue.iterator().next();
			selectedItemRemoved = false;
		}
		for (Service s : checkedServices)
			System.out.println("Le service est : " + s.getLabel());

		setEmployees(employeeService.getEmployeesByService(checkedServices));

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

	public void populateListEmployee(AjaxBehaviorEvent event) {
	}

	public String createNewMedicalJourney() {
		return "addMedicalJourney.faces";
	}

	public String createMedicalJourney() {
		medicalJourney.setContract(contract);
		medicalJourney.setPatient(patient);
		String identifier = "M-" + contract.getCodeContract() + "-"
				+ patient.getCodePatient();
		medicalJourney.setIdentifier(identifier);
		medicalJourneyBean.addMedicalJourney(medicalJourney);
		return "listMedicalJourneys.faces?faces-redirect=true";
	}

	public String affectEmployeesMedicalJourney() {
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(selectedMedicalJourney.getId());
		System.out.println("M->"+m.getId());
		System.out.println("S->"+service.getId());
		System.out.println("E->"+employee.getId());
		// Check if an employee existed with selected service.
		if(!employeeService.checkExistedEmployeeByServiceForMedicalJourney(service,employee,m)){
			Tache tache = medicalJourneyBean.generateTacheForEmployee(m,employee,service);
			MedicalJourneyEmployeeService medicalJourneyEmployeeService = new MedicalJourneyEmployeeService();
			medicalJourneyEmployeeService.setMedicalJourney(m);
			medicalJourneyEmployeeService.setEmployee(employee);
			medicalJourneyEmployeeService.setService(service);
			medicalJourneyEmployeeService.setTache(tache);	
			//m.getMedicalJourneyEmployeeServices().add(medicalJourneyEmployeeService);
			//medicalJourneyBean.editMedicalJourney(m);
			medicalJourneyBean. affectEmployee(medicalJourneyEmployeeService);
			notificationBean.addTaskNotification(m,service);
			fireBase.pushNotification("test", "ounissi", "Notification", "Test from MMS");
			
			return "listMedicalJourneys.faces?faces-redirect=true";		
		}			
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A employee was saved with the samed selected service","Error Message"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return manageEmployees(selectedMedicalJourney.getId());
		}

	}
	
	public String removeAffectedEmployee(int employee_id,int service_id){
		
		System.out.println("am hear !!!");
		Employee e = employeeService.getEmployeeById(employee_id);	
		Service s = serviceBean.getServiceById(service_id);
		System.out.println("MED EMP SRV : "+selectedMedicalJourney.getId()+" "+employee_id+" "+service_id);
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(selectedMedicalJourney.getId());
		for (MedicalJourneyEmployeeService mse :m.getMedicalJourneyEmployeeServices()) {
			if(mse.getEmployee().equals(e) && mse.getService().equals(s)){
				System.out.println("Element Found");
				medicalJourneyBean.removeAffectedEmployee(mse);
				System.out.println("After remove");
			}
		}
		return manageEmployees(m.getId());
	}
	
	public String removeEmployee(int id){
		
		// Find employee with submitted ID
		Employee e = employeeService.getEmployeeById(id);	
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(selectedMedicalJourney.getId());
		logger.info("ID ********* is : "+m.getId());
		Collection<Employee> affectedEmployees;
		if(m.getAffectedEmployees().size() == 0){
			FacesContext.getCurrentInstance().addMessage("employees", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message","A employee was saved with the samed selected service"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return null;
			//return manageEmployees(selectedMedicalJourney.getId());
		}
		else{
			affectedEmployees = m.getAffectedEmployees();
			affectedEmployees.remove(e);
			m.setAffectedEmployees(affectedEmployees);
			medicalJourneyBean.editMedicalJourney(m);
			return "listMedicalJourneys.faces?faces-redirect=true";
		}		
	}

	public String showDetailsMedicalJourney(int id) {
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(id);
		setSelectedMedicalJourney(m);
		setListTreatments(treatmentBean.getListByMedicalJourney(m));
		setListConsultations(consultationBean.getListByMedicalJourney(m));
		setListReservations(reservationBean.getListByMedicalJourney(m));
		setListDisplacements(displacementBean.getListByMedicalJourney(m));
		setListBookings(bookingBean.getListByMedicalJourney(m));
		setListPrescriptions(prescriptionBean.getListByMedicalJourney(m));
		return "showDetailsMedicalJourney.faces";
	}

	public String manageEmployees(int id) {
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(id);
		setMesList(new ArrayList<MedicalJourneyEmployeeService>(m.getMedicalJourneyEmployeeServices()));
		setSelectedMedicalJourney(m);
		return "manageEmployees.faces";
	}
	
	public String listAffectedEmployees() {
		String s_medicalJourneyId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("medicalJourneyId");
		
		System.out.println("F:param ID : "+s_medicalJourneyId);
		System.out.println("ActionListener ID : "+medicalJourneyId);
		
		int mId= Integer.parseInt(s_medicalJourneyId);
		setMedicalJourneyId(mId);
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(mId);
		setMesList(new ArrayList<MedicalJourneyEmployeeService>(m.getMedicalJourneyEmployeeServices()));
		setSelectedMedicalJourney(m);
		return "listAffectedEmployees.faces";
		//return "listAffectedEmployees.faces?faces-redirect=true";
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
		setList(medicalJourneyBean.getAllMedicalJourneys());
		return "listMedicalJourneys.faces?faces-redirect=true";
	}

	public String listAffectedMedicalJourneys() {
		setList(medicalJourneyBean.getAffectedMedicalJourneys());
		return "listAffectedMedicalJourneys.faces?faces-redirect=true";
	}

	public String listCurrentMedicalJourneys() {
		setList(medicalJourneyBean.getCurrentMedicalJourneys());
		return "listCurrentMedicalJourneys.faces?faces-redirect=true";
	}

	public MedicalJourneyBean getMedicalJourneyBean() {
		return medicalJourneyBean;
	}

	public void setMedicalJourneyBean(MedicalJourneyBean medicalJourneyBean) {
		this.medicalJourneyBean = medicalJourneyBean;
	}

	public Collection<MedicalJourney> getList() {
		return list;
	}

	public void setList(Collection<MedicalJourney> list) {
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

	public Collection<MedicalJourney> getListAffectedMedicalJourneys() {
		return listAffectedMedicalJourneys;
	}

	public void setListAffectedMedicalJourneys(
			Collection<MedicalJourney> listAffectedMedicalJourneys) {
		this.listAffectedMedicalJourneys = listAffectedMedicalJourneys;
	}

	public Collection<MedicalJourney> getListCurrentMedicalJourneys() {
		return listCurrentMedicalJourneys;
	}

	public void setListCurrentMedicalJourneys(
			Collection<MedicalJourney> listCurrentMedicalJourneys) {
		this.listCurrentMedicalJourneys = listCurrentMedicalJourneys;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public HtmlSelectManyCheckbox getHtmlSelectManyCheckboxService() {
		return htmlSelectManyCheckboxService;
	}

	public void setHtmlSelectManyCheckboxService(
			HtmlSelectManyCheckbox htmlSelectManyCheckboxService) {
		this.htmlSelectManyCheckboxService = htmlSelectManyCheckboxService;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Service> getCheckedServices() {
		return checkedServices;
	}

	public void setCheckedServices(List<Service> checkedServices) {
		this.checkedServices = checkedServices;
	}

	public Map<String, Service> getAvailableItems() {
		return availableItems;
	}

	public void setAvailableItems(Map<String, Service> availableItems) {
		this.availableItems = availableItems;
	}

	public List<Service> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<Service> selectedItems) {
		this.selectedItems = selectedItems;
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

	public HtmlSelectOneMenu getHtmlServiceSelect() {
		return htmlServiceSelect;
	}

	public void setHtmlServiceSelect(HtmlSelectOneMenu htmlServiceSelect) {
		this.htmlServiceSelect = htmlServiceSelect;
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public List<MedicalJourneyEmployeeService> getMesList() {
		return mesList;
	}

	public void setMesList(List<MedicalJourneyEmployeeService> mesList) {
		this.mesList = mesList;
	}

	public MedicalJourneyEmployeeService getMedicalJourneyEmployeeService() {
		return medicalJourneyEmployeeService;
	}

	public void setMedicalJourneyEmployeeService(
			MedicalJourneyEmployeeService medicalJourneyEmployeeService) {
		this.medicalJourneyEmployeeService = medicalJourneyEmployeeService;
	}

	public int getMedicalJourneyId() {
		return medicalJourneyId;
	}

	public void setMedicalJourneyId(int medicalJourneyId) {
		this.medicalJourneyId = medicalJourneyId;
	}

	public List<Reservation> getListReservations() {
		return listReservations;
	}

	public void setListReservations(List<Reservation> listReservations) {
		this.listReservations = listReservations;
	}

}
