package tn.esprit.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.AbonnementService;
import tn.esprit.spring.service.AbonnementServiceInterface;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;

@RestController
@RequestMapping("/api/abon")
public class AbonnRest {

	
	
	@Autowired
	 AbonnementServiceInterface  AbonnementServiceInterface  ;
	@Autowired
	UserService UserService;
	@Autowired
	UserserviceInterface UserserviceInterface;
	
	
	
// http://localhost:8081/SpringMVC/servlet/api/abon/adda_bonnement  
//	{
//    
//    "pointsfidelite": 100,
//    "remise": 40,
//    "validite":1,
//   "type":"GOLD"
//    }
	
	@PostMapping("/adda_bonnement")
	@ResponseBody
	public String addAbonnement(@RequestBody Abonnement Ab)
	{
		
		return AbonnementServiceInterface.add_abonnement(Ab);
	}
	
	
	@PostMapping(value = "/affecterusertoabon/{userid}/{choice}") 
	public String affecterUsertoab(@PathVariable("userid")Long userid, @PathVariable("choice")String choice) {

	return	UserService.affecterUserAbonnement(userid, choice);
	
	
	}
	
	@PutMapping(value = "/calculnmbrpointsparcommande/{userid}/{prixtotale}") 
	public User affecterUsertoab(@PathVariable("userid")Long userid, @PathVariable("prixtotale")float prixtotale) {

	return	UserserviceInterface.calculnbrpoints(userid, prixtotale);
	
	
	}
	
	
}
