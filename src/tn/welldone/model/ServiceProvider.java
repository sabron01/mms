package tn.welldone.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ServiceProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String codeProvider;
	private String codeCommercialRegister;
	private String taxID;
	private String responsibleIdentity;
	private String companyName;
	private String tradingName;
	private String responsibleName;
	private String activity;
	private ProviderType typeProvider;
	private Collection<Service> services;
	private Date creationDate;
	private Location address;
	private String mail;
	private int fax;
	private int tel;
	private int mobile;
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

	public String getCodeProvider() {
		return codeProvider;
	}

	public void setCodeProvider(String codeProvider) {
		this.codeProvider = codeProvider;
	}

	public String getCodeCommercialRegister() {
		return codeCommercialRegister;
	}

	public void setCodeCommercialRegister(String codeCommercialRegister) {
		this.codeCommercialRegister = codeCommercialRegister;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public String getResponsibleIdentity() {
		return responsibleIdentity;
	}

	public void setResponsibleIdentity(String responsibleIdentity) {
		this.responsibleIdentity = responsibleIdentity;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

//	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@ManyToOne(fetch=FetchType.EAGER)
	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getFax() {
		return fax;
	}

	public void setFax(int fax) {
		this.fax = fax;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	@OneToMany(targetEntity=Service.class,cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<Service> getServices() {
		return services;
	}

	public void setServices(Collection<Service> services) {
		this.services = services;
	}

//	@ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@ManyToOne(fetch= FetchType.EAGER)
	public ProviderType getTypeProvider() {
		return typeProvider;
	}

	public void setTypeProvider(ProviderType typeProvider) {
		this.typeProvider = typeProvider;
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
		ServiceProvider other = (ServiceProvider) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+this.getId();
	}
	
	

}