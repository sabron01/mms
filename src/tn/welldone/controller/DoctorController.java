package tn.welldone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import tn.welldone.model.City;
import tn.welldone.model.Country;
import tn.welldone.model.Doctor;
import tn.welldone.model.Location;
import tn.welldone.model.Person.CivilState;
import tn.welldone.model.Person.Gender;
import tn.welldone.model.Person.HonorificTitle;
import tn.welldone.model.PhoneNumber;
import tn.welldone.model.PhoneNumber.PhoneType;
import tn.welldone.model.Region;
import tn.welldone.model.ServiceProvider;
import tn.welldone.service.DataService;
import tn.welldone.service.DoctorBean;
import tn.welldone.service.PartnerAgencyBean;

@Named("DoctorController")
@ViewScoped
@RequestScoped
public class DoctorController implements Serializable {

	private static final long serialVersionUID = 4653891474454089335L;
	
	@EJB
	private DataService service;

	@EJB
	private DoctorBean doctorBean;
	
	@EJB
	private PartnerAgencyBean partnerAgencyBean;

	private List<Doctor> list;

	private Doctor doctor = new Doctor();

	private Doctor selectedDoctor = new Doctor();

	private ServiceProvider clinic = new ServiceProvider();
	
	private Location location = new Location();

	private PhoneNumber phone = new PhoneNumber();
	
	private Gender[] genders;

	private HonorificTitle[] honorificTitles;

	private CivilState[] civilStates;

	private PhoneType[] phoneTypes;
	
	private List<Country> countries;

	private List<Region> regions;

	private List<City> cities;

	private List<ServiceProvider> clinics;

	@javax.annotation.PostConstruct
	public void init() {
		this.setClinics(partnerAgencyBean.getClinics());
		this.countries = service.getCountries();
		this.genders = Gender.values();
		this.honorificTitles = HonorificTitle.values();
		this.civilStates = CivilState.values();
		this.phoneTypes = PhoneType.values();
		this.regions = service.getRegions();
		this.cities = service.getCities();
		list = doctorBean.getDoctors();
	}

	public String createNewDoctor() {
		return "addDoctor.faces";
	}

	public String createDoctor() {
		doctor = doctorBean.addDoctor(doctor, clinic);
		return "listDoctors.faces?faces-redirect=true";
	}

	public String showEditDoctor(int id) {
		Doctor d = doctorBean.getDoctorById(id);
		setSelectedDoctor(d);
		return "showEditDoctor.faces";
	}

	public String deleteDoctor(int id) {
		Doctor c = doctorBean.getDoctorById(id);
		doctorBean.deleteDoctor(c);
		return "listDoctors.faces?faces-redirect=true";
	}

	public String showDetailDoctor(int id) {
		Doctor d = doctorBean.getDoctorById(id);
		if (!d.getPhoneNumbers().isEmpty())
			setPhone((PhoneNumber) d.getPhoneNumbers().toArray()[0]);
//		if (!d.getAddresses().isEmpty())
//			setLocation(d.getAddresses().get(0));
		this.setSelectedDoctor(d);
		return "showDetailDoctor.faces";
	}

	public String editDoctor() {
		List<Location> addresses = new ArrayList<Location>();
		List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		phone.setOwner(selectedDoctor);
		phoneNumbers.add(phone);
		addresses.add(location);
		selectedDoctor.setAddresses(addresses);
		selectedDoctor.setPhoneNumbers(phoneNumbers);
		selectedDoctor.setClinic(clinic);
		doctorBean.editDoctor(selectedDoctor);
		return "listDoctors.faces?faces-redirect=true";
	}

	public String listDoctors() {
		return "listDoctors.faces?faces-redirect=true";
	}

	public DoctorBean getDoctorBean() {
		return doctorBean;
	}

	public void setDoctorBean(DoctorBean doctorBean) {
		this.doctorBean = doctorBean;
	}

	public List<Doctor> getList() {
		return list;
	}

	public void setList(List<Doctor> list) {
		this.list = list;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Doctor getSelectedDoctor() {
		return selectedDoctor;
	}

	public void setSelectedDoctor(Doctor selectedDoctor) {
		this.selectedDoctor = selectedDoctor;
	}

	public ServiceProvider getClinic() {
		return clinic;
	}

	public void setClinic(ServiceProvider clinic) {
		this.clinic = clinic;
	}

	public List<ServiceProvider> getClinics() {
		return clinics;
	}

	public void setClinics(List<ServiceProvider> clinics) {
		this.clinics = clinics;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	public PartnerAgencyBean getPartnerAgencyBean() {
		return partnerAgencyBean;
	}

	public void setPartnerAgencyBean(PartnerAgencyBean partnerAgencyBean) {
		this.partnerAgencyBean = partnerAgencyBean;
	}

	public Gender[] getGenders() {
		return genders;
	}

	public void setGenders(Gender[] genders) {
		this.genders = genders;
	}

	public HonorificTitle[] getHonorificTitles() {
		return honorificTitles;
	}

	public void setHonorificTitles(HonorificTitle[] honorificTitles) {
		this.honorificTitles = honorificTitles;
	}

	public CivilState[] getCivilStates() {
		return civilStates;
	}

	public void setCivilStates(CivilState[] civilStates) {
		this.civilStates = civilStates;
	}

	public PhoneType[] getPhoneTypes() {
		return phoneTypes;
	}

	public void setPhoneTypes(PhoneType[] phoneTypes) {
		this.phoneTypes = phoneTypes;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	
	

}
