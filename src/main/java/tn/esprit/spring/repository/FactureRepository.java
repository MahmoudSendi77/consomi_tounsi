package tn.esprit.spring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.TypeCommand;




@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

	//serch facture if it is between data 1 and data2
	@Query(value="select * from Facture fact inner join command c on fact.command_id=c.id where fact.factureDate BETWEEN :creationDateTime AND :finDateTime", nativeQuery = true)
	List<Facture> findAllByPublicationTimeBetween(@Param("creationDateTime") LocalDate publicationTimeStart,@Param("finDateTime") LocalDate finDateTime);

	//serch facture by type de facture
		
		@Query(value="select * from Facture fact "
				+ "inner join  command c on fact.command_id=c.id "
				+ " inner join users u on  c.user_id=u.id "
				+ "where c.command_type = :type ", nativeQuery = true)
		List<Facture> findAllByTypeFacture(@Param("type") String type);
	
	/*@Query(value = "select  ca.category_name from category ca "
			+ " inner join product p on p.category_id = ca.id  "
			+ " inner join  product_commands on p.id = product_commands.produits_id "
			+ "inner join command c on c.id = product_commands.commands_id"
			+ " inner join   users u on u.id = c.user_id where datediff(  CURDATE(),u.datenaissance)/365  BETWEEN :age-2 AND :age+2  AND c.command_date BETWEEN :d AND :d1 ", nativeQuery = true)
	public List<String> countySize(@Param("age") int age ,@Param("d") Date d,@Param("d1") Date d1 );*/
	
	//serch facture by name user
		@Query(value="select * from Facture fact "
				+ "inner join  command c on fact.command_id=c.id "
				+ " inner join users u on  c.user_id=u.id "
				+ "where u.name = :nameUser ", nativeQuery = true)
		List<Facture> findFactureByUser(@Param("nameUser") String nameUser);

	
	
}
