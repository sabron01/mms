package tn.welldone.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProviderType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4025770943689586652L;
	private int id;
	private String typeCode;
	private String label;
	private String isDeleted;
	private User addedBy;
	private User editedBy;
	private User deletedBy;
	private Date createdAt;
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

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		ProviderType other = (ProviderType) obj;
		if (id != other.id)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	
	

}
