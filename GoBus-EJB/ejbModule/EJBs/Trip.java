package EJBs;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.jboss.resteasy.annotations.StringParameterUnmarshallerBinder;

@Stateless
@LocalBean
@Entity
@Table(name="Trip")
public class Trip implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="from_station")
	String from_station;
	
	@Column(name="to_station")
	String to_station;
	
	@NotNull
	@Column(name="departure_time")
	
	String departure_time;
	
	@NotNull
	@Column(name="arrival_time")
	String arrival_time;
	
	@NotNull
	@Min(0)
	@Column(name="available_seats")
	int available_seats;
	
	@Transient
	String from_date;
	@Transient
	String to_date;
	
	
	/*
	 *
	 */
	private static final long serialVersionUID = 1L;


	public String getFrom_date() {
		return from_date;
	}



	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}



	public String getTo_date() {
		return to_date;
	}



	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}






	public String getFrom_station() {
		return from_station;
	}



	public void setFrom_station(String from_station) {
		this.from_station = from_station;
	}



	public String getTo_station() {
		return to_station;
	}



	public void setTo_station(String to_station) {
		this.to_station = to_station;
	}



	public String getDeparture_time() {
		return departure_time;
	}



	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}



	public String getArrival_time() {
		return arrival_time;
	}



	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}



	public int getAvailable_seats() {
		return available_seats;
	}



	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}
	

}

