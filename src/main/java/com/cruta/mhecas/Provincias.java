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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "provincias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincias.findAll", query = "SELECT p FROM Provincias p"),
    @NamedQuery(name = "Provincias.findByIdprovincia", query = "SELECT p FROM Provincias p WHERE p.idprovincia = :idprovincia"),
    @NamedQuery(name = "Provincias.findByProvincia", query = "SELECT p FROM Provincias p WHERE p.provincia = :provincia"),
    @NamedQuery(name = "Provincias.findByActivo", query = "SELECT p FROM Provincias p WHERE p.activo = :activo"),
    @NamedQuery(name = "Provincias.findByLatitud", query = "SELECT p FROM Provincias p WHERE p.latitud = :latitud"),
    @NamedQuery(name = "Provincias.findByLongitud", query = "SELECT p FROM Provincias p WHERE p.longitud = :longitud")})
public class Provincias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idprovincia")
    private Integer idprovincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "provincia")
    private String provincia;
    @Size(max = 2)
    @Column(name = "activo")
    private String activo;
    @Column(name = "latitud")
    private Integer latitud;
    @Column(name = "longitud")
    private Integer longitud;
    @JoinColumn(name = "idpais", referencedColumnName = "idpais")
    @ManyToOne
    private Pais idpais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprovincia")
    private Collection<Municipios> municipiosCollection;

    public Provincias() {
    }

    public Provincias(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public Provincias(Integer idprovincia, String provincia) {
        this.idprovincia = idprovincia;
        this.provincia = provincia;
    }

    public Integer getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getLatitud() {
        return latitud;
    }

    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public Pais getIdpais() {
        return idpais;
    }

    public void setIdpais(Pais idpais) {
        this.idpais = idpais;
    }

    @XmlTransient
    public Collection<Municipios> getMunicipiosCollection() {
        return municipiosCollection;
    }

    public void setMunicipiosCollection(Collection<Municipios> municipiosCollection) {
        this.municipiosCollection = municipiosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprovincia != null ? idprovincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincias)) {
            return false;
        }
        Provincias other = (Provincias) object;
        if ((this.idprovincia == null && other.idprovincia != null) || (this.idprovincia != null && !this.idprovincia.equals(other.idprovincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Provincias[ idprovincia=" + idprovincia + " ]";
    }
    
}
