/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.Eps;
import com.wellbeing.entidades.EstadoCivil;
import com.wellbeing.facade.EpsFacade;
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
@Named(value = "epsControlador")
@SessionScoped
public class EpsControlador implements Serializable{
   


/**
 *
 * @author Holman
 */



    @EJB
    private EpsFacade epsFacade;
    private Eps eps;
    private int selectedItemEps;

    @PostConstruct
    public void init() {
        eps = new Eps();
    }
    
    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    
    public List<Eps> listarEps() {
        return epsFacade.findAll();
    }
    
    public Eps getSelected() {
        if (eps == null) {
            eps = new Eps();
            selectedItemEps =-1;
        }
        return eps;
    }
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(epsFacade.findAll(),false);
    }
    
     public Eps getEps(java.lang.Integer id) {
        return epsFacade.find(id);
    }
     
     @FacesConverter(forClass = Eps.class)
    public static class EpsControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EpsControlador controlador = (EpsControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "epsControlador");
            return controlador.getEps(getKey(value));
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
            if (object instanceof Eps) {
                Eps o = (Eps) object;
                return getStringKey(o.getIdEps());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Eps.class.getName());
            }
        }

      

    }

}
    
    
    

