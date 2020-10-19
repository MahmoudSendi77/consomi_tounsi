package tn.esprit.spring.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Don;
import tn.esprit.spring.entities.Product;

public interface DonRepository extends JpaRepository <Don,Long> {

	 @Query
	 (value = "SELECT sum(montantdon) from Don", nativeQuery = true)
	    int countAllDonatedSacks();
	 
	 @Query
	 (value = "SELECT sum(montantdon) from Don where  don.jackpot_id =:jackpotId ", nativeQuery = true)
	    int countAllDonatedByJackpot(@Param("jackpotId") Long jackpotId);
	 
	// nombre de participants dans un jackpot 
	 @Query(value = "SELECT count(user_id) from Don  where  jackpot_id=:idDon ", nativeQuery = true)
	 	 
	    int countParticipantByJackpot(@Param("idDon") Long idDon);
	 
	 
	 
//	 @Query(value = "SELECT sum(montantdon) , u.username from don inner join users u on don.jackpot_id =:jackpotId AND u.id= don.user_id Group By user_id  LIMIT 4 ", nativeQuery = true)
// 	 
//	    List<List<String>> countmontantUserID(@Param("jackpotId") Long jackpotId);
	 
	 @Query(value = "SELECT sum(montantdon) , descriptiondon from don where jackpot_id=:jackpotId Group By nom ORDER by sum(montantdon) DESC LIMIT 4", nativeQuery = true)
 
	    List<List<String>> countmontantUserID(@Param("jackpotId") Long jackpotId);
	 
	 
	 
	 
	 @Query(value = "SELECT count(montantdon) from Don Order By  user_id ", nativeQuery = true)
 	 
	    int countByUserID();	
	 @Query(value = "SELECT SUM(montantdon) from Don where jackpot_id=3  ", nativeQuery = true)
	 
	 float countDonByjackpot(@Param("idDon") Long idDon,@Param("jackpotId") Long jackpotId );
	 
	 //SELECT TOP 3 Donateur 
	 @Query(value = "SELECT sum(d.montantdon) as somme , d.user_id FROM Don d group by d.user_id  Order By somme desc LIMIT 3 ", nativeQuery = true)
	 List<List<Long>> topThreeDon();
	 
	 
	 @Query(value = "SELECT  distinct d.user_id FROM Don d where d.user_id is not null  ", nativeQuery = true)
	 List<Long> getListUser();
//	 @Query(value = "select  do.category_name from category ca "
//				+ " inner join product p on p.category_id = ca.id  "
//				+ " inner join  product_commands on p.id = product_commands.produits_id "
//				+ "inner join command c on c.id = product_commands.commands_id"
//				+ " inner join   users u on u.id = c.user_id where datediff(  CURDATE(),u.datenaissance)/365  BETWEEN :age-2 AND :age+2  AND c.command_date BETWEEN :d AND :d1 ", nativeQuery = true)
//		public List<String> countySize(@Param("age") int age ,@Param("d") Date d,@Param("d1") Date d1 );
	 
	 
	//@Query("SELECT count(*) FROM Employe")
    //public int countemp();
	 
	 
	 
	 // hna 7ajetna njibou les produit X mta3 don y elli type mta3ha produit 
	 // les donner 3anna id mta3 don 
	 
	// @Query(value="select p.* from Don d join Command co on co.don_id=d.id join command_produits c on c.commands_id = co.id join Product p  on p.id = c.produits_id where  d.id =:idDon", nativeQuery = true)
	 
	 @Query(value="select p.*  from Don  d join Command co on co.don_id=d.id join command_produits c on c.commands_id = co.id join Product p  on p.id = c.produits_id where  d.id =:idDon", nativeQuery = true)
	 public List<Product> getDonProduct(@Param("idDon") Long idDon);
	 
	 @Query(value="select p.* from Don d join Command co on co.don_id=d.id join command_produits c on c.commands_id = co.id join Product p  on p.id = c.produits_id where  d.id =1", nativeQuery = true)
	 public List<String> getProduct();
	 
	 
	 
	 
}
