package tn.esprit.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.TypeCommand;
import tn.esprit.spring.security.CurrentUser;
import tn.esprit.spring.security.UserPrincipal;
//import tn.esprit.spring.service.CommandService;
@RequestMapping("/command")
@Controller
public class commandController {
//@Autowired
//CommandService commandSrvice;


//Add Command
	// http://localhost:8081/Command/addcommand/2/1/1/jackpot
//  @PutMapping("/addSelectedProduct/{idCart}/{Adresse}/{Numero}/{commandType}/{userid}")
//
//  public String addSelectedProductToTheCommand(@PathVariable("idCart")Long idCart,@PathVariable("Adresse") String Adresse,@PathVariable("Numero") Long Numero,@PathVariable("commandType")TypeCommand commandType,@PathVariable("userid") Long iduser) {
//		System.out.println("karim");
//	  return commandSrvice.AddAllTheCartAsCommand(idCart,Adresse,Numero, commandType,iduser).toString();
// 
//  
//  }
	
	
}
