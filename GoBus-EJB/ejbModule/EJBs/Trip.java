package EJBs;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Stateless
@LocalBean
@Entity
@Table(name="Trip")
public class Trip implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@ManyToOne
	@JoinColumn(name="from_station_fk")
	Station from_station_fk;
	
	@ManyToOne
	@JoinColumn(name="to_station_fk")
	Station to_station_fk;
	
	@Column(name="from_station")
	String from_station;
	
	
	@Column(name="to_station")
	String to_station;
	
	@Column(name="departure_time")
	String departure_time;
	
	@Column(name="arrival_time")
	String arrival_time;
	
	@Column(name="available_seats")
	int available_seats;
	
	
	/*
	 *
	 */
	private static final long serialVersionUID = 1L;


	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Station getFrom_station_fk() {
		return from_station_fk;
	}



	public void setFrom_station_fk(Station from_station_fk) {
		this.from_station_fk = from_station_fk;
	}



	public Station getTo_station_fk() {
		return to_station_fk;
	}



	public void setTo_station_fk(Station to_station_fk) {
		this.to_station_fk = to_station_fk;
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

