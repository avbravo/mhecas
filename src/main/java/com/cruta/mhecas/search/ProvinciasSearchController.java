/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;



import com.cruta.mhecas.Provincias;
import com.cruta.mhecas.controller.ProvinciasController;
import com.cruta.mhecas.ejb.ProvinciasFacade;
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
public class ProvinciasSearchController implements Serializable, ISearchController {

    @Inject
    ProvinciasFacade provinciasFacade;
    @Inject
    ResourcesFiles rf;
    @Inject
    ProvinciasController provinciasController;
    Provincias provincias = new Provincias();
    Provincias selected = new Provincias();
    private List<Provincias> filtered;
    List<Provincias> provinciasList;
    private List<Provincias> items, itemsEntity, itemsCollection;
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
    public Provincias getProvincias() {
        return provincias;
    }

    public void setProvincias(Provincias provincias) {
        this.provincias = provincias;
    }

    public List<Provincias> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Provincias> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of ProvinciasDataController
     */
    public Provincias getSelected() {
        return selected;
    }

    public void setSelected(Provincias selected) {
        this.selected = selected;
    }

    public ProvinciasSearchController() {
        provinciasList = new ArrayList<>();
    }

    public List<Provincias> getProvinciasList() {
        return provinciasList;
    }

    public void setProvinciasList(List<Provincias> provinciasList) {
        this.provinciasList = provinciasList;
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
        provinciasList = new ArrayList<>();
        return null;
    }

    @Override
    public void iniciar() {
        provinciasList = provinciasFacade.getProvinciasList();
    }

    @Override
    public void iniciar(String value) {
        provinciasList = provinciasFacade.findByActivo(value);
    }

    public String buscarIdProvincia() {
        try {
            provincias.setIdprovincia(0);
            provinciasList = provinciasFacade.findByIdProvincia(provincias.getIdprovincia().toString());

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }

        return "";
    }

    public String buscarProvincia() {
        try {
            provincias.setProvincia("");
            if (!usarlike) {
                provinciasList = provinciasFacade.findByProvincia(provincias.getProvincia());
            } else {
                provinciasList = provinciasFacade.findByProvincia(provincias.getProvincia());
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String showAll() {
        try {

            provinciasList = provinciasFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresar() {
        try {

            provinciasController.setEncontrado(true);
            provinciasController.setProvincias(selected);
            provinciasController.setNuevoregistro(false);
            provinciasController.setNuevoregistro(false);
            provinciasController.setDesactivar(false);
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

            provinciasController.setProvincias(new Provincias());
            provinciasController.setEncontrado(false);

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

    @Override
    public List<Provincias> getItems() {
        try {
            if (items == null) {
                items = provinciasFacade.findAll();
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }

    @Override
    public List<Provincias> getItemsCollection() {
        try {
            itemsCollection = new ArrayList(provincias.getMunicipiosCollection());
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }

        return itemsCollection;
    }

    @Override
    public String load() {
        try {
            provincias = provinciasController.getProvincias();
            selected = provinciasFacade.find(provincias.getIdprovincia());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                provinciasController.setEncontrado(false);
            } else {
                regresar();
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage("load()" + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String changeItems() {
        try {
            items = provinciasFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public List getItemsEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String changeItemsInverso() {
        try {
            items = provinciasFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return "";
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
            provinciasFacade.remove(selected);
            provinciasList.remove(selected);
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
