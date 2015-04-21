/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;


import com.cruta.mhecas.Plagas;
import com.cruta.mhecas.controller.PlagasController;
import com.cruta.mhecas.ejb.PlagasFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.ResourcesFiles;
import com.cruta.mhecas.interfaces.ISearchController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author avbravo
 */
@Named(value = "plagasSearchController")
@ViewScoped
public class PlagasSearchController implements Serializable, ISearchController {

    @Inject
    PlagasFacade plagasFacade;
    @Inject
    ResourcesFiles rf;
    @Inject
    PlagasController plagasController;
    Plagas plagas = new Plagas();
    Plagas selected = new Plagas();
    private List<Plagas> filtered;
    List<Plagas> plagasList;
    private List<Plagas> items, itemsEntity, itemsCollection;
    private Boolean usarlike = false;
    @Inject
    GestorImpresion gestorImpresion;
 private String page_call = "";

    public String getPage_call() {
        return page_call;
    }

    public void setPage_call(String page_call) {
        this.page_call = page_call;
    }
    public Plagas getPlagas() {
        return plagas;
    }

    public void setPlagas(Plagas plagas) {
        this.plagas = plagas;
    }

    public List<Plagas> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Plagas> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of PlagasDataController
     */
    public Plagas getSelected() {
        return selected;
    }

    public void setSelected(Plagas selected) {
        this.selected = selected;
    }

    public PlagasSearchController() {
        plagasList = new ArrayList<>();
    }

    public List<Plagas> getPlagasList() {
        return plagasList;
    }

    public void setPlagasList(List<Plagas> plagasList) {
        this.plagasList = plagasList;
    }

    public Boolean getUsarlike() {
        return usarlike;
    }

    public void setUsarlike(Boolean usarlike) {
        this.usarlike = usarlike;
    }

    @PostConstruct
    public void init() {
iniciar();
    }

    @Override
    public String load() {
        try {
            plagas = plagasController.getPlagas();
            selected = plagasFacade.find(plagas.getNombreplaga());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                plagasController.setEncontrado(false);
            } else {
                regresar();
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage("load()" + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String clear() {
        plagasList = new ArrayList<>();
        return null;
    }

    @Override
    public void iniciar() {
        plagasList = plagasFacade.getPlagasList();
    }

    @Override
    public void iniciar(String value) {
//        plagasList = plagasFacade.findByActivo(value);
    }

    public String buscarEmail() {
        try {

            plagas.setNombrecientifico("");
            plagasList = plagasFacade.findByNombreplaga(plagas.getNombreplaga());
          
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }

        return "";
    }

  

  

  
    /*
     buscar por el idnivel1 para filtrar por el entity relacionado
     */

  

    @Override
    public String showAll() {
        try {

            plagasList = plagasFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresar() {
        try {

            plagasController.setEncontrado(true);
            plagasController.setPlagas(selected);
plagasController.setNuevoregistro(false);
plagasController.setDesactivar(false);
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

            plagasController.setPlagas(new Plagas());
            plagasController.setEncontrado(false);

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

    @Override
    public List<Plagas> getItems() {
        try {
            if (items == null) {
              items = plagasFacade.findAll();
            
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }

    @Override
    public List<Plagas> getItemsEntity() {
        return itemsEntity;
    }

    @Override
    public String changeItems() {
        try {
            itemsEntity = plagasFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public List<Plagas> getItemsCollection() {
        try {
//            itemsCollection = new ArrayList(plagas.getIdgrupousuario().getPlagasCollection());

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return itemsCollection;
    }

    @Override
    public String changeItemsInverso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String imprimirTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   @Override
    public String listar(){
        showAll();
        imprimirTodos();
        return null;
    }
    @Override
      public String delete(){
        try {
            plagasFacade.remove(selected);
        plagasList.remove(selected);
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

