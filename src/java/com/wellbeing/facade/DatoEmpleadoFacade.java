/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.controladores.CorreoControlador;
import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author cristian
 */
@Stateless
public class DatoEmpleadoFacade extends AbstractFacade<DatoEmpleado> {
    
    CorreoControlador correoControlador;
    
    
    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatoEmpleadoFacade() {
        super(DatoEmpleado.class);
    }

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

    public List<DatoEmpleado> buscarInformacionPersonal(String usuario) {
        Query q = em.createQuery("SELECT d FROM DatoEmpleado d JOIN d.usuarioList u WHERE u.idUsuario=?1");
        q.setParameter(1, usuario);
        return q.getResultList();
    }

    public DatoEmpleado buscarDocumento(String u) {
        DatoEmpleado consulta = null;
        Query query = em.createQuery("SELECT d from DatoEmpleado d JOIN d.usuarioList u WHERE u.idUsuario=?1");
        query.setParameter(1, u);
        consulta = (DatoEmpleado) query.getSingleResult();
        return consulta;
    }

    public Date buscarFechaIngreso(String u) {
        Date consulta = null;
        try {

            Query query = em.createQuery("SELECT d.fechaIngreso from DatoEmpleado d JOIN d.usuarioList u WHERE u.idUsuario=?1");
            query.setParameter(1, u);
            consulta = (Date) query.getSingleResult();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El usuario no tiene registrada su fecha de ingreso"));
        }
        return consulta;
    }

    public DatoEmpleado buscarPorIdentificacion(String identificacion) {
        DatoEmpleado datoEmpleado=null;
        try {
            
       
        Query q = em.createQuery("SELECT  d FROM DatoEmpleado d WHERE d.identificacion =?1");
        q.setParameter(1, identificacion);
        List<DatoEmpleado>lista=q.getResultList();
        if (!lista.isEmpty()) {
            datoEmpleado=lista.get(0);
        } } catch (Exception e) {
            throw e;
        }
        return datoEmpleado;

    }
    
    
    
    
}
