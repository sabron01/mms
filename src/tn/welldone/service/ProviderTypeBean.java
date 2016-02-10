package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.ProviderTypeRepository;
import tn.welldone.model.ProviderType;

@Stateless
@Local
public class ProviderTypeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	ProviderTypeRepository providerTypeRepository;
	
	public ProviderType addProviderType(ProviderType providerType){
		
		providerTypeRepository.add(providerType);
		return providerType;
		
	}
	
	public ProviderType editProviderType(ProviderType providerType){
		providerTypeRepository.edit(providerType);
		return providerType;
		
	}
	
	public ProviderType deleteProviderType(ProviderType providerType){
		providerTypeRepository.delete(providerType);
		return providerType;
		
	}
	
	public ProviderType getProviderTypeById(int id){
		return providerTypeRepository.getProviderTypeById(id);
	}
	
	public List<ProviderType> getAllProviderTypes(){
		return providerTypeRepository.getNonDeletedProviders();
	}
	

}
