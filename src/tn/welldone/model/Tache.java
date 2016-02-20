package tn.welldone.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import tn.welldone.converter.ActionConverter;
import tn.welldone.converter.MedicalJourneyStateConverter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Tache implements Serializable {

	public enum TacheState {
		CREATED, PLANNED, ACTIVE, SUSPENDED, ACHIEVED, CANCELED, REJECTED, DELETED
	}

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	private static final long serialVersionUID = 8846925337336266155L;
	private int id;
	private String codeTache;
	private MedicalJourney medicalJourney;
	private User ownerAgent;
	private Action action;
	private Float amount;
	private TacheState tacheState;
	private String isDeleted;
	private User addedBy;
	private User editedBy;
	private User deletedBy;
	private Date createdAt;
	private Date updateAt;
	private Date deletedAt;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeTache() {
		return codeTache;
	}

	public void setCodeTache(String codeTache) {
		this.codeTache = codeTache;
	}

	@ManyToOne
	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	@ManyToOne
	public User getOwnerAgent() {
		return ownerAgent;
	}

	public void setOwnerAgent(User ownerAgent) {
		this.ownerAgent = ownerAgent;
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

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Convert(converter = MedicalJourneyStateConverter.class)
	@Enumerated(EnumType.STRING)
	public TacheState getTacheState() {
		return tacheState;
	}

	public void setTacheState(TacheState tacheState) {
		this.tacheState = tacheState;
	}

	@Convert(converter = ActionConverter.class)
	@Enumerated(EnumType.STRING)
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
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
		Tache other = (Tache) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
