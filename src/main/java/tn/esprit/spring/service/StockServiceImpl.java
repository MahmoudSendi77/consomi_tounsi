package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AisleRepository;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class StockServiceImpl implements IStockService {

	@Autowired
	AisleRepository aisleRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	StockRepository stockRepository;
	
	
	
	@Override
	public long addStock(Stock stock) {
		stockRepository.save(stock);
		return stock.getId();
	}

	@Override
	public long deleteStock(Long stockId) {
		stockRepository.delete(stockRepository.findById(stockId).get());
		return 0;
	}

	@Override
	public long updateStock(Stock stock) {
		if(stockRepository.findById(stock.getId()).get()!=null){
		stockRepository.save(stock);
		return stock.getId();
		}
		return -1;
	}

	@Override
	public long addCategoryToStock(long stockId, Long categoryId) {
		
		return 0;
	}

	@Override
	public long populateStock(Long idstock, List<Long> idProducts) {
//		Stock stock=stockRepository.findById(idstock).get();
//		List<Product> listProduct=new ArrayList<>();
//		if(stock.getProducts()!=null){
//			listProduct=stock.getProducts();
//		}
//		for(int i=0;i<idProducts.size();i++){
//			listProduct.add(productRepository.findById(idProducts.get(i)).get());
//		}
//		stock.setProducts(listProduct);
//		stockRepository.save(stock);
		return 0;
	}

	@Override
	public long deleteCategoryFromStock(long stockId, Long categoryId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> getStockProduct(long id) {
		
		return null;
				//stockRepository.getStockProductById(id);
	}

	@Override
	public List<Stock> getStockX(Stock stock) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		return (List<Stock>) stockRepository.findAll(Example.of(stock, matcher));
		
	}

	@Override
	public void affecterSupplierToStock(long stockId, long productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desaffecterSupplierToStock(long stockId, long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stock> getAllStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock getStockbyId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getListSupplierByStock(long stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getListStockBySupplier(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock getListStockByAdress(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float profitabilityOfstock(long stockId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
