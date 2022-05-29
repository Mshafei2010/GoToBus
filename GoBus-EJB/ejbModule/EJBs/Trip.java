package EJBs;

import java.io.Serializable;
import java.sql.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Stateless
@LocalBean
@Entity
@Table(name="Trip")
public class Trip implements Serializable{
	
	@Column(name="from_station")
	String from_station;
	
	
	@Column(name="to_station")
	String to_station;
	
	@Column(name="departure_time")
	@Temporal(TemporalType.DATE)
	Date departure_time;
	
	@Column(name="arrival_time")
	@Temporal(TemporalType.DATE)
	Date arrival_time;
	
	@Column(name="available_seats")
	int available_seats;
	
	
	/*
	 *
	 */
	private static final long serialVersionUID = 1L;



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



	public Date getDeparture_time() {
		return departure_time;
	}



	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}



	public Date getArrival_time() {
		return arrival_time;
	}



	public void setArrival_time(Date arrival_time) {
		this.arrival_time = arrival_time;
	}



	public int getAvailable_seats() {
		return available_seats;
	}



	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}
	

}
