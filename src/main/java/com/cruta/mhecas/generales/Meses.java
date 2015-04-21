/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class Meses implements Serializable {

    private List<String> listMeses = new ArrayList<>();

    /**
     * Creates a new instance of Meses
     */
    public Meses() {
        listMeses.add("Enero");
        listMeses.add("Febrero");
        listMeses.add("Marzo");
        listMeses.add("Abril");
        listMeses.add("Mayo");
        listMeses.add("Junio");
        listMeses.add("Julio");
        listMeses.add("Agosto");
        listMeses.add("Septiembre");
        listMeses.add("Octubre");
        listMeses.add("Noviembre");
        listMeses.add("Diciembre");

    }

    public List<String> getListMeses() {
//        listMeses = new TreeMap<>();

        return listMeses;
    }

    public void setListMeses(List<String> listMeses) {
        this.listMeses = listMeses;
    }

    public Integer numeroMes(String mes) {
        Integer i = -1;
        for (String l : listMeses) {
            i++;
            if (l.equals(mes)) {
                return i +1;
            } 
        }
//        switch (mes) {
//            case "Enero":
//                return 1;
//            case "Febrero":
//                return 2;
//            case "Marzo":
//                return 3;
//            case "Abril":
//                return 4;
//            case "Mayo":
//                return 5;
//            case "Junio":
//                return 6;
//            case "Julio":
//                return 7;
//            case "Agosto":
//                return 8;
//            case "Septiembre":
//                return 9;
//            case "Octubre":
//                return 10;
//            case "Noviembre":
//                return 11;
//            case "Diciembre":
//                return 12;
//        }

        return -1;

    }
    public String getNombreMes(Integer mes){
        return listMeses.get(mes);
        
    }

}
