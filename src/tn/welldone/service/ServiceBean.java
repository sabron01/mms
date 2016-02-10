package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.ServiceRepository;
import tn.welldone.model.Function;
import tn.welldone.model.Service;

@Stateless
@Local
public class ServiceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	ServiceRepository serviceRepository;
	
	

	public Service addService(Service service) {
		serviceRepository.add(service);
		return service;
	}
	
	public Service editService(Service service) {
		serviceRepository.edit(service);
		return service;
	}
	
	public Service deleteService(Service service) {
		serviceRepository.delete(service);
		return service;
	}
	
	public Service getServiceById(int id){
		return serviceRepository.getServiceById( id);
	}
	
	public List<Service> getAllServicess() {
				
		return serviceRepository.getNonDeletedServices();

	}


}
