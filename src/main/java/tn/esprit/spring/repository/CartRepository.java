package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	/*@Modifying
	@Query("DELETE FROM Cart s join Product c  WHERE c.id=:value")
	public void deleteByCart(@Param("value")Long idProd);*/
	
	
	/*@Query(value="Select sum(p.price) From cart_produits c join Product p on c.produits_id=p.id where c.carts_id=:cartId p.id IN :listProduit", nativeQuery = true)
	public Float getProductById(@Param("listProduit")List<Long> listProduit,@Param("cartId") Long cartId);
	*/
	
	@Query(value="Select p.* From cart_produits c join Product p on c.produits_id=p.id where  p.id IN :listProduit", nativeQuery = true)
	public List<Product> getProductById(@Param("listProduit")List<Long> listProduit);
	
	/*@Query(value="Select p.id,count() From cart_produits c join Product p on c.produits_id=p.id where  p.id IN :listProduit", nativeQuery = true)
	public List<Product> getProductAndQuantity(@Param("listProduit")List<Long> listProduit);*/
	
	@Query(value="select * from Cart c inner join users u on c.user_id=u.id where c.user_id = :idUser ", nativeQuery = true)
	public Cart SearchCartByUser(@Param("idUser") Long idUser );
	
	
}
