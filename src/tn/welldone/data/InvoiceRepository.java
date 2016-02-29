package tn.welldone.data;

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
import tn.welldone.model.Invoice;
import tn.welldone.model.Item;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.User;

@Stateless
@Local
public class InvoiceRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Invoice invoice,
			Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			invoice.setAddedBy(user);
			invoice.setCreatedAt(new Date());
			invoice.setUpdateAt(new Date());
			break;

		case UPDATE:
			invoice.setEditedBy(user);
			invoice.setUpdateAt(new Date());
			break;

		case DELETE:
			invoice.setDeletedBy(user);
			invoice.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Invoice invoice) {
		setTransactionDetails(invoice, Action.CREATE);
		invoice.setIsDeleted(false);
		entityManager.persist(invoice);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Invoice invoice) {
		invoice.setIsDeleted(false);
		setTransactionDetails(invoice, Action.UPDATE);
		Invoice m = entityManager.merge(invoice);
		entityManager.flush();
	}

	public void edit(Invoice invoice, Contract contract) {
		setTransactionDetails(invoice, Action.UPDATE);
		invoice.setIsDeleted(false);
		entityManager.merge(invoice);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Invoice invoice) {
		setTransactionDetails(invoice, Action.DELETE);
		invoice.setIsDeleted(true);
		Invoice m = entityManager.merge(invoice);
		entityManager.flush();
	}

	public List<Invoice> getNonDeletedInvoices() {
		return entityManager.createQuery(
				"select m from Invoice m where m.isDeleted = 'false' ")
				.getResultList();

	}

	public Invoice getInvoiceById(int id) {
		return entityManager.getReference(Invoice.class, id);
	}
	
	public Item getItemById(int id) {
		return entityManager.getReference(Item.class, id);
	}
	
	public Invoice getInvoiceByMedicalJourney(MedicalJourney medicalJourney){
		return (Invoice) entityManager.createQuery(
				"select i from Invoice i where i.medicalJourney = '" + medicalJourney + "'")
				.getSingleResult();
	}



}
