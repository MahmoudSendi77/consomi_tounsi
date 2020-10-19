package tn.esprit.spring.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.TypeCommand;
import tn.esprit.spring.entities.User;

@Controller(value = "factureControllerImpl")
@ELBeanName(value = "factureControllerImpl")
@RestController
public class FactureControllerImpl {
@Autowired
commandControllerimpl commandController; 


private Command commandDetail;
private User userpopup;
private Long numeroCommand;
private TypeCommand commandType;
private float montantTTC;
private LocalDate commandDate;
private String commandEtat;
private String email;
private String username;


///////getter

public Command getCommandDetail() {
	return commandDetail;
}
public void setCommandDetail(Command commandDetail) {
	this.commandDetail = commandDetail;
}
public User getUserpopup() {
	return userpopup;
}
public void setUserpopup(User userpopup) {
	this.userpopup = userpopup;
}
public Long getNumeroCommand() {
	return numeroCommand;
}
public void setNumeroCommand(Long numeroCommand) {
	this.numeroCommand = numeroCommand;
}
public TypeCommand getCommandType() {
	return commandType;
}
public void setCommandType(TypeCommand commandType) {
	this.commandType = commandType;
}
public float getMontantTTC() {
	return montantTTC;
}
public void setMontantTTC(float montantTTC) {
	this.montantTTC = montantTTC;
}
public LocalDate getCommandDate() {
	return commandDate;
}
public void setCommandDate(LocalDate commandDate) {
	this.commandDate = commandDate;
}
public String getCommandEtat() {
	return commandEtat;
}
public void setCommandEtat(String commandEtat) {
	this.commandEtat = commandEtat;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}


////////////methode
public void afficheCommandById(){
		Command cd = commandController.getCommandInter() ;
		setCommandDetail(cd);
		setUsername(cd.getUser().getUsername());
		setCommandDate(cd.getCommandDate());
		setCommandType(cd.getCommandType());
		setEmail(cd.getUser().getEmail());
		setMontantTTC(cd.getMontantTTC());
		if(cd.getCommandEtat())
		{setCommandEtat("delivered");}
		else{
			setCommandEtat("undelivered");
		}
		
		
	}
	
public Map<Product,Integer> afficheProductCommand(){
	Command cd = commandController.getCommandInter() ;
	System.out.println("affiche list des produit dans un produit"+cd.getProduits());
	
	List<Product> prods = new ArrayList<>();
	List<Product> prodTest = new ArrayList<>();
	Map ProductRepition = new HashMap();

	int size = cd.getProduits().size();
	for (int index = 0; index < size; index++) {
		if (cd.getProduits() == null) {
			prodTest.add(cd.getProduits().get(index));
		}
		else {
			if (prodTest.contains(cd.getProduits().get(index))) {
				// nothing to do
			} else {
				prodTest.add(cd.getProduits().get(index));
			}
		}
	}
	int toul = prodTest.size();
	for (int index = 0; index < toul; index++) {
		/* frenquency() compte le nombre d'occurrences */
		Integer repitition = Collections.frequency(cd.getProduits(), prodTest.get(index));
		ProductRepition.put(prodTest.get(index), repitition);
	}
	
	
	System.out.println("map :" +ProductRepition);
	
	return ProductRepition;
}

	
	
}
