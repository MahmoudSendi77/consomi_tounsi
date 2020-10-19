package tn.esprit.spring.repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.TypeCommand;



@Repository
public interface CommandRepository extends JpaRepository<Command, Long>{

	@Query(value="select * from command c inner join users u on c.user_id=u.id where c.command_type = :type ", nativeQuery = true)
	public List<Command> findCommandByType(@Param("type") String type);
	
	@Query(value="select * from command c "
			+ "inner join command_produits command_produits on  command_produits.commands_id = c.id "
			+ "inner join product p on p.id = command_produits.produits_id "
			+ " inner join users u on  c.user_id=u.id "
			+ "where command_produits.produits_id = :idProduct ", nativeQuery = true)
	List<Command> findAllByProduct(@Param("idProduct") Long idProduct);
	

	@Query(value="select * from command c inner join users u on c.user_id=u.id where c.command_date BETWEEN :minDate AND :maxDate ", nativeQuery = true)
	List<Command> findAllByDate(@Param("minDate") LocalDate minDate ,@Param("maxDate")LocalDate maxDate);
	
	
	
	//recherche multi critére
	
	@Query(value="select * from command c inner join users u on c.user_id=u.id where c.command_type = :type "
			+ "AND c.montantttc BETWEEN :minmontantTTC AND :maxmontantTTC AND "
			+ "c.montantht BETWEEN :minmontantHT AND :maxmontantHT AND "
			+ "c.command_etat = :commandEtat AND "
			+ "c.command_date BETWEEN :minDate AND :maxDate ", nativeQuery = true)
	List<Command> filtreCommand(@Param("minDate") Date minDate ,@Param("maxDate")Date maxDate
			,@Param("minmontantTTC")float minmontantTTC,@Param("maxmontantTTC")float maxmontantTTC
			,@Param("minmontantHT")float minmontantHT,@Param("maxmontantHT")float maxmontantHT
			,@Param("type")String type,@Param("commandEtat")Boolean commandEtat);
	
	
	/*
	 select  ca.category_name from category ca "
			+ " inner join product p on p.category_id = ca.id  "
			+ " inner join  product_commands on p.id = product_commands.produits_id "
			//+ "inner join command c on c.id = product_commands.commands_id"
			+ " inner join   users u on u.id = c.user_id
	  */
}
