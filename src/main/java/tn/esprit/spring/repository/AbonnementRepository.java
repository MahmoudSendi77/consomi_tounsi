package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Abonnement;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {

	List<Abonnement> findByType(String type);

	// @Query(value ="select * from abonnement where 1", nativeQuery = true)
	// List<Abonnement> findMealByType();

	@Query("SELECT type FROM Abonnement")
	public List<String> types();

	@Query(value = "select  a.type from abonnement a "
			+ " inner join users u on a.id = u.abonnement_id  ", nativeQuery = true)
	public List<String> nbruserpartype();
	
	@Query(value = "select  a.type , count(a.id) from abonnement a "
			+ " inner join users u on a.id = u.abonnement_id  group by a.type ", nativeQuery = true)
	public List<List<String>> countusers();
	
	
	
	
	
	
	
//	
	@Query(value ="SELECT *  FROM abonnement a  ORDER BY a.pointsfidelite ASC    "   , nativeQuery = true)
	public List<Abonnement> abonnementtrier();
//	
//	

}
