package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProductStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProductStockPK productStockPK;
	
	@ManyToOne
	 @JoinColumn(name = "productId", referencedColumnName = "id", insertable=false, updatable=false)
	private Product product;
	@ManyToOne
	 @JoinColumn(name = "stockId", referencedColumnName = "id", insertable=false, updatable=false)
	private Stock stock;
	private Long quantity;
	private Float unitPrice;
	
	@Temporal(TemporalType.DATE)
	private Date fabricationDate;
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	
	public ProductStockPK getProductStockPK() {
		return productStockPK;
	}
	public void setProductStockPK(ProductStockPK productStockPK) {
		this.productStockPK = productStockPK;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Date getFabricationDate() {
		return fabricationDate;
	}
	public void setFabricationDate(Date fabricationDate) {
		this.fabricationDate = fabricationDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ProductStock(ProductStockPK productStockPK, Product product, Stock stock, Long quantity, Float unitPrice,
			Date fabricationDate, Date expirationDate) {
		super();
		this.productStockPK = productStockPK;
		this.product = product;
		this.stock = stock;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.fabricationDate = fabricationDate;
		this.expirationDate = expirationDate;
	}
	
	public ProductStock(ProductStockPK productStockPK, Product product, Stock stock, Long quantity, Float unitPrice
			) {
		super();
		this.productStockPK = productStockPK;
		this.product = product;
		this.stock = stock;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		
	}
	public ProductStock() {
		super();
	}
	
	
	
	

}
