package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.model.Doctor;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Prescription;
import tn.welldone.model.Reservation;
import tn.welldone.model.Tache;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.service.DataService;
import tn.welldone.service.MedicalJourneyBean;
import tn.welldone.service.PrescriptionBean;

@Named("PrescriptionController")
@ViewScoped
@RequestScoped
public class PrescriptionController implements Serializable {

	private static final long serialVersionUID = 4158328378543554775L;

	@EJB
	private PrescriptionBean prescriptionBean;

	@EJB
	private DataService service;

	@EJB
	private MedicalJourneyBean medicalJourneyBean;
	
	@Inject
	TaskController taskController;

	private Prescription prescription = new Prescription();

	private Prescription selectedPrescription = new Prescription();

	private MedicalJourney medicalJourney = new MedicalJourney();

	private Doctor doctor = new Doctor();
	
	private Float oldPrescriptionAmount = 0f ;

	private List<Prescription> list;

	@javax.annotation.PostConstruct
	public void init() {
		setList(prescriptionBean.getAllPrescriptions());
	}

	public String createNewPrescription() {
		return "addPrescription.faces";
	}
	
	public String createNewTaskPrescription(Tache task) {
		taskController.setTache(task);
		Prescription p = prescriptionBean.getPrescriptionById(task.getId());
		setPrescription(p);
		return "/prescription/addTaskPrescription.faces";
	}
	
	public String createTaskPrescription() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		oldPrescriptionAmount = prescription.getAmount();
		if (oldPrescriptionAmount == null)
			oldPrescriptionAmount = 0f;
		Float newAmount = oldAmount + oldPrescriptionAmount;
		medicalJourney.setAmount(newAmount);
		prescription.setAction(taskController.getTache().getAction());
		prescription.setMedicalJourney(medicalJourney);
		prescription.setCodeTache(taskController.getTache().getCodeTache());
		prescription.setOwnerAgent(taskController.getTache().getOwnerAgent());
		selectedPrescription.setResponsableDoctor(doctor);
		prescription.setTacheState(TacheState.PLANNED);
		prescriptionBean.editPrescription(prescription);
		taskController.setTache(new Tache());
		return "listPrescriptions.faces?faces-redirect=true";
	}


	public String createPrescription() {
		Float oldAmount = medicalJourney.getAmount();
		if(oldAmount == null)	
			oldAmount = 0f;
		if(prescription.getAmount() == null)
			prescription.setAmount(0f);
		Float newAmount = oldAmount+prescription.getAmount();
		medicalJourney.setAmount(newAmount);
		prescription = prescriptionBean.addPrescription(prescription,medicalJourney, doctor);
		return "listPrescriptions.faces?faces-redirect=true";
	}

	public String showEditPrescription(int id) {
		Prescription prescription = prescriptionBean.getPrescriptionById(id);
		if(prescription.getAmount() == null)
			prescription.setAmount(0f);
		oldPrescriptionAmount = prescription.getAmount();
		setDoctor(prescription.getResponsableDoctor());
		setMedicalJourney(prescription.getMedicalJourney());
		setSelectedPrescription(prescription);
		setDoctor(prescription.getResponsableDoctor());
		return "showEditPrescription.faces";
	}

	public String deletePrescription(int id) {
		Prescription d = prescriptionBean.getPrescriptionById(id);
		prescriptionBean.deletePrescription(d);
		return "listPrescriptions.faces?faces-redirect=true";
	}

	public String editPrescription() {
		Float oldAmount = medicalJourney.getAmount();
		if(oldAmount == null)
			oldAmount = 0f;
		if(oldPrescriptionAmount == null)
			oldPrescriptionAmount = 0f;
		if(selectedPrescription.getAmount() == null)
			selectedPrescription.setAmount(0f);
		Float newAmount = oldAmount-oldPrescriptionAmount+selectedPrescription.getAmount();
		selectedPrescription.setAmount(newAmount);	
		selectedPrescription.setMedicalJourney(medicalJourney);		
		selectedPrescription.setResponsableDoctor(doctor);
		prescriptionBean.editPrescription(selectedPrescription);
		return "listPrescriptions.faces?faces-redirect=true";
	}

	public String listPrescriptions() {
		return "listPrescriptions.faces?faces-redirect=true";
	}

	public List<Prescription> getList() {
		return list;
	}

	public void setList(List<Prescription> list) {
		this.list = list;
	}

	public PrescriptionBean getPrescriptionBean() {
		return prescriptionBean;
	}

	public void setPrescriptionBean(PrescriptionBean prescriptionBean) {
		this.prescriptionBean = prescriptionBean;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Prescription getSelectedPrescription() {
		return selectedPrescription;
	}

	public void setSelectedPrescription(Prescription selectedPrescription) {
		this.selectedPrescription = selectedPrescription;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	public Float getOldPrescriptionAmount() {
		return oldPrescriptionAmount;
	}

	public void setOldPrescriptionAmount(Float oldPrescriptionAmount) {
		this.oldPrescriptionAmount = oldPrescriptionAmount;
	}


}
