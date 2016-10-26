/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.Observacion;
import java.util.List;
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
public class ObservacionFacade extends AbstractFacade<Observacion> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservacionFacade() {
        
        
        super(Observacion.class);
    }

    public void actualizarObservacion(String datoEmpleado) {
        StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("actualizarObservacion");
        procedimientoAlmacenado.registerStoredProcedureParameter("iden", String.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("iden", datoEmpleado);
        procedimientoAlmacenado.execute();
    }
    
    public void actualizarIdentficacionObservacion(String iden) {
        try{
        StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("actualizarObservacionRespuesta");
        procedimientoAlmacenado.registerStoredProcedureParameter("iden", String.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("iden", iden);
        procedimientoAlmacenado.execute();}
        catch(Exception e){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "falla ejecucion de procedimiento DESDE FACADE"+iden, "No se pudo registrar correctamente sus o "));
        }
    }
    
    
    public List<Observacion> observacionPorSolicitud(int idSolicitud){
        
       Query q = em.createQuery("SELECT o from Observacion o JOIN o.idSolicitud s WHERE s.idSolicitud=?1");
        q.setParameter(1, idSolicitud);
        return q.getResultList();
      
    }
    
}
