/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class ResourcesFiles implements Serializable {

    private static final long serialVersionUID = 1L;
    Locale currentLocale;
    ResourceBundle mrb; //for messages atributos
    ResourceBundle arb; //for application
    ResourceBundle merb; //menu
    ResourceBundle erb;  // entity
       ResourceBundle frb;  // form

    public ResourcesFiles() {
    }

    @PostConstruct
    public void init() {
        saveLocale();
    }

    public void saveLocale() {
        currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        mrb = ResourceBundle.getBundle("com.cruta.hecas.properties.messages",
                currentLocale);
        arb = ResourceBundle.getBundle("com.cruta.hecas.properties.application",
                currentLocale);
        merb = ResourceBundle.getBundle("com.cruta.hecas.properties.menu",
                currentLocale);
        erb = ResourceBundle.getBundle("com.cruta.hecas.properties.entity",
                currentLocale);
        frb = ResourceBundle.getBundle("com.cruta.hecas.properties.form",
                currentLocale);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public ResourceBundle getMrb() {
        return mrb;
    }

    public void setMrb(ResourceBundle mrb) {
        this.mrb = mrb;
    }

    public ResourceBundle getArb() {
        return arb;
    }

    public void setArb(ResourceBundle arb) {
        this.arb = arb;
    }
    /*
     *Devuelve el mensaje Mrb
     */

    public String getMensajeMrb(String mensaje) {
        return mrb.getString(mensaje);
    }
    /*
     *Devuelve el mensaje Arb
     */

    public String getMensajeArb(String mensaje) {
        return arb.getString(mensaje);
    }

    public ResourceBundle getMerb() {
        return merb;
    }

    public void setMerb(ResourceBundle merb) {
        this.merb = merb;
    }

    public ResourceBundle getErb() {
        return erb;
    }

    public void setErb(ResourceBundle erb) {
        this.erb = erb;
    }

    public ResourceBundle getFrb() {
        return frb;
    }

    public void setFrb(ResourceBundle frb) {
        this.frb = frb;
    }

  
   
}
