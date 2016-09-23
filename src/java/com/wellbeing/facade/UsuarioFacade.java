/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
        }} catch (Exception e) {
            throw e;
        }
        return datoEmpleado;
    }

}
