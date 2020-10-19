package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "categoryDescription")
	private String categoryDescription;

	
	@Column(name = "CategoryName")
	private String categoryName;
	
	@OneToMany(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<SubCategory> subCategory;
	
	@OneToOne(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE)
	private Image logo ;
		
	
	public Long getId() {
		return id;
	}
	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<SubCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Category(Long id, String categoryName, List<SubCategory> subCategory, String categoryDescription) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.subCategory = subCategory;
		this.categoryDescription = categoryDescription;
	}

	public Category(String categoryName, List<SubCategory> subCategory, String categoryDescription) {
		super();
		this.categoryName = categoryName;
		this.subCategory = subCategory;
		this.categoryDescription = categoryDescription;
	}

	public Category(String categoryName, String categoryDescription) {
		super();
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}

	
	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", categoryDescription=" + categoryDescription
				+ "]";
	}

	

//  stock ? /// debbou y fih stocks x -20 //idstock 1le 2021
//	
//	///debbou y fih stock x 200 bdate jdida id stock 1000
	

	
	
}
