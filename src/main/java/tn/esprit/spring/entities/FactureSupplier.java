package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class FactureSupplier implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	private String numero; 
	
	private String supplied;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private Float price ;
	
	
	@ElementCollection(fetch = FetchType.EAGER )
	@Fetch(value = FetchMode.SUBSELECT)
	private Map<Product,Long> products;
	
	@OneToOne
	private User user;

	
	
	public String getSupplied() {
		return supplied;
	}

	public void setSupplied(String supplied) {
		this.supplied = supplied;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Map<Product, Long> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Long> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FactureSupplier(Long id, String numero, Date date, Float price, Map<Product, Long> products, User user) {
		super();
		this.id = id;
		this.numero = numero;
		this.date = date;
		this.price = price;
		this.products = products;
		this.user = user;
	}

	public FactureSupplier(String numero, Date date, Float price, Map<Product, Long> products, User user) {
		super();
		this.numero = numero;
		this.date = date;
		this.price = price;
		this.products = products;
		this.user = user;
	}
	
	public FactureSupplier(String numero, Date date ,String supplied) {
		super();
		this.numero = numero;
		this.date = date;
		this.supplied=supplied;
		
	}

	public FactureSupplier() {
		super();
	}

	@Override
	public String toString() {
		return "FactureSupplier [id=" + id + ", numero=" + numero + ", date=" + date + ", price=" + price + "]";
	}
	
	
}
