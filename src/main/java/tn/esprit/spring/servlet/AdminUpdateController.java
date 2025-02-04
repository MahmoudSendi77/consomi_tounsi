package tn.esprit.spring.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.control.SigninJsf;
import tn.esprit.spring.entities.Brand;
import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Size;
import tn.esprit.spring.entities.SubCategory;
import tn.esprit.spring.service.CatalogueService;
import tn.esprit.spring.service.IBrandService;
import tn.esprit.spring.service.ICategoryService;
import tn.esprit.spring.service.IImageService;
import tn.esprit.spring.service.IProductService;
import tn.esprit.spring.service.ISubCategoryService;

@Scope(value = "session")
// @Controller(value = "ProductControllerImpl")
// @ELBeanName(value = "ProductControllerImpl")
// @Join(path = "/", to = "/login.jsf")

@Controller(value = "adminUpdateController")
@ELBeanName(value = "adminUpdateController")
@Join(path = "/admin/updating", to = "/pages/adminSide/productViews/updateProduct.jsf")
public class AdminUpdateController {

	@Autowired
	SigninJsf signinJsf;
	@Autowired
	IImageService imageService;
	@Autowired
	NavigateTo navigateTo;
	@Autowired
	IProductService productService;

	@Autowired
	CatalogueService catalogueService;
	@Autowired
	IBrandService brandService;
	@Autowired
	ICategoryService categoryService;
	@Autowired
	ISubCategoryService subCategoryService;

	private List<Product> listProduct = new ArrayList<Product>();
	private List<Category> listProductCategory = new ArrayList<Category>();
	private List<Brand> listProductbrand = new ArrayList<Brand>();

	private List<SubCategory> listProductSubCategory = new ArrayList<SubCategory>();

	// forCrud
	private Product product = new Product();
	private Category category = new Category();
	private SubCategory subCategory = new SubCategory();
	private Brand brand = new Brand();

	// uploadFiles

	private static List<UploadedFile> files = new ArrayList<UploadedFile>();
	private UploadedFile fichier;
	private UploadedFile brandLogo;
	private long brandLogoId;
	private UploadedFile categoryImage;
	private long categoryImageId;
	private UploadedFile subCategoryImage;
	private long subCategoryImageId;

	// forStyle
	private String showForm2 = "none";
	private String showColor = "none";
	private String showSize = "none";
	private String showDate = "none";

	private String showBarCodePanelImage = "none";
	private String showBarCodePanel = "none";
	private String showBarCodePanelChoose = "block";

	private String subCategoryStyle = "none";
	private String categoryStyle = "none";
	private String productStyle = "none";
	private String brandStyle = "none";

	private String subCategoryButtonStyle = "fa fa-plus";
	private String categoryButtonStyle = "fa fa-plus";
	private String productButtonStyle = "fa fa-plus";
	private String brandButtonStyle = "fa fa-plus";
	private int showForm;
	

	private int ps = 0;
	private int bs = 0;
	private int scs = 0;
	private int cs = 0;

	// for dinamique select categ
	private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
	private String categorySelect = "";
	private String subCategorySelect = "";
	private String brandSelect = "";
	private Size sizeSelect;
	private Map<String, String> categories;
	private Map<String, String> subCategories;
	private Map<String, String> brands;
	private Map<Size, Size> sizes;

	private String date1;
	private String date2;

	private List<Long> productFiles = new ArrayList<>();

	private String productBarCode;

	
	
	public int getShowForm() {
		return showForm;
	}

	public void setShowForm(int showForm) {
	//	this.showForm = showForm;
		doShow(showForm);
	}

	public List<Brand> getListProductbrand() {
		listProductbrand = brandService.getListBrand();
		return listProductbrand;
	}

	public void setListProductbrand(List<Brand> listProductbrand) {
		this.listProductbrand = listProductbrand;
	}

	public List<SubCategory> getListProductSubCategory() {
		listProductSubCategory = subCategoryService.getAllSubCategory();
		return listProductSubCategory;
	}

	public void setListProductSubCategory(List<SubCategory> listProductSubCategory) {
		this.listProductSubCategory = listProductSubCategory;
	}

	public Size getSizeSelect() {
		return sizeSelect;
	}

	public void setSizeSelect(Size sizeSelect) {
		this.sizeSelect = sizeSelect;
	}

	public Map<Size, Size> getSizes() {
		return sizes;
	}

	public void setSizes(Map<Size, Size> sizes) {
		this.sizes = sizes;
	}

