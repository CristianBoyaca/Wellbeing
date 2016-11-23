/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cristian
 */
@Named(value = "contactoControlador")
@RequestScoped
public class ContactoControlador {

    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String mensaje;
    private String formato;
    private CorreoControlador correoControlador;

    public ContactoControlador() {
        inicializar();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public CorreoControlador getCorreoControlador() {
        return correoControlador;
    }

    public void setCorreoControlador(CorreoControlador correoControlador) {
        this.correoControlador = correoControlador;
    }

    public void inicializar() {
        nombreCompleto = "";
        correo = "";
        telefono = "";
        mensaje = "";
        formato = "";
        correoControlador = new CorreoControlador();
    }

    public void contactar() {
        formato = correoControlador.getCorreo().agregarHtml("/com/wellbeing/util/formatos/contactenos.xhtml");
        formato = formato.replace("{nombreCompleto}", nombreCompleto);
        formato = formato.replace("{contenido}", mensaje);
        formato = formato.replace("{telefono}", telefono);
        formato = formato.replace("{correo}", correo);
        correoControlador.contactar("iskenderungroup@gmail.com", formato);
        inicializar();
    }

}
