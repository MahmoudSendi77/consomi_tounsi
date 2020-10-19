package tn.esprit.spring.control;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.TypeCommand;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.FactureService;
import tn.esprit.spring.service.UserService;

@RestController
@RequestMapping("/facture")
public class FactureController {
	@Autowired
	FactureService factureService;
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	UserService UserService;
	
	
	
	//create Facture
		// http://localhost:8081/SpringMVC/servlet/facture/addFacture/1
	    @PutMapping("/addFacture/{idCommand}")
	    @PreAuthorize("hasRole('USER')")
	    public Facture addtoCart(@PathVariable("idCommand")Long idCommand) {
			return factureService.createFacture(idCommand);
	    }
	    
	    
	    
	    @GetMapping("/getCommand/{idFacture}")
	    @PreAuthorize("hasRole('USER')")
	    public Command getCommand(@PathVariable("idFacture")Long idFacture){
	    	return factureService.getCommandByFacture(idFacture);
	    	
	    }
	    
	    @GetMapping("/getAllFacture")
	    @PreAuthorize("hasRole('USER')")
	    public List<Facture> getAllFacture(){
	    	return factureService.getAllFacture();
	    	
	    }
	    
	 // http://localhost:8081/SpringMVC/servlet/facture/export/1
	    @GetMapping("/export/{idFacture}")
	    @PreAuthorize("hasRole('USER')")
	    public String export(@PathVariable("idFacture") Long idFacture){
	    	return factureService.export(idFacture);
	    	
	    }
	    
	    
	 // http://localhost:8081/SpringMVC/servlet/facture/getFactureByDate/date1/date2
	    @GetMapping("/getFactureByDate/{date1}/{date2}")
	    @PreAuthorize("hasRole('USER')")
	    public List<Facture> getFactureByDate(@PathVariable("date1")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate date1,@PathVariable("date2")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2){
	    	return factureService.getFactureByDate(date1, date2);
	    	
	    } 
	    
		 // http://localhost:8081/SpringMVC/servlet/facture/getFactureByUserName/nameUser
	    @GetMapping("/getFactureByUserName/{nameUser}")
	    @PreAuthorize("hasRole('USER')")
	    public List<Facture> getFactureByUserName(@PathVariable("nameUser") String nameUser){
	    	return factureService.getFactureByUserName(nameUser);
	    	
	    } 
	    
	    // http://localhost:8081/SpringMVC/servlet/facture/getFactureByType/type
	    @GetMapping("/getFactureByType/{type}")
	    @PreAuthorize("hasRole('USER')")
	    public List<Facture> getFactureByType(@PathVariable("type") String type){
	    	return factureService.getFactureByType(type);
	    	
	    } 
	    
	    
	    // http://localhost:8081/SpringMVC/servlet/facture/createQRCode/1
	    @GetMapping("/createQRCode/{idFacture}")
	    @PreAuthorize("hasRole('USER')")
	    public String createQRCode(@PathVariable("idFacture") Long idFacture) throws Exception{
	    	return factureService.writeQRCode(idFacture);
	    	
	    } 
}
