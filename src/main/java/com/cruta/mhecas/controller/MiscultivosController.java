/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.controller;



import com.cruta.mhecas.Miscultivos;
import com.cruta.mhecas.ejb.MiscultivosFacade;
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
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class MiscultivosController implements Serializable, IController {

    private static final long serialVersionUID = 1L;

    @Inject
    MiscultivosFacade miscultivosFacade;
    Miscultivos miscultivos = new Miscultivos();
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

    public Miscultivos getMiscultivos() {
        return miscultivos;
    }

    public void setMiscultivos(Miscultivos miscultivos) {
        this.miscultivos = miscultivos;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance of MiscultivosController
     */
    public MiscultivosController() {
    }

    @PostConstruct
    public void init() {   desactivar =true;
        nuevoregistro = false; 

    }

    @Override
    public String buscar() {
        miscultivos = miscultivosFacade.find(miscultivos.getIdmiscultivos());
        if(miscultivos == null){
            encontrado=false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            miscultivos = new Miscultivos();
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
            miscultivos = new Miscultivos();
        
        
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }
    @Override
    public String save() {
        try {
            
           
          miscultivos.setIdmiscultivos(miscultivosFacade.getMaximo()+1);
            miscultivosFacade.create(miscultivos);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.save"));
            miscultivos = new Miscultivos();
            this.nuevoregistro = false;
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {
            
           miscultivosFacade.edit(miscultivos);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String delete() {
        try {
            miscultivosFacade.remove(miscultivos);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));
            encontrado = false;
            miscultivos = new Miscultivos();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

  
   

    @Override
    public String imprimir() {
        try {
            List<Miscultivos> list = new ArrayList<>();
            list.add(miscultivos);
            String ruta = "/resources/reportes/miscultivos/miscultivos.jasper";
            HashMap parameters = new HashMap();

            gestorImpresion.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimirTodos() {
        String ruta = "/resources/reportes/miscultivos/miscultivos.jasper";
        HashMap parameters = new HashMap();
        gestorImpresion.imprimir(miscultivosFacade.getMiscultivosList(), ruta, parameters);
        return null;
    }

   
    @Override
     public Integer contador(){
        return miscultivosFacade.count();
    }



    @Override
    public String elementoSeleccionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String habilitarConsultar() {        desactivar=true; 
        miscultivos.setIdmiscultivos(Integer.MIN_VALUE); 
        this.nuevoregistro = false; 
        return ""; 
    } 

    @Override
    public Integer getIdSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//      public void handleFileUpload(FileUploadEvent event) {
//        try {
//
//            UploadedFile file = event.getFile();
////application code
//            String destination = JSFUtil.getPathFotosCultivos();
//            if (destination == null) {
//                JSFUtil.addErrorMessage(rf.getMensajeArb("warning.noseobtuvopath"));
//            } else {
//                Boolean continuarGenerado = true;
//                /*
//                 verifica que no exista una imagen con ese nombre
//                 */
//                String nuevoNombreLogo = "";
//                while (continuarGenerado) {
//                    nuevoNombreLogo = JSFUtil.getUUID() + JSFUtil.getExtension(file.getFileName());
//                    miscultivos.setFoto(nuevoNombreLogo);
//                    List<Miscultivos> list = miscultivosFacade.findByFoto(nuevoNombreLogo);
//                    if (list == null || list.isEmpty()) {
//                        continuarGenerado = false;
//                    }
//
//                }
//
//                if (JSFUtil.copyFile(nuevoNombreLogo, file.getInputstream(), destination)) {
//
//                }
//            }
//
//        } catch (Exception e) {
//            JSFUtil.addErrorMessage("handleFileUpload()" + e.getLocalizedMessage());
//        }
//
//    }


  public void onPointSelect(PointSelectEvent event) {
      System.out.println("onPointSelect");
        LatLng latlng = event.getLatLng();
        miscultivos.setLatitud(latlng.getLat());
        
                  miscultivos.setLongitud(latlng.getLng());
                  System.out.println("lat "+miscultivos.getLatitud());
                    System.out.println("long "+miscultivos.getLatitud());
       // addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
    }
      
}

