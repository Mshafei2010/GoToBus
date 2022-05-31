package EJBs;


import java.io.Serializable;
import java.lang.String;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Entity implementation class for Entity: User
 *
 */
@LocalBean
@Stateless
@Entity
@Table(name="USER")
public class User implements Serializable{

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@NotNull
	@Column (name="username")
	String username;
	@NotNull
	@Column (name="password")
	String password;
	
	@Column (name="full_name")
	String full_name;
	
	@Column (name="role")
	String role;
	private static final long serialVersionUID = 1L;
	
	public User() {
		super();
	}
 
	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}   
	public String getusername() {
		return this.username;
	}
	
	public void setusername(String username) {
		this.username = username;
	} 
	 
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String Password) {
		this.password = Password;
	}   
	public String getFull_name() {
		return this.full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	} 
	public String getRole() {
		return this.role;
	}

	public void setRole(String Role) {
		this.role = Role;
	}
   
}

