package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "Event")
//JsonIgnoreProperties({"Event"})
public class Event implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "titre_event")
	private String titre_event;
	private String description ;
	

	private String location;
	private float price;
	
	private boolean status;  // approved or not
	@Column(name = "nbr_vue")
	private int nbr_vue;
	@Temporal(TemporalType.DATE)
	@Column(name = "date_start_event")
	private Date date_start_event;
	@Temporal(TemporalType.DATE)
	@Column(name = "date_end_event")
	private Date date_end_event;
	private String eventimage;
	@Column(name = "nbr_places")
	private int Nbr_places;  
	@Column(name = "nbr_participants")
	private int Nbr_participants;
	@Column(name = "nbr_interssants")
	private int Nbr_interssants;
	  private double lat;
	     
	    private double lng;
	 private String time;
	
	    
	    
	    @ManyToMany(fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
	private List<User> users;
	
	
	
	
	    @ManyToMany(fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
	private List<Image> picture;

	    
	  
	    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre_event() {
		return titre_event;
	}

	public void setTitre_event(String titre_event) {
		this.titre_event = titre_event;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getNbr_vue() {
		return nbr_vue;
	}

	public void setNbr_vue(int nbr_vue) {
		this.nbr_vue = nbr_vue;
	}

	public Date getDate_start_event() {
		return date_start_event;
	}

	public void setDate_start_event(Date date_start_event) {
		this.date_start_event = date_start_event;
	}

	public Date getDate_end_event() {
		return date_end_event;
	}

	public void setDate_end_event(Date date_end_event) {
		this.date_end_event = date_end_event;
	}

	public String getEventimage() {
		return eventimage;
	}

	public void setEventimage(String eventimage) {
		this.eventimage = eventimage;
	}

	public int getNbr_places() {
		return Nbr_places;
	}

	public void setNbr_places(int nbr_places) {
		Nbr_places = nbr_places;
	}

	public int getNbr_participants() {
		return Nbr_participants;
	}

	public void setNbr_participants(int nbr_participants) {
		Nbr_participants = nbr_participants;
	}

	public int getNbr_interssants() {
		return Nbr_interssants;
	}

	public void setNbr_interssants(int nbr_interssants) {
		Nbr_interssants = nbr_interssants;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Image> getPicture() {
		return picture;
	}

	public void setPicture(List<Image> picture) {
		this.picture = picture;
	}

	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	

	

	public Event(Long id, String titre_event, String description, String location, float price, Date date_start_event,
			Date date_end_event, String eventimage, int nbr_places, int nbr_participants, int nbr_interssants,
			double lat, double lng, String time) {
		super();
		this.id = id;
		this.titre_event = titre_event;
		this.description = description;
		this.location = location;
		this.price = price;
		this.date_start_event = date_start_event;
		this.date_end_event = date_end_event;
		this.eventimage = eventimage;
		Nbr_places = nbr_places;
		Nbr_participants = nbr_participants;
		Nbr_interssants = nbr_interssants;
		this.lat = lat;
		this.lng = lng;
		this.time = time;
	}
	
	

	public Event(String titre_event, String description, String location, float price, Date date_start_event,
			Date date_end_event, String eventimage, int nbr_places, int nbr_participants, int nbr_interssants,
			double lat, double lng, String time) {
		super();
		this.titre_event = titre_event;
		this.description = description;
		this.location = location;
		this.price = price;
		this.date_start_event = date_start_event;
		this.date_end_event = date_end_event;
		this.eventimage = eventimage;
		Nbr_places = nbr_places;
		Nbr_participants = nbr_participants;
		Nbr_interssants = nbr_interssants;
		this.lat = lat;
		this.lng = lng;
		this.time = time;
	}

	public Event() {
		super();
	}
	
	
	
	
	
	
	
	
}