	public String getBrandSelect() {

		return brandSelect;
	}

	public void setBrandSelect(String brandSelect) {
		this.brandSelect = brandSelect;
	}

	public Map<String, String> getBrands() {

		return brands;
	}

	public void setBrands(Map<String, String> brands) {
		this.brands = brands;
	}

	public void collapse(int i) {
		System.err
				.println("manageProduct Style int i == " + i + "  bs " + bs + " ps" + ps + " scs " + scs + " cs " + cs);
		if (i == 3 && cs == 0) {
			cs = 3;
			categoryStyle = "block";
			categoryButtonStyle = "fa fa-minus";
		} else {
			if (i == 3 && cs != 0) {
				cs = 0;
				categoryStyle = "none";
				categoryButtonStyle = "fa fa-plus";
			}
		}

		if (i == 4 && scs != 0) {
			scs = 0;
			subCategoryStyle = "none";
			subCategoryButtonStyle = "fa fa-plus";
		} else {
			if (i == 4 && scs == 0) {
				scs = 5;
				subCategoryStyle = "block";
				subCategoryButtonStyle = "fa fa-minus";
			}
		}

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

	public String getShowBarCodePanelImage() {
		return showBarCodePanelImage;
	}

	public void setShowBarCodePanelImage(String showBarCodePanelImage) {
		this.showBarCodePanelImage = showBarCodePanelImage;
	}

	public String getShowBarCodePanel() {
		return showBarCodePanel;
	}

	public void setShowBarCodePanel(String showBarCodePanel) {
		this.showBarCodePanel = showBarCodePanel;
	}

	public String getShowBarCodePanelChoose() {
		return showBarCodePanelChoose;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {

		this.subCategory = subCategory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setShowBarCodePanelChoose(String showBarCodePanelChoose) {
		this.showBarCodePanelChoose = showBarCodePanelChoose;
	}

	public CatalogueService getCatalogueService() {
		return catalogueService;
	}

	public String getCategoryStyle() {
		return categoryStyle;
	}

	public void setCategoryStyle(String categoryStyle) {
		System.out.println("setCategoryStyle ppppp  : "+categoryStyle);
		doShow(3);
		//this.categoryStyle = categoryStyle;
	}

	public void setCatalogueService(CatalogueService catalogueService) {
		this.catalogueService = catalogueService;
	}

	public String getSubCategoryStyle() {
		return subCategoryStyle;
	}

	public void setSubCategoryStyle(String subCategoryStyle) {
		this.subCategoryStyle = subCategoryStyle;
	}

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	public String getBrandStyle() {
		return brandStyle;
	}

	public void setBrandStyle(String brandStyle) {
		this.brandStyle = brandStyle;
	}

	public String getSubCategoryButtonStyle() {
		return subCategoryButtonStyle;
	}

	public void setSubCategoryButtonStyle(String subCategoryButtonStyle) {
		this.subCategoryButtonStyle = subCategoryButtonStyle;
	}

	public String getCategoryButtonStyle() {
		return categoryButtonStyle;
	}

	public void setCategoryButtonStyle(String categoryButtonStyle) {
		this.categoryButtonStyle = categoryButtonStyle;
	}

	public String getProductButtonStyle() {
		return productButtonStyle;
	}

	public void setProductButtonStyle(String productButtonStyle) {
		this.productButtonStyle = productButtonStyle;
	}

	public String getBrandButtonStyle() {
		return brandButtonStyle;
	}

	public void setBrandButtonStyle(String brandButtonStyle) {
		this.brandButtonStyle = brandButtonStyle;
	}

	public UploadedFile getFichier() {
		return fichier;
	}

	public void setFichier(UploadedFile fichier) {
		this.fichier = fichier;
	}

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

	public UploadedFile getFile() {
		return fichier;
	}

	public void setFile(UploadedFile file) {
		this.fichier = file;
	}

	public List<Category> getListProductCategory() {
		this.listProductCategory = productService.getAllCategory();
		System.out.println("list of all category   === " + listProductCategory);
		return listProductCategory;
	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getShowForm2() {
		return showForm2;
	}

	public void setShowForm2(String showForm2) {
		this.showForm2 = showForm2;
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

	public void setListProductCategory(List<Category> listProductCategory) {
		this.listProductCategory = listProductCategory;
	}

	// @PostConstruct
	// public void updateListProduct() {
	// listProduct = productService.getAllProduct();
	// System.out.println("admin side list products " + listProduct);
	// }

	public AdminUpdateController() {
		super();
	}

	public List<Product> getListProduct() {
		listProduct = productService.getAllProduct();

		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if (product != null && product.getCategory() != null) {
			categorySelect = product.getCategory().getCategoryName();
			onCategoryyChange();
			updateForms();
		}
		if (product != null && product.getSubCategory() != null)
			subCategorySelect = product.getSubCategory().getName();
		if (product != null && product.getBrand() != null)
			brandSelect = product.getBrand().getName();
		if (product != null && product.getSize() != null)
			sizeSelect = product.getSize();
		productBarCode = product.getReference();
		this.product = product;
	}

	public List<UploadedFile> getFiles() {
		return files;
	}

	public void setFiles(List<UploadedFile> files) {
		AdminUpdateController.files = files;
	}

	int lengh = 0;

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("hedha lengh mta3 productFiles" + productFiles);

		System.out.println("file uploaded  " + event.getFile());
		System.out.println("file name " + event.getFile().getFileName());
		System.err.println("file contents " + event.getFile().getContents());

		files.add(event.getFile());
		// Long id = imageService.uploadFile("product", "image",
		// event.getFile());
		// productFiles.add(id);
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public Product addProduct(Product product) {
		System.out.println(product.getCategory());
		productService.addProduct(product);
		return product;
	}

	public Product updateProduct(Product product) {
		System.out.println(product.getCategory());
		productService.updateProduct(product);
		return product;
	}

	public Map<Long, Product> getProductofTheMonth() {
		return productService.getProductOfMonth();
	}

	public int countSellByX(String x, String value, String date1, String date2) {
		return productService.countSellByX(x, value, Date.valueOf(date1), Date.valueOf(date2));
	}

	// @GetMapping("/countSellByX/{X}/{reference}/{value}")
	// public int countSellByX(@PathVariable("X") String x,
	// @PathVariable("value") String value) {
	// return productService.countSellByX(x, value);
	// }
	public List<Product> search(String search) {
		return productService.searchProduct(search);
	}

	public List<Product> filterProduct(Product p) {
		return productService.filterSearch(p);
	}

	public void deleteProduct(long id) {
		productService.deleteProduct(id);
		System.out.println("product deleted successfuly  ");
	}

	public String uploadProductFile() {
		System.out.println("uploaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  ");
		String type = "image";
		String name = "";
		String message = "sava";
		System.out.println("zzzzzzzzzzzzzzzznnnnnnnnnnnnbbbbbbbbbbbbb   ==>  " + files.size());
		System.out.println(message);
		return message;
	}

	public String affecterProductToStock(long productId, long stockId, long quantity, float unitPrice, String date1,
			String date2) {
		productService.affecterProductToStock(stockId, productId, quantity, unitPrice, Date.valueOf(date1),
				Date.valueOf(date2));
		return "productAffected";
	}

	public float countSellByX(long productId) {
		return productService.getQuantityById(productId);
	}

	// @PostConstruct
	// public void gettListProductCategory() {
	// System.out.println("admin list caregory " + listProductCategory);
	// this.listProductCategory = productService.getAllCategory();
	// System.out.println("admin list caregory " + listProductCategory);
	// }

	public void findPproductByCategory(String categoryname) {
		listProduct.clear();
		listProduct = productService.searchProductByCategory(categoryname);
		System.out.println("list by cate  oooooooooooo   ==  " + listProduct);
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

	public void init() {
		// listProductCategory.clear();
		// listProduct.clear();
		// subCategorySelect=null;
		// categorySelect=null;

	}

	@PostConstruct
	public void initi() {
		brands = new HashMap<String, String>();
		sizes = new HashMap<Size, Size>();

		for (Size s : Size.values()) {
			sizes.put(s, s);
		}
		List<String> brds = brandService.getListBrandsNames();
		for (int i = 0; i < brds.size(); i++) {
			brands.put(brds.get(i), brds.get(i));
		}
		categories = new HashMap<String, String>();
		listProductCategory = productService.getAllCategory();
		System.out.println("admin list cat " + listProductCategory);

		for (Category category : listProductCategory) {
			categories.put(category.getCategoryName(), category.getCategoryName());
			Map<String, String> map = new HashMap<String, String>();
			if (category.getSubCategory() == null) {
				map.put(category.getCategoryName(), category.getCategoryName());
			} else {
				for (SubCategory subCategory : category.getSubCategory()) {
					map.put(subCategory.getName(), subCategory.getName());

				}
			}
			data.put(category.getCategoryName(), map);
		}
		System.out.println("data admin " + data);
	}

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public void onCategoryyChange() {
		System.err.println("onCategoryyChange za3ma ye5dem ou nn ??");
		if (categorySelect != null && !categorySelect.equals("")) {
			System.err.println("categorySelect onCategoryyChange + = " + categorySelect);
			subCategories = data.get(categorySelect);
			updateForms();
			System.err.println("hna list dyn  subCcateg   = " + subCategories);
		} else
			subCategories = new HashMap<String, String>();
	}

	public void onCategoryUpdateChange(SelectEvent value) {

		System.err.println("hawo d5el " + value.getSource().toString());
		System.err.println("d5el whaw lvalue" + value);

		subCategories = data.get(value);
		updateForms();
		System.err.println("hna list dyn  subCcateg   = " + subCategories);

	}

	public void onSubCategoryChange() {

		System.err.println("selected cat ajax louta onsuuuubCategoryyChange +  =" + subCategorySelect);

	}

	public void handleChange(ValueChangeEvent event) {
		System.err.println("New value of chaged categoryselected: " + event.getNewValue());
	}

	public void handleChange2(ValueChangeEvent event) {
		System.err.println("New value of chaged subcategoryselected: " + event.getNewValue());
	}

	public void displayLocation() {
		// FacesMessage msg;
		// if(subCategorySelect != null && categorySelect != null)
		// msg = new FacesMessage("Selected", subCategorySelect + " of " +
		// categorySelect);
		// else
		// msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City
		// is not selected.");
		//
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getSubCategorySelect() {
		return subCategorySelect;
	}

	public void setSubCategorySelect(String subCategorySelect) {

		this.subCategorySelect = subCategorySelect;
	}

	public Map<String, String> getCategories() {
		System.err.println("categories   =  " + categories);
		return categories;
	}

	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}

	public void setData(Map<String, Map<String, String>> data) {
		this.data = data;
	}

	public String getCategorySelect() {
		return categorySelect;
	}

	public void setCategorySelect(String categorySelect) {
		this.categorySelect = categorySelect;
	}

	public Map<String, String> getSubCategories() {
		System.err.println("getting subcategory  nowwww        = " + subCategories);
		return subCategories;
	}

	public void setSubCategories(Map<String, String> subCategories) {
		this.subCategories = subCategories;
	}

	public String addTheProduct() {

		if (categorySelect != null && categorySelect != "")
			product.setCategory(categoryService.getCategoryByName(categorySelect));
		if (subCategorySelect != null && subCategorySelect != "")
			product.setSubCategory(subCategoryService.getSubCategoryByName(subCategorySelect));
		if (brandSelect != null && brandSelect != "")
			product.setBrand(brandService.getBrandByName(brandSelect));
		if (sizeSelect != null)
			product.setSize(sizeSelect);

		System.out.println("add product success  = " + categorySelect + "   sub  =  " + subCategorySelect
				+ " prodsbc = " + product.getSubCategory() + "catprod  " + product.getCategory() + " brand  "
				+ product.getBrand() + "   the product    =" + product);

		Long productId = productService.addProduct(product);

		// for (int i = 0; i < productFiles.size(); i++) {
		// if (productFiles.get(i) != 0)
		// imageService.affectImageToProduct(productFiles.get(i), productId);
		// }

		for (int i = 0; i < files.size(); i++) {
			System.out.println("list des imge = " + files.get(i) + "\n file names = " + files.get(i).getFileName());
			System.out.println("file contents" + files.get(i).getContents());
			Long id = imageService.uploadFile("product", "image", files.get(i));
			imageService.affectImageToProduct(id, productId);
		}

		product = new Product();
		// PrimeFaces.current().executeScript("alert('This onload script is
		// added from backing bean.')");
		files.clear();productFiles.clear();
		brandSelect = "";
		categorySelect = "";
		subCategorySelect = "";
		sizeSelect = null;
		productBarCode = "";
		return navigateTo.adminHomeProduct();
	}

	public void updateTheSubctegory() {

		long categoryId = categoryService.getCategoryByName(categorySelect).getId();
		long subCategoryId = subCategoryService.addSubCategory(subCategory);
		subCategoryService.affectSubCategoryToCategory(categoryId, subCategoryId);
if(subCategoryImage!=null){		
		imageService.affectImageToSubCategory(subCategoryImageId,subCategoryId);
}
subCategory = new SubCategory();
navigateTo.caregoryBrand();
	}

	public String updateTheBrand() {

		long brandId = brandService.addBrand(brand);
		if(brandLogo!=null){
		 brandLogoId = imageService.uploadFile("brand", "image", brandLogo);
		imageService.affectImageToBrand(brandLogoId, brandId);
		}
		brand = new Brand();
		return navigateTo.caregoryBrand();
	}

	public String updateTheCategory() {
		long categoryId = categoryService.updateCategory(category);
		if(categoryImage!=null){
		//long id categoryImageId= imageService.uploadFile("category", "image", categoryImage);
		imageService.affectImageToCategory(categoryImageId, categoryId);
		}
		return navigateTo.caregoryBrand();
		
	}

	public void verifyBarCodeValue() {
		if (productBarCode != null && productBarCode.trim().startsWith("619") && productBarCode.length() > 8) {
			System.out.println("verifyBarCodeValue == true");
			this.showForm2 = "block";
			product.setReference(productBarCode);
			RequestContext.getCurrentInstance().execute("Toast.fire({ " + "type: \'success\', "
					+ "title: \'Lorem ipsum dolor sit amet, consetetur sadipscing elitr.\'});");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("saret mochkla fi reload ");
				e.printStackTrace();
			}

			// return "/pages/adminSide/productViews/addProd.jsf";
			// return"redirect: return "redirect:/login/editprofile.jsf";";
			// return "redirect:/pages/adminSide/productViews/addProd.jsf";
		} else {
			this.showForm2 = "none";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Add Only Tunisian Product"));
			// return "/pages/adminSide/productViews/addProd.jsf";
		}
	}

	int mm = 0;

	public void handleBarCode(FileUploadEvent event) {
		mm++;
		System.err.println("hhhhhhhhhhhhhhhhh handleBarCode == " + mm);
		fichier = event.getFile();
		this.productBarCode = imageService.verifyBarCode(fichier);
		verifyBarCodeValue();
	}

	public void handleBand(FileUploadEvent event) {
		brandLogo = event.getFile();
		 brandLogoId = imageService.uploadFile("brand", "image", brandLogo);
	}

	public void handleCategory(FileUploadEvent event) {
		System.out.println("handleCategory ");
		categoryImage = event.getFile();
		categoryImageId= imageService.uploadFile("category", "image", categoryImage);
		System.err.println("categoryImageId  " +categoryImageId);

	}

	public void handleSubCategory(FileUploadEvent event) {
		subCategoryImage = event.getFile();
		 subCategoryImageId = imageService.uploadFile("subCategory", "image", subCategoryImage);
	}

	public boolean hasColor() {
		if (categorySelect.equals("marche") || categorySelect.equals("alimentation"))
			return false;
		return true;
	}

	public boolean hasSize() {
		if (categorySelect.equals("mode"))
			return true;
		return false;
	}

	public boolean hasDate() {
		if (categorySelect.equals("marche") || categorySelect.equals("alimentation"))
			return true;
		return false;
	}

	public void updateForms() {
		if (hasColor())
			showColor = "block";
		else
			showColor = "none";

		if (hasSize())
			showSize = "block";
		else
			showSize = "none";

		if (hasDate())
			showDate = "block";
		else
			showDate = "none";
	}

	public void setForm(int ss) {
		System.out.println("setFormsetFormsetForm");
		if (ss == 1) {
			showBarCodePanel = "block";
			showBarCodePanelImage = "none";
			showForm2 = "none";
		}
		if (ss == 2) {
			showBarCodePanel = "none";
			showBarCodePanelImage = "block";
			showForm2 = "none";

		}

	}

	public void doShow(int i) {
		System.out.println("anehou elli yben  = "+i);
		if (i == 1) {

			productStyle = "block";
			brandStyle = "none";
			categoryStyle = "none";
			subCategoryStyle = "none";

		}

		if (i == 2) {

			productStyle = "none";
			brandStyle = "block";
			categoryStyle = "none";
			subCategoryStyle = "none";

		}
		if (i == 3) {

			productStyle = "none";
			brandStyle = "none";
			categoryStyle = "block";
			subCategoryStyle = "none";

		}
		if (i == 4) {

			productStyle = "none";
			brandStyle = "none";
			categoryStyle = "none";
			subCategoryStyle = "block";

		}

	}
	public void deleteTheProduct(long id) {
		System.out.println("id prod "+id);
		productService.deleteProduct(id);
		
		System.out.println("product deleted successfuly  ");
	}
}
