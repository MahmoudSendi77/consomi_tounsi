package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Product;

public interface ICartService {

	public String addToCart(Long produitId, Long cartId, Long idUser);
	public String DeleteProductFromCart(Long produitId, Long cartId);
	public String removeCart(Long id);
	public String updateQuantiteCart(Long cartID, Long produitId, int quantiteProduct);
	public List<Product> getAllProductByCart(Long cartId);
	public Cart getCartById(Long cartId);
	
	
	
}
