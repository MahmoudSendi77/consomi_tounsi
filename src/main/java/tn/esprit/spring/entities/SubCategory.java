package tn.esprit.spring.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SubCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String name;
	
	private String description;
	
	@OneToOne(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE)
	private Image logo ;

	
	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubCategory(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SubCategory(String name) {
		super();
		this.name = name;
	}
	

	public SubCategory() {
		super();
	}

	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", name=" + name + "]";
	}
		
}