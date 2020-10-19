package tn.esprit.spring.control;

import org.apache.catalina.User;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.JwtTokenProvider;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@Controller(value = "AbonnementFront")
@ELBeanName(value = "AbonnementFront")

public class AbonnementFront {

	// @Autowired
	// RestTemplate restTemplate;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService UserService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserserviceInterface UserserviceInterface;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	SigninJsf SigninJsf;

	
	
	private User userpopup;
	
	// private Datet

	public User getUserpopup() {
		return userpopup;
	}

	public void setUserpopup(User userpopup) {
		this.userpopup = userpopup;
	}

	public void affecterUserAbonnement(String type) {
		System.out.println(type + "iddddddddddddddddddddddddddddddddddddddddddddddd");

		String retour = UserService.affecterUserAbonnement(SigninJsf.idusercurrent(), type);
	
		
		
		FacesMessage facesMessage =

				new FacesMessage(retour);
		FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		

		System.out.println(type + "iddddddddddddddddddddddddddddddddddddddddddddddd");

	}

	public Long getTimeAbonnement() {

		Date ab = userRepository.findById(SigninJsf.idusercurrent()).get().getAbonnementDetail().getDeteabon();

		ab.setMinutes(ab.getMinutes()
				+ userRepository.findById(SigninJsf.idusercurrent()).get().getAbonnement().getValidite());
		System.out.println(ab + "hhhhhhhhhhhhhhh" + ab.getTime());

		return ab.getTime();
	}

	
	
	public boolean verificationUserHasOffres (){
		
		
		if (userRepository.findById(SigninJsf.idusercurrent()).get().getAbonnement().getType().equals("NORMAL"))
		
		
		return false;
		
		return true;
	}
	
	
	public void test(){
		
		
		System.out.println("zzazeazeazeassssssssssssssssssssssssss");
		
	}
	
	
	




	private List<tn.esprit.spring.entities.User> users ;

	  public List<tn.esprit.spring.entities.User> getUsers() {
		  
		  System.out.println("aaaaaaabc   " + abc) ;
		  users = userRepository.findByRoles_Id(abc);
		
		return users;
	}

	public void setUsers(List<tn.esprit.spring.entities.User> users) {
		this.users = users;
	}







	private String text;
	

	private Long abc ;
	  
	    public String getText() {
	        return text;
	    }
	    public void setText(String text) {
	        this.text = text;
	    }
	     
	    public void handleKeyEvent() {
	    	
	    	
	    	
	    	
	    	
	    	
	 //   users = userRepository.findByRoles_Id(Long.parseLong(text));
	    	System.out.println("nnnnnnnnnnnnnnnnnnnnnnjjjjjjjjjjjj " + text  );
	        text = text.toUpperCase();
	        
	        
	     abc = Long.parseLong(text) ;
	        System.out.println("passsssssssssssssssssssssssssssss"+ abc);
	        System.err.println("userssssssssss " + users);
	    }
	
	
	    
	    
	    public void turnoff(){
	    	
	    	UserService.run();
	    	
	    }
	    public void popupuser(String username)
	    {
	    	
	    	
	 System.out.println("sssssssssssssssssssssdddddddddddqqqqqqqqqqqqqqqq " + username);
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
