package tn.esprit.spring.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.repository.EventRepository;

@Named
@ViewScoped
public class MarkerSelectionView implements Serializable {
     
    private MapModel simpleModel;
  
    private Marker marker;
    @Autowired
	EventRepository eventReprository;
    
    @PostConstruct
    public void init() {
      
        
      
        	
        
        	
      
     
    }
      
    public MapModel getSimpleModel() {
    	
    	  simpleModel = new DefaultMapModel();
          
    	    
          
    	     
      	
      	List <Event> m= eventReprository.findAll();
      	for(int i=0;i<m.size();i++)
      	{
      
      	
      	  LatLng coord1 = new LatLng(m.get(i).getLat(), m.get(i).getLng());
      	  simpleModel.addOverlay(new Marker(coord1, m.get(i).getLocation()));
      	}
    	
        return simpleModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }
      
    public Marker getMarker() {
        return marker;
    }
}