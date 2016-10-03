/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


/**
 *
 * @author cristian
 */
@Named(value = "plantillaControlador")
@ViewScoped
public class PlantillaControlador implements Serializable {

    public void verificarSesion() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (u == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Wellbeing/faces/inicioSesion.xhtml");
            }
            if(u.getEstado()==2){
                 FacesContext.getCurrentInstance().getExternalContext().redirect("/Wellbeing/faces/protegido/cambioContrasenia.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede puede cargar la página"));
        }

    }
    
     public void verificarSesionTemporal() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (u == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Wellbeing/faces/inicioSesion.xhtml");
            }
             if(u.getEstado()!=2){
                 FacesContext.getCurrentInstance().getExternalContext().redirect("/Wellbeing/faces/protegido/inicio.xhtml");
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede puede cargar la página"));
        }

    }

    
  

}