package tn.esprit.spring.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpServletRequest;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CartRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.CurrentUser;
import tn.esprit.spring.security.UserPrincipal;
import tn.esprit.spring.service.CartService;
import tn.esprit.spring.service.CommandService;
import tn.esprit.spring.service.UserService;

@Join(path = "/login", to = "/login/name.jsf")
@Controller(value = "cartControllerimpl")
@ELBeanName(value = "cartControllerimpl")
@RestController
public class CartControllerImpl {

	@Autowired
	CartService cartService;
	@Autowired
	UserService UserService;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SigninJsf signinJsf;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommandService commandService;
	
	private Long id;
	private float montanthors;
	private float montantTTC;
	private float montantTVA;
	private int number;
	private List<Product> produits;
	private String name;
	private Float price;
	private Float total;
	private Cart cart;
	private int quantiteCartClient;
	
	//////////////////////////////////////////////////////////////////////////////// getter and setter///////////////////////
	
	public int getQuantiteCartClient() {
		return quantiteCartClient;
	}
	public void setQuantiteCartClient(int quantiteCartClient) {
		this.quantiteCartClient = quantiteCartClient;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Long getId() {
		System.out.println("id1111");
		return id;
	}
	public void setId(Long id) {
		System.out.println("id1111");
		this.id = id;
	}
	public float getMontanthors() {
		return montanthors;
	}
	public void setMontanthors(float montanthors) {
		this.montanthors = montanthors;
	}
	public float getMontantTTC() {
		return montantTTC;
	}
	public void setMontantTTC(float montantTTC) {
		this.montantTTC = montantTTC;
	}
	public float getMontantTVA() {
		return montantTVA;
	}
	public void setMontantTVA(float montantTVA) {
		this.montantTVA = montantTVA;
	}
	public List<Product> getProduits() {
		return produits;
	}
	public void setProduits(List<Product> produits) {
		this.produits = produits;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	List<Product> prodIntermediare = new ArrayList<>();
	
	////////////////////////////////////////////methode///////////////////////////////////
	public String adminPage(){
		String navigateTo = "";
		navigateTo = "/pages/adminhomepage.xhtml?faces-redirect=true";
		return navigateTo;
	}
	public 	Cart getSearchCartByUser() {
	System.out.println("testtttttttttt");
	return   cartService.SearchCartByUser(signinJsf.idusercurrent());
	}	
	public Long searchCartByUser() {
		return  cartService.SearchCartByUser(signinJsf.idusercurrent()).getId();
		}
	public List<Product> getAfficheProductInCart() {
		Cart carts = cartService.SearchCartByUser(signinJsf.idusercurrent());
		return cartRepository.findById(carts.getId()).get().getProduits();
	}
	public void removeProductFromCart(Long productId) {
		String result = "product doesn't exist";
		Product Initprod = new Product();
		Cart carts = cartService.SearchCartByUser(signinJsf.idusercurrent());
		Product product = productRepository.findById(productId).get();
		// recherche product dans le cart
		int size = carts.getProduits().size();
		for (int index = 0; index < size; index++) {
			if (carts.getProduits().get(index).getId() == productId) {
				cartService.deleteProductFromCart(carts.getId(), productId);
				result = "product has been deleted";
			}

		}

	}
	public String cartPage() {
		System.err.println("bbbbbbbbbbbbbb");
		String navigateTo = "";
		navigateTo = "/pages/cart.xhtml?faces-redirect=true";
		System.err.println("aaaaaaaaaaaaaa");

		return navigateTo;
	}
	public String navigateToCheckout() {
		System.err.println("checkout page");
		String navigateTo = "";
		navigateTo = "/pages/checkout.xhtml?faces-redirect=true";
		System.err.println("******CHEKOUT**************");
		System.out.println("liste prod"+prodIntermediare);
		System.out.println("liste prod size : "+prodIntermediare.size());

		return navigateTo;
	}
	public List<Product> SelectQuantity(Long idProduct, Long idCart, Long idUser, int quantiteProd) {
		// init
		
		Cart cart = cartRepository.findById(idCart).get();
		Product product = productRepository.findById(idProduct).get();
		User user = userRepository.findById(idUser).get();
		List<Product> prods = new ArrayList<>();
		Product Initprod = new Product();
		
		// recherche product dans le cart
		int size = cart.getProduits().size();
		for (int index = 0; index < size; index++) {
			if (cart.getProduits().get(index).getId() == idProduct) {
				//product exist
				//add to the   of product
				float prixtotal = cart.getMontantTTC() + product.getPrice();
				for (int i = 0; i < quantiteProd; i++) {
					prods.add(productRepository.findById(idProduct).get());
				}
			
				
					
			}				
		}
		

		return prods;

	}	
	public void updateCantidad(Long price,Long idProd) {
		//Product produit = productRepository.findById(idProduct).get();
		System.err.println("update price");
		System.err.println("id prod"+idProd);
		this.searchCartByUser();
		System.out.println("test : "+this.searchCartByUser());
	
		if(prodIntermediare   != null){
			int taille = prodIntermediare.size();
			System.out.println("before remove");
			int taille1 = prodIntermediare.size();
			////////////remove///////////////////
			
			for (int i= 0; i < taille1; i++) {
				
			for (int index = 0; index < taille; index++) {
				if(prodIntermediare.get(index).getId()==idProd){
					prodIntermediare.remove(index);
					break;
				}
			}
			}
			
			////////////end remove/////////////////////
			///////////start add//////////////////////
			int taille2 = prodIntermediare.size();
			System.out.println("after remove : "+taille2);
			int size = this.SelectQuantity(idProd, this.searchCartByUser(), signinJsf.idusercurrent(), number).size();
			for (int index = 0; index < size; index++) {

						prodIntermediare.add(this.SelectQuantity(idProd, this.searchCartByUser(), signinJsf.idusercurrent(), number).get(index));
			}
			System.out.println("after add");
			////////////end add/////////////////////////
		}
		
		
		else{
		int size = this.SelectQuantity(idProd, this.searchCartByUser(), signinJsf.idusercurrent(), number).size();
		for (int index = 0; index < size; index++) {

					prodIntermediare.add(this.SelectQuantity(idProd, this.searchCartByUser(), signinJsf.idusercurrent(), number).get(index));
		}			
		}
		
		
		
		
		Float z = (float) number;
		total = z * price;
		
	}
	
	public float calcul() {
		return  cartService.calculHT(prodIntermediare);
		}	
	public float calcultva(){
		return cartService.calculMontantTva(prodIntermediare);
	}
	public float calcultc(){
		return cartService.calculTTC(prodIntermediare);
	}
	public List<Product> getAffichageProductForPayment(){
		return prodIntermediare;
	}
	public void afficheadd(Long id){
		
		System.out.println("quantite +1");
		Product p = productRepository.findById(id).get() ;
		int x = p.getQuantitCart()+1;
		float prix = x * p.getPrice();
		System.out.println(x);
		p.setPrixQuantiteCart(prix);
		p.setQuantitCart(x);
		productRepository.save(p);
		
	}
	
	public void affichemin(Long id){
		
		System.out.println("quantite +1");
		Product p = productRepository.findById(id).get() ;
		int x = p.getQuantitCart()-1;
		float prix = x * p.getPrice();
		System.out.println(x);
		p.setPrixQuantiteCart(prix);
		p.setQuantitCart(x);
		productRepository.save(p);
		
	}	
	public float calculTvaCart(){
		Cart cart = cartService.SearchCartByUser(signinJsf.idusercurrent());
		return cartService.calculMontantTvaCart(cart.getProduits());
	}
	public float calculHtCart(){
		Cart cart = cartService.SearchCartByUser(signinJsf.idusercurrent());
		return cartService.calculHTCart(cart.getProduits());
		
	}
	public float calculMTC(){
		Cart cart = cartService.SearchCartByUser(signinJsf.idusercurrent());
		return cartService.calculTTCCart(cart.getProduits());
		
	}		
	public String checkoutCartPage(){
		//Product produit = productRepository.findById(idProduct).get();
				System.err.println("checkout");
				Cart cart = cartService.SearchCartByUser(signinJsf.idusercurrent());
			
				if(prodIntermediare != null){
					prodIntermediare.clear();
					int taille = cart.getProduits().size();
					for (int index = 0; index < taille; index++) {
						int n = cart.getProduits().get(index).getQuantitCart();
						for(int ind = 0; ind < n; ind++){
							prodIntermediare.add(cart.getProduits().get(index));
						}
						
					}
					System.out.println("after add");
					////////////end add/////////////////////////
				}
				
				
				else{
					
					int taille = cart.getProduits().size();
					for (int index = 0; index < taille; index++) {
						int n = cart.getProduits().get(index).getQuantitCart();
						for(int ind = 0; ind < n; ind++){
							prodIntermediare.add(cart.getProduits().get(index));
						}
						
					}
					System.out.println("after add");
				}
				
				
				
				String navigateTo = "";
				navigateTo = "/pages/checkout.xhtml?faces-redirect=true";
				return navigateTo;
		
	}

	public Map<String,Product> prodRecommondName(){
		System.out.println("karimmmmmmmmmmmmmmmmmmmmmm");
		Cart cart = cartService.SearchCartByUser(signinJsf.idusercurrent());
		List<Product> prodInit = new ArrayList<>();
		List<Product> prods = new ArrayList<>();
		List<Product> prodTest = new ArrayList<>();
		Map ProductRepition = new HashMap();
		
		//make a list with a recommended product (avec repition des produit) 
		int size=cart.getProduits().size();
		for(int index = 0; index < size; index++){
			Product p =commandService.RecommandedProduct(cart.getProduits().get(index).getId());
			
			ProductRepition.put(cart.getProduits().get(index), p);
			
		}
		
		return ProductRepition;
	}
	
	
	public String addProductToCart(Long idProduct){
		Cart cart = cartService.SearchCartByUser(signinJsf.idusercurrent());
		cartService.addToCart(idProduct, cart.getId(), signinJsf.idusercurrent());
		String navigateTo = "";
		FacesMessage facesMessage =

				new FacesMessage("the product have been added to cart successfully");
		FacesContext.getCurrentInstance().addMessage("form:bbbt", facesMessage);
		//navigateTo = "/pages/frontend/productViews/listProductView.xhtml?faces-redirect=true";
		return null;

	}
	

}
