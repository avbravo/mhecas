/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class ManagementThemes implements Serializable {

    private static final long serialVersionUID = 1L;
//    private String tema = "redmond";
//    private String temaPredeterminado = "redmond";
       private String tema = "aristo";
    private String temaPredeterminado = "aristo";
    private Map<String, String> themes;

    public Map<String, String> getThemes() {
        themes = new TreeMap<String, String>();
        themes.put("afterdark", "afterdark");
        themes.put("afternoon", "afternoon");
        themes.put("afterwork", "afterwork");
        themes.put("aristo", "aristo");
        themes.put("black-tie", "black-tie");
         themes.put("bootstrap","bootstrap");
        themes.put("blitzer", "blitzer");
        themes.put("bluesky", "bluesky");
        themes.put("casablanca", "casablanca");
        themes.put("cupertino", "cupertino");
        themes.put("cruze", "cruze");
        themes.put("dark-hive", "dark-hive");
        themes.put("dot-luv", "dot-luv");
        themes.put("eggplant", "eggplant");
        themes.put("excite-bike", "excite-bike");
        themes.put("flick", "flick");
        themes.put("glass-x", "glass-x");
        themes.put("home", "home");
        themes.put("hot-sneaks", "hot-sneaks");
        themes.put("humanity", "humanity");
        themes.put("le-frog", "le-frog");
        themes.put("midnight", "midnight");
        themes.put("mint-choc", "mint-choc");
        themes.put("none", "none");
        themes.put("overcast", "overcast");
        themes.put("pepper-grinder", "pepper-grinder");
        themes.put("redmond", "redmond");
        themes.put("rocket", "rocket");
        themes.put("sam", "sam");
        themes.put("smoothness", "smoothness");
        themes.put("south-street", "south-street");
        themes.put("start", "start");
        themes.put("sunny", "sunny");
        themes.put("swanky-purse", "swanky-purse");
        themes.put("trontastic", "trontastic");
        themes.put("ui-darkness", "ui-darkness");
        themes.put("ui-lightness", "ui-lightness");
        themes.put("vader", "vader");
        return themes;
    }

    public String getTemaPredeterminado() {
        return temaPredeterminado;
    }

    public void setTemaPredeterminado(String temaPredeterminado) {
        this.temaPredeterminado = temaPredeterminado;
    }

    public void setThemes(Map<String, String> themes) {
        this.themes = themes;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String cambiar() {
        return null;
    }
}
