package tn.esprit.spring.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import tn.esprit.spring.entities.User;

public interface UserserviceInterface {
	public String vide();

	public User affecterUserTonrml(User user);

	public User calculnbrpoints(Long userid, float prixtotale);

	public void statistics(int age);

	public String statisticsdate(int age, Date d, Date d1);

	public String deleteuser(Long id);

	public Float calculsommedescommand(Date d, Date d1);

	public float gain(Date d, Date d1);

	public String nbrutilisateurpartype();

	public User findByusernameoremail(String usernameOrEmail);

	public String banneruser(Long iduser);

	public Boolean debanneruser(User u);

	public Boolean verifdatebanne(User u);

	// public Boolean verifdatebanne(User u);
	public String getbrand(String cat, Date d, Date d1);

	public String getbrand(String cat);

	public Map<String, Float> revenuparbrand(String cat, Date d, Date d1);

	public String parrinage(String code, Long iduser);

	public User finduserbycode(String code);

	public String ajoutPointFideliteUserParrainage(User u);

	public String generatecodeparrainage();

	public User getuserbyid(Long id);

	public String confirmation(String code);

	public String userrole(Long iduser);

	public User setusertoab(User u);

	public List<List<String>> getNbrProdVenduParCateg(String cat);

	public String SignalerUser(Long iduser, Long iduserabanner);

	public int countnbrsignal(Long iduser);
	
	public void desaffecterSignal(Long iduser);
	
	public void convertirPointsuser(float solde,Long iduser,int points);
	
	public void nbrgainparuser(Long iduser,float prix);
	
	public List<List<String>> nbrgainuserspa(Long iduser);
	
}
