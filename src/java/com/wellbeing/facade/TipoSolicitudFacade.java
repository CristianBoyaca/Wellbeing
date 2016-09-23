/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.TipoSolicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cristian
 */
@Stateless
public class TipoSolicitudFacade extends AbstractFacade<TipoSolicitud> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoSolicitudFacade() {
        super(TipoSolicitud.class);
    }

   
       public String consultarTipoSolicitud(Integer tipo){
         String consulta="";
          Query query=em.createQuery("SELECT t.tipoSolicitud from TipoSolicitud t  WHERE t.idTipoSolicitud=?1");
          query.setParameter(1, tipo);
         consulta+=query.getSingleResult();
          return consulta;
        
    
    }
    
}
