package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@JsonIgnoreProperties({"users"})
public class Abonnement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private int pointsfidelite;

	private int remise;

	private int validite;
	private String couleur;

	private String type;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}





	private String image;
	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}





//
	@OneToMany( mappedBy = "abonnement",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
			
	private List<User> users= new ArrayList<>();

	public Abonnement() {
		super();
	}

	public Abonnement(Long id, int pointsfidelite, int remise, int validite, String type,
			List<User> user) {
		super();
		this.id = id;
		this.pointsfidelite = pointsfidelite;
		this.remise = remise;
		this.validite = validite;
		this.type = type;
		this.users = user;
	}

	@Override
	public String toString() {
		return "Abonnement [id=" + id + ", pointsfidelite=" + pointsfidelite + ", remise=" + remise + ", validite="
				+ validite + ", type=" + type + ", user=" + users + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPointsfidelite() {
		return pointsfidelite;
	}

	public void setPointsfidelite(int pointsfidelite) {
		this.pointsfidelite = pointsfidelite;
	}

	public int getRemise() {
		return remise;
	}

	public void setRemise(int remise) {
		this.remise = remise;
	}

	public int getValidite() {
		return validite;
	}

	public void setValidite(int validite) {
		this.validite = validite;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<User> getUser() {
		return users;
	}

	public void setUser(List<User> user) {
		this.users = user;
	}
	
	
	
	
	public void adduser(User user){
		user.setAbonnement(this);
		this.users.add(user);
	}
	


}
