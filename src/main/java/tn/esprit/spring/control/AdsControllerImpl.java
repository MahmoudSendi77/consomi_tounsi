package tn.esprit.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Ads;
import tn.esprit.spring.service.IAdsService;

@RestController
@RequestMapping("/product")
public class AdsControllerImpl {

	@Autowired
	IAdsService adsService;
	@GetMapping("/getAllAds")
	public List<Ads> getAllAds() {
		return adsService.getAllAds();
	}
	
	@GetMapping("/getAdsById/{id}")
	public Ads getAdsById(@PathVariable("id") Long idAds) {
		return adsService.getAdsById(idAds);
	}
	
}
