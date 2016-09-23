/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.CajaCompensacion;
import com.wellbeing.facade.CajaCompensacionFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named(value = "cajaCompensacionControlador")
@SessionScoped
public class CajaCompensacionControlador implements Serializable {
    
     @EJB
    private CajaCompensacionFacade cajaCompensacionFacade;
    private CajaCompensacion cajaCompensacion;
    private int selectedItemCajaCompensacion;

    @PostConstruct
    public void init() {
        cajaCompensacion = new CajaCompensacion();
    }
    
    public CajaCompensacion getCajaCompensacion() {
        return cajaCompensacion;
    }

    public void setCajaCompensacion(CajaCompensacion cajaCompensacion) {
        this.cajaCompensacion = cajaCompensacion;
    }

    
    public List<CajaCompensacion> listarCajaCompensacion() {
        return cajaCompensacionFacade.findAll();
    }
    
    public CajaCompensacion getSelected() {
        if (cajaCompensacion == null) {
            cajaCompensacion = new CajaCompensacion();
            selectedItemCajaCompensacion =-1;
        }
        return cajaCompensacion;
    }
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(cajaCompensacionFacade.findAll(),false);
    }
    
     public CajaCompensacion getCajaCompensacion(java.lang.Integer id) {
        return cajaCompensacionFacade.find(id);
    }
     
     @FacesConverter(forClass = CajaCompensacion.class)
    public static class CajaCompensacionControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CajaCompensacionControlador controlador = (CajaCompensacionControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cajaCompensacionControlador");
            return controlador.getCajaCompensacion(getKey(value));
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
            if (object instanceof CajaCompensacion) {
                CajaCompensacion o = (CajaCompensacion) object;
                return getStringKey(o.getIdCajaCompensacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CajaCompensacion.class.getName());
            }
        }   
        

    }
    
    
    
    
}
