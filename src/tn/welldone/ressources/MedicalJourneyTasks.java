package tn.welldone.ressources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import tn.welldone.data.MedicalJourneyRepository;
import tn.welldone.model.MedicalJourney;
import tn.welldone.service.Event;

@Path("/tasks")
@RequestScoped
public class MedicalJourneyTasks {

	@Inject
	MedicalJourneyRepository medicalJourneyRepository;

	@Produces( "application/json" )
	@GET
	@Path("/list")
	public Collection<Event> getAvailableMedicalJourneys(){
		Collection<MedicalJourney> medicalJourneys = medicalJourneyRepository.getAvailableMedicalJourneys();
		Collection<Event> events = new ArrayList<Event>();
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		for( MedicalJourney m : medicalJourneys ){
			Event e = new Event();
			e.setTitle("Séjour : "+m.getIdentifier());
			e.setStart(formatter.format(m.getStartDate().getTime()));
			e.setEnd(formatter.format(m.getCloseDate().getTime()));
			e.setBackgroundColor("#f39c12");
			e.setBorderColor("f39c12");
			events.add(e);
		}
		return events ;
	}
	
	@GET
	public String sayHello(){
		return "Bonjour";
	}

}
