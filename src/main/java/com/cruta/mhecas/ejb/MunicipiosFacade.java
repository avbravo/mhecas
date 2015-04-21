/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Municipios;
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
public class MunicipiosFacade extends AbstractFacade<Municipios> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipiosFacade() {
        super(Municipios.class);
    }
    
public List<Municipios> getMunicipiosList() {
        return em.createNamedQuery("Municipios.findAll").getResultList();
    }

    public List<Municipios> findByIdMunicipio(String value) {
        Query query = em.createNamedQuery("Municipios.findByIdmunicipio");
        return query.setParameter("idmunicipio", value).getResultList();
    }

    public List<Municipios> findByMunicipio(String value) {
        Query query = em.createNamedQuery("Municipios.findByMunicipio");
        return query.setParameter("municipio", value).getResultList();
    }

    public List<Municipios> findByNombreLike(String value) {
        Query query = em.createNamedQuery("Municipios.findByNombreLike");
        value = "%" + value.trim() + "%";
        return query.setParameter("municipio", value).getResultList();
    }

    public List<Municipios> findByIdProvincia(Provincias value) {
        Query query = em.createNamedQuery("Municipios.findByIdprovincia");
        return query.setParameter("idprovincia", value).getResultList();
    }

    public List<Municipios> findByActivo(String value) {
        Query query = em.createNamedQuery("Municipios.findByActivo");
        value = "%" + value.trim() + "%";
        return query.setParameter("activo", value).getResultList();
    }

    public Long contadorActivo(String value) {
        Query query = em.createNamedQuery("Municipios.contadorActivo");
        return (Long) query.setParameter("activo", value).getSingleResult();
    }
       public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Municipios");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
}
