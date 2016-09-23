/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.Ciudad;
import com.wellbeing.facade.CiudadFacade;
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
@Named(value = "ciudadControlador")
@SessionScoped
public class CiudadControlador  implements Serializable{
    
   @EJB
   private CiudadFacade ciudadFacade;
   private Ciudad ciudad;
   private int selectListCiudad;
   
   @PostConstruct
   public void init(){
    this.ciudad= new Ciudad();
   }

    public CiudadFacade getCiudadFacade() {
        return ciudadFacade;
    }

    public void setCiudadFacade(CiudadFacade ciudadFacade) {
        this.ciudadFacade = ciudadFacade;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
   
    public List<Ciudad> listarCiudades(){
    
        return  ciudadFacade.findAll();
        
    }
   
    public Ciudad getSelected() {
        if (ciudad == null) {
            ciudad = new Ciudad();
            selectListCiudad =-1;
        }
        return ciudad;
    }
     public List<Ciudad> listarCiudad(){
    return ciudadFacade.findAll();
    }
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ciudadFacade.findAll(),false);
    }
    
     public Ciudad getCiudad(java.lang.Integer id) {
        return ciudadFacade.find(id);
    }
     
     @FacesConverter(forClass = Ciudad.class)
    public static class CiudadControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CiudadControlador controlador = (CiudadControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ciudadControlador");
            return controlador.getCiudad(getKey(value));
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
            if (object instanceof Ciudad) {
                Ciudad o = (Ciudad) object;
                return getStringKey(o.getIdCiudad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ciudad.class.getName());
            }
        }

    } 
}
