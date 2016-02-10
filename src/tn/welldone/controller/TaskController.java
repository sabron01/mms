package tn.welldone.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Tache;

@Named("taskController")
@SessionScoped
public class TaskController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3215532032072027296L;
	protected Tache tache = new Tache();
	protected MedicalJourney medicalJourney = new MedicalJourney();

	public void intialiseTask(Tache tache){
		setTache(tache);		
		setMedicalJourney(tache.getMedicalJourney());
		System.out.println("Ini task : tache "+tache.getId());
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}
}
