package tn.esprit.spring.service;

import java.sql.Date;
import java.util.List;

import tn.esprit.spring.entities.PromosMagazin;

public interface IPromosMagazinService {
	
	public long addPromos(PromosMagazin p);
	public long updatePromos(PromosMagazin p);
	public long deletePromos(long id);
	
	public PromosMagazin getPromosById(long id);
	public List<PromosMagazin> getAllPromos();
	public List<PromosMagazin> getCurrentPromos();
	public List<PromosMagazin> getPromosByDate(Date date1,Date date2);
	
	
	
	

}
