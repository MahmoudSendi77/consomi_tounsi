package tn.esprit.spring.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.PromosMagazin;

public interface PromosMagazinRepository extends JpaRepository<PromosMagazin, Long> {
	
	
	
	
	
	@Query("select p from PromosMagazin p where  (p.dateBegin >= :date1) and (p.dateEnd <= :date2) ")
	public List<PromosMagazin> getPromosByDate(@Param("date1") Date date1, @Param("date2") Date date2);
	
	@Query("select p from PromosMagazin p where  (p.dateBegin <= :today) and (p.dateEnd >= :today) ")
	public List<PromosMagazin> getCurrentPromos(@Param("today") Date today);
	
	
}
