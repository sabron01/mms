package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import tn.welldone.helpers.Operation;
import tn.welldone.interceptors.AuthorizationSecurityInterceptor;
import tn.welldone.model.City;
import tn.welldone.model.Country;
import tn.welldone.model.Displacement;
import tn.welldone.model.Location;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Region;
import tn.welldone.model.ServiceProvider;
import tn.welldone.model.Tache;
import tn.welldone.model.Tache.TacheState;
import tn.welldone.security.Permission;
import tn.welldone.service.DataService;
import tn.welldone.service.DisplacementBean;
import tn.welldone.service.MedicalJourneyBean;

@Named("DisplacementController")
@ViewScoped
@RequestScoped
public class DisplacementController implements Serializable {

	private static final long serialVersionUID = 6579502554225500450L;

	@EJB
	private DisplacementBean displacementBean;
	
	@EJB
	private DataService service;
	
	@Inject
	TaskController taskController;

	@EJB
	private MedicalJourneyBean medicalJourneyBean;

	private Displacement displacement = new Displacement();

	private Displacement selectedDisplacement = new Displacement();
	
	private MedicalJourney medicalJourney = new MedicalJourney();

	private ServiceProvider agency = new ServiceProvider();
	
	private Location startLocation = new Location();
	
	private Location endLocation = new Location();
	
	private Float oldDisplacementAmount = 0f ;
	
	private List<ServiceProvider> displacementAgencies;
	
	private List<Country> countries;

	private List<Region> regions;

	private List<City> cities;
	
	private List<Displacement> list;

	@javax.annotation.PostConstruct
	public void init() {
		setList(displacementBean.getAllDisplacements());
		this.displacementAgencies = displacementBean.getDisplacementAgencies();
		this.setRegions(service.getRegions());
		this.setCities(service.getCities());
		this.setCountries(service.getCountries());
	}


	@Permission(resource=Displacement.class,value=Operation.ADD)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String createNewDisplacement() {
		return "/displacement/addDisplacement.faces?faces-redirect=true";
	}
	
	public String createNewTaskDisplacement(Tache task) {
		taskController.setTache(task);
		Displacement d = displacementBean.getDisplacementById(task.getId());
		setDisplacement(d);
		return "/displacement/addTaskDisplacement.faces";
	}
	
	public String createTaskDisplacement() {
		Float oldAmount = medicalJourney.getAmount();
		if (oldAmount == null)
			oldAmount = 0f;
		oldDisplacementAmount = displacement.getAmount();
		if (oldDisplacementAmount == null)
			oldDisplacementAmount = 0f;
		Float newAmount = oldAmount + oldDisplacementAmount;
		medicalJourney.setAmount(newAmount);
		displacement.setAction(taskController.getTache().getAction());
		displacement.setMedicalJourney(medicalJourney);
		displacement.setCodeTache(taskController.getTache().getCodeTache());
		displacement.setOwnerAgent(taskController.getTache().getOwnerAgent());
		selectedDisplacement.setStartPoint(startLocation);
		selectedDisplacement.setEndPoint(endLocation);
		displacement.setTacheState(TacheState.PLANNED);
		displacementBean.editDisplacement(displacement);
		taskController.setTache(new Tache());
		return "listDisplacements.faces?faces-redirect=true";
	}

	@Permission(resource=Displacement.class,value=Operation.ADD)
	@Interceptors( { AuthorizationSecurityInterceptor.class })
	public String createDisplacement() {
		Float oldAmount = medicalJourney.getAmount();
		if(oldAmount == null)
			oldAmount = 0f;
		if(displacement.getAmount() == null)
			displacement.setAmount(0f);
		Float newAmount = oldAmount+displacement.getAmount();
		medicalJourney.setAmount(newAmount);
		displacement.setStartPoint(startLocation);
		displacement.setEndPoint(endLocation);
		displacement = displacementBean.addDisplacement(displacement,medicalJourney,agency,startLocation,endLocation);
		return "listDisplacements.faces?faces-redirect=true";
	}

	public String showEditDisplacement(int id) {
		Displacement d = displacementBean.getDisplacementById(id);
		oldDisplacementAmount = d.getAmount();
		setAgency(d.getProvider());
		setMedicalJourney(d.getMedicalJourney());
		setStartLocation(d.getStartPoint());
		setEndLocation(d.getEndPoint());
		setSelectedDisplacement(d);
		return "showEditDisplacement.faces";
	}

	public String deleteDisplacement(int id) {
		Displacement d = displacementBean.getDisplacementById(id);
		displacementBean.deleteDisplacement(d);
		return "listDisplacements.faces?faces-redirect=true";
	}

	public String editDisplacement() {
		Float oldAmount = medicalJourney.getAmount();
		if(oldAmount == null)
			oldAmount = 0f;
		if(oldDisplacementAmount == null)
			oldDisplacementAmount = 0f;
		if(selectedDisplacement.getAmount() == null)
			selectedDisplacement.setAmount(0f);
		Float newAmount = oldAmount-oldDisplacementAmount+selectedDisplacement.getAmount();
		medicalJourney.setAmount(newAmount);
		selectedDisplacement.setStartPoint(startLocation);
		selectedDisplacement.setEndPoint(endLocation);
		selectedDisplacement.setMedicalJourney(medicalJourney);
		displacementBean.editDisplacement(selectedDisplacement);
		return "listDisplacements.faces?faces-redirect=true";
	}

	public String listDisplacements() {
		return "listDisplacements.faces?faces-redirect=true";
	}

	public DisplacementBean getDisplacementBean() {
		return displacementBean;
	}

	public void setDisplacementBean(DisplacementBean displacementBean) {
		this.displacementBean = displacementBean;
	}

	public List<Displacement> getList() {
		return list;
	}

	public void setList(List<Displacement> list) {
		this.list = list;
	}

	public Displacement getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Displacement displacement) {
		this.displacement = displacement;
	}

	public Displacement getSelectedDisplacement() {
		return selectedDisplacement;
	}

	public void setSelectedDisplacement(Displacement selectedDisplacement) {
		this.selectedDisplacement = selectedDisplacement;
	}
	
	public MedicalJourneyBean getMedicalJourneyBean() {
		return medicalJourneyBean;
	}

	public void setMedicalJourneyBean(MedicalJourneyBean medicalJourneyBean) {
		this.medicalJourneyBean = medicalJourneyBean;
	}

	public MedicalJourney getMedicalJourney() {
		return medicalJourney;
	}

	public void setMedicalJourney(MedicalJourney medicalJourney) {
		this.medicalJourney = medicalJourney;
	}

	public ServiceProvider getAgency() {
		return agency;
	}

	public void setAgency(ServiceProvider agency) {
		this.agency = agency;
	}

	public Location getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
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

	public Float getOldDisplacementAmount() {
		return oldDisplacementAmount;
	}

	public void setOldDisplacementAmount(Float oldDisplacementAmount) {
		this.oldDisplacementAmount = oldDisplacementAmount;
	}

	public List<ServiceProvider> getDisplacementAgencies() {
		return displacementAgencies;
	}

	public void setDisplacementAgencies(List<ServiceProvider> displacementAgencies) {
		this.displacementAgencies = displacementAgencies;
	}



}
