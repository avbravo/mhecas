/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Provincias;
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
public class ProvinciasFacade extends AbstractFacade<Provincias> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProvinciasFacade() {
        super(Provincias.class);
    }
    
public List<Provincias> getProvinciasList() {
        return em.createNamedQuery("Provincias.findAll").getResultList();
    }

    public List<Provincias> findByIdProvincia(String value) {
        Query query = em.createNamedQuery("Provincias.findByIdprovincia");
        return query.setParameter("idprovincia", value).getResultList();
    }

    public List<Provincias> findByProvincia(String value) {
        Query query = em.createNamedQuery("Provincias.findByProvincia");
        return query.setParameter("provincia", value).getResultList();
    }

    public List<Provincias> findByNombreLike(String value) {
        Query query = em.createNamedQuery("Provincias.findByNombreLike");
        value = "%" + value.trim() + "%";
        return query.setParameter("provincia", value).getResultList();
    }
    
    public List<Provincias> findByActivo(String value) {
        Query query = em.createNamedQuery("Provincias.findByActivo");
        value = "%" + value.trim() + "%";
        return query.setParameter("activo", value).getResultList();
    }
    
        public Long contadorActivo(String value) {
        Query query = em.createNamedQuery("Provincias.contadorActivo");
        return (Long) query.setParameter("activo", value).getSingleResult();
    }
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Provincias");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
}
