package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "commands", "carts", "aisle", "users", "stocks", "brand", "products" })
public class Product implements Serializable {

	/**
	 * 
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String reference;// code a barre

	private String name;

	private Float price;

	@Enumerated(EnumType.STRING)
	private Size size;

	private String dimention;

	private Float weight;

	private String color;

	private Float discount;

	private int quantitCart;

	private float prixQuantiteCart;

	// @Temporal(TemporalType.DATE)
	// private Date fabricationDate;
	// @Temporal(TemporalType.DATE)
	// private Date expirationDate;

	private Float tva;

	private String image;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Image> picture;

	@ManyToOne
	private Brand brand;

	@ManyToOne
	private Category category;

	@ManyToOne
	private SubCategory subCategory;

	@ManyToMany(mappedBy = "produits", fetch = FetchType.LAZY)
	private List<Command> commands;

	@ManyToMany(mappedBy = "produits", fetch = FetchType.LAZY)
	private List<Cart> carts;

	@ManyToOne
	private Aisle aisle;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ProductStock> products;

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public String getDimention() {
		return dimention;
	}

	public void setDimention(String dimention) {
		this.dimention = dimention;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getTva() {
		return tva;
	}

	public void setTva(Float tva) {
		this.tva = tva;
	}

	public List<Image> getPicture() {
		return picture;
	}

	public void setPicture(List<Image> picture) {
		this.picture = picture;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Aisle getAisle() {
		return aisle;
	}

	public void setAisle(Aisle aisle) {
		this.aisle = aisle;
	}

	public List<ProductStock> getProducts() {
		return products;
	}

	public void setProducts(List<ProductStock> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

	public int getQuantitCart() {
		return quantitCart;
	}

	public void setQuantitCart(int quantitCart) {
		this.quantitCart = quantitCart;
	}

	public float getPrixQuantiteCart() {
		return prixQuantiteCart;
	}

	public void setPrixQuantiteCart(float prixQuantiteCart) {
		this.prixQuantiteCart = prixQuantiteCart;
	}

	public Product(Long id, String reference, String name, Float price, Size size, String dimention, Float weight,
			String color, Float discount, Float tva, List<Image> picture, Brand brand, Category category,
			List<Command> commands, List<Cart> carts, Aisle aisle, List<ProductStock> products) {
		super();
		this.id = id;
		this.reference = reference;
		this.name = name;
		this.price = price;
		this.size = size;
		this.dimention = dimention;
		this.weight = weight;
		this.color = color;
		this.discount = discount;
		this.tva = tva;
		this.picture = picture;
		this.brand = brand;
		this.category = category;
		this.commands = commands;
		this.carts = carts;
		this.aisle = aisle;
		this.products = products;
	}

	public Product(String reference, String name, Float price, Size size, String dimention, Float weight, String color,
			Float discount, Float tva, List<Image> picture, Brand brand, Category category, List<Command> commands,
			List<Cart> carts, Aisle aisle, List<ProductStock> products) {
		super();
		this.reference = reference;
		this.name = name;
		this.price = price;
		this.size = size;
		this.dimention = dimention;
		this.weight = weight;
		this.color = color;
		this.discount = discount;
		this.tva = tva;
		this.picture = picture;
		this.brand = brand;
		this.category = category;
		this.commands = commands;
		this.carts = carts;
		this.aisle = aisle;
		this.products = products;
	}

	public Product(String reference, String name, Float price, Size size, String dimention, Float weight, String color,
			Float discount, Float tva) {
		super();
		this.reference = reference;
		this.name = name;
		this.price = price;
		this.size = size;
		this.dimention = dimention;
		this.weight = weight;
		this.color = color;
		this.discount = discount;
		this.tva = tva;
	}

	@Override
	public String toString() {
		return "Product [reference=" + reference + ", name=" + name + ", price=" + price + ", size=" + size
				+ ", weight=" + weight + ", color=" + color + ", tva=" + tva + "]";
	}

}
