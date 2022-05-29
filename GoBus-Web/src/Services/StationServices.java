package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EJBs.Station;
import EJBs.User;

@Stateless
@Path("/station")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationServices {
	@PersistenceContext(unitName="GoBusWEB")
	 private EntityManager entityManager;
	
	@POST
	public String creatStation(Station station)
	{
		try {
			  
			  entityManager.persist(station);;
			  return "Station Added Successfuly : "+station.getName();
			}
			catch (Exception e) {
				// TODO: handle exception
				throw new WebApplicationException(Response
					      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
					      .type(MediaType.TEXT_PLAIN)
					      .entity(e.getMessage())
					      .build());
			}
	}
	@GET
	@Path("/{Id}")
	public Station getStation(@PathParam("Id") int Id){
		try {
			Station station = entityManager.find(Station.class, Id);
			return station;
		} catch (Exception e) {
			throw new WebApplicationException(Response
				      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
				      .type(MediaType.TEXT_PLAIN)
				      .entity(e.getMessage())
				      .build());
			
		}
		
	}
	
	
	
	
	

}
