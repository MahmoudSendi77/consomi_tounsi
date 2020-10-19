package tn.esprit.spring.control;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

import tn.esprit.spring.entities.PaymentIntentDto;
import tn.esprit.spring.entities.PaymentIntentDto.Currency;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.CartService;
import tn.esprit.spring.service.CommandService;
import tn.esprit.spring.service.PaymentService;
import tn.esprit.spring.service.UserService;

@Controller(value = "paymentControllerimpl")
@ELBeanName(value = "paymentControllerimpl")
@RestController
public class PaymentControllerimpl {

	@Autowired
	CartService cartService;
	@Autowired
	UserService UserService;
	@Autowired
	CommandService commandService;
	@Autowired
	SigninJsf signinJsf;
	@Autowired
	CartControllerImpl cartControllerImpl;
	@Autowired 
	PaymentService paymentService;
	@Autowired 
	UserRepository userRepository;
	
	private String email;
	private String creditCardNumber;
	private String cvc;
	
	
	//////////////////////////////getter et setter //////////////////////////////////////
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	/////////////////////////////methode////////////////////////////////////////////////
	public String retrieveCustomer(String idCustomer) throws StripeException{
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";

	Customer customer = Customer.retrieve(idCustomer);
	return customer.getEmail();
	}
	
	public String retrieveCard(String idCustomer,String idCard) throws StripeException{
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";

		Customer customer = Customer.retrieve(idCustomer);

		Card card = (Card) customer.getSources().retrieve(idCard);
	
		return card.getLast4();
	}
	
	public void charge(PaymentIntentDto paymentIntentDto) throws StripeException {

		// stripe key
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";

		// `source` is obtained with Stripe.js; see
		// https://stripe.com/docs/payments/accept-a-payment-charges#web-create-token
		Map<String, Object> params = new HashMap<>();
		params.put("amount", paymentIntentDto.getAmount());
		params.put("currency", paymentIntentDto.getCurrency());
		params.put("description", paymentIntentDto.getDescription());
		// add the Stripe_customer_id to the entity
		params.put("customer", "cus_H1OvsnwEn1KX36");

		Charge charge = Charge.create(params);
	
	}
	
	
	
	///////////////////////////////////test/////////////////////////////////////
	
	public void makeCharge() throws StripeException{
	System.out.println("start payment methode");
	System.out.println("*********************");
		User user = userRepository.findById(signinJsf.idusercurrent()).get();
		String resultat;
		PaymentIntentDto paymentIntent = new PaymentIntentDto();
		int amount = (int) cartControllerImpl.calcultc();
		String idcus=user.getIdCustomer();
		String idcar=user.getIdCard();
		String cvccard=paymentService.retrieveCardCVC(idcus, idcar);
		System.out.println("**********");
		System.out.println("mail jsf :"+this.email);
		System.out.println("credit card number : "+this.creditCardNumber);
		System.out.println("card cvc : "+cvccard);
		System.out.println("***************");
		
		System.out.println(amount);
		System.out.println("info sur Stripe attribut :");
		System.out.println("id customer is : "+ idcus);
		System.out.println("id card is : "+idcar);
		
		paymentIntent.setAmount(amount);
		paymentIntent.setCurrency(Currency.eur);
		paymentIntent.setDescription("test de jsf");
		
		System.out.println("payment intent is : "+paymentIntent);
		if((this.retrieveCustomer(idcus).equals(this.email)) & (this.retrieveCard(idcus,idcar).equals(this.creditCardNumber))){
    		System.out.println("test est passer avec succes cool ?");	
			this.charge(paymentIntent);
			System.out.println("mregel");
    			resultat="succes";
    		
    	}
    	else{
    		resultat="noooooooooo";
    	}
    	
		
		System.out.println("*********************");
		System.out.println("end payment methode with "+resultat);
		System.out.println("map of product to buy : "+this.affichageProductToBuy());
    }

	public Map affichageProductToBuy(){
		Map ProductRepition = new HashMap();
		
		int toul = cartControllerImpl.prodIntermediare.size();
		for (int index = 0; index < toul; index++) {
			/* frenquency() compte le nombre d'occurrences */
			Integer repitition = Collections.frequency(cartControllerImpl.prodIntermediare, cartControllerImpl.prodIntermediare.get(index));
			ProductRepition.put(cartControllerImpl.prodIntermediare.get(index), repitition);
		}
		return ProductRepition;
	}
	}
	
	

