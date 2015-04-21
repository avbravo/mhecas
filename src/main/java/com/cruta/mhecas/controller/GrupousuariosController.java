/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.controller;


import com.cruta.mhecas.Grupousuarios;
import com.cruta.mhecas.ejb.GrupousuariosFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
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
public class GrupousuariosController implements Serializable, IController {

    private static final long serialVersionUID = 1L;

    @Inject
    GrupousuariosFacade grupousuariosFacade;
    Grupousuarios grupousuarios = new Grupousuarios();
    private Boolean encontrado = false;
    @Inject
    ResourcesFiles rf;
    @Inject
    GestorImpresion gestorImpresion;
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

    public Grupousuarios getGrupousuarios() {
        return grupousuarios;
    }

    public void setGrupousuarios(Grupousuarios grupousuarios) {
        this.grupousuarios = grupousuarios;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance of GrupousuariosController
     */
    public GrupousuariosController() {
    }

    @PostConstruct
    public void init() {   desactivar =true;
        nuevoregistro = false;

    }

    @Override
    public String buscar() {
        grupousuarios = grupousuariosFacade.find(grupousuarios.getIdgrupousuario());
        if (grupousuarios == null) {
            encontrado = false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            grupousuarios = new Grupousuarios();
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
            grupousuarios = new Grupousuarios();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String save() {
        try {

            if (grupousuariosFacade.find(grupousuarios.getIdgrupousuario()) != null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.idexist"));
                return null;
            }
            grupousuariosFacade.create(grupousuarios);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.save"));
            grupousuarios = new Grupousuarios();
             this.nuevoregistro = false;
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {

            grupousuariosFacade.edit(grupousuarios);

            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String delete() {
        try {
            grupousuariosFacade.remove(grupousuarios);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));

            encontrado = false;
            grupousuarios = new Grupousuarios();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimir() {
        try {
            List<Grupousuarios> list = new ArrayList<>();
            list.add(grupousuarios);
            String ruta = "/resources/reportes/grupousuarios/grupousuarios.jasper";
            HashMap parameters = new HashMap();
            gestorImpresion.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimirTodos() {
        String ruta = "/resources/reportes/grupousuarios/grupousuarios.jasper";
        HashMap parameters = new HashMap();
        gestorImpresion.imprimir(grupousuariosFacade.getGrupousuariosList(), ruta, parameters);
        return null;
    }

    public Integer contador() {
        return grupousuariosFacade.count();
    }

    public Integer contadorActivo(String activo) {
        return grupousuariosFacade.contadorActivo(activo).intValue();
    }

    @Override
    public String elementoSeleccionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String habilitarConsultar() {        desactivar=true;
        grupousuarios.setIdgrupousuario("");
        this.nuevoregistro = false;
        return "";
    }

    @Override
    public Integer getIdSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
