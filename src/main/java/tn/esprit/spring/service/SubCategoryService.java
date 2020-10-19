package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.SubCategory;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.SubCategoryRepository;

@Controller
public class SubCategoryService implements ISubCategoryService {

	@Autowired
	SubCategoryRepository subCategoryRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public long addSubCategory(SubCategory subCategory) {
		subCategoryRepository.save(subCategory);
		return subCategory.getId();
	}

	@Override
	public long updateSubCategory(SubCategory subCategory) {
			
		subCategoryRepository.save(subCategory);
		return subCategory.getId();
	}

	@Override
	public long deleteSubCategory(SubCategory subCategory) {
		try {
			subCategoryRepository.delete(subCategory);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<SubCategory> getAllSubCategory() {
		
		return subCategoryRepository.findAll();
	}

	@Override
	public List<SubCategory> getSubCategoryByCategory(String categoryName) {
		
		return null;
	}

	@Override
	public SubCategory getSubCategoryById(long id) {
		// TODO Auto-generated method stub
		return subCategoryRepository.findById(id).get();
	}

	@Override
	public SubCategory getSubCategoryByName(String subCategory) {
		
		return subCategoryRepository.getSubCategoryByName(subCategory);
	}

	@Override
	public long affectSubCategoryToCategory(long categoryId, long subCategoryId) {
		try {
			
		
		SubCategory subCategory=subCategoryRepository.findById(subCategoryId).get();
		Category category =categoryRepository.findById(categoryId).get();
		List<SubCategory> listSubCat=new ArrayList<SubCategory>();
		if(category.getSubCategory()==null){
		listSubCat.add(subCategory);}
		else{
			listSubCat=category.getSubCategory();
			listSubCat.add(subCategory);
		}
			category.setSubCategory(listSubCat);
			
			categoryRepository.save(category);
		
		return 0;
		} catch (Exception e) {
			return -1;
		}
	}

}
