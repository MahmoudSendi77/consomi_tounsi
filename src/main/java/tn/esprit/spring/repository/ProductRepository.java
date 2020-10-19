package tn.esprit.spring.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Size;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByName(String name);

	List<Product> findByColor(String color);

	List<Product> findByReference(String ref);

	List<Product> findBySize(Size size);

	// ca.category_name like \'%:value%\' or"
	// + " b.name like \'%:value%\' or

	List<Product> findByNameContaining(String name);

	@Query("select p from Product p where p.aisle is null  ")
	public List<Product> getNotAffectedProductToAisle();

	@Query(value = "select p from Product p where p.aisle =: id ")
	public List<Product> getProductByAisleId(@Param("id") Long id);

	// gestion finiancier mta3 les produits selon les produit

	@Query(value = "select p.* from Product p join Brand b on b.id=p.brand_id join Category ca on ca.id=p.category_id  where "
			+ " (ca.category_name like CONCAT('%',:value,'%')) or (b.name like CONCAT('%',:value,'%')) or  (p.name like CONCAT('%',:value,'%')) ", nativeQuery = true)
	public List<Product> search(@Param("value") String value);

	@Query(value = "select p.* from Product p join Brand b on b.id=p.brand_id join Category ca on ca.id=p.category_id  join category_sub_category sbc on sbc.category_id=ca.id join"
			+ "sub_category sub on sub.id=sbc.sub_category_id where "
			+ " (sub.name like CONCAT('%',:value,'%'))  ", nativeQuery = true)
	public List<Product> searchProductBySubCategory(@Param("value") String value);

	@Query(value = "select p.* from Product p join Brand b on b.id=p.brand_id join Category ca on ca.id=p.category_id  where "
			+ " (ca.category_name like CONCAT('%',:value,'%')) ", nativeQuery = true)
	public List<Product> searchProductByCategory(@Param("value") String value);

	@Query(value = "select p.* from Product p join Brand b on b.id=p.brand_id join Category ca on ca.id=p.category_id  where "
			+ "b.name like CONCAT('%',:value,'%')  ", nativeQuery = true)
	public List<Product> searchProductByBrand(@Param("value") String value);

	@Query(value = "select count(p.id) from Product p join  command_produits c on p.id = c.produits_id join Category ca on ca.id=p.category_id  join Brand b "
			+ " on b.id=p.brand_id "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) and ca.category_name =:category and b.name =:brand ", nativeQuery = true)
	public int countSellByCategoryAndBrand(@Param("category") String category, @Param("brand") String brand,
			@Param("date1") Date date1, @Param("date2") Date date2);

	// ---
	@Query(value = "select count(p.id) from Product p join command_produits c on p.id = c.produits_id "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) and  p.name =:value", nativeQuery = true)
	public int countSellByName(@Param("value") String value, @Param("date1") Date date1, @Param("date2") Date date2);

	@Query(value = "select count(p.id) from Product p join command_produits c on p.id = c.produits_id "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) and  p.color =:value ", nativeQuery = true)
	public int countSellByColorAndCategory(@Param("value") String value, @Param("date1") Date date1,
			@Param("date2") Date date2);

	@Query(value = "select count(p.id) from Product p join command_produits c on p.id = c.produits_id "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 )"
			+ " and  (p.price between :binf and :bsup) ", nativeQuery = true)
	public int countSellByPriceBetween(@Param("binf") float binf, @Param("bsup") float bsup, @Param("date1") Date date1,
			@Param("date2") Date date2);

	@Query(value = "select count(p.id) from Product p join command_produits c on p.id = c.produits_id "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) "
			+ "and  p.discount between :binf and :bsup ", nativeQuery = true)
	public int countSellByDiscountBetween(@Param("binf") float binf, @Param("bsup") float bsup,
			@Param("date1") Date date1, @Param("date2") Date date2);

	// -------

	@Query(value = "select count(p.id) from Product p join command_produits c on p.id = c.produits_id "
			+ " join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) "
			+ "and  p.discount between :binf and :bsup ", nativeQuery = true)
	public int countSellByDiscountBetweenByCategory(@Param("binf") float binf, @Param("bsup") float bsup,
			@Param("date1") Date date1, @Param("date2") Date date2);

	@Query(value = "select count(p.id) from Product p join command_produits c on p.id = c.produits_id "
			+ " join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) "
			+ "and p.size =:value", nativeQuery = true)
	public int countSellBySize(@Param("value") String value, @Param("date1") Date date1, @Param("date2") Date date2);

	@Query(value = "select count(p.id) from Product p join command_produits c on c.produits_id=p.id "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 )"
			+ "and p.size =:size and p.color =:color", nativeQuery = true)
	public int countSellBySizeAndColor(@Param("size") String size, @Param("color") String color,
			@Param("date1") Date date1, @Param("date2") Date date2);

	@Query(value = "select count(p.id) from Product p join command_produits c on c.produits_id=p.id "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 )"
			+ "and p.reference =:ref and p.color =:value ", nativeQuery = true)
	public int countSellByReferenceAndColor(@Param("ref") String ref, @Param("value") String value,
			@Param("date1") Date date1, @Param("date2") Date date2);

	@Query(value = "select count(p.id) from Product p join command_produits c on c.produits_id=p.id  "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) and"
			+ "and   p.reference =:value", nativeQuery = true)
	public int countSellByReference(@Param("value") String value, @Param("date1") Date date1,
			@Param("date2") Date date2);

	// --
	@Query(value = "select count(p.id) from Product p join  command_produits c on c.produits_id=p.id  join Category  ca   "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) and"
			+ "  ca.category_name =:category  ", nativeQuery = true)
	public int countSellByCategory(@Param("category") String category, @Param("date1") Date date1,
			@Param("date2") Date date2);

	@Query(value = "select count(p.id) from Product p join  command_produits c  on c.produits_id=p.id join Brand b  "
			+ "join Command com on com.id= c.commands_id where ( com.command_date BETWEEN :date1 and :date2 ) and"
			+ "  b.name =:brand ", nativeQuery = true)
	public int countSellByBrand(@Param("brand") String brand, @Param("date1") Date date1, @Param("date2") Date date2);

	// @Query("select p from Product p where p.discount>0")
	// public List<Product> productWithDiscount();
	//
	// @Query("select p from Product p where p.discount>:discount")
	// public List<Product> productWithMoreDiscount();

	// baddelhom blike bech maywallouch zeydin

	@Query(value = "select * from Product p  where  p.price between :binf and :bsup", nativeQuery = true)
	public List<Product> getByPriceBetween(@Param("binf") float binf, @Param("bsup") float bsup);

	@Query(value = "select p.* from Product p join  category c  on p.category_id=c.id  join Brand b on b.id=p.brand_id where c.category_name =:category  "
			+ "and b.name =:brand ", nativeQuery = true)
	public List<Product> getByCategoryAndBrand(@Param("category") String category, @Param("brand") String brand);

	@Query(value = "select * from  Product p where p.size=?", nativeQuery = true)
	public List<Product> getBySize(String size);

	@Query(value = "select p.* from Product p join Category c on p.category_id=c.id  where c.category_name = :category", nativeQuery = true)
	public List<Product> getByCategory(@Param("category") String category);

	@Query(value = "select p.* from Product p join Category ca on p.category_id=c.id join category_sub_category sbc on sbc.category_id=ca.id join"
			+ "sub_category sub on sub.id=sbc.sub_category_id where sub.name like  \'%:category%\'", nativeQuery = true)
	public List<Product> getBySubCategory(@Param("category") String subCategory);

	@Query(value = "select p.* from Product p join Brand b on b.id=p.brand_id where b.name like \'%:brand%\' ", nativeQuery = true)
	public List<Product> getByBrand(@Param("brand") String brand);

	@Query("select p from Product p where p.discount > 0")
	public List<Product> getByDiscount();

	@Query("select p from Product p where p.discount between :binf and :bsup")
	public List<Product> getByDiscountBetween(@Param("binf") float binf, @Param("bsup") float bsup);

	/// ---------------------

	@Query(value = "select  count(*) as sell , p.reference   from Product p join command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id join Category cat on p.category_id = cat.id"
			+ " where ca.command_date BETWEEN CURRENT_DATE() -30 and CURRENT_DATE() group by p.reference order by sell desc LIMIT 5 ", nativeQuery = true)
	public List<List<Long>> getProductOfMonth();

	@Query(value = "select  count(*) as sell , p.id ,cat.id   from Product p join command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id join Category cat on p.category_id=cat.id"
			+ " where ca.command_date BETWEEN CURRENT_DATE() -1 and CURRENT_DATE() group by p.id order by sell desc LIMIT 5 ", nativeQuery = true)
	public List<List<Long>> getProductOfDay();

	@Query(value = "select  count(*) as sell , p.id  from Product p join command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id "
			+ " where ca.command_date BETWEEN CURRENT_DATE() -365 and CURRENT_DATE() group by p.id order by sell desc LIMIT 5 ", nativeQuery = true)
	public List<List<Long>> getProductOfYear();

	@Query(value = "select   count(*) as sell ,cc.id  from Product p join Category cc on p.category_id=cc.id   "
			+ "join command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id where ca.command_date BETWEEN CURRENT_DATE() -30 and CURRENT_DATE()"
			+ " group by cc.id order by sell desc LIMIT 3 ", nativeQuery = true)
	public List<List<Long>> getCategoryOfMonth();

	@Query(value = "select count(*) as sell ,cc.id from Product p join Category cc on p.category_id=cc.id"
			+ " join command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id where ca.command_date BETWEEN CURRENT_DATE() -1 and CURRENT_DATE()"
			+ " group by cc.id order by sell desc LIMIT 5 ", nativeQuery = true)
	public List<List<Long>> getCategoryOfDay();

	@Query(value = "select count(*) as sell , cc.id from Product p join Category cc on p.category_id=cc.id join"
			+ " command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id where ca.command_date BETWEEN CURRENT_DATE() -365 and CURRENT_DATE()"
			+ " group by cc.id order by sell desc LIMIT 5 ", nativeQuery = true)
	public List<List<Long>> getCategoryOfYear();

	@Query(value = "select   count(*) sell ,b.id   from Product p join Brand b on b.id=p.brand_id join"
			+ " command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id where ca.command_date BETWEEN CURRENT_DATE() -30 and CURRENT_DATE()"
			+ " group by b.id order by sell desc LIMIT 3 ", nativeQuery = true)
	public List<List<Long>> getBrandOfMonth();

	@Query(value = "select count(*) as sell ,b.id from Product p join Brand b on b.id=p.brand_id join"
			+ " command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id where ca.command_date BETWEEN CURRENT_DATE() -1 and CURRENT_DATE() group "
			+ "by b.id order by sell desc LIMIT 5 ", nativeQuery = true)
	public List<List<Long>> getBrandOfDay();

	@Query(value = "select count(*) as sell , b.id from Product p join Brand b on b.id=p.brand_id join "
			+ "command_produits c on c.produits_id=p.id "
			+ "join Command ca on ca.id= c.commands_id where ca.command_date BETWEEN CURRENT_DATE() -365 and CURRENT_DATE()"
			+ " group by b.id order by sell desc LIMIT 5 ", nativeQuery = true)
	public List<List<Long>> getBrandOfYear();

	@Query(value = "select p.* from Product p join product_stock ps on p.id=ps.product_id  where ps.expiration_date < DATE_ADD(CURRENT_DATE, INTERVAL :value DAY) and ps.expiration_date > CURRENT_DATE   ", nativeQuery = true)
	public List<Product> getRedZonProduct(@Param("value") int value);

	@Query(value = "select p.* from Product p join product_stock ps on p.id=ps.product_id  where ps.expiration_date <= CURRENT_TIMESTAMP ", nativeQuery = true)
	public List<Product> getExpiredProduct();

	@Query(value = "select sum(ps.quantity) from  product_stock ps where ps.product_id=:productId ", nativeQuery = true) // l
	public Long getProductQuantityById(@Param("productId") long productId);// ken
																			// 3enna
																	// product_stock

	@Query(value = "select sum(ps.quantity) from product p join product_stock ps product_stock ps on p.id= ps.product_id where p.reference=:ref ", nativeQuery = true) // l
	public Long getProductQuantityByReference(@Param("ref") String ref);// ken
																		// 3enna
	
	@Query(value = "select p.reference ,sum(ps.quantity) as quantities from product p join   product_stock ps on p.id= ps.product_id where quantities<:value "
			+ "group by p.reference   ", nativeQuery = true) // l
	public List<List<Long>> getProductQuantityLessThan(@Param("value") long value);// ken
																					// 3enna
																			// product_stock

	/// ---------------------
	// @Query(value="select * from Product where ? ", nativeQuery = true)
	// public List<Product> filter(String filter);

	// @Modifying
	// @Transactional
	// @Query("UPDATE `product` SET
	// `brand`=:brand,`color`=:color,`dimention`=:dimention,"
	// +
	// "`discount`=[value-5],`name`=[value-6],`price`=[value-7],`reference`=[value-8],`size`=[value-9],"
	// + "`weight`=[value-10],`aisle_id`=[value-11],`category_id`=[value-12]
	// WHERE")

	// @PostMapping("{id}/vote/{optionId}/{iduser}")
	// public ResponseEntity<?> post(@PathVariable Long id, @PathVariable Long
	// optionId, HttpServletRequest request ,@PathVariable Long iduser) throws
	// Exception {
	// pollService.vote(id, optionId, iduser);
	// request.getRemoteAddr();
	// return ResponseEntity.ok().build();
	// }

}
