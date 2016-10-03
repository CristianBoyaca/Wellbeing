/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Usuario;
import com.wellbeing.facade.DatoEmpleadoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cristian
 */
@Named(value = "datoEmpleadoControlador")
@SessionScoped
public class DatoEmpleadoControlador implements Serializable {

    @EJB
    private DatoEmpleadoFacade datoEmpleadoFacade;
    private DatoEmpleado datoEmpleado;
    private String nombreCompleto;
    private boolean estado;
    private Integer validador;
    private String identificacionEmpleado;
   
    @PostConstruct()
    public void init() {
        datoEmpleado = new DatoEmpleado();
        estado = false;
        validador = 0;

    }

    public DatoEmpleado getDatoEmpleado() {
        return datoEmpleado;
    }

    public void setDatoEmpleado(DatoEmpleado datoEmpleado) {
        this.datoEmpleado = datoEmpleado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Integer getValidador() {
        return validador;
    }

    public void setValidador(Integer validador) {
        this.validador = validador;
    }

    public String getIdentificacionEmpleado() {
        return identificacionEmpleado;
    }

    public void setIdentificacionEmpleado(String identificacionEmpleado) {
        this.identificacionEmpleado = identificacionEmpleado;
    }

    public String buscarNombre() {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return nombreCompleto = datoEmpleadoFacade.buscarNombre(u.getIdUsuario());
    }

    public List<DatoEmpleado> buscarInformacion() {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return datoEmpleadoFacade.buscarInformacionPersonal(u.getIdUsuario());
    }

    public void actualizarDatosEmpleado(DatoEmpleado datoEmpleado) {
        this.datoEmpleado = datoEmpleado;

    }

    public void actualizarDatosEmpleado() {
        try {
              
            if ((DatoEmpleado) datoEmpleadoFacade.buscarPorIdentificacion(datoEmpleado.getIdentificacion())!= null) {
                datoEmpleadoFacade.edit(datoEmpleado);
                estado = true;
                limpiar();
            } else {
                 datoEmpleado = new DatoEmpleado();
                validador = 2;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "En el momento no se pudo procesar su solicitud"));
        }

    }

    public void cambiarEstado() {
        estado = false;
    }

    public void crearDatosEmpleado() {

        try {
            datoEmpleadoFacade.create(datoEmpleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creaciòn", "Se ha Creado correctamente el empleado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo Crear correctamente el empleado"));
        }

    }

    public void consultarDatosEmpleadoPorIdentificacion() {

        this.datoEmpleado = (DatoEmpleado) datoEmpleadoFacade.buscarPorIdentificacion(this.identificacionEmpleado);
        if (this.datoEmpleado != null) {
            validador = 1;

        } else {
            this.datoEmpleado = new DatoEmpleado();
            validador = 2;
        }

        //return datoEmpleadoFacade.buscarPorIdentificacion(this.identificacionEmpleado);
    }

    public String limpiar() {

        datoEmpleado = new DatoEmpleado();
        return "";

    }

  

}
