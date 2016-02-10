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
import tn.welldone.model.Department;
import tn.welldone.model.Employee;
import tn.welldone.model.Function;
import tn.welldone.model.Location;
import tn.welldone.model.Person.CivilState;
import tn.welldone.model.Person.Gender;
import tn.welldone.model.Person.HonorificTitle;
import tn.welldone.model.PhoneNumber;
import tn.welldone.model.PhoneNumber.PhoneType;
import tn.welldone.model.Region;
import tn.welldone.model.Service;
import tn.welldone.service.DataService;
import tn.welldone.service.EmployeeService;

@Named("EmployeeController")
@RequestScoped
public class EmployeeController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5672227254140127464L;

	@EJB
	private DataService service;

	@EJB
	private EmployeeService employeeService;

	private Employee employee = new Employee();

	private Employee selectedEmployee = new Employee();

	private Department department = new Department();

	private Department managedDepartment = new Department();

	private Employee manager = new Employee();

	private Location location = new Location();

	private PhoneNumber phone = new PhoneNumber();

	private Country country = new Country();

	private Country selectedCountry = new Country();

	private Region region = new Region();

	private Region selectedRegion = new Region();

	private City city = new City();

	private City selectedCity = new City();

	private List<Employee> employees;

	private List<Employee> managers;

	private List<Country> countries;

	private List<Region> regions;

	private List<City> cities;

	private Gender[] genders;

	private HonorificTitle[] honorificTitles;

	private List<Function> functions;

	private CivilState[] civilStates;

	private PhoneType[] phoneTypes;

	private Service[] selectedServices;

	@javax.annotation.PostConstruct
	public void init() {
		this.employees = employeeService.getEmployees();
		this.countries = service.getCountries();
		this.genders = Gender.values();
		this.honorificTitles = HonorificTitle.values();
		this.civilStates = CivilState.values();
		this.phoneTypes = PhoneType.values();
		this.regions = service.getRegions();
		this.cities = service.getCities();
		this.managers = employeeService.getManagers();
		this.setFunctions(service.getFunctions());
	}

	public String createNewEmployee() {
		return "addEmployee.faces";
	}

	public String createEmployee() {
		Collection<Location> addresses = new HashSet<Location>();
		Collection<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phone.setOwner(employee);
		phoneNumbers.add(phone);
		addresses.add(location);
		employee.setAddresses(addresses);
		employee.setPhoneNumbers(phoneNumbers);
		employee = employeeService.addEmployee(employee, addresses,
				phoneNumbers, department, manager, managedDepartment);
		return "listEmployees.faces?faces-redirect=true";
	}

	public String showEditEmployee(int id) {
		Employee p = employeeService.getEmployeeById(id);
		if (!p.getPhoneNumbers().isEmpty())
			setPhone((PhoneNumber) p.getPhoneNumbers().toArray()[0]);
		if (!p.getAddresses().isEmpty())
			setLocation((Location) p.getAddresses().toArray()[0]);
		this.setSelectedEmployee(p);
		return "showEditEmployee.faces";
	}

	public String showDetailEmployee(int id) {
		Employee p = employeeService.getEmployeeById(id);
		if (!p.getPhoneNumbers().isEmpty())
			setPhone((PhoneNumber) p.getPhoneNumbers().toArray()[0]);
		if (!p.getAddresses().isEmpty())
			setLocation((Location) p.getAddresses().toArray()[0]);
		this.setSelectedEmployee(p);
		return "showDetailEmployee.faces";
	}

	public String showEmployeeActivities(int id) {
		Employee p = employeeService.getEmployeeById(id);
		if (!p.getPhoneNumbers().isEmpty())
			setPhone((PhoneNumber) p.getPhoneNumbers().toArray()[0]);
		if (!p.getAddresses().isEmpty())
			setLocation((Location) p.getAddresses().toArray()[0]);
		this.setSelectedEmployee(p);		
		System.out.println("NB activities :"+p.getActivities().size());	
		return "showEmployeeActivities.faces";

	}

	public String setEmployeeActivities() {
		Employee p = employeeService.getEmployeeById(selectedEmployee.getId());
		Collection<Service> checkedServices = new ArrayList<Service>();
		for(Service e : selectedEmployee.getActivities()){
			checkedServices.add(e);
		}
		System.out.println("NB activities :"+selectedEmployee.getActivities().size());		
		p.setActivities(checkedServices);
		System.out.println("NB activities new :"+p.getActivities().size());
		employeeService.editEmployee(p);
		return "listEmployees.faces?faces-redirect=true";
	}

	public String deleteEmployee(int id) {
		Employee c = employeeService.getEmployeeById(id);
		employeeService.deleteEmployee(c);
		return "listEmployees.faces?faces-redirect=true";
	}

	public String editEmployee() {
		Collection<Location> addresses = new ArrayList<Location>();
		Collection<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		phone.setOwner(selectedEmployee);
		phoneNumbers.add(phone);
		addresses.add(location);
		selectedEmployee.setManagedDepartment(managedDepartment);
		selectedEmployee.setDepartment(department);
		setSelectedEmployee(employeeService
				.editEmployee(selectedEmployee, addresses, phoneNumbers,
						department, manager, managedDepartment));
		return "listEmployees.faces?faces-redirect=true";
	}

	public String listEmployees() {
		return "listEmployees.faces?faces-redirect=true";
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getManagers() {
		return managers;
	}

	public void setManagers(List<Employee> managers) {
		this.managers = managers;
	}

	public Department getManagedDepartment() {
		return managedDepartment;
	}

	public void setManagedDepartment(Department managedDepartment) {
		this.managedDepartment = managedDepartment;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public Service[] getSelectedServices() {
		return selectedServices;
	}

	public void setSelectedServices(Service[] selectedServices) {
		this.selectedServices = selectedServices;
	}

}
