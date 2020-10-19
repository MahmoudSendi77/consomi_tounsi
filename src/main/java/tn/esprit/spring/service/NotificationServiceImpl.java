package tn.esprit.spring.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Aisle;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.ProductNotif;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.NotificationRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
@EnableScheduling
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public long addNotification(ProductNotif n) {
		notificationRepository.save(n);
		// TODO Auto-generated method stub
		return n.getId();
	}

	@Override
	public long updateddNotification(ProductNotif n) {
		notificationRepository.save(n);
		return 0;
	}

	@Override
	public long deleteNotification(Long id) {
		notificationRepository.delete(notificationRepository.findById(id).get());
		return 0;
	}

	@Override
	public List<ProductNotif> getNotificationByUser(long id) {
		// TODO Auto-generated method stub
		return notificationRepository.getNotificationByUser(id);
	}

	@Override
	public List<ProductNotif> getNotReadedNotificationByRecever(long id) {
		return notificationRepository.findByUsersAndSeen(id, false);
	}

	@Override
	public List<ProductNotif> getReadedNotificationByRecever(long id) {
		return notificationRepository.findByUsersAndSeen(id, true);
	}

	public Map<Aisle, List<Product>> productsToMap(List<Product> products) {
		Map<Aisle, List<Product>> map = new HashMap<>();
		List<Product> prod = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			if (!map.containsKey(products.get(i).getAisle())) {
				map.put(products.get(i).getAisle(), new ArrayList<>());
			}
			if(map.get(products.get(i).getAisle())!=null)
			prod = map.get(products.get(i).getAisle());			
			prod.add(products.get(i));
			map.put(products.get(i).getAisle(), prod);
		}
		return map;
	}

	static int quantity;
	
	public boolean existInDB(){
		
	boolean b =false;
	return b;
	}


//	@Scheduled(fixedDelay = 1000000)
//	public void checkAndDeleteByValidationDate() {
//		List<Product> products = productRepository.getExpiredProduct();
//		Date date = new Date();
//		long id;
//		for(int i=0;i<products.size();i++){
//			//productRepository.delete(products.get(i));//
//		id= addNotification(new ProductNotif(" product have been expired and Deleted from Stock ",
//				date, false, null));
//		if(products.get(i).getAisle().getUser()!=null)
//		affectNotificationTo(id,products.get(i).getAisle().getUser());
//		}
//
//		}
//	
//	@Scheduled(fixedDelay = 1000000)
//	public void checkRedZoneProduct() {
//		List<Product> products = productRepository.getRedZonProduct(30);
//		Date date = new Date();
//		long id;
//		for(int i=0;i<products.size();i++){
//			//productRepository.delete(products.get(i));
//		id= addNotification(new ProductNotif(" product entred toRedZone",
//				date, false, new ArrayList<>()));
//		affectNotificationTo(id,products.get(i).getAisle().getUser());
//		}
//
//		}

	@Override
	public void affectNotificationTo(long id, User user) {
		ProductNotif productNotif= notificationRepository.findById(id).get();	
		List<User> users =new ArrayList<>();
		if(productNotif.getUsers()==null){
		users.add(user);		
		productNotif.setUsers(users);
		notificationRepository.save(productNotif);
		}
		else {		
			if(!productNotif.getUsers().contains(user)){			
			users =productNotif.getUsers();
			users.add(user);		
			productNotif.setUsers(users);
			notificationRepository.save(productNotif);
		}
			}
	}
	}


