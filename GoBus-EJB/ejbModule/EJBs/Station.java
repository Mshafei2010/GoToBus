package EJBs;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
@Stateless
@LocalBean
@Entity
@Table(name="Station")
public class Station implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int Id;
	@NotNull
	@Column(name="name")
	String name;
	@NotNull
	@Column(name="longitude")
	double longitude;
	@NotNull
	@DecimalMax("90.0")
	@Column(name="latitude")
	double latitude;

	@OneToMany(mappedBy = "from_station_fk",fetch = FetchType.LAZY)
	List<Trip>fromTrips;
	
	@OneToMany(mappedBy = "to_station_fk",fetch = FetchType.LAZY)
	List<Trip>toTrips;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	

}
