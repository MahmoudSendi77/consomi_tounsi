package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ProductStock;
import tn.esprit.spring.entities.ProductStockPK;
@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
	List<ProductStock> findByProductStockPK(ProductStockPK productStockPK);
}
