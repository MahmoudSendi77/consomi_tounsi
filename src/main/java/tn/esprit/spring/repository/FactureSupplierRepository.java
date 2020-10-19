package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.FactureSupplier;
import tn.esprit.spring.entities.User;


@Repository
public interface FactureSupplierRepository extends JpaRepository<FactureSupplier, Long>  {

	 List<FactureSupplier> findByUser(Long user);
	 @Query(value="select  fq.* from Users u join facture_supplier fq on u.id = fq.user_id where u.id=:value", nativeQuery = true)
	 public  List<FactureSupplier> getFactureByUser(@Param("value") Long value);
	 
	 @Query(value="select sum(ps.unit_price * fq.products) from Product p join facture_supplier_products fq on p.id = fq.products_key"
	 		+ " join product_stock ps on p.id=ps.product_id where  fq.facture_supplier_id =:value", nativeQuery = true)
	 public Float countFacturePrice(@Param("value") Long value);
}
