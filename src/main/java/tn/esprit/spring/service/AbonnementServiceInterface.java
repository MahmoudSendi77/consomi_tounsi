package tn.esprit.spring.service;

import tn.esprit.spring.entities.Abonnement;

public interface AbonnementServiceInterface {

	
	
	public String add_abonnement ( Abonnement Ab);
	public String update_abonnement ( Abonnement Ab);
	public String delete_abonnement ( Long idAb);
	public Boolean existbytype( String type);
	
}
