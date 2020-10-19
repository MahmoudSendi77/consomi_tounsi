package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Jackpot;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.TypeCommand;
import tn.esprit.spring.entities.Typejackpot;
import tn.esprit.spring.entities.newsletter;
//import tn.esprit.spring.repository.CommandRepository;
import tn.esprit.spring.repository.ImageRepository;
import tn.esprit.spring.repository.JackpotRepository;
import tn.esprit.spring.repository.NewsletterRepository;
@Service
public class JackpotService implements IJackpotService{

	@Autowired
	JackpotRepository jackpotrepository;
//	@Autowired
//	CommandRepository commandRepository;
	
	@Autowired
	NewsletterService newsletterService;
	
	 @Autowired
		NewsletterRepository newsletterReprository;
	 
	 @Autowired
		ImageRepository imageRepository;
		
	 @Autowired
	    public JavaMailSender emailSender;
	
	public String sendSimpleEmail() {
		List<newsletter> l = newsletterReprository.findAll();
		
	    // Create a Simple MailMessage.
	    SimpleMailMessage message = new SimpleMailMessage();
	    
	     for(int i=0;i<l.size();i++)
	     {
	    message.setTo(l.get(i).getEmail());
	    message.setSubject("New Jackpot added");
	    message.setText("aider les gents sur consomi tounsi " );

	    // Send Message!
	    this.emailSender.send(message);
	    
	     }

	    return "Email Sent!";
	}
	
	
	public Long addJackpots(Jackpot jackpot) {
		
		jackpot.setTypeJackpot(Typejackpot.Argent);
		jackpotrepository.save(jackpot);
		return jackpot.getId();
	}
	
	public Long addJackpot(Jackpot jackpot) {
		jackpotrepository.save(jackpot);
		return jackpot.getId();
	}
	
	
	public long updateJackpot(Jackpot jackpot) {
		if (jackpotrepository.findById(jackpot.getId()).get() != null) {
			jackpotrepository.save(jackpot);
			return jackpot.getId();
		}
		return 0;
	}
	
public void deletejackpotById (Long jackpotId){
		
		Jackpot JackpotId = jackpotrepository.findById(jackpotId).get();
		
		jackpotrepository.delete(JackpotId);
	
	}


	
public List<Jackpot> getAllJackpot() {



	return jackpotrepository.findAll();
}


//public List <Jackpot> getoneJackpot() {
//
//
//
//	return jackpotrepository.lastimage();
//}




public long daysBetween( Long jackpotId) {
	Jackpot jackpot = jackpotrepository.findById(jackpotId).get();
	Date g = new Date();
	
	
	long  days =( jackpot.getDatefin().getTime() - g.getTime())/(1000*60*60*24);
	
	
//	if (days==0)
//	{
//		
//		jackpotrepository.deleteById(jackpotId);
//		System.out.println("jackpot end");
//	}
	return days;
	
}



//		public List<Product> getAllProductByJackpot(Long id){
//			List<Command> commands = commandRepository.findAll();
//			
//			List<Product> products = new ArrayList<>();
//			int size = commands.size();
//			for(int index = 0;index<size;index++){
//				if((commands.get(index).getCommandType().equals("jackpot")) &&  (commands.get(index).getUser().getId() == id) ){
//					int taille = commands.get(index).getProduits().size();
//					for(int s = 0;s<taille;s++){
//					
//					
//					products.add(commands.get(index).getProduits().get(s));
//				
//					}
//				}
//				
//			}
//			
//			return products;
//		}






}
