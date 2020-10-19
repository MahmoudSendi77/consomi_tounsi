package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.ChargeRequest;
import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.email;

public interface ICommandService {

	String createCommand(Long idCart, String Adresse, Long Numero, String commandType);
	String deleteCommand(Long idCommand);
	List<Product> searchCommandByNumero(Long numeroCommand);
	List<Command> SearchCommandByType(String typeCommand);
	String UpdateCommandTypeCommande(Long Command,String type);

	
}
