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
@Table(name = "alertasfotos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alertasfotos.findAll", query = "SELECT a FROM Alertasfotos a"),
    @NamedQuery(name = "Alertasfotos.findByIdalertasfotos", query = "SELECT a FROM Alertasfotos a WHERE a.idalertasfotos = :idalertasfotos"),
    @NamedQuery(name = "Alertasfotos.findByFoto", query = "SELECT a FROM Alertasfotos a WHERE a.foto = :foto")})
public class Alertasfotos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idalertasfotos")
    private Integer idalertasfotos;
    @Size(max = 200)
    @Column(name = "foto")
    private String foto;
    @JoinColumn(name = "idalerta", referencedColumnName = "idalerta")
    @ManyToOne
    private Alertas idalerta;

    public Alertasfotos() {
    }

    public Alertasfotos(Integer idalertasfotos) {
        this.idalertasfotos = idalertasfotos;
    }

    public Integer getIdalertasfotos() {
        return idalertasfotos;
    }

    public void setIdalertasfotos(Integer idalertasfotos) {
        this.idalertasfotos = idalertasfotos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Alertas getIdalerta() {
        return idalerta;
    }

    public void setIdalerta(Alertas idalerta) {
        this.idalerta = idalerta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalertasfotos != null ? idalertasfotos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alertasfotos)) {
            return false;
        }
        Alertasfotos other = (Alertasfotos) object;
        if ((this.idalertasfotos == null && other.idalertasfotos != null) || (this.idalertasfotos != null && !this.idalertasfotos.equals(other.idalertasfotos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Alertasfotos[ idalertasfotos=" + idalertasfotos + " ]";
    }
    
}
