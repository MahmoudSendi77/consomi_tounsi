package tn.esprit.spring.service;

import java.util.Map;

import tn.esprit.spring.entities.Product;

public interface IStatisticProductService {

	public Map<Product,Long> quantityToReCommandByProduct(String productName);//product with same name and sub category
	public Map<Product,Long> quantityToReCommandBySingleProduct(String productName);//by single product deference color or size 
	public Map<Product,Long> quantityToReCommandByProductAndSubCategory(String category);//all product of same Sub Category
	public Map<Product,Long> quantityToReCommandByProductAndBrand(String brand);//all product of same Sub Category
	
}
