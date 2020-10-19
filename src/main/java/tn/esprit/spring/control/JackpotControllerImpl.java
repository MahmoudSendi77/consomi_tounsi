package tn.esprit.spring.control;



import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Command;
import tn.esprit.spring.entities.Don;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.Image;
import tn.esprit.spring.entities.Jackpot;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.newsletter;
import tn.esprit.spring.repository.DonRepository;
import tn.esprit.spring.repository.JackpotRepository;
import tn.esprit.spring.repository.NewsletterRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.CurrentUser;
import tn.esprit.spring.security.UserPrincipal;
import tn.esprit.spring.entities.Typejackpot;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.DonService;
import tn.esprit.spring.service.IProductService;
import tn.esprit.spring.service.ImageServiceImpl;
import tn.esprit.spring.service.JackpotService;
import tn.esprit.spring.service.NewsletterService;
import tn.esprit.spring.service.UserService;




@Scope(value = "session")
@Controller(value = "jackpotControllerImpl")
@ELBeanName(value = "jackpotControllerImpl")
//@Join(path = "/servlet/api/aymen/", to = "/jackpotpage.jsf")
@Join(path = "/login", to = "/login/name.jsf")

public class JackpotControllerImpl implements  Serializable {

	private Long id;
	public static Long ids;
	
     private String titre;
	private java.sql.Date datefinjackpot;
	private Date  datefin;
	private float maxValue;
	private String descriptionjackpot;
	private int nbrvue;
	@Enumerated(EnumType.STRING)
	private Typejackpot typeJackpot;
	
	public Typejackpot[] gettypeJackpots() { return Typejackpot.values(); }

