package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.data.CityRepository;
import tn.welldone.data.CountryRepository;
import tn.welldone.data.FunctionRepository;
import tn.welldone.data.RegionRepository;
import tn.welldone.model.City;
import tn.welldone.model.Country;
import tn.welldone.model.Function;
import tn.welldone.model.Region;

@Stateless
@Local
@Named
public class DataService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	CountryRepository countryRepository;
	
	@Inject
	RegionRepository regionRepository;
	
	@Inject
	CityRepository cityRepository;
	
	@Inject
	FunctionRepository functionRepository;
	
	public List<Country> getCountries() {
		return countryRepository.getCountries();
	}
	
	public List<Region> getRegions() {
		return regionRepository.getRegions();
	}

	public List<City> getCities() {
		return cityRepository.getCities();
	}
	
	public List<City> getCities(String region_id) {
		return cityRepository.getCities(region_id);
	}
	
	public List<Region> getRegions(Country country) {
		return regionRepository.getRegions(country);
	}
	
	public List<Region> getRegions(String country_id) {
		return regionRepository.getRegions(country_id);
	}

	public List<Region> getDefaultRegions() {
		Country c = countryRepository.getCountryById(1);
		return regionRepository.getDefaultRegions(c);
	}
	
	public Country getCountryById(int id) {
		return countryRepository.getCountryById( id);
	}
	
	public Region getRegionById(int id) {
		return regionRepository.getRegionById( id);
	}
	
	public City getCityById(int id) {
		return cityRepository.getCityById( id);
	}

	public Country addCountry(Country country) {
		countryRepository.add(country);
		return country;
	}
	
	public Region addRegion(Region region) {
		regionRepository.add(region);
		return region;
	}
	
	public City addCity(City city) {
		cityRepository.add(city);
		return city;
	}
	
	public Country editCountry(Country country) {
		return countryRepository.edit(country);
		
	}

	public Region editRegion(Region region) {
		return regionRepository.edit(region);
	}
	
	public City editCity(City city) {
		return cityRepository.edit(city);
	}
	
	public Country deleteCountry(Country c) {
		countryRepository.delete(c);
		return c;	
	}

	public void deleteRegion(Region r) {
		regionRepository.delete(r);
		
	}

	public void deleteCity(City c) {
		cityRepository.delete(c);
		
	}

	public void addFunction(Function function) {
		functionRepository.add(function);
	}

	public Function getFunctionById(int id) {
		return functionRepository.getFunctionById(id);
	}

	public void deleteFunction(Function f) {
		functionRepository.delete(f);
	}

	public Function editFunction(Function function) {
		return functionRepository.edit(function);
	}

	public List<Function> getFunctions() {
		return functionRepository.getFunctions();
	}

}
