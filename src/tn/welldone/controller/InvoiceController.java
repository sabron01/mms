package tn.welldone.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.model.Invoice;
import tn.welldone.model.Item;
import tn.welldone.model.MedicalJourney;
import tn.welldone.service.InvoiceBean;
import tn.welldone.service.MedicalJourneyBean;
import tn.welldone.service.NotificationBean;

@Named("InvoiceController")
@RequestScoped
public class InvoiceController implements Serializable {

	private static final long serialVersionUID = 6934318577686945595L;

	@EJB
	private MedicalJourneyBean medicalJourneyBean;

	@EJB
	private InvoiceBean invoiceBean;

	@EJB
	private NotificationBean notificationBean;

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private transient Logger logger;

	private MedicalJourney medicalJourney;

	private Invoice invoice;
	
	private Item item;

	private Collection<MedicalJourney> list;
	
	private Collection<MedicalJourney> listUngeneratedInvoicesMedicalJourney;

	private int medicalJourneyId;
	
	private int itemId;

	@javax.annotation.PostConstruct
	public void init() {
		System.out.println("am hear !!!");
		setListUngeneratedInvoicesMedicalJourney(medicalJourneyBean.getNonGeneratedInvoicesMedicalJourneys());
		setList(medicalJourneyBean.getGeneratedInvoicesMedicalJourneys());
		String s_medicalJourneyId = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("medicalJourneyId");
		String s_itemId = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("itemId");
		if (s_itemId != null) {
			int iId = Integer.parseInt(s_itemId);
			setItemId(iId);
			Item i = invoiceBean.getItemById(iId);
			if (i != null)
				setItem(i);
		}
		if (s_medicalJourneyId != null) {
			int mId = Integer.parseInt(s_medicalJourneyId);
			setMedicalJourneyId(mId);
			MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(mId);
			if (m != null){
				setMedicalJourney(m);
				setInvoice(invoiceBean.getInvoiceByMedicalJourney(m));
			}	
		}
	}

	public String showEditInvoice(int id) {
		
		return "/facturation/showEditInvoice.faces?faces-redirect=true&medicalJourneyId="+id;
	}
	
	public String updateItem() {
		setMedicalJourney(medicalJourneyBean.getMedicalJourneyById(medicalJourneyId));
		setInvoice(invoiceBean.getInvoiceByMedicalJourney(medicalJourney));
		return "/facturation/showEditInvoice.faces";
	}

	public String showGenerateInvoice(int id) {
		System.out.println("showGenerateInvoice ID = "+id);
		setMedicalJourney(medicalJourneyBean.getMedicalJourneyById(medicalJourneyId));
		invoiceBean.generateNewInvoice(medicalJourney);
		setInvoice(invoiceBean.getInvoiceByMedicalJourney(medicalJourney));
		return "/facturation/showGenerateInvoice.faces";
	}

	public String verifyItemInvoice(int id) {
		return "/facturation/showGenerateInvoice.faces?faces-redirect=true&itemId="+id;
	}
	
	public String showDetailsInvoice(int id) {
		MedicalJourney m = medicalJourneyBean.getMedicalJourneyById(id);
		return "showDetailsMedicalJourney.faces";
	}

	public String editInvoice() {
		return "/facturation/listInvoices.faces?faces-redirect=true";
	}

	public String listInvoices() {
		setList(medicalJourneyBean.getAllMedicalJourneys());
		return "/facturation/listInvoices.faces?faces-redirect=true";
	}

	public String listUnGeneratedInvoices() {
		return "/facturation/listUngeneratedInvoices.faces?faces-redirect=true";
	}

	public Collection<MedicalJourney> getList() {
		return list;
	}

	public void setList(Collection<MedicalJourney> list) {
		this.list = list;
	}

	public int getMedicalJourneyId() {
		return medicalJourneyId;
	}

	public void setMedicalJourneyId(int medicalJourneyId) {
		this.medicalJourneyId = medicalJourneyId;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Collection<MedicalJourney> getListUngeneratedInvoicesMedicalJourney() {
		return listUngeneratedInvoicesMedicalJourney;
	}

	public void setListUngeneratedInvoicesMedicalJourney(
			Collection<MedicalJourney> listUngeneratedInvoicesMedicalJourney) {
		this.listUngeneratedInvoicesMedicalJourney = listUngeneratedInvoicesMedicalJourney;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}
