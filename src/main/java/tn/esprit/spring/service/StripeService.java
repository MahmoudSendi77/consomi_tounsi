 package tn.esprit.spring.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.ChargeRequest;
import tn.esprit.spring.entities.PaymentIntentDto;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.email;
import tn.esprit.spring.repository.CartRepository;

import tn.esprit.spring.repository.ProductRepository;

@Service
public class StripeService  {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Value("${STRIPE_SECRET_KEY}")
	private String secretKey;

	@PostConstruct
	public void init() {
		Stripe.apiKey = secretKey;
	}

	// creation d'un stripe account

	/**
	 * @param accountUuid
	 *            Linked account id.
	 * @throws APIException
	 * @throws CardException
	 * @throws APIConnectionException
	 * @throws InvalidRequestException
	 * @throws AuthenticationException
	 * @link https://stripe.com/docs/api#create_customer
	 */
	public String createStripeCustomer(email email)  {
		return secretKey;

	}

	// creation d'un carte bancaire

	public String createCard(Long idUser,email customerId) throws StripeException {
		
		// stripe key
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";

		Customer customer = Customer.retrieve(customerId.getEmail());

		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number", "5555555555554444");
		cardParam.put("exp_month", "11");
		cardParam.put("exp_year", "2026");
		cardParam.put("cvc", "123");

		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam);

		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());

		customer.getSources().create(source);
		return token.getId();
	}

	// faire un payment

	public Long charge(ChargeRequest chargeRequest) throws StripeException {
		
		// stripe key
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";

		// `source` is obtained with Stripe.js; see
		// https://stripe.com/docs/payments/accept-a-payment-charges#web-create-token
		Map<String, Object> params = new HashMap<>();
		params.put("amount", "500");
		params.put("currency", chargeRequest.getCurrency());
		params.put("description", chargeRequest.getDescription());
		params.put("customer", "cus_GxNBzUYslR89pq");
		params.put("card", "cus_GxNBzUYslR89pq");
		Charge charge = Charge.create(params);
		return charge.getAmount();
	}

	// list des produit dans la carte

	public String stripe(Model model) {
		List<Product> productList = (List<Product>) productRepository.findAll();
		List<Cart> cartItemList = (List<Cart>) cartRepository.findAll();

		float total = 0;

		for (Cart item : cartItemList) {
			total = total + item.getMontantTTC();
		}

		model.addAttribute("productList", productList);
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("total", total);

		return "stripe-cart";
	}

	// add product to the cart

	/*
	 * public String addToCart(String name) { Product product =
	 * productRepository.findOne(name);
	 * 
	 * CartItem cartItem = new CartItem(); cartItem.setProduct(product);
	 * cartItem.setQty(1); cartItem.setSubTotal(new
	 * BigDecimal(product.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
	 * cartItemRepository.save(cartItem);
	 * 
	 * return "redirect:/stripe/"; }
	 */

	
	
	
	
	
	
	
}
