/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.Observacion;
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
    
}
