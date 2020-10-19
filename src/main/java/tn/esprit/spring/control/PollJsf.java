package tn.esprit.spring.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Choix;
import tn.esprit.spring.entities.Poll;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ChoixRepository;
import tn.esprit.spring.repository.PollRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.PollService;

@Controller(value = "PollJsf")
@ELBeanName(value = "PollJsf")
public class PollJsf {
	
	
	
	
	  @Autowired
	   PollService pollService;
	  
	  @Autowired
	  PollRepository PollRepository;
	  
	  
	  @Autowired
	   SigninJsf SigninJsf;
	  @Autowired
	  UserRepository UserRepository;
	  @Autowired
	  ChoixRepository ChoixRepository;
	  private String paaram;
	  private String sujet ;
	  





	public String getSujet() {
		return sujet;
	}




	public void setSujet(String sujet) {
		this.sujet = sujet;
	}




	List <Choix> ch = new ArrayList<Choix>();
		


	public List<Choix> getCh() {
			return ch;
		}




		public void setCh(List<Choix> ch) {
			this.ch = ch;
		}




	




	public String getPaaram() {
		return paaram;
	}




	public void setPaaram(String paaram) {
		
		System.out.println("paaaaaaaaaaa " + paaram );
		if (paaram!=null || paaram!="")
			
		{
			System.out.println("sssssssssssssssssssssssssssssssssssssssss");
			ch.add(new Choix(paaram,0l) );
			
			
			System.out.println("raaaaaaaaaaaaaaaaaaaaaaaaaaaak lhna");
			
			
			
		}
//		this.paaram = this.paaram+"," + paaram;
//		System.out.println("aaaaaaaaaaaaaaassssss   " + this.paaram);
	}




	public void  Addpoll()
	  {
		  
		
//		Poll p = new Poll();
//		
//		p.setTitle(sujet);
//		p.setEndDate(new Date(2020,1,1));
//	p.setVisible(false);
//	
//	p.setOptions(ch);
		
		
		
		
	
	pollService.savePoll(new Poll(ch, sujet, new Date(2020,1,1), false),UserRepository.findById(SigninJsf.idusercurrent()).get().getUsername() );
		
	
	
	
	System.out.println("aaaaaaaaaaaaaaaaa");
		 
		  
		  System.out.println("ccccccccccccccccccccccc " + ch );
		  
		  paaram = "";
		  
	  }
	
	
	
	private long optionselect;
	
	public long getOptionselect() {
		return optionselect;
	}




	public void setOptionselect(long optionselect) {
		
		System.out.println("ssssssssssssss "+optionselect);
		if(optionselect != 0l)
		this.optionselect = optionselect;
	}




	private String selectoption;
	public String getSelectoption() {
		return selectoption;
	}



private boolean verf;
	
	

	public boolean isVerf() {
	return verf;
}




public void setVerf(boolean verf) {
	this.verf = verf;
}




	public void setSelectoption(String selectoption) {
		
		
		
		
		System.out.println("ssssssssssssss "+selectoption);
		
		if(selectoption.equals("")==false)
			
		this.selectoption = selectoption;
	}




	public List<Choix> choix(long id){
		
		
		
		
		
		
		return PollRepository.findById(id).get().getOptions();
		
		
	}
	
	
	public void voote(long idpoll) throws Exception{
		
	
		System.out.println("rrrrrrttttttyyyyyuuuuuuuuuuuu "+ this.getOptionselect());
		
		
		String retour=  pollService.vote(idpoll, optionselect, SigninJsf.idusercurrent());
	  
	  
		FacesMessage facesMessage =

				new FacesMessage(retour);
		FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		
		
		optionselect=0l;
		selectoption = "";
	
	}
	
	
	
	
	
	public List<Poll> polls(){
		
		
	return	PollRepository.findAll();
		
		
	}
	
	
	
	public List<Poll> mespoll()
	{
		
		
		return pollService.getAllForUser(  UserRepository.findById(SigninJsf.idusercurrent()).get().getUsername() ) ;
	}

public boolean verf(long iduser){
	System.out.println("test" + iduser);
	
	
//if(	UserRepository.findById(SigninJsf.idusercurrent()).get().equals(user) )
//	return true;
//
//
//else 
	return false;
}


	

}
