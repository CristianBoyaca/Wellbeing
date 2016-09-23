/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.Permiso;
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
public class PermisoFacade extends AbstractFacade<Permiso> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisoFacade() {
        super(Permiso.class);
    }

    public List<Permiso> buscarPermisos(String idUsuario) {
        Query q = em.createQuery("SELECT p FROM Permiso p JOIN p.rolList pr JOIN pr.usuarioList u WHERE u.idUsuario=?1");
        q.setParameter(1, idUsuario);
        return q.getResultList();
    }
    
    
    
}
