package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({  "users", "products"})
public class ProductNotif implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String title;	
	private String message;
	@Temporal(TemporalType.DATE)
	private Date date;

	private boolean seen;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<User> users =new ArrayList<>();
	
	public ProductNotif(String title, String message, Date date, boolean seen) {
		super();
		this.title = title;
		this.message = message;
		this.date = date;
		this.seen = seen;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProductNotif(String title, String message, Date date, boolean seen, List<User> users) {
		super();
		this.title = title;
		this.message = message;
		this.date = date;
		this.seen = seen;
		this.users = users;
	}

	public ProductNotif(Long id, String title, String message, Date date, boolean seen) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.date = date;
		this.seen = seen;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProductNotif(String message, Date date, boolean seen, List<User> users) {
		super();
		this.message = message;
		this.date = date;
		this.seen = seen;
		this.users = users;
	}

	public ProductNotif(Long id, String message, Date date, boolean seen, List<User> users) {
		super();
		this.id = id;
		this.message = message;
		this.date = date;
		this.seen = seen;
		this.users = users;
	}

	public ProductNotif() {
		super();
	}

	@Override
	public String toString() {
		return "ProductNotif [id=" + id + ", message=" + message + ", date=" + date + ", seen=" + seen + "]";
	}

	//

}
