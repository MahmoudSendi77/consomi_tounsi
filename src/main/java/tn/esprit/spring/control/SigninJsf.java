package tn.esprit.spring.control;



	
	

	import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.config.MyConstants;
import tn.esprit.spring.entities.Reporting;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.RoleName;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.JwtTokenProvider;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;

	//@RestController
//@Scope(value = "session")
	@RestController
	@RequestMapping("/api/auth/jsf")
	@Controller(value = "SigninJsf")
	@ELBeanName(value = "SigninJsf")

	public class SigninJsf {

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

		private String username;
		private String password;
		private String token;
		private String codesignup;
		
		public static String token1;
		
		public String getCodesignup() {
			return codesignup;
		}

		public void setCodesignup(String codesignup) {
			this.codesignup = codesignup;
		}

		@NaturalId
		@NotBlank
		@Size(max = 40)
		@Email
		private String Email;
		
		public String getEmail() {
			return Email;
		}

		public void setEmail(String email) {
			Email = email;
		}

		private Date date;
		private String gender ;
		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		private int i = 0;

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		
		
		public void ver() {

			System.err.println(" Email"+   this.Email   ); 
			System.err.println(" gender"+   this.gender); 
			System.err.println("password "+   this.password); 		
			System.err.println("username "+   this.username); 
			System.err.println(" date"+   this.date); 
					}
		
		
		
		public void doLogin() {

			String userName = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}
			System.err.println("uuuuu :" + userName);
			
			System.err.println("hetha user "+ userRepository.findByUsername(userName) );

		}
		
		@PostConstruct
		public boolean nameuser(){
//			
//			System.err.println("SecurityContextHolder as : " + SecurityContextHolder.getContext());
//			
//			System.err.println("SecurityContextHolder.getContext().getAuthentication() as : " + SecurityContextHolder.getContext().getAuthentication());
//			System.err.println("SecurityContextHolder.getContext().getAuthentication().getPrincipal() as : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			String userName = null;
			if(SecurityContextHolder.getContext().getAuthentication() == null)
			return false;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			
//			System.err.println("SecurityContextHolder as : " + SecurityContextHolder.getContext());
//			
//			System.err.println("SecurityContextHolder.getContext().getAuthentication() as : " + SecurityContextHolder.getContext().getAuthentication());
//			System.err.println("SecurityContextHolder.getContext().getAuthentication().getPrincipal() as : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}
			System.err.println("login as : " +userName);
			
			if(userName.equals("anonymousUser"))
			return true ;
			else 
				return false ;
		}
		
		
public String nameuserC(){
			
			
			String userName = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}
			System.err.println("login as : " +userName);

		
			
			if(userName.equals("anonymousUser"))
			return "" ;
			else return userName;
	
		}
		
		
		
