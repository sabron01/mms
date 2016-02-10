package tn.welldone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import tn.welldone.model.Location;
import tn.welldone.model.ProviderType;
import tn.welldone.model.Service;
import tn.welldone.model.ServiceProvider;
import tn.welldone.service.InsuranceAgencyBean;
import tn.welldone.service.ProviderTypeBean;


@Named("InsuranceAgencyController")
@RequestScoped
public class InsuranceAgencyController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4702932483504962891L;

	@EJB
	private InsuranceAgencyBean insuranceAgencyBean;

	@EJB
	private ProviderTypeBean providerTypeBean;

	private ServiceProvider insuranceAgency = new ServiceProvider();

	private ServiceProvider selectedInsuranceAgency = new ServiceProvider();

	private Location location = new Location();

	private ProviderType typeProvider = new ProviderType();

	private List<Service> services = new ArrayList<Service>();

	private List<ServiceProvider> insuranceAgencies;

	@javax.annotation.PostConstruct
	public void init() {
		insuranceAgencies = insuranceAgencyBean.getInsuranceAgencies();
		setTypeProvider(providerTypeBean.getProviderTypeById(5));
	}

	
	public String createNewInsuranceAgency() {
		return "addInsuranceAgency.faces";
	}

	
	public String createInsuranceAgency() {
		insuranceAgency = insuranceAgencyBean.addServiceProvider(location,
				insuranceAgency, typeProvider, services);
		return "listInsuranceAgencies.faces?faces-redirect=true";
	}

	
	public String showEditInsuranceAgency(int id) {
		ServiceProvider s = insuranceAgencyBean.getInsuranceAgencyById(id);
		setLocation(s.getAddress());
		setSelectedInsuranceAgency(s);
		return "showEditInsuranceAgency.faces";
	}

	
	public String showDetailInsuranceAgency(int id) {
		ServiceProvider s = insuranceAgencyBean.getInsuranceAgencyById(id);
		setLocation(s.getAddress());
		setSelectedInsuranceAgency(s);
		return "showDetailInsuranceAgency.faces";
	}

	
	public String deleteInsuranceAgency(int id) {
		ServiceProvider s = insuranceAgencyBean.getInsuranceAgencyById(id);
		insuranceAgencyBean.deleteInsuranceAgency(s);
		return "listInsuranceAgencies.faces?faces-redirect=true";
	}

	
	public String editInsuranceAgency() {
		insuranceAgencyBean.editInsuranceAgency(selectedInsuranceAgency,location);
		return "listInsuranceAgencies.faces?faces-redirect=true";
	}

	
	public String listInsuranceAgencies() {
		return "listInsuranceAgencies.faces?faces-redirect=true";
	}

	public InsuranceAgencyBean getInsuranceAgencyBean() {
		return insuranceAgencyBean;
	}

	public void setInsuranceAgencyBean(InsuranceAgencyBean insuranceAgencyBean) {
		this.insuranceAgencyBean = insuranceAgencyBean;
	}

	public ServiceProvider getInsuranceAgency() {
		return insuranceAgency;
	}

	public void setInsuranceAgency(ServiceProvider insuranceAgency) {
		this.insuranceAgency = insuranceAgency;
	}

	public ServiceProvider getSelectedInsuranceAgency() {
		return selectedInsuranceAgency;
	}

	public void setSelectedInsuranceAgency(
			ServiceProvider selectedInsuranceAgency) {
		this.selectedInsuranceAgency = selectedInsuranceAgency;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public ProviderType getTypeProvider() {
		return typeProvider;
	}

	public void setTypeProvider(ProviderType typeProvider) {
		this.typeProvider = typeProvider;
	}

	public ProviderTypeBean getProviderTypeBean() {
		return providerTypeBean;
	}

	public void setProviderTypeBean(ProviderTypeBean providerTypeBean) {
		this.providerTypeBean = providerTypeBean;
	}

	public List<ServiceProvider> getInsuranceAgencies() {
		return insuranceAgencies;
	}

	public void setInsuranceAgencies(List<ServiceProvider> insuranceAgencies) {
		this.insuranceAgencies = insuranceAgencies;
	}

}
