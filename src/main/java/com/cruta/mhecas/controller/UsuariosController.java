/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cruta.mhecas.controller;


import com.cruta.mhecas.Grupousuarios;
import com.cruta.mhecas.Usuarios;
import com.cruta.mhecas.ejb.GrupousuariosFacade;
import com.cruta.mhecas.ejb.UsuariosFacade;
import com.cruta.mhecas.generales.GestorImpresion;
import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.LoginBean;
import com.cruta.mhecas.generales.ResourcesFiles;
import com.cruta.mhecas.interfaces.IController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;



/**
 *
 * @author avbravo
 */
@Named
@SessionScoped
public class UsuariosController implements Serializable, IController {

    private static final long serialVersionUID = 1L;
@Inject
GrupousuariosFacade grupousuariosFacade;

    @Inject
    UsuariosFacade usuariosFacade;
    Usuarios usuarios = new Usuarios();
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

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance of UsuariosController
     */
    public UsuariosController() {
    }

    @PostConstruct
    public void init() {   desactivar =true;
        nuevoregistro = false; 

    }

    @Override
    public String buscar() {
        usuarios = usuariosFacade.find(usuarios.getEmail());
        if(usuarios == null){
            encontrado=false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            usuarios = new Usuarios();
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
            usuarios = new Usuarios();
            usuarios.setNombre("");
            usuarios.setPassword("");
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }
    @Override
    public String save() {
        try {
            
            List<Grupousuarios> list = grupousuariosFacade.findByGrupousuarios("agricultor");
            if(list == null || list.isEmpty()){
                JSFUtil.addWarningMessage("No existe un grupo de usuario agricultores");
                return null;
            }
            usuarios.setIdgrupousuario(list.get(0));
            if (usuariosFacade.find(usuarios.getEmail()) != null) {
                JSFUtil.warningDialog(rf.getMensajeArb("info.message"), rf.getMensajeArb("warning.idexist"));
                return null;
            }
            usuariosFacade.create(usuarios);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.save"));
            usuarios = new Usuarios();
            this.nuevoregistro = false;
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {
            
           usuariosFacade.edit(usuarios);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String delete() {
        try {
            usuariosFacade.remove(usuarios);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));
            encontrado = false;
            usuarios = new Usuarios();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

  
   

    @Override
    public String imprimir() {
        try {
            List<Usuarios> list = new ArrayList<>();
            list.add(usuarios);
            String ruta = "/resources/reportes/usuarios/usuarios.jasper";
            HashMap parameters = new HashMap();

            gestorImpresion.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimirTodos() {
        String ruta = "/resources/reportes/usuarios/usuarios.jasper";
        HashMap parameters = new HashMap();
        gestorImpresion.imprimir(usuariosFacade.getUsuariosList(), ruta, parameters);
        return null;
    }

   
    @Override
     public Integer contador(){
        return usuariosFacade.count();
    }




    @Override
    public String elementoSeleccionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String habilitarConsultar() {        desactivar=true; 
        usuarios.setEmail(
                ""); 
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
            String destination = JSFUtil.getPathFotosUsuarios();
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
                    usuarios.setFoto(nuevoNombreLogo);
                    List<Usuarios> list = usuariosFacade.findByFoto(nuevoNombreLogo);
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
