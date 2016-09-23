/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.TipoCertificado;
import com.wellbeing.facade.TipoCertificadoFacade;
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
import javax.faces.model.SelectItem;

/**
 *
 * @author cristian
 */
@Named
@SessionScoped
public class TipoCertificadoControlador implements Serializable {
    @EJB
    private TipoCertificadoFacade  tipoCertificadoFacade;
    private TipoCertificado tipoCertificado;
  

    @PostConstruct
    public void init() {
        tipoCertificado=new TipoCertificado();

    }

    public TipoCertificado getTipoCertificado() {
        return tipoCertificado;
    }

    public void setTipoCertificado(TipoCertificado tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }

    
    public List<TipoCertificado>getListarTipos() {
       return tipoCertificadoFacade.findAll();
    }
     

    public TipoCertificado getTipoCertificado(java.lang.Integer id) {
        return tipoCertificadoFacade.find(id);
    }
    
    @FacesConverter(forClass = TipoCertificado.class)
    public static class TipoCertificadoControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoCertificadoControlador controlador = (TipoCertificadoControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoCertificadoControlador");
            return controlador.getTipoCertificado(getKey(value));
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
            if (object instanceof TipoCertificado) {
                TipoCertificado o = (TipoCertificado) object;
                return getStringKey(o.getIdTipoCertificados());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoCertificado.class.getName());
            }
        }

    }


    
}
