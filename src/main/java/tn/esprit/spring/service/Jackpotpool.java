package tn.esprit.spring.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Jackpot;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;


@Controller(value = "Jackpotpool")
@ELBeanName(value = "Jackpotpool")
public class Jackpotpool implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	UserRepository userRepository;
	
	private User userpopup;

	public User getUserpopup() {
		return userpopup;
	}

	public void setUserpopup(User userpopup) {
		this.userpopup = userpopup;
	}

private Jackpot j;

	
	public Jackpot getJ() {
	return j;
}

public void setJ(Jackpot j) {
	this.j = j;
}

	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		
		
		
		
		
		return userRepository.findAll();
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


//	   Set<String> s = new HashSet<String>(); 
//	    s.add("Geeks"); 
//	    s.add("for"); 
//	  
//	    List<String> aList = new ArrayList<String>(); 
//	    aList.addAll(s); 

	

private Jackpot idjackpo;

	public Jackpot getIdjackpo() {
	return idjackpo;
}

public void setIdjackpo(Jackpot idjackpo) {
	this.idjackpo = idjackpo;
}


private String type; 
	public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

	public void onSelect(Jackpot j, String typeOfSelection, String indexes) {
		
		
		this.type=j.getTitre();
		
		
		System.out.println("hhouwa jak : "+j);
		idjackpo=j;
		System.out.println("onSelect sssssssssssssssssssssssssss "+indexes);
		
		
		
	
	}

	public void onDeselect(Jackpot j,String typeOfSelection, String indexes) {
		
		
		System.out.println("hhouwa jak : "+j);
		
		
		System.out.println("onDeselect ddddddddddddddddddddddddddddd "+indexes);
		
		
	
	}
	
	
	public void deleteuser(User user){
		
		System.out.println("ssssssssssssssssssssss ");
		
		
	}
}



