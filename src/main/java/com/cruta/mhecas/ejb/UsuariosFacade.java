/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.ejb;

import com.cruta.mhecas.Grupousuarios;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> {
    @PersistenceContext(unitName = "com.cruta_mhecas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
public Usuarios findById(String id) {
        return em.find(Usuarios.class, id);
    }

    public List<Usuarios> getUsuariosList() {
        return em.createNamedQuery("Usuarios.findAll").getResultList();
    }

   
 public List<Usuarios> findByEmail(String value) {
        Query query = em.createNamedQuery("Usuarios.findByEmail");
        return query.setParameter("username", value).getResultList();
    }
 
 public List<Usuarios> findByPassword(String value) {
        Query query = em.createNamedQuery("Usuarios.findByPassword");
        return query.setParameter("password", value).getResultList();
    }
  

    public List<Usuarios> findByNombre(String value) {
        Query query = em.createNamedQuery("Usuarios.findByNombre");
        return query.setParameter("nombre", value).getResultList();
    }

    public List<Usuarios> findByNombreLike(String value) {
        Query query = em.createNamedQuery("Usuarios.findByNombreLike");
        value = "%" + value.trim() + "%";
        return query.setParameter("nombre", value).getResultList();
    }

    public List<Usuarios> findByIdgrupousuario(Grupousuarios value) {
        Query query = em.createNamedQuery("Usuarios.findByIdgrupousuario");
        return query.setParameter("idgrupousuario", value).getResultList();
    }

   
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Usuarios");
        int deleteRecords;
        deleteRecords = query.executeUpdate();
    }
    
      public List<Usuarios> findByFoto(String foto){
         Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.foto= :foto");
        return query.setParameter("foto", foto).getResultList();
    }
}


