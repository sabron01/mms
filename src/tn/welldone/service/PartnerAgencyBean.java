package tn.welldone.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.DoctorRepository;
import tn.welldone.data.ProviderTypeRepository;
import tn.welldone.data.ServiceProviderRepository;
import tn.welldone.model.Doctor;
import tn.welldone.model.Location;
import tn.welldone.model.ProviderType;
import tn.welldone.model.Service;
import tn.welldone.model.ServiceProvider;

@Stateless
@Local
public class PartnerAgencyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Inject
	private ServiceProviderRepository serviceProviderReposotiry;
	
	@Inject
	ProviderTypeRepository providerTypeRepository;
	
	@Inject
	private DoctorRepository doctorRepository;
	
	public ProviderType getProviderTypeById(int id){
		return providerTypeRepository.getProviderTypeById(id);
	}
	
	public ServiceProvider addServiceProvider(ServiceProvider partner) {
		serviceProviderReposotiry.add(partner);
		return partner;
	}
	
	public Doctor addServiceProvider(Doctor doctor,ServiceProvider clinic) {
		doctorRepository.add(doctor, clinic);
		return doctor;
	}
	
	public ServiceProvider addServiceProvider(ServiceProvider partner,ProviderType typeProvider) {
		ServiceProvider sp = serviceProviderReposotiry.add(partner,typeProvider);		
		return sp;

	}
	
	public ServiceProvider addServiceProvider(ServiceProvider partner,
			Location location, ProviderType typeProvider, List<Service> services) {
		ServiceProvider sp = serviceProviderReposotiry.add(partner,location,typeProvider,services);		
		return sp;
	}
	
	public ServiceProvider addServiceProvider(ServiceProvider partner,ProviderType typeProvider, List<Service> services) {
		ServiceProvider sp = serviceProviderReposotiry.add(partner,typeProvider,services);		
		return sp;
	}
	
	public ServiceProvider editPartner(ServiceProvider partner) {
		serviceProviderReposotiry.edit(partner);
		return partner;
	}
	
	public ServiceProvider deletePartner(ServiceProvider partner) {
		serviceProviderReposotiry.delete(partner);
		return partner;
	}
	
	public ServiceProvider getPartnerById(int id){
		return serviceProviderReposotiry.getServiceProviderById( id);
	}


	public List<ServiceProvider> getClinics() {
		return serviceProviderReposotiry.getClinics();
	}

	public List<ServiceProvider> getHotels() {
		return serviceProviderReposotiry.getHotels();
	}

	public List<ServiceProvider> getRealEstateAgencies() {
		return serviceProviderReposotiry.getRealEstateAgencies();
	}

	public List<ServiceProvider> getRentAcarAgencies() {
		return serviceProviderReposotiry.getRentAcarAgencies();
	}

	public List<ServiceProvider> getAmbulancesAgencies() {
		return serviceProviderReposotiry.getAmbulancesAgencies();
	}

	public List<ServiceProvider> getPharmacies() {
		return serviceProviderReposotiry.getPharmacies();
	}
	
	public List<Doctor> getDoctors() {
		
		return doctorRepository.getDoctors();

	}
	
	public Collection<ServiceProvider> getTravelAgencies(){
		return serviceProviderReposotiry.getTravelAgencies();
	}
	
	public Collection<ServiceProvider> getAirports(){
		return serviceProviderReposotiry.getAirports();
	}

	public DoctorRepository getDoctorRepository() {
		return doctorRepository;
	}

	public void setDoctorRepository(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}


	
}
