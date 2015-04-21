/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.controller;


import com.cruta.mhecas.Corregimientos;
import com.cruta.mhecas.ejb.CorregimientosFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.LoginBean;
import com.cruta.mhecas.generales.ResourcesFiles;
import com.cruta.mhecas.interfaces.IController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class CorregimientosController implements Serializable, IController {

    private static final long serialVersionUID = 1L;

    @Inject
    CorregimientosFacade corregimientosFacade;
    Corregimientos corregimientos = new Corregimientos();
    private Boolean encontrado = false;
    @Inject
    ResourcesFiles rf;
    @Inject
    GestorImpresion gestorImpresion;
    @Inject
    LoginBean loginBean;
    private Boolean nuevoregistro = false;
 Boolean desactivar =true;

    public Boolean getDesactivar() {
        return desactivar;
    }

    public void setDesactivar(Boolean desactivar) {
        this.desactivar = desactivar;
    }


    public Boolean getNuevoregistro() {
        return nuevoregistro;
    }

    public void setNuevoregistro(Boolean nuevoregistro) {
        this.nuevoregistro = nuevoregistro;
    }

    public Corregimientos getCorregimientos() {
        return corregimientos;
    }

    public void setCorregimientos(Corregimientos corregimientos) {
        this.corregimientos = corregimientos;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance of CorregimientosController
     */
    public CorregimientosController() {
    }

    @PostConstruct
    public void init() {   desactivar =true;
        nuevoregistro = false;
    }

    @Override
    public String buscar() {
        corregimientos = corregimientosFacade.find(corregimientos.getIdcorregimiento());
        if (corregimientos == null) {
            encontrado = false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            corregimientos = new Corregimientos();
        } else {
            encontrado = true;
        }
        return "";
    }

    @Override
    public String prepararNew() {      desactivar = false;
        try {
            nuevoregistro = true;
            encontrado = false;
            corregimientos = new Corregimientos();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String save() {
        try {
//            corregimientos.setUsername(loginBean.getUsuarios());
            //corregimientos.setIdmunicipio(loginBean.getUsuarios().getIdmunicipio());

//            corregimientos.setFecha(JSFUtil.getFechaActual());
            if (corregimientosFacade.find(corregimientos.getIdcorregimiento()) != null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.idexist"));
                return null;
            }
            corregimientosFacade.create(corregimientos);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.save"));
            corregimientos = new Corregimientos();
            this.nuevoregistro = false;
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {
//            corregimientos.setUsername(loginBean.getUsuarios());
//            corregimientos.setIdmunicipio(loginBean.getUsuarios().getIdmunicipio());

//            corregimientos.setFecha(JSFUtil.getFechaActual());
            corregimientosFacade.edit(corregimientos);

            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String delete() {
        try {
            corregimientosFacade.remove(corregimientos);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));

            encontrado = false;
            corregimientos = new Corregimientos();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimir() {
        try {

            List<Corregimientos> list = new ArrayList<>();
            list.add(corregimientos);
            String ruta = "/resources/reportes/corregimientos/corregimientos.jasper";
            HashMap parameters = new HashMap();
            gestorImpresion.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimirTodos() {
        String ruta = "/resources/reportes/corregimientos/corregimientos.jasper";
        HashMap parameters = new HashMap();
        //gestorImpresion.imprimir(corregimientosFacade.getCorregimientosList(), ruta, parameters);
        return null;
    }

    @Override
    public Integer contador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String elementoSeleccionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String habilitarConsultar() {        desactivar=true;
        corregimientos.setIdcorregimiento(0);
        this.nuevoregistro = false;
        return "";
    }

    @Override
    public Integer getIdSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
