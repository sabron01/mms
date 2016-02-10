package tn.welldone.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@AssociationOverrides({
	@AssociationOverride(name="medicalJourney",joinColumns = @JoinColumn(name="medicalJourney.id")),
	@AssociationOverride(name = "employee", joinColumns = @JoinColumn(name = "employee.id")) })
public class MedicalJourneyEmployee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4035384613525715931L;
	private MedicalJourneyEmployeeId medicalJourneyEmployeeId = new MedicalJourneyEmployeeId();
	private Service service;
	private Tache tache;

	public MedicalJourneyEmployee() {
	}

	@EmbeddedId
	public MedicalJourneyEmployeeId getMedicalJourneyEmployeeId() {
		return medicalJourneyEmployeeId;
	}

	public void setMedicalJourneyEmployeeId(MedicalJourneyEmployeeId medicalJourneyEmployeeId) {
		this.medicalJourneyEmployeeId = medicalJourneyEmployeeId;
	}

	@Transient
	public MedicalJourney getMedicalJourney() {
		return getMedicalJourneyEmployeeId().getMedicalJourney();
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		getMedicalJourneyEmployeeId().setMedicalJourney(medicalJourney);
	}

	@Transient
	public Employee getEmployee() {
		return getMedicalJourneyEmployeeId().getEmployee();
	}

	public void setEmployee(Employee employee) {
		getMedicalJourneyEmployeeId().setEmployee(employee);
	}


	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MedicalJourneyEmployee that = (MedicalJourneyEmployee) o;

		if (getMedicalJourneyEmployeeId() != null ? !getMedicalJourneyEmployeeId().equals(that.getMedicalJourneyEmployeeId())
				: that.getMedicalJourneyEmployeeId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getMedicalJourneyEmployeeId() != null ? getMedicalJourneyEmployeeId().hashCode() : 0);
	}

	@ManyToOne
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@ManyToOne
	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

}
