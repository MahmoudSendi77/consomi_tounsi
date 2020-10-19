package tn.esprit.spring.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.PromosMagazin;
import tn.esprit.spring.service.CatalogueService;
import tn.esprit.spring.service.IImageService;
import tn.esprit.spring.service.IProductService;
import tn.esprit.spring.service.IPromosMagazinService;

//@Controller(value = "promosController")
//@ELBeanName(value = "promosController")

@Controller(value = "promoUpdateController")
@ELBeanName(value = "promoUpdateController")
@Join(path = "/admin/managePromotionUpdate", to = "/pages/adminSide/promos/updatePromos.jsf")
public class PromosUpdateController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	IPromosMagazinService promosService;
	@Autowired
	IProductService productService;
	@Autowired
	IImageService imageService;
	@Autowired
	CatalogueService catalogueService;
	// @Inject
	// SigninJsf signinJsf;
	private Long idCurrentUser;
	private UploadedFile filepromos;
	private Long filepromosId;
	// @Autowired
	// IFactureSupplierService factureSupplierService;

	private List<Product> catalogueImages = new ArrayList<>();
	private List<Product> catalogueImagesDropped = new ArrayList<>();
	private Product selectedImage;
	private List<Long> listImages = new ArrayList<>();
	private PromosMagazin promos=new PromosMagazin();
	private PromosMagazin selectedPromos=new PromosMagazin();
	private List<PromosMagazin> listPromos = new ArrayList<PromosMagazin>();

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


	public PromosMagazin getSelectedPromos() {
		return selectedPromos;
	}

	public void setSelectedPromos(PromosMagazin selectedPromos) {
		this.selectedPromos = selectedPromos;
	}

	public PromosMagazin getPromos() {
		return promos;
	}

	public void setPromos(PromosMagazin promos) {
		this.promos = promos;
	}

	public Long getIdCurrentUser() {
		// idCurrentUser = signinJsf.nameuser() ? signinJsf.idusercurrent() :
		// 0L;
		return idCurrentUser;
	}

	public void setIdCurrentUser(Long idCurrentUser) {
		this.idCurrentUser = idCurrentUser;
	}

	public List<PromosMagazin> getListPromos() {
		listPromos=promosService.getAllPromos();
		return listPromos;
	}

	public List<Product> getCatalogueImages() {
		return catalogueImages;
	}

	public void setCatalogueImages(List<Product> catalogueImages) {
		this.catalogueImages = catalogueImages;
	}

	public List<Product> getCatalogueImagesDropped() {
		return catalogueImagesDropped;
	}

	public void setCatalogueImagesDropped(List<Product> catalogueImagesDropped) {
		this.catalogueImagesDropped = catalogueImagesDropped;
	}

	public Product getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(Product selectedImage) {
		this.selectedImage = selectedImage;
	}

	public List<Long> getListImages() {
		return listImages;
	}

	public void setListImages(List<Long> listImages) {
		this.listImages = listImages;
	}

	public void setListPromos(List<PromosMagazin> listPromos) {
		this.listPromos = listPromos;
	}

	@PostConstruct
	public void updateList() {
		listPromos = promosService.getCurrentPromos();

		catalogueImages = productService.getNotAffectedProductToAisle();
		// catalogueImagesDropped = productService.getProductByAisleId(1L);
		System.out.println("list images droppable" + catalogueImages);
	}

	public void onImageDrop(DragDropEvent ddEvent) {

		System.out.println("onCarDrop  image ???????");
		System.err.println("clvklkv,lv,flvk");
		Product im = (Product) ddEvent.getData();

		catalogueImagesDropped.add(im);
		catalogueImages.remove(im);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("saret mochkla fi reload ");
			e.printStackTrace();
		}
	}

	public void refresh() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("saret mochkla fi reload ");
			e.printStackTrace();
		}
	}

	public void addImages() {

		System.out.println("za3ma jeb 7aja " + catalogueImages);
		System.err.println("haw d5al ech 7ajtek " + catalogueImages);
	}

	public void affectToAisle() {// affectProducts

		for (int i = 0; i < catalogueImagesDropped.size(); i++) {
			productService.affecterProductToAisle(1L, catalogueImagesDropped.get(i).getId());
		}
	}

	public String addThePromos(){
		System.err.println("addThePromos");
		System.err.println("addThePromos"+promos);
		if(date1!=null && date1!=""&& date2!=null && date2!=""){
		promos.setDateBegin(new Date(Date.parse(date1)));
		
		promos.setDateEnd(new Date(Date.parse(date2)));}
		long id = promosService.addPromos(promos);
		if(filepromos!=null)
			imageService.affectImageToPromos(filepromosId, id);
		promos =new PromosMagazin();
		date1=date2="";
		filepromosId=null;
		filepromos=null;
		NavigateTo n=new NavigateTo();
		return n.adminHomePromos();
	}
	
	public void handlePromosFile(FileUploadEvent event) {
		filepromos = event.getFile();
		filepromosId = imageService.uploadFile("promos", "image", filepromos);
	}
}
