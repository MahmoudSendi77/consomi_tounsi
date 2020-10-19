package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.chartistjsf.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.control.Profile;
import tn.esprit.spring.entities.Abonnement;
import tn.esprit.spring.entities.AbonnementDetail;
import tn.esprit.spring.entities.ProductNotif;
import tn.esprit.spring.entities.Reporting;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AbonnementDetailRepository;
import tn.esprit.spring.repository.AbonnementRepository;

import tn.esprit.spring.repository.ReportingRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
@EnableScheduling
public class UserService implements UserserviceInterface {

	public UserService() {
		super();
	}

	@Autowired
	UserRepository UserRepository;
	@Autowired
	ReportingRepository ReportingRepository;
	@Autowired
	AbonnementRepository AbonnementRepository;
	@Autowired
	tn.esprit.spring.repository.NotificationRepository NotificationRepository;
	
	@Autowired
	AbonnementDetailRepository AbonnementDetailRepository;
	@Autowired
	INotificationService notificationService;

	
    @Autowired
	Profile Profile;
	public int somme(Long id) {

		
	
		
		int s = 0;
		{
			for (int i = 0; i < UserRepository.findByRoles_Id(id).size(); i++)
				s = s + UserRepository.findByRoles_Id(id).get(i).getSalary();
		}

		return s;
	}

	public void deleteUserById(Long userId) {
		User User = UserRepository.findById(userId).get();
		UserRepository.delete(User);
	}

	// public void commanduser(Long userId) {
	// List<Command> list = new ArrayList<Command>();
	// for (int i = 0; i < UserRepository.findByRoles_Id(1l).size(); i++){
	// if ( UserRepository.findByRoles_Id(1l).get(i).getId() == userId)
	// System.out.println(UserRepository.findByRoles_Id(1l).get(i).getCommands());
	//
	// }
	//
	//
	// }

	
	
	
	@Transactional
	public User affecterUserAbonnementsignup(User user, String type) {

	
		
		
		List<Abonnement> list = new ArrayList<Abonnement>();

		AbonnementDetail abonnementDetail = new AbonnementDetail();
		Abonnement AB = new Abonnement();

		if (type.equals("NORMAL")) {
			for (int i = 0; i < AbonnementRepository.findByType("NORMAL").size(); i++)
				AB = AbonnementRepository.findByType("NORMAL").get(i);

			list.add(AB);
			user.setAbonnement(list.get(0));
			//
			Date g = new Date();
			g.getTime();
			g.setYear(g.getYear() - 30);
			abonnementDetail.setDeteabon(g);

			user.setAbonnementDetail(abonnementDetail);

			return user;

		}

		System.err.println("lbara");

		return user;
	}

	@Override
	public User affecterUserTonrml(User user) {
		// TODO Auto-generated method stub

		List<Abonnement> list = new ArrayList<Abonnement>();

		AbonnementDetail abonnementDetail = new AbonnementDetail();
		Abonnement AB = new Abonnement();

		if (AbonnementRepository.findByType("NORMAL").size() == 0)
			System.err.println("vide");
		else
			System.err.println("pleine");

		AB = AbonnementRepository.findByType("NORMAL").get(0);

		list.add(AB);
		user.setAbonnement(list.get(0));
		//
		Date g = new Date();
		g.getTime();
		g.setYear(g.getYear() - 30);
		abonnementDetail.setDeteabon(g);

		user.setAbonnementDetail(abonnementDetail);
		
		

		return user;

	}
	

//@Scheduled(fixedDelay = 5000,initialDelay = 5000)

