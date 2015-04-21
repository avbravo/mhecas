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
import javax.persistence.Lob;
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
@Table(name = "miscultivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miscultivos.findAll", query = "SELECT m FROM Miscultivos m"),
    @NamedQuery(name = "Miscultivos.findByIdmiscultivos", query = "SELECT m FROM Miscultivos m WHERE m.idmiscultivos = :idmiscultivos"),
    @NamedQuery(name = "Miscultivos.findByLatitud", query = "SELECT m FROM Miscultivos m WHERE m.latitud = :latitud"),
    @NamedQuery(name = "Miscultivos.findByLongitud", query = "SELECT m FROM Miscultivos m WHERE m.longitud = :longitud")})
public class Miscultivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmiscultivos")
    private Integer idmiscultivos;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Size(max = 65535)
    @Column(name = "datossiembra")
    private String datossiembra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "longitud")
    private Double longitud;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne
    private Usuarios email;
    @JoinColumn(name = "nombrecultivo", referencedColumnName = "nombrecultivo")
    @ManyToOne(optional = false)
    private Cultivos nombrecultivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmiscultivos")
    private Collection<Miscultivosfotos> miscultivosfotosCollection;

    public Miscultivos() {
    }

    public Miscultivos(Integer idmiscultivos) {
        this.idmiscultivos = idmiscultivos;
    }

    public Miscultivos(Integer idmiscultivos, String descripcion) {
        this.idmiscultivos = idmiscultivos;
        this.descripcion = descripcion;
    }

    public Integer getIdmiscultivos() {
        return idmiscultivos;
    }

    public void setIdmiscultivos(Integer idmiscultivos) {
        this.idmiscultivos = idmiscultivos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatossiembra() {
        return datossiembra;
    }

    public void setDatossiembra(String datossiembra) {
        this.datossiembra = datossiembra;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Usuarios getEmail() {
        return email;
    }

    public void setEmail(Usuarios email) {
        this.email = email;
    }

    public Cultivos getNombrecultivo() {
        return nombrecultivo;
    }

    public void setNombrecultivo(Cultivos nombrecultivo) {
        this.nombrecultivo = nombrecultivo;
    }

    @XmlTransient
    public Collection<Miscultivosfotos> getMiscultivosfotosCollection() {
        return miscultivosfotosCollection;
    }

    public void setMiscultivosfotosCollection(Collection<Miscultivosfotos> miscultivosfotosCollection) {
        this.miscultivosfotosCollection = miscultivosfotosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmiscultivos != null ? idmiscultivos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miscultivos)) {
            return false;
        }
        Miscultivos other = (Miscultivos) object;
        if ((this.idmiscultivos == null && other.idmiscultivos != null) || (this.idmiscultivos != null && !this.idmiscultivos.equals(other.idmiscultivos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Miscultivos[ idmiscultivos=" + idmiscultivos + " ]";
    }
    
}
