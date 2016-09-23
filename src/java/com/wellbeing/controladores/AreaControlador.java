/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.Area;
import com.wellbeing.facade.AreaFacade;
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
@Named(value = "areaControlador")
@SessionScoped
public class AreaControlador implements Serializable {

    @EJB
    private AreaFacade areaFacade;
    private Area area;
    private int selectedItemArea;

    @PostConstruct
    public void init() {
        area = new Area();
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> listarArea() {
        return areaFacade.findAll();
    }

    public Area getSelected() {
        if (area == null) {
            area = new Area();
            selectedItemArea = -1;
        }
        return area;
    }

    public List<Area> listarAreas() {
        return areaFacade.findAll();
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(areaFacade.findAll(), false);
    }

    public Area getArea(java.lang.Integer id) {
        return areaFacade.find(id);
    }

    @FacesConverter(forClass = Area.class)
    public static class AreaControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreaControlador controlador = (AreaControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areaControlador");
            return controlador.getArea(getKey(value));
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
            if (object instanceof Area) {
                Area o = (Area) object;
                return getStringKey(o.getIdArea());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Area.class.getName());
            }
        }

    }

}
