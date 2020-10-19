package tn.esprit.spring.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.AisleRepository;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.ProductStockRepository;
import tn.esprit.spring.repository.StockRepository;

public class StatisticProductService implements IStatisticProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AisleRepository aisleRepository;
	
	@Autowired
	StockRepository stockRepository;
	@Autowired
	IProductService productService;
	
	@Autowired
	ProductStockRepository productStockRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public Map<Product, Long> quantityToReCommandByProduct(String productName) {
		Map<Product, Long> map =new HashedMap();
		List<Product> listProducts=productRepository.findByName(productName);
		Date d = new Date();
		long dd = d.getTime() - 30 ;
		Date d2 =new Date(dd);
		
		for(Product p :listProducts){
			
		//	productRepository.countSellByReference(p.getName(),d,  d2);
		}
		return null;
	}

	@Override
	public Map<Product, Long> quantityToReCommandByProductAndSubCategory(String subCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Product, Long> quantityToReCommandByProductAndBrand(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Product, Long> quantityToReCommandBySingleProduct(String barCode) {
		Map<Product, Long> map =new HashedMap();
		List<Product> listProducts=productRepository.findByReference(barCode);
			return null;
	}

}
