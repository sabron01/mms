package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import tn.welldone.model.SystemResource;
import tn.welldone.service.SystemResourceBean;

@Named("SystemResourceController")
@RequestScoped
public class SystemResourceController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2824710672349736036L;

	@EJB
	private SystemResourceBean systemResourceBean;

	private List<SystemResource> list;

	private SystemResource systemResource = new SystemResource();

	private SystemResource selectedSystemResource = new SystemResource();

	@javax.annotation.PostConstruct
	public void init() {
		list = systemResourceBean.getAllSystemResourcess();
	}

	public String createNewSystemResource() {
		return "addSystemResource.faces";
	}

	public String createSystemResource() {
		systemResource = systemResourceBean.addSystemResource(systemResource);
		return "systemResource.faces?faces-redirect=true";
	}

	public String showEditSystemResource(int id) {
		SystemResource s = systemResourceBean.getSystemResourceById(id);
		setSelectedSystemResource(s);
		return "showEditSystemResource.faces";
	}

	public String deleteSystemResource(int id) {
		SystemResource s = systemResourceBean.getSystemResourceById(id);
		systemResourceBean.deleteSystemResource(s);
		return "systemResource.faces?faces-redirect=true";
	}

	public String editSystemResource() {
		systemResourceBean.editSystemResource(selectedSystemResource);
		return "systemResource.faces?faces-redirect=true";
	}

	public String listSystemResources() {
		return "systemResource.faces?faces-redirect=true";
	}

	public SystemResourceBean getSystemResourceBean() {
		return systemResourceBean;
	}

	public void setSystemResourceBean(SystemResourceBean systemResourceBean) {
		this.systemResourceBean = systemResourceBean;
	}

	public SystemResource getSystemResource() {
		return systemResource;
	}

	public void setSystemResource(SystemResource systemResource) {
		this.systemResource = systemResource;
	}

	public List<SystemResource> getList() {
		return list;
	}

	public void setList(List<SystemResource> list) {
		this.list = list;
	}

	public SystemResource getSelectedSystemResource() {
		return selectedSystemResource;
	}

	public void setSelectedSystemResource(SystemResource selectedSystemResource) {
		this.selectedSystemResource = selectedSystemResource;
	}

}
