package Services;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

@RequestScoped
@Path("/station")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationServices {
	@PersistenceContext(unitName="GoBusWEB")
	 private EntityManager entityManager;
	@Resource
	private UserTransaction ut;

	
	@POST
	public String creatStation(Station station) throws IllegalStateException, SecurityException, SystemException
	{
		try {
			  ut.begin();
			  entityManager.persist(station);
			  ut.commit();
			  return "Station Added Successfuly : "+station.getName();
			}
			catch (Exception e) {
				// TODO: handle exception
				ut.rollback();
				throw new WebApplicationException(Response
					      .status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR)
					      .type(MediaType.TEXT_PLAIN)
					      .entity(e.getMessage())
					      .build());
			}
	}
	@GET
	@Path("/{Id}")
	public Station getStation(@PathParam("Id") String Id) throws IllegalStateException, SecurityException, SystemException{
		try {
			ut.begin();
			Station station = entityManager.find(Station.class, Id);
			ut.commit();
			return station;
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

