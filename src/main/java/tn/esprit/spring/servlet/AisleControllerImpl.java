package tn.esprit.spring.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.platform.commons.util.FunctionUtils;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.AisleType;
import tn.esprit.spring.entities.FactureSupplier;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.ProductNotif;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.IAisleService;
import tn.esprit.spring.service.IFactureSupplierService;
import tn.esprit.spring.service.IImageService;
import tn.esprit.spring.service.INotificationService;
import tn.esprit.spring.service.IProductService;
import tn.esprit.spring.service.UserService;

@Controller(value = "aisleController")
@ELBeanName(value = "aisleController")
@Join(path = "/admin/manageAisles", to = "/pages/adminSide/aislesViews/addAisle.jsf")

public class AisleControllerImpl implements IAisleController {

	@Autowired
	IAisleService aisleService;
	@Autowired
	IImageService imageService;
	@Autowired
	NavigateTo navigateTo;
	@Autowired
	UserService userService;

	@Autowired
	INotificationService nortificationService;

	@Autowired
	UserRepository userRepository;
	private List<Aisle> listAisle = new ArrayList<Aisle>();
	private long aisleId = 0;
	private Long aisleImageId;
	private Aisle aisleDetails;
	private Aisle aisleSelected;
	private Aisle aisle = new Aisle();
	private long supplierId = 0;
	private long productId = 0;

	String imageVR;
	private String test = "mmmmm";
	private AisleType[] aisleTypes;
	private AisleType aisleType;
	private UploadedFile aisleImage;
	private String productStyle = "none";
	private String productStyle4 = "none";
	private String productStyle5 = "none";
	private String brandStyle = "none";

	private String productButtonStyle = "fa fa-plus";
	private String productButtonStyle5 = "fa fa-plus";
	private String productButtonStyle4 = "fa fa-plus";
	private String brandButtonStyle = "fa fa-plus";
	private long chefAisleId = 0;
	private Map<Long, Long> listUser;
	private Map<Long, Long> listProducts;
	private Map<Long, Long> listsuplier;
	@Autowired
	IProductService productService;
	private long quantity;

	private List<FactureSupplier> listFacture = new ArrayList<>();
	private List<FactureSupplier> listSupplying = new ArrayList<>();

	List<Product> keyList = new ArrayList<Product>();

	public List<Product> produ(FactureSupplier f) {
		keyList = new ArrayList<Product>(f.getProducts().keySet());
		return keyList;
	}

	public List<FactureSupplier> getListSupplying() {
		List<FactureSupplier> ff = factureSup.getFactureBySuplierId(3L);
		listSupplying.clear();
		for (FactureSupplier f : ff) {
			if (f.getSupplied() != null && f.getSupplied().equals("no"))
				listSupplying.add(f);
		}
		return listSupplying;
	}

	public void setListSupplying(List<FactureSupplier> listSupplying) {
		this.listSupplying = listSupplying;
	}

	public List<FactureSupplier> getListFacture() {
		List<FactureSupplier> ff = factureSup.getFactureBySuplierId(3L);
		listFacture.clear();
		for (FactureSupplier f : ff) {
			if (f.getSupplied() != null && f.getSupplied().equals("yes"))
				listFacture.add(f);
		}
		return listFacture;
	}

