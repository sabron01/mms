package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.SystemResourceRepository;
import tn.welldone.model.SystemResource;

@Stateless
@Local
public class SystemResourceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	SystemResourceRepository systemResourceRepository;
	
	

	public SystemResource addSystemResource(SystemResource systemResource) {
		systemResourceRepository.add(systemResource);
		return systemResource;
	}
	
	public SystemResource editSystemResource(SystemResource systemResource) {
		systemResourceRepository.edit(systemResource);
		return systemResource;
	}
	
	public SystemResource deleteSystemResource(SystemResource systemResource) {
		systemResourceRepository.delete(systemResource);
		return systemResource;
	}
	
	public SystemResource getSystemResourceById(int id){
		return systemResourceRepository.getSystemResourceById( id);
	}
	
	public List<SystemResource> getAllSystemResourcess() {
				
		return systemResourceRepository.getNonDeletedSystemResources();

	}

}
