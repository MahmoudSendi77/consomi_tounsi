package tn.esprit.spring.control;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AbonnementRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.UserserviceInterface;

@Controller(value = "Profile")
@ELBeanName(value = "Profile")

public class Profile {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Autowired
	UserserviceInterface UserserviceInterface;
	@Autowired
	SigninJsf SigninJsf;
	
	@Autowired
	AbonnementRepository AbonnementRepository;
	
	private String password;
	private String confirmepassword;
	private String oldPassword;
	private String usernameconfirmation;
	public String getUsernameconfirmation() {
		
		System.out.println(usernameconfirmation+"zzzzzzzzzzzzzzzzzzzzzqsdqsdqsdqsdqs");
		return usernameconfirmation;
	}
	public void setUsernameconfirmation(String usernameconfirmation) {
		this.usernameconfirmation = usernameconfirmation;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getConfirmepassword() {
		return confirmepassword;
	}
	public void setConfirmepassword(String confirmepassword) {
		this.confirmepassword = confirmepassword;
	}

	private String Username ;
private String abonnementName;

private float solde;
private int mespoints;

private float  soldefinale;


public float getSoldefinale() {
	return soldefinale;
}
public void setSoldefinale(float soldefinale) {
	this.soldefinale = soldefinale;
}

private File filess ;


public File getFiless() {
	return filess;
}
public void setFiless(File filess) {
	this.filess = filess;
}
public void upploadd(){
	
	
	System.err.println("eededededededededede"  + filess);
	
}



public int getMespoints() {
	
	
	mespoints=
	        userRepository.findById(SigninJsf.idusercurrent())	.get().getAbonnementDetail().getPointsfideliteuser();
	return mespoints;
}

public void setMespoints(int mespoints) {
	this.mespoints = mespoints;
}

public float getSolde() {
	
	solde=
	        userRepository.findById(SigninJsf.idusercurrent())	.get().getAbonnementDetail().getSolde();
	return solde;
}

public void setSolde(float solde) {
	this.solde = solde;
}

	public String getAbonnementName() {
		abonnementName =	userRepository.findById(SigninJsf.idusercurrent()).get().getAbonnement().getType();
	return abonnementName;
}




public void setAbonnementName(String abonnementName) {
	this.abonnementName = abonnementName;
}




	public String getUsername() {
		
		
		if(SigninJsf.nameuser()==false)
		{
		
			Username =		userRepository.findById(SigninJsf.idusercurrent()).get().getUsername()   ;
		
		}
		return Username;
	}




	public void setUsername(String username) {
		Username = username;
	}
	
	
	
	
	public String getrandomparrainage() {

		User user = new User();
		user = userRepository.findById(SigninJsf.idusercurrent()).get();
		
		String genarate = "http://localhost:8081/mycode/" + user.getReporting().getCodeparrinage();

	

		return genarate;

	}
	
	
	public String redirecToSom(){
		System.out.println("ja lil redirection");
		
        return "/login/editprofile.xhtml?faces-redirect=true";
	}

	
	
	public String imageUser(){
		
User user = userRepository.findById(SigninJsf.idusercurrent()).get()	;	
		

return user.getProfileImage();
		
	}
	
	
	public String emailUser(){
		
		User user = userRepository.findById(SigninJsf.idusercurrent()).get()	;	
				

		return user.getEmail();
				
			}
	
	
	
	public String nameUser(){
		
		User user = userRepository.findById(SigninJsf.idusercurrent()).get()	;	
				

		return user.getName();
				
			}
	

	public String usernameUser(){
		
		User user = userRepository.findById(SigninJsf.idusercurrent()).get()	;	
				

		return user.getUsername();
				
			}
	
	
public String statusUser(){
		
		User user = userRepository.findById(SigninJsf.idusercurrent()).get()	;	
				

		return user.getAbonnement().getType();
				
			}
	
	
	public boolean imageuser(){
		
		
		

		
		User user = userRepository.findById(SigninJsf.idusercurrent()).get()	;
		if (user.getProfileImage()== null){
			
			return false;
		}
		return true;
		
	}
	
	
	public String changePass(){
		
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+password +"old password"   );
		
		
	
	
	
	if( password.equals(confirmepassword)) 
	{
		
		User user = userRepository.findById(SigninJsf.idusercurrent()).get()	;
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	
		return "/login/templatelogin.xhtml?faces-redirect=true";}
	else
	{
		FacesMessage facesMessage = new FacesMessage("Password change failed: please check your password and confirmation egain.");
FacesContext.getCurrentInstance().addMessage("formId:btt-calculer", facesMessage);
return null ;
	}
	
	}
	
public String gologin(){
	
	
	
return "/login/templatelogin.xhtml?faces-redirect=true";
	
	
	
	
}




public String toprofile(){
	
	
	return "/login/editprofile.xhtml?faces-redirect=true";
	
}

	
}
