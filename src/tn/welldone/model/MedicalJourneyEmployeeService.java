package tn.welldone.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@AssociationOverrides({
		@AssociationOverride(name = "medicalJourney", joinColumns = @JoinColumn(name = "medicalJourney.id")),
		@AssociationOverride(name = "service", joinColumns = @JoinColumn(name = "service.id")),
		@AssociationOverride(name = "employee", joinColumns = @JoinColumn(name = "employee.id")) })
public class MedicalJourneyEmployeeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4035384613525715931L;
	private int Id;
	private MedicalJourneyEmployeeServiceId medicalJourneyEmployeeServiceId = new MedicalJourneyEmployeeServiceId();
	private Tache tache;

	public MedicalJourneyEmployeeService() {
		
	}

	
	@Transient
	public MedicalJourney getMedicalJourney() {
		return getMedicalJourneyEmployeeServiceId().getMedicalJourney();
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		getMedicalJourneyEmployeeServiceId().setMedicalJourney(medicalJourney);
	}

	@Transient
	public Employee getEmployee() {
		return getMedicalJourneyEmployeeServiceId().getEmployee();
	}

	public void setEmployee(Employee employee) {
		getMedicalJourneyEmployeeServiceId().setEmployee(employee);
	}

	@Transient
	public Service getService() {
		return getMedicalJourneyEmployeeServiceId().getService();
	}

	public void setService(Service service) {
		getMedicalJourneyEmployeeServiceId().setService(service);
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((medicalJourneyEmployeeServiceId == null) ? 0
						: medicalJourneyEmployeeServiceId.hashCode());
		result = prime * result + ((tache == null) ? 0 : tache.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalJourneyEmployeeService other = (MedicalJourneyEmployeeService) obj;
		if (medicalJourneyEmployeeServiceId == null) {
			if (other.medicalJourneyEmployeeServiceId != null)
				return false;
		} else if (!medicalJourneyEmployeeServiceId
				.equals(other.medicalJourneyEmployeeServiceId))
			return false;
		if (tache == null) {
			if (other.tache != null)
				return false;
		} else if (!tache.equals(other.tache))
			return false;
		return true;
	}

	@ManyToOne
	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}


	public MedicalJourneyEmployeeServiceId getMedicalJourneyEmployeeServiceId() {
		return medicalJourneyEmployeeServiceId;
	}


	public void setMedicalJourneyEmployeeServiceId(
			MedicalJourneyEmployeeServiceId medicalJourneyEmployeeServiceId) {
		this.medicalJourneyEmployeeServiceId = medicalJourneyEmployeeServiceId;
	}

}
