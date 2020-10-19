package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	@Query("select b.name from Brand b ")
	public List<String> getListBrandsName();
	
	
	@Query("select b from Brand b where b.name=:name ")
	public Brand getBrandByName(@Param("name") String name);
	
}
