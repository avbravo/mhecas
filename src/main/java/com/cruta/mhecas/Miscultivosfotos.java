/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "miscultivosfotos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miscultivosfotos.findAll", query = "SELECT m FROM Miscultivosfotos m"),
    @NamedQuery(name = "Miscultivosfotos.findByIdmiscultivosfotos", query = "SELECT m FROM Miscultivosfotos m WHERE m.idmiscultivosfotos = :idmiscultivosfotos"),
    @NamedQuery(name = "Miscultivosfotos.findByFoto", query = "SELECT m FROM Miscultivosfotos m WHERE m.foto = :foto")})
public class Miscultivosfotos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmiscultivosfotos")
    private Integer idmiscultivosfotos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "foto")
    private String foto;
    @JoinColumn(name = "idmiscultivos", referencedColumnName = "idmiscultivos")
    @ManyToOne(optional = false)
    private Miscultivos idmiscultivos;

    public Miscultivosfotos() {
    }

    public Miscultivosfotos(Integer idmiscultivosfotos) {
        this.idmiscultivosfotos = idmiscultivosfotos;
    }

    public Miscultivosfotos(Integer idmiscultivosfotos, String foto) {
        this.idmiscultivosfotos = idmiscultivosfotos;
        this.foto = foto;
    }

    public Integer getIdmiscultivosfotos() {
        return idmiscultivosfotos;
    }

    public void setIdmiscultivosfotos(Integer idmiscultivosfotos) {
        this.idmiscultivosfotos = idmiscultivosfotos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Miscultivos getIdmiscultivos() {
        return idmiscultivos;
    }

    public void setIdmiscultivos(Miscultivos idmiscultivos) {
        this.idmiscultivos = idmiscultivos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmiscultivosfotos != null ? idmiscultivosfotos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miscultivosfotos)) {
            return false;
        }
        Miscultivosfotos other = (Miscultivosfotos) object;
        if ((this.idmiscultivosfotos == null && other.idmiscultivosfotos != null) || (this.idmiscultivosfotos != null && !this.idmiscultivosfotos.equals(other.idmiscultivosfotos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Miscultivosfotos[ idmiscultivosfotos=" + idmiscultivosfotos + " ]";
    }
    
}
