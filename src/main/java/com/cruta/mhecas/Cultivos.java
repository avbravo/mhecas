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
@Table(name = "cultivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cultivos.findAll", query = "SELECT c FROM Cultivos c"),
    @NamedQuery(name = "Cultivos.findByNombrecultivo", query = "SELECT c FROM Cultivos c WHERE c.nombrecultivo = :nombrecultivo"),
    @NamedQuery(name = "Cultivos.findByNombrecientifico", query = "SELECT c FROM Cultivos c WHERE c.nombrecientifico = :nombrecientifico"),
    @NamedQuery(name = "Cultivos.findByDescripcion", query = "SELECT c FROM Cultivos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Cultivos.findByFoto", query = "SELECT c FROM Cultivos c WHERE c.foto = :foto"),
    @NamedQuery(name = "Cultivos.findByFamilia", query = "SELECT c FROM Cultivos c WHERE c.familia = :familia")})
public class Cultivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombrecultivo")
    private String nombrecultivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrecientifico")
    private String nombrecientifico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 200)
    @Column(name = "foto")
    private String foto;
    @Size(max = 100)
    @Column(name = "familia")
    private String familia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombrecultivo")
    private Collection<Miscultivos> miscultivosCollection;
    @OneToMany(mappedBy = "nombrecultivo")
    private Collection<Plagasporcultivos> plagasporcultivosCollection;

    public Cultivos() {
    }

    public Cultivos(String nombrecultivo) {
        this.nombrecultivo = nombrecultivo;
    }

    public Cultivos(String nombrecultivo, String nombrecientifico, String descripcion) {
        this.nombrecultivo = nombrecultivo;
        this.nombrecientifico = nombrecientifico;
        this.descripcion = descripcion;
    }

    public String getNombrecultivo() {
        return nombrecultivo;
    }

    public void setNombrecultivo(String nombrecultivo) {
        this.nombrecultivo = nombrecultivo;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    @XmlTransient
    public Collection<Miscultivos> getMiscultivosCollection() {
        return miscultivosCollection;
    }

    public void setMiscultivosCollection(Collection<Miscultivos> miscultivosCollection) {
        this.miscultivosCollection = miscultivosCollection;
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
        hash += (nombrecultivo != null ? nombrecultivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cultivos)) {
            return false;
        }
        Cultivos other = (Cultivos) object;
        if ((this.nombrecultivo == null && other.nombrecultivo != null) || (this.nombrecultivo != null && !this.nombrecultivo.equals(other.nombrecultivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Cultivos[ nombrecultivo=" + nombrecultivo + " ]";
    }
    
}
