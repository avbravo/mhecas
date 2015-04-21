/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.controller;



import com.cruta.mhecas.Cultivos;
import com.cruta.mhecas.ejb.CultivosFacade;
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
@Named(value = "cultivosController")
@SessionScoped
public class CultivosController implements Serializable, IController {

    private static final long serialVersionUID = 1L;

    @Inject
    CultivosFacade cultivosFacade;
    Cultivos cultivos = new Cultivos();
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

    public Cultivos getCultivos() {
        return cultivos;
    }

    public void setCultivos(Cultivos cultivos) {
        this.cultivos = cultivos;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance of CultivosController
     */
    public CultivosController() {
    }

    @PostConstruct
    public void init() {   desactivar =true;
        nuevoregistro = false; 

    }

    @Override
    public String buscar() {
        cultivos = cultivosFacade.find(cultivos.getNombrecultivo());
        if(cultivos == null){
            encontrado=false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            cultivos = new Cultivos();
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
            cultivos = new Cultivos();
        
        
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }
    @Override
    public String save() {
        try {
            
           
          
            cultivosFacade.create(cultivos);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.save"));
            cultivos = new Cultivos();
            this.nuevoregistro = false;
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {
            
           cultivosFacade.edit(cultivos);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String delete() {
        try {
            cultivosFacade.remove(cultivos);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));
            encontrado = false;
            cultivos = new Cultivos();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

  
   

    @Override
    public String imprimir() {
        try {
            List<Cultivos> list = new ArrayList<>();
            list.add(cultivos);
            String ruta = "/resources/reportes/cultivos/cultivos.jasper";
            HashMap parameters = new HashMap();

            gestorImpresion.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimirTodos() {
        String ruta = "/resources/reportes/cultivos/cultivos.jasper";
        HashMap parameters = new HashMap();
        gestorImpresion.imprimir(cultivosFacade.getCultivosList(), ruta, parameters);
        return null;
    }

   
    @Override
     public Integer contador(){
        return cultivosFacade.count();
    }



    @Override
    public String elementoSeleccionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String habilitarConsultar() {        desactivar=true; 
        cultivos.setNombrecultivo(""); 
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
            String destination = JSFUtil.getPathFotosCultivos();
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
                    cultivos.setFoto(nuevoNombreLogo);
                    List<Cultivos> list = cultivosFacade.findByFoto(nuevoNombreLogo);
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
