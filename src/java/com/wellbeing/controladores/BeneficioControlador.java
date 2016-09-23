/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.Beneficio;
import com.wellbeing.entidades.Usuario;
import com.wellbeing.facade.BeneficioFacade;
import com.wellbeing.facade.UsuarioFacadeLocal;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author cristian
 */
@Named(value = "beneficioControlador")
@SessionScoped
public class BeneficioControlador implements Serializable {

    @EJB
    private BeneficioFacade beneficioFacade;
    private Beneficio beneficio;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private UploadedFile archivo;

    @PostConstruct
    public void init() {
        beneficio = new Beneficio();

    }

    public Beneficio getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public void cargarDocumento() {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        beneficio.setIdentificacion(usuarioFacadeLocal.buscarDocumento(u.getIdUsuario()));
    }

    public void registrarBeneficio() {

        InputStream fi;

        try {
            Integer tipoSolicitud=1;
            String documento = "";
            documento+=beneficio.getIdentificacion();
            if (archivo.getSize() > 0) {
                fi = archivo.getInputstream();
               // archivo.getContentType();
                // creamos el buffer
                byte[] buffer = new byte[(int) archivo.getSize()];
                // Leer al buffer
                int readers = fi.read(buffer);

                beneficio.setImgSoporte(buffer);
            }
           
            beneficioFacade.create(beneficio);
            
            beneficioFacade.registrarSolicitudBeneficio(documento, tipoSolicitud);
            beneficio.setImgSoporte(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Beneficio", "Se ha registrado correctamente su solicitud "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar correctamente su solicitud de beneficio"));
        }

    }
}
