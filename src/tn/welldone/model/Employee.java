package tn.welldone.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8014996297220041368L;
	private String codeEmployee;
	private Function function;
	private Collection<Service> activities;
	private Collection<Employee> managedEmployees;
	private Employee manager;
	private User user;
	private Department department;
	private Department managedDepartment;
	private String isManager;
	private double salary;
	private Calendar startDate;
	private Calendar endDate;
	private Collection<MedicalJourney> managedMedicalJourneys;
	private Set<MedicalJourneyEmployee> medicalJourneyEmployees = new HashSet<MedicalJourneyEmployee>(0);



	// Need to check the Fetch type
//	@OneToMany(mappedBy="manager",fetch=FetchType.LAZY)
	@OneToMany
	public Collection<Employee> getManagedEmployees() {
		return managedEmployees;
	}

	public void setManagedEmployees(Collection<Employee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}

//	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@ManyToOne
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@ManyToOne
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

//	@OneToOne(mappedBy="chief")
	@OneToOne
	public Department getManagedDepartment() {
		return managedDepartment;
	}

	public void setManagedDepartment(Department managedDepartment) {
		this.managedDepartment = managedDepartment;
	}

	public String getCodeEmployee() {
		return codeEmployee;
	}

	public void setCodeEmployee(String codeEmployee) {
		this.codeEmployee = codeEmployee;
	}

	public Boolean getIsManager() {
		if (isManager == null)
			return null;
		return isManager == "Y" ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsManager(Boolean active) {
		if (active == null) {
			this.isManager = null;
		} else {
			this.isManager = active == true ? "Y" : "N";
		}
	}

	@ManyToMany(mappedBy = "affectedEmployees")
	public Collection<MedicalJourney> getManagedMedicalJourneys() {
		return managedMedicalJourneys;
	}

	public void setManagedMedicalJourneys(Collection<MedicalJourney> managedMedicalJourneys) {
		this.managedMedicalJourneys = managedMedicalJourneys;
	}

	@ManyToOne
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	@OneToOne(mappedBy="employee")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JoinTable(name = "EmployeeActivities", joinColumns = { @JoinColumn(name = "service_id") }, inverseJoinColumns = { @JoinColumn(name = "employee_id") })
	@ManyToMany(cascade = {CascadeType.ALL})
	public Collection<Service> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Service> activities) {
		this.activities = activities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicalJourneyEmployeeId.employee")
	public Set<MedicalJourneyEmployee> getMedicalJourneyEmployees() {
		return medicalJourneyEmployees;
	}

	public void setMedicalJourneyEmployees(Set<MedicalJourneyEmployee> medicalJourneyEmployees) {
		this.medicalJourneyEmployees = medicalJourneyEmployees;
	}

}