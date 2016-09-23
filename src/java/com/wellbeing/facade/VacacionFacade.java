/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.Vacacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
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
    
}
