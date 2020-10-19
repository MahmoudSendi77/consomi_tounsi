package tn.esprit.spring.control;


import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.repository.CommandRepository;
import tn.esprit.spring.service.CartService;
import tn.esprit.spring.service.CommandService;
import tn.esprit.spring.service.FactureService;
import tn.esprit.spring.service.PaypalService;
import tn.esprit.spring.service.UserService;
@Controller(value = "commandControllerimpl")
@ELBeanName(value = "commandControllerimpl")
@RestController
public class commandControllerimpl {

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
	FactureService factureService;
	@Autowired
	public JavaMailSender emailSender;
	@Autowired
	CommandRepository commandRepository;
	@Autowired
	PaypalService paypalService;
	@Autowired
	FactureControllerImpl factureController;
	
	private String cardNumber;
	private String passwordCard;
	private String name;
	private String email;
	private String phonenumber;
	private String type;  
	private String confirmType;
	
	
	
	/////intermediare variable/////////
	
	private String cardNumberInter;
	private String passwordCardInter;
	private String nameInter;
	private String emailInter;
	private String phonenumberInter;
	private String typeInter; 
	private Command CommandInter;
	
	///////////////////////////////////////getter et setter//////////////////////////////////////
	
	public String getName() {
		return name;
	}
	public Command getCommandInter() {
		return CommandInter;
	}
	public void setCommandInter(Command commandInter) {
		CommandInter = commandInter;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	private List<String> type1;
	
	@PostConstruct
    public void init() {
        type1 = new ArrayList<String>();
        type1.add("Cash On Delivery");
        type1.add("online Buy");
        type1.add("To Jackpot");
       
    }

	public List<String> getType1() {
		return type1;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getConfirmType() {
		return confirmType;
	}
	public void setConfirmType(String confirmType) {
		this.confirmType = confirmType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPasswordCard() {
		return passwordCard;
	}
	public void setPasswordCard(String passwordCard) {
		this.passwordCard = passwordCard;
	}
	public String getCardNumberInter() {
		return cardNumberInter;
	}
	public void setCardNumberInter(String cardNumberInter) {
		this.cardNumberInter = cardNumberInter;
	}
	public String getPasswordCardInter() {
		return passwordCardInter;
	}
	public void setPasswordCardInter(String passwordCardInter) {
		this.passwordCardInter = passwordCardInter;
	}
	public String getNameInter() {
		return nameInter;
	}
	public void setNameInter(String nameInter) {
		this.nameInter = nameInter;
	}
	public String getEmailInter() {
		return emailInter;
	}
	public void setEmailInter(String emailInter) {
		this.emailInter = emailInter;
	}
	public String getPhonenumberInter() {
		return phonenumberInter;
	}
	public void setPhonenumberInter(String phonenumberInter) {
		this.phonenumberInter = phonenumberInter;
	}
	public String getTypeInter() {
		return typeInter;
	}
	public void setTypeInter(String typeInter) {
		this.typeInter = typeInter;
	}
	////////////////////////////////////methode////////////////////////////

	
	
	public String afficheAllCommandAdmin(){
		System.out.println("details x");
		String navigateTo = "";
		navigateTo = "/pages/adminhomePage.xhtml?faces-redirect=true";
		return navigateTo;
	}
	
	public String paymentPage() {
		
		String navigateTo = "";
		navigateTo = "/pages/payment.xhtml?faces-redirect=true";
		System.err.println("succes");

		return navigateTo;
	}
	
	public Command addCommand() throws Exception{
		System.err.println("***********************");
		String navigate = "";
		Command cd = new Command();
		
		
		if(type.equals("online Buy")){
			navigate = "/pages/payment.xhtml?faces-redirect=true";
		}
		else if (type.equals("To Jackpot")) {
			navigate = "/pages/cart.xhtml?faces-redirect=true";
		}
		else{
		 cd =commandService.AddProductAsCommand(cartControllerImpl.prodIntermediare, type, signinJsf.idusercurrent());
		Long numCommand=cd.getId();
		cd.setNumeroCommand(numCommand);
		commandRepository.save(cd);
		Facture fact = factureService.createFacture(cd.getId());
		setCommandInter(cd);
//    	factureService.writeQRCode(fact.getFactureId());
    	factureService.export(fact.getFactureId());
    	
//    	 //send mail
//    	MimeMessage message = emailSender.createMimeMessage();
//        
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//         
//        helper.setTo(email);
//        helper.setSubject("Consommi Tounsi 619");
//        helper.setText("your command has ben passed and it will be delivered by our delevery man ");
//        String path1 = "E:/projet Spring/-619/619/Facture13.pdf"; 
//        FileSystemResource file = new FileSystemResource(new File(path1));
//        helper.addAttachment("Facture13.pdf", file);
//     
//        emailSender.send(message);
        
		System.out.println("after is added");
		System.out.println("enter payment page");
		navigate = "/pages/adminhomepage.xhtml?faces-redirect=true";
		
		
		}
		System.err.println("succes");
		return cd;
	}

	public String filtrePage(){
		String navigateTo = "";
		navigateTo = "/pages/adminhomepage.xhtml?faces-redirect=true";
		System.err.println("succes");

		return navigateTo;
	}
	
	public List<Command> afficheCommande(){
		System.out.println("affiche table");
		return commandService.afficheAllCommand();
	}
	/////////////////////////////////////admin partie /////////////////////
	
	public void suppCommande(Command command){
		
		commandService.deleteCommand(command.getId());
		 System.out.println("succes");
	}
	 
	public void commandInfo(){
		setEmailInter(email);
		setTypeInter(type);
		setPhonenumberInter(phonenumber);
		
	}
	
	public void cardInfo(){
		System.out.println("card info");
		
	}

	public String placeOreder() throws Exception {
		System.out.println("karim");
	Command cd = addCommand();
		
		
		try {
			
			String approvalLink = paypalService.authorizePayment(cd);
			
			System.out.println("link : "+approvalLink);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    externalContext.redirect(approvalLink);
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public String confirmOrder() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String paymentId = request.getParameter("paymentId");
		String payerID = request.getParameter("PayerID");
		String token = request.getParameter("token");
		
		try {
		
			Payment payment = paypalService.executePayment(paymentId, payerID);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			
			System.out.println("payment successs \n user "+payerInfo.getFirstName()+" with transaction "+transaction.getDescription());
			//return home page
			factureController.afficheCommandById();
			
			
			
			String navigateTo = "";
			navigateTo = "/pages/frontend/productViews/listProductView.xhtml?faces-redirect=true";
			return navigateTo;
			
		} catch (PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
