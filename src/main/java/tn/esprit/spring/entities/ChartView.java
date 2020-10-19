package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.control.SigninJsf;
import tn.esprit.spring.repository.AbonnementRepository;
import tn.esprit.spring.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.MeterGaugeChartModel;

@Controller(value = "ChartView")
@ELBeanName(value = "ChartView")
public class ChartView implements Serializable {
	
	@Autowired
	SigninJsf SigninJsf;
	@Autowired
	UserRepository UserRepository;
	
	@Autowired
	AbonnementRepository AbonnementRepository;

    private MeterGaugeChartModel meterGaugeModel1;
    private MeterGaugeChartModel meterGaugeModel2;
    private int mespoints;
    
    public List<Abonnement> getAbstotle() {
    	
    	
    	abstotle = AbonnementRepository.findAll();
    	
 
   
		return abstotle;
	}






	public void setAbstotle(List<Abonnement> abstotle) {
		this.abstotle = abstotle;
	}

	private List<Abonnement> abstotle ; 
    

	private List<Abonnement> abs ;
private List<Abonnement> abonnementsautres;



    public List<Abonnement> getAbonnementsautres() {
    	abonnementsautres = AbonnementRepository.findAll();
    	
    	if(abonnementsautres .size() < 3)
    	{
    		abonnementsautres .clear();
    	
    	}
	return abonnementsautres.subList(3, abonnementsautres.size());
}

    

    
    
    private List<Abonnement> absbase ;
    
    
    
    
    
    
public List<Abonnement> getAbsbase() {
	
	
	
	absbase = AbonnementRepository.abonnementtrier();
		return absbase;
	}






	public void setAbsbase(List<Abonnement> absbase) {
		this.absbase = absbase;
	}






public void setAbonnementsautres(List<Abonnement> abonnementsautres) {
	this.abonnementsautres = abonnementsautres;
}

	public List<Abonnement> getAbs() {
    	
     abs = AbonnementRepository.findAll().subList(0, 3);
    	
		return abs;
	}

	public void setAbs(List<Abonnement> abs) {
		this.abs = abs;
	}

	public int getMespoints() {
		
		
		
	    
		
		
    	
    	
    	if (	SigninJsf.nameuser() ==false  )
        {
    		
    		
    		mespoints=
        UserRepository.findById(SigninJsf.idusercurrent())	.get().getAbonnementDetail().getPointsfideliteuser();
    	
        }
		return mespoints;
	}

//	@PostConstruct
//    public void abon(){
//    	
//    	
//  abs  = AbonnementRepository.findAll();
//
//    	
//    }
//    
	public void setMespoints(int mespoints) {
		this.mespoints = mespoints;
	}

	@PostConstruct
    public void init() {
        createMeterGaugeModels();
    }

    public MeterGaugeChartModel getMeterGaugeModel1() {
    	
    	
    	
        return meterGaugeModel1;
    }

    public MeterGaugeChartModel getMeterGaugeModel2() {
    	
    if (	SigninJsf.nameuser() ==false  )
    {
    	
    	
    	
  	String couleurs="" ;
//        List<Abonnement> abs = AbonnementRepository.findAll();
//    	for(int i= 0 ; i< abs.size();i++)
//    	{
//    		if (couleurs.equals(""))
//    			couleurs = abs.get(i).getCouleur();
//    		else
//    		couleurs = couleurs + "," + abs.get(i).getCouleur();
//    	}
//  	meterGaugeModel2.setSeriesColors(couleurs);
    	
    	
    //	UserRepository.findById(SigninJsf.idusercurrent())	.get().getAbonnementDetail().getPointsfideliteuser();
    	
    	
    	meterGaugeModel2.setValue(UserRepository.findById(SigninJsf.idusercurrent())	.get().getAbonnementDetail().getPointsfideliteuser());
    	List<Number> intervals = new ArrayList<Number>();
    	
    	
    	List<Abonnement> abs = AbonnementRepository.findAll();
    	for(int i= 0 ; i<this.getAbsbase() .size();i++)
    	{
    		
    		// System.err.println("points "+this.getAbsbase().get(i).getPointsfidelite());	
    	
    		 intervals.    add(this.getAbsbase().get(i).getPointsfidelite());
        
    	
    			if (couleurs.equals("")){
        			couleurs = this.getAbsbase().get(i).getCouleur();}
        		else{
        		couleurs = couleurs + "," + this.getAbsbase().get(i).getCouleur();
        		}
        	
      	meterGaugeModel2.setSeriesColors(couleurs);
    	meterGaugeModel2.setIntervals(intervals);
    	
 	meterGaugeModel2.setGaugeLabel("Points "+    UserRepository.findById(SigninJsf.idusercurrent())	.get().getAbonnementDetail().getPointsfideliteuser());
 	meterGaugeModel2.setGaugeLabelPosition("bottom");

 	meterGaugeModel2.setShadow(true);
 	meterGaugeModel2.isShowTickLabels();
 	meterGaugeModel2.setLabelHeightAdjust(20);
 
    	}
    	
    }
        return meterGaugeModel2;
    }

    private MeterGaugeChartModel initMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>(){{
        	
        		
        	
//        	  add(120);
//              add(400);
              
        }};
        
        
		List<Abonnement> abs = AbonnementRepository.findAll();
    	for(int i= 0 ; i< this.getAbsbase().size();i++)
    	{
    		
    		
    	
    		 intervals.    add(this.getAbsbase().get(i).getPointsfidelite());
        
        
        
      // add(50);        
     
    	
    	
    	}

        return new MeterGaugeChartModel(0, intervals);
    }

    private void createMeterGaugeModels() {
//        meterGaugeModel1 = initMeterGaugeModel();
//        meterGaugeModel1.setTitle("Points");
//        
//        meterGaugeModel1.set
        
        
        
    	
        

        meterGaugeModel2 = initMeterGaugeModel();
////        meterGaugeModel2.setIntervalInnerRadius(10);
//        meterGaugeModel2.setIntervalOuterRadius(10);
        meterGaugeModel2.setTitle("Points Fidelités");
        
        String couleurs="" ;
        List<Abonnement> abs = AbonnementRepository.findAll();
    	for(int i= 0 ; i< this.getAbsbase().size();i++)
    	{
    		if (couleurs.equals(""))
    			couleurs = this.getAbsbase().get(i).getCouleur();
    		else
    		couleurs = couleurs + "," + this.getAbsbase().get(i).getCouleur();
    	}
  	meterGaugeModel2.setSeriesColors(couleurs);
        
        
   //meterGaugeModel2.setSeriesColors("darkcyan,2F4F4F,FFD700");
//        meterGaugeModel2.setGaugeLabel("Points");
//        
        meterGaugeModel2.setShowTickLabels(true);
      meterGaugeModel2.isShowTickLabels();
//      meterGaugeModel2.setIntervalInnerRadius(10);
//      meterGaugeModel2.
//        meterGaugeModel2.setLabelHeightAdjust(110);
//        meterGaugeModel2.setIntervalOuterRadius(100);
    }
    
    
    public int points()
    {
    
    	return UserRepository.findById(SigninJsf.idusercurrent())	.get().getAbonnementDetail().getPointsfideliteuser();
    }

}