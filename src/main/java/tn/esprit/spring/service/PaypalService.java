package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import tn.esprit.spring.entities.Command;

@Service
public class PaypalService {
	private static final String CLIENT_ID = "AaK7gJQXgasrqVVlA2ue9gImTX8sY3E-e1XNgbQXMw6ybhIoJ8wlGUTCbOECifeAtxgDH82hAXi7zY0I";
	private static final String CLIENT_SECRET = "EEcsKC6DdzczZ284bz7HMIl1NGOUw1QMrA4uwkHiQoqv0FjRNTTjwYuwAD7__C7nNwKVIia6yFP8DE1x";
	private static final String MODE = "sandbox";

	public String authorizePayment(Command command)			
			throws PayPalRESTException {		

		// Set payer details
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// shipping address
		ShippingAddress Sh = new ShippingAddress();
		Sh.setCountryCode("TN");
		Sh.setCity("FAHS");
		Sh.setLine1("test Street");
		Sh.setState("TUNIS");
		Sh.setPostalCode("75001");
		ItemList itemList = new ItemList();
	    itemList.setShippingAddress(Sh);
		// Set redirect URLs
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:3000/cancel");
		redirectUrls.setReturnUrl("http://localhost:8081/pages/confirmpaypal.jsf");

		// Set payment details
		//float subTotal = Float.valueOf(String.format("%2f",cart.getTotalPrice()));
		Details details = new Details();
		details.setShipping("1");
		details.setSubtotal(String.valueOf(command.getMontantTTC()));
		details.setTax("1");

		// Payment amount
		float am = command.getMontantTTC()+1+1;
		Amount amount = new Amount();
		amount.setCurrency("EUR");
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal(String.valueOf(am));
		amount.setDetails(details);

		
		// Transaction information
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction
		  .setDescription("This is the payment transaction description.")
		  .setItemList(itemList);

		// Add transaction to a list
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		// Add payment details
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setRedirectUrls(redirectUrls);
		payment.setTransactions(transactions);

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		Payment approvedPayment = payment.create(apiContext);

		System.out.println("=== CREATED PAYMENT: ====");
		System.out.println(approvedPayment);

		return getApprovalLink(approvedPayment);

	}
	
	
	//APPROVE BIHA PAYMENT
	public String getApprovalLink(Payment approvedPayment) {
		List<Links> links = approvedPayment.getLinks();
		String approvalLink = null;
		
		for (Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalLink = link.getHref();
				break;
			}
		}		
		
		return approvalLink;
	}

	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);

		Payment payment = new Payment().setId(paymentId);

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		return payment.execute(apiContext, paymentExecution);
	}
	
	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return Payment.get(apiContext, paymentId);
	}
	
	
	
	
}
