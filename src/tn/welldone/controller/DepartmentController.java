package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import tn.welldone.model.Department;
import tn.welldone.model.Employee;
import tn.welldone.service.DepartmentBean;

@Named("DepartmentController")
@RequestScoped
public class DepartmentController implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1800313847517207223L;

	@EJB
	private DepartmentBean departmentBean;
	
	private Department department = new Department();
	
	private Department selectedDepartment = new Department();
	
	private Employee manager = new Employee();
	
	private List<Department> list;
	
	
	@javax.annotation.PostConstruct
	public void init() {
		setList(departmentBean.getAllDepartments());
	}

	public String createNewDepartment() {
		return "addDepartment.faces";
	}
	
	public String createDepartment() {
		department = departmentBean.addDepartment(department,manager);
		return "department.faces?faces-redirect=true";
	}

	public String showEditDepartment(int id) {
		Department p = departmentBean.getDepartmentById(id);
		setSelectedDepartment(p);
		return "showEditDepartment.faces";
	}
	
	public String deleteDepartment(int id) {
		Department p = departmentBean.getDepartmentById(id);
		departmentBean.deleteDepartment(p);
		return "department.faces?faces-redirect=true";
	}

	public String editDepartment() {
		selectedDepartment.setChief(manager);
		departmentBean.editDepartment(selectedDepartment);
		return "department.faces?faces-redirect=true";
	}

	public String listDepartments() {
		return "department.faces?faces-redirect=true";
	}
	

	public DepartmentBean getDepartmentBean() {
		return departmentBean;
	}

	public void setDepartmentBean(DepartmentBean departmentBean) {
		this.departmentBean = departmentBean;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(Department selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

	public List<Department> getList() {
		return list;
	}

	public void setList(List<Department> list) {
		this.list = list;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	

}
