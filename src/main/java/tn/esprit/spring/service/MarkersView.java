package tn.esprit.spring.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class MarkersView implements Serializable {
    
     
  
    @Autowired
	EventRepository eventReprository;
   
	    @Autowired
	    Stockdata stockdata;
	    @Autowired
	    Stockdataevent stockdataevent;
  
	    private MapModel simpleModel;
	    private final Long num =1L;
	    @PostConstruct
	    public void init() {
	       
	          
  
          
     


        	}
        
      
        	
        
        	
      
     
   
      
	    public MapModel getSimpleModel() {
	    	
	    	
	    	 simpleModel = new DefaultMapModel();
	        Event m= eventReprository.findById(stockdataevent.getIdevent()).get();
	     	 
	      
	    	//System.err.println("idEvent"+stockdataevent.getIdevent());
			  LatLng coord1 = new LatLng(m.getLat(), m.getLng());
			  
				//System.err.println("Lattitude"+m.getLat());
			  
	    	  simpleModel.addOverlay(new Marker(coord1));
	        return simpleModel;
	    }
	}