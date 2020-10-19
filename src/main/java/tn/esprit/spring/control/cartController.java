package tn.esprit.spring.control;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.CartRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.CurrentUser;
import tn.esprit.spring.security.UserPrincipal;
import tn.esprit.spring.service.CartService;
import tn.esprit.spring.service.UserService;


@RestController
public class cartController {

	@Autowired
	CartService cartService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserService UserService;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;

	
	
	// Add to cart
	// http://localhost:8081/SpringMVC/servlet/cart/addtoCart/1/1
	@PutMapping("/addtoCart/{produitId}/{cartId}/{quantiteProd}")
	@PreAuthorize("hasRole('USER')")
	public String addtoCart(@PathVariable("produitId") Long produitId, @PathVariable("cartId") Long cartId,
			@PathVariable("quantiteProd") Integer quantiteProd, @CurrentUser UserPrincipal currentUser) {
		for (int index = 0; index < quantiteProd; index++) {
			String result = cartService.addToCart(produitId, cartId, currentUser.getId());
		}
		return "product is added";
// return currentUser.getName();
	}

	// delete Cart
	// http://localhost:8081/SpringMVC/servlet/cart//DeleteCart/1/1/1
	@PutMapping("/DeleteCart/{produitId}/{cartId}")
	@PreAuthorize("hasRole('USER')")
	public String DeleteCart(@PathVariable("cartId") Long cartId) {
		return cartService.removeCart(cartId);
	}

	// select le nombre de produit a supprimer du panier
	// http://localhost:8081/SpringMVC/servlet/cart/updateQuantite/2/2/3
	@DeleteMapping("/updateQuantite/{cartId}/{productId}/{quantiteProduct}")
	@ResponseBody
	@PreAuthorize("hasRole('USER')")
	public String DeleteProductFromCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId,
			@PathVariable("quantiteProduct") Integer quantite) {
		for (int index = 0; index < quantite; index++) {
			String x = cartService.enleverProduit(cartId, productId, quantite);
		}
		return "upda";
	}

	// get all product in the cart
	// http://localhost:8081/SpringMVC/servlet/cart/getAllProductByCart/{idCart}
	@GetMapping(value = "getAllProductByCart/{idCart}")
	@ResponseBody
	public List<Product> getAllProductByCart(@PathVariable("idCart") Long idCart) {
		return cartService.getAllProductByCart(idCart);
	}

//	// calculer montant TTC
//	// http://localhost:8081/SpringMVC/servlet/cart/calculMontantTTC/{idCart}
//	@GetMapping(value = "calculMontantTTC/{idCart}")
//	public float calculMontantTTC(@PathVariable("idCart") Long idCart){
//		return cartService.calculTTC(idCart);
//	}
//	
//	// calculer montant HT
//	// http://localhost:8081/SpringMVC/servlet/cart/calculMontantHT/{idCart}
//	@GetMapping(value = "calculMontantHT/{idCart}")
//	public float calculMontantHT(@PathVariable("idCart") Long idCart){
//		return cartService.calculHT(idCart);
//	}
//	
//	// calculer montant TVA
//		// http://localhost:8081/SpringMVC/servlet/cart/calculMontantTVA/{idCart}
//		@GetMapping(value = "calculMontantTVA/{idCart}")
//		public float calculMontantTVA(@PathVariable("idCart") Long idCart){
//			return cartService.calculMontantTva(idCart);
//		}
//		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////JSF//////////////////////////////////////////////////////////////////////////////////////////////////
		
		//search cart by user
		// http://localhost:8081/SpringMVC/servlet/cart/SearchCartByUser
		@GetMapping("/SearchCartByUser")
		public Cart SearchCartByUser(@CurrentUser UserPrincipal currentUser) {
			return cartService.SearchCartByUser(currentUser.getId());
		}
		
		
		@GetMapping("/getAllProduct/{idCart}")
		public List<Product> afficheProductInCart(Long idCart){
			return cartRepository.findById(idCart).get().getProduits();
		}
		
		// Add to cart
		// http://localhost:8081/SpringMVC/servlet/cart/addProductToCart/2/1
		@PutMapping("/addProductToCart/{cartId}/{produitId}")
		public List<Product> addProductToCart(@PathVariable("produitId") Long produitId, @PathVariable("cartId") Long cartId, @CurrentUser UserPrincipal currentUser) {
				 
			return cartService.addProductToCart(cartId, produitId, currentUser.getId());
	// return currentUser.getName();
		}

		// select le  produit a supprimer du panier
		// http://localhost:8081/SpringMVC/servlet/cart/removeProductFromCart/2/3
		@DeleteMapping("/removeProductFromCart/{cartId}/{productId}")
		@ResponseBody
		public String removeProductFromCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
			String result = "product doesn't exist";
			Product Initprod = new Product();
			Cart cart = cartRepository.findById(cartId).get();
			Product product = productRepository.findById(productId).get();
			// recherche product dans le cart
			int size = cart.getProduits().size();
			for (int index = 0; index < size; index++) {
				if (cart.getProduits().get(index).getId() == productId) {
					cartService.deleteProductFromCart(cartId, productId);
					result = "product has been deleted";
				}
				
			}
			return result;
		}	

		//search product in cart(implemented in the removeProductFromCart methode) 
		// http://localhost:8081/SpringMVC/servlet/cart/SearchProductInCart/2/3
		@GetMapping("/SearchProductInCart/{cartId}/{productId}")
		public Product SearchProductInCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId){
			return cartService.searchProductInCart(cartId, productId);
		}

		//select quantity of product
		// http://localhost:8081/SpringMVC/servlet/cart/SelectQuantity/2/3/2
		@PutMapping("/SelectQuantity/{idCart}/{idProduct}/{quantiteProd}")
		@PreAuthorize("hasRole('USER')")
		public List<Product> SelectQuantity(@PathVariable("idCart") Long idCart
				, @PathVariable("idProduct") Long idProduct,@PathVariable("quantiteProd")Integer quantiteProd, @CurrentUser UserPrincipal currentUser){
			return cartService.SelectQuantity(idProduct, idCart, currentUser.getId(), quantiteProd);
			
		}

		
}
