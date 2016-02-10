package tn.welldone.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tn.welldone.converter.MedicalJourneyStateConverter;

@Entity
public class MedicalJourney implements Serializable {

	public enum MedicalJourneyState {
		PLANNED, ACTIVE, SUSPENDED, ACHIEVED, CANCELED, REJECTED, DELETED
	}

	private static final long serialVersionUID = -5286672829995674678L;
	/**
	 * 
	 */

	private int id;
	private String identifier;
	private Patient patient;
	private Contract contract;
	private Calendar startDate;
	private Calendar closeDate;
	private MedicalJourneyState medicalJourneyState;
	private Float amount;
	private String isDeleted;
	private User addedBy;
	private User editedBy;
	private User deletedBy;
	private Date createdAt;
	private Date updateAt;
	private Date deletedAt;
	private Collection<Tache> taches;
	private Collection<Employee> affectedEmployees;
	private Set<MedicalJourneyEmployee> medicalJourneyEmployees = new HashSet<MedicalJourneyEmployee>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne
	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}

	@ManyToOne
	public User getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(User editedBy) {
		this.editedBy = editedBy;
	}

	@ManyToOne
	public User getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(User deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@ManyToOne
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Boolean getIsDeleted() {
		if (isDeleted == null)
			return null;
		return isDeleted == "Y" ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsDeleted(Boolean active) {
		if (active == null) {
			this.isDeleted = null;
		} else {
			this.isDeleted = active == true ? "Y" : "N";
		}
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Calendar closeDate) {
		this.closeDate = closeDate;
	}

	@Override
	public String toString() {
		return "" + this.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		MedicalJourney other = (MedicalJourney) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	// Need to check Fetch Type
	// @OneToMany(fetch=FetchType.LAZY,mappedBy="medicalJourney")
	@OneToMany(mappedBy="medicalJourney",cascade = {CascadeType.ALL})
	public Collection<Tache> getTaches() {
		return taches;
	}

	public void setTaches(Collection<Tache> taches) {
		this.taches = taches;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	@JoinTable(name = "MedicalJourney_AffectedEmployee", joinColumns = { @JoinColumn(name = "medicalJourney_id") }, inverseJoinColumns = { @JoinColumn(name = "employee_id") })
	@ManyToMany
	public Collection<Employee> getAffectedEmployees() {
		return affectedEmployees;
	}

	public void setAffectedEmployees(Collection<Employee> affectedEmployees) {
		this.affectedEmployees = affectedEmployees;
	}

	@Convert(converter = MedicalJourneyStateConverter.class)
	@Enumerated(EnumType.STRING)
	public MedicalJourneyState getMedicalJourneyState() {
		return medicalJourneyState;
	}

	public void setMedicalJourneyState(MedicalJourneyState medicalJourneyState) {
		this.medicalJourneyState = medicalJourneyState;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicalJourneyEmployeeId.medicalJourney", cascade=CascadeType.ALL)
	public Set<MedicalJourneyEmployee> getMedicalJourneyEmployees() {
		return medicalJourneyEmployees;
	}

	public void setMedicalJourneyEmployees(Set<MedicalJourneyEmployee> medicalJourneyEmployees) {
		this.medicalJourneyEmployees = medicalJourneyEmployees;
	}

}