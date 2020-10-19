package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"cosmmands","jackpot","user"})
public class Don implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float montantdon;
	private float montantconsomi;
	private String descriptiondon;
	private String nom ;
	private String mail;
	private boolean montrermontant;
	private boolean aider;
	private boolean montreridentité;
	private boolean modepaiment;
	private int nbrvue;
	
	@Column(name = "date")
 
	private Date date;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="don",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)

	private List<Command> cosmmands;
	
	@ManyToOne
	private Jackpot jackpot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getMontantdon() {
		return montantdon;
	}

	public void setMontantdon(float montantdon) {
		this.montantdon = montantdon;
	}

	public float getMontantconsomi() {
		return montantconsomi;
	}

	public void setMontantconsomi(float montantconsomi) {
		this.montantconsomi = montantconsomi;
	}

	public String getDescriptiondon() {
		return descriptiondon;
	}

	public void setDescriptiondon(String descriptiondon) {
		this.descriptiondon = descriptiondon;
	}

	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isMontrermontant() {
		return montrermontant;
	}

	public void setMontrermontant(boolean montrermontant) {
		this.montrermontant = montrermontant;
	}

	public boolean isAider() {
		return aider;
	}

	public void setAider(boolean aider) {
		this.aider = aider;
	}

	public boolean isMontreridentité() {
		return montreridentité;
	}

	public void setMontreridentité(boolean montreridentité) {
		this.montreridentité = montreridentité;
	}

	public boolean isModepaiment() {
		return modepaiment;
	}

	public void setModepaiment(boolean modepaiment) {
		this.modepaiment = modepaiment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Command> getCosmmands() {
		return cosmmands;
	}

	public void setCosmmands(List<Command> cosmmands) {
		this.cosmmands = cosmmands;
	}

	public Jackpot getJackpot() {
		return jackpot;
	}

	public void setJackpot(Jackpot jackpot) {
		this.jackpot = jackpot;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	

	public int getNbrvue() {
		return nbrvue;
	}

	public void setNbrvue(int nbrvue) {
		this.nbrvue = nbrvue;
	}



	@Override
	public String toString() {
		return "Don [id=" + id + ", montantdon=" + montantdon + ", montantconsomi=" + montantconsomi
				+ ", descriptiondon=" + descriptiondon + ", nom=" + nom + ", mail=" + mail + ", montrermontant="
				+ montrermontant + ", aider=" + aider + ", montreridentité=" + montreridentité + ", modepaiment="
				+ modepaiment + ", nbrvue=" + nbrvue + ", date=" + date + ", user=" + user + ", cosmmands=" + cosmmands
				+ ", jackpot=" + jackpot + "]";
	}

	public Don(float montantdon, float montantconsomi, String descriptiondon, String nom, String mail,
			boolean montrermontant, boolean aider, boolean montreridentité, boolean modepaiment) {
		super();
		this.montantdon = montantdon;
		this.montantconsomi = montantconsomi;
		this.descriptiondon = descriptiondon;
		this.nom = nom;
		this.mail = mail;
		this.montrermontant = montrermontant;
		this.aider = aider;
		this.montreridentité = montreridentité;
		this.modepaiment = modepaiment;
	}

	public Don(Long id, float montantdon, float montantconsomi, String descriptiondon, String nom, String mail,
			boolean montrermontant, boolean aider, boolean montreridentité, boolean modepaiment) {
		super();
		this.id = id;
		this.montantdon = montantdon;
		this.montantconsomi = montantconsomi;
		this.descriptiondon = descriptiondon;
		this.nom = nom;
		this.mail = mail;
		this.montrermontant = montrermontant;
		this.aider = aider;
		this.montreridentité = montreridentité;
		this.modepaiment = modepaiment;
	}

	public Don(float montantdon, float montantconsomi, String descriptiondon, String nom, String mail,
			boolean montrermontant, boolean aider, boolean montreridentité, boolean modepaiment, Date date, User user,
			List<Command> cosmmands, Jackpot jackpot) {
		super();
		this.montantdon = montantdon;
		this.montantconsomi = montantconsomi;
		this.descriptiondon = descriptiondon;
		this.nom = nom;
		this.mail = mail;
		this.montrermontant = montrermontant;
		this.aider = aider;
		this.montreridentité = montreridentité;
		this.modepaiment = modepaiment;
		this.date = date;
		this.user = user;
		this.cosmmands = cosmmands;
		this.jackpot = jackpot;
	}

	
	
	public Don(float montantdon, float montantconsomi, String descriptiondon, String nom, String mail,
			boolean montrermontant, boolean aider, boolean montreridentité, boolean modepaiment, Date date, User user,
			Jackpot jackpot) {
		super();
		this.montantdon = montantdon;
		this.montantconsomi = montantconsomi;
		this.descriptiondon = descriptiondon;
		this.nom = nom;
		this.mail = mail;
		this.montrermontant = montrermontant;
		this.aider = aider;
		this.montreridentité = montreridentité;
		this.modepaiment = modepaiment;
		this.date = date;
		this.user = user;
		this.jackpot = jackpot;
	}

	public Don() {
		super();
	}

	public Don(float montantdon, float montantconsomi, String descriptiondon, String nom, String mail,
			boolean montrermontant, boolean aider, boolean montreridentité, boolean modepaiment, Date date) {
		super();
		this.montantdon = montantdon;
		this.montantconsomi = montantconsomi;
		this.descriptiondon = descriptiondon;
		this.nom = nom;
		this.mail = mail;
		this.montrermontant = montrermontant;
		this.aider = aider;
		this.montreridentité = montreridentité;
		this.modepaiment = modepaiment;
		this.date = date;
	}

	public Don(Long id, float montantdon, float montantconsomi, String descriptiondon, String nom, String mail,
			boolean montrermontant, boolean aider, boolean montreridentité, boolean modepaiment, int nbrvue,
			Date date) {
		super();
		this.id = id;
		this.montantdon = montantdon;
		this.montantconsomi = montantconsomi;
		this.descriptiondon = descriptiondon;
		this.nom = nom;
		this.mail = mail;
		this.montrermontant = montrermontant;
		this.aider = aider;
		this.montreridentité = montreridentité;
		this.modepaiment = modepaiment;
		this.nbrvue = nbrvue;
		this.date = date;
	}

	public Don(float montantdon, float montantconsomi, String descriptiondon, String nom, String mail,
			boolean montrermontant, boolean aider, boolean montreridentité, boolean modepaiment, int nbrvue,
			Date date) {
		super();
		this.montantdon = montantdon;
		this.montantconsomi = montantconsomi;
		this.descriptiondon = descriptiondon;
		this.nom = nom;
		this.mail = mail;
		this.montrermontant = montrermontant;
		this.aider = aider;
		this.montreridentité = montreridentité;
		this.modepaiment = modepaiment;
		this.nbrvue = nbrvue;
		this.date = date;
	}

	
	
	
	
	
	
	
}

