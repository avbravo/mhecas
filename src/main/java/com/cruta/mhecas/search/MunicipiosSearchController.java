/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;



import com.cruta.mhecas.Municipios;
import com.cruta.mhecas.controller.MunicipiosController;
import com.cruta.mhecas.ejb.MunicipiosFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.ResourcesFiles;
import com.cruta.mhecas.interfaces.ISearchController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
public class MunicipiosSearchController implements Serializable, ISearchController {

    @Inject
    MunicipiosFacade municipiosFacade;
    @Inject
    ResourcesFiles rf;
    @Inject
    MunicipiosController municipiosController;
    Municipios municipios = new Municipios();
    Municipios selected = new Municipios();
    private List<Municipios> filtered;
    List<Municipios> municipiosList;
    private List<Municipios> items, itemsEntity, itemsCollection;
    private Boolean usarlike = false;
    @Inject
    GestorImpresion gestorImpresion;
    @Inject
    ProvinciasSearchController provinciasSearchController;
 private String page_call = "";

    public String getPage_call() {
        return page_call;
    }

    public void setPage_call(String page_call) {
        this.page_call = page_call;
    }
    public Municipios getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Municipios municipios) {
        this.municipios = municipios;
    }

    public List<Municipios> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Municipios> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of MunicipiosDataController
     */
    public Municipios getSelected() {
        return selected;
    }

    public void setSelected(Municipios selected) {
        this.selected = selected;
    }

    public MunicipiosSearchController() {
        municipiosList = new ArrayList<>();
    }

    public List<Municipios> getMunicipiosList() {
        return municipiosList;
    }

    public void setMunicipiosList(List<Municipios> municipiosList) {
        this.municipiosList = municipiosList;
    }

    public Boolean getUsarlike() {
        return usarlike;
    }

    public void setUsarlike(Boolean usarlike) {
        this.usarlike = usarlike;
    }

    @PostConstruct
    public void init() {

    }

    @Override
    public String clear() {
        municipiosList = new ArrayList<>();
        return null;
    }

    @Override
    public void iniciar() {
        municipiosList = municipiosFacade.getMunicipiosList();
    }

    @Override
    public void iniciar(String value) {
        municipiosList = municipiosFacade.findByActivo(value);
    }

    public String buscarIdMunicipio() {
        try {
            municipios.setIdmunicipio(0);
            municipiosList = municipiosFacade.findByIdMunicipio(municipios.getIdmunicipio().toString());

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }

        return "";
    }

    public String buscarMunicipio() {
        try {
            municipios.setMunicipio("");
            if (!usarlike) {
                municipiosList = municipiosFacade.findByMunicipio(municipios.getMunicipio());
            } else {
                municipiosList = municipiosFacade.findByMunicipio(municipios.getMunicipio());
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String showAll() {
        try {

            municipiosList = municipiosFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresar() {
        try {

            municipiosController.setEncontrado(true);
            municipiosController.setMunicipios(selected);
            municipiosController.setNuevoregistro(false);
            municipiosController.setNuevoregistro(false);
            municipiosController.setDesactivar(false);
          if (page_call == ""){
          
                  
               }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresarSinSeleccion() {
        try {

            municipiosController.setMunicipios(new Municipios());
            municipiosController.setEncontrado(false);

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

    @Override
    public List<Municipios> getItems() {
        try {
            if (items == null) {
                items = municipiosFacade.findAll();
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }

    @Override
    public List<Municipios> getItemsCollection() {

        try {
            itemsCollection = new ArrayList(municipios.getIdprovincia().getMunicipiosCollection());
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return itemsCollection;
    }

    @Override
    public List<Municipios> getItemsEntity() {
        return itemsEntity;
    }

    @Override
    public String changeItems() {
        try {

            itemsEntity = municipiosFacade.findByIdProvincia(municipios.getIdprovincia());
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String load() {
        try {
            municipios = municipiosController.getMunicipios();
            selected = municipiosFacade.find(municipios.getIdmunicipio());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                municipiosController.setEncontrado(false);
            } else {
                regresar();
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage("load()" + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String changeItemsInverso() {
        try {
          
            itemsEntity = municipiosFacade.findByIdProvincia(municipios.getIdprovincia());
            provinciasSearchController.setProvincias(municipios.getIdprovincia());
            provinciasSearchController.changeItems();
        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimirTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String listar() {
        showAll();
        imprimirTodos();
        return null;
    }

    @Override
    public String delete() {
        try {
            municipiosFacade.remove(selected);
            municipiosList.remove(selected);
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void onCellEdit(CellEditEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