	public void setListFacture(List<FactureSupplier> listFacture) {
		this.listFacture = listFacture;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	private int ps = 0;
	private int ps5 = 0;
	private int ps4 = 0;
	private int bs = 0;

	public long getChefAisleId() {
		return chefAisleId;
	}

	public void setChefAisleId(long chefAisleId) {
		this.chefAisleId = chefAisleId;
	}

	public Map<Long, Long> getListProducts() {
		List<Product> ll = productService.getAllProduct();
		listProducts = new HashMap<>();
		for (Product i : ll) {

			listProducts.put(i.getId(), i.getId());

		}

		return listProducts;
	}

	public void setListProducts(Map<Long, Long> listProducts) {
		this.listProducts = listProducts;
	}

	public Map<Long, Long> getListsuplier() {

		listu2 = userRepository.findByRoles_Id(6L);
		listsuplier = new HashMap<>();

		for (User i : listu2) {

			listsuplier.put(i.getId(), i.getId());

		}
		System.err.println();
		return listsuplier;

	}

	public void setListsuplier(Map<Long, Long> listsuplier) {
		this.listsuplier = listsuplier;
	}

	private List<User> listu = new ArrayList<>();
	private List<User> listu2 = new ArrayList<>();

	public Map<Long, Long> getListUser() {
		listu = userRepository.findByRoles_Id(5L);
		listUser = new HashMap<>();

		for (User i : listu) {

			listUser.put(i.getId(), i.getId());

		}
		System.err.println();
		return listUser;
	}

	public void setListUser(Map<Long, Long> listaisles) {
		this.listUser = listaisles;
	}

	public long getAisleId() {
		return aisleId;
	}

	public void setAisleId(long aisleId) {
		this.aisleId = aisleId;
	}

	public Aisle getAisleSelected() {
		return aisleSelected;
	}

	public void setAisleSelected(Aisle aisleSelected) {
		System.out.println("setAisleSelected   = " + aisleSelected);
		System.out.println("setAisleSelected  images = " + aisleSelected.getImages());
		this.aisleSelected = aisleSelected;
	}

	public UploadedFile getAisleImage() {
		return aisleImage;
	}

	public void setAisleImage(UploadedFile aisleImage) {
		this.aisleImage = aisleImage;
	}

	public String getProductButtonStyle() {
		return productButtonStyle;
	}

	public void setProductButtonStyle(String productButtonStyle) {
		this.productButtonStyle = productButtonStyle;
	}
	
	public String getProductButtonStyle5() {
		return productButtonStyle5;
	}

	public void setProductButtonStyle5(String productButtonStyle5) {
		this.productButtonStyle5 = productButtonStyle5;
	}
	
	public String getProductButtonStyle4() {
		return productButtonStyle4;
	}

	public void setProductButtonStyle4(String productButtonStyle4) {
		this.productButtonStyle4 = productButtonStyle4;
	}
	

	public String getBrandButtonStyle() {
		return brandButtonStyle;
	}

	public void setBrandButtonStyle(String brandButtonStyle) {
		this.brandButtonStyle = brandButtonStyle;
	}

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}
	
	public String getProductStyle5() {
		return productStyle5;
	}

	public void setProductStyle5(String productStyle5) {
		this.productStyle5 = productStyle5;
	}
	public String getProductStyle4() {
		return productStyle4;
	}

	public void setProductStyle4(String productStyle4) {
		this.productStyle4 = productStyle4;
	}

	public String getBrandStyle() {
		return brandStyle;
	}

	public void setBrandStyle(String brandStyle) {
		this.brandStyle = brandStyle;
	}

	public String getImageVR() {
		return imageVR;
	}

