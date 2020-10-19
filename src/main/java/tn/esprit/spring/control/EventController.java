package tn.esprit.spring.control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Event;

import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.service.EventService;
import tn.esprit.spring.service.UserService;


@RestController
@RequestMapping("/api/aymen")
public class EventController {
	
	@Autowired
	private EventService eventService;
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/ajoutEvent")
	//@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public String ajouterEvent(@RequestBody Event event)
	{
		
		return eventService.ajouterEvent(event);
	}
	
	
	// URL : http://localhost:8081/SpringMVC/servlet/api/aymen/deleteEventById/2
    @DeleteMapping("/deleteEventById/{idEvent}") 
	@ResponseBody
	public void deleteEventById(@PathVariable("idEvent")Long EventId) {
    	eventService.deleteEventById(EventId);
    	
	}
    
    
 // URL : http://localhost:8081/SpringMVC/servlet/api/aymen/modifyTitreEvent/2
    @PutMapping(value = "/modifyTitreEvent/{id}/{newtitre}") 
	@ResponseBody
	public void mettreAjourtitre_eventIdJPQL(@PathVariable("newtitre") String titre_event, @PathVariable("id") int eventId) {
    	eventService.mettreAjourtitre_eventIdJPQL(titre_event, eventId);
		
    }
    
    
    
    
    // URL : http://localhost:8081/SpringMVC/servlet/api/aymen/getAllTitleEventJPQL
    @GetMapping( "/getAllTitleEventJPQL")
    @ResponseBody
	public List<String> getAllTitleEventJPQL() {
		
		return eventService.getAllTitleEventJPQL();
	}
    
    
    // URL : http://localhost:8081/SpringMVC/servlet/api/aymen/getAllTitleEventJPQL
    @GetMapping( "/getNameEventJPQL/{name}")
    @ResponseBody
	public List<Event> getNameEventJPQL(@PathVariable("name") String name) {
		
		return eventService.findEvent(name);
	}
    
    
 // URL : http://localhost:8081/SpringMVC/servlet/api/aymen/upcomingEvents
    @GetMapping("/upcomingEvents")
	public List<Event> upcomingEvents() {
		return eventService.upcomingEvents();
	}
    

    
    //affecterUserAevent 
    
    // URL : http://localhost:8081/SpringMVC/servlet/api/aymen/affecterUserAevent/1/1
    @PutMapping(value = "/affecterUserAevent/{eventId}//{userId}") 
  	@ResponseBody
    public void affecterUserAevent(@PathVariable("eventId")Long eventId,@PathVariable("userId")Long userId) {
    	eventService.affecterUser2Aevent(eventId,userId);

	}
    
    
  //  affecterUserdonAevent user must have don 
    
    
    // URL : http://localhost:8081/SpringMVC/servlet/api/aymen/affecterUserAevent/1/1
    @PutMapping(value = "/affecterUserdonAevent/{eventId}//{userId}") 
  	@ResponseBody
    public void affecterUserdonAevent(@PathVariable("eventId")Long eventId,@PathVariable("userId")Long userId) {
    	eventService.affecterUserAevent(eventId,userId);

	}
    
    
    
    // URL : http://localhost:8081/SpringMVC/servlet/api/aymen/InviteParticipantForFree/1/4/1
    @PutMapping(value = "/InviteParticipantForFree/{currentUser}/{userToBeAdded}/{eventId}") 
  	@ResponseBody
    public void InviteParticipantForFree(@PathVariable("currentUser") Long currentUser ,@PathVariable("userToBeAdded") Long userToBeAdded, @PathVariable("eventId") Long eventId) {
    	eventService.InviteParticipantForFree(currentUser, userToBeAdded, eventId);

	}
    
    

}
