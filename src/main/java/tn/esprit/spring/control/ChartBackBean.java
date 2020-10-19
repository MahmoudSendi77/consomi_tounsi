package tn.esprit.spring.control;

import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.ChartSeries;
import org.chartistjsf.model.chart.LineChartModel;
import org.chartistjsf.model.chart.LineChartSeries;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.ItemSelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.enums.Statics;

@ManagedBean
@Scope(value = "session")
@Controller(value = "ChartBackBean")
@ELBeanName(value = "ChartBackBean")
@Join(path = "/login", to = "/login/name.jsf")
public class ChartBackBean {
    private LineChartModel lineChartModel;
    private Statics role = Statics.Year;
    private Date date3;
    private List<Date> range;
    
	public List<Date> getRange() {
		return range;
	}

	public void setRange(List<Date> range) {
		this.range = range;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public Statics getRole() {
		return role;
	}

	public void setRole(Statics role) {
		this.role = role;
	}

	public Statics[] getRoles() {
		return Statics.values();
	}
    
    public ChartBackBean () {
       
    }
    public LineChartModel createLineModel() {
    	
    	
    	
    	
    	
    	
        lineChartModel = new LineChartModel();
        lineChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
        
        
        if(role.equals(Statics.Month))
        	
        {
        lineChartModel.addLabel("1");
        lineChartModel.addLabel("2");
        lineChartModel.addLabel("3");
        lineChartModel.addLabel("4");
        lineChartModel.addLabel("5");
        lineChartModel.addLabel("6");
        lineChartModel.addLabel("7");
        lineChartModel.addLabel("8");
        LineChartSeries lineChartSeries1 = new LineChartSeries();
        lineChartSeries1.setName("month 1");
        lineChartSeries1.set(12);
        lineChartSeries1.set(21);
        lineChartSeries1.set(7);
        lineChartSeries1.set(15);
        lineChartSeries1.set(12);
        lineChartSeries1.set(21);
        lineChartSeries1.set(7);
        lineChartSeries1.set(15);
        LineChartSeries lineChartSeries2 = new LineChartSeries();
        lineChartSeries2.setName("month 2");
        lineChartSeries2.set(14);
        lineChartSeries2.set(2);
        lineChartSeries2.set(8);
        lineChartSeries2.set(9);
        lineChartSeries2.set(14);
        lineChartSeries2.set(2);
        lineChartSeries2.set(8);
        lineChartSeries2.set(9);
        LineChartSeries lineChartSeries3 = new LineChartSeries();
        lineChartSeries3.setName("month 3");
        lineChartSeries3.set(1);
        lineChartSeries3.set(24);
        lineChartSeries3.set(84);
        lineChartSeries3.set(94);
        lineChartSeries3.set(1);
        lineChartSeries3.set(24);
        lineChartSeries3.set(4);
        lineChartSeries3.set(4);
        
        org.chartistjsf.model.chart.Axis xAxis = lineChartModel.getAxis(AxisType.X);
        lineChartModel.addSeries(lineChartSeries1);
        lineChartModel.addSeries(lineChartSeries2);
        lineChartModel.addSeries(lineChartSeries3);
        
        }
        else{  lineChartModel.addLabel("1");
        lineChartModel.addLabel("2");
        lineChartModel.addLabel("3");
      
        LineChartSeries lineChartSeries1 = new LineChartSeries();
        
        lineChartSeries1.set(7);
        lineChartSeries1.set(15);
        LineChartSeries lineChartSeries2 = new LineChartSeries();
        lineChartSeries2.setName("anne 2");
       
        lineChartSeries2.set(8);
        lineChartSeries2.set(9);
        LineChartSeries lineChartSeries3 = new LineChartSeries();
        lineChartSeries3.setName("anne 3");
       
        lineChartSeries3.set(4);
        lineChartSeries3.set(4);
        
        org.chartistjsf.model.chart.Axis xAxis = lineChartModel.getAxis(AxisType.X);
        lineChartModel.addSeries(lineChartSeries1);
        lineChartModel.addSeries(lineChartSeries2);
        lineChartModel.addSeries(lineChartSeries3);}
        
        lineChartModel.setAnimateAdvanced(true);
        lineChartModel.setShowTooltip(true);
        lineChartModel.setShowArea(true);
        System.err.println("dddd");
        
        return lineChartModel;
        
    }
    public void itemSelect(ItemSelectEvent event) {
       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "+ 
((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getData().get(event.getItemIndex())
                + ", Series name:" +
 ((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getName());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }
    public LineChartModel getLineModel() {
    	lineChartModel = createLineModel();
        return lineChartModel;
    }
    public void setLineModel(LineChartModel lineModel) {
        this.lineChartModel = lineModel;
    }
}