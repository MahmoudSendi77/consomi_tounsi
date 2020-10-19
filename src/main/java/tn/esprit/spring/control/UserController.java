package tn.esprit.spring.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.User;

import tn.esprit.spring.playod.UserIdentityAvailability;
import tn.esprit.spring.playod.UserSummary;
import tn.esprit.spring.repository.AbonnementRepository;

import tn.esprit.spring.repository.UserRepository;

import tn.esprit.spring.security.CurrentUser;
import tn.esprit.spring.security.UserPrincipal;
import tn.esprit.spring.service.AbonnementService;
//import tn.esprit.spring.service.CommandService;

import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;

@RestController
@RequestMapping("/api")

public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserService UserService;

	

	
	@Autowired
	AbonnementRepository AbonnementRepository;
	@Autowired
	UserserviceInterface UserserviceInterface;

	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
				currentUser.getName());
		return userSummary;
	}

	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/user/USERS")
	public List<User> GETUSERS(@RequestParam(value = "role") Long id) {

		List<User> list = new ArrayList<User>();

		for (int i = 0; i < userRepository.findByRoles_Id(id).size(); i++) {
			User u = new User();
			u = userRepository.findByRoles_Id(id).get(i);
			list.add(u);
		}

		return list;
	}

	@GetMapping("/user/USERS/salaire")
	public int GETUSERSsalaire(@RequestParam(value = "role") Long id) {
		return UserService.somme(id);

	}

	// @GetMapping("/user/product/{age}")
	// public List<String> getallproductvendu(@PathVariable("age") int age) {
	// return ProductRespository.getAllcategoriebyage(age) ;
	//
	// }
	//

//	@GetMapping("/user/product/{age}")
//	public List<String> getallproductvendu(@PathVariable("age") int age) {
//		return ProductRespository.countSellBySize(age);
//
//	}

	@GetMapping("/user/categ/{age}")
	public void getcategories(@PathVariable("age") int age) {

		UserserviceInterface.statistics(age);

	}

	@GetMapping("/user/catgdate/{age}/{d}/{d1}")
	public String getcategories(@PathVariable("age") int age, @PathVariable("d") String d,
			@PathVariable("d1") String d1) {

		Date da = Date.valueOf(d);
		Date da1 = Date.valueOf(d1);

		//
		System.err.println(da);
		//
		return UserserviceInterface.statisticsdate(age, da, da1);
		// return ProductRespository.countySize(age, da, da1);

	}

	@DeleteMapping("/user/deleteuser/{userid}")
	public String deleteuser(@PathVariable("userid") Long userid) {
		return UserserviceInterface.deleteuser(userid);

	}

	// http://localhost:8081/SpringMVC/servlet/api/user/prixcommandes/2020-03-03/2020-03-05
	@GetMapping("/user/prixcommandes/{d}/{d1}")
	public Float getsommesalaire(@PathVariable("d") String d, @PathVariable("d1") String d1) {

		Date da = Date.valueOf(d);
		Date da1 = Date.valueOf(d1);
		System.err.println(UserserviceInterface.calculsommedescommand(da, da1));
		return UserserviceInterface.calculsommedescommand(da, da1);

	}

	// http://localhost:8081/SpringMVC/servlet/api/user/salaires/2020-03-03/2020-03-05
	@GetMapping("/user/salaires/{d}/{d1}")
	public Float getsalaire(@PathVariable("d") String d, @PathVariable("d1") String d1) {

		Date da = Date.valueOf(d);
		Date da1 = Date.valueOf(d1);
		// System.err.println(UserserviceInterface.calculsommedescommand(da,
		// da1));
		return userRepository.getsalaireparnmbrdejours(da, da1);

	}

	// http://localhost:8081/SpringMVC/servlet/api/user/deponse/2020-03-03/2020-03-05
	@GetMapping("/user/deponse/{d}/{d1}")
	public Float getalldeponse(@PathVariable("d") String d, @PathVariable("d1") String d1) {

		Date da = Date.valueOf(d);
		Date da1 = Date.valueOf(d1);
		// System.err.println(UserserviceInterface.calculsommedescommand(da,
		// da1));
		return userRepository.getAlldeponse(da, da1);

	}

	// http://localhost:8081/SpringMVC/servlet/api/user/gain/2020-03-03/2020-03-05
	@GetMapping("/user/gain/{d}/{d1}")
	public Float gain(@PathVariable("d") String d, @PathVariable("d1") String d1) {

		Date da = Date.valueOf(d);
		Date da1 = Date.valueOf(d1);
		// System.err.println(UserserviceInterface.calculsommedescommand(da,
		// da1));
		return UserserviceInterface.gain(da, da1);

	}

	@GetMapping("/user/type")
	public String type() {

		// System.err.println(UserserviceInterface.calculsommedescommand(da,
		// da1));
		return UserserviceInterface.nbrutilisateurpartype();

	}

	@PutMapping("/user/banner/{iduser}")
	public String banneruser(@PathVariable("iduser") Long iduser) {

		// System.err.println(UserserviceInterface.calculsommedescommand(da,
		// da1));
		return UserserviceInterface.banneruser(iduser);

	}

	@GetMapping("/user/getbranD/{cat}/{d}/{d1}")
	public String getbranD(@PathVariable("cat") String cat, @PathVariable("d") String d,
			@PathVariable("d1") String d1) {

		Date da = Date.valueOf(d);
		Date da1 = Date.valueOf(d1);

		//
		System.err.println(da);
		//
		return UserserviceInterface.getbrand(cat, da, da1).toString();
		// return ProductRespository.countySize(age, da, da1);

	}



	@GetMapping("/user/getbranDvalueaffaire/{cat}/{d}/{d1}")
	public String getbranDvalueaffarire(@PathVariable("cat") String cat, @PathVariable("d") String d,
			@PathVariable("d1") String d1) {

		Date da = Date.valueOf(d);
		Date da1 = Date.valueOf(d1);

		//
		System.err.println(da);
		//

		return UserserviceInterface.revenuparbrand(cat, da, da1).toString();
		// return ProductRespository.countySize(age, da, da1);

	}

