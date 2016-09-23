/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.Rol;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import com.wellbeing.facade.UsuarioFacadeLocal;
import com.wellbeing.entidades.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author cristian
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private Usuario usuario;
    private Rol rolUsuario;
    private String redireccion;
    private String contraseniaActual;
    private String confirmacionContrasenia;
    private Integer estado;
    private int selectedItemUsuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        rolUsuario = new Rol();
        estado = 0;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Rol rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    
    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }

    public String getContraseniaActual() {
        return contraseniaActual;
    }

    public void setContraseniaActual(String contraseniaActual) {
        this.contraseniaActual = contraseniaActual;
    }

    public String getConfirmacionContrasenia() {
        return confirmacionContrasenia;
    }

    public void setConfirmacionContrasenia(String confirmacionContrasenia) {
        this.confirmacionContrasenia = confirmacionContrasenia;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String iniciarSesion() {
        Usuario u = null;
        redireccion = null;
        try {
            u = usuarioFacade.iniciarSesion(usuario);
            if (u != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                redireccion = "protegido/inicio?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Credenciales incorrectas"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede iniciar sesión"));
        }
        return redireccion;
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public void redireccionarPagina() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (u != null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Wellbeing/faces/protegido/inicio.xhtml");
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede puede cargar la página"));
        }

    }

    public void cargarUsuario() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.usuario = usuario;

    }

    public void cambiarContraseña() {
        try {
            cargarUsuario();
            if (!usuarioFacade.validarContraseña(usuario.getIdUsuario(), contraseniaActual).equals("")) {
               usuario.setContrasena(confirmacionContrasenia);
                usuarioFacade.edit(usuario);
                estado = 1;
            } else {
                estado = 2;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "En el momento no se pudo procesar su solicitud"));
        }

    }

    public void cambiarEstado() {
        estado = 0;
    }

    public Usuario getSelected() {
        if (usuario == null) {
            usuario = new Usuario();
            selectedItemUsuario = -1;
        }
        return usuario;
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(usuarioFacade.findAll(),false);
    }

    public List<Usuario> listarUsuario() {
        return usuarioFacade.findAll();
    }

    public Usuario getUsuario(java.lang.Integer id) {
        return usuarioFacade.find(id);
    }

   @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioControlador controlador = (UsuarioControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioControlador");
            return controlador.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return o.getIdUsuario();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }

     }
     
    public String actualizarRolUsuario(Usuario usuarior){
        usuarior.getRolList().set(0, rolUsuario);
        try {    
        usuarioFacade.edit(usuarior);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización","Se ha actualizado correctamente el registro"));
        }catch(Exception e){
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error","No se pudo actualizar correctamente el registro"));
    }
        return "";
}
}
