package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Event;



import java.lang.String;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
	
	
	List<Event> findByPrice(float price);
	

	  @Query("SELECT titre_event FROM Event")
	    public List<String> TitleEvent();
	  
	  
	  
	  
	
	  @Modifying
	    @Transactional
	    @Query("UPDATE Event e SET e.titre_event=:titre_event1 where e.id=:eventId")
	    public void mettreAjourtitre_eventIdJPQL(@Param("titre_event1")String titre, @Param("eventId")int eventId);
	  
	  @Query("SELECT e FROM Event e where e.titre_event=:name")
	  public List<Event>  findEventsByName(@Param("name") String EventName);
	  
	  @Query("SELECT ev FROM Event ev where ev.date_start_event > current_timestamp()")
		List<Event> upcomingEvents();
	  
	
	  
	  
	   //public List<String> findByEventCategory(String eventCategory);
	  //  public List<String> findByOrganizerID(Long organizerID);
	   // public List<String> findByStatus(boolean status);
	   // public Event findByEventId(Long IdEvent );
	  //  public List<Event> findByLocationContaining(String location);
	    //public List<Event> findByEventNameContaining(String location);
	  //  public List<Event> findByEventNameContainingOrLocationContaining(String eventName, String location);
	  
	  
}
