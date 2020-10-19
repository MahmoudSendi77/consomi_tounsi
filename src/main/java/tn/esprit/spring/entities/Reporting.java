package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reporting implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	
	private Date datefinbanne;
	private Boolean isbanned;
	private int  nbrbanne;
	private	String codeparrinage ;
	private String usrnameparrain;
	private int  nbrgain;
	
	public int getNbrgain() {
		return nbrgain;
	}
	public void setNbrgain(int nbrgain) {
		this.nbrgain = nbrgain;
	}
	
	@ElementCollection(fetch = FetchType.EAGER)
    @JsonIgnore
 
	@Fetch(value = FetchMode.SUBSELECT)
    private List<String> ipAdresses;
	
	
	
	
	public String getCodeparrinage() {
		return codeparrinage;
	}
	public void setCodeparrinage(String codeparrinage) {
		this.codeparrinage = codeparrinage;
	}
	
	
	public String getUsrnameparrain() {
		return usrnameparrain;
	}
	public void setUsrnameparrain(String usrnameparrain) {
		this.usrnameparrain = usrnameparrain;
	}
	public List<String> getIpAdresses() {
		return ipAdresses;
	}
	public void setIpAdresses(List<String> ipAdresses) {
		this.ipAdresses = ipAdresses;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDatefinbanne() {
		return datefinbanne;
	}
	public void setDatefinbanne(Date datefinbanne) {
		this.datefinbanne = datefinbanne;
	}
	public Boolean getIsbanned() {
		return isbanned;
	}
	public void setIsbanned(Boolean isbanned) {
		this.isbanned = isbanned;
	}
	public int getNbrbanne() {
		return nbrbanne;
	}
	public void setNbrbanne(int nbrbanne) {
		this.nbrbanne = nbrbanne;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Reporting() {
		super();
	}
	public Reporting(Long id, Date datefinbanne, Boolean isbanned, int nbrbanne) {
		super();
		this.id = id;
		this.datefinbanne = datefinbanne;
		this.isbanned = isbanned;
		this.nbrbanne = nbrbanne;
	}
	@Override
	public String toString() {
		return "Reporting [id=" + id + ", datefinbanne=" + datefinbanne + ", isbanned=" + isbanned + ", nbrbanne="
				+ nbrbanne + "]";
	}
	
	
	
	
	
	
	

}
