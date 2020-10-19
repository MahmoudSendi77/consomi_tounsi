package tn.esprit.spring.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Jackpot;

@Repository
public interface JackpotRepository extends JpaRepository<Jackpot, Long>  {

	
//	 @Query
//	 (value = "SELECT * from jackpot j join jackpot_picture jp on jp.jackpot_id=j.picture_id", nativeQuery = true)
//	    int countAllDonatedSacks();
//	
//	 
//	 
//	 @Query
//	 (value = " SELECT Max(id) , 'image', titre , max_value from jackpot join jackpot_picture on jackpot_id=picture_id" )
//	 public List <Jackpot>lastimage();
}