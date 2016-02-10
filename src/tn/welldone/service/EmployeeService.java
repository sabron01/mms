package tn.welldone.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.EmployeeRepository;
import tn.welldone.model.Department;
import tn.welldone.model.Employee;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.MedicalJourneyEmployee;
import tn.welldone.model.PhoneNumber;
import tn.welldone.model.Service;

@Stateless
@Local
@Named
public class EmployeeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998111900854981915L;

	@Inject
	EmployeeRepository employeeReposotiry;

	public List<Employee> getEmployees() {
		return employeeReposotiry.getEmployees();
	}

	public List<Employee> getManagers() {
		return employeeReposotiry.getManagers();
	}

	public Employee getEmployeeById(int id) {
		return employeeReposotiry.getEmployeeById(id);
	}

	public Employee addEmployee(Employee employee) {
		employeeReposotiry.add(employee);
		return employee;
	}

	public Employee addEmployee(Employee employee, Department department,
			Employee manager, Department managedDepartment) {
		return employeeReposotiry.add(employee, department, manager,
				managedDepartment);
	}

	public Employee addEmployee(Employee employee,
			Collection<Location> addresses,
			Collection<PhoneNumber> phoneNumbers, Department department,
			Employee manager, Department managedDepartment) {
		return employeeReposotiry.add(employee, addresses, phoneNumbers,
				department, manager, managedDepartment);
	}

	public Employee editEmployee(Employee employee) {
		return employeeReposotiry.edit(employee);

	}

	public Employee editEmployee(Employee employee,
			Collection<Location> addresses,
			Collection<PhoneNumber> phoneNumbers, Department department,
			Employee manager, Department managedDepartment) {
		return employeeReposotiry.edit(employee, addresses, phoneNumbers,
				department, manager, managedDepartment);
	}

	public Employee editEmployee(Employee employee, Department department,
			Employee manager, Department managedDepartment) {
		return employeeReposotiry.edit(employee, department, manager,
				managedDepartment);
	}

	public Employee deleteEmployee(Employee c) {
		employeeReposotiry.delete(c);
		return c;
	}
	
	public List<Employee> getEmployeesByService(Service service) {
		List<Employee> employees = new ArrayList<Employee>();
		List<Employee> fetchedEmployees = employeeReposotiry.getEmployeesByService(service);
		if (fetchedEmployees != null)
			employees.addAll(fetchedEmployees);
		Set<Employee> hs = new LinkedHashSet<>(employees);
		hs.addAll(employees);
		employees.clear();
		employees.addAll(hs);
		return employees;
	}

	public List<Employee> getEmployeesByService(List<Service> selectedServices) {
		List<Employee> employees = new ArrayList<Employee>();
		for (Service s : selectedServices) {
			List<Employee> fetchedEmployees = employeeReposotiry
					.getEmployeesByService(s);
			
			if (fetchedEmployees != null)
				employees.addAll(fetchedEmployees);
		}
		Set<Employee> hs = new LinkedHashSet<>(employees);
		hs.addAll(employees);
		employees.clear();
		employees.addAll(hs);
		return employees;
	}

	public boolean checkExistedEmployeeByServiceForAffectedList(Service service,Employee employee,
			Collection<Employee> affectedEmployees) {
		for (Employee e : affectedEmployees) {
			if(e.getFunction().equals(employee.getFunction()))
				return true;
		}
		return false;
	}

	public boolean checkExistedEmployeeByServiceForMedicalJourney(
			Service service, Employee employee,
			MedicalJourney medicalJourney) {
		for (MedicalJourneyEmployee mje : medicalJourney.getMedicalJourneyEmployees()) {
			if(mje.getEmployee().equals(employee) && mje.getService().equals(service))
				return true;
		}
		return false;
	}

}
