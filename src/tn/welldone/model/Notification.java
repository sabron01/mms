package tn.welldone.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import tn.welldone.converter.TypeConverter;

@Entity
public class Notification implements Serializable {

	/**
	 * 
	 */
	public enum Type {
		Alert,Message,Notification,Task
	}
	
	private static final long serialVersionUID = 2449689096410825251L;
	private int id;
	private String subject;
	private String text;
	private Type type;
	private User notifiedBy;
	private Collection<User> targetUsers;
	private MedicalJourney medicalJourney;
	private String Status;
	private String isActive;
	private String isChecked;
	private String isDeleted;
	private Date createdAt;
	private User addedBy;
	private User editedBy;
	private User deletedBy;
	private Date updateAt;
	private Date deletedAt;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Convert(converter = TypeConverter.class)
	@Enumerated(EnumType.STRING)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Boolean getIsChecked() {
		if (isChecked == null)
			return null;
		return isChecked == "Y" ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsChecked(Boolean active) {
		if (active == null) {
			this.isChecked = null;
		} else {
			this.isChecked = active == true ? "Y" : "N";
		}
	}

	@ManyToOne
	public User getNotifiedBy() {
		return notifiedBy;
	}

	public void setNotifiedBy(User notifiedBy) {
		this.notifiedBy = notifiedBy;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Boolean getIsActive() {
		if (isActive == null)
			return null;
		return isActive == "Y" ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsActive(Boolean active) {
		if (active == null) {
			this.isActive = null;
		} else {
			this.isActive = active == true ? "Y" : "N";
		}
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	
	@ManyToMany(targetEntity = User.class)
	@JoinTable(name = "User_Notification", joinColumns = { @JoinColumn(name = "notification_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	public Collection<User> getTargetUsers() {
		return targetUsers;
	}

	public void setTargetUsers(Collection<User> targetUsers) {
		this.targetUsers = targetUsers;
	}

	@ManyToOne
	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

}
