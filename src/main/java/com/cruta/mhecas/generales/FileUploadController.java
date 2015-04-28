/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import com.cruta.mhecas.Alertas;
import com.cruta.mhecas.ejb.AlertasFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
public class FileUploadController implements Serializable {

    private UploadedFile file;
    @Inject
    AlertasFacade alertasFacade;
    Alertas alertas = new Alertas();
  @Inject
    ResourcesFiles rf;
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        try {
            if (file != null) {
                FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

                String destination = JSFUtil.getPathFotosAlertas();
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
                        alertas.setFoto(nuevoNombreLogo);
                        List<Alertas> list = alertasFacade.findByFoto(nuevoNombreLogo);
                        if (list == null || list.isEmpty()) {
                            continuarGenerado = false;
                        }

                    }

                    if (JSFUtil.copyFile(nuevoNombreLogo, file.getInputstream(), destination)) {
 JSFUtil.addWarningMessage("se copio el archivo");
                    }else{
                        JSFUtil.addWarningMessage("no se pudo copiar el archivo");
                    }

                }
            } else {
                JSFUtil.addWarningMessage("no se selecciono un archivo");
            }
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("upload() " + ex.getLocalizedMessage());
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
