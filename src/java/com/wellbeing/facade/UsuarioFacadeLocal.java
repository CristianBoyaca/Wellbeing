/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Rol;
import com.wellbeing.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cristian
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario iniciarSesion(Usuario u);

    public String validarContraseña(String usuario, String contrasenia);
    public DatoEmpleado buscarDocumento(String usuario);
    public  Rol buscarRol(String usuario);
    public String consultarCorreo(String usuario);
    public String buscarNombre(String usuario);
    
}
