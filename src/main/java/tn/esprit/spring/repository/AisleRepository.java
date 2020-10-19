package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.Product;


@Repository
public interface AisleRepository extends JpaRepository<Aisle, Long>{

	@Query("select p from  Product p join Aisle a on p.aisle = a.id  where a.id = :id ")
	 public List<Product> getAisleProductById(@Param("id")Long id);
	
	@Query("select i from  Image i join Aisle a where i.id = :id ")
	 public List<Image> getListImageOfProductAisle(@Param("id") long id);
	
//	@Query(value="select count(p.id) as pp from Product p join Aisle a join Command c  group by a.id order by pp  ", nativeQuery = true)
//	 public List<Integer> search(@Param("value")String value);
//	
//	@Query(value="select sum(p.price) as gain, a.id from Product p join  category c  on p.category_id=c.id  join Brand b on b.id=p.brand_id where c.category_name =:category  and b.name =:brand ", nativeQuery = true)
//	 public List<String> getByCategoryAndBrand(@Param("category")String category,@Param("brand")String brand);	
//	
	//
	@Query(value="select  sum(p.price) as gain  from Product p join command_produits c on p.id = c.produits_id join Aisle a on p.aisle_id=a.id  where  a.id=:aisleId ", nativeQuery = true)
	 public  Float  rentabilteAisle(@Param("aisleId")Long aisleId);
	
	@Query(value="select  a.id, sum(p.price) as gain  from Product p join command_produits c on p.id = c.produits_id  join Aisle a on p.aisle_id=a.id join Brand b on b.id=p.brand_id where  b.name =:brand group by a.id order by gain DESC ", nativeQuery = true)
	 public List< List<Float>>  countBrandRentabilteByAisle(@Param("brand")String brand);	
	
	@Query(value="select  p.reference, sum(p.price) as gain  from Product p join command_produits c on p.id = c.produits_id join Aisle a on p.aisle_id=a.id j where  p.reference =:ref and a.id=:id  group by p.reference order by gain DESC ", nativeQuery = true)
	 public List< List<Float>>  countProductRentabilteByAisle(@Param("ref")String ref,@Param("id")long id);	// list des produit de aisle avec somme prix
	
	@Query(value="select  b.id, sum(p.price) as gain  from Product p join command_produits c on p.id = c.produits_id  join Aisle a on p.aisle_id=a.id join Brand b on b.id=p.brand_id where a.id=:id group by b.id order by gain DESC ", nativeQuery = true)
	 public List< List<Float>>  countAisleRentabilteByBrand(@Param("id")long id);	//list of brand and rentabality in aisl
	
	@Query(value="select  b.id, sum(p.price) as gain  from Product p join command_produits c on p.id = c.produits_id  join Aisle a on p.aisle_id=a.id join Brand b on b.id=p.brand_id where a.id=:id group by b.id order by gain DESC ", nativeQuery = true)
	 public List< List<Float>>  countAisleRentabilteByCategory(@Param("id")long id);	//list of brand and rentabality in aisl
	
	
	@Query(value="select a.id, sum(p.price) as gain  from Product p join command_produits c on p.id = c.produits_id join Aisle a on p.aisle_id=a.id  where  a.id=p.aisle_id group by a.id order by gain DESC", nativeQuery = true)
	 public List< List<Float>> countAisleRentability();	
	
	@Query(value="select  a.*  from aisle_images i join Aisle a on i.aisle_id=a.id  where  a.id=:aisleId ", nativeQuery = true)
	 public  List<Image>  getListImageOfAisle(@Param("aisleId")Long aisleId);
	
	@Query(value="select  a.*  from aisle_images i join Aisle a on i.aisle_id=a.id  ", nativeQuery = true)
	 public  List<Image>  getListImageOfAllAisle();
	
}
