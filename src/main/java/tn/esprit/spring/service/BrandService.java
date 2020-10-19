package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Brand;
import tn.esprit.spring.repository.BrandRepository;

@Service
public class BrandService implements IBrandService {

	@Autowired
	BrandRepository brandRepository;
	@Override
	public long addBrand(Brand brand) {
		brandRepository.save(brand);
		return brand.getId();
	}

	@Override
	public long updateBrand(Brand brand) {
		if(brand.getId()!=null)
		brandRepository.save(brand);
		return brand.getId();
	}

	@Override
	public long deleteBrand(Brand brand) {
	try {
		brandRepository.delete(brand);
		return brand.getId();
	} catch (Exception e) {
	return 0;	
	}
		
	}

	@Override
	public List<Brand> getListBrand() {
		
		return brandRepository.findAll();
	}

	@Override
	public List<Brand> getListBrandForCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Brand> getListBrandForSubCategory(String subCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getListBrandsNames() {
		
		return brandRepository.getListBrandsName();
	}

	@Override
	public Brand getBrandByName(String name) {
		// TODO Auto-generated method stub
		return brandRepository.getBrandByName(name);
	}

}
