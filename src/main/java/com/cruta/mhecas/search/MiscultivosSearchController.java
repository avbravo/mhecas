/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;


import com.cruta.mhecas.Miscultivos;
import com.cruta.mhecas.controller.MiscultivosController;
import com.cruta.mhecas.ejb.MiscultivosFacade;
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
@Named(value = "miscultivosSearchController")
@ViewScoped
public class MiscultivosSearchController implements Serializable, ISearchController {

    @Inject
    MiscultivosFacade miscultivosFacade;
    @Inject
    ResourcesFiles rf;
    @Inject
    MiscultivosController miscultivosController;
    Miscultivos miscultivos = new Miscultivos();
    Miscultivos selected = new Miscultivos();
    private List<Miscultivos> filtered;
    List<Miscultivos> miscultivosList;
    private List<Miscultivos> items, itemsEntity, itemsCollection;
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
    public Miscultivos getMiscultivos() {
        return miscultivos;
    }

    public void setMiscultivos(Miscultivos miscultivos) {
        this.miscultivos = miscultivos;
    }

    public List<Miscultivos> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Miscultivos> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of MiscultivosDataController
     */
    public Miscultivos getSelected() {
        return selected;
    }

    public void setSelected(Miscultivos selected) {
        this.selected = selected;
    }

    public MiscultivosSearchController() {
        miscultivosList = new ArrayList<>();
    }

    public List<Miscultivos> getMiscultivosList() {
        return miscultivosList;
    }

    public void setMiscultivosList(List<Miscultivos> miscultivosList) {
        this.miscultivosList = miscultivosList;
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
            miscultivos = miscultivosController.getMiscultivos();
            selected = miscultivosFacade.find(miscultivos.getIdmiscultivos());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                miscultivosController.setEncontrado(false);
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
        miscultivosList = new ArrayList<>();
        return null;
    }

    @Override
    public void iniciar() {
        miscultivosList = miscultivosFacade.getMiscultivosList();
    }

    @Override
    public void iniciar(String value) {
//        miscultivosList = miscultivosFacade.findByActivo(value);
    }

    public String buscarIdmiscultivos() {
        try {

   
            miscultivosList = miscultivosFacade.findByIdmiscultivos(miscultivos.getIdmiscultivos());
          
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

            miscultivosList = miscultivosFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresar() {
        try {

            miscultivosController.setEncontrado(true);
            miscultivosController.setMiscultivos(selected);
miscultivosController.setNuevoregistro(false);
miscultivosController.setDesactivar(false);
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

            miscultivosController.setMiscultivos(new Miscultivos());
            miscultivosController.setEncontrado(false);

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

    @Override
    public List<Miscultivos> getItems() {
        try {
            if (items == null) {
              items = miscultivosFacade.findAll();
            
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }

    @Override
    public List<Miscultivos> getItemsEntity() {
        return itemsEntity;
    }

    @Override
    public String changeItems() {
        try {
            itemsEntity = miscultivosFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public List<Miscultivos> getItemsCollection() {
        try {
//            itemsCollection = new ArrayList(miscultivos.getIdgrupousuario().getMiscultivosCollection());

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
            miscultivosFacade.remove(selected);
        miscultivosList.remove(selected);
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