//	@GetMapping("/user/montantparjackpot")
//	public List<List<String>> montantparjackpot() throws UnknownHostException {
//
//		InetAddress localhost = InetAddress.getLocalHost();
//		System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
//
//		String systemipaddress = "";
//		try {
//			URL url_name = new URL("http://bot.whatismyipaddress.com");
//
//			BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
//
//			// reads system IPAddress
//			systemipaddress = sc.readLine().trim();
//		} catch (Exception e) {
//			systemipaddress = "Cannot Execute Properly";
//		}
//		System.out.println("Public IP Address: " + systemipaddress + "\n");
//
//		return ProductRespository.montantparjackpot();
//
//	}



	

	@GetMapping("/getParinageCodeByUser")
	public String getrandomparrainage(@CurrentUser UserPrincipal currentUser) {

		User user = new User();
		user =  UserserviceInterface.getuserbyid(currentUser.getId());
		
		String genarate = " http://localhost:8081/SpringMVC/servlet/api/auth/signup/" + user.getReporting().getCodeparrinage();

	

		return genarate;

	}

	@GetMapping("/userParinage/{cat}")
	public String finduserparrainage(@PathVariable("cat") String cat) throws UnknownHostException {

		InetAddress localhost = InetAddress.getLocalHost();
		localhost.getHostName().trim();

		System.err.println(localhost.getHostName().trim());

		User u = new User();
		u = UserserviceInterface.finduserbycode(cat);
		if (u == null)
			return "pas de user avec ce code ";
		else
			return UserserviceInterface.finduserbycode(cat).toString();

	}
//	@GetMapping("/test")
//	public List<List<String>>getbrand()  {
//
//
//		List<List<String>> l = ProductRespository.nbrDeVenteParBrandAndCateg1("cat3");
//		System.err.println( l.size());
//		for (int i = 0; i < l.size(); i++)
//
//	
//			System.err.println( (int )Float.parseFloat(l.get(i).get(1)));
//			
//			
//			return ProductRespository.nbrDeVenteParBrandAndCateg1("cat3");
//
//	}
//	
	
	@GetMapping("/signal/{iduserabanner}")
	public String signal(@CurrentUser UserPrincipal currentUser,@PathVariable("iduserabanner") Long iduserabanner)  {


			
			
			return UserserviceInterface.SignalerUser(currentUser.getId(), iduserabanner);

	}
	@GetMapping("/signaltest/{iduserabanner}")
	public int signal(@PathVariable("iduserabanner") Long iduserabanner)  {


			
			
			return UserserviceInterface.countnbrsignal(iduserabanner);

	}

@GetMapping("/desasignaltesta")
public List<User> desasignaltest()  {


		
		
	return	 userRepository.findAll();

}
//@GetMapping("/desasignaltest")
//public String getPerfilUsuario(@RequestParam(value = "code") String code) throws Exception {
//	System.err.println("vide");
//	ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
//	HttpServletRequest request = (HttpServletRequest) ex.getRequest();
//
//	Map<String, String> parametros = SocialAuthUtil.getRequestParametersMap(request);
//	System.err.println("vide");
////	if (socialManager != null) {
////		AuthProvider provider = socialManager.connect(parametros);
////		this.setProfile(provider.getUserProfile());
////		System.err.println(provider);
////
////	}
//return parametros.toString();
////	FacesContext.getCurrentInstance().getExternalContext().redirect(mainURL);
//}
	
}
