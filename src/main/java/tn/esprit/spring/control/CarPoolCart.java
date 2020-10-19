package tn.esprit.spring.control;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.TypeCommand;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.CommandService;

@Controller(value = "CarPoolCart")
@ELBeanName(value = "CarPoolCart")
public class CarPoolCart implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommandService commandService;
	
	private Command commandDetail;
	private User userpopup;
	private Long numeroCommand;
	private TypeCommand commandType;
	private float montantTTC;
	private LocalDate commandDate;
	private String commandEtat;
	private String email;
	private String username;

	///////////////////////////////// getter ///////////////////////////////
	public User getUserpopup() {
		return userpopup;
	}
	public void setUserpopup(User userpopup) {
		this.userpopup = userpopup;
	}
	private List<User> users = new ArrayList<User>();
	public List<User> getUsers() {

		return userRepository.findAll();
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Command getCommandDetail() {
		return commandDetail;
	}
	public void setCommandDetail(Command commandDetail) {
		this.commandDetail = commandDetail;
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
	////////////////////////////////////////////////////////////////////////
	public void onSelect(Long id, String typeOfSelection, String indexes) {

		System.out.println("onSelect  :" + indexes + " id :" + id);

	}

	public void onDeselect(Long id, String typeOfSelection, String indexes) {
		System.out.println("onDeselect : " + indexes + " id :" + id);
		System.out.println(" typeOfSelection: " + typeOfSelection + " indexes: " + indexes);
	}

	public String deltailCommand(Command command) {
		setCommandDetail(command);
		System.out.println("details ");
		String navigateTo = "";
		navigateTo = "/pages/detailCommand.xhtml?faces-redirect=true";
		System.err.println("succes");

		
		setUsername(commandDetail.getUser().getUsername());
		setCommandDate(commandDetail.getCommandDate());
		setCommandType(commandDetail.getCommandType());
		setEmail(commandDetail.getUser().getEmail());
		setMontantTTC(commandDetail.getMontantTTC());
		if(commandDetail.getCommandEtat())
		{setCommandEtat("delivered");}
		else{
			setCommandEtat("undelivered");
		}
		
		return navigateTo;

	}
	
	public String afficheAllCommandAdmin(){
		System.out.println("details x");
		String navigateTo = "";
		navigateTo = "/pages/adminhomePage.xhtml?faces-redirect=true";
		return navigateTo;
	}
	
	
 public Map<Product,Integer> afficheProductCommand(){

	
	List<Product> prods = new ArrayList<>();
	List<Product> prodTest = new ArrayList<>();
	Map ProductRepition = new HashMap();
	

	
	int size = commandDetail.getProduits().size();
	for (int index = 0; index < size; index++) {
		if (commandDetail.getProduits() == null) {
			prodTest.add(commandDetail.getProduits().get(index));
		}
		else {
			if (prodTest.contains(commandDetail.getProduits().get(index))) {
				// nothing to do
			} else {
				prodTest.add(commandDetail.getProduits().get(index));
			}
		}
	}
	int toul = prodTest.size();
	for (int index = 0; index < toul; index++) {
		/* frenquency() compte le nombre d'occurrences */
		Integer repitition = Collections.frequency(commandDetail.getProduits(), prodTest.get(index));
		ProductRepition.put(prodTest.get(index), repitition);
	}
	
	

	
	return ProductRepition;
}

 	



public void afficheMapProduct(){
	
	List<Product> prods = new ArrayList<>();
	List<Product> prodTest = new ArrayList<>();
	Map ProductRepition = new HashMap();
	
	
	int size = commandDetail.getProduits().size();
	for (int index = 0; index < size; index++) {
		if (commandDetail.getProduits() == null) {
			prodTest.add(commandDetail.getProduits().get(index));
		}
		else {
			if (prodTest.contains(commandDetail.getProduits().get(index))) {
				// nothing to do
			} else {
				prodTest.add(commandDetail.getProduits().get(index));
			}
		}
	}
	int toul = prodTest.size();
	for (int index = 0; index < toul; index++) {
		/* frenquency() compte le nombre d'occurrences */
		Integer repitition = Collections.frequency(commandDetail.getProduits(), prodTest.get(index));
		ProductRepition.put(prodTest.get(index), repitition);
	}
	
	
	
	
}
}