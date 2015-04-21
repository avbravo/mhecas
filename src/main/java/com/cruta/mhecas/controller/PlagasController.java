/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.controller;


import com.cruta.mhecas.Plagas;
import com.cruta.mhecas.ejb.PlagasFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.LoginBean;
import com.cruta.mhecas.generales.ResourcesFiles;
import com.cruta.mhecas.interfaces.IController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author avbravo
 */
@Named(value = "plagasController")
@SessionScoped
public class PlagasController implements Serializable, IController {

    private static final long serialVersionUID = 1L;

    @Inject
    PlagasFacade plagasFacade;
    Plagas plagas = new Plagas();
    private Boolean encontrado = false;
    @Inject
    ResourcesFiles rf;
    @Inject
    GestorImpresion gestorImpresion;
    @Inject
    LoginBean loginBean;
 private Boolean nuevoregistro = false; 
  Boolean desactivar =true;
private String imagenURL ="/resources/fotos/foto.png";
private String imagen="foto.png";



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

    public Plagas getPlagas() {
        return plagas;
    }

    public void setPlagas(Plagas plagas) {
        this.plagas = plagas;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance of PlagasController
     */
    public PlagasController() {
    }

    @PostConstruct
    public void init() {   desactivar =true;
        nuevoregistro = false; 

    }

    @Override
    public String buscar() {
        plagas = plagasFacade.find(plagas.getNombreplaga());
        if(plagas == null){
            encontrado=false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            plagas = new Plagas();
        }
        else{
            encontrado = true;
        }
        return "";
    }

    @Override
    public String prepararNew(){  desactivar = false;
        try {
            nuevoregistro = true; 

            encontrado = false;
            plagas = new Plagas();
        
        
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }
    @Override
    public String save() {
        try {
            
           
          
            plagasFacade.create(plagas);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.save"));
            plagas = new Plagas();
            this.nuevoregistro = false;
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {
            
           plagasFacade.edit(plagas);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String delete() {
        try {
            plagasFacade.remove(plagas);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));
            encontrado = false;
            plagas = new Plagas();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

  
   

    @Override
    public String imprimir() {
        try {
            List<Plagas> list = new ArrayList<>();
            list.add(plagas);
            String ruta = "/resources/reportes/plagas/plagas.jasper";
            HashMap parameters = new HashMap();

            gestorImpresion.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimirTodos() {
        String ruta = "/resources/reportes/plagas/plagas.jasper";
        HashMap parameters = new HashMap();
        gestorImpresion.imprimir(plagasFacade.getPlagasList(), ruta, parameters);
        return null;
    }

   
    @Override
     public Integer contador(){
        return plagasFacade.count();
    }



    @Override
    public String elementoSeleccionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String habilitarConsultar() {        desactivar=true; 
        plagas.setNombreplaga(""); 
        this.nuevoregistro = false; 
        return ""; 
    } 

    @Override
    public Integer getIdSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      public void handleFileUpload(FileUploadEvent event) {
        try {

            UploadedFile file = event.getFile();
//application code
            String destination = JSFUtil.getPathFotosPlagas();
            if (destination == null) {
                JSFUtil.addErrorMessage(rf.getMensajeArb("warning.noseobtuvopath"));
            } else {
                Boolean continuarGenerado = true;
                /*
                 verifica que no exista una imagen con ese nombre
                 */
                String nuevoNombreLogo = "";
                while (continuarGenerado) {
                    nuevoNombreLogo = JSFUtil.getUUID() + JSFUtil.getExtension(file.getFileName());
                    plagas.setFoto(nuevoNombreLogo);
                    List<Plagas> list = plagasFacade.findByFoto(nuevoNombreLogo);
                    if (list == null || list.isEmpty()) {
                        continuarGenerado = false;
                    }

                }

                if (JSFUtil.copyFile(nuevoNombreLogo, file.getInputstream(), destination)) {

                }
            }

        } catch (Exception e) {
            JSFUtil.addErrorMessage("handleFileUpload()" + e.getLocalizedMessage());
        }

    }
}
