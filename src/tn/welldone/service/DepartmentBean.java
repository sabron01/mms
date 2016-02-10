package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.DepartmentRepository;
import tn.welldone.model.Department;
import tn.welldone.model.Employee;

@Stateless
@Local
public class DepartmentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1502279889876278987L;
	@Inject
	DepartmentRepository departmentRepository;

	public Department addDepartment(Department department) {

		departmentRepository.add(department);
		return department;

	}

	public Department editDepartment(Department department) {
		departmentRepository.edit(department);
		return department;
	}

	public Department deleteDepartment(Department department) {
		departmentRepository.delete(department);
		return department;
	}

	public Department getDepartmentById(int id) {
		return departmentRepository.getDepartmentById(id);

	}

	public List<Department> getAllDepartments() {
		return departmentRepository.getAllDepartments();
	}

	public Department addDepartment(Department department, Employee manager) {
		Department d = departmentRepository.add(department, manager);
		return d;
	}

}
