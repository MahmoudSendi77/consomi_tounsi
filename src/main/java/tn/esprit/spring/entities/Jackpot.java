package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({"dons","picture"})
public class Jackpot implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	
	private Date  datefin;
	private float maxValue;
	private String Image;
	private String descriptionjackpot;
	
	private int nbrvue;
	@Enumerated(EnumType.STRING)
	private Typejackpot typeJackpot;
	
	@OneToMany(mappedBy="jackpot",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)

	private List<Don> dons;
	
	
	@OneToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
	private List<Image> picture;
	
	

	public int getNbrvue() {
		return nbrvue;
	}


	public void setNbrvue(int nbrvue) {
		this.nbrvue = nbrvue;
	}


	public String getImage() {
		return Image;
	}


	public void setImage(String image) {
		Image = image;
	}


	public List<Image> getPicture() {
		return picture;
	}


	public void setPicture(List<Image> picture) {
		this.picture = picture;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Date getDatefin() {
		return datefin;
	}


	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}


	public float getMaxValue() {
		return maxValue;
	}


	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}


	public Typejackpot getTypeJackpot() {
		return typeJackpot;
	}


	public void setTypeJackpot(Typejackpot typeJackpot) {
		this.typeJackpot = typeJackpot;
	}


	public List<Don> getDons() {
		return dons;
	}


	public void setDons(List<Don> dons) {
		this.dons = dons;
	}


	public String getDescriptionjackpot() {
		return descriptionjackpot;
	}


	public void setDescriptionjackpot(String descriptionjackpot) {
		this.descriptionjackpot = descriptionjackpot;
	}



	@Override
	public String toString() {
		return "Jackpot [id=" + id + ", titre=" + titre + ", datefin=" + datefin + ", maxValue=" + maxValue + ", Image="
				+ Image + ", descriptionjackpot=" + descriptionjackpot + ", nbrvue=" + nbrvue + ", typeJackpot="
				+ typeJackpot + ", dons=" + dons + ", picture=" + picture + "]";
	}


	public Jackpot(Long id, String titre, Date datefin, float maxValue, String image, String descriptionjackpot,
			Typejackpot typeJackpot, List<Don> dons, List<tn.esprit.spring.entities.Image> picture) {
		super();
		this.id = id;
		this.titre = titre;
		this.datefin = datefin;
		this.maxValue = maxValue;
		Image = image;
		this.descriptionjackpot = descriptionjackpot;
		this.typeJackpot = typeJackpot;
		this.dons = dons;
		this.picture = picture;
	}


	public Jackpot(String titre, Date datefin, float maxValue, String image, String descriptionjackpot,
			Typejackpot typeJackpot) {
		super();
		this.titre = titre;
		this.datefin = datefin;
		this.maxValue = maxValue;
		Image = image;
		this.descriptionjackpot = descriptionjackpot;
		this.typeJackpot = typeJackpot;
	}


	public Jackpot(Long id, String titre, Date datefin, float maxValue, String image, String descriptionjackpot,
			Typejackpot typeJackpot) {
		super();
		this.id = id;
		this.titre = titre;
		this.datefin = datefin;
		this.maxValue = maxValue;
		Image = image;
		this.descriptionjackpot = descriptionjackpot;
		this.typeJackpot = typeJackpot;
	}


	public Jackpot(Long id, String titre, Date datefin, float maxValue, String image, String descriptionjackpot,
			int nbrvue, Typejackpot typeJackpot) {
		super();
		this.id = id;
		this.titre = titre;
		this.datefin = datefin;
		this.maxValue = maxValue;
		Image = image;
		this.descriptionjackpot = descriptionjackpot;
		this.nbrvue = nbrvue;
		this.typeJackpot = typeJackpot;
	}






	public Jackpot() {
		super();
	}


	
	
	
}