	public void run() {
		Date g = new Date();
		g.getTime();

		Date g1 = new Date();
		g1.getTime();

		List<Abonnement> AB = AbonnementRepository.findAll();

		List<User> users;
		System.err.println(AB);

		for (int i = 0; i < AB.size(); i++) {

			users = AB.get(i).getUser();

			for (int j = 0; j < users.size(); j++) {

				System.err.println(users);

				User userssilver = new User();
				userssilver = users.get(j);

				Date d1 = userssilver.getAbonnementDetail().getDeteabon();

				d1.setMinutes(d1.getMinutes() + AB.get(i).getValidite());

				if (d1.compareTo(g) < 0 && userssilver.getAbonnement().getType().equals("NORMAL") == false) {

					Abonnement ABc = new Abonnement();
					AbonnementDetail abd = new AbonnementDetail();
					AbonnementDetail abdaremove = new AbonnementDetail();

					abd = userssilver.getAbonnementDetail();
					abdaremove = userssilver.getAbonnementDetail();

					abd.setRemise(0);
					abd.setDeteabon(g);

					ABc = AbonnementRepository.findByType("NORMAL").get(0);

					userssilver.setAbonnement(ABc);
					AbonnementDetailRepository.save(abd);
					userssilver.setAbonnementDetail(abd);
					System.err.println(userssilver.getAbonnementDetail());
					UserRepository.saveAndFlush(userssilver);

					System.err.println("c bn");

				}

				System.err.println("khatih");
			}
		}

	}
	@Transactional
	public String affecterUserAbonnement(Long userid, String type) {

		Abonnement AB = new Abonnement();
		Date g = new Date();
		g.getTime();

		List<String> list = new ArrayList<String>();
		List<String> listtotale = new ArrayList<String>();

		AbonnementDetail abonnementDetail = new AbonnementDetail();
		AbonnementDetail abonnementDetailaremove = new AbonnementDetail();
		User user = UserRepository.findById(userid).get();

		for (int i = 0; i < AbonnementRepository.findByType(type).size(); i++)
			AB = AbonnementRepository.findByType(type).get(i);

		for (int i = 0; i < AB.getUser().size(); i++)
			list.add(AB.getUser().get(i).getUsername());

		if (list.contains(user.getUsername()))
			return "tu es deja inscrit au abonnement " + type;

		abonnementDetailaremove = user.getAbonnementDetail();

		if (abonnementDetailaremove.getPointsfideliteuser() < AB.getPointsfidelite())
			return "you dont have points for  " + type + "(" + AB.getPointsfidelite() + ")";

		user.setAbonnement(AB);

		abonnementDetail.setDeteabon(g);
		abonnementDetail
				.setPointsfideliteuser(abonnementDetailaremove.getPointsfideliteuser() - AB.getPointsfidelite());
		abonnementDetail.setRemise(AB.getRemise());
		user.setAbonnementDetail(abonnementDetail);

		UserRepository.saveAndFlush(user);
		AbonnementDetailRepository.delete(abonnementDetailaremove);

		return "you are now " + type + " Enjoy !";
	}

	@Override
	public User calculnbrpoints(Long userid, float prixtotale) {
		// TODO Auto-generated method stub

		User user = new User();
		User usergagnant = new User();

		user = UserRepository.findById(userid).get();
		
		
	if(	user.getReporting().getUsrnameparrain().equals("no One") == false)
	{
		usergagnant = 	UserRepository.findOneByUsername(user.getReporting().getUsrnameparrain());
		
		if (user.getReporting().getNbrgain()<5)
			
		{
			int prix2 = (int) (prixtotale / 100)/2;

			usergagnant.getAbonnementDetail().setPointsfideliteuser(usergagnant.getAbonnementDetail().getPointsfideliteuser() + prix2);
		
			user.getReporting().setNbrgain(user.getReporting().getNbrgain()+1);
			UserRepository.save(user);
			UserRepository.save(usergagnant);
		//notification
		}
	}

		int prix = (int) (prixtotale / 100);

		user.getAbonnementDetail().setPointsfideliteuser(user.getAbonnementDetail().getPointsfideliteuser() + prix);
		UserRepository.save(user);
		return user;
	}

	@Override
	public void statistics(int age) {

		// TODO Auto-generated method stub
//
//		List<String> list = ProductRespository.countSellBySize(age);
//		List<Integer> list1;
//
//		Map<String, Integer> map = new HashMap<>();
//		for (int i = 0; i < list.size(); i++) {
//			if (map.containsKey(list.get(i)))
//				map.replace(list.get(i), map.get(list.get(i)) + 1);
//
//			// Sinon on l'ajoute
//
//			else {
//				map.put(list.get(i), 1);
//			}
//
//		}
//
//		Set set = map.entrySet();// Converting to Set so that we can traverse
//		Iterator itr = set.iterator();
//		while (itr.hasNext()) {
//			// Converting to Map.Entry so that we can get key and value
//			// separately
//			Map.Entry entry = (Map.Entry) itr.next();
//			System.err.println(entry.getKey() + " " + entry.getValue());
//		}

		// System.err.println(map);

	}

