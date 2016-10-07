/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import com.wellbeing.facade.PermisoFacade;
import com.wellbeing.entidades.Permiso;
import com.wellbeing.entidades.Rol;
import com.wellbeing.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author cristian
 */
@Named(value = "permisoControlador")
@SessionScoped
public class PermisoControlador implements Serializable {

    @EJB
    private PermisoFacade permisoFacade;
    private List<Permiso> listaPermisos;
    private MenuModel model;

   @PostConstruct
    public void init(){
        this.listarMenus();
        model = new DefaultMenuModel();
        this.establecerPermisos();
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public List<Permiso> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public List<Permiso>listarPemisos(){
    return permisoFacade.findAll();
    }

    public void listarMenus() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            listaPermisos = permisoFacade.buscarPermisos(u.getIdUsuario());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void establecerPermisos() {

        for (Permiso p : listaPermisos) {
            if (p.getTipo().equals("S")) {
                DefaultSubMenu firstSubmenu = new DefaultSubMenu(p.getNombreDelPermiso());
                for (Permiso i : listaPermisos) {
                    Permiso submenu = i.getCodigoSubmenu();
                    if (submenu != null) {
                        if (submenu.getIdPermiso() == p.getIdPermiso()) {
                            DefaultMenuItem item = new DefaultMenuItem(i.getNombreDelPermiso());
                            item.setUrl(i.getUrl());
                            firstSubmenu.addElement(item);
                        }

                    }
                }
                model.addElement(firstSubmenu);
            } else if (p.getCodigoSubmenu() == null) {
                DefaultMenuItem item = new DefaultMenuItem(p.getNombreDelPermiso());
                item.setUrl(p.getUrl());
                model.addElement(item);
            }
        }
    }
   
     public Permiso getPermisos(java.lang.Integer id) {
        return permisoFacade.find(id);
    }

    @FacesConverter(forClass = Permiso.class)
    public static class PermisoControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PermisoControlador controlador = (PermisoControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "permisoControlador");
            return controlador.getPermisos(getKey(value));
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
            if (object instanceof Permiso) {
                Permiso o = (Permiso) object;
                return getStringKey(o.getIdPermiso());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Permiso.class.getName());
            }
        }

    }

}
