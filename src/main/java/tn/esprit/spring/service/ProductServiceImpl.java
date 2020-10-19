package tn.esprit.spring.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.ProductStock;
import tn.esprit.spring.entities.ProductStockPK;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.repository.AisleRepository;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.ProductStockRepository;
import tn.esprit.spring.repository.StockRepository;

@Service

public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AisleRepository aisleRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	ProductStockRepository productStockRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public long addProduct(Product p) {
		productRepository.save(p);
		return p.getId();
	}

	@Override
	public long updateProduct(Product p) {
		if (productRepository.findById(p.getId()).get() != null) {
			productRepository.save(p);
			return p.getId();
		}
		return 0;
	}

	@Override
	public boolean verifyProduct(String s) {
		System.out.println(s);
		if (s.trim().startsWith("619")) {
			return true;
		} else
			return false;
	}

	@Override
	public long deleteProduct(long id) {
		Product p = productRepository.findById(id).get();
		productRepository.delete(p);
		return id;
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductbyX(String x, String value) {
		return x.equals(
				"color") ? productRepository.findByColor(value)
						: x.equals(
								"brand") ? productRepository.getByBrand(value)
										: x.equals(
												"category")
														? productRepository.getByCategory(value)
																: x.equals(
																		"subCategory")
																				? productRepository.getBySubCategory(value)
														: x.equals("categoryAndBrand")
																? productRepository.getByCategoryAndBrand(
																		value.substring(0, value.indexOf("_")),
																		value.substring(value.indexOf("_") + 1,
																				value.length()))
																: x.equals("priceBetween")
																		? productRepository.getByPriceBetween(
																				Float.parseFloat(value
																						.substring(0,
																								value.indexOf("_"))),
																				Float.parseFloat(value.substring(
																						value.indexOf("_") + 1,
																						value.length())))
																		: x.equals("discountBetween")
																				? productRepository
																						.getByDiscountBetween(
																								Float.parseFloat(value
																										.substring(0,
																												value.indexOf(
																														"_"))),
																								Float.parseFloat(
																										value.substring(
																												value.indexOf(
																														"_")
																														+ 1,
																												value.length())))
																				: x.equals("discount")
																						? productRepository
																								.getByDiscount()
																						: x.equals("size")
																								? productRepository
																										.getBySize(
																												value)
																								: x.equals("reference")
																										? productRepository
																												.findByReference(
																														value)
																										: x.equals(
																												"name") ? productRepository
																														.findByName(
																																value)
																														: null;
	}
	
	@Override
	public Long getQuantityById(long value) {

		return productRepository.getProductQuantityById(value);
		
	}
	@Override
	public Long getQuantityByReference(String value) {

		return productRepository.getProductQuantityByReference(value);
		
	}
	
	@Override
	public List<List<Long>> getQuantityLessThan(long value) {

		return productRepository.getProductQuantityLessThan(value);
		
	}

	@Override	
	public int countSellByX(String x, String value, Date date1, Date date2) {	
		return x.equals(
				"color") ? productRepository.countSellByColorAndCategory(value, date1, date2)
						: x.equals(
								"brand") ? productRepository.countSellByBrand(value, date1, date2)
										: x.equals("category")
												? productRepository.countSellByCategory(value, date1,
														date2)
												: x.equals("categoryAndBrand")
														? productRepository.countSellByCategoryAndBrand(
																value.substring(0, value.indexOf(",")),
																value.substring(value.indexOf(",") + 1, value.length()),
																date1, date2)
														: x.equals("sizeAndColor")
																? productRepository.countSellBySizeAndColor(
																		value.substring(0, value.indexOf(",")),
																		value.substring(value.indexOf(",") + 1,
																				value.length()),
																		date1, date2)
																: x.equals("referenceAndColor") ? productRepository
																		.countSellByReferenceAndColor(
																				value.substring(0, value.indexOf(",")),
																				value.substring(value.indexOf(",") + 1,
																						value.length()),
																				date1, date2)
																		: x.equals("priceBetween")
																				? productRepository
																						.countSellByPriceBetween(
																								Float.parseFloat(value
																										.substring(0,
																												value.indexOf(
																														","))),
																								Float.parseFloat(
																										value.substring(
																												value.indexOf(
																														",")
																														+ 1,
																												value.length())),
																								date1, date2)
																				: x.equals("discountBetween")
																						? productRepository
																								.countSellByDiscountBetween(
																										Float.parseFloat(
																												value.substring(
																														0,
																														value.indexOf(
																																","))),
																										Float.parseFloat(
																												value.substring(
																														value.indexOf(
																																",")
																																+ 1,
																														value.length())),
																										date1, date2)
																						: x.equals("size")
																								? productRepository
																										.countSellBySize(
																												value,
																												date1,
																												date2)
																								: x.equals("reference")
																										? productRepository
																												.countSellByReference(
																														value,
																														date1,
																														date2)
																										: x.equals(
																												"name") ? productRepository
																														.countSellByName(
																																value,
																																date1,
																																date2)
																														: 0;

	}

	@Override
	public Product getProductById(long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> searchProduct(String search) {

		return productRepository.search(search);
	}

	@Override
	public List<Product> getProductByRef(String ref) {

		return productRepository.findByReference(ref);
	}

	@Transactional
	public void affecterProductToAisle(long aisleId, long productId) {
		Aisle aisle = aisleRepository.findById(aisleId).get();
		Product product = productRepository.findById(productId).get();

		product.setAisle(aisle);
		productRepository.save(product);

	}

	@Transactional
	public void desaffecterProductToAisle(long aisleId, long productId) {
		Product product = productRepository.findById(productId).get();

		product.setAisle(null);
		productRepository.save(product);
	}
	
	
	public String productStockExist(long stockId, long productId){
		
	List<ProductStock> lps=	productStockRepository.findByProductStockPK(new ProductStockPK(productId, stockId));
	if(lps==null)		{
		return "notExist";}
	else {if(lps.get(0).getQuantity()==0){
		return "quantity";
	}
	}
	return "no" ;
	}

	@Transactional
	public String affecterProductToStock(long stockId, long productId, long quantity, float unitPrice,Date fabricationDate, Date expirationDate ) {
		
		Stock stock = stockRepository.findById(stockId).get();
		Product product = productRepository.findById(productId).get();
		ProductStock productStock = new ProductStock(new ProductStockPK(productId, stockId), product, stock, quantity,
				unitPrice,fabricationDate,expirationDate);
		productStockRepository.save(productStock);
		if (stock.getStocks() == null) {
			List<ProductStock> products = new ArrayList<>();
			products.add(productStock);
			stock.setStocks(products);
			product.setProducts(products);
			productRepository.save(product);
			stockRepository.save(stock);
			return "new affectation ";
		} else {
			if(!stock.getStocks().contains(productStock)){
			stock.getStocks().add(productStock);
			stockRepository.save(stock);
			return "new affectation ";
//			if(stock.getStocks().)
			}
		}
		return "new affectation ";
	}
	
	
	@Transactional
	public String affecterProductToStock(long stockId, long productId, long quantity, float unitPrice ) {
		
		Stock stock = stockRepository.findById(stockId).get();
		Product product = productRepository.findById(productId).get();
		ProductStock productStock = new ProductStock(new ProductStockPK(productId, stockId), product, stock, quantity,
				unitPrice);
		productStockRepository.save(productStock);
		if (stock.getStocks() == null) {
			List<ProductStock> products = new ArrayList<>();
			products.add(productStock);
			stock.setStocks(products);
			product.setProducts(products);
			productRepository.save(product);
			stockRepository.save(stock);
			return "new affectation ";
		} else {
			if(!stock.getStocks().contains(productStock)){
			stock.getStocks().add(productStock);
			stockRepository.save(stock);
			return "new affectation ";
//			if(stock.getStocks().)
			}
		}
		return "new affectation ";
	}
	
	
	
	@Transactional
	public void desaffecterProductToStock(long stockId, long productId) {
		Stock stock = stockRepository.findById(stockId).get();
		for (int i = 0; i < stock.getStocks().size(); i++) {
			if (stock.getStocks().get(i).getProduct().getId() == productId) {
				stock.getStocks().remove(i);
				stockRepository.save(stock);
				break;
			}
		}
	}

	// @Scheduled( cron = "0 0 * * * *")/every day at 00
	//@Scheduled(fixedDelay = 1000000)
	public void checkDateValiditer() {
		List<Product> products = productRepository.getExpiredProduct();
		for (int i = 0; i < products.size(); i++) {
			System.out.println(
					"the product   " + products.get(i).getName() + "   have been expired ..  Deleted From DB ");
			// deleteProduct(products.get(i).getId());
		}
	}

	@Override
	public long populateAisle(Long idAisle, List<Long> idProducts) {
		for (int i = 0; i < idProducts.size(); i++) {
			affecterProductToAisle(idAisle, idProducts.get(i));
		}
		return 0;
	}

	@Override

	public List<Product> filterSearch(Product product) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		return (List<Product>) productRepository.findAll(Example.of(product, matcher));

	}

	@Override
	public List<List<Long>> getProductOf(String value) {
		
		return productRepository.getProductOfDay();
	}

	@Override
	public List<List<Long>> getCategoryOf(String value) {
		return null;
		// return value.equals("year")? productRepository.getProductOfYear() :
		// value.equals("month")? productRepository.getProductOfMonth() :
		// value.equals("day")? productRepository.getProductOfDay(): null;
	}

	@Override
	public Map<Long, Product> getProductOfYear() {
		// TODO Auto-generated method stub
		return toMapp(productRepository.getProductOfYear());
	}
	
	public Map<Long, Product> toMapp(List<List<Long>> l){
		System.err.println("list ll ="+l);
		Map<Long, Product> map = new HashMap<>();
		for(int i=0;i<l.size();i++){
		Product product = productRepository.findById(l.get(i).get(1)).get();
		System.out.println("getProductOfYear p"+product);
		System.out.println("getProductOfYear q"+l.get(i).get(0)	);
		map.put(l.get(i).get(0), product);
		}
		System.err.println("map=   " +map);
		return map;
	}

	@Override
	public Map<Long, Product> getProductOfMonth() {
		List<List<Long>> l = productRepository.getProductOfMonth();
		
		return toMapp(l);
	}

	@Override
	public void updateAffecteProductToStock(long productId, long quantity, float unitPrice, Date fabricationDate,
			Date expirationDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> getAllCategory() {
		
		return categoryRepository.findAll();
	}

	@Override
	public List<Product> searchProductByName(String value) {
		// TODO Auto-generated method stub
		return productRepository.findByNameContaining(value);
	}

	@Override
	public List<Product> searchProductByCategory(String value) {
		
		return productRepository.searchProductByCategory(value);
	}

	@Override
	public List<Product> searchProductByBrand(String value) {
		
		return productRepository.searchProductByBrand(value);
	}

	@Override
	public List<Product> getNotAffectedProductToAisle() {
		
		return productRepository.getNotAffectedProductToAisle();
	}

	@Override
	public List<Product> getProductByAisleId(long id) {
		
		return productRepository.getProductByAisleId(id);
	}

//	@Override
//	public float getProductQuantity(long productId) {
//		
//		return productRepository.getProductQuantity(productId);
//	}

//	@Override
//	public float getproductRentability(String reference,Date date1) {
//
//	}
//
//	@Override
//	public float getCategoryRentability(String categoryName) {
//
//	}
//
//	@Override
//	public float getCategoryBrandRentability(String categoryName, String brandName) {
//
//	}

//	@Override
//	public float getBrandRentability(String brandName) {
//
//	}

}
