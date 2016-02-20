package tn.welldone.data;

import java.io.Serializable;
import java.util.Collection;
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
import tn.welldone.data.EmployeeRepository.Action;
import tn.welldone.model.Location;
import tn.welldone.model.ProviderType;
import tn.welldone.model.Service;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.User;

@Stateless
@Local
public class ServiceProviderRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(ServiceProvider agency, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			agency.setAddedBy(user);
			agency.setCreatedAt(new Date());
			agency.setUpdateAt(new Date());
			break;

		case UPDATE:
			agency.setEditedBy(user);
			agency.setUpdateAt(new Date());
			break;

		case DELETE:
			agency.setDeletedBy(user);
			agency.setDeletedAt(new Date());
			break;
		}

	}
	
	public void setTransactionDetails(Location location, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			location.setAddedBy(user);
			location.setCreatedAt(new Date());
			location.setUpdateAt(new Date());
			break;

		case UPDATE:
			location.setEditedBy(user);
			location.setUpdateAt(new Date());
			break;

		case DELETE:
			location.setDeletedBy(user);
			location.setDeletedAt(new Date());
			break;
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void add(ServiceProvider agency) {
		setTransactionDetails(agency, Action.CREATE);
		agency.setIsDeleted(false);
		entityManager.persist(agency);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ServiceProvider add(ServiceProvider insuranceAgency,
			ProviderType typeProvider) {
		setTransactionDetails(insuranceAgency, Action.CREATE);
		insuranceAgency.setIsDeleted(false);
		entityManager.persist(insuranceAgency);
		insuranceAgency.setTypeProvider(typeProvider);
		ServiceProvider sp = entityManager.merge(insuranceAgency);
		entityManager.flush();
		return sp;
	}

	public ServiceProvider add(ServiceProvider partner, Location location,
			ProviderType typeProvider, List<Service> services) {
		entityManager.persist(location);
		setTransactionDetails(partner, Action.CREATE);
		partner.setAddress(location);
		partner.setIsDeleted(false);
		entityManager.persist(partner);
		partner.setTypeProvider(typeProvider);
		partner.setServices(services);
		ServiceProvider sp = entityManager.merge(partner);
		entityManager.flush();
		return sp;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ServiceProvider add(ServiceProvider insuranceAgency,
			ProviderType typeProvider, List<Service> services) {
		setTransactionDetails(insuranceAgency, Action.CREATE);
		insuranceAgency.setIsDeleted(false);
		entityManager.persist(insuranceAgency);
		insuranceAgency.setTypeProvider(typeProvider);
		insuranceAgency.setServices(services);
		ServiceProvider sp = entityManager.merge(insuranceAgency);
		entityManager.flush();
		return sp;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ServiceProvider add(Location location,
			ServiceProvider insuranceAgency, ProviderType typeProvider,
			List<Service> services) {
		entityManager.persist(location);
		setTransactionDetails(insuranceAgency, Action.CREATE);
		insuranceAgency.setAddress(location);
		insuranceAgency.setIsDeleted(false);
		entityManager.persist(insuranceAgency);
		insuranceAgency.setTypeProvider(typeProvider);
		insuranceAgency.setServices(services);
		ServiceProvider sp = entityManager.merge(insuranceAgency);
		entityManager.flush();
		return sp;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ServiceProvider edit(ServiceProvider insuranceAgency, Location location) {
		setTransactionDetails(location, Action.UPDATE);
		Location l = entityManager.merge(location);
		setTransactionDetails(insuranceAgency, Action.UPDATE);
		insuranceAgency.setAddress(l);
		insuranceAgency.setIsDeleted(false);
		ServiceProvider sp = entityManager.merge(insuranceAgency);
		entityManager.flush();
		return sp;
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(ServiceProvider agency) {
		setTransactionDetails(agency, Action.UPDATE);
		agency.setIsDeleted(false);
		ServiceProvider a = entityManager.merge(agency);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(ServiceProvider agency) {
		setTransactionDetails(agency, Action.DELETE);
		// Option 1
		agency.setIsDeleted(true);
		agency.setServices(null);
		ServiceProvider a = entityManager.merge(agency);

		// Option 2
		// entityManager.remove(service);

		entityManager.flush();
	}

	public ServiceProvider getServiceProviderById(int id) {
		return entityManager.getReference(ServiceProvider.class, id);
	}

	public List<ServiceProvider> getInsuranceAgencies() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 5")
				.getResultList();
	}

	public List<ServiceProvider> getNonDeletedInsuranceAgencies() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 5 ")
				.getResultList();

	}

	public List<ServiceProvider> getHotels() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 1")
				.getResultList();
	}

	public List<ServiceProvider> getClinics() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 4")
				.getResultList();
	}

	public List<ServiceProvider> getRealEstateAgencies() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 2")
				.getResultList();
	}

	public List<ServiceProvider> getRentAcarAgencies() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 3")
				.getResultList();
	}

	public List<ServiceProvider> getAmbulancesAgencies() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 4")
				.getResultList();
	}

	public List<ServiceProvider> getPharmacies() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 6")
				.getResultList();
	}

	public List<ServiceProvider> getDisplacementAgencies() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id IN (3,4)")
				.getResultList();
	}

	public Collection<ServiceProvider> getAirports() {
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 8")
				.getResultList();
	}
	
	public Collection<ServiceProvider> getTravelAgencies(){
		return entityManager
				.createQuery(
						"select i from ServiceProvider i where i.isDeleted = 'false' AND i.typeProvider.id = 7")
				.getResultList();
	}



}
