/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.controller;


import com.cruta.mhecas.Provincias;
import com.cruta.mhecas.ejb.ProvinciasFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.LoginBean;
import com.cruta.mhecas.generales.ResourcesFiles;
import com.cruta.mhecas.interfaces.IController;
import java.io.Serializable;
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
public class ProvinciasController implements Serializable, IController {

    private static final long serialVersionUID = 1L;

    @Inject
    ProvinciasFacade provinciasFacade;
    Provincias provincias = new Provincias();
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

    public Provincias getProvincias() {
        return provincias;
    }

    public void setProvincias(Provincias provincias) {
        this.provincias = provincias;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance of ProvinciasController
     */
    public ProvinciasController() {
    }

    @PostConstruct
    public void init() {   desactivar =true;
        nuevoregistro = false;

    }

    @Override
    public String buscar() {
        provincias = provinciasFacade.find(provincias.getIdprovincia());
        if (provincias == null) {
            encontrado = false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            provincias = new Provincias();
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
            provincias = new Provincias();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String save() {
        try {

            if (provinciasFacade.find(provincias.getIdprovincia()) != null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.idexist"));
                return null;
            }
            provinciasFacade.create(provincias);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.save"));
            provincias = new Provincias();
            this.nuevoregistro = false;
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {

            provinciasFacade.edit(provincias);

            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String delete() {
        try {
            provinciasFacade.remove(provincias);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));

            encontrado = false;
            provincias = new Provincias();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimir() {
//        try {
//            List<Provincias> list = new ArrayList<>();
//            list.add(provincias);
//            String ruta = "/resources/reportes/provincias/provincias.jasper";
//            HashMap parameters = new HashMap();
//            gestorImpresion.imprimir(list, ruta, parameters);
//        } catch (Exception ex) {
//            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
//        }
        return null;
    }

    @Override
    public String imprimirTodos() {
//        String ruta = "/resources/reportes/provincias/provincias.jasper";
//        HashMap parameters = new HashMap();
//        gestorImpresion.imprimir(provinciasFacade.getProvinciasList(), ruta, parameters);
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

    @Override
    public String habilitarConsultar() {        desactivar=true;
        provincias.setIdprovincia(0);
        this.nuevoregistro = false;
        return "";
    }

    @Override
    public Integer getIdSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
