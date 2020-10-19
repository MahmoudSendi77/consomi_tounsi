package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Brand;

public interface IBrandService {

	public long addBrand(Brand brand);
	public long updateBrand(Brand brand);
	public long deleteBrand(Brand brand);
	
	public List<Brand> getListBrand();
	public List<Brand> getListBrandForCategory(String category);
	public List<Brand> getListBrandForSubCategory(String subCategory);
	
	public List<String> getListBrandsNames();
	public Brand getBrandByName(String name);
	
	
}