@Transactional
		public String logout  () throws IOException {
			//
			// HttpServletRequest response =response. ;
			//
			// response.logout();
			//

//			ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
//			HttpServletRequest request = (HttpServletRequest) ex.getRequest();
//
//			HttpServletResponse response = (HttpServletResponse) ex.getResponse();
//
//			Cookie[] cookies = request.getCookies();
//			if (cookies == null)
//				System.err.println("true");
//			
//			
//			Optional<Cookie> token = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("token")).findFirst();
//			
//			token.get().setValue(null);
//			token.get().setMaxAge(0);
//			
//			response.addCookie(token.get());
//			
//			
		

			// response.sendRedirect("http://localhost:8081/login");

			return "/login/templatelogin.xhtml?faces-redirect=true";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		public String rnder(){
			
	
			return "/login/templatelogin.xhtml?faces-redirect=true";
		}

		
		
		
		
		
		
		
		
		
		
		
		public String getroleuser() {

			User u ;
			String userName = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}

			u = userRepository.findByUsername(userName).get();

			String rolesname;
			rolesname = u.getRoles().stream().findFirst().get().getName().name();

			return  rolesname;
		}

		
		public Long idusercurrent() {

			User u ;
			String userName = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}

			u = userRepository.findByUsername(userName).get();
			
			System.err.println("hetha user "+ u);
			
			
	System.err.println("hetha current user id"+ u.getId());


			return  u.getId();
		}

		
		
		public String signin() {

			
			
	System.out.println("eeeeeeeeeeeeeeeeeeeeeeee"  + codesignup);
			
			
			
//			System.err.println("token l gdim " + this.getToken());
			
			
			
			

			Authentication authentication;
			String url = null;
			try {
				authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(username, password));
				System.err.println(authentication);
				SecurityContextHolder.getContext().setAuthentication(authentication);

				String jwt = tokenProvider.generateToken(authentication);
				


				this.token = jwt;
				token1=jwt;

				System.err.println("dff");

				System.err.println("token jdid " + jwt);
				String userName = null;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				if (principal instanceof UserDetails) {
					userName = ((UserDetails) principal).getUsername();
				} else {
					userName = principal.toString();
				}
				System.err.println(userName);
				Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
				List<String> roles = new ArrayList<String>();
				for (GrantedAuthority a : authorities) {
					roles.add(a.getAuthority());
				}

				
				if (roles.contains("ROLE_USER"))
			
//			HttpServletResponse r ; =		r.sendRedirect("http://​localhost:8081/login/editprofile.jsf");
			url = "/pages/frontend/productViews/homeProductView.xhtml?faces-redirect=true";

				
				if (roles.contains("ROLE_ADMIN"))
					
//					HttpServletResponse r ; =		r.sendRedirect("http://​localhost:8081/login/editprofile.jsf");
					url = "/login/aboncrud.jsf?faces-redirect=true";
			

				if (roles.contains("ROLE_DELIVERYMAN"))
					url = "/name.xhtml?faces-redirect=true";

				System.err.println(roles + " : roles");

				ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
				HttpServletRequest request = (HttpServletRequest) ex.getRequest();

				System.err.println(request + " : request");
				return url;
			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
				FacesMessage facesMessage =

						new FacesMessage("Login Failed: please check your username/password and try again.");
				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			System.err.println(e.getMessage());	
				
			}
			return url;

		}

	

		
		 

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	
		
		
		
		
		@Transactional
		public void registerUser(
			) {
System.err.println(this.gender);
			if (userRepository.existsByUsername(this.username)) {
				FacesMessage facesMessage =

						new FacesMessage("Username is already taken!");
				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
//				return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
				System.err.println("Username is already taken!");
			}

			else	if (userRepository.existsByEmail(this.Email)) {
				FacesMessage facesMessage =

						new FacesMessage("Email Address already  in use!");
				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
//				return new ResponseEntity(new ApiResponse(false, "Email Address already  in use!"), HttpStatus.BAD_REQUEST);
				System.err.println("Email Address already  in use!");
			}
			
			else	if (this.gender.equals("Gender")) {
				FacesMessage facesMessage =	
				new FacesMessage("please enter your Gender!");
				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			}
			else{

			// Creating user's account
			User user = new User("no one", this.username, this.Email,
					this.password, this.date);

			user.setPassword(passwordEncoder.encode(user.getPassword()));
user.setProfileImage("avat-01-512.png");
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new UsernameNotFoundException("User Role not set." + 1));

			user.setRoles(Collections.singleton(userRole));

			// mailSender.;

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(MyConstants.FRIEND_EMAIL);
			Random objGenerator = new Random();
			int randomNumber = objGenerator.nextInt(1000);

			message.setSubject("email de verification");

			//////
			Date d = new Date();
			d.getTime();
			Reporting reporting = new Reporting();
			reporting.setIsbanned(false);
			reporting.setDatefinbanne(d);
			String codeparrinage = UserserviceInterface.generatecodeparrainage();
			reporting.setCodeparrinage(codeparrinage);
			user.setReporting(reporting);
			user.setActive(false);
			
			System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyy   " +  codesignup  );
			if(codesignup != null)
			{
				
				reporting.setUsrnameparrain(UserserviceInterface.finduserbycode(codesignup).getUsername());
				
				reporting.setNbrgain(10);

				codesignup=null;
			}
			else{
			reporting.setUsrnameparrain("no One"); 
			
			
			}
			message.setText("http://localhost:8081/comfirme/" + codeparrinage);

			// Send Message!
			mailSender.send(message);
			user = UserserviceInterface.affecterUserTonrml(user);

			User result = userRepository.save(user);

//			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
//					.buildAndExpand(result.getUsername()).toUri();

//			return ResponseEntity.created(location)
//					.body(new ApiResponse(true, "User registered successfully merci de confirmer votre compte :p "));
			
			System.err.println( "User registered successfully merci de confirmer votre compte :p ");
			
			FacesMessage facesMessage =

					new FacesMessage("User registered successfully merci de confirmer votre compte :p ");
			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			
			
			}
		}
		
		
		
		
		
		
//		@PostMapping("/signup/{code}")
//		public void registerUser( String code) {
//
//			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//				return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
//			}
//
//			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//				return new ResponseEntity(new ApiResponse(false, "Email Address already  in use!"), HttpStatus.BAD_REQUEST);
//			}
//
//			// Creating user's account
//			User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
//					signUpRequest.getPassword(), signUpRequest.getDatenaissance());
//
//			user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//			Role userRole = roleRepository.findByName(RoleName.ROLE_NOTCONFIRMED)
//					.orElseThrow(() -> new UsernameNotFoundException("User Role not set." + 1));
//
//			user.setRoles(Collections.singleton(userRole));
//
//			// mailSender.;
//
//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setTo(MyConstants.FRIEND_EMAIL);
//			Random objGenerator = new Random();
//			int randomNumber = objGenerator.nextInt(1000);
//
//			message.setSubject("email de verification");
//
//			//////
//			Date d = new Date();
//			d.getTime();
//			Reporting reporting = new Reporting();
//			reporting.setIsbanned(false);
//			reporting.setDatefinbanne(d);
//			String codeparrinage = UserserviceInterface.generatecodeparrainage();
//			reporting.setCodeparrinage(codeparrinage);
//			user.setActive(false);
//			reporting.setUsrnameparrain(UserserviceInterface.finduserbycode(code).getUsername());
//
//			user.setReporting(reporting);
//			message.setText(" http://localhost:8081/SpringMVC/servlet/api/auth/comfirme/" + codeparrinage);
//
//			// Send Message!
//			mailSender.send(message);
//			user = UserserviceInterface.affecterUserTonrml(user);
//
//			User result = userRepository.saveAndFlush(user);
//
//			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
//					.buildAndExpand(result.getUsername()).toUri();
//
//			return ResponseEntity.created(location)
//					.body(new ApiResponse(true, "User registered successfully merci de confirmer votre compte :p "));
//		}
		
		
	}

	
	
	
	

