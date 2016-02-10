package tn.welldone.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tn.welldone.converter.CivilStateConverter;
import tn.welldone.converter.GenderConverter;
import tn.welldone.converter.HonorificTitleConverter;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable {
	
	public enum Gender {
		MALE,FEMALE
	}
	
	public enum HonorificTitle {
		Mr, Mrs, Miss, Ms 
		}
	
	public enum CivilState {
		Married,Celibataire
		}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8749012327141254370L;
	private int id;
	private Gender gender;
	private HonorificTitle honorificTitle;
	private String matricule;
	private String passeport;
	private String firstName;
	private String lastName;
	private Date birthday;
	private CivilState civilState;
	private Collection<Location> addresses;
	private String mail;
	private Collection<PhoneNumber> phoneNumbers;
	private String isDeleted;
	private User addedBy;
	private User editedBy;
	private User deletedBy;
	private Date createdAt;
	private Date updateAt;
	private Date deletedAt;

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Convert(converter=GenderConverter.class)
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Convert(converter=HonorificTitleConverter.class)
	@Enumerated(EnumType.STRING)
	public HonorificTitle getHonorificTitle() {
		return honorificTitle;
	}

	public void setHonorificTitle(HonorificTitle honorificTitle) {
		this.honorificTitle = honorificTitle;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getPasseport() {
		return passeport;
	}

	public void setPasseport(String passeport) {
		this.passeport = passeport;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Convert(converter=CivilStateConverter.class)
	@Enumerated(EnumType.STRING)
	public CivilState getCivilState() {
		return civilState;
	}

	public void setCivilState(CivilState civilState) {
		this.civilState = civilState;
	}

//	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@OneToMany
	public Collection<Location> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Location> addresses) {
		this.addresses = addresses;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

//	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@OneToMany
	public Collection<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Collection<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
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
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+this.getId();
	}
}
