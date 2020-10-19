package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Ads;

public interface IAdsService {

	public	long addAds(Ads a);
	 public long updateAds(Ads a);
	 public long deleteAds(long id);
	 
	 public List<Ads> getAllAds();
	 public Ads getAdsById(long idAds);
	 public List<Ads> getAdsOfProduct(long idProduct);
	// public List<Long> getTargetedByGender(Ads ads);
}
