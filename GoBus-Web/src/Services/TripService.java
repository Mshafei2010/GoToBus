package Services;

import java.util.List;


import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EJBs.Station;
import EJBs.Trip;
import EJBs.User;
import EJBs.UserXTrip;

@RequestScoped
@Path("/trip")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TripService {
	@PersistenceContext(unitName="GoBusWEB")
	 private EntityManager entityManager;
	
	@Resource
	private UserTransaction ut;

	
	@POST
	public String createTrip(final Trip trip) throws IllegalStateException, SecurityException, SystemException {

		try {
			   ut.begin();
			    Station station=entityManager.find(Station.class,trip.getTo_station());
				trip.setTo_station_fk(station);
				
				Station station2=entityManager.find(Station.class,trip.getFrom_station());
				trip.setFrom_station_fk(station2);
			    entityManager.persist(trip);
			    
			   ut.commit();
			  return "Trip Created Successfully";
		}catch (Exception e) {
			 ut.rollback();
			throw new WebApplicationException(Response
				      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
				      .type(MediaType.TEXT_PLAIN)
				      .entity(e.getMessage())
				      .build());
		}
	}
	@POST
	@Path("/searchtrips")
	public List<Trip> searchTrips(Trip trip) throws IllegalStateException, SecurityException, SystemException
	{
		
		try {
			ut.begin();

			TypedQuery<Trip>query = entityManager.createQuery("SELECT t FROM Trip t where t.departure_time >=?1 and t.arrival_time <=?2 and t.from_station LIKE ?3 and t.to_station LIKE ?4",Trip.class);
			query.setParameter(1, trip.getFrom_date());
			query.setParameter(2, trip.getTo_date());
			query.setParameter(3,trip.getFrom_station());
			query.setParameter(4,  trip.getTo_station());
			List<Trip> trips=query.getResultList();
			ut.commit();
			return trips;
		} catch (Exception e) {
			ut.rollback();
			// TODO Auto-generated catch block
			throw new WebApplicationException(Response
				      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
				      .type(MediaType.TEXT_PLAIN)
				      .entity(e.getMessage())
				      .build());
		}
	}
	
	@POST
	@Path("/booktrip")
	public String bookTrip(UserXTrip usertrip) throws IllegalStateException, SecurityException, SystemException {
		try {
			ut.begin();
			User user= entityManager.find(User.class, usertrip.getUser_id());
			Trip trip=entityManager.find(Trip.class, usertrip.getTrip_id());
			//entityManager.persist(usertrip);
			if(trip.adduser(user)) {
				entityManager.merge(trip);
				entityManager.merge(user);
				user.addtrip(trip);
				ut.commit();
				return "Trip Booked Successfully";
			}
			else {
				ut.commit();
				return "No Available Seats";
			}
			
		} catch (Exception e) {
			ut.rollback();
			throw new WebApplicationException(Response
				      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
				      .type(MediaType.TEXT_PLAIN)
				      .entity(e.getMessage())
				      .build());
		}
		
	}
	
	@GET
	@Path("/viewtrips/{user_id}")
	public List<Trip> viewTrip(@PathParam("user_id") int user_id) throws IllegalStateException, SecurityException, SystemException {
		try {
			ut.begin();
			User user= entityManager.find(User.class,user_id );
			List<Trip>trips= user.UserTrips();
			ut.commit();
			return trips;
			
		} catch (Exception e) {
			ut.rollback();
			throw new WebApplicationException(Response
				      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
				      .type(MediaType.TEXT_PLAIN)
				      .entity(e.getMessage())
				      .build());
		}
	
		
	}
	
	
}

