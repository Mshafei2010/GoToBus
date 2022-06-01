package Services;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EJBs.Station;
import EJBs.Trip;

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
			Station station=entityManager.find(Station.class,trip.getTo_station());
			Station station2=entityManager.find(Station.class,trip.getFrom_station());
			TypedQuery<Trip>query = entityManager.createQuery("SELECT t FROM Trip t where t.departure_time >=?1 and t.arrival_time <=?2 and t.from_station_fk LIKE ?3 and t.to_station_fk LIKE ?4",Trip.class);
			query.setParameter(1, trip.getFrom_date());
			query.setParameter(2, trip.getTo_date());
			query.setParameter(3,station2);
			query.setParameter(4,  station);
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
	/*
	@POST
	@Path("/booktrip")
	public String bookTrip(UsersXTrips usertrip) throws IllegalStateException, SecurityException, SystemException {
		try {
			ut.begin();
			User user= entityManager.find(User.class, usertrip.getUser_id());
			Trip trip=entityManager.find(Trip.class, usertrip.getTrip_id());
			user.getTrips().add(trip);
			trip.getUsers().add(user);
			trip.setAvailable_seats(trip.getAvailable_seats()-1);
			entityManager.refresh(trip);
			entityManager.refresh(user);
			ut.commit();
			return "Trip Booked Successfully";
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
			Query query = entityManager.createQuery("SELECT tr FROM Trip tr Where tr.id IN(SELECT t.trip_id FROM UserXTrip t where t.user_id =?1) ");
			query.setParameter(1, user_id);
			List<Trip>trips= query.getResultList();
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
	*/
	
}

