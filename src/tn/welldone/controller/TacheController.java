package tn.welldone.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.model.Tache;
import tn.welldone.model.Tache.Action;
import tn.welldone.service.TacheBean;

@Named("tacheController")
@RequestScoped
public class TacheController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4017552385113292845L;

	@EJB
	private TacheBean tacheBean;
	
	@Inject
	TaskController taskController;
	
	@Inject
	TreatmentController treatmentController;
	
	@Inject
	ConsultationController consultationController;
	
	@Inject
	ReservationController reservationController;
	
	@Inject
	PrescriptionController prescriptionController;
	
	@Inject
	DisplacementController displacementController;

	@Inject
	private transient Logger logger;

	private Tache tache = new Tache();

	private Tache selectedTache = new Tache();
	
		private Collection<Tache> currentUserTaches;

	

	@javax.annotation.PostConstruct
	public void init() {
		setCurrentUserTaches(tacheBean.getCurrentUserTasks());
	}

	public String listCurrentUserTaches() {
		setCurrentUserTaches(tacheBean.getCurrentUserTasks());
		return "listCurrentUserTaches.faces";
	}

	public String performTache(int id) {
		
		System.out.println(" Submitted ID :" + id);
		System.out.println(" TACHE ID :" + selectedTache.getId());
		System.out.println(" TACHE action :" + selectedTache.getAction());

		/*Tache task = tacheBean.instanciateTask(selectedTache);
		task.setAction(selectedTache.getAction());
		task.setCodeTache(selectedTache.getCodeTache());
		task.setOwnerAgent(selectedTache.getOwnerAgent());
		task.setMedicalJourney(selectedTache.getMedicalJourney());
		task.setTacheState(selectedTache.getTacheState());*/
		
		Tache task = tacheBean.getTacheById(id);

		if (task != null) {
			System.out.println(" task not null :" + task.getTacheState());
			System.out.println(" Class Name :" + task.getClass().getName());

			switch (task.getClass().getName()) {
			
			case "tn.welldone.model.Treatment":
				System.out.println(" Am in switch ");
				if (selectedTache.getAction().equals(Action.CREATE)) {
					taskController.intialiseTask(task);
					System.out.println(" Create state :");
					return treatmentController.createNewTaskTreatment(task);
				} else
					System.out.println(" Not Create state :");
			
			
			case "tn.welldone.model.Consultation":
				System.out.println(" Am in switch ");
				if (selectedTache.getAction().equals(Action.CREATE)) {
					System.out.println(" Create state :");
					taskController.intialiseTask(task);
					return consultationController.createNewTaskConsultation(task);
				} else
					System.out.println(" Not Create state :");

			case "tn.welldone.model.Reservation":
				System.out.println(" Am in switch ");
				if (selectedTache.getAction().equals(Action.CREATE)) {
					System.out.println(" Create state :");
					taskController.intialiseTask(task);
					return reservationController.createNewTaskReservation(task);
				} else
					System.out.println(" Not Create state :");
				
				
			case "tn.welldone.model.Prescription":
				System.out.println(" Am in switch ");
				if (selectedTache.getAction().equals(Action.CREATE)) {
					System.out.println(" Create state :");
					taskController.intialiseTask(task);
					return prescriptionController.createNewTaskPrescription(task);
				} else
					System.out.println(" Not Create state :");
				
				
			case "tn.welldone.model.Displacement":
				System.out.println(" Am in switch ");
				if (selectedTache.getAction().equals(Action.CREATE)) {
					System.out.println(" Create state :");
					taskController.intialiseTask(task);
					return displacementController.createNewTaskDisplacement(task);
				} else
					System.out.println(" Not Create state :");
			}
		} else
			System.out.println(" task null :");

		return null;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public Tache getSelectedTache() {
		return selectedTache;
	}

	public void setSelectedTache(Tache selectedTache) {
		this.selectedTache = selectedTache;
	}

	public Collection<Tache> getCurrentUserTaches() {
		return currentUserTaches;
	}

	public void setCurrentUserTaches(Collection<Tache> currentUserTaches) {
		this.currentUserTaches = currentUserTaches;
	}

}
