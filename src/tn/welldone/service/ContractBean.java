package tn.welldone.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.ContractRepository;
import tn.welldone.model.Contract;
import tn.welldone.model.Service;
import tn.welldone.model.ServiceProvider;

@Stateless
@Local
@Named
public class ContractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4415593592305681818L;
	
	@Inject
	private ContractRepository contractReposotiry;

	public Contract addContract(Contract contract) {
		contractReposotiry.add(contract);
		return contract;
	}
	
	public Contract addContract(Contract contract,ServiceProvider serviceProvider) {
		Contract sp = contractReposotiry.add(contract,serviceProvider);		
		return sp;

	}
	
	public Contract editContract(Contract contract) {
		contractReposotiry.edit(contract);
		return contract;
	}
	
	public Contract deleteContract(Contract contract) {
		contractReposotiry.delete(contract);
		return contract;
	}
	
	public Contract getContractById(int id){
		return contractReposotiry.getContractById( id);
	}
	
	public List<Contract> getContracts() {
				
		return contractReposotiry.getContracts();

	}

	public Collection<Service> getServicesByContract(int contract_id) {
		Contract contract = contractReposotiry.getContractById( contract_id);
		return contract.getInsuranceAgency().getServices();
	}

}
