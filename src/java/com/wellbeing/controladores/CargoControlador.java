/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.Cargo;
import com.wellbeing.facade.CargoFacade;
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
@Named(value = "cargoControlador")
@SessionScoped
public class CargoControlador implements Serializable {
    
    @EJB
    private CargoFacade cargoFacade;
    private Cargo cargo;
    private int selectedItemCargo;

    @PostConstruct
    public void init() {
        cargo = new Cargo();
    }
    
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    
    public List<Cargo> listarCargo() {
        return cargoFacade.findAll();
    }
    
    public Cargo getSelected() {
        if (cargo == null) {
            cargo = new Cargo();
            selectedItemCargo =-1;
        }
        return cargo;
    }
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(cargoFacade.findAll(),false);
    }
    
     public Cargo getCargo(java.lang.Integer id) {
        return cargoFacade.find(id);
    }
     
     @FacesConverter(forClass = Cargo.class)
    public static class CargoControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CargoControlador controlador = (CargoControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cargoControlador");
            return controlador.getCargo(getKey(value));
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
            if (object instanceof Cargo) {
                Cargo o = (Cargo) object;
                return getStringKey(o.getIdCargo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cargo.class.getName());
            }
        }

     }

       
    
}
