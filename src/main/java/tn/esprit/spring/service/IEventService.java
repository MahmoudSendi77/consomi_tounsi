package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Event;

public interface IEventService {
	public String ajouterEvent (Event event);
	public void deleteEventById (int EventId);
	public List<String>getAllTitleEventJPQL();
	public List<Event> getAllEvents();
}
