package tn.esprit.spring.control;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.esprit.spring.config.MyConstants;
import tn.esprit.spring.entities.Reporting;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.RoleName;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.playod.ApiResponse;
import tn.esprit.spring.playod.JwtAuthenticationResponse;
import tn.esprit.spring.playod.LoginRequest;
import tn.esprit.spring.playod.SignUpRequest;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.CurrentUser;
import tn.esprit.spring.security.JwtTokenProvider;
import tn.esprit.spring.security.UserPrincipal;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;

//@RestController

@RequestMapping("/api/auth")
@Controller(value = "AuthController")
@ELBeanName(value = "AuthController")
@Scope(value = "session")
public class AuthController {

	// @Autowired
	// RestTemplate restTemplate;

	
	

	@Autowired
	SigninJsf SigninJsf;
	
	
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

	public static String ttoken = null ;
	
	
	private int i = 0;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	}

	public String LOGOUT() throws IOException {
		//
		// HttpServletRequest response =response. ;
		//
		// response.logout();
		//

		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ex.getRequest();

		HttpServletResponse response = (HttpServletResponse) ex.getResponse();

		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			System.err.println("true");
		Optional<Cookie> token = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("token")).findFirst();

		token.get().setValue(null);

		response.addCookie(token.get());

		// response.sendRedirect("http://localhost:8081/login");

		return "/login/logindesign.xhtml?faces-redirect=true";
	}

	public String getroleuser() {

		User u = new User();
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

		return "hetha role : " + rolesname;
	}

	
	public Long idusercurrent() {

		User u = new User();
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		u = userRepository.findByUsername(userName).get();

		
System.err.println("hetha current user id"+ u.getId());


		return  u.getId();
	}

	
	
	public String signin() {

//		System.err.println("token l gdim " + this.getToken());
//
//		Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		String jwt = tokenProvider.generateToken(authentication);
//
//		// System.err.println(jwt);
//
//		AuthController.ttoken = jwt;
//
//		System.err.println("dff");
//
//		System.err.println("token jdid " + jwt);
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		System.err.println(userName);
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		List<String> roles = new ArrayList<String>();
//		for (GrantedAuthority a : authorities) {
//			roles.add(a.getAuthority());
//		}

		String url = "";
//		if (this.getroleuser().equals("ROLE_USER"))
		
			url = "/name.xhtml?faces-redirect=true";

//		if (roles.contains("ROLE_DELIVERYMAN"))
//			url = "/home2.xhtml?faces-redirect=true";

		System.err.println(this.getroleuser() + " : roles");
		System.err.println(url + " : URL");
//		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
//		HttpServletRequest request = (HttpServletRequest) ex.getRequest();

//		System.err.println(request + " : request");

		return url;
	}

	public void ver() {

		// System.err.println(token);

		HttpHeaders headers = new HttpHeaders();

	}

	// public void doFilter(ServletRequest request, ServletResponse response,
	// FilterChain chain) throws IOException, ServletException {
	// HttpServletRequest req = (HttpServletRequest) request;
	// HttpServletResponse res = (HttpServletResponse) response;
	//
	//
	// res.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + this.token);
	//
	// AuthController controller = (AuthController) req.getSession()
	// .getAttribute("AuthController");
	//
	// res.sendRedirect("../login.xhtml");
	//
	// }
	//

	//
	// public void getTransactions() {
	// // only a 24h token for the sandbox, so not security critical
	// // still I replaced the last 10 digits here with 'x' but not in my
	// original code
	// String authToken =
	// "tylhtvGM6Duy8q0ZBbGaTg2FZefLfyeEeMZvCXlU2bEiinnZcLSACTxxxxxxxxxx";
	//
	// HttpHeaders headers = new HttpHeaders();
	//
	// headers.add("Authorization", "Bearer "+authToken );
	//
	// ResponseEntity<TransactionsResponse> response = restTemplate.exchange(
	// "https://api-sandbox.starlingbank.com/api/v1/transactions",
	// HttpMethod.GET,
	// new HttpEntity<>("parameters", headers),
	// TransactionsResponse.class
	// );
	//
	// return response.getBody().getEmbedded().getTransactions();
	// }

	// http://localhost:8081/SpringMVC/servlet/api/auth/signin
	// {
	//
	// "usernameOrEmail": "user",
	// "password": "000000"
	//
	// }

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

	// @Bean
	// public RestTemplate restTemplate() {
	// return new RestTemplate();
	// }

	//
	// public String signin1 ()
	// {
	//
	//
	// Authentication authentication = authenticationManager.authenticate(
	// new UsernamePasswordAuthenticationToken(username, password));
	// SecurityContextHolder.getContext().setAuthentication(authentication);
	//
	// String jwt = tokenProvider.generateToken(authentication);
	// this.token = tokenProvider.generateToken(authentication);
	// System.err.println(jwt);
	//
	//
	//
	//
	//
	// HttpHeaders headers = new HttpHeaders();
	//// headers.setContentType(MediaType.APPLICATION_JSON);
	// headers.set("Authorization", "Bearer "+jwt);
	//
	//
	//
	// // Fire the initial string payload through to the correct controller
	// endpoint
	// // restTemplate.exchange("http://user-service/users/" + endpoint,
	// HttpMethod.GET, userEntity, String.class);
	// // restTemplate.exchange(access_token_url, HttpMethod.PUT, request,
	// String.class,"zz");
	//
	//
	// HttpEntity<String> entity = new HttpEntity<String>(headers);
	// String result =
	// restTemplate.postForObject("http://localhost:8081/SpringMVC/servlet/jsf/",
	// entity, String.class);
	// return result;
	//
	// }

	// eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTg2NTU3NDg5LCJleHAiOjE1ODcxNjIyODl9.K_a3CAkRTvNyFeOTYNm-w7fW7Or-sb-X1ie0SEy-Qi8gUdFPTESxNwqsT-OHjnzMS6CJFzNP3dYhXjh-TwuCkA
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		System.err.println(authentication);

		User u = new User();
		u = UserService.findByusernameoremail(loginRequest.getUsernameOrEmail());
		// Set<Role> role = u.getRoles();

		// System.err.println(u.setRoles(roles););
		System.err.println(UserserviceInterface.userrole(u.getId()));

		if (UserserviceInterface.userrole(u.getId()).equals("ROLE_USER")) {

			Date d = new Date();
			d.getTime();
			Reporting rep = new Reporting();

			rep = UserService.findByusernameoremail(loginRequest.getUsernameOrEmail()).getReporting();

			if (UserserviceInterface.verifdatebanne(u)) {

				// UserserviceInterface.verifdatebanne(u);
				return new ResponseEntity(new ApiResponse(false, "you return in  " + rep.getDatefinbanne()),
						HttpStatus.LOCKED);

			} else {

				UserserviceInterface.debanneruser(u);

			}

		} else
			System.err.println("role != Roleuser");

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

	}

	@PostMapping("/signup/{code}")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
			final HttpServletRequest request, @PathVariable("code") String code) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already  in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword(), signUpRequest.getDatenaissance());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_NOTCONFIRMED)
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
		user.setActive(false);
		reporting.setUsrnameparrain(UserserviceInterface.finduserbycode(code).getUsername());

		user.setReporting(reporting);
		message.setText(" http://localhost:8081/SpringMVC/servlet/api/auth/comfirme/" + codeparrinage);

		// Send Message!
		mailSender.send(message);
		user = UserserviceInterface.affecterUserTonrml(user);

		User result = userRepository.saveAndFlush(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location)
				.body(new ApiResponse(true, "User registered successfully merci de confirmer votre compte :p "));
	}

	// http://localhost:8081/SpringMVC/servlet/api/auth/signup
	// {
	//
	// "username": "user",
	// "name": "user",
	// "email": "houssem.alimi@esprit.tn",
	// "password": "000000",
	// "datenaissance" : "1996-03-07"
	// }

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
			final HttpServletRequest request) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already  in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword(), signUpRequest.getDatenaissance());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

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
		reporting.setUsrnameparrain("no One");
		message.setText(" http://localhost:8081/SpringMVC/servlet/api/auth/comfirme/" + codeparrinage);

		// Send Message!
		mailSender.send(message);
		user = UserserviceInterface.affecterUserTonrml(user);

		User result = userRepository.saveAndFlush(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location)
				.body(new ApiResponse(true, "User registered successfully merci de confirmer votre compte :p "));
	}

	@PostMapping("/comfirme/{code}")
	public String comfirmation(final HttpServletRequest request, @PathVariable("code") String code) {
		if (UserserviceInterface.finduserbycode(code) == null)
			return "code invalide";

		return UserserviceInterface.confirmation(code);

	}

	@PostMapping("/signupdelivery")
	public ResponseEntity<?> registeradmin(@Valid @RequestBody SignUpRequest signUpRequest,
			final HttpServletRequest request) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, " Username is already taken! "), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword(), signUpRequest.getDatenaissance());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_DELIVERYMAN)
				.orElseThrow(() -> new UsernameNotFoundException("User Role not set." + 1));

		user.setRoles(Collections.singleton(userRole));
		user.setSalary(800);
		// user = UserserviceInterface.affecterUserTonrml(user);

		User result = userRepository.saveAndFlush(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "delivery registered successfully"));
	}

}
