/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.Vacacion;
import java.util.Date;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author cristian
 */
@Stateless
public class VacacionFacade extends AbstractFacade<Vacacion> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VacacionFacade() {
        super(Vacacion.class);
    }

    public void registrarSolicitudVacaciones(String documento, Integer tipoSolicitud) {
       StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("insertarSolicitud");
        procedimientoAlmacenado.registerStoredProcedureParameter("iden", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("tipo", Integer.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("iden", documento);
        procedimientoAlmacenado.setParameter("tipo", tipoSolicitud);
        procedimientoAlmacenado.execute();
    }
    
    
    public Vacacion buscarPorSolicitud(int idSolicitud){
    
        
        Query q = em.createQuery("SELECT v from Vacacion v JOIN v.idSolicitud s WHERE s.idSolicitud=?1");
        q.setParameter(1, idSolicitud);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", "Se ha actualizado correctamente el registro"));
        return  (Vacacion) q.getResultList().get(0);
        
    }
    
   public Vacacion buscarDias(String identificacion) {
       Vacacion vacacion=null; 
       Query q = em.createQuery("SELECT v FROM Vacacion v JOIN v.identificacion d  WHERE d.identificacion=?1 AND v.diasDisfrutados!=null ORDER BY v.idVacaciones DESC ");
        q.setParameter(1, identificacion);
        q.setMaxResults(1);
        if (!q.getResultList().isEmpty()) {
           vacacion=(Vacacion) q.getSingleResult();
       }
        return vacacion ;
   
   }
   
   public Vacacion buscarDias(String identificacion,Date fecha) {
       Vacacion vacacion=null; 
       Query q = em.createQuery("SELECT v FROM Vacacion v JOIN v.identificacion d  WHERE d.identificacion=?1 AND v.diasDisfrutados!=null AND v.fechaInicial>=?2 ORDER BY v.idVacaciones DESC ");
        q.setParameter(1, identificacion);
        q.setParameter(2,fecha);
        q.setMaxResults(1);
        if (!q.getResultList().isEmpty()) {
           vacacion=(Vacacion) q.getSingleResult();
       }
        return vacacion ;
   
   }
}
