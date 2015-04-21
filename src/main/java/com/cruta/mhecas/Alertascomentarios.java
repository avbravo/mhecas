/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "alertascomentarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alertascomentarios.findAll", query = "SELECT a FROM Alertascomentarios a"),
    @NamedQuery(name = "Alertascomentarios.findByIdalertascomentarios", query = "SELECT a FROM Alertascomentarios a WHERE a.idalertascomentarios = :idalertascomentarios"),
    @NamedQuery(name = "Alertascomentarios.findByFecha", query = "SELECT a FROM Alertascomentarios a WHERE a.fecha = :fecha")})
public class Alertascomentarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idalertascomentarios")
    private Integer idalertascomentarios;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "idalerta", referencedColumnName = "idalerta")
    @ManyToOne(optional = false)
    private Alertas idalerta;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Usuarios email;

    public Alertascomentarios() {
    }

    public Alertascomentarios(Integer idalertascomentarios) {
        this.idalertascomentarios = idalertascomentarios;
    }

    public Alertascomentarios(Integer idalertascomentarios, String comentario, Date fecha) {
        this.idalertascomentarios = idalertascomentarios;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Integer getIdalertascomentarios() {
        return idalertascomentarios;
    }

    public void setIdalertascomentarios(Integer idalertascomentarios) {
        this.idalertascomentarios = idalertascomentarios;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Alertas getIdalerta() {
        return idalerta;
    }

    public void setIdalerta(Alertas idalerta) {
        this.idalerta = idalerta;
    }

    public Usuarios getEmail() {
        return email;
    }

    public void setEmail(Usuarios email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalertascomentarios != null ? idalertascomentarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alertascomentarios)) {
            return false;
        }
        Alertascomentarios other = (Alertascomentarios) object;
        if ((this.idalertascomentarios == null && other.idalertascomentarios != null) || (this.idalertascomentarios != null && !this.idalertascomentarios.equals(other.idalertascomentarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Alertascomentarios[ idalertascomentarios=" + idalertascomentarios + " ]";
    }
    
}
