package tn.welldone.ressources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import tn.welldone.model.Service;

@Path("/sevices")
public interface ServiceRemote {
	
	@PUT
    @Path( "add" )
    @Consumes( "application/json" )
	public Service addService(Service service);
	
	public String removeService(String id);
	
	public String updateService(Service service);
	
	public Service findService(String id);
	
	public List<Service> getAllService();

}
