package tn.esprit.spring.servlet;

import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import tn.esprit.spring.control.SigninJsf;
import tn.esprit.spring.entities.ProductNotif;
import tn.esprit.spring.service.INotificationService;

@Controller(value = "productNotifController")
@ELBeanName(value = "productNotifController")
//scope mta3 lpage lezm tetbadel 3lech ?
@RequestScope
public class ProductNotifController {
	
	@Autowired
	INotificationService notificationService;
	@Autowired
	SigninJsf signinJsf;
	private Long idCurrentUser;
	
	private List<ProductNotif> listNotification;
	private int listNotificationLengh;

	public int getListNotificationLengh() {		
		listNotificationLengh = listNotification != null ?listNotification.size():0;
		return listNotificationLengh;
	}

	public Long getIdCurrentUser() {
		
		//idCurrentUser= signinJsf.nameuser() ? signinJsf.idusercurrent() : 0L;
		return idCurrentUser;
	}

	public void setIdCurrentUser(Long idCurrentUser) {
		this.idCurrentUser = idCurrentUser;
	}

	public void setListNotificationLengh(int listNotificationLengh) {
		this.listNotificationLengh = listNotificationLengh;
	}

	public List<ProductNotif> getListNotification() {
		updateListNotification();
		return listNotification;
	}

	public void setListNotification(List<ProductNotif> listNotification) {
		this.listNotification = listNotification;
	}

	//@PostConstruct
	public void updateListNotification() {
		
		//listNotification = notificationService.getNotificationByUser(getIdCurrentUser());
	}
	
	public String navigateToNotificationList(){
		return "/pages/frontend/aisleViews/notificationListView.xhtml?faces-redirect=true";
	 }
	
	public void deleteNotification(long id){
		System.out.println("behi tw nafs5ou ");
	//	notificationService.desaffectNotificationTo(id, user);
	 }
}
