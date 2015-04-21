/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;



import com.cruta.mhecas.Corregimientos;
import com.cruta.mhecas.controller.CorregimientosController;
import com.cruta.mhecas.ejb.CorregimientosFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.LoginBean;
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
public class CorregimientosSearchController implements Serializable, ISearchController {

    private static final long serialVersionUID = 1L;

    @Inject
    CorregimientosFacade corregimientosFacade;
    @Inject
    MunicipiosSearchController municipiosSearchController;
    @Inject
    ResourcesFiles rf;
    @Inject
    CorregimientosController corregimientosController;
    Corregimientos corregimientos = new Corregimientos();
    Corregimientos selected = new Corregimientos();
    private List<Corregimientos> filtered;
    List<Corregimientos> corregimientosList;
    private List<Corregimientos> items, itemsEntity, itemsFiltrados, itemsCollection;
    @Inject
    GestorImpresion gestorImpresion;
    @Inject
    LoginBean loginBean;
    private Boolean usarlike = false;

     private String page_call = "";

    public String getPage_call() {
        return page_call;
    }

    public void setPage_call(String page_call) {
        this.page_call = page_call;
    }
    public Corregimientos getCorregimientos() {
        return corregimientos;
    }

    public void setCorregimientos(Corregimientos corregimientos) {
        this.corregimientos = corregimientos;
    }

    public List<Corregimientos> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Corregimientos> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of CorregimientosDataController
     *
     * @return
     */
    public Corregimientos getSelected() {
        return selected;
    }

    public void setSelected(Corregimientos selected) {
        this.selected = selected;
    }

    public CorregimientosSearchController() {
        corregimientosList = new ArrayList<>();
    }

    public List<Corregimientos> getCorregimientosList() {
        return corregimientosList;
    }

    public void setCorregimientosList(List<Corregimientos> corregimientosList) {
        this.corregimientosList = corregimientosList;
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
        corregimientosList = new ArrayList<>();
        return null;
    }

    @Override
    public void iniciar() {
//        corregimientosList = corregimientosFacade.getCorregimientosList();
    }

    @Override
    public void iniciar(String value) {
//        corregimientosList = corregimientosFacade.find(value);
    }

    public Corregimientos buscarIdCorregimiento(Integer idcorregimiento) {
        try {
            corregimientos.setIdcorregimiento(idcorregimiento);
            corregimientosList = corregimientosFacade.findByIdCorregimiento(corregimientos.getIdcorregimiento());
            if (!corregimientosList.isEmpty()) {

                return corregimientosList.get(0);
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage("buscarIdCorregimiento() " + e.getLocalizedMessage());
        }
        return new Corregimientos();
    }

    public String buscarIdCorregimiento() {
        try {
            corregimientos.setIdcorregimiento(1);
            corregimientosList = corregimientosFacade.findByIdCorregimiento(corregimientos.getIdcorregimiento());
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    public String buscarCorregimiento() {
        try {
            corregimientos.setCorregimiento("");
            if (!usarlike) {
                corregimientosList = corregimientosFacade.findByCorregimiento(corregimientos.getCorregimiento());
            } else {
                corregimientosList = corregimientosFacade.findByNombreLike(corregimientos.getCorregimiento());
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String showAll() {
        try {
            corregimientosList = corregimientosFacade.findAll();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresar() {
        try {

            corregimientosController.setEncontrado(true);
            corregimientosController.setCorregimientos(selected);
            corregimientosController.setNuevoregistro(false);
            municipiosSearchController.setMunicipios(selected.getIdmunicipio());
            municipiosSearchController.changeItems();
            corregimientosController.setNuevoregistro(false);
             corregimientosController.setDesactivar(false);
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

            corregimientosController.setCorregimientos(new Corregimientos());
            corregimientosController.setEncontrado(false);

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

//    public String imprimirTodos() {
//        String ruta = "/resources/reportes/contribuyente/corregimientos.jasper";
//        HashMap parameters = new HashMap();
//        gestorImpresion.imprimir(corregimientosList, ruta, parameters);
//        return null;
//    }
    @Override
    public List<Corregimientos> getItems() {
        try {
            if (items == null) {
                items = corregimientosFacade.findAll();
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }

    @Override
    public List<Corregimientos> getItemsEntity() {
        return itemsEntity;
    }

    @Override
    public String changeItems() {
        try {
//            if (itemsEntity == null || itemsEntity.isEmpty()) {
//            }

            itemsEntity = corregimientosFacade.findByIdMunicipio(corregimientos.getIdmunicipio());
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    /*
     devuelve los corregimientos del municipio actual
     */
    public List<Corregimientos> getItemsFiltrados() {
        try {
            // if (itemsFiltrados == null) {
//            itemsFiltrados = corregimientosFacade.findByIdMunicipio(loginBean.getUsuarios().getIdmunicipio());
            //}
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return itemsFiltrados;
    }

    @Override
    public List<Corregimientos> getItemsCollection() {
        try {
//            itemsCollection = new ArrayList(corregimientos.getIdmunicipio().getUsuariosCollection());
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }

        return itemsCollection;
    }

    @Override
    public String load() {
        try {
            corregimientos = corregimientosController.getCorregimientos();
            selected = corregimientosFacade.find(corregimientos.getIdcorregimiento());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                corregimientosController.setEncontrado(false);
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

            itemsEntity = corregimientosFacade.findByIdMunicipio(corregimientos.getIdmunicipio());

            municipiosSearchController.setMunicipios(corregimientos.getIdmunicipio());

            municipiosSearchController.changeItemsInverso();

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
            corregimientosFacade.remove(selected);
            corregimientosList.remove(selected);
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
