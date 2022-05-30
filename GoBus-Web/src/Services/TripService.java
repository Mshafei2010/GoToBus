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
import EJBs.Trip;
import EJBs.User;

@Stateless
@Path("/trip")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TripService {
	@PersistenceContext(unitName="GoBusWEB")
	 private EntityManager entityManager;
	
	@POST
	public String createTrip(Trip trip) {
		try {
			 String select = "SELECT s FROM Station s WHERE s.name=:Name";
			  Query query = entityManager.createQuery(select);
			  query.setParameter("Name", trip.getFrom_station());
			  Station fromStation =  (Station) query.getSingleResult();
			  trip.setFrom_station_fk(fromStation);
			  
			  String select2 = "SELECT s FROM Station s WHERE s.name=:Name";
			  Query query2 = entityManager.createQuery(select);
			  query2.setParameter("Name", trip.getTo_station());
			  Station toStation =  (Station) query2.getSingleResult();
			  trip.setTo_station_fk(toStation);
			  
			  entityManager.persist(trip);
			  return "Trip Created Successfully";
			  
		}catch (Exception e) {
			throw new WebApplicationException(Response
				      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
				      .type(MediaType.TEXT_PLAIN)
				      .entity(e.getMessage())
				      .build());
		}
	}
	
	@GET
	public List<Trip>  getTrips() {
		String select = "SELECT t FROM Trip t ";
		  Query query = entityManager.createQuery(select);
		  List<Trip> trips =  query.getResultList();
		  return trips;
	}
	
}
