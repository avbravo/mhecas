/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Alertas;
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
public class AlertasFacade extends AbstractFacade<Alertas> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlertasFacade() {
        super(Alertas.class);
    }
    
public Alertas findById(String id) {
        return em.find(Alertas.class, id);
    }

    public List<Alertas> getAlertasList() {
        return em.createNamedQuery("Alertas.findAll").getResultList();
    }

   
 public List<Alertas> getAlertasOrdenados() {
        Query query = em.createQuery("SELECT a FROM Alertas a ORDER BY a.idalerta DESC");
        return query.getResultList();
    }
 public List<Alertas> findByIdalerta(Integer idalerta) {
        Query query = em.createNamedQuery("Alertas.findByIdalerta");
        return query.setParameter("idalerta", idalerta).getResultList();
    }
 
 public List<Alertas> findByFoto(String foto) {
        Query query = em.createQuery("SELECT a FROM Alertas a WHERE a.foto = :foto");
        return query.setParameter("foto", foto).getResultList();
    }
   
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Alertas");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
       public Integer getMaximo() {
        try {

            Query q = em.createQuery("SELECT MAX(a.idalerta) FROM Alertas a");

            Number result = (Number) q.getSingleResult();
            return result.intValue();
        } catch (Exception e) {

        }
        return 0;
    }
}

