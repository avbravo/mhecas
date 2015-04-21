/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;


import com.cruta.mhecas.Cultivos;
import com.cruta.mhecas.controller.CultivosController;
import com.cruta.mhecas.ejb.CultivosFacade;
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
@Named(value = "cultivosSearchController")
@ViewScoped
public class CultivosSearchController implements Serializable, ISearchController {

    @Inject
    CultivosFacade cultivosFacade;
    @Inject
    ResourcesFiles rf;
    @Inject
    CultivosController cultivosController;
    Cultivos cultivos = new Cultivos();
    Cultivos selected = new Cultivos();
    private List<Cultivos> filtered;
    List<Cultivos> cultivosList;
    private List<Cultivos> items, itemsEntity, itemsCollection;
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
    public Cultivos getCultivos() {
        return cultivos;
    }

    public void setCultivos(Cultivos cultivos) {
        this.cultivos = cultivos;
    }

    public List<Cultivos> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Cultivos> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of CultivosDataController
     */
    public Cultivos getSelected() {
        return selected;
    }

    public void setSelected(Cultivos selected) {
        this.selected = selected;
    }

    public CultivosSearchController() {
        cultivosList = new ArrayList<>();
    }

    public List<Cultivos> getCultivosList() {
        return cultivosList;
    }

    public void setCultivosList(List<Cultivos> cultivosList) {
        this.cultivosList = cultivosList;
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
            cultivos = cultivosController.getCultivos();
            selected = cultivosFacade.find(cultivos.getNombrecultivo());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                cultivosController.setEncontrado(false);
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
        cultivosList = new ArrayList<>();
        return null;
    }

    @Override
    public void iniciar() {
        cultivosList = cultivosFacade.getCultivosList();
    }

    @Override
    public void iniciar(String value) {
//        cultivosList = cultivosFacade.findByActivo(value);
    }

    public String buscarEmail() {
        try {

            cultivos.setNombrecientifico("");
            cultivosList = cultivosFacade.findByNombrecultivo(cultivos.getNombrecultivo());
          
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

            cultivosList = cultivosFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresar() {
        try {

            cultivosController.setEncontrado(true);
            cultivosController.setCultivos(selected);
cultivosController.setNuevoregistro(false);
cultivosController.setDesactivar(false);
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

            cultivosController.setCultivos(new Cultivos());
            cultivosController.setEncontrado(false);

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

    @Override
    public List<Cultivos> getItems() {
        try {
            if (items == null) {
              items = cultivosFacade.findAll();
            
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }

    @Override
    public List<Cultivos> getItemsEntity() {
        return itemsEntity;
    }

    @Override
    public String changeItems() {
        try {
            itemsEntity = cultivosFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public List<Cultivos> getItemsCollection() {
        try {
//            itemsCollection = new ArrayList(cultivos.getIdgrupousuario().getCultivosCollection());

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
            cultivosFacade.remove(selected);
        cultivosList.remove(selected);
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

