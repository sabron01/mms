package tn.welldone.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity  
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698873311391347084L;
	private int id;
	private String reference;
	private MedicalJourney medicalJourney;
	private ServiceProvider beneficiary;
	private ServiceProvider owner;
	private String headerMessage;
	private String footerMessage;
	private Date issueDate;
	private Date paymentDate;
	private float tvaValue;
	private float amount;
	private String isDeleted;
	private User addedBy;
	private User editedBy;
	private User deletedBy;
	private Date createdAt;
	private Date updateAt;
	private Date deletedAt;
	private Collection<Item> items;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@OneToOne
	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	@ManyToOne
	public ServiceProvider getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(ServiceProvider beneficiary) {
		this.beneficiary = beneficiary;
	}

	@ManyToOne
	public ServiceProvider getOwner() {
		return owner;
	}

	public void setOwner(ServiceProvider owner) {
		this.owner = owner;
	}

	public String getHeaderMessage() {
		return headerMessage;
	}

	public void setHeaderMessage(String headerMessage) {
		this.headerMessage = headerMessage;
	}

	public String getFooterMessage() {
		return footerMessage;
	}

	public void setFooterMessage(String footerMessage) {
		this.footerMessage = footerMessage;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public float getTvaValue() {
		return tvaValue;
	}

	public void setTvaValue(float tvaValue) {
		this.tvaValue = tvaValue;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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

	@OneToMany(mappedBy="invoice",cascade = {CascadeType.ALL})
	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
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
		Invoice other = (Invoice) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
