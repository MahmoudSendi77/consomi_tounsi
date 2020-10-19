package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Section implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_s; 
	private String name ; 
	private String description ;
	private String image_s ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="section")
    private List<Topic> topics ;
	
	public Long getId_s() {
		return id_s;
	}
	public void setId_s(Long id_s) {
		this.id_s = id_s;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage_s() {
		return image_s;
	}
	public void setImage_s(String image_s) {
		this.image_s = image_s;
	}
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public Section(Long id_s, String name, String description, String image_s, List<Topic> topics) {
		super();
		this.id_s = id_s;
		this.name = name;
		this.description = description;
		this.image_s = image_s;
		this.topics = topics;
	}
	public Section() {
		super();
	}
	
	@Override
	public String toString() {
		return "Section [id_s=" + id_s + ", name=" + name + ", description=" + description + ", image_s=" + image_s
				+ ", topics=" + topics + "]";
	}

	 
	

}
