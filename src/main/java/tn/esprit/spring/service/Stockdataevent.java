package tn.esprit.spring.service;

import java.util.Date;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Event;

import tn.esprit.spring.repository.EventRepository;
@Controller(value = "Stockdataevent")
@ELBeanName(value = "Stockdataevent")
public class Stockdataevent {
	
	
	
	
	
	
	@Autowired 
	
	EventRepository eventReprository;
	private Long id;
	private Long idevent;
	private String titre_event;
	private String description ;



	private String location;
	private float price;
	
	private boolean status;  // approved or not

	private int nbr_vue;
	
	private Date date_start_event;
	

	private Date date_end_event;
	
	private String eventimage;
	
	 private String time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdevent() {
		
		idevent=eventReprository.findById(id).get().getId();
		return idevent;
	}

	public void setIdevent(Long idevent) {
		this.idevent = idevent;
	}

	public String getTitre_event() {
		titre_event=eventReprository.findById(id).get().getTitre_event();
		return titre_event;
	}

	public void setTitre_event(String titre_event) {
		this.titre_event = titre_event;
	}

	public String getDescription() {
		
		description=eventReprository.findById(id).get().getDescription();
		
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getLocation() {
		location=eventReprository.findById(id).get().getLocation();
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getPrice() {
		price=eventReprository.findById(id).get().getPrice();
		
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
		nbr_vue=eventReprository.findById(id).get().getNbr_vue();
		return nbr_vue;
	}

	public void setNbr_vue(int nbr_vue) {
		this.nbr_vue = nbr_vue;
	}

	public Date getDate_start_event() {
		date_start_event=eventReprository.findById(id).get().getDate_start_event();
		return date_start_event;
	}

	public void setDate_start_event(Date date_start_event) {
		this.date_start_event = date_start_event;
	}

	public Date getDate_end_event() {
		date_end_event=eventReprository.findById(id).get().getDate_end_event();
		return date_end_event;
	}

	public void setDate_end_event(Date date_end_event) {
		this.date_end_event = date_end_event;
	}

	
	
	
	public String getEventimage() {
		eventimage=eventReprository.findById(id).get().getEventimage();
		return eventimage;
	}

	public void setEventimage(String eventimage) {
		this.eventimage = eventimage;
	}
	
	
	
	
	public String getTime() {
		time=eventReprository.findById(id).get().getTime();
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String showdataevent(Long eventid){

		id=eventid;
		// elli mawjoou fi init ywalli mawjoud hna 
		
		
	
		
		return "/tiketevent.xhtml?faces-redirect=true"; 
		
		
	}
	
	
	

}
