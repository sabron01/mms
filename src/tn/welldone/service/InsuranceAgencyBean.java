package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.ServiceProviderRepository;
import tn.welldone.model.Location;
import tn.welldone.model.ProviderType;
import tn.welldone.model.Service;
import tn.welldone.model.ServiceProvider;


@Stateless
@Local
public class InsuranceAgencyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceProviderRepository serviceProviderReposotiry;

	public ServiceProvider addServiceProvider(ServiceProvider agency) {
		serviceProviderReposotiry.add(agency);
		return agency;
	}

	public ServiceProvider addServiceProvider(ServiceProvider insuranceAgency,
			ProviderType typeProvider) {
		ServiceProvider sp = serviceProviderReposotiry.add(insuranceAgency,
				typeProvider);
		return sp;

	}

	public ServiceProvider addServiceProvider(ServiceProvider insuranceAgency,
			ProviderType typeProvider, List<Service> services) {
		ServiceProvider sp = serviceProviderReposotiry.add(insuranceAgency,
				typeProvider, services);
		return sp;
	}
	
	public ServiceProvider addServiceProvider(Location location,
			ServiceProvider insuranceAgency, ProviderType typeProvider,
			List<Service> services) {
		ServiceProvider sp = serviceProviderReposotiry.add(location,insuranceAgency,
				typeProvider, services);
		return sp;
	}

	public ServiceProvider editInsuranceAgency(ServiceProvider agency) {
		serviceProviderReposotiry.edit(agency);
		return agency;
	}
	
	public ServiceProvider editInsuranceAgency(ServiceProvider agency,Location location) {
		serviceProviderReposotiry.edit(agency,location);
		return agency;
	}

	public ServiceProvider deleteInsuranceAgency(ServiceProvider agency) {
		serviceProviderReposotiry.delete(agency);
		return agency;
	}

	public ServiceProvider getInsuranceAgencyById(int id) {
		return serviceProviderReposotiry.getServiceProviderById(id);
	}

	public List<ServiceProvider> getAllInsuranceAgencies() {
		
		return serviceProviderReposotiry.getInsuranceAgencies();
		
	}

	public List<ServiceProvider> getInsuranceAgencies() {
		
		return serviceProviderReposotiry.getNonDeletedInsuranceAgencies();
		
	}

}
