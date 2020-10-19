package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Ads;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long>{

//	@Query(value="select u.id from Product p join command_produits c on p.id = c.produits_id join users u join Ads a on a.produit=p.id where a.id =:value", nativeQuery = true)
//	 public List<Long> targetedUserByProduct(@Param("value") String value);
//	
//	@Query(value="select u.id from Product p join command_produits c on p.id = c.produits_id join users u  where p.id =:value", nativeQuery = true)
//	 public List<Long> targetedUserByCategory(@Param("value") String value);
//	
//	@Query(value="select u.id from users u  where u.gender =:value", nativeQuery = true)
//	 public List<Long> listTargetedByGender(@Param("value") String value);
//	
//	
////	datediff(  CURDATE(),u.datenaissance)/365
//	
//	@Query(value="select  from users u  where u.gender =:value", nativeQuery = true)
//	 public Long age();
	
}
