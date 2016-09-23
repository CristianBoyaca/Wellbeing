/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.Certificado;
import com.wellbeing.entidades.Usuario;
import com.wellbeing.facade.CertificadoFacade;
import com.wellbeing.facade.DatoEmpleadoFacade;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author cristian
 */
@Named
@SessionScoped
public class CertificadoControlador implements Serializable {

    @EJB
    private CertificadoFacade certificadoFacade;
    private Certificado certificado;
    @EJB
    private DatoEmpleadoFacade datoEmpleadoFacade;
    private UploadedFile archivo;

    @PostConstruct
    public void init() {
        certificado = new Certificado();
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }
    
    

    public void registrarCertificado() {
         InputStream fi;
        try {
            if (archivo.getSize() > 0) {
                fi = archivo.getInputstream();
               // archivo.getContentType();
                // creamos el buffer
                byte[] buffer = new byte[(int) archivo.getSize()];
                // Leer al buffer
                int readers = fi.read(buffer);

                certificado.setImgCertificado(buffer);
            }
            certificadoFacade.create(certificado);
            certificado.setImgCertificado(null);
            Integer tipoSolicitud = 4;
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            String documento = "";
            documento += datoEmpleadoFacade.buscarDocumento(u.getIdUsuario());
            certificadoFacade.registrarSolicitudCertificado(documento, tipoSolicitud);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Certificado", "Se ha registrado correctamente su solicitud "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar correctamente su solicitud de certificado"));
        }
    }

    

}
