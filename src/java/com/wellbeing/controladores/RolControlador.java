/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.Permiso;
import com.wellbeing.entidades.Rol;
import com.wellbeing.facade.RolFacade;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
@Named(value = "rolControlador")
@SessionScoped
public class RolControlador implements Serializable {

    @EJB
    private RolFacade rolFacade;
    private Rol rol;
    private Permiso[] permisos;
    private List<Permiso> permisos1;
    private int selectedItemEps;
    private List<Rol> roles;

    @PostConstruct
    public void init() {
        rol = new Rol();

    }

    public Permiso[] getPermisos() {
        return permisos;
    }

    public void setPermisos(Permiso[] permisos) {
        this.permisos = permisos;
    }

    public List<Permiso> getPermisos1() {
        return permisos1;
    }

    public void setPermisos1(List<Permiso> permisos1) {
        this.permisos1 = permisos1;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getSelected() {
        if (rol == null) {
            rol = new Rol();
            selectedItemEps = -1;
        }
        return rol;
    }

    public List<Rol> listarRoles() {
        return rolFacade.findAll();
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(rolFacade.findAll(), false);
    }

    public Rol getRol(java.lang.Integer id) {
        return rolFacade.find(id);
    }

    @FacesConverter(forClass = Rol.class)
    public static class RolControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolControlador controlador = (RolControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolControlador");
            return controlador.getRol(getKey(value));
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
            if (object instanceof Rol) {
                Rol o = (Rol) object;
                return getStringKey(o.getIdRol());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rol.class.getName());
            }
        }
    }

    public String limpiarRolesUsu() {
        rol = new Rol();
        return "";
    }

    public List<Permiso> buscarPermisos() {

        permisos1 = rolFacade.buscarPermisosRol(rol.getIdRol());
        Permiso[] miarray = new Permiso[permisos1.size()];
        miarray = permisos1.toArray(miarray);
        permisos = miarray;
        return rolFacade.buscarPermisosRol(rol.getIdRol());

    }

    public void actualizarPermisos() {
        try {

            rol.setPermisoList(Arrays.asList(permisos));
            rolFacade.edit(rol);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Se ha actualizado correctamente los permiso"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo actualizar correctamente los permisos"));
        }
    }
}
