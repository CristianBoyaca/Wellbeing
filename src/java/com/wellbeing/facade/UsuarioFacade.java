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
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 *
 * @author cristian
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario iniciarSesion(Usuario u) {
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "SELECT u FROM Usuario u WHERE u.idUsuario=?1 AND u.contrasena=?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, u.getIdUsuario());
            query.setParameter(2, u.getContrasena());
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    @Override
    public String validarContraseña(String usuario, String contrasenia) {
        String u = "";
        String consulta;
        try {
            consulta = "SELECT u FROM Usuario u WHERE u.idUsuario=?1 AND u.contrasena=?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, usuario);
            query.setParameter(2, contrasenia);
            List<String> lista = query.getResultList();
            if (!lista.isEmpty()) {
                u += lista;
            }
        } catch (Exception e) {
            throw e;
        }

        return u;
    }

    @Override
    public DatoEmpleado buscarDocumento(String u) {
        String consulta = null;
        DatoEmpleado datoEmpleado = null;
        try {
            TypedQuery<Usuario> usuario = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class);
            usuario.setParameter("idUsuario", u);

            List<Usuario> results = usuario.getResultList();
            for (Usuario us : results) {
                datoEmpleado = us.getDATOSEMPLEADOSidentificacion();
            }
        } catch (Exception e) {
            throw e;
        }
        return datoEmpleado;
    }

    @Override
    public Rol buscarRol(String usuario) {
        Rol rol = null;
        try {

            String consulta = "SELECT r from Usuario u JOIN u.rolList r WHERE u.idUsuario=?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, usuario);
            List<Rol> roles = query.getResultList();
            if (!roles.isEmpty()) {
                rol = roles.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rol;
    }

    @Override
    public String consultarCorreo(String usuario) {
        String correo = "";
        try {
            Query query = em.createQuery("SELECT d.emailPersonal from DatoEmpleado d JOIN d.usuarioList u WHERE u.idUsuario=?1");
            query.setParameter(1, usuario);
            correo=(String)query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return correo;
    }

    @Override
    public String buscarNombre(String usuario) {
        String consulta = " ";

        Query q = em.createQuery("SELECT d FROM DatoEmpleado d JOIN d.usuarioList u WHERE u.idUsuario=?1");
        q.setParameter(1, usuario);
        List<DatoEmpleado> results = q.getResultList();
        for (DatoEmpleado p : results) {
            consulta = p.getPrimerNombre() + " " + p.getPrimerApellido();
        }
        return consulta;
    }
    
    @Override
        public String retornaIdUsuario(DatoEmpleado empleado){
      
            String consulta="ok";  
                    
           
    /*****extrae la primera letra del nombre y concatena por el apellido*****/
           consulta=(substring(empleado.getPrimerNombre(),1,1)+empleado.getPrimerApellido());
         /*  consult = "SELECT u FROM Usuario u WHERE u.idUsuario=?1";
            Query query = em.createQuery(consult);
            query.setParameter(1,consulta);
            List<Usuario> lista = query.getResultList();*/
    /*****Validación para crear un usuario ****/
    
             /*   if (!result.isEmpty()) {
    
                  consulta=(substring(empleado.getPrimerNombre(),1,2)+empleado.getPrimerApellido());
                  Query qu = em.createQuery("SELECT d FROM DatoEmpleado d JOIN d.usuarioList u WHERE u.idUsuario=?1");
                  qu.setParameter(1, consulta);
                  List<DatoEmpleado> resul = qu.getResultList();
                  if (!resul.isEmpty()){
                      consulta=(substring(empleado.getSegundoNombre(),1,2)+empleado.getPrimerApellido());
                                       
                  }
                }  */
        
      
      return consulta;
      
        }
    
    
}
