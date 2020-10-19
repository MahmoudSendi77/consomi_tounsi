package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entities.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{

	@Query(value = "select sbc.* from Category ca  join category_sub_category sbc on sbc.category_id=ca.id join"
			+ "sub_category sub on sub.id=sbc.sub_category_id where ca.category_name = :category", nativeQuery = true)
	public List<SubCategory> getSubCategoryByCategory(@Param("category") String categoryName);

	@Query(value = "select sub.* from Sub_Category sub where sub.name= :subCategory ", nativeQuery = true)
	public SubCategory getSubCategoryByName(@Param("subCategory") String categoryName);

}
