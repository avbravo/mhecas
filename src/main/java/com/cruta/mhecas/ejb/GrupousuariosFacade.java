/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Grupousuarios;
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
public class GrupousuariosFacade extends AbstractFacade<Grupousuarios> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupousuariosFacade() {
        super(Grupousuarios.class);
    }
    
 public Grupousuarios findById(String id) {
        return em.find(Grupousuarios.class, id);
    }

    public List<Grupousuarios> getGrupousuariosList() {
        return em.createNamedQuery("Grupousuarios.findAll").getResultList();
    }

    public List<Grupousuarios> findByActivo(String value) {
        Query query = em.createNamedQuery("Grupousuarios.findByActivo");
        return query.setParameter("activo", value).getResultList();
    }

    public List<Grupousuarios> findByIdGrupousuarios(String value) {
        Query query = em.createNamedQuery("Grupousuarios.findByIdgrupousuario");
        return query.setParameter("idgrupousuario", value).getResultList();
    }
    
    public List<Grupousuarios> findByGrupousuarios(String value) {
        Query query = em.createNamedQuery("Grupousuarios.findByGrupousuario");
        return query.setParameter("grupousuario", value).getResultList();
    }

  
public Long contadorActivo(String value) {
        Query query = em.createNamedQuery("Grupousuarios.contadorActivo");
        return (Long) query.setParameter("activo", value).getSingleResult();
    }
    
   public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Grupousuarios");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
}

