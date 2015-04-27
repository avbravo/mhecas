/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;


import com.cruta.mhecas.Alertas;
import com.cruta.mhecas.controller.AlertasController;
import com.cruta.mhecas.ejb.AlertasFacade;
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
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author avbravo
 */
@Named(value = "alertasSearchController")
@ViewScoped
public class AlertasSearchController implements Serializable, ISearchController {

    @Inject
    AlertasFacade alertasFacade;
    @Inject
    ResourcesFiles rf;
    @Inject
    AlertasController alertasController;
    Alertas alertas = new Alertas();
    Alertas selected = new Alertas();
    private List<Alertas> filtered;
    List<Alertas> alertasList;
    private List<Alertas> items, itemsEntity, itemsCollection;
    private Boolean usarlike = false;
    @Inject
    GestorImpresion gestorImpresion;
 private String page_call = "";
 
    private MapModel simpleModel;

    public String getPage_call() {
        return page_call;
    }

    public void setPage_call(String page_call) {
        this.page_call = page_call;
    }
    public Alertas getAlertas() {
        return alertas;
    }

    public void setAlertas(Alertas alertas) {
        this.alertas = alertas;
    }

    public List<Alertas> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Alertas> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of AlertasDataController
     */
    public Alertas getSelected() {
        return selected;
    }

    public void setSelected(Alertas selected) {
        this.selected = selected;
    }

    public AlertasSearchController() {
        alertasList = new ArrayList<>();
    }

    public List<Alertas> getAlertasList() {
        System.out.println("Invocado" + JSFUtil.getHoraActual());
        System.out.println("registros "+alertasList.size());
        return alertasList;
    }

    public void setAlertasList(List<Alertas> alertasList) {
        this.alertasList = alertasList;
    }

    public Boolean getUsarlike() {
        return usarlike;
    }

    public void setUsarlike(Boolean usarlike) {
        this.usarlike = usarlike;
    }

    @PostConstruct
    public void init() {
    simpleModel = new DefaultMapModel();
iniciar();
    }

    @Override
    public String load() {
        try {
            alertas = alertasController.getAlertas();
            selected = alertasFacade.find(alertas.getNombrecultivo());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                alertasController.setEncontrado(false);
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
        alertasList = new ArrayList<>();
        return null;
    }

    @Override
    public void iniciar() {
        //alertasList = alertasFacade.getAlertasList();
        alertasList = alertasFacade.getAlertasOrdenados();
    }

    @Override
    public void iniciar(String value) {
//        alertasList = alertasFacade.findByActivo(value);
    }

    public String buscarEmail() {
        try {

            
            alertasList = alertasFacade.findByIdalerta(alertas.getIdalerta());
          
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

            alertasList = alertasFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresar() {
        try {

            alertasController.setEncontrado(true);
            alertasController.setAlertas(selected);
alertasController.setNuevoregistro(false);
alertasController.setDesactivar(false);
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

            alertasController.setAlertas(new Alertas());
            alertasController.setEncontrado(false);

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

    @Override
    public List<Alertas> getItems() {
        try {
            if (items == null) {
              items = alertasFacade.findAll();
            
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }

    @Override
    public List<Alertas> getItemsEntity() {
        return itemsEntity;
    }

    @Override
    public String changeItems() {
        try {
            itemsEntity = alertasFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public List<Alertas> getItemsCollection() {
        try {
//            itemsCollection = new ArrayList(alertas.getIdgrupousuario().getAlertasCollection());

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
            alertasFacade.remove(selected);
        alertasList.remove(selected);
        } catch (Exception e) {
              JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void onCellEdit(CellEditEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MapModel getSimpleModel() {
        //Shared coordinates
        LatLng coord1 = new LatLng(8.44218663317722, -80.25163656100631);
//                LatLng coord1 = new LatLng(alertas.getLatitud(),Integer.parseInt( alertas.getLongitud()));

          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Panama"));

        return simpleModel;
    }
    public MapModel getSimpleModel2() {
        //Shared coordinates
 LatLng coord1 = new LatLng(8.44218663317722, -80.25163656100631);
//              LatLng coord1 = new LatLng(alertas.getLatitud(),Integer.parseInt( alertas.getLongitud()));

          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Panama"));

        return simpleModel;
    }
   
    
}

