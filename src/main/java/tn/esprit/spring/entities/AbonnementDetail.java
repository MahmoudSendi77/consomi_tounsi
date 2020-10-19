package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AbonnementDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private int pointsfideliteuser;
	private Date deteabon;
	private int remise ;
	private float solde;
	
	
	
	public float getSolde() {
		return solde;
	}
	public void setSolde(float solde) {
		this.solde = solde;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPointsfideliteuser() {
		return pointsfideliteuser;
	}
	public void setPointsfideliteuser(int pointsfideliteuser) {
		this.pointsfideliteuser = pointsfideliteuser;
	}
	public Date getDeteabon() {
		return deteabon;
	}
	public void setDeteabon(Date deteabon) {
		this.deteabon = deteabon;
	}
	public int getRemise() {
		return remise;
	}
	public void setRemise(int remise) {
		this.remise = remise;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AbonnementDetail [id=" + id + ", pointsfideliteuser=" + pointsfideliteuser + ", deteabon=" + deteabon
				+ ", remise=" + remise + "]";
	}
	public AbonnementDetail(Long id, int pointsfideliteuser, Date deteabon, int remise) {
		super();
		this.id = id;
		this.pointsfideliteuser = pointsfideliteuser;
		this.deteabon = deteabon;
		this.remise = remise;
	}
	public AbonnementDetail() {
		super();
	}

	
	
	
	
	
	
}
