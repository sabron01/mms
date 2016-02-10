package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.PermissionRepository;
import tn.welldone.model.Permission;

@Stateless
@Local
public class PermissionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1502279889876278987L;
	@Inject
	PermissionRepository permissionRepository;

	public void addPermission(Permission permission) {
		permissionRepository.add(permission);
	}

	public Permission editPermission(Permission permission) {
		permissionRepository.edit(permission);
		return permission;

	}

	public Permission deletePermission(Permission permission) {
		permissionRepository.delete(permission);
		return permission;

	}

	public Permission getPermissionById(int id) {
		return permissionRepository.getPermissionById(id);

	}

	public List<Permission> getAllPermissions() {
		return permissionRepository.getAllPermissions();
	}

	public List<Permission> getGroupedPermissionsByResource() {
		return permissionRepository.getGroupedPermissionsByResource();
	}

}
