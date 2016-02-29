package tn.welldone.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.InvoiceRepository;
import tn.welldone.model.Invoice;
import tn.welldone.model.Item;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Tache;

@Local
@Stateless
@Named
public class InvoiceBean implements Serializable {

	private static final long serialVersionUID = 9045986005423917507L;

	@Inject
	InvoiceRepository invoiceRepository;

	public Invoice getInvoiceById(int id) {
		return invoiceRepository.getInvoiceById(id);

	}
	
	public Item getItemById(int id) {
		return invoiceRepository.getItemById(id);
	}

	public Invoice getInvoiceByMedicalJourney(MedicalJourney medicalJourney) {
		return invoiceRepository.getInvoiceByMedicalJourney(medicalJourney);
	}

	public List<Invoice> getAllInvoices() {

		return invoiceRepository.getNonDeletedInvoices();

	}

	public void addInvoice(Invoice invoice) {
		invoiceRepository.add(invoice);
	}

	public Invoice editInvoice(Invoice invoice) {
		invoiceRepository.edit(invoice);
		return invoice;
	}

	public void generateNewInvoice(MedicalJourney medicalJourney) {
		Invoice invoice = new Invoice();
		invoice.setMedicalJourney(medicalJourney);
		invoice.setOwner(medicalJourney.getContract().getInsuranceAgency());
		invoice.setBeneficiary(medicalJourney.getContract().getInsuranceAgency());
		invoice = generateProductsForInvoice(invoice,medicalJourney);
		addInvoice(invoice);
	}

	private Invoice generateProductsForInvoice(Invoice invoice,MedicalJourney medicalJourney) {
		Collection<Tache> taches = medicalJourney.getTaches();
		Collection<Item> products = new ArrayList<Item>();
		for(Tache t : taches){
			Item item = new Item();
			item.setProduct(t);
			products.add(item);
		}
		invoice.setItems(products);
		return invoice;
	}



}
