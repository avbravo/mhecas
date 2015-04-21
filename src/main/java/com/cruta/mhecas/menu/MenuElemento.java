/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.menu;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class MenuElemento implements Serializable{

    Boolean menu;
    Boolean crear;
    Boolean consultar;
    Boolean editar;
    Boolean eliminar;
    Boolean listar;

    /**
     * Creates a new instance of MenuPojo
     */
    public MenuElemento() {
        
    }

    public MenuElemento(Boolean menu, Boolean crear, Boolean consultar, Boolean editar, Boolean eliminar, Boolean listar) {
        this.menu = menu;
        this.crear = crear;
        this.consultar = consultar;
        this.editar = editar;
        this.eliminar = eliminar;
        this.listar = listar;
    }
    
    
    public void habilitar(Boolean t){
        menu=t;
        crear=t;
        consultar=t;
        editar=t;
        eliminar=t;
        listar=t;
    }

    public Boolean getMenu() {
        return menu;
    }

    public void setMenu(Boolean menu) {
        this.menu = menu;
    }

    public Boolean getCrear() {
        return crear;
    }

    public void setCrear(Boolean crear) {
        this.crear = crear;
    }

    public Boolean getConsultar() {
        return consultar;
    }

    public void setConsultar(Boolean consultar) {
        this.consultar = consultar;
    }

    public Boolean getEditar() {
        return editar;
    }

    public void setEditar(Boolean editar) {
        this.editar = editar;
    }

    public Boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }

    public Boolean getListar() {
        return listar;
    }

    public void setListar(Boolean listar) {
        this.listar = listar;
    }

}
