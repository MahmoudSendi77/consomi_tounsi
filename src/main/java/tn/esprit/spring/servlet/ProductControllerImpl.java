package tn.esprit.spring.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import lombok.val;
import tn.esprit.spring.control.SigninJsf;
import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.service.CatalogueService;
import tn.esprit.spring.service.IBrandService;
import tn.esprit.spring.service.ICategoryService;
import tn.esprit.spring.service.IFactureSupplierService;
import tn.esprit.spring.service.IImageService;
import tn.esprit.spring.service.IProductService;
import tn.esprit.spring.service.ISubCategoryService;

@Controller(value = "productController")
@ELBeanName(value = "productController")
@RequestScoped
// @Join(path = "/", to = "/login.jsf")
public class ProductControllerImpl {

	@Autowired
	SigninJsf signinJsf;
	@Autowired
	IImageService imageService;
	@Autowired
	CatalogueService catalogueService;
	@Autowired
	IProductService productService;
	@Autowired
	IFactureSupplierService factureSupplierService;
	@Autowired
	IBrandService brandService;
	@Autowired
	ICategoryService categoryService;
	@Autowired
	ISubCategoryService subCategoryService;
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	private Product product;
	private float price;
	private UploadedFile fichier;
	private List<UploadedFile> files = new ArrayList<UploadedFile>();
	private List<UploadedFile> filess = new ArrayList<UploadedFile>();
	private List<String> categories= new ArrayList<String>();
	private String[] selectedCategories;

	private List<String> brands= new ArrayList<String>();
	private String[] selectedBrands;

	private List<Product> listProduct = new ArrayList<Product>();
	
	private List<Product> listToSellProduct = new ArrayList<Product>();
	private List<Product> listProductSameCat = new ArrayList<Product>();
	
	
	
	
	
	private List<Category> listProductCategory = new ArrayList<Category>();
	private String searchValue;
	private String filterSearch;
	private String productBarCode;
	private String category;
	private String barCodeValue;
	private String jasserS;
	private String searchValueAdmin = "";

	private String showForm2 = "block";
	private String showColor = "block";
	private String showSize = "block";
	private String showDate = "block";
	
	private float fPrice;
	private float ePrice=5000;
	
	
	
	
	public List<Product> getListProductSameCat() {
		if(product!=null && product.getCategory()!=null)
		listProductSameCat=productService.getProductbyX("category", product.getCategory().getCategoryName());
		return listProductSameCat;
	}

	public void setListProductSameCat(List<Product> listProductSameCat) {
		this.listProductSameCat = listProductSameCat;
	}

	public List<Product> getListToSellProduct() {
		Map<Long,Product> lt = productService.getProductOfYear();
		System.out.println("controller "+lt);
		System.out.println("controller2 "+lt.get(1L));
		for(Long i : lt.keySet()){
			listToSellProduct.add(lt.get(i));
		}
		System.out.println("listToSellProduct   = "+listToSellProduct);
		return listToSellProduct;
	}

	public void setListToSellProduct(List<Product> listToSellProduct) {
		this.listToSellProduct = listToSellProduct;
	}

	public float getfPrice() {
		return fPrice;
	}

	public void setfPrice(float fPrice) {
		this.fPrice = fPrice;
	}

	public float getePrice() {
		return ePrice;
	}

	public void setePrice(float ePrice) {
		this.ePrice = ePrice;
	}

	public List<String> getBrands() {
		if(brands==null || brands.size()<2)
		brands=brandService.getListBrandsNames();
		return brands;
	}

	public void setBrands(List<String> brands) {
		this.brands = brands;
	}

	public String[] getSelectedBrands() {
		return selectedBrands;
	}

	public void setSelectedBrands(String[] selectedBrands) {
		this.selectedBrands = selectedBrands;
	}

