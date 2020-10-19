package tn.esprit.spring.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.DragDropEvent;

import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.PromosMagazin;
import tn.esprit.spring.service.CatalogueService;
import tn.esprit.spring.service.IAisleService;
import tn.esprit.spring.service.IImageService;
import tn.esprit.spring.service.IProductService;
import tn.esprit.spring.service.IPromosMagazinService;

//@Controller(value = "promosController")
//@ELBeanName(value = "promosController")

@Named("promosController")
@ViewScoped
public class PromosMagazinController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	IPromosMagazinService promosService;
	@Inject
	IProductService productService;
	@Inject
	IImageService imageService;
	@Inject
	CatalogueService catalogueService;
	@Inject
	IAisleService aisleService;
	// @Inject
	// SigninJsf signinJsf;
	private Long idCurrentUser;
	// @Autowired
	// IFactureSupplierService factureSupplierService;

	private List<Product> catalogueImages = new ArrayList<>();
	private List<Product> catalogueImagesDropped = new ArrayList<>();
	private Product selectedImage;
	private List<Long> listImages = new ArrayList<>();
	private Map<Long, Long> listaisles;

	public Map<Long, Long> getListaisles() {
		listAisle = aisleService.getAllAisle();
		listaisles = new HashMap<>();

		for (Aisle i : listAisle) {

			listaisles.put(i.getId(), i.getId());

		}
		System.err.println(listaisles);
		return listaisles;
	}

	public void setListaisles(Map<Long, Long> listaisles) {
		this.listaisles = listaisles;
	}

	private List<PromosMagazin> listPromos = new ArrayList<PromosMagazin>();

	private List<Aisle> listAisle = new ArrayList<Aisle>();
	private Long aisleId;

	public List<Aisle> getListAisle() {
		listAisle = aisleService.getAllAisle();
		System.err.println("lisaisle   = " + listAisle);
		return listAisle;
	}

	public void setListAisle(List<Aisle> listAisle) {
		this.listAisle = listAisle;
	}

	public Long getAisleId() {
		return aisleId;
	}

	public void setAisleId(Long aisleId) {
		this.aisleId = aisleId;
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
		System.err.println("aise id to affedrted    = " + aisleId);
		System.out.println("product to affect   " + catalogueImagesDropped);
		if (aisleId != null)
			for (int i = 0; i < catalogueImagesDropped.size(); i++) {

				productService.affecterProductToAisle(aisleId, catalogueImagesDropped.get(i).getId());
			}
		NavigateTo n =new NavigateTo();
		n.adminHomeProduct();
	}

}
