package tn.esprit.spring.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;

import tn.esprit.spring.entities.ChargeRequest;
import tn.esprit.spring.entities.PaymentIntentDto;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class PaymentService {

	/*
	 * @Value("${stripe.key.secret}") String secretKey;
	 */

	@Autowired
	UserRepository userRepository;

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
	public String createStripeCustomer(Long idUser) {
		// stripe key
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";
		User user = userRepository.findById(idUser).get();

		Map<String, Object> params = new HashMap<>();
		params.put("description", "My First Test Customer (created for API docs)");
		params.put("email", user.getEmail());

		// affichage id du customer
		try {
			Customer customer = Customer.create(params);

			System.out.println("create customer id: {}");
			return customer.getId();
		} catch (StripeException e) {

			throw new RuntimeException(e);
		}

	}

	/*
	 * create Stripe card for the customer
	 */
	
	public String createCustumorStripe(String customerId, String carta, String expMonth, String expYear, String cvc)
			throws StripeException {

		// stripe key
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";

		Customer customer = Customer.retrieve(customerId);

		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number", carta);
		cardParam.put("exp_month", expMonth);
		cardParam.put("exp_year", expYear);
		cardParam.put("cvc", cvc);

		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam);

		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());

		customer.getSources().create(source);
		return token.getId();
	}

	/*
	 * A PaymentIntent guides you through the process of collecting a payment from your customer. 
	 * we create exactly one PaymentIntent for each order or customer session in our system. 
	 * we can reference the PaymentIntent later to see the history of payment attempts for a particular session.
	 */
	public String paymentIntent(PaymentIntentDto paymentIntentDto) throws StripeException {
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";
		List<String> paymentMethodTypes = new ArrayList();
		paymentMethodTypes.add("card");
		Map<String, Object> params = new HashMap<>();
		params.put("amount", paymentIntentDto.getAmount());
		params.put("currency", paymentIntentDto.getCurrency());
		params.put("description", paymentIntentDto.getDescription());
		params.put("payment_method_types", paymentMethodTypes);

		PaymentIntent p = PaymentIntent.create(params);
		p.getId();
		return p.getId();
	}

	/*
	 * this methode is to confirm that your customer intends to pay with current
	 * or provided payment method. Upon confirmation, the PaymentIntent will
	 * attempt to initiate a payment
	 */
	public PaymentIntent confirm(String id) throws StripeException {
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		Map<String, Object> params = new HashMap<>();
		params.put("payment_method", "pm_card_visa");
		// params.put("customer", "cus_H1OvsnwEn1KX36");
		paymentIntent.confirm(params);
		return paymentIntent;
	}

	public PaymentIntent cancel(String id) throws StripeException {
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		PaymentIntent paymentIntent1 = paymentIntent.cancel();
		return paymentIntent1;
	}

	/*
	 * If payment succeeds, the PaymentIntent will transition to the succeeded
	 * status (or requires_capture, if capture_method is set to manual). the
	 * role of this methode is to Capture the funds of an existing uncaptured
	 * PaymentIntent when its status is requires_capture. Uncaptured
	 * PaymentIntents will be canceled exactly seven days after they are
	 * created.
	 */
	public PaymentIntent Capture(String id) throws StripeException {
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		PaymentIntent paymentIntent1 = paymentIntent.capture();
		return paymentIntent1;
	}

	
	
	/*
	 * add to the map
	 * payment_intent
	 * les donner de la carte bancaire (test -)
	 *  "card": {
      "brand": "visa",
      "checks": {
        "address_line1_check": null,
        "address_postal_code_check": null,
        "cvc_check": null
      },
      "country": "US",
      "exp_month": 8,
      "exp_year": 2021,
      "fingerprint": "6rVrZSoq6tTX5tHv",
      "funding": "credit",
      "installments": null,
      "last4": "4242",
      "network": "visa",
      "three_d_secure": null,
      "wallet": null
    },
    "type": "card"
  }
	 */
	public Long charge(PaymentIntentDto paymentIntentDto) throws StripeException {

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
		return charge.getAmount();
	}

	
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
	
	public String retrieveCardCVC(String idCustomer,String idCard) throws StripeException{
		Stripe.apiKey = "sk_test_GoY3Wdg4CoRCK2snuRLF52MD00D9SCttXw";

		Customer customer = Customer.retrieve(idCustomer);

		Card card = (Card) customer.getSources().retrieve(idCard);
	
		return card.getCvcCheck();
	}
	
}
