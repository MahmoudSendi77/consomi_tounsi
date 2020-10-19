package tn.esprit.spring.control;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.service.IFactureSupplierService;
import tn.esprit.spring.service.IProductService;



////@Scope(value = "session")
//@Controller(value = "ProductControllerImpl")
//@ELBeanName(value = "ProductControllerImpl")
//@Join(path = "/", to = "/login.jsf")

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements IProductController {

	@Autowired
	IProductService productService;

	@Autowired
	IFactureSupplierService factureSupplierService;

	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	@PostMapping("/addProduct")
	@ResponseBody
	public Product addProduct(@RequestBody Product product) {
		System.out.println(product.getCategory());
		productService.addProduct(product);
		return product;
	}

	@PutMapping("/updateProduct")
	@ResponseBody
	public Product updateProduct(@RequestBody Product product) {
		System.out.println(product.getCategory());
		productService.updateProduct(product);
		return product;
	}


	@GetMapping("/getProductBy/{X}/{value}")
	public List<Product> getProductByX(@PathVariable("X") String x, @PathVariable("value") String value) {
		return productService.getProductbyX(x, value);
	}
	
	@GetMapping("/getProductById/{value}")
	public Product getProductById( @PathVariable("value") Long id) {
		return productService.getProductById(id);
	}
	
	@GetMapping("/getAllProduct")
	public List<Product> getAllProduct() {
		return productService.getAllProduct();
	}
	
	
	@GetMapping("/getProductofTheMonth")
	public  Map<Long,Product> getProductofTheMonth() {
		return productService.getProductOfMonth();
	}

	@GetMapping("/countSellByX/{X}/{value}/{date1}/{date2}")
	public int countSellByX(@PathVariable("X") String x, @PathVariable("value") String value,@PathVariable("date1") String date1,@PathVariable("date2") String date2) {
		return productService.countSellByX(x, value, Date.valueOf(date1),Date.valueOf(date2));
	}
	
	
//	@GetMapping("/countSellByX/{X}/{reference}/{value}")
//	public int countSellByX(@PathVariable("X") String x, @PathVariable("value") String value) {
//		return productService.countSellByX(x, value);
//	}
	

	@GetMapping("/search/{search}")
	public List<Product> search(@PathVariable("search") String search) {
		return productService.searchProduct(search);
	}

	@GetMapping("/search/filter")
	@ResponseBody
	public List<Product> searchB(@RequestBody Product p) {
		return productService.filterSearch(p);
	}
	
	@GetMapping("/countFucturePrice/{id}")
	@ResponseBody
	public Float countFucturePrice(@PathVariable("id") Long id) {
		return factureSupplierService.countPrice(id) ;
	}

	@DeleteMapping("/deleteProduct/{id}")
	public String search(@PathVariable("id") long id) {
		productService.deleteProduct(id);
		return "product deleted successfuly";
	}

	@PostMapping("/uploadProductFile")
	@ResponseBody
	public String uploadProductFile(@RequestParam("type") String type, @RequestParam("id") Long id,@RequestParam("file") MultipartFile[] files) {
		String name="";
		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			 name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(System.getProperty("user.dir")+"/uploads"+ File.separator + "product" + File.separator + type);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				message = message + "You successfully uploaded file=" + name + "";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
	@PostMapping("/uploadMultipleFile1")
	@ResponseBody
	public String uploadMultipleFileHandler(@RequestParam("files") MultipartFile[] files){
		StringBuilder fileNames =new StringBuilder();
		
		for (MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
			  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			  fileNames.append(file.getOriginalFilename()+" ");
			  System.out.println(file.getOriginalFilename());
			  try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				System.out.println(e);
				 return "erreuuuuuuuuuuuur";
			}
		  }
		  return "uploadstatusview";
	}
	
	
	@PostMapping("/affectProductToStock")
	@ResponseBody
	public String verifyProduct(@RequestParam("productId") long  productId,@RequestParam("stockId") long  stockId,@RequestParam("quantity") long  quantity,
			@RequestParam("unitPrice") float unitPrice ,@RequestParam("date1") String date1,@RequestParam("date2") String date2 ) {
				productService.affecterProductToStock(stockId, productId, quantity, unitPrice,Date.valueOf(date1),Date.valueOf(date2));
return "productAffected";
	}
	

	@PostMapping("/affectProductToStock2")
	@ResponseBody
	public String verifyProduct2(@RequestParam("productId") long  productId,@RequestParam("stockId") long  stockId,@RequestParam("quantity") long  quantity,
			@RequestParam("unitPrice") float unitPrice  ) {
				productService.affecterProductToStock(stockId, productId, quantity, unitPrice);
return "productAffected";
	}
	
	
	@GetMapping("/getProductQuantity/{productId}")
	public float countSellByX(@PathVariable("productId") long productId) {
		return productService.getQuantityById(productId);
	}
	
	@PutMapping("/affectProductToAisle/{productId}/{aisleId}")
	public String affectProductToAisle(@PathVariable("productId") long productId,@PathVariable("aisleId") long aisleId) {
		
				productService.affecterProductToAisle(aisleId, productId);
	return "done";
	}
	
	
	
}
