/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.facade;

import com.wellbeing.entidades.Permiso;
import com.wellbeing.entidades.Rol;
import java.util.ArrayList;
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
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "WellbeingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    public List<Permiso> buscarPermisosRol(Integer idRol) {
        Query q = em.createQuery("SELECT p FROM Permiso p JOIN p.rolList r WHERE r.idRol=?1");
        q.setParameter(1, idRol);
        return q.getResultList();

    }
    
    public Rol buscarRol(String usuario){
      Rol rol=null; 
       Query q = em.createQuery("SELECT r FROM Rol r JOIN r.usuarioList u WHERE u.idUsuario=?1");
        q.setParameter(1, usuario);
        if (!q.getResultList().isEmpty()) {
           rol=(Rol) q.getSingleResult();
       }
        return rol ;
    
    }

}
