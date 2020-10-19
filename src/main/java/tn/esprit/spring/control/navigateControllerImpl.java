package tn.esprit.spring.control;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller(value = "navigateControllerImpl")
@ELBeanName(value = "navigateControllerImpl")
@RestController
public class navigateControllerImpl {
	@Autowired
	CartControllerImpl cartControllerImpl;
	
	
	public String navigateToCartClientPage(){
		String navigateTo = "";
		navigateTo = "/pages/cartPageClient.xhtml?faces-redirect=true";
		cartControllerImpl.setQuantiteCartClient(1);
		return navigateTo;
	}
	
	public String navigateToPaymentCart(){
		String navigateTo = "";
		navigateTo = "/pages/checkout.xhtml?faces-redirect=true";
		return navigateTo;
		
	}
	
	public String navigateToFactureDetail(){
		String navigateTo = "";
		navigateTo = "/pages/invoicecommand.xhtml?faces-redirect=true";
		return navigateTo;
		
	}
	
}
