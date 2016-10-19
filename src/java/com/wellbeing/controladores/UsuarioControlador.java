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
import com.wellbeing.util.Correo;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;

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
    private List<Rol> roles;
    private String redireccion;
    private String contraseniaActual;
    private String confirmacionContrasenia;
    private Integer estado;
    private int selectedItemUsuario;
    private CorreoControlador correoControlador;
    private String contenido;
    private String contrasenia;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        rolUsuario = new Rol();
        estado = 0;
        correoControlador = new CorreoControlador();
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

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public CorreoControlador getCorreoControlador() {
        return correoControlador;
    }

    public void setCorreoControlador(CorreoControlador correoControlador) {
        this.correoControlador = correoControlador;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String iniciarSesion() {
        Usuario u = null;
        redireccion = null;
        try {
            usuario.setContrasena(DigestUtils.md5Hex(usuario.getContrasena()));
            u = usuarioFacade.iniciarSesion(usuario);
            if (u != null && u.getEstado() != 0) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                if (u.getEstado() != 2) {
                    redireccion = "protegido/inicio?faces-redirect=true";
                } else {
                    redireccion = "protegido/cambioContrasenia?faces-redirect=true";
                }

            } else {

                usuario.setContrasena("");
                if (u == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Credenciales incorrectas"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Usuario inactivo"));
                }
            }

        } catch (Exception e) {
            usuario.setIdUsuario("");
            usuario.setContrasena("");
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
                redireccion = redireccion.replace("?faces-redirect=true", ".xhtml");
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Wellbeing/faces/" + redireccion);
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede puede cargar la página"));
        }

    }

    public void cargarUsuario() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.usuario = usuario;

    }

    public void cambiarContrasenia() {
        try {
            cargarUsuario();
            contraseniaActual = DigestUtils.md5Hex(contraseniaActual);
            if (!usuarioFacade.validarContraseña(usuario.getIdUsuario(), contraseniaActual).equals("")) {
                usuario.setContrasena(DigestUtils.md5Hex(confirmacionContrasenia));
                usuarioFacade.edit(usuario);
                estado = 1;
            } else {
                estado = 2;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "En el momento no se pudo procesar su solicitud"));
        }

    }

    public void cambiarContraseniaTemporal() {
        try {
            cargarUsuario();
            contraseniaActual = DigestUtils.md5Hex(contraseniaActual);
            if (!contraseniaActual.equalsIgnoreCase(usuario.getContrasena())) {
                usuario.setContrasena(contraseniaActual);
                usuario.setEstado(1);
                usuarioFacade.edit(usuario);
                estado = 0;
            } else {
                estado = 2;
                contraseniaActual = "";
                confirmacionContrasenia = "";
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
        return JsfUtil.getSelectItems(usuarioFacade.findAll(), false);
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

    public void buscarRol(Usuario usuario) {

        rolUsuario = usuarioFacade.buscarRol(usuario.getIdUsuario());
    }

    public String actualizarRolUsuario(Usuario u) {
        try {
            u.getRolList().set(0, rolUsuario);
            usuarioFacade.edit(u);
            estado = 1;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Se ha actualizado correctamente el rol"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo actualizar correctamente el rol"));
        }
        return "";

    }

    public void recuperarContrasenia() {
        try {
            if (!usuarioFacade.consultarCorreo(usuario.getIdUsuario()).equalsIgnoreCase("")) {
                usuario.setDATOSEMPLEADOSidentificacion(usuarioFacade.buscarDocumento(usuario.getIdUsuario()));
                usuario.setEstado(2);
                contrasenia = getCadenaAlfanumAleatoria(8);
                usuario.setContrasena(DigestUtils.md5Hex(contrasenia));
                roles = new ArrayList<>();
                roles.add(usuarioFacade.buscarRol(usuario.getIdUsuario()));
                usuario.setRolList(roles);
                usuarioFacade.edit(usuario);
                contenido = correoControlador.getCorreo().agregarHtml("/com/wellbeing/util/formatos/recordarContrasenia.xhtml");
                contenido = contenido.replace("{nombre}", usuarioFacade.buscarNombre(usuario.getIdUsuario()));
                contenido = contenido.replace("{usuario.contrasena}", contrasenia);
                correoControlador.recuperarContrasenia(usuarioFacade.consultarCorreo(usuario.getIdUsuario()), contenido);
                usuario = new Usuario();

            } else {
                usuario.setIdUsuario("");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "usuario no se encuentra registrado"));
            }

        } catch (Exception e) {
            usuario.setIdUsuario("");
            usuario.setContrasena("");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "En el momento no se pudo procesar su solicitud"));
        }

    }

    public String getCadenaAlfanumAleatoria(int longitud) {
        String cadenaAleatoria = "";

        Random r = new Random();
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }

}
