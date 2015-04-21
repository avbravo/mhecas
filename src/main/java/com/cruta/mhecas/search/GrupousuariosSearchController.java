/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;



import com.cruta.mhecas.Grupousuarios;
import com.cruta.mhecas.controller.GrupousuariosController;
import com.cruta.mhecas.ejb.GrupousuariosFacade;
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
public class GrupousuariosSearchController implements Serializable,ISearchController{

    @Inject
    GrupousuariosFacade grupousuariosFacade;
    @Inject
    ResourcesFiles rf;
    @Inject
    GrupousuariosController grupousuariosController;
    Grupousuarios grupousuarios = new Grupousuarios();
    Grupousuarios selected = new Grupousuarios();
    private List<Grupousuarios> filtered;
    List<Grupousuarios> grupousuariosList;
    private List<Grupousuarios> items,  itemsEntity,itemsCollection;
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
    public Grupousuarios getGrupousuarios() {
        return grupousuarios;
    }

    public void setGrupousuarios(Grupousuarios grupousuarios) {
        this.grupousuarios = grupousuarios;
    }

    public List<Grupousuarios> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Grupousuarios> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of GrupousuariosDataController
     */
    public Grupousuarios getSelected() {
        return selected;
    }

    public void setSelected(Grupousuarios selected) {
        this.selected = selected;
    }

    public GrupousuariosSearchController() {
        grupousuariosList = new ArrayList<>();
    }

    public List<Grupousuarios> getGrupousuariosList() {
        return grupousuariosList;
    }

    public void setGrupousuariosList(List<Grupousuarios> grupousuariosList) {
        this.grupousuariosList = grupousuariosList;
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
    public String load() {
        try {
            grupousuarios = grupousuariosController.getGrupousuarios();
            selected = grupousuariosFacade.find(grupousuarios.getIdgrupousuario());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                grupousuariosController.setEncontrado(false);
            } else {
                    regresar();
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage("load()" + e.getLocalizedMessage());
        }
        return null;
    }
    @Override
    public String clear(){
    grupousuariosList = new ArrayList<>();
        return null;
}
    @Override
    public void iniciar() {
        grupousuariosList = grupousuariosFacade.getGrupousuariosList();
    }

    @Override
    public void iniciar(String value) {
        grupousuariosList = grupousuariosFacade.findByActivo(value);
    }

    public String buscarIdGrupousuarios() {
        try {
            grupousuarios.setGrupousuario("");
            grupousuariosList = grupousuariosFacade.findByIdGrupousuarios(grupousuarios.getIdgrupousuario());

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }

        return "";
    }

    public String buscarNombre() {
        try {
              grupousuarios.setIdgrupousuario("");
        
                grupousuariosList = grupousuariosFacade.findByGrupousuarios(grupousuarios.getGrupousuario());
           
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    public String buscarActivo() {
        try {
  
            grupousuarios.setIdgrupousuario("");
            grupousuarios.setGrupousuario("");
            grupousuariosList = grupousuariosFacade.findByActivo(grupousuarios.getActivo());
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String showAll() {
        try { 
  
            grupousuariosList = grupousuariosFacade.findAll();
           
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
     public String delete(){
        try {
           grupousuariosFacade.remove(selected);
           grupousuariosList.remove(selected);
           
        } catch (Exception e) {
              JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }
    @Override
    public String regresar() {
        try {
      
            grupousuariosController.setEncontrado(true);
            grupousuariosController.setGrupousuarios(selected);
           grupousuariosController.setNuevoregistro(false);
             grupousuariosController.setDesactivar(false);
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
         
            grupousuariosController.setGrupousuarios(new Grupousuarios());
            grupousuariosController.setEncontrado(false);
           
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

    

    @Override
    public List<Grupousuarios> getItems() {
        try {
            if (items == null) {
                //items = grupousuariosFacade.findAll();
                items = grupousuariosFacade.findByActivo("si");
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }
    @Override
     public String changeItems() {
        try {
            items = grupousuariosFacade.findByActivo("si");

        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return "";
    }
    @Override
     public List<Grupousuarios> getItemsCollection() {
        try {
            itemsCollection = new ArrayList(grupousuarios.getUsuariosCollection());

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
     public List<Grupousuarios> getItemsEntity() {
        return itemsEntity;
    }

    @Override
     public String listar(){
        showAll();
        imprimirTodos();
        return null;
    }

    @Override
    public void onCellEdit(CellEditEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
