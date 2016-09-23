/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Observacion;
import com.wellbeing.entidades.Usuario;
import com.wellbeing.facade.ObservacionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cristian
 */
@Named(value = "observacionControlador")
@SessionScoped
public class ObservacionControlador implements Serializable {
 
    @EJB
    private ObservacionFacade observacionFacade;
    private Observacion observacion;
    
    @PostConstruct
    public void init() {
        observacion = new Observacion();
    }

    public Observacion getObservacion() {
        return observacion;
    }

    public void setObservacion(Observacion observacion) {
        this.observacion = observacion;
    }
    
     public void registrarObservacion(DatoEmpleado documento) {
        try {

            Pattern p = Pattern.compile("[a-zA-Z]");
            Matcher m = p.matcher(observacion.getObservacion());

            if (!observacion.getObservacion().equals("") && m.find()) {
                observacionFacade.create(observacion);
                observacion.setObservacion("");
                observacion.setIdentificacion(documento);
                String datoEmpleado = "";
                datoEmpleado += observacion.getIdentificacion();
                observacionFacade.actualizarObservacion(datoEmpleado);
               
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro O", "Se ha registrado correctamente sus vacaciones"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar correctamente sus o " + observacion));

        }

    }
     
     public List<Observacion> buscarObservacionPorSolicitud(){

        return  observacionFacade.findAll();
    }
    
}
