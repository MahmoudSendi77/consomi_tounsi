package tn.esprit.spring.control;

import java.sql.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.chartistjsf.model.chart.PieChartModel;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.ItemSelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//import tn.esprit.spring.repository.ProductRespository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.UserService;
import tn.esprit.spring.service.UserserviceInterface;


@Scope(value = "session")
@Controller(value = "UserPieChartBean")
@ELBeanName(value = "UserPieChartBean")
public class UserPieChartBean {
	
	
	@Autowired
	UserserviceInterface UserserviceInterface;
	
	@Autowired
	SigninJsf SigninJsf;
	
	@Autowired
	UserRepository UserRepository;
	
	
	private UserService UserService;

	public UserserviceInterface getUserserviceInterface() {
		return UserService;
	}

	public void setUserserviceInterface(UserService userserviceInterface) {
		UserService = userserviceInterface;
	}

	private PieChartModel pieChartModel;


	public PieChartModel createPieChart() {
		pieChartModel = new PieChartModel();
		
		
		List<List<String>> l = UserserviceInterface.nbrgainuserspa(SigninJsf.idusercurrent());
		System.err.println( l.size());
		
		
		for (int i = 0; i < l.size(); i++)
		{
			if((int )Float.parseFloat(l.get(i).get(1)) < 10) {
		
			pieChartModel.addLabel(l.get(i).get(0));
			
			pieChartModel.set( 10 - (int )Float.parseFloat(l.get(i).get(1)));
			}
		}
		
	
		pieChartModel.setShowTooltip(true);
	//	pieChartModel.setAnimatePath(true);
	//	pieChartModel.setShowLabel(true);
		//pieChartModel.setChartPadding("brand label");
		pieChartModel.setDonut(true);
		
		
		
//	System.out.println(UserserviceInterface.getbrand("cat3"));	
		
		
		return pieChartModel ;
	}
public void fixpiechartmodel(){
	
	
	
	
}
	public void pieItemSelect(ItemSelectEvent event) {
	
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Value: " + pieChartModel.getData().get(event.getItemIndex()));
		FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
	}

	public PieChartModel getPieChartModel() {
		pieChartModel =	createPieChart();
		return pieChartModel;
	}

	public void setPieChartModel(PieChartModel pieChartModel) {

		this.pieChartModel = pieChartModel;
	}
}