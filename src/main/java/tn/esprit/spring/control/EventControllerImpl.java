package tn.esprit.spring.control;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.Jackpot;

import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.EventService;


@Controller(value = "eventControllerImpl")
@ELBeanName(value = "eventControllerImpl")
//@Join(path = "/servlet/api/aymen/", to = "/jackpotpage.jsf")



public class EventControllerImpl {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SigninJsf signinJsf;
	private Long id;
	
	private String titre_event;
	private String description ;


	private String location;
	private float price;
	
	private boolean status;  // approved or not

	private int nbr_vue;
	
	private Date date_start_event;
	

	private Date date_end_event;

	 private String time;
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
	
	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}

	@Autowired
	EventRepository eventReprository;
	@Autowired
	EventService eventService;
	
	
	private String eventimage;
	private int Nbr_places;  
	private int Nbr_participants;
	private int Nbr_interssants;
	  private double lat;
	     
	    private double lng;
	
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
		//lat=tn.esprit.spring.service.AddMarkersView.lat1;
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}


	public double getLng() {
		//lng=tn.esprit.spring.service.AddMarkersView.lng1;
		return lng;
	}


	public void setLng(double lng) {
		this.lng = lng;
	}

	private Long eventIdToBeUpdated;
	
	public Long getEventIdToBeUpdated() {
		return eventIdToBeUpdated;
	}


	public void setEventIdToBeUpdated(Long eventIdToBeUpdated) {
		this.eventIdToBeUpdated = eventIdToBeUpdated;
	}


	public void ajouterEvent()
	{
		eventService.ajouterEvente(new Event(titre_event, description, location, price, date_start_event,
				date_end_event, eventimage, Nbr_places, Nbr_participants, Nbr_interssants,
				tn.esprit.spring.service.AddMarkersView.lat1,tn.esprit.spring.service.AddMarkersView.lng1,time));
		
	}
	
	public void updateEvent() {
		eventService.updateEvent(new Event(eventIdToBeUpdated,titre_event,description,location,price,date_start_event,date_end_event,eventimage,Nbr_places,Nbr_participants,Nbr_interssants,lat,lng,time));
		}

	public void deleteeventById(Long eventId) {
		eventService.deleteEventById(eventId);
		
	}
	


public void displayEvent(Event e) { 
		
		System.err.println("fffffflattt"+ e.getLat() +e.getLng());
		 this.setTitre_event(e.getTitre_event());
		this.setDescription(e.getDescription());

		this.setPrice(e.getPrice());
		this.setDate_start_event(e.getDate_start_event());
		this.setDate_end_event(e.getDate_end_event());
		this.setNbr_places(e.getNbr_places());
		this.setNbr_participants(e.getNbr_participants());
		this.setLng(e.getLng());
		this.setLat(e.getLat());
		this.setTime(e.getTime());
		this.setLocation(e.getLocation());
		this.setEventIdToBeUpdated(e.getId());

		 }
	

public void mapEvent( ) { 
	
	
	

}

//public List <Event> getallmapLat(){
//	
//	List <Event> m= eventReprository.findAll();
//	for(int i=0;i<m.size();i++)
//	{
//	m.get(i).getLat();
//	
//	
//	}
//	return m;
//	
//}
//public List <Event> getallmapLng(){
//	
//	List <Event> m= eventReprository.findAll();
//	for(int i=0;i<m.size();i++)
//	{
//	m.get(i).getLng();
//	
//	
//	}
//	return m;
//	
//}


	public List<Event> getevents(){
		System.out.println("jakpotslogin"); 
		
		List events =eventService.getAllEvents();
		
		return events;
	}

	private Float total;
	private int number;


	public Float getTotal() {
		return total;
	}


	public void setTotal(Float total) {
		this.total = total;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}
	
	
	public void updateTiket(float price) {
	
		Float z = (float) number;
		total = z * price;
	
	}
	
	
	
//	public String currentuser(){
//		String user = userRepository.findById(signinJsf.idusercurrent()).get().getUsername();
//		
//		return user;
//	}
}
