/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "grupousuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupousuarios.findAll", query = "SELECT g FROM Grupousuarios g"),
    @NamedQuery(name = "Grupousuarios.findByIdgrupousuario", query = "SELECT g FROM Grupousuarios g WHERE g.idgrupousuario = :idgrupousuario"),
    @NamedQuery(name = "Grupousuarios.findByGrupousuario", query = "SELECT g FROM Grupousuarios g WHERE g.grupousuario = :grupousuario"),
    @NamedQuery(name = "Grupousuarios.findByActivo", query = "SELECT g FROM Grupousuarios g WHERE g.activo = :activo")})
public class Grupousuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "idgrupousuario")
    private String idgrupousuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "grupousuario")
    private String grupousuario;
    @Size(max = 2)
    @Column(name = "activo")
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupousuario")
    private Collection<Usuarios> usuariosCollection;

    public Grupousuarios() {
    }

    public Grupousuarios(String idgrupousuario) {
        this.idgrupousuario = idgrupousuario;
    }

    public Grupousuarios(String idgrupousuario, String grupousuario) {
        this.idgrupousuario = idgrupousuario;
        this.grupousuario = grupousuario;
    }

    public String getIdgrupousuario() {
        return idgrupousuario;
    }

    public void setIdgrupousuario(String idgrupousuario) {
        this.idgrupousuario = idgrupousuario;
    }

    public String getGrupousuario() {
        return grupousuario;
    }

    public void setGrupousuario(String grupousuario) {
        this.grupousuario = grupousuario;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupousuario != null ? idgrupousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupousuarios)) {
            return false;
        }
        Grupousuarios other = (Grupousuarios) object;
        if ((this.idgrupousuario == null && other.idgrupousuario != null) || (this.idgrupousuario != null && !this.idgrupousuario.equals(other.idgrupousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Grupousuarios[ idgrupousuario=" + idgrupousuario + " ]";
    }
    
}
