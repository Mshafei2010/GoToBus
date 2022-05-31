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
	@POST
	@Path("/searchtrips")
	public List<Trip> searchTrips(Trip trip)
	{
		
		try {
			Query query = entityManager.createQuery("SELECT t FROM Trip t where t.departure_time >=?1 and t.arrival_time <=?2 and t.from_station LIKE ?3 and t.to_station LIKE ?4",Trip.class);
			query.setParameter(1, trip.getFrom_date());
			query.setParameter(2, trip.getTo_date());
			query.setParameter(3, trip.getFrom_station());
			query.setParameter(4, trip.getTo_station());
			return query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new WebApplicationException(Response
				      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
				      .type(MediaType.TEXT_PLAIN)
				      .entity(e.getMessage())
				      .build());
		}
	}
}

