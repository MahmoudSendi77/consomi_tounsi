package tn.esprit.spring.control;

//import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.SlideEndEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;

@Controller(value = "sliderView")
@ELBeanName(value = "sliderView")
public class SliderView {
	
	@Autowired
	UserService UserService;
	@Autowired
	UserserviceInterface UserserviceInterface;
	@Autowired
	UserRepository UserRepository;
	@Autowired
	SigninJsf SigninJsf;
	
    private float ajaxnb;
    private int points ;
   private double sliderpoints;
   
  
    public double getSliderpoints() {
    	
    	
    	sliderpoints=  (double)	UserRepository.findById(SigninJsf.idusercurrent()).get().getAbonnementDetail().getPointsfideliteuser();  	
    	
    	
    	
	return sliderpoints;
}

public void setSliderpoints(double sliderpoints) {
	this.sliderpoints = sliderpoints;
}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public float getAjaxnb() {
    	
    	
    	
    	
		return ajaxnb;
	}

	public void setAjaxnb(float ajaxnb) {
		this.ajaxnb = ajaxnb;
	}

	private int number1;
    private float number2 = 0.2f;  
    private int number3;   
    private int number4;   
    private int number5;
    private int number6;  
    private int number7;
    private int number8 = 30;
    private int number9 = 80;
 
    public int getNumber1() {
        return number1;
    }
 
    public void setNumber1(int number1) {
        this.number1 = number1;
    }
 
    public float getNumber2() {
        return number2;
    }
 
    public void setNumber2(float number2) {
        this.number2 = number2;
    }
 
    public int getNumber3() {
        return number3;
    }
 
    public void setNumber3(int number3) {
        this.number3 = number3;
    }
 
    public int getNumber4() {
        return number4;
    }
 
    public void setNumber4(int number4) {
        this.number4 = number4;
    }
 
    public int getNumber5() {
        return number5;
    }
 
    public void setNumber5(int number5) {
        this.number5 = number5;
    }
 
    public int getNumber6() {
        return number6;
    }
 
    public void setNumber6(int number6) {
        this.number6 = number6;
    }
 
    public int getNumber7() {
        return number7;
    }
 
    public void setNumber7(int number7) {
        this.number7 = number7;
    }
 
    public int getNumber8() {
        return number8;
    }
 
    public void setNumber8(int number8) {
        this.number8 = number8;
    }
 
    public int getNumber9() {
        return number9;
    }
 
    public void setNumber9(int number9) {
        this.number9 = number9;
    }
 
    public void onInputChanged(ValueChangeEvent event) {
    	//a discuter
    	ajaxnb = UserService.convertirPionts((int) event.getNewValue());

    } 
     
    public void onSlideEnd(SlideEndEvent event) {
    	
    	
    	
    //	ajaxnb =(float)  event.getValue();
    	ajaxnb = UserService.convertirPionts((int) event.getValue());
    	
    	
    	points =(int) event.getValue() ;
    	
    	System.out.println(ajaxnb);
      
    } 
    
    public void convertpoint(){
    	System.err.println("c eeeeeeee");
    	
       	System.err.println("c bien" + ajaxnb +"iduser" +SigninJsf.idusercurrent() +"points :"+ points);
    	UserserviceInterface.convertirPointsuser(ajaxnb,SigninJsf.idusercurrent() ,points);
    	
    	//System.err.println("c iuoioyukyherdgwsd");
    	
    }
    
    
}