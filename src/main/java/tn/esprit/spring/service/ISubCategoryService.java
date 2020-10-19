package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.SubCategory;

public interface ISubCategoryService {

	public long addSubCategory(SubCategory subCategory);
	public long updateSubCategory(SubCategory subCategory);
	public long deleteSubCategory(SubCategory subCategory);
	
	public List<SubCategory> getAllSubCategory();
	public List<SubCategory> getSubCategoryByCategory(String categoryName);
	public SubCategory getSubCategoryById(long id);
	public SubCategory getSubCategoryByName(String subCategory);
	public long affectSubCategoryToCategory(long categoryId,long subCategoryId);
}