	@OneToMany(mappedBy="jackpot",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Don> dons;
	
	
	@Autowired
	private DonService donService;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getNbrvue() {
		return nbrvue;
	}


	public void setNbrvue(int nbrvue) {
		this.nbrvue = nbrvue;
	}


	public String getDescriptionjackpot() {
		return descriptionjackpot;
	}


	public void setDescriptionjackpot(String descriptionjackpot) {
		this.descriptionjackpot = descriptionjackpot;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Date getDatefin() {
		return datefin;
	}


	public void setDatefin(Date datefin) {
		System.err.println("gggbbbb"+datefin);
		  
		this.datefin = datefin;
		System.err.println("kkkkkkkkkkk"+this.datefin);
	}


	public float getMaxValue() {
		return maxValue;
	}


	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}


	public Typejackpot getTypeJackpot() {
		return typeJackpot;
	}


	public void setTypeJackpot(Typejackpot typeJackpot) {
		this.typeJackpot = typeJackpot;
	}


	public List<Don> getDons() {
		return dons;
	}


	public void setDons(List<Don> dons) {
		this.dons = dons;
	}
	

	public java.sql.Date getDatefinjackpot() {
		return datefinjackpot;
	}


	public void setDatefinjackpot(java.sql.Date datefinjackpot) {
		this.datefinjackpot = datefinjackpot;
	}

	private float montantdon;
	private float montantconsomi;
	private String descriptiondon;
	private String nom ;
	private String mail;
	private boolean montrermontant;
	private boolean aider;
	private boolean montreridentité;
	private boolean modepaiment;
	

 
	private Date date;
	



	public float getMontantdon() {
		return montantdon;
	}

	public void setMontantdon(float montantdon) {
		this.montantdon = montantdon;
	}

	public float getMontantconsomi() {
		return montantconsomi;
	}

	public void setMontantconsomi(float montantconsomi) {
		this.montantconsomi = montantconsomi;
	}

	public String getDescriptiondon() {
		return descriptiondon;
	}

	public void setDescriptiondon(String descriptiondon) {
		this.descriptiondon = descriptiondon;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isMontrermontant() {
		return montrermontant;
	}

	public void setMontrermontant(boolean montrermontant) {
		this.montrermontant = montrermontant;
	}

	public boolean isAider() {
		return aider;
	}

	public void setAider(boolean aider) {
		this.aider = aider;
	}

	public boolean isMontreridentité() {
		return montreridentité;
	}

	public void setMontreridentité(boolean montreridentité) {
		this.montreridentité = montreridentité;
	}

	public boolean isModepaiment() {
		return modepaiment;
	}

	public void setModepaiment(boolean modepaiment) {
		this.modepaiment = modepaiment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	 
	@Autowired
	JackpotService jackpotService;
	@Autowired
	ImageServiceImpl ImageServiceImpl;
	
	@Autowired
	NewsletterService newsletterService;
	
	@Autowired
	JackpotRepository jackpotrepository;
	@Autowired
	DonRepository donRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService UserService;
	 @Autowired
		NewsletterRepository newsletterReprository;
	 
	 @Autowired
		SigninJsf siginJSF;
	 
	 
	private newsletter newsletter;
	private String email;
    private List<newsletter> emails;

    
    
    
private Jackpot jackpot;



	public Jackpot getJackpot() {
	return jackpot;
}


public void setJackpot(Jackpot jackpot) {
	this.jackpot = jackpot;
}


	public newsletter getNewsletter() {
		return newsletter;
	}


	public void setNewsletter(newsletter newsletter) {
		this.newsletter = newsletter;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<newsletter> getEmails() {
	return emails;
}


public void setEmails(List<newsletter> emails) {
	this.emails = emails;
}


private String image;



public String getImage() {
	return image;
}


public void setImage(String image) {
	this.image = image;
}






private String param;







public String getParam() {
	
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
	        .getRequest();

	String id = request.getParameter("jackpot");
	System.out.println("id"+request);
	return id;
}

public String  parametre(){
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
	        .getRequest();
	String id = request.getParameter("jackpot");
	System.out.println("id"+request.getParameterMap());
	System.out.println("id"+id);
	return id;
	
}

public void setParam(String param) {
	this.param = param;
}









public String voir() {
	String url = "";
	url = "/jackpotpage.xhtml?faces-redirect=true";
	return url;
}


public String voirevent() {
	String url = "";
	url = "/pageevent.jsf?faces-redirect=true";
	return url;
}

public String adminevent() {
	String url = "";
	url = "/adminevent.xhtml?faces-redirect=true";
	return url;
}
public String adminjackpot() {
	String url = "";
	url = "/adminjackpot.xhtml?faces-redirect=true";
	return url;
}

public int GeParticipantByJackpot( Long id) {
	return donService.countParticipantByJackpot(id);	 
		
}

public void addJackpot() {
	
	
	jackpotService.addJackpots(new Jackpot(titre,datefin,maxValue,image,descriptionjackpot,typeJackpot));

    
	jackpotService.sendSimpleEmail();
	

}

public void updateJackpot() {
	
	Jackpot j =new Jackpot(jeckpotIdToBeUpdated,titre,datefin,maxValue,image,descriptionjackpot,typeJackpot);
	System.err.println("ffffffff");
jackpotService.updateJackpot(j); 
}


	public void ajouternewsletter() {
		
	
		newsletterService.ajouternewsletter(new newsletter(email));
	}



	public List<Jackpot> getjakpotes(){
	 
		
		List jakpotes =jackpotService.getAllJackpot();
		
		return jakpotes;
	}

	
	 @Autowired
	    public JavaMailSender emailSender;
	 
	
	 private Long jeckpotIdToBeUpdated;



	public Long getJeckpotIdToBeUpdated() {
		return jeckpotIdToBeUpdated;
	}


	public void setJeckpotIdToBeUpdated(Long jeckpotIdToBeUpdated) {
		this.jeckpotIdToBeUpdated = jeckpotIdToBeUpdated;
	}


	public void displayJackpot(Jackpot j) { 
		
		
		
		  

		 this.setTitre(j.getTitre());
		 this.setMaxValue(j.getMaxValue());
		 this.setTypeJackpot(j.getTypeJackpot());
		 this.setDatefin(j.getDatefin()); 
		 this.setImage(j.getImage());
		 this.setDescriptionjackpot(j.getDescriptionjackpot());
	     this.setJeckpotIdToBeUpdated(j.getId());

		 }
	
	 
 

	

//	public Jackpot updateJackpot(@RequestBody Jackpot jackpot) {
//		//System.out.println(Don.getMontantdon());
//
//		jackpotService.updateJackpot(jackpot);
//		return jackpot;
//	}

	
	

	public void deletejackpotById(Long JackpotId) {
		jackpotService.deletejackpotById(JackpotId);
		
	}
	
	


	
	
	
	public Long  daysBetween(Long jackpotId){
		
		return jackpotService.daysBetween(jackpotId);
	}

	//visite jackpot
	public int visit(Long jackpotId){
		
		
		Jackpot jackpot =  jackpotrepository.findById(jackpotId).get();
		
		jackpot.setNbrvue(jackpot.getNbrvue()+1);
	
		 jackpotrepository.save(jackpot);
		
		return jackpot.getNbrvue();
	}
	
	
	
	public int restdonbyjackpot(Long jackpotId){
		
		int montant = 0;
		int rest =0;
	
		 montant =donRepository.countAllDonatedByJackpot(jackpotId);
	
		 

		Jackpot jackpot = jackpotrepository.findById(jackpotId).get();
		
		 rest = (int) (jackpot.getMaxValue()-montant);
		System.err.println("countAllDonatedByJackpot");
		return rest;
	}
	
	
	
	public int  pourcentageByJackpot(Long jackpotId ){
		float total;
		total = 0;
			
		Jackpot jackpot = jackpotrepository.findById(jackpotId).get();
        List<Don> don =	jackpot.getDons();
		for (int i =0;i<don.size();i++)
		{
			
			total = total + don.get(i).getMontantdon();
			
			
		}
		float moy = 0;
		if(total !=0)
	 moy = (float)	(total  /   jackpot.getMaxValue())*100;  
	
	
	
		return (int) moy;
		
	}
	
	public int montant(Long jackpotId ){
		int total;
		total = 0;
		Jackpot jackpot = jackpotrepository.findById(jackpotId).get();
		
		List<Don> don =	jackpot.getDons();
		
		for (int i =0;i<don.size();i++)
		{
			
			total = (int) (total + don.get(i).getMontantdon());
			
			
		}
		return  total ;
	}
	
	
	
	public List< String >namemontantbyuser(Long jackpotId){
		System.out.println("jjjjjjjjjjj"+jackpotId);
		List<List<String>> l = donRepository.countmontantUserID(jackpotId);
		System.out.println("jjjjjjjjjjj"+l);
		List <String> user = new ArrayList<>();
		

		for (int i = 0; i < l.size(); i++) 
		{
			user.add(l.get(i).get(1));
		}
		return user;
	
	}
	
	
	
	public List< String >summontantbyuser(Long jackpotId){
	
		List<List<String>> l = donRepository.countmontantUserID(jackpotId);
	
		List <String> user = new ArrayList<>();
		

		for (int i = 0; i < l.size(); i++) 
		{
			user.add(l.get(i).get(0));
		}
		return user;
	
	}
	
	
	
	
	public String username(){
		
		
		
	User u =	userRepository.findById(siginJSF.idusercurrent()).get();// ligne user username 
	
		return u.getUsername();
		
	}
	
	
	
	
	public String email(){
		
		
		
		User u =	userRepository.findById(siginJSF.idusercurrent()).get();// ligne user username email
		
			return u.getEmail();
			
		}
	
	private static  String Anonyms="Anonyme";
	
	public static String getAnonyms() {
		return Anonyms;
	}


	public static void setAnonyms(String anonyms) {
		Anonyms = anonyms;
	}

@Transactional
	public String addD(Long JackpotId) { 
		
		
		



	//LocalDate today = LocalDate.now();
	
	Date g = new Date();
    g.getTime();
		


	//donService.addDonate(siginJSF.idusercurrent(),JackpotId,new Don(montantdon, montantconsomi, descriptiondon, nom, mail, montrermontant,aider,montreridentité,modepaiment,g));
	

 if (montreridentité==false)
{
	
	
	
	
	String nom =getAnonyms();
	String descriptiondon =getAnonyms();
	
	
	donService.addDonate(siginJSF.idusercurrent(),JackpotId,new Don(montantdon, montantconsomi, descriptiondon, nom, mail, montrermontant,aider,montreridentité,modepaiment,g));

}


 
else 
	donService.addDonate(siginJSF.idusercurrent(),JackpotId,new Don(montantdon, montantconsomi, descriptiondon, nom, mail, montrermontant,aider,montreridentité,modepaiment,g));
	
 
	return "/pagedon.xhtml?faces-redirect=true"; 
}
	
	
	private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/webapp/resources/uploads";

   
    @PostMapping("/uploadimage") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("tit")  String tit,@RequestParam("des")  String des,@RequestParam("dat") java.sql.Date dat,
    		@RequestParam("max")  float number
    		
    		) {

        if (file.isEmpty()) {
        	System.out.println("ssssssssssssssssssssssssssssss");
        	

        	
           // redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
           // return "redirect:uploadStatus";
        }

        try {
        	
            // Get the file and save it somewhere
        	File dir = new File( UPLOADED_FOLDER);
			
        	if (!dir.exists())
				dir.mkdirs();
            byte[] bytes = file.getBytes();
      
            Path path = Paths.get(UPLOADED_FOLDER +File.separator+ file.getOriginalFilename());
            Files.write(path, bytes);

//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
      
          //  UserRepository.findById(SigninJsf.idusercurrent()).get().setProfileImage(file.getOriginalFilename());
            
            
            
        	jackpotService.addJackpots(new Jackpot(tit,dat,number,file.getOriginalFilename(),des,typeJackpot));

            
        	jackpotService.sendSimpleEmail();
        	
         
            return "redirect:/adminjackpot.jsf";

        } catch (IOException e) {
        	
            e.printStackTrace();
     
            return "redirect:/adminjackpot.jsf";
        }

      //  return "redirect:/uploadStatus";
    }

  
//    public List<Product> commandToJackpot(){
//    	System.out.println("kkkkkk");
//    	System.out.println("all product"+jackpotService.getAllProductByJackpot(siginJSF.idusercurrent()));
//    	
//    	
//    	return jackpotService.getAllProductByJackpot(siginJSF.idusercurrent());
//    }

	
	
}

	
	
	

