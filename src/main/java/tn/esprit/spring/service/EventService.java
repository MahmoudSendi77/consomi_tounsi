package tn.esprit.spring.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.entities.Don;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.Jackpot;
import tn.esprit.spring.entities.User;

import tn.esprit.spring.repository.DonRepository;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class EventService{

	@Autowired
	EventRepository eventReprository;
	@Autowired
	UserRepository userReprository;
	@Autowired
	DonRepository donReprository;
	@Autowired
    private JavaMailSender javaMailSender;
	@Autowired
	UserRepository UserReprository;
	
	@Autowired
	UserService UserService;
	public String ajouterEvent (Event event){
		eventReprository.save(event);
		return "event sucess";
	}
	
	
	
	
	public Long ajouterEvente (Event event){
		eventReprository.save(event);
		return event.getId();
	}
	
	
	
	public void deleteEventById (Long eventId){
		
		Event EventId = eventReprository.findById(eventId).get();
		
		eventReprository.delete(EventId);
	}
	
	
	
	
	public long updateEvent(Event event) {
		if (eventReprository.findById(event.getId()).get() != null) {
			eventReprository.save(event);
			return event.getId();
		}
		return 0;
	}
	
	
//	
//	public void mettreAjourtitre_eventIdJPQL(String titre_event, long eventId) {
//		eventReprository.mettreAjourtitre_eventIdJPQL(titre_event, eventId);
//
//	}
	
	
	public void mettreAjourtitre_eventIdJPQL(String titre_event, long eventId) {
		Event event = eventReprository.findById(eventId).get();
		event.setTitre_event(titre_event);
		eventReprository.save(event);

	}
	
	
	
	
	

	public List<Event> upcomingEvents() {
		List<Event> list= eventReprository.upcomingEvents();
		return list;
	}
	

	
	
	public List<String>getAllTitleEventJPQL(){
		return eventReprository.TitleEvent();
		
	}
	
	
	
	
	public List<Event> getAllEvents(){
		return (List<Event>) eventReprository.findAll();
	}
	
	
	
	public 	List<Event> findEvent(String name) {
	List<Event> eventList = eventReprository.findEventsByName(name);
	System.out.println("Employee list: " + eventList);
	return eventList;
	}
	
	
	
	
	
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent eventId)
    {
       User user = (User) eventId.getAuthentication().getPrincipal();
        System.out.println("LOGIN name: "+user.getUsername());
    }
	

	

	
		public boolean hasDon(long userId){
			if(donReprository.getListUser().contains(userId))
				return true;
						return false;
		}
		
		
		
		//affecter paticipant qui a juste des don  a event 
		
	   
		public void affecterUserdonAevent( Long eventId,Long userId) {

			Event event = eventReprository.findById( eventId).get();
			
			User user = userReprository.findById( userId).get();
			List<User> users = new ArrayList<>();

			 if(hasDon(userId)){
			if (event.getUsers() == null) {						
				users.add(user);						
				event.setUsers(users);
				eventReprository.save(event);
			}
			
			else{
				if(!event.getUsers().contains(user)){// nzidou capaciter mta3 event 
					if(event.getUsers().size()<event.getNbr_vue()){
				users=event.getUsers();
				users.add(user);
				event.setUsers(users);
				eventReprository.save(event);}
					else System.out.println("nombre de place atteind");}
				else System.out.println("user already added ");
				
			}
			
			 }

			 else System.out.println("user have to have don");
}
		
		//}
		//	
		//afrfecter un user a un event
		
		
		
		public void affecterUserAevent( Long eventId,Long userId) {

					Event event = eventReprository.findById( eventId).get();
					
					User user = userReprository.findById( userId).get();
					List<User> users = new ArrayList<>();

			
					if (event.getUsers() == null) {						
						users.add(user);						
						event.setUsers(users);
						eventReprository.save(event);
					}
					
					else{
						if(!event.getUsers().contains(user)){//capaciter mta3 event 
							if(event.getUsers().size()<event.getNbr_vue()){
						users=event.getUsers();
						users.add(user);
						event.setUsers(users);
						eventReprository.save(event);}
							else System.out.println("nombre de place atteind");}
						else System.out.println("user already added ");
						
					}
			
	
		
		}
		
		
		public void affecterUser2Aevent( Long eventId,Long userId) {

			Event event = eventReprository.findById( eventId).get();
			
			User user = userReprository.findById( userId).get();
			List<User> users = new ArrayList<>();

	
			if (event.getUsers() == null) {						
				users.add(user);						
				event.setUsers(users);
				eventReprository.save(event);
			}
			
			else{
				if(!event.getUsers().contains(user)){//capaciter mta3 event 
					if(event.getUsers().size()<event.getNbr_vue()){
				users=event.getUsers();
				users.add(user);
				event.setUsers(users);
				eventReprository.save(event);}
					else System.out.println("nombre de place atteind");}
				else System.out.println("user already added ");
				
			}
	


}
		
		
		
		//get user Top 3
		
		public List<User> getTopThreeUserDon(){
			 List<List<Long>> l = donReprository.topThreeDon();//[[10000,2],[3000,25],[2000,29]]
			  List<User> lu = new ArrayList<>();
			 for(int i =0 ;i<l.size();i++){
				 lu.add(  userReprository.findById(l.get(i).get(1)).get());
			 }
			 return lu;
		}
		
		
		
	    //slect participant a event 
	public String  InviteParticipantForFree(Long currentUser,Long userToBeAdded,Long eventId) {//currentUser user couran , userToBeAdded user elli bech nzidouh gratuitement
		
		  List<List<Long>> l = donReprository.topThreeDon();//[[10000,2],[3000,25],[2000,29]]
		  List<Long> lu = new ArrayList<>();
			 for(int i =0 ;i<l.size();i++){
				 lu.add(l.get(i).get(1));
			 }
		
		if(  (lu.contains(currentUser)))  {
			affecterUserAevent(eventId,userToBeAdded);
			return "participant invited";
		
	}
		return " invitation not autorized";
		
		}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	 // List<Event> findAll();
	  //  List<Event> findLatest4();
	   // List<Event> findLatest5();
	  //  Event findByEventID(int eventID);
	
//	 public List<Event> findLatest(int num){
//	        List<Event> allEvents = (List<Event>) eventReprository.findAll();
//	        Collections.sort(allEvents, new Comparator<Event>(){
//	            @Override
//	           // public int compare(Event e1, Event e2){
//	             //   return e2.getDateStartEvent().compareTo(e1.getDateStartEvent());
//	            }
//	        });
//	        for(int i=allEvents.size()-1; i>=num; i--){
//	            allEvents.remove(i);
//	        }
//	        return allEvents;                
//	    }
//
//	@Override
//	public void deleteEventById(int eventId) {
//		// TODO Auto-generated method stub
//		
//	}
//	 
	 
}
