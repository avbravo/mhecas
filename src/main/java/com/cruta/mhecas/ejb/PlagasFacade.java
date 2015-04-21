/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Plagas;
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
public class PlagasFacade extends AbstractFacade<Plagas> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlagasFacade() {
        super(Plagas.class);
    }
    
public List<Plagas> getPlagasList() {
        return em.createNamedQuery("Plagas.findAll").getResultList();
    }

   
 public List<Plagas> findByNombreplaga(String nombreplaga) {
        Query query = em.createNamedQuery("Plagas.findByNombreplaga");
        return query.setParameter("nombreplaga", nombreplaga).getResultList();
    }
 
 
public List<Plagas> findByFoto(String foto) {
        Query query = em.createNamedQuery("Plagas.findByFoto");
        return query.setParameter("foto", foto).getResultList();
    }
   
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Plagas");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
        
}
