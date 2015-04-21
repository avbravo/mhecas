/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.search;



import com.cruta.mhecas.Usuarios;
import com.cruta.mhecas.controller.UsuariosController;
import com.cruta.mhecas.ejb.UsuariosFacade;
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
public class UsuariosSearchController implements Serializable, ISearchController {

    @Inject
    UsuariosFacade usuariosFacade;
    @Inject
    ResourcesFiles rf;
    @Inject
    UsuariosController usuariosController;
    Usuarios usuarios = new Usuarios();
    Usuarios selected = new Usuarios();
    private List<Usuarios> filtered;
    List<Usuarios> usuariosList;
    private List<Usuarios> items, itemsEntity, itemsCollection;
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
    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Usuarios> filtered) {
        this.filtered = filtered;
    }

    /**
     * Creates a new instance of UsuariosDataController
     */
    public Usuarios getSelected() {
        return selected;
    }

    public void setSelected(Usuarios selected) {
        this.selected = selected;
    }

    public UsuariosSearchController() {
        usuariosList = new ArrayList<>();
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
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
            usuarios = usuariosController.getUsuarios();
            selected = usuariosFacade.find(usuarios.getEmail());
            if (selected == null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.noexiste"));
                usuariosController.setEncontrado(false);
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
        usuariosList = new ArrayList<>();
        return null;
    }

    @Override
    public void iniciar() {
        usuariosList = usuariosFacade.getUsuariosList();
    }

    @Override
    public void iniciar(String value) {
//        usuariosList = usuariosFacade.findByActivo(value);
    }

    public String buscarEmail() {
        try {

            usuarios.setNombre("");
            usuariosList = usuariosFacade.findByEmail(usuarios.getEmail());
          
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }

        return "";
    }

  

    public String buscarNombre() {
        try {

            usuarios.setEmail("");
            if (!usarlike) {
                usuariosList = usuariosFacade.findByNombre(usuarios.getNombre());
            } else {
                usuariosList = usuariosFacade.findByNombreLike(usuarios.getNombre());
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

  
    /*
     buscar por el idnivel1 para filtrar por el entity relacionado
     */

    public String buscarIdgrupousuario() {
        try {
            usuarios.setEmail("");
            usuarios.setNombre("");
            usuariosList = usuariosFacade.findByIdgrupousuario(usuarios.getIdgrupousuario());
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String showAll() {
        try {

            usuariosList = usuariosFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String regresar() {
        try {

            usuariosController.setEncontrado(true);
            usuariosController.setUsuarios(selected);
usuariosController.setNuevoregistro(false);
usuariosController.setDesactivar(false);
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

            usuariosController.setUsuarios(new Usuarios());
            usuariosController.setEncontrado(false);

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";

    }

    @Override
    public List<Usuarios> getItems() {
        try {
            if (items == null) {
              items = usuariosFacade.findAll();
            
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return items;
    }

    @Override
    public List<Usuarios> getItemsEntity() {
        return itemsEntity;
    }

    @Override
    public String changeItems() {
        try {
            itemsEntity = usuariosFacade.findAll();

        } catch (Exception e) {
            JSFUtil.addErrorMessage("changeItems() " + e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public List<Usuarios> getItemsCollection() {
        try {
            itemsCollection = new ArrayList(usuarios.getIdgrupousuario().getUsuariosCollection());

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
            usuariosFacade.remove(selected);
        usuariosList.remove(selected);
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

