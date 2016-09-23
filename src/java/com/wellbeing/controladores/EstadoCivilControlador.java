/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;


import com.wellbeing.entidades.EstadoCivil;
import com.wellbeing.facade.EstadoCivilFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author cristian
 */
@Named(value = "estadoCivilControlador")
@SessionScoped
public class EstadoCivilControlador implements Serializable {

    @EJB
    private EstadoCivilFacade estadoCivilFacade;
    private EstadoCivil estadoCivil;
   

    @PostConstruct
    public void init() {
        estadoCivil = new EstadoCivil();
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    
    public List<EstadoCivil> listarEstadosCiviles() {
        return estadoCivilFacade.findAll();
    }
   
     public EstadoCivil getEstadoCivil(java.lang.Integer id) {
        return estadoCivilFacade.find(id);
    }
     
     @FacesConverter(forClass = EstadoCivil.class)
    public static class EstadoCivilControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoCivilControlador controlador = (EstadoCivilControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoCivilControlador");
            return controlador.getEstadoCivil(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EstadoCivil) {
                EstadoCivil o = (EstadoCivil) object;
                return getStringKey(o.getIdEstadoCivil());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoCivil.class.getName());
            }
        }

    }

}