	@Override
	public String deleteuser(Long id) {
		// TODO Auto-generated method stub
		User u = new User();
		u = UserRepository.findById(id).get();
		UserRepository.delete(u);
		return null;
	}

	@Override
	public String statisticsdate(int age, java.sql.Date d, java.sql.Date d1) {
		// TODO Auto-generated method stub
//
//		List<String> list = ProductRespository.countySize(age, d, d1);
//		List<Integer> list1;
//
//		Map<String, Integer> map = new HashMap<>();
//		for (int i = 0; i < list.size(); i++) {
//			if (map.containsKey(list.get(i)))
//				map.replace(list.get(i), map.get(list.get(i)) + 1);
//
//			// Sinon on l'ajoute
//
//			else {
//				map.put(list.get(i), 1);
//			}
//
//		}
//
//		Set set = map.entrySet();// Converting to Set so that we can traverse
//		Iterator itr = set.iterator();
//		while (itr.hasNext()) {
//			// Converting to Map.Entry so that we can get key and value
//			// separately
//			Map.Entry entry = (Map.Entry) itr.next();
//			System.err.println(entry.getKey() + " " + entry.getValue());
//
//		}
//		return map.toString();
		return "";
	}

	@Override
	public Float calculsommedescommand(java.sql.Date d, java.sql.Date d1) {
		// TODO Auto-generated method stub

		return 1F ;// CommandRepository.getsommedesprixdescommande(d, d1);

	}

	@Override
	public float gain(java.sql.Date d, java.sql.Date d1) {
		// TODO Auto-generated method stub

		return 1f ;// CommandRepository.getsommedesprixdescommande(d, d1)
				//- (UserRepository.getsalaireparnmbrdejours(d, d1) + UserRepository.getAlldeponse(d, d1));

	}

	@Override
	public String nbrutilisateurpartype() {
		// TODO Auto-generated method stub

		List<String> list = AbonnementRepository.nbruserpartype();
		List<Integer> list1;

		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			if (map.containsKey(list.get(i)))
				map.replace(list.get(i), map.get(list.get(i)) + 1);

			// Sinon on l'ajoute

			else {
				map.put(list.get(i), 1);
			}

		}

		Set set = map.entrySet();// Converting to Set so that we can traverse
		Iterator itr = set.iterator();
		while (itr.hasNext()) {
			// Converting to Map.Entry so that we can get key and value
			// separately
			Map.Entry entry = (Map.Entry) itr.next();
			System.err.println(entry.getKey() + " " + entry.getValue());

		}
		return map.toString();

	}

	@Override
	public User findByusernameoremail(String usernameOrEmail) {

		User u = new User();
		u = UserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();

		// User u = UserRepository.findByUsernameOrEmail("");

		// TODO Auto-generated method stub
		return u;
	}
	
	
	
	
