package tn.esprit.spring.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.TypeCommand;

public interface IFactureService {
	
	public Facture createFacture(Long idCommand);
	
	public String DeleteFacture(Long idFacture);
	
	public String UpdateFacture(Long idFacture);
	
	public Facture searchFactureByNumero(Long numero);
	
	public List<Product> getAllProductByFacture(Long idFacture);
	
	public List<Facture> getAllFacture();
	
	public List<Facture> getFactureByDate(LocalDate date1,LocalDate date2);

	Command getCommandByFacture(Long idFacture);
	
	public Facture getFactureById(Long idFacture);
	
	public List<Facture> getFactureByUserName(String name);
	
	public List<Facture> getFactureByType(String type);
	
	public String createFactureDelivery(Long idCommand);
	
 }
