/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "plagas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plagas.findAll", query = "SELECT p FROM Plagas p"),
    @NamedQuery(name = "Plagas.findByNombreplaga", query = "SELECT p FROM Plagas p WHERE p.nombreplaga = :nombreplaga"),
    @NamedQuery(name = "Plagas.findByNombrecientifico", query = "SELECT p FROM Plagas p WHERE p.nombrecientifico = :nombrecientifico"),
    @NamedQuery(name = "Plagas.findByFoto", query = "SELECT p FROM Plagas p WHERE p.foto = :foto")})
public class Plagas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombreplaga")
    private String nombreplaga;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrecientifico")
    private String nombrecientifico;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "aquienafecta")
    private String aquienafecta;
    @Size(max = 200)
    @Column(name = "foto")
    private String foto;
    @OneToMany(mappedBy = "nombreplaga")
    private Collection<Plagasporcultivos> plagasporcultivosCollection;

    public Plagas() {
    }

    public Plagas(String nombreplaga) {
        this.nombreplaga = nombreplaga;
    }

    public Plagas(String nombreplaga, String nombrecientifico, String descripcion, String aquienafecta) {
        this.nombreplaga = nombreplaga;
        this.nombrecientifico = nombrecientifico;
        this.descripcion = descripcion;
        this.aquienafecta = aquienafecta;
    }

    public String getNombreplaga() {
        return nombreplaga;
    }

    public void setNombreplaga(String nombreplaga) {
        this.nombreplaga = nombreplaga;
    }

    public String getNombrecientifico() {
        return nombrecientifico;
    }

    public void setNombrecientifico(String nombrecientifico) {
        this.nombrecientifico = nombrecientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAquienafecta() {
        return aquienafecta;
    }

    public void setAquienafecta(String aquienafecta) {
        this.aquienafecta = aquienafecta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Plagasporcultivos> getPlagasporcultivosCollection() {
        return plagasporcultivosCollection;
    }

    public void setPlagasporcultivosCollection(Collection<Plagasporcultivos> plagasporcultivosCollection) {
        this.plagasporcultivosCollection = plagasporcultivosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreplaga != null ? nombreplaga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plagas)) {
            return false;
        }
        Plagas other = (Plagas) object;
        if ((this.nombreplaga == null && other.nombreplaga != null) || (this.nombreplaga != null && !this.nombreplaga.equals(other.nombreplaga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Plagas[ nombreplaga=" + nombreplaga + " ]";
    }
    
}
