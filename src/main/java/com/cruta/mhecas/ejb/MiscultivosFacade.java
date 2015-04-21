/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Cultivos;
import com.cruta.mhecas.Miscultivos;
import com.cruta.mhecas.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author avbravo
 */
@Stateless
public class MiscultivosFacade extends AbstractFacade<Miscultivos> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MiscultivosFacade() {
        super(Miscultivos.class);
    }
    
 public List<Miscultivos> getMiscultivosList() {
        return em.createNamedQuery("Miscultivos.findAll").getResultList();
    }

   
    
 public List<Miscultivos> findByIdmiscultivos(Integer idmiscultivos) {
        Query query = em.createNamedQuery("Miscultivos.findByIdmiscultivos");
        return query.setParameter("idmiscultivos", idmiscultivos).getResultList();
    }
 
 

   
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Miscultivos");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
        
     public List<Usuarios> findByCultivo(Cultivos nombrecultivo){
         Query query = em.createQuery("SELECT m FROM Miscultivos m WHERE m.nombrecultivo= :nombrecultivo ");
        return query.setParameter("nombrecultivo", nombrecultivo).getResultList();
    }
     
     
      public Integer getMaximo() {
        try {

            Query q = em.createQuery("SELECT MAX(m.idmiscultivos) FROM Miscultivos m");

            Number result = (Number) q.getSingleResult();
            return result.intValue();
        } catch (Exception e) {

        }
        return 0;
    }
}

