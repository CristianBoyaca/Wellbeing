/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.Usuario;
import com.wellbeing.facade.UsuarioFacade;
import com.wellbeing.util.Correo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
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
    
   
    
    public CorreoControlador() {
        correo = new Correo();
    }

    public Correo getCorreo() {
        return correo;
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
    }

    
     
    public void recuperarContrasenia(String correo,String contenido){
  
            this.correo.setCorreoDestinatario(correo);
            this.correo.setAsunto("Recuperación De Contraseña Sistema Wellbeing");
            this.correo.setContenido(contenido);
            enviarMensaje(1);
        
    }
    
    public void notifiacionCreacion(String correo,String contenido){
  
            this.correo.setCorreoDestinatario(correo);
            this.correo.setAsunto("Notificaciòn Creaciòn de Usuario Wellbeing");
            this.correo.setContenido(contenido);
            //url="formatos/recordarContrasenia.xhtml";
            enviarMensaje(1);
        
    }
    
    public void notificacionMasiva(List<String> correos,String contenido){
    
         for (int i = 0; i < correos.size(); i++) {
            this.correo.setCorreoDestinatario(correos.get(i));
            this.correo.setAsunto("Notificaciòn Creaciòn de Usuario Wellbeing");
            this.correo.setContenido(contenido);
            //url="formatos/recordarContrasenia.xhtml";
            enviarMensaje(1); 
             
        }
           
        
    }
        
    
    public void enviarMensaje(Integer tipo) {
        if (correo.enviarCorreo()) {
            if(tipo==1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recuperación de cuenta", "Se te ha enviado una nueva contraseña al correo electrónico personal"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo enviar el mensaje.Por favor contacte al administrador"));
        }

    }

}
