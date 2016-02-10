package tn.welldone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import tn.welldone.model.City;
import tn.welldone.model.Country;
import tn.welldone.model.Location;
import tn.welldone.model.Patient;
import tn.welldone.model.Person.CivilState;
import tn.welldone.model.Person.Gender;
import tn.welldone.model.Person.HonorificTitle;
import tn.welldone.model.PhoneNumber;
import tn.welldone.model.PhoneNumber.PhoneType;
import tn.welldone.model.Region;
import tn.welldone.service.DataService;
import tn.welldone.service.PatientService;

@Named("PatientController")
@RequestScoped
public class PatientController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4464010471032067994L;

	@EJB
	private DataService service;

	@EJB
	private PatientService patientService;
	
	private Patient patient = new Patient();
	
	private Location location = new Location();
	
	private PhoneNumber phone = new PhoneNumber();

	private Patient selectedPatient= new Patient();

	private Country country = new Country();

	private Country selectedCountry = new Country();

	private Region region = new Region();

	private Region selectedRegion = new Region();

	private City city = new City();

	private City selectedCity = new City();

	private List<Patient> patients;

	private List<Country> countries;

	private List<Region> regions;

	private List<City> cities;
	
	private Gender[] genders;
	
	private HonorificTitle[] honorificTitles;
	
	private CivilState[] civilStates;
	
	private PhoneType[] phoneTypes;

	@javax.annotation.PostConstruct
	public void init() {
		this.patients = patientService.getPatients();
		this.countries = service.getCountries();
		this.genders = Gender.values();
		this.honorificTitles = HonorificTitle.values();
		this.civilStates = CivilState.values();
		this.phoneTypes = PhoneType.values();		
		this.regions = service.getRegions();
		this.cities = service.getCities();
	}
	
	public String createNewPatient() {
		return "addPatient.faces";
	}

	public String createPatient() {
		Collection<Location> addresses = new HashSet<Location>();
		Collection<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phone.setOwner(patient);
		phoneNumbers.add(phone);
		addresses.add(location);
		patient.setAddresses(addresses );
		patient.setPhoneNumbers(phoneNumbers);
		patient = patientService.addPatient(patient,addresses,phoneNumbers);
		return "listPatients.faces?faces-redirect=true";
	}

	public String showEditPatient(int id) {
		Patient p = patientService.getPatientById(id);
		if(!p.getPhoneNumbers().isEmpty())
			setPhone((PhoneNumber) p.getPhoneNumbers().toArray()[0]);
		if(!p.getAddresses().isEmpty())
			setLocation((Location) p.getAddresses().toArray()[0]);		
		this.setSelectedPatient(p);
		return "showEditPatient.faces";
	}

	public String deletePatient(int id) {
		Patient c = patientService.getPatientById(id);
		patientService.deletePatient(c);
		return "listPatients.faces?faces-redirect=true";
	}
	
	public String showDetailPatient(int id) {
		Patient p = patientService.getPatientById(id);
		if(!p.getPhoneNumbers().isEmpty())
			setPhone((PhoneNumber) p.getPhoneNumbers().toArray()[0]);
		if(!p.getAddresses().isEmpty())
			setLocation((Location) p.getAddresses().toArray()[0]);	
		this.setSelectedPatient(p);
		return "showDetailPatient.faces";
	}

	public String editPatient() {
		Collection<Location> addresses = new ArrayList<Location>();
		Collection<PhoneNumber> phoneNumbers =new ArrayList<PhoneNumber>();
		phone.setOwner(selectedPatient);
		phoneNumbers.add(phone);
		addresses.add(location);
		setSelectedPatient(patientService.editPatient(selectedPatient,addresses,phoneNumbers));
		return "listPatients.faces?faces-redirect=true";
	}

	public String listPatients() {
		return "listPatients.faces?faces-redirect=true";
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

	public DataService getService() {
		return service;
	}

	public void setService(DataService service) {
		this.service = service;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Country getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Country selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public Region getSelectedRegion() {
		return selectedRegion;
	}

	public void setSelectedRegion(Region selectedRegion) {
		this.selectedRegion = selectedRegion;
	}

	public City getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(City selectedCity) {
		this.selectedCity = selectedCity;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(Patient selectedPatient) {
		this.selectedPatient = selectedPatient;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	public PhoneType[] getPhoneTypes() {
		return phoneTypes;
	}

	public void setPhoneTypes(PhoneType[] phoneTypes) {
		this.phoneTypes = phoneTypes;
	}

}
