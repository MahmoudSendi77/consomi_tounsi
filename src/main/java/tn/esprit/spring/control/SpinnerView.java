package tn.esprit.spring.control;

import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Named
@RequestScoped
@Scope(value = "session")
@Controller(value ="spinnerView")
@ELBeanName(value = "spinnerView")
public class SpinnerView {
     
  
    private int number3;
 
    private Float total;
    
    public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public int getNumber3() {
        return number3;
    }
 
    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public void updateCantidad(Float x) {
    	Float z=  (float) number3;
    	total = z* x;
    	}
    
  
    
    
    
}