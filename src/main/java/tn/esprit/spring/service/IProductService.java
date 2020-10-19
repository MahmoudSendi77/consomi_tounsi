package tn.esprit.spring.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Product;

public interface IProductService {

	public long addProduct(Product p);

	public long updateProduct(Product p);

	public long deleteProduct(long id);

	public List<Product> getAllProduct();

	public List<Product> getNotAffectedProductToAisle();

	public List<Product> getProductByAisleId(long id);

	public List<Product> getProductbyX(String x, String value);

	public Product getProductById(long id);

	public List<Product> getProductByRef(String ref);

	public boolean verifyProduct(String s);

	public int countSellByX(String x, String value, Date date1, Date date2);

	public void affecterProductToAisle(long aisleId, long productId);

	public void desaffecterProductToAisle(long aisleId, long productId);

	public String affecterProductToStock(long stockId, long productId, long quantity, float unitPrice,
			Date fabricationDate, Date expirationDate);
	
	public String affecterProductToStock(long stockId, long productId, long quantity, float unitPrice
			);

	public void updateAffecteProductToStock(long productId, long quantity, float unitPrice, Date fabricationDate,
			Date expirationDate);

	public void desaffecterProductToStock(long stockId, long productId);

	public void checkDateValiditer();

	public List<Product> filterSearch(Product product);

	public long populateAisle(Long idAisle, List<Long> idProducts);

	public Long getQuantityById(long value);

	public Long getQuantityByReference(String value);

	public List<List<Long>> getQuantityLessThan(long value);

	//
	// historique search
	// notifaction product ----- chef aisle stock fra8 --search out stock wall
	//

	public List<List<Long>> getProductOf(String value);

	public List<List<Long>> getCategoryOf(String value);

	public Map<Long, Product> getProductOfYear();

	public Map<Long, Product> getProductOfMonth();

	public List<Category> getAllCategory();

	// searching filtred

	public List<Product> searchProduct(String search);

	public List<Product> searchProductByName(String value);

	public List<Product> searchProductByCategory(String value);

	public List<Product> searchProductByBrand(String value);

}
