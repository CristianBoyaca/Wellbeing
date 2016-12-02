/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.TipoBonificacion;
import com.wellbeing.facade.TipoBonificacionFacade;
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
@Named(value = "tipoBonificacionControlador")
@SessionScoped
public class TipoBonificacionControlador implements Serializable {

    @EJB
    private TipoBonificacionFacade tipoBonificacionFacade;
    private TipoBonificacion tipoBonificacion;

    @PostConstruct
    public void init() {
        tipoBonificacion = new TipoBonificacion();
    }

    public TipoBonificacion getTipoBonificacion() {
        return tipoBonificacion;
    }

    public void setTipoBonificacion(TipoBonificacion tipoBonificacion) {
        this.tipoBonificacion = tipoBonificacion;
    }

    public List<TipoBonificacion> getListarTipos() {
        return tipoBonificacionFacade.findAll();
    }

    public TipoBonificacion getTipoBonificacion(java.lang.Integer id) {
        return tipoBonificacionFacade.find(id);
    }

    @FacesConverter(forClass = TipoBonificacion.class)
    public static class TipoBonificacionControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoBonificacionControlador controlador = (TipoBonificacionControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoBonificacionControlador");
            return controlador.getTipoBonificacion(getKey(value));
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
            if (object instanceof TipoBonificacion) {
                TipoBonificacion o = (TipoBonificacion) object;
                return getStringKey(o.getIdTipoBonificacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoBonificacion.class.getName());
            }
        }

    }

}
