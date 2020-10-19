package tn.esprit.spring.control;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import tn.esprit.spring.entities.PaymentIntentDto;
import tn.esprit.spring.security.CurrentUser;
import tn.esprit.spring.service.PaymentService;



@RestController
@RequestMapping("/pay")
public class PaymentController {
	@Autowired
    PaymentService paymentService;

	
	 @PostMapping("/customer1/{idUser}")
	    @ResponseBody
	    @PreAuthorize("hasRole('USER')")
	    public String createCustomer(@PathVariable("idUser")Long idUser)  {
	               return paymentService.createStripeCustomer(idUser);
	    }
	
	
	
	// http://localhost:8081/SpringMVC/servlet/pay/customer/retour_ta3_methode_create_customer/4242424242424242/11/2026/123
	@PostMapping("/customer/{customerId}/{carta}/{expMonth}/{expYear}/{cvc}")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public String createCustomer(@PathVariable("customerId")String customerId,@PathVariable("carta")String carta,@PathVariable("expMonth")String expMonth,@PathVariable("expYear")String expYear,@PathVariable("cvc")String cvc) throws StripeException  {
               return paymentService.createCustumorStripe(customerId, carta, expMonth, expYear, cvc);
    }
	
	
	
	// http://localhost:8081/SpringMVC/servlet/pay/paymentintent
	/*{
		"description":"test la methode payment",
		"amount":"10000",
		"currency":"eur"
	}*/
    @PostMapping("/paymentintent")
    @PreAuthorize("hasRole('USER')")
    public String payment(@RequestBody PaymentIntentDto paymentIntentDto) throws StripeException {
    	return paymentService.paymentIntent(paymentIntentDto);
      //  String paymentStr = paymentIntent.toJson();
        //return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

 // http://localhost:8081/SpringMVC/servlet/pay/confirm/{id}
    @PostMapping("/confirm/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> confirm(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.confirm(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
    
 // http://localhost:8081/SpringMVC/servlet/pay/charge
    /*{
	"description":"test la methode payment",
	"amount":"10000",
	"currency":"eur"
}*/
    @PostMapping("/charge")
	@ResponseBody
    public  Long charge(@RequestBody PaymentIntentDto PaymentIntentDto) throws StripeException  {
		return paymentService.charge(PaymentIntentDto);
    }
    
 // http://localhost:8081/SpringMVC/servlet/pay/capture/{id}
    @PostMapping("/capture/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> capture(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.Capture(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
    
 // http://localhost:8081/pay/retriveCustomer/{idCustomer}
    @PostMapping("/retriveCustomer/{idCustomer}")
    
    public String retrieveCustomer(@PathVariable("idCustomer") String idCustomer) throws StripeException{
    	return paymentService.retrieveCustomer(idCustomer);
    }
    
   // http://localhost:8081/pay/retriveCard/{idCustomer}/{idCard}
    @PostMapping("/retriveCard/{idCustomer}/{idCard}")
    
    public String retrieveCard(@PathVariable("idCustomer")String idCustomer,@PathVariable("idCard")String idCard) throws StripeException{
    	return paymentService.retrieveCard(idCustomer,idCard);
    }
    
    // http://localhost:8081/pay/makeCharge/{mail}/{numeroCard}/{idCustomer}/{idCard}
    /*{
   	"description":"test la methode payment",
   	"amount":"10000",
   	"currency":"eur"
   }*/
    @PostMapping("/makeCharge/{mail}/{numeroCard}/{idCustomer}/{idCard}")
    public String makeCharge(@PathVariable("mail") String mail,@PathVariable("numeroCard") String numeroCard
    		,@PathVariable("idCustomer")String idCustomer,@PathVariable("idCard")String idCard
    		,@RequestBody PaymentIntentDto PaymentIntentDto) throws StripeException{
    	String resultat;
    	if((paymentService.retrieveCustomer(idCustomer).equals(mail)) & paymentService.retrieveCard(idCustomer,idCard).equals(numeroCard)){
    			Long id =paymentService.charge(PaymentIntentDto);
    			resultat="test s7i7";
    		
    	}
    	else{
    		resultat="noooooooooo";
    	}
    	
    	
    	return resultat;
    }
}

