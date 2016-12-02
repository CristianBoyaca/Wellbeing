/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.Bonificacion;
import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Usuario;
import com.wellbeing.facade.BonificacionFacade;
import com.wellbeing.facade.DatoEmpleadoFacade;
import com.wellbeing.facade.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cristian
 */
@Named(value = "bonificacionControlador")
@SessionScoped
public class BonificacionControlador implements Serializable {

    @EJB
    private BonificacionFacade bonificacionFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    private DatoEmpleadoFacade datoEmpleadoFacade;
    private Bonificacion bonificacion;
    private String documento;

    @PostConstruct
    public void init() {
        bonificacion = new Bonificacion();

    }

    public Bonificacion getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Bonificacion bonificacion) {
        this.bonificacion = bonificacion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void cargarDocumento() {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        documento = usuarioFacadeLocal.buscarDocumento(u.getIdUsuario()).getIdentificacion();
    }

    public void registrarBonificacion() {
        try {
            Integer tipoSolicitud = 2;
            if ((DatoEmpleado) datoEmpleadoFacade.buscarPorIdentificacion(documento) != null) {
                DatoEmpleado datoEmpleado = new DatoEmpleado();
                datoEmpleado.setIdentificacion(documento);
                bonificacion.setIdentificacion(datoEmpleado);
                bonificacionFacade.create(bonificacion);
                cargarDocumento();
                bonificacionFacade.registrarSolicitudBonificacion(documento, tipoSolicitud);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Bonificación", "Se ha registrado correctamente su bonificación "));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro Bonificación", "El empleado no se encuentra registrado "));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro Bonificación", "No se ha registrado correctamente su bonificación "));
        }
    }

}
