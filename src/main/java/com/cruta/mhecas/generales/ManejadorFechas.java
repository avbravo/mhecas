/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author avbravo
 */
@Named(value = "manejadorFechas")
@SessionScoped
public class ManejadorFechas implements Serializable {

    private Date desde;
    private Date hasta;

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
    
    /**
     * Creates a new instance of ManejadorFechas
     */
    public ManejadorFechas() {
    }
    /*
    de una fecha convierte a localdate y coloca la fecha del primer dia del mes
    y del ultimo dia
    */
    public void conversorInicioFin(Date fecha){
        try {

            LocalDate mydate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate inicio = LocalDate.of(mydate.getYear(), mydate.getMonth(), 1);

            LocalDate fin = LocalDate.of(mydate.getYear(), mydate.getMonth(), mydate.lengthOfMonth());
            desde = Date.from(inicio.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            hasta = Date.from(fin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
             JSFUtil.addErrorMessage("conversorInicioFin " + e.getLocalizedMessage());
        }
    }
}
