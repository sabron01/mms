package tn.welldone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import tn.welldone.model.City;
import tn.welldone.model.Country;
import tn.welldone.model.Function;
import tn.welldone.model.ProviderType;
import tn.welldone.model.Region;
import tn.welldone.model.Service;
import tn.welldone.service.ContractBean;
import tn.welldone.service.DataService;
import tn.welldone.service.ProviderTypeBean;
import tn.welldone.service.ServiceBean;

@Named("DataParameterController")
@RequestScoped
@ViewScoped
public class DataParameterController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4464010471032067994L;

	@EJB
	private DataService service;

	@EJB
	private ContractBean contractBean;

	@EJB
	private ProviderTypeBean providerTypeBean;

	@EJB
	private ServiceBean serviceBean;

	private HtmlSelectOneMenu htmlCountrySelect;

	private HtmlSelectOneMenu htmlStartCountrySelect;

	private HtmlSelectOneMenu htmlRegionSelect;

	private HtmlSelectOneMenu htmlStartRegionSelect;

	private HtmlSelectOneMenu htmlConvensionSelect;

	private Country country = new Country();

	private Country selectedCountry = new Country();

	private Region region = new Region();

	private Region selectedRegion = new Region();

	private City city = new City();

	private City selectedCity = new City();
	
	private Function function = new Function();
	
	private Function selectedFunction = new Function();

	private List<Country> countries;

	private List<Service> services;

	private List<ProviderType> providerTypes;

	private List<Region> regions = new ArrayList<Region>();

	private List<City> cities = new ArrayList<City>();
	
	private List<Function> functions = new ArrayList<Function>();

	@javax.annotation.PostConstruct
	public void init() {
		this.countries = service.getCountries();
		this.regions = service.getRegions();
		this.cities = service.getCities();
		this.services = serviceBean.getAllServicess();
		this.functions = service.getFunctions();
		this.providerTypes = providerTypeBean.getAllProviderTypes();
	}
	
	public String createFunction() {
		service.addFunction(function);
		return "function.faces?faces-redirect=true";
	}


	public String createCountry() {
		country = service.addCountry(country);
		return "country.faces?faces-redirect=true";
	}

	public String createRegion() {
		region.setCountry(country);
		region = service.addRegion(region);
		return "region.faces?faces-redirect=true";
	}

	public String createCity() {
		city.setRegion(region);
		city = service.addCity(city);
		return "city.faces?faces-redirect=true";
	}

	public String showEditFunction(int id) {
		Function c = service.getFunctionById(id);
		this.setSelectedFunction(c);
		return "showEditFunction.faces";
	}
	
	public String showEditCountry(int id) {
		Country c = service.getCountryById(id);
		this.setSelectedCountry(c);
		return "showEditCountry.faces";
	}

	public String showEditRegion(int id) {
		Region r = service.getRegionById(id);
		this.setSelectedRegion(r);
		return "showEditRegion.faces";
	}

	public void changeListServices(AjaxBehaviorEvent event) {
		htmlConvensionSelect = ((HtmlSelectOneMenu) event.getSource());
		int contract_id = Integer.parseInt((String) htmlConvensionSelect
				.getSubmittedValue());
		services = (List<Service>) contractBean
				.getServicesByContract(contract_id);
		this.setServices(services);
	}

	public String showEditCity(int id) {
		City c = service.getCityById(id);
		this.setSelectedCity(c);
		return "showEditCity.faces";
	}

	public void changeCountry(AjaxBehaviorEvent event) {
		htmlCountrySelect = ((HtmlSelectOneMenu) event.getSource());
		regions = service
				.getRegions("" + htmlCountrySelect.getSubmittedValue());
		this.setRegions(regions);
	}

	public void changeStartCountry(AjaxBehaviorEvent event) {
		htmlCountrySelect = ((HtmlSelectOneMenu) event.getSource());
		regions = service
				.getRegions("" + htmlCountrySelect.getSubmittedValue());
		this.setRegions(regions);
	}

	public void changeRegion(AjaxBehaviorEvent event) {
		htmlRegionSelect = ((HtmlSelectOneMenu) event.getSource());
		cities = service.getCities("" + htmlRegionSelect.getSubmittedValue());
		this.setCities(cities);
	}

	public void changeStartRegion(AjaxBehaviorEvent event) {
		htmlRegionSelect = ((HtmlSelectOneMenu) event.getSource());
		cities = service.getCities("" + htmlRegionSelect.getSubmittedValue());
		this.setCities(cities);
	}

	public String deleteCountry(int id) {
		Country c = service.getCountryById(id);
		service.deleteCountry(c);
		return "country.faces?faces-redirect=true";
	}
	
	public String deleteFunction(int id) {
		Function f = service.getFunctionById(id);
		service.deleteFunction(f);
		return "function.faces?faces-redirect=true";
	}

	public String deleteRegion(int id) {
		Region r = service.getRegionById(id);
		service.deleteRegion(r);
		return "region.faces?faces-redirect=true";
	}

	public String deleteCity(int id) {
		City c = service.getCityById(id);
		service.deleteCity(c);
		return "city.faces?faces-redirect=true";
	}

	public String editCountry() {
		setSelectedCountry(service.editCountry(selectedCountry));
		return "country.faces?faces-redirect=true";
	}
	
	public String editFunction() {
		setSelectedFunction(service.editFunction(selectedFunction));
		return "function.faces?faces-redirect=true";
	}

	public String editRegion() {
		service.editRegion(selectedRegion);
		return "region.faces?faces-redirect=true";
	}

	public String editCity() {
		service.editCity(selectedCity);
		return "city.faces?faces-redirect=true";
	}

	public String listCountries() {
		return "country.faces?faces-redirect=true";
	}
	
	public String listFunctions() {
		return "function.faces?faces-redirect=true";
	}

	public String listRegions() {
		return "region.faces?faces-redirect=true";
	}

	public String listCities() {
		return "city.faces?faces-redirect=true";
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

	public HtmlSelectOneMenu getHtmlCountrySelect() {
		return htmlCountrySelect;
	}

	public void setHtmlCountrySelect(HtmlSelectOneMenu htmlCountrySelect) {
		this.htmlCountrySelect = htmlCountrySelect;
	}

	public HtmlSelectOneMenu getHtmlRegionSelect() {
		return htmlRegionSelect;
	}

	public void setHtmlRegionSelect(HtmlSelectOneMenu htmlRegionSelect) {
		this.htmlRegionSelect = htmlRegionSelect;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<ProviderType> getProviderTypes() {
		return providerTypes;
	}

	public void setProviderTypes(List<ProviderType> providerTypes) {
		this.providerTypes = providerTypes;
	}

	public ProviderTypeBean getProviderTypeBean() {
		return providerTypeBean;
	}

	public void setProviderTypeBean(ProviderTypeBean providerTypeBean) {
		this.providerTypeBean = providerTypeBean;
	}

	public ServiceBean getServiceBean() {
		return serviceBean;
	}

	public void setServiceBean(ServiceBean serviceBean) {
		this.serviceBean = serviceBean;
	}

	public HtmlSelectOneMenu getHtmlStartCountrySelect() {
		return htmlStartCountrySelect;
	}

	public void setHtmlStartCountrySelect(
			HtmlSelectOneMenu htmlStartCountrySelect) {
		this.htmlStartCountrySelect = htmlStartCountrySelect;
	}

	public HtmlSelectOneMenu getHtmlStartRegionSelect() {
		return htmlStartRegionSelect;
	}

	public void setHtmlStartRegionSelect(HtmlSelectOneMenu htmlStartRegionSelect) {
		this.htmlStartRegionSelect = htmlStartRegionSelect;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public Function getSelectedFunction() {
		return selectedFunction;
	}

	public void setSelectedFunction(Function selectedFunction) {
		this.selectedFunction = selectedFunction;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
	
	

}
