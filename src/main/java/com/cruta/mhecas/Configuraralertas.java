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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author avbravo
 */
@Entity
@Table(name = "configuraralertas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuraralertas.findAll", query = "SELECT c FROM Configuraralertas c"),
    @NamedQuery(name = "Configuraralertas.findByEmail", query = "SELECT c FROM Configuraralertas c WHERE c.email = :email"),
    @NamedQuery(name = "Configuraralertas.findByFrecuencia", query = "SELECT c FROM Configuraralertas c WHERE c.frecuencia = :frecuencia"),
    @NamedQuery(name = "Configuraralertas.findByNombreplaga", query = "SELECT c FROM Configuraralertas c WHERE c.nombreplaga = :nombreplaga"),
    @NamedQuery(name = "Configuraralertas.findByNombrecultivo", query = "SELECT c FROM Configuraralertas c WHERE c.nombrecultivo = :nombrecultivo"),
    @NamedQuery(name = "Configuraralertas.findByTwitter", query = "SELECT c FROM Configuraralertas c WHERE c.twitter = :twitter"),
    @NamedQuery(name = "Configuraralertas.findByFacebook", query = "SELECT c FROM Configuraralertas c WHERE c.facebook = :facebook"),
    @NamedQuery(name = "Configuraralertas.findByInstagram", query = "SELECT c FROM Configuraralertas c WHERE c.instagram = :instagram")})
public class Configuraralertas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "frecuencia")
    private String frecuencia;
    @Size(max = 80)
    @Column(name = "nombreplaga")
    private String nombreplaga;
    @Size(max = 80)
    @Column(name = "nombrecultivo")
    private String nombrecultivo;
    @Size(max = 80)
    @Column(name = "twitter")
    private String twitter;
    @Size(max = 80)
    @Column(name = "facebook")
    private String facebook;
    @Size(max = 80)
    @Column(name = "instagram")
    private String instagram;
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuarios usuarios;

    public Configuraralertas() {
    }

    public Configuraralertas(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getNombreplaga() {
        return nombreplaga;
    }

    public void setNombreplaga(String nombreplaga) {
        this.nombreplaga = nombreplaga;
    }

    public String getNombrecultivo() {
        return nombrecultivo;
    }

    public void setNombrecultivo(String nombrecultivo) {
        this.nombrecultivo = nombrecultivo;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuraralertas)) {
            return false;
        }
        Configuraralertas other = (Configuraralertas) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Configuraralertas[ email=" + email + " ]";
    }
    
}
