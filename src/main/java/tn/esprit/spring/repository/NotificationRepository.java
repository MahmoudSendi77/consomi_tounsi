package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.ProductNotif;



@Repository
public interface NotificationRepository  extends JpaRepository<ProductNotif, Long> {

	List<ProductNotif> findByUsers(long id);
	List<ProductNotif> findByUsersAndSeen(long id,boolean b);
	
	@Query(value = "select p.* from product_notif p join product_notif_users pu on p.id = pu.product_notif_id where  pu.users_id=:id", nativeQuery = true)
	public List<ProductNotif> getNotificationByUser(@Param("id") long id);
	
}
