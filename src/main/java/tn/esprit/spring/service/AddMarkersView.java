package tn.esprit.spring.service;

import java.io.Serializable;
import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.control.EventControllerImpl;

@ManagedBean
public class AddMarkersView implements Serializable {
    
    private MapModel emptyModel;
     
    private String title;
     
    private double lat;
     
    private double lng;
 public static  double lat1;
 public static  double lng1;
    public static String titre1;
    @Autowired
    EventControllerImpl eventControllerImpl;
    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }
     
    public MapModel getEmptyModel() {
        return emptyModel;
    }
     
    public String getTitle() {
    	System.out.println("ay message= "+title);
    
        return title;
    }
 
    public void setTitle(String title) {
    	System.out.println("setmessage= "+title);
    	titre1=title;
        this.title = title;
    }
 
    public double getLat() {
    	System.out.println("ay message= "+lat);
    	
        return lat;
    }
 
    public void setLat(double lat) {
    	lat1=lat;
    	System.out.println("set message= "+lat);
        this.lat = lat;
    }
 
    public double getLng() {
    	System.out.println("ay message= "+lng);
        return lng;
    }
 
    public void setLng(double lng) {
    	lng1=lng;
    	System.out.println("set message= "+lng);
        this.lng = lng;
    }
     
    
    
    
    
    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
       
        emptyModel.addOverlay(marker);
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
       
        eventControllerImpl.setLat(lat);
        eventControllerImpl.setLng(lng);
        eventControllerImpl.setLocation(title);
      
    }
    public void marker(){
    this.setTitle(getTitle());
    this.setLat(getLat());
    this.setLng(getLng());
    }
}