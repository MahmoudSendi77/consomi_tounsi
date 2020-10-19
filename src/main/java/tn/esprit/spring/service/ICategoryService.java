package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Category;

public interface ICategoryService {

	public long addCategory(Category category);
	public long updateCategory(Category category);
	public long deleteCategory(Category category);
	public List<Category> getAllCategory();
	public Category getCategoryById(long id);
	public Category getCategoryByName(String name);
	
}
