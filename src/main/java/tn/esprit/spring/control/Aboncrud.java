package tn.esprit.spring.control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.Part;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.RoleName;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AbonnementRepository;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.JwtTokenProvider;
import tn.esprit.spring.service.AbonnementService;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;


@Controller(value = "Aboncrud")
@ELBeanName(value = "Aboncrud")
public class Aboncrud implements Serializable {
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
	
	return userRepository.findByRoles_Id(2l);
	
	if(this.role.equals("ROLE_USER"))
	return userRepository.findByRoles_Id(1l);
	if(this.role.equals("ROLE_ADMIN"))
		return userRepository.findByRoles_Id(3l);
	else
	
		return userRepository.findAll();
	}
	
	return userRepository.findAll();
	}
	
	
	public void setUsers(List<User> users) {
		
		
		
		this.users = users;
	}




	



	public void onSelect(Long id, String typeOfSelection, String indexes) {
		
		
		
		System.out.println("onSelect sssssssssssssssssssssssssss "+indexes +"hhhhhhhhhh"+id);
		
		

		
	}

	public void onDeselect( String username,String typeOfSelection, String indexes) {
		
		
	
		
		
		System.out.println("onDeselect sssssssssssssssssssssssssss "+indexes  +"hhhhhhhhhh"+username);
		
		
		
		System.out.println( " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);

		
	}
	
	
	public void deleteuser(Abonnement abon){
		
		System.out.println("ssssssssssssssssssssss "+ abon.getType());
		
		
		
		//userRepository.delete(user);
		
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
		
		
		return "/login/usrescrud.jsf?faces-redirect=true";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	AbonnementRepository AbonnementRepository;
	
	@Autowired
	AbonnementService AbonnementService;
	
	
	
	private List<Abonnement> abons = new ArrayList<Abonnement>();
	public List<Abonnement> getAbons() {
		
		
		
		
		return AbonnementRepository.findAll();
	}

	public void setAbons(List<Abonnement> abons) {
		this.abons = abons;
	}
	
	
	
	
	
	
	
	
	public String manageabons(){
		
		
		return "/login/aboncrud.jsf?faces-redirect=true";
	}
	
	
	
	
public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getValidite() {
		return validite;
	}

	public void setValidite(int validite) {
		this.validite = validite;
	}

	public int getRemise() {
		return remise;
	}

	public void setRemise(int remise) {
		this.remise = remise;
	}




private	String type;
	

private int points;

private String couleur;
private  int validite;
private int remise;


	












private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/webapp/resources/uploads/userProfileImage";

@PostMapping("/uploadabon") // //new annotation since 4.3
public String singleFileUpload(@RequestParam("file") MultipartFile file , @RequestParam("typeabon") String typeabon ,
		@RequestParam("validite") int valid      ,@RequestParam("remise") int remise    ,@RequestParam("points") int points  , @RequestParam("couleur") String couleur ) {
	
    if (file.isEmpty()) {
    	System.out.println("ssssssssssssssssssssssssssssss");
    	
    	
  
    	
       // redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
       // return "redirect:uploadStatus";
    }

    try {
    	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"  +typeabon+   valid+ remise +points+couleur.toString());
        // Get the file and save it somewhere
    	File dir = new File( UPLOADED_FOLDER);
		
    	if (!dir.exists())
			dir.mkdirs();
        byte[] bytes = file.getBytes();
  
        Path path = Paths.get(UPLOADED_FOLDER +File.separator+ file.getOriginalFilename());
        Files.write(path, bytes);

        
        Abonnement ab =new Abonnement();
        
        
        
        
        
        ab.setCouleur(couleur);
        ab.setImage(file.getOriginalFilename());
        ab.setPointsfidelite(points);
        ab.setRemise(remise);
        ab.setType(typeabon);
        ab.setValidite(valid);
        
        
        AbonnementService.add_abonnement(ab);
        
        
        
        
        
        
        
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded '" + file.getOriginalFilename() + "'");
  
//        UserRepository.findById(SigninJsf.idusercurrent()).get().setProfileImage(file.getOriginalFilename());
//        UserRepository.save( UserRepository.findById(SigninJsf.idusercurrent()).get());
        return "redirect:/login/aboncrud.jsf";

    } catch (IOException e) {
    	
        e.printStackTrace();
    //    profile.redirecToSom();
        return "redirect:/uploadStatus";
    }

  //  return "redirect:/uploadStatus";
}


private Long idabb;

public Long getIdabb() {
	return idabb;
}

public void setIdabb(Long idabb) {
	this.idabb = idabb;
}




private String abosn;


public String getAbosn() {
	return abosn;
}

public void setAbosn(String abosn) {
	this.abosn = abosn;
}


private String imagetest;



public String getImagetest() {
	return imagetest;
}

public void setImagetest(String imagetest) {
	this.imagetest = imagetest;
}

public void update(Abonnement abon){
	
	
	System.out.println(abon.getType());
	this.setIdabb(abon.getId());
	this.setAbosn(abon.getImage());
	
	this.setType(abon.getType());
	
this.setPoints(abon.getPointsfidelite());
	//private int points;

//	private String couleur;
	
	this.setCouleur(abon.getCouleur());
	
	//private  int validite;
	
	this.setValidite(abon.getValidite());
	//private int remise;
	
	
	
	this.setRemise(abon.getRemise());
	
	
	
	
	
	
}


public void updateaabon(){
	
	Abonnement ab = new Abonnement();
ab =	AbonnementRepository.findById(this.idabb).get();
	
	ab.setPointsfidelite(this.points);
	ab.setRemise(remise);
	ab.setValidite(validite);
	ab.setType(type);
	
	
	AbonnementRepository.save(ab);
	
	
	
}

public boolean abonimage(){
	
	
	if(this.getAbosn()!=null )
	return false ;
	else
	return true;
}





@PostMapping("/uploadabonupdate") // //new annotation since 4.3
public String singleFileUploadupdate(@RequestParam("file") MultipartFile file  ) {
	
    if (file.isEmpty()) {
    	System.out.println("ssssssssssssssssssssssssssssss");
    	
    	
  
    	
       // redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
       // return "redirect:uploadStatus";
    }

    try {
    	
        // Get the file and save it somewhere
    	File dir = new File( UPLOADED_FOLDER);
		
    	if (!dir.exists())
			dir.mkdirs();
        byte[] bytes = file.getBytes();
  
        Path path = Paths.get(UPLOADED_FOLDER +File.separator+ file.getOriginalFilename());
        Files.write(path, bytes);

        
        Abonnement ab = new Abonnement();
        ab =	AbonnementRepository.findById(this.idabb).get();
        
        ab.setImage(file.getOriginalFilename());
        
        AbonnementRepository.save(ab);
        
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded '" + file.getOriginalFilename() + "'");
  
//        UserRepository.findById(SigninJsf.idusercurrent()).get().setProfileImage(file.getOriginalFilename());
//        UserRepository.save( UserRepository.findById(SigninJsf.idusercurrent()).get());
        return "redirect:/login/aboncrud.jsf";

    } catch (IOException e) {
    	
        e.printStackTrace();
    //    profile.redirecToSom();
        return "redirect:/uploadStatus";
    }

  //  return "redirect:/uploadStatus";
}











	
	
}