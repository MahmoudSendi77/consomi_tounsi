package tn.esprit.spring.control;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;


@Controller(value = "UserDataa")
@ELBeanName(value = "UserDataa")
public class UserDataa implements Serializable {
   private static final long serialVersionUID = 1L;
   public String data = "1";
   

   public String getData() {
      return data;
   }

   public void setData(String data) {
      this.data = data;
   }

   public String showResult() {
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = 
         fc.getExternalContext().getRequestParameterMap();
      data =  params.get("username"); 
      return "/login/paramii.jsf";
   }
   
   
   
   public String showResult1() {
	      FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	         fc.getExternalContext().getRequestParameterMap();
	      data =  params.get("username"); 
	      return "/login/templatelogin.jsf";
	   }
   
   
}