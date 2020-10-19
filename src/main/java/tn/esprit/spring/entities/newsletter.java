package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class newsletter implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	
	
	
	
	
	
	
	public newsletter() {
		super();
	}







	public newsletter(String email) {
		super();
		this.email = email;
	}







	public newsletter(Long id, String email) {
		super();
		this.id = id;
		this.email = email;
	}







	public String getEmail() {
		return email;
	}







	public void setEmail(String email) {
		this.email = email;
	}







	public Long getId() {
		return id;
	}







	public void setId(Long id) {
		this.id = id;
	}







	@Override
	public String toString() {
		return "newsletter [id=" + id + ", email=" + email + "]";
	}
	
	
	
	
}
