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
import javax.persistence.OneToOne;
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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByEmail", query = "SELECT u FROM Usuarios u WHERE u.email = :email"),
    @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByCelular", query = "SELECT u FROM Usuarios u WHERE u.celular = :celular"),
    @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuarios.findBySexo", query = "SELECT u FROM Usuarios u WHERE u.sexo = :sexo"),
    @NamedQuery(name = "Usuarios.findByFoto", query = "SELECT u FROM Usuarios u WHERE u.foto = :foto"),
    @NamedQuery(name = "Usuarios.findByProfesion", query = "SELECT u FROM Usuarios u WHERE u.profesion = :profesion")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Size(max = 35)
    @Column(name = "celular")
    private String celular;
    @Size(max = 35)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 200)
    @Column(name = "foto")
    private String foto;
    @Size(max = 80)
    @Column(name = "profesion")
    private String profesion;
    @OneToMany(mappedBy = "email")
    private Collection<Miscultivos> miscultivosCollection;
    @JoinColumn(name = "idgrupousuario", referencedColumnName = "idgrupousuario")
    @ManyToOne(optional = false)
    private Grupousuarios idgrupousuario;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private Configuraralertas configuraralertas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "email")
    private Collection<Alertascomentarios> alertascomentariosCollection;

    public Usuarios() {
    }

    public Usuarios(String email) {
        this.email = email;
    }

    public Usuarios(String email, String nombre, String password, String sexo) {
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @XmlTransient
    public Collection<Miscultivos> getMiscultivosCollection() {
        return miscultivosCollection;
    }

    public void setMiscultivosCollection(Collection<Miscultivos> miscultivosCollection) {
        this.miscultivosCollection = miscultivosCollection;
    }

    public Grupousuarios getIdgrupousuario() {
        return idgrupousuario;
    }

    public void setIdgrupousuario(Grupousuarios idgrupousuario) {
        this.idgrupousuario = idgrupousuario;
    }

    public Configuraralertas getConfiguraralertas() {
        return configuraralertas;
    }

    public void setConfiguraralertas(Configuraralertas configuraralertas) {
        this.configuraralertas = configuraralertas;
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
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cruta.mhecas.Usuarios[ email=" + email + " ]";
    }
    
}
