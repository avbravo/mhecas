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
@Table(name = "corregimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corregimientos.findAll", query = "SELECT c FROM Corregimientos c"),
    @NamedQuery(name = "Corregimientos.findByIdcorregimiento", query = "SELECT c FROM Corregimientos c WHERE c.idcorregimiento = :idcorregimiento"),
    @NamedQuery(name = "Corregimientos.findByCorregimiento", query = "SELECT c FROM Corregimientos c WHERE c.corregimiento = :corregimiento"),
    @NamedQuery(name = "Corregimientos.findByLatitud", query = "SELECT c FROM Corregimientos c WHERE c.latitud = :latitud"),
    @NamedQuery(name = "Corregimientos.findByLongitud", query = "SELECT c FROM Corregimientos c WHERE c.longitud = :longitud")})
public class Corregimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcorregimiento")
    private Integer idcorregimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "corregimiento")
    private String corregimiento;
    @Column(name = "latitud")
    private Integer latitud;
    @Column(name = "longitud")
    private Integer longitud;
    @JoinColumn(name = "idmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipios idmunicipio;

    public Corregimientos() {
    }

    public Corregimientos(Integer idcorregimiento) {
        this.idcorregimiento = idcorregimiento;
    }

    public Corregimientos(Integer idcorregimiento, String corregimiento) {
        this.idcorregimiento = idcorregimiento;
        this.corregimiento = corregimiento;
    }

    public Integer getIdcorregimiento() {
        return idcorregimiento;
    }

    public void setIdcorregimiento(Integer idcorregimiento) {
        this.idcorregimiento = idcorregimiento;
    }

    public String getCorregimiento() {
        return corregimiento;
    }

    public void setCorregimiento(String corregimiento) {
        this.corregimiento = corregimiento;
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

    public Municipios getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Municipios idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcorregimiento != null ? idcorregimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corregimientos)) {
            return false;
        }
        Corregimientos other = (Corregimientos) object;
        if ((this.idcorregimiento == null && other.idcorregimiento != null) || (this.idcorregimiento != null && !this.idcorregimiento.equals(other.idcorregimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Corregimientos[ idcorregimiento=" + idcorregimiento + " ]";
    }
    
}
