package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

@Entity
public class Ads implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	
	private String name;
	
	private String description;

	private String canal;
	
	private String type;
	
	private Float cost;

	@OneToMany(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Image> file;
	
	@ManyToOne(fetch = FetchType.EAGER )
	private Product produit;

	@Temporal(TemporalType.DATE)
	private Date beginDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private Integer initialTargetedViews;
	
	private Integer finalTargetedViews;


	
	
	public Product getProduit() {
		return produit;
	}


	public void setProduit(Product produit) {
		this.produit = produit;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Ads( String name, String description, String canal, Float cost, List<Image> file,
			Date beginDate, Date endDate, Integer initialTargetedViews, Integer finalTargetedViews) {
		super();
		this.name = name;
		this.description = description;
		this.canal = canal;
		this.cost = cost;
		this.file = file;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.initialTargetedViews = initialTargetedViews;
		this.finalTargetedViews = finalTargetedViews;
	}


	public Ads(Long id, String name, String description, String canal, Float cost, List<Image> file,
			Date beginDate, Date endDate, Integer initialTargetedViews, Integer finalTargetedViews) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.canal = canal;
		this.cost = cost;
		this.file = file;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.initialTargetedViews = initialTargetedViews;
		this.finalTargetedViews = finalTargetedViews;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCanal() {
		return canal;
	}


	public void setCanal(String canal) {
		this.canal = canal;
	}


	public Float getCost() {
		return cost;
	}


	public void setCost(Float cost) {
		this.cost = cost;
	}


	public List<Image> getFile() {
		return file;
	}


	public void setFile(List<Image> file) {
		this.file = file;
	}


	public Date getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Integer getInitialTargetedViews() {
		return initialTargetedViews;
	}


	public void setInitialTargetedViews(Integer initialTargetedViews) {
		this.initialTargetedViews = initialTargetedViews;
	}


	public Integer getFinalTargetedViews() {
		return finalTargetedViews;
	}


	public void setFinalTargetedViews(Integer finalTargetedViews) {
		this.finalTargetedViews = finalTargetedViews;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Ads() {
		super();
	}


	@Override
	public String toString() {
		return "Ads [id=" + id + ", name=" + name + ", description=" + description + ", canal=" + canal + ", cost="
				+ cost + ", file=" + file + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", initialTargetedViews=" + initialTargetedViews + ", finalTargetedViews=" + finalTargetedViews + "]";
	}
	

}
