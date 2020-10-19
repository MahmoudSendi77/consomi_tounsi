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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class PromosMagazin implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
    private	Date dateBegin;
	@Temporal(TemporalType.DATE)
	private	Date dateEnd;
	
	private String discription ;
	@OneToOne 
	private Image image;	
	private String status;
	@ManyToMany(fetch = FetchType.EAGER )
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Product> products;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateend) {
		this.dateEnd = dateend;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	public PromosMagazin(String name, Date dateBegin, Date dateend, String discription, Image image, String status,
			List<Product> products) {
		super();
		this.name = name;
		this.dateBegin = dateBegin;
		this.dateEnd = dateend;
		this.discription = discription;
		this.image = image;
		this.status = status;
		this.products = products;
	}

	public PromosMagazin(Long id, String name, Date dateBegin, Date dateend, String discription, Image image,
			String status, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.dateBegin = dateBegin;
		this.dateEnd = dateend;
		this.discription = discription;
		this.image = image;
		this.status = status;
		this.products = products;
	}

	public PromosMagazin() {
		super();
	}

	@Override
	public String toString() {
		return "PromosMagazin [id=" + id + ", name=" + name + ", dateBegin=" + dateBegin + ", dateend=" + dateEnd
				+ ", discription=" + discription + ", image=" + image + ", products=" + products + "]";
	}
	
	

}