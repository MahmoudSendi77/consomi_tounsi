package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CartRepository;

import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.repository.ProductRepository;

import tn.esprit.spring.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepeository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	
	//recherche cart par user
	
	public Cart SearchCartByUser(Long idUser){
		return cartRepeository.SearchCartByUser(idUser);	
	}
	
	//ajouter un produit a un panier
	@Transactional
	public String addToCart(Long produitId, Long cartId, Long idUser) {
		// TODO Auto-generated method stub
		Cart cartManagedEntity = cartRepeository.findById(cartId).get();
		Product produitManagedEntity = productRepository.findById(produitId).get();
		User userManagedEntity = userRepository.findById(idUser).get();
		// prix de la carte
		
		float prixtotal = cartManagedEntity.getMontantTTC() + produitManagedEntity.getPrice();
		
		//varible pour le calcul des montant et taux de tva
		float MontantTTC = 0;
		float MontantHT = 0;
		float prixHTProd = 0;
		float MontantTTVA = 0;
		float prixHTProd1 = 0;
		float prixHTProd2 = 0;
		float tauxtva = 0;
		
		
		
		cartManagedEntity.setUser(userManagedEntity);
		if (cartManagedEntity.getProduits() == null) {
			List<Product> prodcts = new ArrayList<>();
			prodcts.add(produitManagedEntity);
			cartManagedEntity.setProduits(prodcts);
		} else {
			cartManagedEntity.getProduits().add(produitManagedEntity);

		}
		// calcul montant
		
		if (cartManagedEntity.getProduits() != null) {
			int size = cartManagedEntity.getProduits().size();
			for (int index = 0; index < size; index++) {
				//
				MontantTTC = MontantTTC + cartManagedEntity.getProduits().get(index).getPrice();
				//
				prixHTProd=cartManagedEntity.getProduits().get(index).getPrice() / ( 1+( cartManagedEntity.getProduits().get(index).getTva()/100 ) );
				MontantHT= MontantHT + prixHTProd;
				//
				prixHTProd1=cartManagedEntity.getProduits().get(index).getPrice() / ( 1+( cartManagedEntity.getProduits().get(index).getTva()/100 ) );
				prixHTProd2=prixHTProd1 * ( cartManagedEntity.getProduits().get(index).getTva()/100 ) ;
				MontantTTVA= MontantTTVA + prixHTProd2;
				
			}

		} else {
			MontantTTC = 0;
			MontantHT = 0;
			MontantTTVA = 0;
		}
		
		//calcul taux tva
		if (cartManagedEntity.getProduits() != null) {
			
			tauxtva = ((MontantTTC-MontantHT)/MontantHT)*100;
		} else {
			tauxtva = 0;
		}
		
		
		cartManagedEntity.setMontantTTC(MontantTTC);
		cartManagedEntity.setMontantHT(MontantHT);
		
		

		cartRepeository.save(cartManagedEntity);
		return "product is added ";

	}

	// suprimer le panier
	public String removeCart(Long id) {
		cartRepeository.deleteById(id);
		return "cart : deleted avec succes ";
	}

	// affichage les produit dans un panier
	public List<Product> getAllProductByCart(Long cartId) {
		Cart dep = cartRepeository.findById(cartId).get();
		return dep.getProduits();
	}

	// get cart by Id
	public Cart getCartById(Long cartId) {
		return cartRepeository.findById(cartId).get();
	}

	// Enlever produit au panier
	@Transactional
	public String enleverProduit(Long cartId, Long produitId, Integer quantiteEnvlever) {

		// TODO Auto-generated method stub
		Cart cartManagedEntity = cartRepeository.findById(cartId).get();
		Product produitManagedEntity = productRepository.findById(produitId).get();

		// recherche product dans le cart

		int employeNb = cartManagedEntity.getProduits().size();
		for (int index = 0; index < employeNb; index++) {
			if (cartManagedEntity.getProduits().get(index).getId() == produitId) {
				cartManagedEntity.getProduits().remove(produitManagedEntity);
				break;
			}
		}

		// Integer count = 0;

		// Integer nouvelleQuantite = produitManagedEntity.getQuantity() +
		// quantiteEnvlever;
		// produitManagedEntity.setQuantity(nouvelleQuantite);

		return "panier is updated";

	}

	// quantité prod dans le panier
	@Transactional
	public Integer quantiteProduitPanier(Long idCart, Long idProduit) {

		Cart cart = cartRepeository.findById(idCart).get();
		Product prod = productRepository.findById(idProduit).get();
		List<Product> CartItem = new ArrayList();
		Integer apparision = 0;
		int size = cart.getProduits().size();
		for (int index = 0; index < size; index++) {
			if (cart.getProduits().get(index).getId() == idProduit) {
				CartItem.add(cart.getProduits().get(index));
				break;
			}
		}

		return CartItem.size();
	}

	//calcul le montant TTC d'un panier
	public float calculTTC(List<Product> prod) {
		

		float MontantTTC = 0;

		if (prod != null) {
			int size = prod.size();
			for (int index = 0; index < size; index++) {
				MontantTTC = MontantTTC + prod.get(index).getPrice();
			}

		} else {
			MontantTTC = 0;
		}
		return MontantTTC;
	}

	//calcul le montant HT d'un panier
	public float calculHT(List<Product> prod) {
		

		float MontantHT = 0;
		float prixHTProd = 0;
		if (prod != null) {
			int size = prod.size();
			for (int index = 0; index < size; index++) {
				//prix Prod / (1 + (tva/100))
				prixHTProd=prod.get(index).getPrice() / ( 1+( prod.get(index).getTva()/100 ) );
				MontantHT= MontantHT + prixHTProd;
			}

		} else {
			MontantHT = 0;
		}
		return MontantHT;
	}

	//calcul montant TVA
	public float calculMontantTva(List<Product> prod){
		
		float MontantTTVA = 0;
		float prixHTProd = 0;
		float prixHTProd1 = 0;
		
		if (prod != null) {
			int size = prod.size();
			for (int index = 0; index < size; index++) {
				//prix HT / (1+ (tva/100) )
				prixHTProd=prod.get(index).getPrice() / ( 1+( prod.get(index).getTva()/100 ) );
				prixHTProd1=prixHTProd * ( prod.get(index).getTva()/100 ) ;
				MontantTTVA= MontantTTVA + prixHTProd1;
			}

		} else {
			MontantTTVA = 0;
		}
		return MontantTTVA;
		
	}

	
	//calcul taux de tva
		public float calcultauxTva(List<Product> prod){
			

			float tauxtva = 0;
			float prixTTC = 0;
			float prixHT = 0;
			
			if (prod != null) {
				prixTTC = calculTTC(prod);
				prixHT = calculHT(prod);
				tauxtva = ((prixTTC-prixHT)/prixHT)*100;
			} else {
				tauxtva = 0;
			}
			return tauxtva;
			
		}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////JSF/////
		
		//add to cart
		public List<Product> addProductToCart (Long idCart,Long idProduct,Long idUser){
			//init
			Cart cart = cartRepeository.findById(idCart).get();
			Product product = productRepository.findById(idProduct).get();
			User user = userRepository.findById(idUser).get();
			float MontantTTC = 0;
			float MontantHT = 0;
			float prixHTProd = 0;
			float MontantTTVA = 0;
			float prixHTProd1 = 0;
			float prixHTProd2 = 0;
			//add
			float prixtotal = (cart.getMontantTTC() + product.getPrice());
			cart.setUser(user);
			if (cart.getProduits() == null) {
				List<Product> prodcts = new ArrayList<>();
				prodcts.add(product);
				cart.setProduits(prodcts);
			} else {
				cart.getProduits().add(product);
			}
			// calcul montant
			if (cart.getProduits() != null) {
				int size = cart.getProduits().size();
				for (int index = 0; index < size; index++) {
					//
					MontantTTC = MontantTTC + cart.getProduits().get(index).getPrice();
					//
					prixHTProd=cart.getProduits().get(index).getPrice() / ( 1+( cart.getProduits().get(index).getTva()/100 ) );
					MontantHT= MontantHT + prixHTProd;
					//
					prixHTProd1=cart.getProduits().get(index).getPrice() / ( 1+( cart.getProduits().get(index).getTva()/100 ) );
					prixHTProd2=prixHTProd1 * ( cart.getProduits().get(index).getTva()/100 ) ;
					MontantTTVA= MontantTTVA + prixHTProd2;	
				}
			} else {
				MontantTTC = 0;
				MontantHT = 0;
				MontantTTVA = 0;
			}
			cart.setMontantTTC(MontantTTC);
			cart.setMontantHT(MontantHT);
			
			

			cartRepeository.save(cart);
			return  cart.getProduits();
			
		}

		// Enlever produit au panier
		@Transactional
		public String deleteProductFromCart(Long cartId, Long produitId) {
			//init
			Cart cart = cartRepeository.findById(cartId).get();
			Product product = productRepository.findById(produitId).get();
			// recherche product dans le cart
			int size = cart.getProduits().size();
			for (int index = 0; index < size; index++) {
				if (cart.getProduits().get(index).getId() == produitId) {
					cart.getProduits().remove(product);
					break;
				}
			}
			return "product is delted";

		}
		
		//search Product in cart
		public Product searchProductInCart(Long idCart,Long idProduct){
			Product Initprod = new Product();
			Cart cart = cartRepeository.findById(idCart).get();
			Product product = productRepository.findById(idProduct).get();
			// recherche product dans le cart
			int employeNb = cart.getProduits().size();
			for (int index = 0; index < employeNb; index++) {
				if (cart.getProduits().get(index).getId() == idProduct) {
					Initprod = cart.getProduits().get(index);
				}
				
			}
			
			return Initprod;
		}

		//select the quantité of the product to add to the commande
		@Transactional
		public List<Product> SelectQuantity(Long idProduct, Long idCart, Long idUser, int quantiteProd) {
			// init
			System.out.println("aaaaaaa");
			Cart cart = cartRepeository.findById(idCart).get();
			Product product = productRepository.findById(idProduct).get();
			User user = userRepository.findById(idUser).get();
			List<Product> prods = new ArrayList<>();
			Product Initprod = new Product();
			float MontantTTC = 0;
			float MontantHT = 0;
			float prixHTProd = 0;
			float MontantTTVA = 0;
			float prixHTProd1 = 0;
			float prixHTProd2 = 0;
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
					// calcul montant
					if (prods != null) {
						int r = prods.size();
						for (int j = 0; j < r; j++) {
							//
							MontantTTC = MontantTTC + prods.get(j).getPrice();
							//
							prixHTProd=prods.get(j).getPrice() / ( 1+( prods.get(j).getTva()/100 ) );
							MontantHT= MontantHT + prixHTProd;
							//
							prixHTProd1=prods.get(j).getPrice() / ( 1+( prods.get(j).getTva()/100 ) );
							prixHTProd2=prixHTProd1 * ( prods.get(j).getTva()/100 ) ;
							MontantTTVA= MontantTTVA + prixHTProd2;	
						}
					} else {
						MontantTTC = 0;
						MontantHT = 0;
						MontantTTVA = 0;
					}
					////fiin montant
					cart.setMontantTTC(MontantTTC);
					cart.setMontantHT(MontantHT);
					cart.setMontantTVA(MontantTTVA);	
				}				
			}
			

			return prods;

		}
			
		public float calculMontantTvaCart(List<Product> prod){
			
			float MontantTTVA = 0;
			float prixHTProd = 0;
			float prixHTProd1 = 0;
			
			if (prod != null) {
				int size = prod.size();
				for (int index = 0; index < size; index++) {
					//prix HT / (1+ (tva/100) )
					prixHTProd=prod.get(index).getPrice() / ( 1+( prod.get(index).getTva()/100 ) );
					prixHTProd1=prixHTProd * ( prod.get(index).getTva()/100 ) ;
					prixHTProd1 = prixHTProd1 * prod.get(index).getQuantitCart();
					MontantTTVA= MontantTTVA + prixHTProd1;
				}

			} else {
				MontantTTVA = 0;
			}
			return MontantTTVA;
			
		}
		
		public float calculHTCart(List<Product> prod) {
			
			float MontantHT = 0;
			float prixHTProd = 0;
			if (prod != null) {
				int size = prod.size();
				for (int index = 0; index < size; index++) {
					//prix Prod / (1 + (tva/100))
					prixHTProd=prod.get(index).getPrice() / ( 1+( prod.get(index).getTva()/100 ) );
					prixHTProd = prixHTProd * prod.get(index).getQuantitCart();
					MontantHT= MontantHT + prixHTProd;
				}

			} else {
				MontantHT = 0;
			}
			return MontantHT;
		}
		
		
		public float calculTTCCart(List<Product> prod) {
			

			float MontantTTC = 0;

			if (prod != null) {
				int size = prod.size();
				for (int index = 0; index < size; index++) {
					MontantTTC = MontantTTC + prod.get(index).getPrixQuantiteCart() ;
					
				}

			} else {
				MontantTTC = 0;
			}
			return MontantTTC;
		}

		
		
		
		
		
}