	public void setImageVR(String imageVR) {
		this.imageVR = imageVR;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String navigateToHomeAisle() {
		return "/pages/frontend/aisleViews/homeAisleView.xhtml?faces-redirect=true";
	}

	public String navigateToAisleVR() {

		return "/pages/frontend/aisleViews/aisleVRView.xhtml?faces-redirect=true";
	}

	public String viewCatalague() {
		return "/catalogue/samples/magazine/magazinCatalogue.xhtml?faces-redirect=true";
	}

	// @PostConstruct
	// public void gettListAisle(){
	//
	// this.listAisle = aisleService.getAllAisle();
	//
	// }

	public void uploadedImageAisle() {
		System.err.println("hi !!!!! aisles");
	}

	public AisleType[] getAisleTypes() {
		return aisleTypes;
	}

	public void setAisleTypes(AisleType[] aisleTypes) {
		this.aisleTypes = aisleTypes;
	}

	public AisleType getAisleType() {
		return aisleType;
	}

	public void setAisleType(AisleType aisleType) {
		this.aisleType = aisleType;
	}

	public Aisle getAisle() {
		return aisle;
	}

	public void setAisle(Aisle aisle) {
		this.aisle = aisle;
	}

	public Aisle getAisleDetails() {
		System.out.println("aopaodpaokdpaok88888 ***** ");
		return aisleDetails;
	}

	public void updateAisleDetails(long id) {
		aisleDetails = aisleService.getAislebyId(id);
		imageVR = aisleDetails.getImages().get(0).getImage();
		System.out.println(aisleDetails);
	}

	public void setAisleDetails(Aisle aisleDetails) {
		this.aisleDetails = aisleDetails;
	}

	public List<Aisle> getListAisle() {
		listAisle = aisleService.getAllAisle();
		return listAisle;
	}

	public void setListAisle(List<Aisle> listAisle) {
		this.listAisle = listAisle;
	}

	@PostMapping("/addAisle")
	@ResponseBody
	public String addAisle(@RequestBody Aisle aisle) {
		aisleService.addAisle(aisle);
		return "aisle added successfuly";
	}

	@GetMapping("/countBrandRentabilteByAisle/{brandName}")
	@ResponseBody
	public List<List<Float>> countBrandRentabilteByAisle(@PathVariable("brandName") String brandName) {
		return aisleService.countBrandRentabilteByAisle(brandName);
	}

	@GetMapping("/countRentabilteByAisle")
	@ResponseBody
	public List<List<Float>> countRentabilteByAisle() {
		return aisleService.countRentabilteByAisle();

	}

	@DeleteMapping("/deleteProduct/{id}")

	public String addAisle(@PathVariable("id") Long aisleId) {
		aisleService.deleteAisle(aisleId);
		return "aisle deleted successfuly";
	}

	@PutMapping("/updateProduct")
	@ResponseBody
	public String updateAisle(@RequestBody Aisle aisle) {
		aisleService.updateAisle(aisle);
		return "aisle addedsuccessfuly";
	}

	// @GetMapping("/populateAisle/{id}")
	// @ResponseBody
	// public String populateProduct(@PathVariable("id") Long id,@RequestBody
	// List<Long> listProduct){
	// aisleService.populateAisle(id, listProduct);
	// return "ok";
	// }

	@GetMapping("/getAisleProduct/{id}")
	public List<Product> getAisleProduct(@PathVariable("id") Long id) {
		return aisleService.getAisleProduct(id);

	}

	@GetMapping("/getAisle/{id}")
	public Aisle getAisleById(@PathVariable("id") Long id) {
		return aisleService.getAislebyId(id);

	}

	@GetMapping("/getAllAisle")
	public List<Aisle> getAllAisle() {
		return aisleService.getAllAisle();

	}

	@PutMapping("/addCategoryToAisle/{aisleId}/{categoryId}")
	public String addCategoryToAisle(@PathVariable("aisleId") Long aisleId,
			@PathVariable("categoryId") Long categoryId) {
		aisleService.addCategoryToAisle(aisleId, categoryId);
		return "aisle updated successfuly";
	}

	@PutMapping("/deleteCategoryFromAisle/{aisleId}/{categoryId}")
	public String deleteCategoryFromAisle(@PathVariable("aisleId") Long aisleId,
			@PathVariable("categoryId") Long categoryId) {
		aisleService.deleteCategoryFromAisle(aisleId, categoryId);
		return "aisle deleted successfuly";
	}

	public String affecterCheafAisleToAisle(Long aisleId, Long userId) {
		aisleService.affecterCheafAisleToAisle(aisleId, userId);
		return "aisle affecterCheafAisleToAisle successfuly";
	}

	@PutMapping("/desaffecterCheafAisleToAisle/{aisleId}/{userId}")
	public String desaffecterCheafAisleToAisle(@PathVariable("aisleId") Long aisleId,
			@PathVariable("userId") Long userId) {
		aisleService.desaffecterCheafAisleToAisle(aisleId, userId);
		return "aisle desaffecterCheafAisleToAisle successfuly";
	}

	public void handleAisleFile(FileUploadEvent event) {
		System.err.println("file uploaded success");
		aisleImage = event.getFile();
		aisleImageId = imageService.uploadFile("aisle", "image", aisleImage);
	}

	public String addTheAisle() {
		System.err.println("addTheAisle  = " + aisle);
		long aisleId = aisleService.addAisle(aisle);
		System.err.println("aisleId  = " + aisleId);
		imageService.affectImageToAisle(aisleImageId, aisleId);
		return navigateTo.showAisles();
	}

	public void collapse(int i) {

		if (i == 1 && ps != 0) {
			ps = 0;
			productStyle = "none";
			productButtonStyle = "fa fa-plus";
		} else {
			if (i == 1 && ps == 0) {
				ps = 5;
				productStyle = "block";
				productButtonStyle = "fa fa-minus";
			}
		}

		
		if (i == 4 && ps4 != 0) {
			ps4 = 0;
			productStyle4 = "none";
			productButtonStyle4 = "fa fa-plus";
		} else {
			if (i == 4 && ps4 == 0) {
				ps4 = 5;
				productStyle4 = "block";
				productButtonStyle4 = "fa fa-minus";
			}
		}
		
		if (i == 5 && ps5 != 0) {
			ps5 = 0;
			productStyle5 = "none";
			productButtonStyle5 = "fa fa-plus";
		} else {
			if (i == 5 && ps5 == 0) {
				ps5 = 5;
				productStyle5 = "block";
				productButtonStyle5 = "fa fa-minus";
			}
		}

		
		
		
		if (i == 2 && bs == 0) {
			bs = 5;
			brandStyle = "block";
			brandButtonStyle = "fa fa-minus";
		} else {
			if (i == 2 && bs != 0) {
				bs = 0;
				brandStyle = "none";
				brandButtonStyle = "fa fa-plus";
			}
		}

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("saret mochkla fi reload ");
			e.printStackTrace();
		}
	}

