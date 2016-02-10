package tn.welldone.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MedicalJourneyEmployeeId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7611622619646669996L;
	private MedicalJourney medicalJourney;
    private Employee employee;

	@ManyToOne
	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	@ManyToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalJourneyEmployeeId that = (MedicalJourneyEmployeeId) o;

        if (medicalJourney != null ? !medicalJourney.equals(that.medicalJourney) : that.medicalJourney != null) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (medicalJourney != null ? medicalJourney.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }
}
