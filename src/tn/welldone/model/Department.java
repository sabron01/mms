package tn.welldone.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5767736877585544177L;
	private int id;
	private String codeDepartment;
	private String label;
	private Employee chief;
	private String isDeleted;
	private User addedBy;
	private User editedBy;
	private User deletedBy;
	private Date createdAt;
	private Date updateAt;
	private Date deletedAt;
	private Collection<Employee> employees;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	//Need To check the fetch type
//	@OneToOne(targetEntity=Employee.class,cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	@OneToOne
	public Employee getChief() {
		return chief;
	}

	public void setChief(Employee chief) {
		this.chief = chief;
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

//	@OneToMany(mappedBy="department")
	@OneToMany
	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return this.getLabel();
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
		Department other = (Department) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getCodeDepartment() {
		return codeDepartment;
	}

	public void setCodeDepartment(String codeDepartment) {
		this.codeDepartment = codeDepartment;
	}
	
}
