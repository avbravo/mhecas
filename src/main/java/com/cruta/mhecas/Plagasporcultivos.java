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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "plagasporcultivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plagasporcultivos.findAll", query = "SELECT p FROM Plagasporcultivos p"),
    @NamedQuery(name = "Plagasporcultivos.findByIdplagasporcultivos", query = "SELECT p FROM Plagasporcultivos p WHERE p.idplagasporcultivos = :idplagasporcultivos")})
public class Plagasporcultivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idplagasporcultivos")
    private Integer idplagasporcultivos;
    @JoinColumn(name = "nombrecultivo", referencedColumnName = "nombrecultivo")
    @ManyToOne
    private Cultivos nombrecultivo;
    @JoinColumn(name = "nombreplaga", referencedColumnName = "nombreplaga")
    @ManyToOne
    private Plagas nombreplaga;

    public Plagasporcultivos() {
    }

    public Plagasporcultivos(Integer idplagasporcultivos) {
        this.idplagasporcultivos = idplagasporcultivos;
    }

    public Integer getIdplagasporcultivos() {
        return idplagasporcultivos;
    }

    public void setIdplagasporcultivos(Integer idplagasporcultivos) {
        this.idplagasporcultivos = idplagasporcultivos;
    }

    public Cultivos getNombrecultivo() {
        return nombrecultivo;
    }

    public void setNombrecultivo(Cultivos nombrecultivo) {
        this.nombrecultivo = nombrecultivo;
    }

    public Plagas getNombreplaga() {
        return nombreplaga;
    }

    public void setNombreplaga(Plagas nombreplaga) {
        this.nombreplaga = nombreplaga;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplagasporcultivos != null ? idplagasporcultivos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plagasporcultivos)) {
            return false;
        }
        Plagasporcultivos other = (Plagasporcultivos) object;
        if ((this.idplagasporcultivos == null && other.idplagasporcultivos != null) || (this.idplagasporcultivos != null && !this.idplagasporcultivos.equals(other.idplagasporcultivos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Plagasporcultivos[ idplagasporcultivos=" + idplagasporcultivos + " ]";
    }
    
}