	public List<String> getCategories() {
		if(	categories==null || categories.size()<2){
		for(Category c :listProductCategory){
		categories.add(c.getCategoryName());}}
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String[] getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(String[] selectedCategories) {
		this.selectedCategories = selectedCategories;
	}

	public String getSearchValueAdmin() {
		return searchValueAdmin;
	}

	public void setSearchValueAdmin(String searchValueAdmin) {
		this.searchValueAdmin = searchValueAdmin;
	}

	public String getShowForm2() {
		return showForm2;
	}

	public void setShowForm2(String showForm2) {
		System.err.println("display div ==" + showForm2);
		this.showForm2 = showForm2;
	}

	public List<UploadedFile> getFiless() {
		return filess;
	}

	public void setFiless(List<UploadedFile> filess) {
		filess = filess;
	}

	public String getCategory() {
		System.err.println("sddsdsd" +category);
		return category;
	}

	public void setCategory(String category) {
		System.err.println("ggggggggggggggggg   = " + category);
		this.category = category;
	}

	public String getJasserS() {

		return jasserS;
	}

	public void setJasserS(String jasserS) {
		this.jasserS = jasserS;
	}

	public UploadedFile getFichier() {
		return fichier;
	}

	public String getShowColor() {
		return showColor;
	}

	public void setShowColor(String showColor) {
		this.showColor = showColor;
	}

	public String getShowSize() {
		return showSize;
	}

	public void setShowSize(String showSize) {
		this.showSize = showSize;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public void setFichier(UploadedFile fichier) {
		System.out.println("66666666666666666666666666666");
		this.fichier = fichier;
	}

	public String getBarCodeValue() {
		return barCodeValue;
	}

	public void setBarCodeValue(String barCodeValue) {
		this.barCodeValue = barCodeValue;
	}

	public List<Category> getListProductCategory() {
		return listProductCategory;

	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public void setListProductCategory(List<Category> listProductCategory) {
		this.listProductCategory = listProductCategory;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getFilterSearch() {
		return filterSearch;
	}

	public void setFilterSearch(String filterSearch) {
		this.filterSearch = filterSearch;
	}

	@PostConstruct
	public void updateListProduct() {
		listProduct = productService.getAllProduct();
	}
	public void updatePage() {
		listProduct = productService.getAllProduct();
	}

	public List<Product> getListProduct() {
		if (searchValueAdmin.equals(""))
			return listProduct;
		else {
			searchValue = searchValueAdmin;
			filterSearch = "all";
			searchWithFilter();

			return listProduct;
		}
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<UploadedFile> getFiles() {
		System.out.println("jasser jasser = " + files);
		return files;
	}

	public void setFiles(List<UploadedFile> files) {
		files = files;
	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("wsel");
		System.out.println("oooooooooooooooooooo houssem  " + event.getFile());

		files.add(event.getFile());
		// uploadProductFile();
	}

	


	public static String getUploadDirectory() {
		return uploadDirectory;
	}

	

	public List<Product> getAllProduct() {
		return productService.getAllProduct();
	}

	public Map<Long, Product> getProductofTheMonth() {
		return productService.getProductOfMonth();
	}

	public int countSellByX(String x, String value, String date1, String date2) {
		return productService.countSellByX(x, value, Date.valueOf(date1), Date.valueOf(date2));
	}
	

	
	public List<Product> search(String search) {
		return productService.searchProduct(search);
	}


	public List<Product> filterProduct(Product p) {
		return productService.filterSearch(p);
	}

	public Float countFucturePrice(Long id) {
		return factureSupplierService.countPrice(id);
	}

	public String deleteProduct(long id) {
		productService.deleteProduct(id);
		return "product deleted successfuly";
	}

	
	// @PostMapping("/uploadMultipleFile1")

	public String uploadMultipleFileHandler(MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();

		for (MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename() + " ");
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

	public String verifyProduct(long productId, long stockId, long quantity, float unitPrice, String date1,
			String date2) {
		productService.affecterProductToStock(stockId, productId, quantity, unitPrice, Date.valueOf(date1),
				Date.valueOf(date2));
		return "productAffected";
	}

	// @GetMapping("/getProductQuantity/{productId}")
	public float countSellByX(long productId) {
		return productService.getQuantityById(productId);
	}

	@PostConstruct
	public void gettListProductCategory() {
		this.listProductCategory = productService.getAllCategory();
	}

	public void searchWithFilter() {
		if (searchValue != null && filterSearch != null) {
			listProduct.clear();
			listProduct = filterSearch.equals("category") ? productService.searchProductByCategory(searchValue)
					: filterSearch.equals("product") ? productService.searchProductByName(searchValue)
							: filterSearch.equals("brand") ? productService.searchProductByBrand(searchValue)
									: productService.searchProduct(searchValue);
			setSearchValue(null);
			setFilterSearch("all");
			System.out.println(listProduct);
		}
	}

	public void findPproductByCategory(String categoryname) {
		listProduct.clear();
		listProduct = productService.searchProductByCategory(categoryname);
		categoryname = null;
	}

	public void findPproductBySubCategory() {
		// listProduct =
		// productService.getProductbyX("subCategory",subCategory);
	}

	public void addFile() {
		try {
			catalogueService.creatFileAndInsertValue();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadProduct() {
		if (productBarCode != null)
			productService.getQuantityByReference(productBarCode);
	}

	// najmoou fi form nab3thou bel atribue mta3 entity

	public void testUpload() {
		try {
			System.out.println("housssmldknvodnlnofhvlfvn 23323 ");
		} catch (Exception e) {
			System.out.println("reeefdkvjdivdbvifbvibf " + e);
		}

	}

	
	public String goToProductHome() {
		updateListProduct();
		return "/pages/frontend/productViews/listProductView.xhtml?faces-redirect=true";
	}

	public String goToLogin() {
		return "/login.xhtml?faces-redirect=true";
	}

	public String navigateProductTo(String page) {
		return "/pages/frontend/productViews/" + page + ".xhtml?faces-redirect=true";
	}

	public String navigateToProductDetails(String productBarCode) {
		listProduct = productService.getProductByRef(productBarCode);
		return "/pages/frontend/productViews/productDetailsView.xhtml?faces-redirect=true";
	}

	public String navigateToSideBare() {
		return "/pages/frontend/productViews/productSideBarView.xhtml?faces-redirect=true";
	}

	public String navigateToHomeProduct() {
		updateListProduct();
		return "/pages/frontend/productViews/homeProductView.xhtml?faces-redirect=true";
	}

	public String navigateTolistDeroulanet() {
		return "/pages/frontend/productViews/listDeroulanteProduct.xhtml?faces-redirect=true";
	}

	public String navigateToPromos() {
		return "/pages/frontend/productViews/promosProductView.xhtml?faces-redirect=true";
	}

	public void handleChange(ValueChangeEvent event){  
	    System.err.println("New value: " + event.getNewValue());
	}
	
	public void filterProducts(){
		System.err.println("filter ok   = " +selectedCategories.toString()+" brands  =  "+selectedBrands.length +"   range  "+price );
		listProduct=listProduct.stream().filter((p)->filtred(p)).collect(Collectors.toList());
		
	}
	
	public void onPriceRangeChange(ValueChangeEvent value){
		System.err.println("lfknlknlkn"+value);
		listProduct=listProduct.stream().filter((p)->filtred(p)).collect(Collectors.toList());
	}
	
	public boolean filtred(Product p){
		
		for(Product pp :listProduct){
			System.err.println("list ssss s  = "+"product name   ="
					+ pp.getName()+" "+pp.getProducts());
		}
		
		List<String> brandLis=new ArrayList<>();
		boolean b=true;
		for(String s :selectedBrands){
			brandLis.add(s);	}
		List<String> catLis=new ArrayList<>();
		//|| p.getPrice()>ePrice || p.getPrice()<fPrice
		System.out.println(brandLis);
		for(String s :selectedCategories){
			catLis.add(s);	}
		System.out.println(catLis);
		if(brandLis!=null && brandLis.size()>0 && !brandLis.contains( p.getBrand().getName()) 
				|| catLis!=null && catLis.size()>0 && !catLis.contains( p.getCategory().getCategoryName()
						)
				|| p.getPrice()>ePrice || p.getPrice()<fPrice
				){
			b=false;
		}
		System.err.println("filter 9al   ="+b);
		return b ;
		
	}
	
	
}
