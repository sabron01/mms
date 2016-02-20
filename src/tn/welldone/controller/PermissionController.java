package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import tn.welldone.helpers.Operation;
import tn.welldone.model.Permission;
import tn.welldone.model.SystemResource;
import tn.welldone.service.PermissionBean;

@Named("PermissionController")
@RequestScoped
@ViewScoped
public class PermissionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1800313847517207223L;

	@EJB
	private PermissionBean permissionBean;

	private Permission permission = new Permission();

	private Permission selectedPermission = new Permission();

	private SystemResource systemResource = new SystemResource();

	private List<Permission> list;
	
	private List<Permission> permissions;

	private Operation[] operations;

	@javax.annotation.PostConstruct
	public void init() {
		this.setOperations(Operation.values());
		this.permissions = permissionBean.getGroupedPermissionsByResource();
		setList(permissionBean.getAllPermissions());
	}

	public String createNewPermission() {
		return "addPermission.faces?faces-redirect=true";
	}

	public String createPermission() {
		permission.setSystemResource(systemResource);
		permissionBean.addPermission(permission);
		return "permission.faces?faces-redirect=true";
	}

	public String showEditPermission(int id) {
		Permission p = permissionBean.getPermissionById(id);		
		setSelectedPermission(p);
		setSystemResource(selectedPermission.getSystemResource());
		return "showEditPermission.faces";
	}

	public String deletePermission(int id) {
		Permission p = permissionBean.getPermissionById(id);
		permissionBean.deletePermission(p);
		return "permission.faces?faces-redirect=true";
	}

	public String editPermission() {
		permissionBean.editPermission(selectedPermission);
		return "permission.faces?faces-redirect=true";
	}

	public String listPermissions() {
		return "permission.faces?faces-redirect=true";
	}

	public PermissionBean getPermissionBean() {
		return permissionBean;
	}

	public void setPermissionBean(PermissionBean permissionBean) {
		this.permissionBean = permissionBean;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Permission getSelectedPermission() {
		return selectedPermission;
	}

	public void setSelectedPermission(Permission selectedPermission) {
		this.selectedPermission = selectedPermission;
	}

	public List<Permission> getList() {
		return list;
	}

	public void setList(List<Permission> list) {
		this.list = list;
	}

	public Operation[] getOperations() {
		return operations;
	}

	public void setOperations(Operation[] operations) {
		this.operations = operations;
	}

	public SystemResource getSystemResource() {
		return systemResource;
	}

	public void setSystemResource(SystemResource systemResource) {
		this.systemResource = systemResource;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
