package tn.esprit.spring.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class ProductDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	private String ficheTechnique;
	private Float note;
	private Float ranking;
	private Integer reclamtion;
	private Integer declined;
	private Date lastStockUpdate;
	private String tauxDeVidengeDeStock_;
	
	//
	
}
