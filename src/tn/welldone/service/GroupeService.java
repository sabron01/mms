package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.GroupeRepository;
import tn.welldone.model.Groupe;


@Stateless
@Local
@Named
public class GroupeService implements Serializable{

	private static final long serialVersionUID = 1L;
    
	@Inject
	GroupeRepository groupeReposotiry;

	public List<Groupe> getGroupes() {
		return groupeReposotiry.getGroupes();
	}
	
	

	public Groupe getGroupeById(int id) {
		return groupeReposotiry.getGroupeById(id);
	}

	public Groupe addGroupe(Groupe groupe) {
		groupeReposotiry.add(groupe);
		return groupe;
	}

//	public Groupe addEmployee(Employee employee, Department department,
//			Employee manager, Department managedDepartment) {
//		return employeeReposotiry.add(employee, department, manager,
//				managedDepartment);
//	}

//	public Employee editEmployee(Employee employee, Department department,
//			Employee manager, Department managedDepartment) {
//		return employeeReposotiry.edit(employee, department, manager,
//				managedDepartment);
//	}

	public Groupe editGroupe(Groupe groupe) {
		return groupeReposotiry.edit(groupe);

	}

	public Groupe deleteGroupe(Groupe g) {
		groupeReposotiry.delete(g);
		return g;
	}
}
