/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.controller;



import com.cruta.mhecas.Municipios;
import com.cruta.mhecas.ejb.MunicipiosFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.LoginBean;
import com.cruta.mhecas.generales.ResourcesFiles;
import com.cruta.mhecas.interfaces.IController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class MunicipiosController implements Serializable, IController {

    private static final long serialVersionUID = 1L;

    @Inject
    MunicipiosFacade municipiosFacade;
    Municipios municipios = new Municipios();
    private Boolean encontrado = false;
    @Inject
    ResourcesFiles rf;
    @Inject
    GestorImpresion gestorImpresion;
    @Inject
    LoginBean loginBean;

    private List<Municipios> items;
    private List<SelectItem> municipiosItems;

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

    public Municipios getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Municipios municipios) {
        this.municipios = municipios;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance ofMunicipiosController
     */
    public MunicipiosController() {
    }

    @PostConstruct
    public void init() {   desactivar =true;
        nuevoregistro = false;

    }

    @Override
    public String buscar() {
        municipios = municipiosFacade.find(municipios.getIdmunicipio());
        if (municipios == null) {
            encontrado = false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            municipios = new Municipios();
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
            municipios = new Municipios();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String save() {
        try {
          //  municipios.setUsername(loginBean.getUsuarios());
            //  municipios.setIdmunicipio(loginBean.getUsuarios().getIdmunicipio());

            //  municipios.setFecha(JSFUtil.getFechaActual());
            if (municipiosFacade.find(municipios.getIdmunicipio()) != null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.idexist"));
                return null;
            }
            municipiosFacade.create(municipios);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.save"));
            municipios = new Municipios();
            this.nuevoregistro = false;
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {
//            municipios.setUsername(loginBean.getUsuarios());
//            municipios.setIdmunicipio(Integer.valueOf(loginBean.getUsuarios().getIdmunicipio().toString()));

//            municipios.setFecha(JSFUtil.getFechaActual());
            municipiosFacade.edit(municipios);

            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String delete() {
        try {
            municipiosFacade.remove(municipios);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));

            encontrado = false;
            municipios = new Municipios();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimir() {
//        try {
//            List<Municipios> list = new ArrayList<>();
//            list.add(municipios);
//            String ruta = "/resources/reportes/municipios/municipios.jasper";
//            HashMap parameters = new HashMap();
//            gestorImpresion.imprimir(list, ruta, parameters);
//        } catch (Exception ex) {
//            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
//        }
        return null;
    }

    @Override
    public String imprimirTodos() {
//        String ruta = "/resources/reportes/municipios/municipios.jasper";
//        HashMap parameters = new HashMap();
//        gestorImpresion.imprimir(municipiosFacade.getMunicipiosList(), ruta, parameters);
        return null;
    }

    public List<SelectItem> loadItems() {
        try {
            municipiosItems = new ArrayList<>();

            for (Municipios o : municipiosFacade.getMunicipiosList()) {
                municipiosItems.add(new SelectItem(o.getIdmunicipio(), String.valueOf(o.getMunicipio())));
            }

            return municipiosItems;
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("loadItems() " + ex.getLocalizedMessage());
        }
        return null;
    }

    public List<Municipios> getItems() {
        if (items == null) {
            items = municipiosFacade.findAll();
        }
        return items;
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
        municipios.setIdmunicipio(0);
        this.nuevoregistro = false;
        return "";
    }

    @Override
    public Integer getIdSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
