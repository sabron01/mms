package tn.welldone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import tn.welldone.model.ProviderType;
import tn.welldone.model.Region;
import tn.welldone.model.Service;
import tn.welldone.model.ServiceProvider;
import tn.welldone.service.DataService;
import tn.welldone.service.DoctorBean;
import tn.welldone.service.PartnerAgencyBean;
import tn.welldone.service.ProviderTypeBean;

@Named("PartnerAgencyController")
@ViewScoped
@RequestScoped
public class PartnerAgencyController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2305632068168497200L;

	@EJB
	private ProviderTypeBean providerTypeBean;

	@EJB
	private PartnerAgencyBean partnerAgencyBean;
	
	@EJB
	private DoctorBean doctorBean;
	
	@EJB
	private DataService service;

	private List<ServiceProvider> hotels;

	private ServiceProvider hotel = new ServiceProvider();

	private ServiceProvider selectedHotel = new ServiceProvider();

	private List<ServiceProvider> clinics;

	private ServiceProvider clinic = new ServiceProvider();

	private ServiceProvider selectedClinic = new ServiceProvider();

	private List<ServiceProvider> realEstateAgencies;

	private ServiceProvider realEstateAgency = new ServiceProvider();

	private ServiceProvider selectedRealEstateAgency = new ServiceProvider();

	private List<ServiceProvider> rentAcarAgencies;

	private ServiceProvider rentAcarAgency = new ServiceProvider();

	private ServiceProvider selectedRentAcarAgency = new ServiceProvider();

	private List<ServiceProvider> ambulancesAgencies;

	private ServiceProvider ambulanceAgency = new ServiceProvider();

	private ServiceProvider selectedAmbulanceAgency = new ServiceProvider();

	private List<ServiceProvider> pharmacies;

	private ServiceProvider pharmacy = new ServiceProvider();

	private ServiceProvider selectedPharmacy = new ServiceProvider();

	private List<Doctor> doctors;

	private Doctor doctor = new Doctor();

	private Doctor selectedDoctor = new Doctor();
	
	private Collection<ServiceProvider> travelAgencies;
	
	private ServiceProvider travelAgency = new ServiceProvider();
	
	private ServiceProvider selectedTravelAgency = new ServiceProvider();
	
	private Collection<ServiceProvider> airports;
	
	private ServiceProvider airport = new ServiceProvider();
	
	private ServiceProvider selectedAirport = new ServiceProvider();
	
	private Gender[] genders;

	private HonorificTitle[] honorificTitles;

	private CivilState[] civilStates;

	private PhoneType[] phoneTypes;
	
	private PhoneNumber phone = new PhoneNumber();

	private Location location = new Location();
	
	private List<Country> countries;

	private List<Region> regions;

	private List<City> cities;

	private ProviderType typeProvider = new ProviderType();
	
	private List<Service> services = new ArrayList<Service>();

	@javax.annotation.PostConstruct
	public void init() {
		this.hotels = partnerAgencyBean.getHotels();
		this.clinics = partnerAgencyBean.getClinics();
		this.realEstateAgencies = partnerAgencyBean.getRealEstateAgencies();
		this.rentAcarAgencies = partnerAgencyBean.getRentAcarAgencies();
		this.ambulancesAgencies = partnerAgencyBean.getAmbulancesAgencies();
		this.pharmacies = partnerAgencyBean.getPharmacies();
		this.doctors = partnerAgencyBean.getDoctors();
		this.travelAgencies = partnerAgencyBean.getTravelAgencies();
		this.airports = partnerAgencyBean.getAirports();
		this.countries = service.getCountries();
		this.genders = Gender.values();
		this.honorificTitles = HonorificTitle.values();
		this.civilStates = CivilState.values();
		this.phoneTypes = PhoneType.values();
		this.regions = service.getRegions();
		this.cities = service.getCities();
	}
	
	// ************les Aéroports************* 
	// ***********************************
	
	public String createNewAirport() {
		return "/partners/addAirport.faces?redirect=true";
	}

	public String createAirport() {
		setTypeProvider(providerTypeBean.getProviderTypeById(8));
		airport = partnerAgencyBean.addServiceProvider(airport, location, typeProvider,services);
		return "listAirports.faces?faces-redirect=true";
	}

	public String showEditAirport(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedAirport(s);
		return "showEditAirport.faces";
	}

	public String showDetailAirport(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedAirport(s);
		return "showDetailAirport.faces";
	}

	public String deleteAirport(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		partnerAgencyBean.deletePartner(s);
		return "listAirports.faces?faces-redirect=true";
	}

	public String editAirport() {
		selectedAirport.setAddress(location);
		setTypeProvider(providerTypeBean.getProviderTypeById(8));
		selectedAirport.setTypeProvider(typeProvider);
		partnerAgencyBean.editPartner(selectedAirport);
		return "listAirports.faces?faces-redirect=true";
	}

	public String listAirports() {
		return "/partners/listAirports.faces?faces-redirect=true";
	}
	
	
	
	
	// ************les Agences des voyages************* 
	// ***********************************
	
	public String createNewTravelAgency() {
		return "/partners/addTravelAgency.faces?redirect=true";
	}

	public String createTravelAgency() {
		setTypeProvider(providerTypeBean.getProviderTypeById(7));
		travelAgency = partnerAgencyBean.addServiceProvider(travelAgency, location, typeProvider,services);
		return "listTravelAgencies.faces?faces-redirect=true";
	}

	public String showEditTravelAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedTravelAgency(s);
		return "showEditTravelAgency.faces";
	}

	public String showDetailTravelAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedTravelAgency(s);
		return "showDetailTravelAgency.faces";
	}

	public String deleteTravelAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		partnerAgencyBean.deletePartner(s);
		return "listTravelAgencies.faces?faces-redirect=true";
	}

	public String editTravelAgency() {
		selectedTravelAgency.setAddress(location);
		partnerAgencyBean.editPartner(selectedTravelAgency);
		return "listTravelAgencies.faces?faces-redirect=true";
	}

	public String listTravelAgencies() {
		return "/partners/listTravelAgencies.faces?faces-redirect=true";
	}

	// ************les Hôtels************* 
	// ***********************************
	
	public String createNewHotel() {
		return "addHotel.faces";
	}

	public String createHotel() {
		//hotel.setAddress(location);
		setTypeProvider(providerTypeBean.getProviderTypeById(1));
		hotel = partnerAgencyBean.addServiceProvider(hotel, location, typeProvider,services);
		return "listHotels.faces?faces-redirect=true";
	}

	public String showEditHotel(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedHotel(s);
		return "showEditHotel.faces";
	}

	public String showDetailHotel(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedHotel(s);
		return "showDetailHotel.faces";
	}

	public String deleteHotel(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		partnerAgencyBean.deletePartner(s);
		return "listHotels.faces?faces-redirect=true";
	}

	public String editHotel() {
		selectedHotel.setAddress(location);
		partnerAgencyBean.editPartner(selectedHotel);
		return "listHotels.faces?faces-redirect=true";
	}

	public String listHotels() {
		return "listHotels.faces?faces-redirect=true";
	}
	
	// ***********les Cliniques*********** 
	// ***********************************

	public String createNewClinic() {
		return "addClinic.faces";
	}

	public String createClinic() {
		clinic.setAddress(location);
		clinic = partnerAgencyBean.addServiceProvider(
				clinic, typeProvider);
		return "listClinics.faces?faces-redirect=true";
	}

	public String showEditClinic(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedClinic(s);
		return "showEditClinic.faces";
	}

	public String showDetailClinic(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedClinic(s);
		return "showDetailClinic.faces";
	}

	public String deleteClinic(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		partnerAgencyBean.deletePartner(s);
		return "listClinics.faces?faces-redirect=true";
	}

	public String editClinic() {
		selectedClinic.setAddress(location);
		partnerAgencyBean.editPartner(selectedClinic);
		return "listClinics.faces?faces-redirect=true";
	}

	public String listClinics() {
		return "listClinics.faces?faces-redirect=true";
	}

	// *******Agences Immobilieres******** 
	// ***********************************

	public String createNewRealEstateAgency() {
		return "addRealEstateAgency.faces";
	}

	public String createRealEstateAgency() {
		realEstateAgency.setAddress(location);
		realEstateAgency = partnerAgencyBean.addServiceProvider(
				realEstateAgency, typeProvider);
		return "listRealEstateAgencies.faces?faces-redirect=true";
	}

	public String showEditRealEstateAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedRealEstateAgency(s);
		return "showEditRealEstateAgency.faces";
	}

	public String showDetailRealEstateAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedRealEstateAgency(s);
		return "showDetailRealEstateAgency.faces";
	}

	public String deleteRealEstateAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		partnerAgencyBean.deletePartner(s);
		return "listRealEstateAgencies.faces?faces-redirect=true";
	}

	public String editRealEstateAgency() {
		selectedHotel.setAddress(location);
		partnerAgencyBean.editPartner(selectedHotel);
		return "listRealEstateAgencies.faces?faces-redirect=true";
	}

	public String listRealEstateAgencies() {
		return "listRealEstateAgencies.faces?faces-redirect=true";
	}

	// ****Rent A car Agency***********
	// ********************************

	public String createNewRentAcarAgency() {
		return "addAgenceVoiture.faces";
	}

	public String createRentAcarAgency() {
		rentAcarAgency.setAddress(location);
		rentAcarAgency = partnerAgencyBean.addServiceProvider(
				rentAcarAgency, typeProvider);
		return "listRentAcarAgencies.faces?faces-redirect=true";
	}

	public String showEditRentAcarAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedRentAcarAgency(s);
		return "showEditRentAcarAgency.faces";
	}

	public String showDetailRentAcarAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedRentAcarAgency(s);
		return "showDetailRentAcarAgency.faces";
	}

	public String deleteRentAcarAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		partnerAgencyBean.deletePartner(s);
		return "listRentAcarAgencies.faces?faces-redirect=true";
	}

	public String editRentAcarAgency() {
		selectedHotel.setAddress(location);
		partnerAgencyBean.editPartner(selectedHotel);
		return "listRentAcarAgencies.faces?faces-redirect=true";
	}

	public String listRentAcarAgencies() {
		return "listRentAcarAgencies.faces?faces-redirect=true";
	}
	
	// *******Les Agences d'Ambulances******
	// *************************************

	public String createNewAmbulanceAgency() {
		return "addAmbulanceAgency.faces";
	}

	public String createAmbulanceAgency() {
		ambulanceAgency.setAddress(location);
		ambulanceAgency = partnerAgencyBean.addServiceProvider(
				ambulanceAgency, typeProvider);
		return "listAmbulanceAgencies.faces?faces-redirect=true";
	}

	public String showEditAmbulanceAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedAmbulanceAgency(s);
		return "showEditAmbulanceAgency.faces";
	}

	public String showDetailAmbulanceAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedAmbulanceAgency(s);
		return "showDetailAmbulanceAgency.faces";
	}

	public String deleteAmbulanceAgency(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		partnerAgencyBean.deletePartner(s);
		return "listAmbulanceAgencies.faces?faces-redirect=true";
	}

	public String editAmbulanceAgency() {
		selectedAmbulanceAgency.setAddress(location);
		partnerAgencyBean.editPartner(selectedAmbulanceAgency);
		return "listAmbulanceAgencies.faces?faces-redirect=true";
	}

	public String listAmbulanceAgencies() {
		return "listAmbulanceAgencies.faces?faces-redirect=true";
	}


	// ************les pharmacies***********
	// *************************************

	public String createNewPharmacie() {
		return "addPharmacy.faces";
	}

	public String createPharmacy() {
		pharmacy.setAddress(location);
		pharmacy = partnerAgencyBean.addServiceProvider(
				pharmacy, typeProvider);
		return "listPharmacies.faces?faces-redirect=true";
	}

	public String showEditPharmacy(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedPharmacy(s);
		return "showEditPharmacy.faces";
	}

	public String showDetailPharmacy(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		setLocation(s.getAddress());
		setSelectedPharmacy(s);
		return "showDetailPharmacy.faces";
	}

	public String deletePharmacy(int id) {
		ServiceProvider s = partnerAgencyBean.getPartnerById(id);
		partnerAgencyBean.deletePartner(s);
		return "listPharmacies.faces?faces-redirect=true";
	}

	public String editPharmacy() {
		selectedPharmacy.setAddress(location);
		partnerAgencyBean.editPartner(selectedPharmacy);
		return "listPharmacies.faces?faces-redirect=true";
	}

	public String listPharmacies() {
		return "listPharmacies.faces?faces-redirect=true";
	}

	// *********les Docteurs***************
	// *************************************

	public String createNewDoctor() {
		return "addDoctor.faces";
	}

	public String createDoctor() {
		doctor = partnerAgencyBean.addServiceProvider(doctor, clinic);
		return "listDoctors.faces?faces-redirect=true";
	}

	public String showEditDoctor(int id) {
		Doctor d = doctorBean.getDoctorById(id);
		setSelectedDoctor(d);
		return "showEditDoctor.faces";
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

	public String deleteDoctor(int id) {
		Doctor d = doctorBean.getDoctorById(id);
		setSelectedDoctor(d);
		return "listDoctors.faces?faces-redirect=true";
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

	public List<ServiceProvider> getHotels() {
		return hotels;
	}

	public void setHotels(List<ServiceProvider> hotels) {
		this.hotels = hotels;
	}

	public List<ServiceProvider> getClinics() {
		return clinics;
	}

	public void setClinics(List<ServiceProvider> clinics) {
		this.clinics = clinics;
	}

	public PartnerAgencyBean getPartnerAgencyBean() {
		return partnerAgencyBean;
	}

	public void setPartnerAgencyBean(PartnerAgencyBean partnerAgencyBean) {
		this.partnerAgencyBean = partnerAgencyBean;
	}

	public ServiceProvider getHotel() {
		return hotel;
	}

	public void setHotel(ServiceProvider hotel) {
		this.hotel = hotel;
	}

	public ServiceProvider getSelectedHotel() {
		return selectedHotel;
	}

	public void setSelectedHotel(ServiceProvider selectedHotel) {
		this.selectedHotel = selectedHotel;
	}

	public ServiceProvider getClinic() {
		return clinic;
	}

	public void setClinic(ServiceProvider clinic) {
		this.clinic = clinic;
	}

	public ServiceProvider getSelectedClinic() {
		return selectedClinic;
	}

	public void setSelectedClinic(ServiceProvider selectedClinic) {
		this.selectedClinic = selectedClinic;
	}

	public List<ServiceProvider> getRealEstateAgencies() {
		return realEstateAgencies;
	}

	public void setRealEstateAgencies(List<ServiceProvider> realEstateAgencies) {
		this.realEstateAgencies = realEstateAgencies;
	}

	public ServiceProvider getRealEstateAgency() {
		return realEstateAgency;
	}

	public void setRealEstateAgency(ServiceProvider realEstateAgency) {
		this.realEstateAgency = realEstateAgency;
	}

	public ServiceProvider getSelectedRealEstateAgency() {
		return selectedRealEstateAgency;
	}

	public void setSelectedRealEstateAgency(
			ServiceProvider selectedRealEstateAgency) {
		this.selectedRealEstateAgency = selectedRealEstateAgency;
	}

	public List<ServiceProvider> getRentAcarAgencies() {
		return rentAcarAgencies;
	}

	public void setRentAcarAgencies(List<ServiceProvider> rentAcarAgencies) {
		this.rentAcarAgencies = rentAcarAgencies;
	}

	public ServiceProvider getRentAcarAgency() {
		return rentAcarAgency;
	}

	public void setRentAcarAgency(ServiceProvider rentAcarAgency) {
		this.rentAcarAgency = rentAcarAgency;
	}

	public ServiceProvider getSelectedRentAcarAgency() {
		return selectedRentAcarAgency;
	}

	public void setSelectedRentAcarAgency(ServiceProvider selectedRentAcarAgency) {
		this.selectedRentAcarAgency = selectedRentAcarAgency;
	}

	public List<ServiceProvider> getAmbulancesAgencies() {
		return ambulancesAgencies;
	}

	public void setAmbulancesAgencies(List<ServiceProvider> ambulancesAgencies) {
		this.ambulancesAgencies = ambulancesAgencies;
	}

	public ServiceProvider getAmbulanceAgency() {
		return ambulanceAgency;
	}

	public void setAmbulanceAgency(ServiceProvider ambulanceAgency) {
		this.ambulanceAgency = ambulanceAgency;
	}

	public ServiceProvider getSelectedAmbulanceAgency() {
		return selectedAmbulanceAgency;
	}

	public void setSelectedAmbulanceAgency(
			ServiceProvider selectedAmbulanceAgency) {
		this.selectedAmbulanceAgency = selectedAmbulanceAgency;
	}

	public List<ServiceProvider> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<ServiceProvider> pharmacies) {
		this.pharmacies = pharmacies;
	}

	public ServiceProvider getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(ServiceProvider pharmacy) {
		this.pharmacy = pharmacy;
	}

	public ServiceProvider getSelectedPharmacy() {
		return selectedPharmacy;
	}

	public void setSelectedPharmacy(ServiceProvider selectedPharmacy) {
		this.selectedPharmacy = selectedPharmacy;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ProviderType getTypeProvider() {
		return typeProvider;
	}

	public void setTypeProvider(ProviderType typeProvider) {
		this.typeProvider = typeProvider;
	}
	
	public Doctor getSelectedDoctor() {
		return selectedDoctor;
	}

	public void setSelectedDoctor(Doctor selectedDoctor) {
		this.selectedDoctor = selectedDoctor;
	}

	public DoctorBean getDoctorBean() {
		return doctorBean;
	}

	public void setDoctorBean(DoctorBean doctorBean) {
		this.doctorBean = doctorBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ProviderTypeBean getProviderTypeBean() {
		return providerTypeBean;
	}

	public void setProviderTypeBean(ProviderTypeBean providerTypeBean) {
		this.providerTypeBean = providerTypeBean;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
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

	public Collection<ServiceProvider> getTravelAgencies() {
		return travelAgencies;
	}

	public void setTravelAgencies(Collection<ServiceProvider> travelAgencies) {
		this.travelAgencies = travelAgencies;
	}

	public Collection<ServiceProvider> getAirports() {
		return airports;
	}

	public void setAirports(Collection<ServiceProvider> airports) {
		this.airports = airports;
	}

	public ServiceProvider getTravelAgency() {
		return travelAgency;
	}

	public void setTravelAgency(ServiceProvider travelAgency) {
		this.travelAgency = travelAgency;
	}

	public ServiceProvider getSelectedTravelAgency() {
		return selectedTravelAgency;
	}

	public void setSelectedTravelAgency(ServiceProvider selectedTravelAgency) {
		this.selectedTravelAgency = selectedTravelAgency;
	}

	public ServiceProvider getAirport() {
		return airport;
	}

	public void setAirport(ServiceProvider airport) {
		this.airport = airport;
	}

	public ServiceProvider getSelectedAirport() {
		return selectedAirport;
	}

	public void setSelectedAirport(ServiceProvider selectedAirport) {
		this.selectedAirport = selectedAirport;
	}


}
