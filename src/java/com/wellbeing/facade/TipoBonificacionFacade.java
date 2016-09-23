/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.TipoBonificacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cristian
 */
@Stateless
public class TipoBonificacionFacade extends AbstractFacade<TipoBonificacion> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoBonificacionFacade() {
        super(TipoBonificacion.class);
    }
    
}
