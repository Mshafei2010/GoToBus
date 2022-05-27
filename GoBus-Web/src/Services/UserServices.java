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
import javax.ws.rs.core.MediaType;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;

import EJBs.User;

@Stateless
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserServices {
	
	@PersistenceContext(unitName="GoBusWEB")
	 private EntityManager entityManager;

	
	
	@POST
	@Path("/register")
	public String  register(User client) {
		try {
		  
		  entityManager.persist(client);
		  return "Client Added Successfuly : "+client.getusername();
		}
		catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}
	
	@GET
	@Path("/getUsers")
	public List<User> getUsers(){
		Query query=entityManager.createQuery("SELECT u from User u ");
		 List<User> persons = query.getResultList();
		 return persons;
	}
	@GET
	@Path("/get")
	public String get(){
		 return "Heloo";
	}

}
