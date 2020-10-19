package tn.esprit.spring.servlet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "navigateTo")
@ELBeanName(value = "navigateTo")
@RequestScoped
public class NavigateTo {
	public String goToProductHome() {

		return "/pages/frontend/productViews/listProductView.xhtml?faces-redirect=true";
	}

	public String navigateProductTo(String page) {
		return "/pages/frontend/productViews/" + page + ".xhtml?faces-redirect=true";
	}

	public String navigateToProductDetails(String productBarCode) {
		return "/pages/frontend/productViews/homeProductView.xhtml?faces-redirect=true";
	}

	public String navigateToSideBare() {
		return "/pages/frontend/productViews/productSideBarView.xhtml?faces-redirect=true";
	}
	
	public String supplierHome() {
		return "/pages/supplier/supplierHome.xhtml?faces-redirect=true";
	}

	public String adminHomeProduct() {
		return "/pages/adminSide/productViews/adminHomeProductView.xhtml?faces-redirect=true";
	}

	public String adminHomeAds() {
		return "/pages/adminSide/ads/adminHomeAdsView.xhtml?faces-redirect=true";
	}

	public String manageAds() {
		return "/pages/adminSide/ads/manageAds.xhtml?faces-redirect=true";
	}

	public String updateAds() {
		return "/pages/adminSide/ads/updateAds.xhtml?faces-redirect=true";
	}

	public String adminHomePromos() {
		return "/pages/adminSide/promos/adminHomePromosView.xhtml?faces-redirect=true";
	}

	public String managePromos() {
		return "/pages/adminSide/promos/addPromos.xhtml?faces-redirect=true";
	}
	
	public String updatePromos() {
		return "/pages/adminSide/promos/updatePromos.xhtml?faces-redirect=true";
	}

	public String toLogin() {

		return "/pages/adminSide/productViews/addProd.jsf?faces-redirect=true";

	}

	public String addProduct() {

		return "/pages/adminSide/Store/manageCatalogue.jsf?faces-redirect=true";
	}

	public String updateProduct() {
		System.out.println("to update");
		return "/pages/adminSide/productViews/updateProduct.jsf";
	}

	public String updateProduct1() {
		System.out.println("to update1");
		return "/pages/adminSide/productViews/updateProduct.jsf";
	}

	public String updateProduct2() {
		System.out.println("to update2");
		return "/pages/adminSide/productViews/updateProduct.jsf";
	}

	public String manageCatalogue() {
		return "/pages/adminSide/Store/manageCatalogue.jsf?faces-redirect=true";
	}

	public String manageProduct() {
		return "/pages/adminSide/productViews/manageProducts.jsf";
	}

	public String showAisles() {
		return "/pages/adminSide/aislesViews/adminHomeAislesView.jsf?faces-redirect=true";
	}

	public String addAisle() {
		return "/pages/adminSide/aislesViews/addAisle.jsf";
	}

	public void addProduct2() {
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
				FacesContext.getCurrentInstance(), null, "/pages/adminSide/productViews/addProd.xhtml");
	}

	public String caregoryBrand() {
		return "/pages/adminSide/Store/addCategory.jsf?faces-redirect=true";
	}

	public String navigateToHomeProduct() {
		return "/pages/frontend/productViews/homeProductView.xhtml?faces-redirect=true";
	}

	public String navigateTolistDeroulanet() {
		return "/pages/frontend/productViews/listDeroulanteProduct.xhtml?faces-redirect=true";
	}

	public String navigateToPromos() {
		return "/pages/frontend/productViews/promosProductView.xhtml?faces-redirect=true";
	}

	public void navigateTo() {
		FacesMessage facesMessage =

				new FacesMessage("sa77it lknvlnv ");

		FacesContext.getCurrentInstance().addMessage("myfform1:llo", facesMessage);

	}
}
