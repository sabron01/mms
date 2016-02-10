package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import tn.welldone.model.Service;
import tn.welldone.service.ServiceBean;

@Named("ServiceController")
@RequestScoped
@ManagedBean
public class ServiceController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ServiceBean serviceBean;
	
	private List<Service> list;

	private Service service = new Service();

	private Service selectedService = new Service();

	@javax.annotation.PostConstruct
	public void init() {
		list = serviceBean.getAllServicess();		
	}

	public String createNewService() {
		return "addService.faces";
	}
	
	public String createService() {
		service = serviceBean.addService(service);
		return "service.faces?faces-redirect=true";
	}

	public String showEditService(int id) {
		Service s = serviceBean.getServiceById(id);
		setSelectedService(s);
		return "showEditService.faces";
	}
	
	public String deleteService(int id) {
		Service s = serviceBean.getServiceById(id);
		serviceBean.deleteService(s);
		return "service.faces?faces-redirect=true";
	}

	public String editService() {
		serviceBean.editService(selectedService);
		return "service.faces?faces-redirect=true";
	}

	public String listServices() {
		return "service.faces?faces-redirect=true";
	}

	public ServiceBean getServiceBean() {
		return serviceBean;
	}

	public void setServiceBean(ServiceBean serviceBean) {
		this.serviceBean = serviceBean;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Service> getList() {
		return list;
	}

	public void setList(List<Service> list) {
		this.list = list;
	}

    public Service getSelectedService() {
		return selectedService;
	}

	public void setSelectedService(Service selectedService) {
		this.selectedService = selectedService;
	}

}
