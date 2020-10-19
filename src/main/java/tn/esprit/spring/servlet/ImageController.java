package tn.esprit.spring.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.PromosMagazin;
import tn.esprit.spring.service.CatalogueService;
import tn.esprit.spring.service.IImageService;
import tn.esprit.spring.service.IProductService;
import tn.esprit.spring.service.IPromosMagazinService;


//@RestController
//@RequestMapping("/image")
@Scope(value = "session")
@Controller(value = "imageController")
@ELBeanName(value = "imageController")
@Join(path = "/admin/manageCatalogue", to = "/pages/adminSide/Store/manageCatalogue.jsf")

public class ImageController {
	@Autowired
	IImageService imageService;
	IProductService productService;
	@Inject
	IPromosMagazinService promosService;
	@Autowired
	CatalogueService catalogueService;

	private List<Image> catalogueImages =new ArrayList<>();
	private List<Image> catalogueImagesDropped =new ArrayList<>();
	private Image selectedImage; 
	private List<Long> listImages =new ArrayList<>();
	
	private List<PromosMagazin> listPromos =new ArrayList<PromosMagazin>();

	public List<PromosMagazin> getListPromos() {
		return listPromos;
	}
	
	public List<Image> getCatalogueImagesDropped() {
		return catalogueImagesDropped;
	}

	public void setCatalogueImagesDropped(List<Image> catalogueImagesDropped) {
		this.catalogueImagesDropped = catalogueImagesDropped;
	}

	public Image getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(Image selectedImage) {
		this.selectedImage = selectedImage;
	}

	public List<Image> getCatalogueImages() {
		System.out.println("list images droppable bel get"+catalogueImages);
		
		return catalogueImages;
	}

	public void setCatalogueImages(List<Image> catalogueImages) {
		this.catalogueImages = catalogueImages;
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
	public void updateList(){
		listPromos= promosService.getCurrentPromos();
		
		System.out.println("list images droppable"+catalogueImages);
	}
	
	public void onImageDrop(DragDropEvent ddEvent) {
       
		System.out.println("onCarDrop  image ???????");
		System.err.println("clvklkv,lv,flvk");
		Image im =(Image) ddEvent.getData();
  
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
	
	public void handleCatalogueFile(FileUploadEvent event){
		System.out.println("catalogue++++++ ");
		Long id=imageService.uploadFile("catalogue","image", event.getFile());
		listImages.add(id);
		System.err.println(listImages);
		catalogueImages.add(imageService.getImageById(id));
		System.err.println(catalogueImages);
		
	}
	
	public String refresh(){
		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("saret mochkla fi reload ");
				e.printStackTrace();
			}
			return "";
	}
	public void addImages(){
		
		System.out.println("za3ma jeb 7aja "+catalogueImages);
		System.err.println("haw d5al ech 7ajtek "+catalogueImages );
	}
public void createCatalogue(){
		
	try {
		catalogueService.updateCatalogue(catalogueImagesDropped);
		System.err.println("c bon :)))))))))))))");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.err.println("mat3addech !!!!!!!!!!!!!!");
	}
	
	
	}
		
	
}
