package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.entities.User;

public interface IStockService {

	public long addStock(Stock stock);
	
	public long deleteStock(Long stockId);

	public long updateStock(Stock stock);

	public long addCategoryToStock(long stockId,  Long categoryId);

	public long populateStock(Long idstock, List<Long> idProducts);

	public long deleteCategoryFromStock(long stockId, Long categoryId);

	public List<Product> getStockProduct(long id);

	public List<Stock> getStockX(Stock stock);

	public void affecterSupplierToStock(long stockId, long productId);

	public void desaffecterSupplierToStock(long stockId, long userId);

	public List<Stock> getAllStock();

	public Stock getStockbyId(long id);	
	
	public List<User> getListSupplierByStock(long stockId);
	
	public List<Stock> getListStockBySupplier(long userId);
	
	public Stock getListStockByAdress(long userId);
	
	public float profitabilityOfstock(long stockId);
	
	
}
