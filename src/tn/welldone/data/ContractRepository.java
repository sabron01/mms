package tn.welldone.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Contract;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.User;

@Stateless
@Local
public class ContractRepository implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7606618152319725603L;
	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;
	
	@Inject
	private SessionUser session;
	
	public enum Action {
		CREATE,UPDATE,DELETE
	}
	
	public void setTransactionDetails(Contract contract,Action action){
		User user = session.getUser();
		switch (action)
	    {

	      case CREATE:
	    	  contract.setAddedBy(user);
	    	  contract.setCreatedAt(new Date());
	    	  contract.setUpdateAt(new Date());
	    	  break;
	 
	      case UPDATE:
	    	  contract.setEditedBy(user);
	    	  contract.setUpdateAt(new Date());
	    	  break;
	 
	      case DELETE:
	    	  contract.setDeletedBy(user);
	    	  contract.setDeletedAt(new Date());
	    	  break;
	    }
		
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(Contract contract) {
		setTransactionDetails(contract, Action.CREATE);
		contract.setIsDeleted(false);
		entityManager.persist(contract);
		entityManager.flush();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Contract add(Contract contract,ServiceProvider insuranceAgency) {
		setTransactionDetails(contract, Action.CREATE);
		contract.setIsDeleted(false);
		entityManager.persist(contract);
		contract.setInsuranceAgency(insuranceAgency);
		Contract sp =entityManager.merge(contract);
		entityManager.flush();		
		return sp;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Contract contract) {
		setTransactionDetails(contract, Action.UPDATE);
		contract.setIsDeleted(false);
		Contract a = entityManager.merge(contract);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Contract contract) {
		setTransactionDetails(contract, Action.DELETE);
		// Option 1
		contract.setIsDeleted(true);
		Contract a = entityManager.merge(contract);
	
		// Option 2
		// entityManager.remove(service);
	
		entityManager.flush();
	}

	public List<Contract> getList() {
		return entityManager.createQuery("select i from Contract i")
				.getResultList();

	}

	public List<Contract> getContracts() {
		return entityManager.createQuery(
				"select i from Contract i where i.isDeleted = 'false' ")
				.getResultList();

	}

	public Contract getContractById(int id) {
		return entityManager.getReference(Contract.class, id);
	}

}
