/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "alertas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alertas.findAll", query = "SELECT a FROM Alertas a"),
    @NamedQuery(name = "Alertas.findByIdalerta", query = "SELECT a FROM Alertas a WHERE a.idalerta = :idalerta"),
    @NamedQuery(name = "Alertas.findByNombrecultivo", query = "SELECT a FROM Alertas a WHERE a.nombrecultivo = :nombrecultivo"),
    @NamedQuery(name = "Alertas.findByNombreplaga", query = "SELECT a FROM Alertas a WHERE a.nombreplaga = :nombreplaga"),
    @NamedQuery(name = "Alertas.findByGradoafectacion", query = "SELECT a FROM Alertas a WHERE a.gradoafectacion = :gradoafectacion"),
    @NamedQuery(name = "Alertas.findByLatitud", query = "SELECT a FROM Alertas a WHERE a.latitud = :latitud"),
    @NamedQuery(name = "Alertas.findByLongitud", query = "SELECT a FROM Alertas a WHERE a.longitud = :longitud"),
    @NamedQuery(name = "Alertas.findByPorcentajeafectacion", query = "SELECT a FROM Alertas a WHERE a.porcentajeafectacion = :porcentajeafectacion"),
    @NamedQuery(name = "Alertas.findByEtapadelcultivo", query = "SELECT a FROM Alertas a WHERE a.etapadelcultivo = :etapadelcultivo"),
    @NamedQuery(name = "Alertas.findByFlor", query = "SELECT a FROM Alertas a WHERE a.flor = :flor"),
    @NamedQuery(name = "Alertas.findByTallo", query = "SELECT a FROM Alertas a WHERE a.tallo = :tallo"),
    @NamedQuery(name = "Alertas.findByHoja", query = "SELECT a FROM Alertas a WHERE a.hoja = :hoja"),
    @NamedQuery(name = "Alertas.findByFruto", query = "SELECT a FROM Alertas a WHERE a.fruto = :fruto"),
    @NamedQuery(name = "Alertas.findByRaices", query = "SELECT a FROM Alertas a WHERE a.raices = :raices"),
    @NamedQuery(name = "Alertas.findByFecha", query = "SELECT a FROM Alertas a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Alertas.findByPuntos", query = "SELECT a FROM Alertas a WHERE a.puntos = :puntos"),
    @NamedQuery(name = "Alertas.findByFoto", query = "SELECT a FROM Alertas a WHERE a.foto = :foto")})
public class Alertas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idalerta")
    private Integer idalerta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombrecultivo")
    private String nombrecultivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreplaga")
    private String nombreplaga;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "gradoafectacion")
    private String gradoafectacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitud")
    private Double latitud;
    @Size(max = 45)
    @Column(name = "longitud")
    private String longitud;
    @Column(name = "porcentajeafectacion")
    private Double porcentajeafectacion;
    @Size(max = 45)
    @Column(name = "etapadelcultivo")
    private String etapadelcultivo;
    @Size(max = 2)
    @Column(name = "flor")
    private String flor;
    @Size(max = 2)
    @Column(name = "tallo")
    private String tallo;
    @Size(max = 2)
    @Column(name = "hoja")
    private String hoja;
    @Size(max = 2)
    @Column(name = "fruto")
    private String fruto;
    @Size(max = 2)
    @Column(name = "raices")
    private String raices;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "puntos")
    private Integer puntos;
    @Size(max = 200)
    @Column(name = "foto")
    private String foto;
    @OneToMany(mappedBy = "idalerta")
    private Collection<Alertasfotos> alertasfotosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idalerta")
    private Collection<Alertascomentarios> alertascomentariosCollection;

    public Alertas() {
    }

    public Alertas(Integer idalerta) {
        this.idalerta = idalerta;
    }

    public Alertas(Integer idalerta, String nombrecultivo, String nombreplaga) {
        this.idalerta = idalerta;
        this.nombrecultivo = nombrecultivo;
        this.nombreplaga = nombreplaga;
    }

    public Integer getIdalerta() {
        return idalerta;
    }

    public void setIdalerta(Integer idalerta) {
        this.idalerta = idalerta;
    }

    public String getNombrecultivo() {
        return nombrecultivo;
    }

    public void setNombrecultivo(String nombrecultivo) {
        this.nombrecultivo = nombrecultivo;
    }

    public String getNombreplaga() {
        return nombreplaga;
    }

    public void setNombreplaga(String nombreplaga) {
        this.nombreplaga = nombreplaga;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGradoafectacion() {
        return gradoafectacion;
    }

    public void setGradoafectacion(String gradoafectacion) {
        this.gradoafectacion = gradoafectacion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Double getPorcentajeafectacion() {
        return porcentajeafectacion;
    }

    public void setPorcentajeafectacion(Double porcentajeafectacion) {
        this.porcentajeafectacion = porcentajeafectacion;
    }

    public String getEtapadelcultivo() {
        return etapadelcultivo;
    }

    public void setEtapadelcultivo(String etapadelcultivo) {
        this.etapadelcultivo = etapadelcultivo;
    }

    public String getFlor() {
        return flor;
    }

    public void setFlor(String flor) {
        this.flor = flor;
    }

    public String getTallo() {
        return tallo;
    }

    public void setTallo(String tallo) {
        this.tallo = tallo;
    }

    public String getHoja() {
        return hoja;
    }

    public void setHoja(String hoja) {
        this.hoja = hoja;
    }

    public String getFruto() {
        return fruto;
    }

    public void setFruto(String fruto) {
        this.fruto = fruto;
    }

    public String getRaices() {
        return raices;
    }

    public void setRaices(String raices) {
        this.raices = raices;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Alertasfotos> getAlertasfotosCollection() {
        return alertasfotosCollection;
    }

    public void setAlertasfotosCollection(Collection<Alertasfotos> alertasfotosCollection) {
        this.alertasfotosCollection = alertasfotosCollection;
    }

    @XmlTransient
    public Collection<Alertascomentarios> getAlertascomentariosCollection() {
        return alertascomentariosCollection;
    }

    public void setAlertascomentariosCollection(Collection<Alertascomentarios> alertascomentariosCollection) {
        this.alertascomentariosCollection = alertascomentariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalerta != null ? idalerta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alertas)) {
            return false;
        }
        Alertas other = (Alertas) object;
        if ((this.idalerta == null && other.idalerta != null) || (this.idalerta != null && !this.idalerta.equals(other.idalerta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Alertas[ idalerta=" + idalerta + " ]";
    }
    
}
