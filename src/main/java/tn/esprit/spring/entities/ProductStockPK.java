package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductStockPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long productId;
	private Long stockId;
	
	//private Long quantity;
	//private Float unitPrice;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ProductStockPK(Long productId, Long stockId) {
		super();
		this.productId = productId;
		this.stockId = stockId;
	}
	public ProductStockPK() {
		super();
	}
	
	

}
