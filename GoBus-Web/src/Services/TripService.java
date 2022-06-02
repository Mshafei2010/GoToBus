package Services;


import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	
}

