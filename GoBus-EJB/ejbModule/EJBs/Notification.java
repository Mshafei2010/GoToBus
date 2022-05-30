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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
@Stateless
@LocalBean
@Entity
@Table(name="Notification")
public class Notification implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@NotNull
	@Column(name="message")
	String message;
	
	@NotNull
	@Column(name="notification_datetime")
	@Temporal(TemporalType.DATE)
	Date notification_datetime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getNotification_datetime() {
		return notification_datetime;
	}

	public void setNotification_datetime(Date notification_datetime) {
		this.notification_datetime = notification_datetime;
	}

	
}
