package tn.esprit.spring.control;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import tn.esprit.spring.entities.Image;
import tn.esprit.spring.service.IImageService;
import tn.esprit.spring.service.IProductService;


@RestController
@RequestMapping("/image")

//@Controller(value = "imageController")
//@ELBeanName(value = "imageController")
public class ImageControllerImpl {
	@Autowired
	IImageService imageService;
	@Autowired
	IProductService productService;
	
//	private Part image;

	
//	public String uploadFile() {
//		String name="";
//		String message = "";
//		for (int i = 0; i < files.length; i++) {
//			MultipartFile file = files[i];
//			 name = file.getOriginalFilename();
//			try {
//				byte[] bytes = file.getBytes();
//				File dir = new File(System.getProperty("user.dir")+"/uploads"+ File.separator + "product" + File.separator + type);
//				if (!dir.exists())
//					dir.mkdirs();
//				// Create the file on server
//				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//				stream.write(bytes);
//				stream.close();			
//				long imageId=imageService.addImage(new Image(type,file.getOriginalFilename()));
//				imageService.affectImageToProduct(imageId, productId);				
//				message = message + "You successfully uploaded file=" + name + "";
//			} catch (Exception e) {
//				return "You failed to upload " + name + " => " + e.getMessage();
//			}
//		}
//		return message;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/uploadProductFile")
	@ResponseBody
	public String uploadProductFile(@RequestParam("type") String type, @RequestParam("id") Long productId,@RequestParam("file") MultipartFile[] files) {
		return imageService.uploadProductFile(type, productId, files);
	}
	
	@PostMapping("/uploadAisleFile")
	@ResponseBody
	public String uploadAisleFile(@RequestParam("type") String type, @RequestParam("id") Long id,@RequestParam("file") MultipartFile[] files) {
		
		return imageService.uploadAisleFile(type, id, files);
	}
	
	@PostMapping("/uploadBrandFile")
	@ResponseBody
	public String uploadBrandFile(@RequestParam("type") String type, @RequestParam("id") Long id,@RequestParam("file") MultipartFile[] files) {
	
		return imageService.uploadBrandFile(type, id, files);
	}
	
	@PostMapping("/uploadAdsFile")
	@ResponseBody
	public String uploadAdsFile(@RequestParam("type") String type, @RequestParam("id") Long id,@RequestParam("file") MultipartFile[] files) {
		
		return imageService.uploadAdsFile(type, id, files);
	}
	
	@PostMapping("/uploadBarCodeFile")
	@ResponseBody
	public String uploadBarCodeFile( @RequestParam("id") Long id,@RequestParam("file") MultipartFile[] files) {
		
		return imageService.uploadBarCodeFile(id, files);
	}
		
	
}
