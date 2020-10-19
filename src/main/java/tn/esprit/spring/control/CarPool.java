package tn.esprit.spring.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.Part;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.RoleName;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.JwtTokenProvider;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;


@Controller(value = "CarPool")
@ELBeanName(value = "CarPool")
public class CarPool implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	UserRepository userRepository;
	
	
	private String role1;
	public String getRole1() {
		return role1;
	}

	public void setRole1(String role1) {
		this.role1 = role1;
	}




	private RoleName roleName;

	public RoleName[] getRoleName() {
		
		
	
		return RoleName.values();
	}
	
	
	
	
	private RoleName roleNameVerif;

	public RoleName[] getRoleNameVerif() {
		return RoleName.values();
	}
	
	

	
private String usernamedet;
	public String getUsernamedet() {
	return usernamedet;
}
	
	
	private float salary;
	private String mail;
	private String passworddet;
	
	public String getPassworddet() {
		return passworddet;
	}

	public void setPassworddet(String passworddet) {
		this.passworddet = passworddet;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	


	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}


	private Date datenaissance;

public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

public void setUsernamedet(String usernamedet) {
	this.usernamedet = usernamedet;
}
private double number2; 


	public double getNumber2() {
	return number2;
}

public void setNumber2(double number2) {
	this.number2 = number2;
}




	private User userpopup;

	public User getUserpopup() {
		return userpopup;
	}

	public void setUserpopup(User userpopup) {
		this.userpopup = userpopup;
	}


	
	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		
		System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr "+ role);
	if(role != null )	
	{
	if(this.role.equals("ROLE_DELIVERYMAN"))
	
	return userRepository.findByRoles_Id(3l);
	
	if(this.role.equals("ROLE_USER"))
	return userRepository.findByRoles_Id(1l);
	if(this.role.equals("ROLE_ADMIN"))
		return userRepository.findByRoles_Id(2l);
	else
	
		return userRepository.findAll();
	}
	
	return userRepository.findAll();
	}
	
	
	public void setUsers(List<User> users) {
		
		
		
		this.users = users;
	}




	



	public void onSelect(String username, String typeOfSelection, String indexes) {
		
		
		
		System.out.println("onSelect sssssssssssssssssssssssssss "+indexes +"hhhhhhhhhh"+username);
		
		
		
		
		
		
		
		
		
	}

	public void onDeselect( String username,String typeOfSelection, String indexes) {
		
		
	
		
		
		System.out.println("onDeselect sssssssssssssssssssssssssss "+indexes  +"hhhhhhhhhh"+username);
		
		
		
		System.out.println( " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);

		
	}
	
	
	public void deleteuser(User user){
		
		System.out.println("ssssssssssssssssssssss "+ user.getUsername());
		
		
		
		userRepository.delete(user);
		
	}
	
	public void onSubmitDate3(){
		System.out.println("ssssssssssssssssssssss "+ this.datenaissance );
		System.out.println("ssssssssssssssssssssss "+ this.salary );
		System.out.println("ssssssssssssssssssssss "+ this.mail );
		System.out.println("ssssssssssssssssssssss "+ this.usernamedet );
		System.out.println("ssssssssssssssssssssss "+ this.passworddet );
		System.out.println("ssssssssssssssssssssss "+ this.role );
	}
	
	
	
	private List<String> roles=new ArrayList<String>();
	public List<String> getRoles() {
		
		roles.add("ROLE_USER");
		roles.add("ROLE_DELIVERY");
		roles.add("ROLE_ADMIN");
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	private String role;
	public String getRole() {
		
		
		
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	@Autowired
	AuthenticationManager authenticationManager;

	
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
	
	
	
	
	
	
	public void registeradmin(
			) {
	System.out.println("sssssssssssssssssssssssssssssssss");

		// Creating user's account
		User user = new User("no one", this.usernamedet, this.mail,
				this.passworddet, this.datenaissance);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		
		
		if (role1 .equals("ROLE_DELIVERYMAN"))
		{
		
		
		
		Role userRole = roleRepository.findByName(RoleName.ROLE_DELIVERYMAN)
				.orElseThrow(() -> new UsernameNotFoundException("User Role not set." + 1));
		
		
		

		user.setRoles(Collections.singleton(userRole));
		}
		
		
		if (role1 .equals("ROLE_ADMIN"))
		{
		
		
		
		Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new UsernameNotFoundException("User Role not set." + 1));
		
		
		

		user.setRoles(Collections.singleton(userRole));
		}
		
		
		
		
		
		
		user.setSalary( (int) this.salary);
		user.setProfileImage("avat-01-512.png");
		// user = UserserviceInterface.affecterUserTonrml(user);
        user.setActive(true);
		User result = userRepository.saveAndFlush(user);

//		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
//				.buildAndExpand(result.getUsername()).toUri();

		
	}
	
	public void banneruser(User user)
	{
		
		System.out.println(user.getUsername());
		UserService .banneruser(user.getId())	;
		
		
	}
	
	public String manageuser(){
		
		
		return "/login/usrecrud.jsf?faces-redirect=true";
	}
	
	
	
}