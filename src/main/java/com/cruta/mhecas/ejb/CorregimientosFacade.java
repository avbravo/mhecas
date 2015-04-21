/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Corregimientos;
import com.cruta.mhecas.Municipios;
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
public class CorregimientosFacade extends AbstractFacade<Corregimientos> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorregimientosFacade() {
        super(Corregimientos.class);
    }
    
public List<Corregimientos> getCorregimientosList() {
        return em.createNamedQuery("Corregimientos.findAll").getResultList();
    }

    public List<Corregimientos> findByIdCorregimiento(Integer value) {
        Query query = em.createNamedQuery("Corregimientos.findByIdcorregimiento");
        return query.setParameter("idcorregimiento", value).getResultList();
    }
    
    public List<Corregimientos> findByIdProvincia(String value) {
        Query query = em.createNamedQuery("Corregimientos.findByIdprovincia");
        return query.setParameter("idprovincia", value).getResultList();
    }

    public List<Corregimientos> findByCorregimiento(String value) {
        Query query = em.createNamedQuery("Corregimientos.findByCorregimiento");
        return query.setParameter("corregimiento", value).getResultList();
    }

    public List<Corregimientos> findByNombreLike(String value) {
        Query query = em.createNamedQuery("Corregimientos.findByNombreLike");
        value = "%" + value.trim() + "%";
        return query.setParameter("corregimiento", value).getResultList();
    }

       public List<Corregimientos> findByIdMunicipio(Municipios value) {
        Query query = em.createNamedQuery("Corregimientos.findByIdmunicipio");
        return query.setParameter("idmunicipio", value).getResultList();
    }
       
      public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Corregimientos");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
}
