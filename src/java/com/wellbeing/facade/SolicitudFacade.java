/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.Solicitud;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cristian
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }

    public List<Solicitud> buscarPorUsuario(String usuario) {
        Query q = em.createQuery("SELECT s from Solicitud s WHERE s.usuarioAsignado=?1");
        q.setParameter(1, usuario);
        return q.getResultList();
    }
    
    public Solicitud buscarPorSolicitud(int idSolicitud){
    
        Query q = em.createQuery("SELECT d FROM Solicitud d WHERE d.idSolicitud =?1");
        q.setParameter(1, idSolicitud);
        return (Solicitud) q.getResultList().get(0);
    
    }

    public void actualizarDecision(int idSolicitud,String decision,String estadoSol,String usuarioAsignado){
    
        
       Query q = em.createQuery("UPDATE Solicitud  s SET  s.decision=?1,s.estado=?2,s.usuarioAsignado=?3 WHERE s.idSolicitud=?4");
        q.setParameter(1, decision);
        q.setParameter(2, estadoSol);
        q.setParameter(3, usuarioAsignado);
        q.setParameter(4, idSolicitud);
        q.executeUpdate();
    }

    public List<Solicitud> buscarSolicitud(String usuario) {
        Query q = em.createQuery("SELECT s from Solicitud s JOIN s.usuarioRadicador u WHERE u.idUsuario=?1");
        q.setParameter(1, usuario);
        return q.getResultList();
    }

    public List<Solicitud> buscarSolicitudesRespondidas(String usuario) {
        Query q = em.createQuery("SELECT s FROM Solicitud s WHERE s.usuarioAsignado=?1 AND s.decision!=null");
        q.setParameter(1, usuario);
        return q.getResultList();
    }
}
