package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Ads;
import tn.esprit.spring.repository.AdsRepository;

@Service
public class AdsServiceImpl implements IAdsService {

	@Autowired
	AdsRepository adsRepository;
	
	@Override
	public long addAds(Ads a) {
		adsRepository.save(a);
		return a.getId();
	}

	@Override
	public long updateAds(Ads a) {
		if(adsRepository.findById(a.getId()).get()!=null){
			adsRepository.save(a);
			return a.getId();
		}
		return 0;
	}

	@Override
	public long deleteAds(long id) {
		Ads ads =adsRepository.findById(id).get();
		if(ads!=null){
			adsRepository.delete(ads);
			return 0;
		}
		return -1;
	}
	
	public String getTargetGender(String adsDescription){
		String gender = adsDescription.contains("for man ") ? "man" :
			adsDescription.contains("for kids ") ? "kids" :
				adsDescription.contains("for woman ") ? "woman" : "all";
		return gender;
	}

	@Override
	public List<Ads> getAllAds() {
		// TODO Auto-generated method stub
		return adsRepository.findAll();
	}

	@Override
	public Ads getAdsById(long idAds) {
		// TODO Auto-generated method stub
		return adsRepository.findById(idAds).get();
	}

	@Override
	public List<Ads> getAdsOfProduct(long idProduct) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Long> getTargetedByGender(Ads ads) {
//		String gender =getTargetGender(ads.getDescription());
//		
//		return adsRepository.listTargetedByGender(gender);
//	}
	
	

}
