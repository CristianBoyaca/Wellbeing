/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.util.Correo;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cristian
 */
@Named(value = "correoControlador")
@RequestScoped
public class CorreoControlador {

    private Correo correo;
    private String asunto;
    private String contenido;
    
    public CorreoControlador() {
        correo=new Correo();
    }

    public Correo getCorreo() {
        return correo;
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public void enviarMensaje(){
        if (correo.enviarCorreo(asunto, contenido)) {
           correo.setCorreoRemitente("");
           correo.setContraseniaRemitente("");
           correo.setCorreoDestinatario("");
           asunto="";
           contenido="";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Enviado","Su mensaje ha sido enviado"));
        }
        else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error","Su mensaje no se pudo enviar"));
        }
    
    }
}
