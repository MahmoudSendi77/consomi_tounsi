package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.repository.CategoryRepository;

@Controller
public class CategoryService implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public long addCategory(Category category) {
		categoryRepository.save(category);
		return category.getId();
	}

	@Override
	public long updateCategory(Category category) {
		
		if(category.getId()!=null && categoryRepository.findById(category.getId()).get()!=null)
		{categoryRepository.save(category);
		return category.getId();
		}else return 0;
	}

	@Override
	public long deleteCategory(Category category) {
		try {
			categoryRepository.delete(category);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
		
	}

	@Override
	public List<Category> getAllCategory() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(long id) {
		
		return categoryRepository.findById(id).get();
	}

	@Override
	public Category getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.getCategoryByName(name);
	}

}
