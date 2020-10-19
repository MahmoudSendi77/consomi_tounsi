package tn.esprit.spring.service;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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

import tn.esprit.spring.entities.Ads;
import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.Brand;
import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.PromosMagazin;
import tn.esprit.spring.entities.SubCategory;
import tn.esprit.spring.repository.AdsRepository;
import tn.esprit.spring.repository.AisleRepository;
import tn.esprit.spring.repository.BrandRepository;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.ImageRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.PromosMagazinRepository;
import tn.esprit.spring.repository.SubCategoryRepository;

@Service
public class ImageServiceImpl implements IImageService {

	@Autowired
	ImageRepository imageRepository;
	@Autowired
	AdsRepository adsRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	AisleRepository aisleRepository;
	@Autowired
	BrandRepository brandRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SubCategoryRepository subCategoryRepository;
	@Autowired
	PromosMagazinRepository promosRepository;
	
	@Override
	public long addImage(Image image) {
		imageRepository.save(image);
		return image.getId();
	}

	@Override
	public long updateImage(List<Image> p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteImage(long id) {
		
		return 0;
	}

	@Override
	public List<Image> getAllImage() {
		
		return imageRepository.findAll();
	}

	@Override
	public List<Image> getImagebyProduct(long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getImageById(long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id).get();
	}

	@Override
	public long affectImageToProduct(Long imageId, Long productId) {
		List<Image> listImage =new ArrayList<>();
		Image image =imageRepository.findById(imageId).get();
		Product product =productRepository.findById(productId).get();
		if(product.getPicture()!=null)
			listImage=product.getPicture();
		listImage.add(image);
		product.setPicture(listImage);
		productRepository.save(product);
		return 0;
	}

	@Override
	public long desaffectImageToProduct(Long imageId, Long productId) {
	//	if(productRepository.findById(productId).get().getPicture()!=null)
			
		return 0;
	}

	@Override
	public long affectImageToAisle(Long imageId, Long aisleId) {
		List<Image> listImage =new ArrayList<>();
		Image image =imageRepository.findById(imageId).get();
		Aisle Aisle =aisleRepository.findById(aisleId).get();
		if(Aisle.getImages()!=null)
			listImage=Aisle.getImages();
		listImage.add(image);
		Aisle.setImages(listImage);
		aisleRepository.save(Aisle);
		return 0;
	}

	@Override
	public long desaffectImageToAisle(Long imageId, Long aisleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long affectImageToBrand(Long imageId, Long brandId) {
		
		Image logo =imageRepository.findById(imageId).get();
		Brand Brand =brandRepository.findById(brandId).get();
		if(Brand.getLogo()==null)			
			Brand.setLogo(logo);
		brandRepository.save(Brand);
		return 0;
	}

	@Override
	public long desaffectImageToBrand(Long imageId, Long brandId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long affectImageToAds(Long imageId, Long adsId) {
		List<Image> listImage =new ArrayList<>();
		Image image =imageRepository.findById(imageId).get();
		Ads ads =adsRepository.findById(adsId).get();
		if(ads.getFile()!=null)
			listImage=ads.getFile();
		listImage.add(image);
		ads.setFile(listImage);
		adsRepository.save(ads);
		return 0;
	}

	@Override
	public long desaffectImageToAds(Long imageId, Long brandId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	

	
	//jsf
	
	@Override
	public String uploadProductFile( String type, Long productId, MultipartFile[] files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			 name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "product" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();			
				long imageId=addImage(new Image(type,file.getOriginalFilename()));
				affectImageToProduct(imageId, productId);				
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	@Override
	public String uploadAisleFile( String type,  Long id, MultipartFile[] files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			 name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "aisle" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				long imageId=addImage(new Image(type,file.getOriginalFilename()));
				affectImageToAisle(imageId, id);				
				message = message + "You successfully uploaded file=" + name + "";
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	@Override
	public String uploadBrandFile(String type,  Long id, MultipartFile[] files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			 name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "brand" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				long imageId=addImage(new Image(type,file.getOriginalFilename()));
				affectImageToBrand(imageId, id);
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	@Override
	public String uploadAdsFile(@RequestParam("type") String type, @RequestParam("id") Long id,@RequestParam("file") MultipartFile[] files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			 name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "ads" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				long imageId=addImage(new Image(type,file.getOriginalFilename()));
				affectImageToAds(imageId, id);
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	@Override
	public String uploadBarCodeFile(Long id, MultipartFile[] files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			 name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "product" + File.separator + "barCode");
				if (!dir.exists())
					dir.mkdirs();
				
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
								
//				barCodeInputStream = new FileInputStream(UPLOADED_FOLDER + file.getOriginalFilename());
//				BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
			//
//				LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
//				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//				Reader reader = new MultiFormatReader();
//				Result result = reader.decode(bitmap);
//				System.out.println("Barcode text is :" + result.getText());
//				return result.getText();
				
				
				BufferedImage barCodeBufferedImage = ImageIO.read(new FileInputStream(dir.getAbsolutePath() + File.separator + name));
				LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Reader reader = new MultiFormatReader();
				Result result = reader.decode(bitmap);
				
				System.out.println("Barcode text is ===" + result.getText());
				String code =result.getText();
				message = "Barcode text issssss ===" + result.getText();
				System.out.println(message);
				System.out.println(" product issssss   ==== " + code);
				long imageId=addImage(new Image("barCode",name));
				System.out.println(imageId);
				affectImageToProduct(imageId, id);

				
				
				// Create the file on server
		
				
			//	message = message + "You successfully uploaded file=" + name + "";
			} 
			
			catch (FileNotFoundException e) {
				System.out.println("barCodeInputStream = new FileInputStream(file.)");
				e.printStackTrace();
			} catch (IOException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (NotFoundException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (ChecksumException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (FormatException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			}
			catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	
	public String verifyBarCode(org.primefaces.model.UploadedFile files) {
		String name="";
		String message = "";
		
			org.primefaces.model.UploadedFile file = files;
			 name = file.getFileName();
			try {
				byte[] bytes = file.getContents();
				System.out.println("verif bar code get contents = " +bytes);
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "product" + File.separator + "barCode");
				if (!dir.exists())
					dir.mkdirs();
				
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
								
//				barCodeInputStream = new FileInputStream(UPLOADED_FOLDER + file.getOriginalFilename());
//				BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
			//
//				LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
//				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//				Reader reader = new MultiFormatReader();
//				Result result = reader.decode(bitmap);
//				System.out.println("Barcode text is :" + result.getText());
//				return result.getText();
				
				
				BufferedImage barCodeBufferedImage = ImageIO.read(new FileInputStream(dir.getAbsolutePath() + File.separator + name));
				LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Reader reader = new MultiFormatReader();
				Result result = reader.decode(bitmap);
				
				System.out.println("Barcode text is :" + result.getText());
				String code =result.getText();
				message = result.getText();
				System.out.println(" product issssss   : " + code);
				
				
			} 
			
			catch (FileNotFoundException e) {
				System.out.println("barCodeInputStream = new FileInputStream(file.)");
				e.printStackTrace();
			} catch (IOException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (NotFoundException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (ChecksumException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (FormatException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			}
			catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		
		return message;
	}
	
	
	@Override
	public String uploadProductFile( String type, Long productId, List<UploadedFile> files) {
		String name="";
		String message = "";
		System.err.println("hana fi methode mta3 upload serivce image ");
		for (int i = 0; i < files.size(); i++) {
			System.err.println( "hedhi image uploaded num   =  "+i+"  esmha =  "+files.get(i).getFileName());
			System.err.println( "hedhi image uploaded num   =  "+i+"  esmha =  "+files.get(i).getContents());
			
		}
		for (int i = 0; i < files.size(); i++) {
			org.primefaces.model.UploadedFile file = files.get(i);
			 name = file.getFileName();
			try {
				System.out.println("hna d5elna bech najoutou "+name);
				
				byte[] bytes = file.getContents();
				System.out.println("kjhfkjfh = "+ bytes.toString());
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "product" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();		
				System.out.println("hna sayer");
				long imageId=addImage(new Image(type,name));
				System.out.println("hna d5elna bech najoutou ajoutineha whedha id   = "+imageId);
				affectImageToProduct(imageId, productId);
				System.out.println("affecter lelproduit +++++ ");
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				System.out.println(e);
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	
	public String uploadAisleFile( String type,  Long id, List<UploadedFile> files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.size(); i++) {
			org.primefaces.model.UploadedFile file = files.get(i);
			 name = file.getFileName();
			try {
				byte[] bytes = file.getContents();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "aisle" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				long imageId=addImage(new Image(type,name));
				affectImageToAisle(imageId, id);				
				message = message + "You successfully uploaded file=" + name + "";
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	@Override
	public String uploadBrandFile(String type,  Long id, List<UploadedFile> files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.size(); i++) {
			org.primefaces.model.UploadedFile file = files.get(i);
			 name = file.getFileName();
			try {
				byte[] bytes = file.getContents();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "brand" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				long imageId=addImage(new Image(type,name));
				affectImageToBrand(imageId, id);
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	@Override
	public String uploadAdsFile(@RequestParam("type") String type, @RequestParam("id") Long id,@RequestParam("file") List<UploadedFile> files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.size(); i++) {
			org.primefaces.model.UploadedFile file = files.get(i);
			 name = file.getFileName();
			try {
				byte[] bytes = file.getContents();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "ads" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				long imageId=addImage(new Image(type,name));
				affectImageToAds(imageId, id);
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	
	@Override
	public String uploadBarCodeFile(Long id, List<UploadedFile> files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.size(); i++) {
			org.primefaces.model.UploadedFile file = files.get(i);
			 name = file.getFileName();
			try {
				byte[] bytes = file.getContents();
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + "product" + File.separator + "barCode");
				if (!dir.exists())
					dir.mkdirs();
				
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
								
//				barCodeInputStream = new FileInputStream(UPLOADED_FOLDER + file.getOriginalFilename());
//				BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
			//
//				LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
//				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//				Reader reader = new MultiFormatReader();
//				Result result = reader.decode(bitmap);
//				System.out.println("Barcode text is :" + result.getText());
//				return result.getText();
				
				
				BufferedImage barCodeBufferedImage = ImageIO.read(new FileInputStream(dir.getAbsolutePath() + File.separator + name));
				LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Reader reader = new MultiFormatReader();
				Result result = reader.decode(bitmap);
				
				System.out.println("Barcode text is ===" + result.getText());
				String code =result.getText();
				message = "Barcode text issssss ===" + result.getText();
				System.out.println(message);
				System.out.println(" product issssss   ==== " + code);
				long imageId=addImage(new Image("barCode",name));
				System.out.println(imageId);
				affectImageToProduct(imageId, id);

				
				
				// Create the file on server
		
				
			//	message = message + "You successfully uploaded file=" + name + "";
			} 
			
			catch (FileNotFoundException e) {
				System.out.println("barCodeInputStream = new FileInputStream(file.)");
				e.printStackTrace();
			} catch (IOException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (NotFoundException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (ChecksumException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			} catch (FormatException e) {
				//BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
				e.printStackTrace();
			}
			catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	
	
	
	
	
	@Override
	public long uploadFile( String to ,String type, UploadedFile file) {
		String name="";
		String message = "";
		
		
			
			 name = file.getFileName();
			try {
				
				byte[] bytes = file.getContents();
				
				File dir = new File(System.getProperty("user.dir")+"/src/main/webapp/resources/uploads"+ File.separator + to + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();		
				long imageId=addImage(new Image(type,name));
				System.out.println("hna d5elna bech najoutou ajoutineha whedha id   = "+imageId);
				
			
				System.out.println("affecter lelproduit +++++ ");
				message = message + "You successfully uploaded file=" + name + "";
				System.out.println(message);
				return imageId;
			} catch (Exception e) {
				System.out.println(e);
				return 0;
			}
				
	}

	@Override
	public long affectImageToCategory(Long imageId, Long categoryId) {
		
		Image logo =imageRepository.findById(imageId).get();
		Category category =categoryRepository.findById(categoryId).get();
		
			category.setLogo(logo);
			categoryRepository.save(category);
		return 0;
	}

	@Override
	public long affectImageToSubCategory(Long imageId, Long subCategoryId) {
		Image logo =imageRepository.findById(imageId).get();
		SubCategory subCategory =subCategoryRepository.findById(subCategoryId).get();
		
		subCategory.setLogo(logo);
		subCategoryRepository.save(subCategory);
			return 0;
	}

	@Override
	public long affectImageToPromos(Long imageId, Long promosId) {
		Image logo =imageRepository.findById(imageId).get();
		PromosMagazin promos =promosRepository.findById(promosId).get();
		
		promos.setImage(logo);;
		promosRepository.save(promos);
		return 0;
	}
	
	
	
	
	

}
