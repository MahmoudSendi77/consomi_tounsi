package tn.esprit.spring.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Ads;
import tn.esprit.spring.service.IAdsService;
import tn.esprit.spring.service.IImageService;



@Controller(value = "adsController")
@ELBeanName(value = "adsController")
@Join(path = "/admin/manageAds", to = "/pages/adminSide/ads/manageAds.jsf")
public class AdsController {

	@Autowired
	IAdsService adsService;
	
	@Autowired
	IImageService imageService;
	
	private Ads ads=new Ads();	
	private Ads selectedAds=new Ads();
	
	private UploadedFile adsFile;
	private Long adsFileId;
	

	private List<Ads> listAds=new ArrayList<Ads>();
	

	private String date1;
	private String date2;
public String getDate1() {
		
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public Ads getSelectedAds() {
		return selectedAds;
	}
	public void setSelectedAds(Ads selectedAds) {
		this.selectedAds = selectedAds;
	}
	public List<Ads> getListAds() {
		System.err.println("list ads");
		listAds=adsService.getAllAds();
		System.err.println("list ads"+listAds);
		return listAds;
	}
	public void setListAds(List<Ads> listAds) {
		this.listAds = listAds;
	}
	public Ads getAds() {
		return ads;
	}
	public void setAds(Ads ads) {
		this.ads = ads;
	}
	public UploadedFile getAdsFile() {
		return adsFile;
	}
	public void setAdsFile(UploadedFile adsFile) {
		this.adsFile = adsFile;
	}
	public void handleBand(FileUploadEvent event) {
		System.err.println("asd file uploaded");
		adsFile = event.getFile();
		if(ads.getType()!=null)
		adsFileId=imageService.uploadFile("ads", ads.getType(), adsFile);
	}
	public String addTheAds(){
		System.err.println(ads);
		if(date1!=null && date1!=""&& date2!=null && date2!=""){
			ads.setBeginDate(new Date(Date.parse(date1)));
			
			ads.setEndDate(new Date(Date.parse(date2)));}
		long id =adsService.addAds(ads);
		System.out.println("idddddddddddddd= "+id);
		if(adsFile!=null)
		imageService.affectImageToAds(adsFileId, id);
		ads=new Ads();
		adsFile=null;
		adsFileId=null;
		date1=date2=null;
		NavigateTo n=new NavigateTo();
		return n.adminHomeAds();
	}
	public List<Ads> getAllAds() {
		return adsService.getAllAds();
	}
	
	
	
	public Ads getAdsById(@PathVariable("id") Long idAds) {
		return adsService.getAdsById(idAds);
	}
	
	public void handleAdsFile(FileUploadEvent event) {
	System.err.println("adsFile");
	adsFile=event.getFile();
	adsFileId=imageService.uploadFile("ads","image" , adsFile);
	}
}
