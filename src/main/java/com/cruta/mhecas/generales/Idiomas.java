/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;



/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class Idiomas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    ResourcesFiles rf;
    private String locale = Locale.getDefault().getDisplayLanguage();

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public synchronized String getLocale() {
        return locale;
    }

    public synchronized String changeLanguage() {
        return "changed";
    }

    public Idiomas() {
    }

    public String englishAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.ENGLISH);
        this.locale = "en";
        rf.saveLocale();
        return null;
    }

    public String spanishAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale("es"));
        this.locale = "es";
        rf.saveLocale();
        return null;
    }
    
    
}