@Transactional
	@Override
	public String banneruser(Long iduser) {
		// TODO Auto-generated method stub

	
	
		 
			User u = new User();
			Reporting rep = new Reporting();
			Reporting reparemove = new Reporting();
			Date d = new Date();
			d.getTime();
			u = UserRepository.findById(iduser).get();
			reparemove = u.getReporting();
			rep = u.getReporting();
			rep.setIsbanned(true);
			d.setHours((24 * (rep.getNbrbanne() + 1)));

			rep.setDatefinbanne(d);
			rep.setNbrbanne(rep.getNbrbanne() + 1);
			u.setReporting(rep);

			UserRepository.save(u);
			ReportingRepository.delete(reparemove);
			this.desaffecterSignal(iduser);

			return "you are banned for " + (24 * (rep.getNbrbanne())) + " heures";
		
	//	return "nbr de signal < 2";
		// fazet remove
	}

	@Override
	public Boolean debanneruser(User u) {
		// TODO Auto-generated method stub
		Reporting rep = new Reporting();
		Reporting reparemove = new Reporting();
		rep = u.getReporting();

		reparemove = u.getReporting();

		rep.setIsbanned(false);
		UserRepository.save(u);
		ReportingRepository.delete(reparemove);

		return null;
	}

	@Override
	public Boolean verifdatebanne(User u) {
		// TODO Auto-generated method stub
		Date d = new Date();
		d.getTime();
		Reporting rep = new Reporting();
		Reporting reparemove = new Reporting();
		rep = u.getReporting();
		reparemove = u.getReporting();

		if (d.compareTo(rep.getDatefinbanne()) < 0 && rep.getIsbanned()) {

			return true;

			// return " you can signin in : " +
			// u.getReporting().getDatefinbanne() ;

		}
		return false;

	}


	@Override
	public String parrinage(String code, Long iduser) {
		// TODO Auto-generated method stub

		User u = UserRepository.findById(iduser).get();
		Reporting rep = new Reporting();
		Reporting reparemove = new Reporting();

		rep = u.getReporting();
		reparemove = u.getReporting();

		rep.setCodeparrinage(code);

		u.setReporting(rep);

		UserRepository.save(u);
		ReportingRepository.delete(reparemove);

		
		return null;
	}

	@Override
	public User finduserbycode(String code) {
		// TODO Auto-generated method stub

		User u = new User();

		u = UserRepository.getuserbycodeparrinage(code);

		return u;
	}

	@Override
	public String ajoutPointFideliteUserParrainage(User u) {

		// TODO Auto-generated method stub
		AbonnementDetail ad = new AbonnementDetail();

		AbonnementDetail adaremove = new AbonnementDetail();

		ad = u.getAbonnementDetail();
		adaremove = u.getAbonnementDetail();

		ad.setPointsfideliteuser(ad.getPointsfideliteuser() + 10);

		u.setAbonnementDetail(ad);
		UserRepository.save(u);
		AbonnementDetailRepository.delete(adaremove);
		return "vous avez recu 10 points grace a votre ami ";
	}

	static String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	@Override
	public String generatecodeparrainage() {
		// TODO Auto-generated method stub

		Random objGenerator = new Random();
		int randomNumber = objGenerator.nextInt(35);

		String s = getAlphaNumericString(randomNumber);
		// String genarate = "
		// http://localhost:8081/SpringMVC/servlet/api/userParinage/" + s;

		return s;

	}

	@Override
	public User getuserbyid(Long id) {
		// TODO Auto-generated method stub
		User user = new User();
		user = UserRepository.findById(id).get();
		return user;
	}

	@Override
	@Transactional
	@Transient
	public String confirmation(String code) {
		// TODO Auto-generated method stub
		
		
		
		
		String s = "";
		User user = new User();
		User userajout = new User();
		user = this.finduserbycode(code);
 ///// front
		
		Profile.setUsernameconfirmation(user.getUsername());
///////
		user.setActive(true);
		List<String> l = user.getReporting().getIpAdresses();

		if (user.getReporting().getUsrnameparrain().equals("no One") == false) {
			


			if (    l==null ||  l.contains(user.getReporting().getUsrnameparrain()) == false) {
				
				
		
				
				
				
				
				l.add(user.getReporting().getUsrnameparrain());
				

				userajout = UserRepository.findOneByUsername(user.getReporting().getUsrnameparrain());
				
				
				
				///// Notification
				Date gn = new Date();
				
				Long idN= notificationService.addNotification(new ProductNotif("POINTS FIDELITE","Vous avez recu 10 points grace a votre amie",gn,false));
				notificationService.affectNotificationTo(idN, userajout);
				
		
			//	NotificationRepository.save(arg0)
				
				
				
				////
				
				
				userajout.getAbonnementDetail()
						.setPointsfideliteuser(userajout.getAbonnementDetail().getPointsfideliteuser() + 10);
				UserRepository.save(userajout);
				s = s + "votre ami " + user.getReporting().getUsrnameparrain() + " a recu 10 points grace a vous";

			} else
				s = s + "vous avez deja parrainer cette personne " + user.getReporting().getUsrnameparrain();
		}

		User result = UserRepository.saveAndFlush(user);
		s = s + "user enregistré avec succe";

		return s;
	}

	@Override
	public String userrole(Long iduser) {
		// TODO Auto-generated method stub

		return UserRepository.getuserrole(iduser);
	}

	@Override
	public User setusertoab(User u) {
		// TODO Auto-generated method stub
		AbonnementDetail ad = new AbonnementDetail();
		AbonnementDetail adaremove = new AbonnementDetail();

		ad = u.getAbonnementDetail();
		adaremove = u.getAbonnementDetail();

		ad.setRemise(0);
		ad.setDeteabon(u.getAbonnementDetail().getDeteabon());
		ad.setPointsfideliteuser(u.getAbonnementDetail().getPointsfideliteuser());
		u.setAbonnementDetail(ad);
		UserRepository.save(u);
		return u;
	}

	@Override
	public String vide() {
		// TODO Auto-generated method stub
		return "aaa";

	}

	
	@Override
	public String SignalerUser(Long iduser, Long iduserabanner) {
		// TODO Auto-generated method stub

		User user = UserRepository.findById(iduser).get();
		User userabanner = UserRepository.findById(iduserabanner).get();

		List<User> users = new ArrayList<>();
		if (user.getUsers() == null) {

			users.add(userabanner);
			user.setUsers(users);
		} else {

			if (!user.getUsers().contains(userabanner)) {
				users = user.getUsers();
				users.add(userabanner);
				user.setUsers(users);
			} else
				return "deja signaleé";
		}

		UserRepository.save(user);

		return "signal succes";
	}

	@Override
	public int countnbrsignal(Long iduser) {
		// TODO Auto-generated method stub

		return UserRepository.nbrsignal(iduser);
	}

	@Override
	public void desaffecterSignal(Long iduser) {
		// TODO Auto-generated method stub

		User user = UserRepository.findById(iduser).get();
		User userabanner = new User();
		List<Long> l = UserRepository.lesId(iduser);
		List<User> users = new ArrayList<>();

		System.err.println(l);

		for (int i = 0; i < l.size(); i++) {

			userabanner = UserRepository.findById(l.get(i)).get();
			users = userabanner.getUsers();
			System.err.println(userabanner);
			if (userabanner.getUsers().contains(user)) {

				System.err.println(userabanner.getUsers());

				users = userabanner.getUsers();
				users.remove(user);
				userabanner.setUsers(users);
				UserRepository.save(userabanner);

			}

		}

	}
	
	
	
	
	public float convertirPionts(int points){
		
		float nbr ;
		nbr =(float) points * 0.1f;
		
		return nbr;
		
		
		
	}

	@Override
	@Transactional
	public void convertirPointsuser(float solde,Long iduser, int points) {
		// TODO Auto-generated method stub
		System.out.println("sdqs"+solde  + "zaz"+iduser);
		
		 
	User u =	UserRepository.findById(iduser).get();
	AbonnementDetail ad = new AbonnementDetail();
	AbonnementDetail adaremove = new AbonnementDetail();

	ad = u.getAbonnementDetail();
	adaremove = u.getAbonnementDetail();

	ad.setRemise(0);
	ad.setDeteabon(u.getAbonnementDetail().getDeteabon());
	ad.setPointsfideliteuser(u.getAbonnementDetail().getPointsfideliteuser() - points);
	
	ad.setSolde(solde + u.getAbonnementDetail().getSolde() );
	
	
	
	
	
	u.setAbonnementDetail(ad);
	
	//u.getAbonnementDetail().setSolde(u.getAbonnementDetail().getSolde()+ solde);
	

	UserRepository.saveAndFlush(u);
		
	}

	@Override
	public void nbrgainparuser(Long iduser, float prix) {
		// TODO Auto-generated method stub
		
	User user =	UserRepository.findById(iduser).get();
		
	
	if (user.getReporting().getUsrnameparrain().equals("no one")==false)
	{
		User usergagnat = UserRepository.findOneByUsername(user.getReporting().getUsrnameparrain());
		
		
	if(	user.getCommands().size()  < 10 )
	{
		usergagnat.getAbonnementDetail().setPointsfideliteuser(usergagnat.getAbonnementDetail().getPointsfideliteuser() + 10);
		
		
	}
	
	}
		
	}

	@Override
	public List<List<String>> nbrgainuserspa(Long iduser) {
		// TODO Auto-generated method stub
		
		
		
		
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh   "+UserRepository.nbrgaindesuser(UserRepository.findById(iduser).get().getReporting().getUsrnameparrain()));
		
		return UserRepository.nbrgaindesuser(UserRepository.findById(iduser).get().getUsername());
		
		//return null ;
	}

	@Override
	public String getbrand(String cat, java.sql.Date d, java.sql.Date d1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getbrand(String cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Float> revenuparbrand(String cat, java.sql.Date d, java.sql.Date d1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getNbrProdVenduParCateg(String cat) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}