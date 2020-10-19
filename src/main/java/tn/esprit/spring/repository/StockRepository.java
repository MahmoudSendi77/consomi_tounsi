package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

	
//	@Query("select p from  Product p join Stock s on p.stocks = s.id  where s.id = :id ")
//	 public List<Product> getStockProductById(@Param("id")Long id);
//	 @Query("select p.id, sum(p.quantity) from  Product p join Stock s on p.stocks = s.id  group by p.id  where s.id = :id ")
//	 public List<List<Long>> getStockProductQunatity(@Param("id")Long id);
//	
}
