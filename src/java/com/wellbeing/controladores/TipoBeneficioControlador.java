/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.TipoBeneficio;
import com.wellbeing.facade.TipoBeneficioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author cristian
 */
@Named(value = "tipoBeneficioControlador")
@SessionScoped
public class TipoBeneficioControlador implements Serializable{

    @EJB
    private TipoBeneficioFacade tipoBeneficioFacade;
    private TipoBeneficio tipoBeneficio;
 

    @PostConstruct
    public void init() {
     tipoBeneficio=new TipoBeneficio();
    }

    public TipoBeneficio getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }
    
    
    
  public List<TipoBeneficio>getListarTipos() {
       return tipoBeneficioFacade.findAll();
    }
    
   

    public TipoBeneficio getTipoBeneficio(java.lang.Integer id) {
        return tipoBeneficioFacade.find(id);
    }
    
      
      @FacesConverter(forClass = TipoBeneficio.class)
    public static class TipoBeneficioControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoBeneficioControlador controlador = (TipoBeneficioControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoBeneficioControlador");
            return controlador.getTipoBeneficio(getKey(value));
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
            if (object instanceof TipoBeneficio) {
                TipoBeneficio o = (TipoBeneficio) object;
                return getStringKey(o.getIdTipoBeneficio());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoBeneficio.class.getName());
            }
        }

    }

}
