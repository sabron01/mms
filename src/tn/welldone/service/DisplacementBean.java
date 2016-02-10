package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.DisplacementRepository;
import tn.welldone.data.ServiceProviderRepository;
import tn.welldone.model.Displacement;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.ServiceProvider;

@Local
@Stateless
@Named
public class DisplacementBean implements Serializable {

	
	private static final long serialVersionUID = 3972230447894034250L;
    
	@Inject
	DisplacementRepository displacementRepository;
	
	@Inject
	private ServiceProviderRepository serviceProviderReposotiry;	
	
   public Displacement addDisplacement(Displacement displacement){
		
		displacementRepository.add(displacement);
		return displacement;
		
	}
	
	public Displacement editDisplacement(Displacement displacement){
        displacementRepository.edit(displacement);
		return displacement;
		
	}
	
	public Displacement deleteDisplacement(Displacement displacement){
		displacementRepository.delete(displacement);
		return displacement;
		
	}
	
	public Displacement getDisplacementById(int id){
		return displacementRepository.getDisplacementById(id);
		
		
	}
	
	public List<Displacement> getAllDisplacements(){
		
		return displacementRepository.getNonDeletedDisplacements();

	}

	public Displacement addDisplacement(Displacement displacement,
			MedicalJourney medicalJourney, ServiceProvider agency, Location startLocation, Location endLocation) {
		displacementRepository.add(displacement,medicalJourney,agency,startLocation,endLocation);
		return displacement;
	}

	public List<Displacement> getListByMedicalJourney(MedicalJourney m) {
		return displacementRepository.getListByMedicalJourney(m);
	}

	public List<ServiceProvider> getDisplacementAgencies() {
		return serviceProviderReposotiry.getDisplacementAgencies();
	}
	
}
