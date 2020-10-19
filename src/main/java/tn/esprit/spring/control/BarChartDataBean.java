package tn.esprit.spring.control;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//import org.apache.xerces.impl.xpath.XPath.Axis;
import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.BarChartModel;
import org.chartistjsf.model.chart.BarChartSeries;
import org.chartistjsf.model.chart.ChartSeries;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.ItemSelectEvent;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller(value = "BarChartDataBean")
@ELBeanName(value = "BarChartDataBean")
public class BarChartDataBean {
	private BarChartModel barChartModel;

	public BarChartDataBean() {
		createBarModel();
	}

	public void createBarModel() {
		barChartModel = new BarChartModel();
		barChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		barChartModel.addLabel("Measure1");
		barChartModel.addLabel("Measure2");
		barChartModel.addLabel("Measure3");
		barChartModel.addLabel("Measure4");
		barChartModel.addLabel("Measure5");
		BarChartSeries series1 = new BarChartSeries();
		series1.setName("First Series");
		series1.set(19.5);
		series1.set(27);
		series1.set(12);
		series1.set(33);
		series1.set(40);
		BarChartSeries series2 = new BarChartSeries();
		series2.setName("Second Series");
		series2.set(13);
		series2.set(61);
		series2.set(16);
		series2.set(28);
		series2.set(11);
		BarChartSeries series3 = new BarChartSeries();
		series3.setName("Third Series");
		series3.set(18);
		series3.set(11);
		series3.set(21);
		series3.set(53);
		series3.set(48);
		org.chartistjsf.model.chart.Axis xAxis = barChartModel.getAxis(AxisType.X);
		xAxis.setShowGrid(false);
		barChartModel.addSeries(series1);
		barChartModel.addSeries(series2);
		barChartModel.addSeries(series3);
		barChartModel.setShowTooltip(true);
		barChartModel.setSeriesBarDistance(15);
		barChartModel.setAnimateAdvanced(true);
		

	}

	public BarChartModel getBarChartModel() {
		return barChartModel;
	}

	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}

	public void barItemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Value: "
						+ ((ChartSeries) barChartModel.getSeries().get(event.getSeriesIndex())).getData()
								.get(event.getItemIndex())
						+ ", Series name:"
						+ ((ChartSeries) barChartModel.getSeries().get(event.getSeriesIndex())).getName());
		FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
	}
}
