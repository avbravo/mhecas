/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Cultivos;
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
public class CultivosFacade extends AbstractFacade<Cultivos> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CultivosFacade() {
        super(Cultivos.class);
    }
    
  public Cultivos findById(String id) {
        return em.find(Cultivos.class, id);
    }

    public List<Cultivos> getCultivosList() {
        return em.createNamedQuery("Cultivos.findAll").getResultList();
    }

   
 public List<Cultivos> findByNombrecultivo(String nombrecultivo) {
        Query query = em.createNamedQuery("Cultivos.findByNombrecultivo");
        return query.setParameter("nombrecultivo", nombrecultivo).getResultList();
    }
 
 
public List<Cultivos> findByFoto(String foto) {
        Query query = em.createNamedQuery("Cultivos.findByFoto");
        return query.setParameter("foto", foto).getResultList();
    }
   
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Cultivos");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
    
  
    
}
