package tn.esprit.spring.control;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/*
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.net.APIResource.RequestMethod;
*/
import tn.esprit.spring.entities.ChargeRequest;
import tn.esprit.spring.entities.ChargeRequest.Currency;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.entities.email;
import tn.esprit.spring.service.StripeService;
import tn.esprit.spring.service.UserService;

@RestController
public class ChargeController {
 
	@Autowired
	private StripeService stripeService;

	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	UserService UserService;
	
	//create customer
/*
    @PostMapping("/customer")
    @ResponseBody
    public String createCustomer(@RequestBody email email)  {
               return stripeService.createStripeCustomer(email);
    }
	
    
    @PostMapping(value = "/customerCard")
    @ResponseBody
    public String createCard(@RequestBody email customerId)  {
               return stripeService.createCard(customerId);
    }
	
	
	@PostMapping("/charge")
	@ResponseBody
    public  Long charge(@RequestBody ChargeRequest chargeRequest)  {
		return stripeService.charge(chargeRequest);
    }
 
   /* @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }*/
    
    
    
    
}