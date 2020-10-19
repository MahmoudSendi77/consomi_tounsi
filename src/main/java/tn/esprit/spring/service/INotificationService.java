package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.ProductNotif;
import tn.esprit.spring.entities.User;

public interface INotificationService {

	public long addNotification(ProductNotif n);
	public long updateddNotification(ProductNotif n);
	public long deleteNotification(Long id);
	
	public List<ProductNotif> getNotificationByUser(long id);
	
	public List<ProductNotif> getNotReadedNotificationByRecever(long id);
	public List<ProductNotif> getReadedNotificationByRecever(long id);
	public void affectNotificationTo(long id,User user);
	
}
