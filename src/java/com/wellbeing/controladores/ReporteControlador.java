package com.wellbeing.controladores;



import com.wellbeing.facade.SolicitudFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
 
@Named(value = "reporteControlador")
@SessionScoped
public class ReporteControlador implements Serializable {
   
    @EJB
    private SolicitudFacade solicitudFacade;
    private CartesianChartModel combinedModel;
    
 
    @PostConstruct
    public void init() {
        createCombinedModel();
    }
 
    public CartesianChartModel getCombinedModel() {
        return combinedModel;
    }
     
    private void createCombinedModel() {
        combinedModel = new BarChartModel();

        
        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Boys");
 
        boys.set("Beneficios",1);
        boys.set("Bonificaciones", 11);
        boys.set("Certificados", 14);
        boys.set("Vacaciones", 4);
        boys.set("2008", 25);
 
        LineChartSeries girls = new LineChartSeries();
        girls.setLabel("Girls");
 
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
 
        combinedModel.addSeries(boys);
        combinedModel.addSeries(girls);
         
        combinedModel.setTitle("Bar and Line");
        combinedModel.setLegendPosition("ne");
        combinedModel.setMouseoverHighlight(false);
        combinedModel.setShowDatatip(false);
        combinedModel.setShowPointLabels(true);
        Axis yAxis = combinedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
}