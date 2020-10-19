package tn.esprit.spring.servlet;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//@Scope(value = "session")
@Controller(value = "ProductControllerImpl")
@ELBeanName(value = "ProductControllerImpl")
//@Join(path = "/", to = "/pages/adminSide/aislesViews/test.jsf")
@Join(path = "/", to = "/pages/frontend/productViews/homeProductView.jsf")
//@Join(path = "/", to = "/pages/adminSide/productViews/adminHomeProductView.jsf")
//@Join(path = "/", to = "pages/adminSide/Store/manageCatalogue.jsf")
//@Join(path = "/", to = "/login.jsf")

public class EmployeCotroller {

}
