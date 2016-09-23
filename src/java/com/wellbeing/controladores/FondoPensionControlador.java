/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.FondoPension;
import com.wellbeing.facade.FondoPensionFacade;
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
@Named(value = "fondoPensionControlador")
@SessionScoped
public class FondoPensionControlador implements Serializable{
    
    @EJB
    private FondoPensionFacade fondoPensionFacade;
    private FondoPension fondoPension;
    private int selectedItemfondoPension;

    @PostConstruct
    public void init() {
        fondoPension = new FondoPension();
    }
    
    public FondoPension getFondoPension() {
        return fondoPension;
    }

    public void setFondoPension(FondoPension fondoPension) {
        this.fondoPension = fondoPension;
    }

    
    public List<FondoPension> listarFondoPension() {
        return fondoPensionFacade.findAll();
    }
    
    public FondoPension getSelected() {
        if (fondoPension == null) {
            fondoPension = new FondoPension();
            selectedItemfondoPension =-1;
        }
        return fondoPension;
    }
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(fondoPensionFacade.findAll(),false);
    }
    
     public FondoPension getFondoPension(java.lang.Integer id) {
        return fondoPensionFacade.find(id);
    }
     
     @FacesConverter(forClass = FondoPension.class)
    public static class FondoPensionControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FondoPensionControlador controlador = (FondoPensionControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fondoPensionControlador");
            return controlador.getFondoPension(getKey(value));
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
            if (object instanceof FondoPension) {
                FondoPension o = (FondoPension) object;
                return getStringKey(o.getIdFondo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FondoPension.class.getName());
            }
        }

       

        

    }
    
}
