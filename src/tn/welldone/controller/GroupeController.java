package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import tn.welldone.model.Employee;
import tn.welldone.model.Groupe;
import tn.welldone.service.GroupeBean;

@Named("GroupeController")
@RequestScoped
public class GroupeController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1800313847517207223L;

	@EJB
	private GroupeBean groupeBean;

	private Groupe groupe = new Groupe();

	private Groupe selectedGroupe = new Groupe();

	private Employee manager = new Employee();

	private List<Groupe> list;

	@javax.annotation.PostConstruct
	public void init() {
		setList(groupeBean.getAllGroupes());
	}

	public String createNewGroupe() {
		return "addGroupe.faces";
	}

	public String createGroupe() {
		groupe = groupeBean.addGroupe(groupe);
		return "groupe.faces?faces-redirect=true";
	}

	public String showEditGroupe(int id) {
		Groupe p = groupeBean.getGroupeById(id);
		setSelectedGroupe(p);
		return "showEditGroupe.faces";
	}

	public String showSetPermissions(int id) {
		Groupe p = groupeBean.getGroupeById(id);
		setSelectedGroupe(p);
		return "showSetPermissions.faces";
	}

	public String deleteGroupe(int id) {
		Groupe p = groupeBean.getGroupeById(id);
		groupeBean.deleteGroupe(p);
		return "groupe.faces?faces-redirect=true";
	}

	public String editGroupe() {
		groupeBean.editGroupe(selectedGroupe);
		return "groupe.faces?faces-redirect=true";
	}

	public String setPermissions() {
		groupeBean.editGroupe(selectedGroupe);
		return "groupe.faces?faces-redirect=true";
	}

	public String listGroupes() {
		return "groupe.faces?faces-redirect=true";
	}

	public GroupeBean getGroupeBean() {
		return groupeBean;
	}

	public void setGroupeBean(GroupeBean groupeBean) {
		this.groupeBean = groupeBean;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Groupe getSelectedGroupe() {
		return selectedGroupe;
	}

	public void setSelectedGroupe(Groupe selectedGroupe) {
		this.selectedGroupe = selectedGroupe;
	}

	public List<Groupe> getList() {
		return list;
	}

	public void setList(List<Groupe> list) {
		this.list = list;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

}
