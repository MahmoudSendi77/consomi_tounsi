package tn.esprit.spring.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.TypeCommand;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CartRepository;
import tn.esprit.spring.repository.CommandRepository;
import tn.esprit.spring.repository.DeliveyRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class CommandService {
	
	@Autowired
	CartRepository cartRepeository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CommandRepository commandRepository;	
	@Autowired
	DeliveyRepository deliveryRepository;	
	@Autowired
	CartService cartservice;	
	@Autowired
	UserRepository userRepository;

	
	private final static String[] status;
	
	 static {
		 status = new String[3];
		 status[0] = "Cash On Delivery";
		 status[1] = "online Buy";
		 status[2] = "To Jackpot";
	    }
	
	  public List<String> getStatus() {
	        return Arrays.asList(status);
	    }
	
	// passer tous le contenu de panier en tant que commande

	@Transactional
	public Command AddAllTheCartAsCommand(Long idDelevery,Long idCart, String Adresse, Long Numero, TypeCommand commandType) {
		// get cart
		Cart cartManagedEntity = cartRepeository.findById(idCart).get();
		Command commandManagedEntity = new Command();
		Delivery del = deliveryRepository.findById(idCart).get();
		
		
		LocalDate today = LocalDate.now();
		List<Product> prodcts = new ArrayList<>();

		int taille = cartservice.getAllProductByCart(idCart).size();
		for (int index = 0; index < taille; index++) {
			prodcts.add(cartservice.getAllProductByCart(idCart).get(index));

		}
		
		commandManagedEntity.setDelvey(del);
		commandManagedEntity.setUser(cartManagedEntity.getUser());
		commandManagedEntity.setProduits(prodcts);
		commandManagedEntity.setMontantTTC(cartManagedEntity.getMontantTTC());
		commandManagedEntity.setMontantHT(cartManagedEntity.getMontantHT());
		
		commandManagedEntity.setNumeroCommand(Numero);
		commandManagedEntity.setCommandEtat(false);
		commandManagedEntity.setCommandAdresse(Adresse);
		commandManagedEntity.setCommandDate(today);
		commandManagedEntity.setCommandType(commandType);
		commandRepository.save(commandManagedEntity);
		

		return commandManagedEntity;

	}

	// select the product to add to the cart (new Command)
	@Transactional
	public Long addSelectedProductToTheCommand(Long idCart, Long idProduct, Integer quantite, String Adresse,
			Long Numero, TypeCommand commandType) {

		Cart cartManagedEntity = cartRepeository.findById(idCart).get();
		Product ProductManagedEntity = productRepository.findById(idProduct).get();
		Command commandManagedEntity = new Command();

		LocalDate today = LocalDate.now();
		List<Product> prodcts = new ArrayList<>();
		commandManagedEntity.setProduits(null);
		commandManagedEntity.setUser(cartManagedEntity.getUser());
		// commandManagedEntity.setPrix(cartManagedEntity.getTotal());
		commandManagedEntity.setNumeroCommand(Numero);
		commandManagedEntity.setCommandEtat(false);
		commandManagedEntity.setCommandAdresse(Adresse);
		commandManagedEntity.setCommandDate(today);
		commandManagedEntity.setCommandType(commandType);

		// now select the product to add to the cart

		/*
		 * Integer cnt=0; int taille =
		 * cartservice.getAllProductByCart(idCart).size(); for (int index = 0;
		 * index < taille; index++) {
		 * 
		 * if((cartservice.getAllProductByCart(idCart).get(index).getId() ==
		 * idProduct)&&(cnt!=quantite)){
		 * prodcts.add(cartservice.getAllProductByCart(idCart).get(index));
		 * cnt++; break; } }
		 * 
		 * 
		 * commandManagedEntity.setProduits(prodcts);
		 * 
		 */

		commandRepository.save(commandManagedEntity);
		commandManagedEntity.getId();

		return commandManagedEntity.getId();
	}

	@Transactional
	public String addSelectedProduct(Long idCommand, Long idCart, Long idProduct, Integer quantite) {

		Cart cartManagedEntity = cartRepeository.findById(idCart).get();//
		Product produitManagedEntity = productRepository.findById(idProduct).get();
		Command commandManagedEntity = commandRepository.findById(idCommand).get();
		// recherche product dans le cart
		List<Product> prods = new ArrayList<>();
		if (commandManagedEntity.getProduits() != null) {
			prods = commandManagedEntity.getProduits();
		}
		for (int i = 0; i < quantite; i++) {
			prods.add(productRepository.findById(idProduct).get());
		}

		commandManagedEntity.setProduits(prods);
		commandRepository.save(commandManagedEntity);

		return "added";
	}

	public List<Product> getListProductFromCart(List<Long> listProduct) {
		return cartRepeository.getProductById(listProduct);
	}

	// get all product in the cart
	public List<Product> getAllProductByCommand(Long cartId) {
		Command dep = commandRepository.findById(cartId).get();
		return dep.getProduits();
	}

	// delete the command
	public String deleteCommand(Long idCommand) {
		// TODO Auto-generated method stub
		commandRepository.delete(commandRepository.findById(idCommand).get());

		return "delete command";
	}

	public List<Product> searchCommandByNumero(Long numeroCommand) {
		// TODO Auto-generated method stub

		Command cd = commandRepository.findById(numeroCommand).get();

		return cd.getProduits();
	}

	public List<Command> SearchCommandByType(String typeCommand) {
		// TODO Auto-generated method stub
		List<Command> command = commandRepository.findCommandByType(typeCommand);

		return command;
	}

	public String UpdateCommandTypeCommande(Long Command, TypeCommand type) {
		// TODO Auto-generated method stub
		Command cd = commandRepository.findById(Command).get();
		cd.setCommandType(type);
		commandRepository.save(cd);
		return "update quantite";

	}

	// recommended product
	public Product RecommandedProduct(Long idProduct) {

		List<Command> comands = commandRepository.findAllByProduct(idProduct);
		List<Product> prods = new ArrayList<>();
		List<Product> prodTest = new ArrayList<>();
		Product ps = productRepository.findById(idProduct).get();
		Map ProductRepition = new HashMap();
		int taille = comands.size();
		for (int index = 0; index < taille; index++) {
			prods.addAll(comands.get(index).getProduits());
		}
		int size = prods.size();
		for (int index = 0; index < size; index++) {
			if(ps.getId() == prods.get(index).getId()){
				//nothing to do
			}
			else{
			
			if ((prods == null) ) {
				prodTest.add(prods.get(index));
			}
			else {
				if (prodTest.contains(prods.get(index))) {
					// nothing to do
				} else {
					prodTest.add(prods.get(index));
				}
			}
			
			
			
			}
		}
		int toul = prodTest.size();
		for (int index = 0; index < toul; index++) {
			/* frenquency() compte le nombre d'occurrences */
			Integer repitition = Collections.frequency(prods, prodTest.get(index));
			ProductRepition.put(prodTest.get(index), repitition);
		}
		Integer max = 0;
		Product p = new Product();
		Set<Entry<Product, Integer>> setLhm = ProductRepition.entrySet();
		Iterator<Entry<Product, Integer>> it2 = setLhm.iterator();
		while (it2.hasNext()) {
			Entry<Product, Integer> e = it2.next();
			if (max < e.getValue()) {
				max = e.getValue();
				p = e.getKey();
			} else {
				max = max;
			}
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		return p;
	}
	

	//recherche multi critére
	public List<Command> filtreCommande(String type,float minmontantTTC,float maxmontantTTC,float minmontantHT,float maxmontantHT,Date minDate,Date maxDate,Boolean commandEtat){
		List<Command> commds = new ArrayList<>();
		TypeCommand cd = TypeCommand.nothing;
		LocalDate today = LocalDate.now();
		if( (minDate!= null) && (type != TypeCommand.nothing.toString())   ){
			return commandRepository.filtreCommand(minDate, maxDate, minmontantTTC, maxmontantTTC, minmontantHT, maxmontantHT, type,commandEtat);
			//, minmontantTTC, maxmontantTTC, minmontantHT, maxmontantHT, type
		}
		else{
			return commandRepository.findAll();  
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////JSF//////////////////////////////////////////////////////////////////////////////////////////////////

	public Command addSelectedProductCommand(Long idCart,List<Product> prods, String Adresse,
			Long Numero, TypeCommand commandType,Long idDelevery){
				//init
				Cart cart = cartRepeository.findById(idCart).get();
				Command command = new Command();
				Delivery delevery = deliveryRepository.findById(idDelevery).get();
				
				LocalDate today = LocalDate.now();
				List<Product> prodcts = new ArrayList<>();

				int taille = prods.size();
				for (int index = 0; index < taille; index++) {
					prodcts.add(prods.get(index));

				}
				command.setDelvey(delevery);
				command.setUser(cart.getUser());
				command.setProduits(prodcts);
				command.setMontantTTC(cart.getMontantTTC());
				command.setMontantHT(cart.getMontantHT());
				command.setNumeroCommand(Numero);
				command.setCommandEtat(false);
				command.setCommandAdresse(Adresse);
				command.setCommandDate(today);
				command.setCommandType(commandType);
				commandRepository.save(command);
				return command;
	}
	
	
	@Transactional
	public Command AddProductAsCommand(List<Product> prod, String commandType,Long idUser) {
		// get cart
		User user = userRepository.findById(idUser).get();
		Command commandManagedEntity = new Command();
		float montantTc= cartservice.calculTTC(prod);
		float montantht= cartservice.calculHT(prod);
		Long num = (long) Math.random();
		TypeCommand type = TypeCommand.nothing;
		
		if(commandType.equals("Cash On Delivery")){
			type = TypeCommand.CashOnDelivery;
		}
		else if (commandType.equals("online Buy")) {
			type = TypeCommand.onlineBuy;
		}
		else if (commandType.equals("To Jackpot")) {
			type = TypeCommand.ToJackpot;

		}
		
		LocalDate today = LocalDate.now();
		List<Product> prodcts = new ArrayList<>();

		int taille = prod.size();
		for (int index = 0; index < taille; index++) {
			prodcts.add(prod.get(index));

		}
		
		commandManagedEntity.setDelvey(null);
		commandManagedEntity.setUser(user);
		commandManagedEntity.setProduits(prodcts);
		commandManagedEntity.setMontantTTC(montantTc);
		commandManagedEntity.setMontantHT(montantht);
		commandManagedEntity.setNumeroCommand(num);
		commandManagedEntity.setCommandEtat(false);
		commandManagedEntity.setCommandAdresse(null);
		commandManagedEntity.setCommandDate(today);
		commandManagedEntity.setCommandType(type);
		commandRepository.save(commandManagedEntity);
		

		return commandManagedEntity;

	}
	

	public List<Command> afficheAllCommand(){
		return commandRepository.findAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

