package tn.esprit.spring.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Image;
import org.primefaces.model.UploadedFile;

public interface IImageService {
	public	long addImage(Image image);
	 public long updateImage(List<Image> image);
	 public long deleteImage(long id);
	 
	 public List<Image> getAllImage();
	 public List<Image> getImagebyProduct(long productId);
	 public Image getImageById(long id);
	 
	 public long affectImageToProduct(Long imageId,Long productId);
	 public long desaffectImageToProduct(Long imageId,Long productId);
	 
	 public long affectImageToAisle(Long imageId,Long aisleId);
	 public long desaffectImageToAisle(Long imageId,Long aisleId);
	 
	 public long affectImageToBrand(Long imageId,Long brandId);
	 public long affectImageToCategory(Long imageId,Long categoryId);
	 public long affectImageToPromos(Long imageId,Long promosId);
	 public long affectImageToSubCategory(Long imageId,Long subCategoryId);
	 
	 public long desaffectImageToBrand(Long imageId,Long brandId);
	 
	 public long affectImageToAds(Long imageId,Long brandId);
	 public long desaffectImageToAds(Long imageId,Long brandId);
	 
	 //springmvc
	 public String uploadProductFile( String type, Long productId, MultipartFile[] files);
	 public String uploadAisleFile( String type,  Long id, MultipartFile[] files);
	 public String uploadBrandFile(String type,  Long id, MultipartFile[] files);
	 public String uploadAdsFile(@RequestParam("type") String type, @RequestParam("id") Long id,@RequestParam("file") MultipartFile[] files);
	 public String uploadBarCodeFile(Long id, MultipartFile[] files);
	
	 //jsf
	 public String verifyBarCode(org.primefaces.model.UploadedFile files);
	 public String uploadProductFile( String type, Long productId, List<UploadedFile> files);
	 public String uploadAisleFile( String type,  Long id, List<UploadedFile> files);
	 public String uploadBrandFile(String type,  Long id, List<UploadedFile> files);
	 public String uploadAdsFile(@RequestParam("type") String type, @RequestParam("id") Long id,@RequestParam("file") List<UploadedFile> files);
	 public String uploadBarCodeFile(Long id, List<UploadedFile> files);

	 public long uploadFile(String to, String type, UploadedFile files);
		

}
