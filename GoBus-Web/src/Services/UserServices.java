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
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EJBs.User;

@RequestScoped
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserServices {
	
	@PersistenceContext(unitName="GoBusWEB")
	 private EntityManager entityManager;

	@Resource
	private UserTransaction ut;
	
	@POST
	@Path("/register")
	public String  register(User client) throws IllegalStateException, SecurityException, SystemException {
		try {
		  ut.begin();
		  entityManager.persist(client);
		  ut.commit();
		  return "Client Added Successfuly : "+client.getusername();
		}
		catch (Exception e) {
			// TODO: handle exception
			ut.rollback();
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/login")
	public String  login(User client) throws IllegalStateException, SecurityException, SystemException {
  
		try {
			ut.begin();
		  String select = "SELECT u FROM User u WHERE u.username=:userName and u.password=:passWord";
		  Query query = entityManager.createQuery(select);
		  query.setParameter("userName", client.getusername());
		  query.setParameter("passWord", client.getPassword());
		  User person = (User) query.getSingleResult();
		  ut.commit();
		  return  "SuccessFully Logged In ";
		}
		catch (Exception e) {
			// TODO: handle exception
			ut.rollback();
			throw new WebApplicationException(Response
				      .status(javax.ws.rs.core.Response.Status.BAD_REQUEST)
				      .type(MediaType.TEXT_PLAIN)
				      .entity("Sorry Username or Password are not right")
				      .build());
		}
	}
	



}

