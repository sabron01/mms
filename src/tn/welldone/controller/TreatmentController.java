package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import tn.welldone.helpers.Operation;
import tn.welldone.interceptors.AuthorizationSecurityInterceptor;
import tn.welldone.model.Displacement;
import tn.welldone.model.Doctor;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.Tache;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.model.Treatment;
import tn.welldone.security.Permission;
import tn.welldone.service.TacheBean;
import tn.welldone.service.TreatmentBean;

@Named("TreatmentController")
@ViewScoped
@RequestScoped
public class TreatmentController implements Serializable {

	private static final long serialVersionUID = -7315996974779476699L;

	@EJB
	private TreatmentBean treatmentBean;

	@EJB
	private TacheBean tacheBean;

	@Inject
	TaskController taskController;

	private List<Treatment> list;

	private Treatment treatment = new Treatment();

	private Treatment selectedTreatment = new Treatment();

	private MedicalJourney medicalJourney = new MedicalJourney();

	private ServiceProvider clinic = new ServiceProvider();

	private Doctor doctor = new Doctor();

	private Float oldTreatmentAmount = 0f;

	@javax.annotation.PostConstruct
	public void init() {
		setList(treatmentBean.getAllTreatments());
	}

	@Permission(resource=Treatment.class,value=Operation.ADD)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String createNewTreatment() {
		return "addTreatment.faces";
	}

	@Permission(resource=Treatment.class,value=Operation.ADD)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String createNewTaskTreatment(Tache tache) {
		taskController.setTache(tache);
		Treatment t = treatmentBean.getTreatmentById(tache.getId());
		setTreatment(t);
		return "/treatment/addTaskTreatment.faces";
	}

	@Permission(resource=Treatment.class,value=Operation.ADD)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String createTaskTreatment() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		oldTreatmentAmount = treatment.getAmount();
		if (oldTreatmentAmount == null)
			oldTreatmentAmount = 0f;
		Float newAmount = oldAmount + oldTreatmentAmount;
		medicalJourney.setAmount(newAmount);
		treatment.setAction(taskController.getTache().getAction());
		treatment.setMedicalJourney(medicalJourney);
		treatment.setCodeTache(taskController.getTache().getCodeTache());
		treatment.setOwnerAgent(taskController.getTache().getOwnerAgent());
		treatment.setClinic(clinic);
		treatment.setResponsableDoctor(doctor);
		treatment.setTacheState(TacheState.PLANNED);
		treatmentBean.editTreatment(treatment);
		taskController.setTache(new Tache());
		return "listTreatments.faces?faces-redirect=true";
	}

	@Permission(resource=Treatment.class,value=Operation.ADD)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String createTreatment() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		Float newAmount = oldAmount + treatment.getAmount();
		medicalJourney.setAmount(newAmount);
		treatment.setMedicalJourney(medicalJourney);
		treatment.setClinic(clinic);
		treatment.setResponsableDoctor(doctor);
		treatment = treatmentBean.addTreatment(treatment, medicalJourney,
				clinic, doctor);
		return "listTreatments.faces?faces-redirect=true";
	}

	@Permission(resource=Treatment.class,value=Operation.EDIT)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String showEditTreatment(int id) {
		Treatment treatment = treatmentBean.getTreatmentById(id);
		System.out.println("showEditTreatment Treatment :" + treatment.getId());
		oldTreatmentAmount = treatment.getAmount();
		if (oldTreatmentAmount == null)
			oldTreatmentAmount = 0f;
		setSelectedTreatment(treatment);
		setMedicalJourney(treatment.getMedicalJourney());
		setClinic(treatment.getClinic());
		setDoctor(treatment.getResponsableDoctor());
		return "/treatment/showEditTreatment.faces";
	}

	@Permission(resource=Treatment.class,value=Operation.DELETE)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String deleteTreatment(int id) {
		Treatment d = treatmentBean.getTreatmentById(id);
		treatmentBean.deleteTreatment(d);
		return "listTreatments.faces?faces-redirect=true";
	}

	@Permission(resource=Treatment.class,value=Operation.EDIT)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String editTreatment() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		Float newAmount = oldAmount - oldTreatmentAmount
				+ selectedTreatment.getAmount();
		selectedTreatment.setAmount(newAmount);
		selectedTreatment.setMedicalJourney(medicalJourney);
		selectedTreatment.setClinic(clinic);
		selectedTreatment.setResponsableDoctor(doctor);
		treatmentBean.editTreatment(selectedTreatment);
		return "listTreatments.faces?faces-redirect=true";
	}

	@Permission(resource=Treatment.class,value=Operation.VIEW)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String listTreatments() {
		return "listTreatments.faces?faces-redirect=true";
	}

	public TreatmentBean getTreatmentBean() {
		return treatmentBean;
	}

	public void setTreatmentBean(TreatmentBean treatmentBean) {
		this.treatmentBean = treatmentBean;
	}

	public List<Treatment> getList() {
		return list;
	}

	public void setList(List<Treatment> list) {
		this.list = list;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public Treatment getSelectedTreatment() {
		return selectedTreatment;
	}

	public void setSelectedTreatment(Treatment selectedTreatment) {
		this.selectedTreatment = selectedTreatment;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	public ServiceProvider getClinic() {
		return clinic;
	}

	public void setClinic(ServiceProvider clinic) {
		this.clinic = clinic;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Float getOldTreatmentAmount() {
		return oldTreatmentAmount;
	}

	public void setOldTreatmentAmount(Float oldTreatmentAmount) {
		this.oldTreatmentAmount = oldTreatmentAmount;
	}

}
