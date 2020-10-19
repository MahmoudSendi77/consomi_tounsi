package tn.esprit.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adresse  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "adresseNumero")
	private int adresseNumero;
	
	@Column(name = "rue")
	private String rue;
	
	@Column(name = "codePostal")
	private Long codePostal;

	@Column(name = "city")
	private String city;

	
	

}

