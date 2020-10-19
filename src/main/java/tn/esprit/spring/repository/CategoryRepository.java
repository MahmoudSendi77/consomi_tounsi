package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

	@Query("select c from Category c where c.categoryName=:name ")
	public Category getCategoryByName(@Param("name") String name);
}
