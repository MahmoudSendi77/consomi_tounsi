package tn.esprit.spring.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import tn.esprit.spring.entities.Jackpot;
import tn.esprit.spring.entities.Typejackpot;

import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.JackpotRepository;
@Controller(value = "Stockdata")
@ELBeanName(value = "Stockdata")


public class Stockdata implements Serializable{
	@Autowired
	JackpotRepository jackpotrepository;
	private Long id ;
	public String  jackpottitre ;
	

	private Date  jackpotdatefin;
	private float  jackpotmaxValue;
	
	private String jackpotdescriptionjackpot;
	
	private Typejackpot jackpottype;
	private int jackpotnbvue;
	
	
	 public String data = "1";

	   public String getData() {
	      return data;
	   }

	   public void setData(String data) {
	      this.data = data;
	   }
	   

	   public Long getId() {
		 
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	private Long jackpotid;
	
    private String jackpotimage;
    
    
	public Long getJackpotid() {
		jackpotid =	jackpotrepository.findById(id).get().getId();
		return jackpotid;
	}

	public void setJackpotid(Long jackpotid) {
		this.jackpotid = jackpotid;
	}

	public String getJackpottitre() {
		jackpottitre =	jackpotrepository.findById(id).get().getTitre();
		
		return jackpottitre;
	}

	public void setJackpottitre(String jackpottitre) {
		this.jackpottitre = jackpottitre;
	}
	
	
	

	public String getJackpotimage() {
		
		jackpotimage= jackpotrepository.findById(id).get().getImage();
		return jackpotimage;
	}

	public void setJackpotimage(String jackpotimage) {
		this.jackpotimage = jackpotimage;
	}
	
	
	
	

	public Date getJackpotdatefin() {
		
		jackpotdatefin=jackpotrepository.findById(id).get().getDatefin();
		return jackpotdatefin;
	}

	public void setJackpotdatefin(Date jackpotdatefin) {
		this.jackpotdatefin = jackpotdatefin;
	}

	public float getJackpotmaxValue() {
		jackpotmaxValue=jackpotrepository.findById(id).get().getMaxValue();
		return jackpotmaxValue;
	}

	public void setJackpotmaxValue(float jackpotmaxValue) {
		this.jackpotmaxValue = jackpotmaxValue;
	}

	public String getJackpotdescriptionjackpot() {
		jackpotdescriptionjackpot=jackpotrepository.findById(id).get().getDescriptionjackpot();
		return jackpotdescriptionjackpot;
	}

	public void setJackpotdescriptionjackpot(String jackpotdescriptionjackpot) {
		this.jackpotdescriptionjackpot = jackpotdescriptionjackpot;
	}

	
	
	
	public Typejackpot getJackpottype() {
		
		jackpottype=jackpotrepository.findById(id).get().getTypeJackpot();
		return jackpottype;
	}

	public void setJackpottype(Typejackpot jackpottype) {
		this.jackpottype = jackpottype;
	}

	public int getJackpotnbvue() {
		
		jackpotnbvue=jackpotrepository.findById(id).get().getNbrvue();
		return jackpotnbvue;
	}

	public void setJackpotnbvue(int jackpotnbvue) {
		this.jackpotnbvue = jackpotnbvue;
	}

//	public String showResult() {
//		   System.out.println("jaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbb");
//	      FacesContext fc = FacesContext.getCurrentInstance();
//	      Map<String,String> params = 
//	         fc.getExternalContext().getRequestParameterMap();
//	      data =  params.get("jackpot"); 
//	      
//	      if(data!=null)
//	     id = Long.parseLong(data);
//	     System.out.println("id"+id);
//	      return "/pagedon.jsf";
//	   }
//	
	

	public String showdata(Long jackpotid){
		
		id=jackpotid;
		
		return "/pagedon.jsf?faces-redirect=true"; 
		
		
	}
	
	

	
}