	public String affectToAisle() {
		if (aisleId != 0 && chefAisleId != 0)
			aisleService.affecterCheafAisleToAisle(aisleId, chefAisleId);
		return navigateTo.showAisles();
	}

	@Autowired
	IFactureSupplierService factureSup;

	public void askFor() {
		System.err.println("askFor");

		long notiId = nortificationService
				.addNotification(new ProductNotif("supply request", "ask for supply", new Date(), false, null));
		User sup = userRepository.findById(supplierId).get();
		nortificationService.affectNotificationTo(notiId, sup);
		FactureSupplier factureSuplier = new FactureSupplier(randomAlphaNumeric(10), new Date(), "no");
		Map<Product, Long> products = new HashMap<>();
		Product p = productService.getProductById(productId);
		products.put(p, (Long) quantity);
		float price = quantity*p.getPrice();
		
		factureSuplier.setProducts(products);
		
		factureSuplier.setUser(sup);
		factureSuplier.setPrice(price);
		factureSup.addFacture(factureSuplier);

	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public void supplyFor(long id) {
		System.err.println("suplyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

		long notiId = nortificationService.addNotification(
				new ProductNotif("supply Respence", "supplied quantity have been added ", new Date(), false, null));

		List<User> admins = userRepository.findByRoles_Id(4L);

		for (User u : admins) {
			nortificationService.affectNotificationTo(notiId, u);
		}

		List<User> chefs = userRepository.findByRoles_Id(5L);

		for (User u : chefs) {
			nortificationService.affectNotificationTo(notiId, u);
		}
		
		FactureSupplier factureSuplier = factureSup.getFactureById(id);
				
		factureSuplier.setSupplied("yes");
		factureSup.addFacture(factureSuplier);
		System.err.println("factureSuplier  " + factureSuplier);
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("saret mochkla fi reload ");
			e.printStackTrace();
		}
	}
}
