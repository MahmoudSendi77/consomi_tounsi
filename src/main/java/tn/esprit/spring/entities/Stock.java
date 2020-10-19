package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"products","users"})
public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private Long capacite;
			
	private String type;//
	
	@OneToOne
	private Adresse adress;	//

	@OneToMany
	private List<User> users;  //

	@OneToMany(mappedBy="stock")
	private List<ProductStock> stocks; //list des produits 

	@Override
	public String toString() {
		return "Stock [id=" + id + ", capacite=" + capacite + ", type=" + type + ", adress=" + adress + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCapacite() {
		return capacite;
	}

	public void setCapacite(Long capacite) {
		this.capacite = capacite;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Adresse getAdress() {
		return adress;
	}

	public void setAdress(Adresse adress) {
		this.adress = adress;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ProductStock> getStocks() {
		return stocks;
	}

	public void setStocks(List<ProductStock> stocks) {
		this.stocks = stocks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Stock(Long id, Long capacite, String type, Adresse adress, List<User> users, List<ProductStock> stocks) {
		super();
		this.id = id;
		this.capacite = capacite;
		this.type = type;
		this.adress = adress;
		this.users = users;
		this.stocks = stocks;
	}

	public Stock(Long capacite, String type, Adresse adress, List<User> users, List<ProductStock> stocks) {
		super();
		this.capacite = capacite;
		this.type = type;
		this.adress = adress;
		this.users = users;
		this.stocks = stocks;
	}

	public Stock() {
		super();
	}
	public Stock(Long capacite, String type) {
		super();
		this.capacite = capacite;
		this.type = type;
		
	}
	
	
}
