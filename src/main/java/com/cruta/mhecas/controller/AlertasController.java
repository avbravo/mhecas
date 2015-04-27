/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.controller;

import com.cruta.mhecas.Alertas;
import com.cruta.mhecas.Cultivos;
import com.cruta.mhecas.Plagas;
import com.cruta.mhecas.ejb.AlertasFacade;
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
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.LatLng;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author avbravo
 */
@Named(value = "alertasController")
@SessionScoped
public class AlertasController implements Serializable, IController {

    private static final long serialVersionUID = 1L;

    @Inject
    AlertasFacade alertasFacade;
    Alertas alertas = new Alertas();
    private Boolean encontrado = false;
    @Inject
    ResourcesFiles rf;
    @Inject
    GestorImpresion gestorImpresion;
    @Inject
    LoginBean loginBean;
    private Boolean nuevoregistro = false;
    Boolean desactivar = true;
    private String imagenURL = "/resources/fotos/foto.png";
    private String imagen = "foto.png";
    Cultivos cultivos = new Cultivos();
    Plagas plagas = new Plagas();
    private UploadedFile file;

    private Boolean bflor;
    private Boolean btallo;
    private Boolean bhoja;
    private Boolean bfruto;
    private Boolean braices;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Boolean getBflor() {
        return bflor;
    }

    public void setBflor(Boolean bflor) {
        this.bflor = bflor;
    }

    public Boolean getBtallo() {
        return btallo;
    }

    public void setBtallo(Boolean btallo) {
        this.btallo = btallo;
    }

    public Boolean getBhoja() {
        return bhoja;
    }

    public void setBhoja(Boolean bhoja) {
        this.bhoja = bhoja;
    }

    public Boolean getBfruto() {
        return bfruto;
    }

    public void setBfruto(Boolean bfruto) {
        this.bfruto = bfruto;
    }

    public Boolean getBraices() {
        return braices;
    }

    public void setBraices(Boolean braices) {
        this.braices = braices;
    }

    public Plagas getPlagas() {
        return plagas;
    }

    public void setPlagas(Plagas plagas) {
        this.plagas = plagas;
    }

    public Cultivos getCultivos() {
        return cultivos;
    }

    public void setCultivos(Cultivos cultivos) {
        this.cultivos = cultivos;
    }

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

    public Alertas getAlertas() {
        return alertas;
    }

    public void setAlertas(Alertas alertas) {
        this.alertas = alertas;
    }

    public Boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * Creates a new instance of AlertasController
     */
    public AlertasController() {
    }

    @PostConstruct
    public void init() {
        desactivar = true;
        nuevoregistro = false;

    }

    @Override
    public String buscar() {
        alertas = alertasFacade.find(alertas.getNombrecultivo());
        if (alertas == null) {
            encontrado = false;
            JSFUtil.addWarningMessage(rf.getMensajeArb("warning.noexiste"));
            alertas = new Alertas();
        } else {
            encontrado = true;
        }
        return "";
    }

    @Override
    public String prepararNew() {
        desactivar = false;
        try {
            nuevoregistro = true;

            encontrado = false;
            alertas = new Alertas();
            alertas.setFecha(JSFUtil.getFechaActual());

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * calcularGradoAfectacion()
     *
     * @return
     */
    public String calcularGradoAfectacion() {
        try {
            if (alertas.getPorcentajeafectacion() >= 50) {
                alertas.setGradoafectacion("Alto");

            } else {
                if (alertas.getPorcentajeafectacion() >= 21) {
                    alertas.setGradoafectacion("Medio");

                } else {
                    alertas.setGradoafectacion("Bajo");
                }
            }

        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String save() {
        try {
            System.out.println("Guardar");
            alertas.setTallo(btallo ? "si" : "no");
            alertas.setHoja(bhoja ? "si" : "no");
            alertas.setFruto(bfruto ? "si" : "no");
            alertas.setRaices(braices ? "si" : "no");
            alertas.setIdalerta(alertasFacade.getMaximo() + 1);
            calcularGradoAfectacion();

            alertas.setPuntos(0);
            alertas.setNombrecultivo(cultivos.getNombrecultivo());
            alertas.setNombreplaga(plagas.getNombreplaga());
            if(alertas.getFlor() == null || alertas.getFoto().equals("")){
                alertas.setFlor("");
            }
            alertasFacade.create(alertas);
            JSFUtil.addSuccessMessage("Guardado exitosamente");
            alertas = new Alertas();
            this.nuevoregistro = false;
            notificarPUSH();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String edit() {
        try {

            alertasFacade.edit(alertas);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return "";
    }

    @Override
    public String delete() {
        try {
            alertasFacade.remove(alertas);
            JSFUtil.addSuccessMessage(rf.getMensajeArb("info.delete"));
            encontrado = false;
            alertas = new Alertas();
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimir() {
        try {
            List<Alertas> list = new ArrayList<>();
            list.add(alertas);
            String ruta = "/resources/reportes/alertas/alertas.jasper";
            HashMap parameters = new HashMap();

            gestorImpresion.imprimir(list, ruta, parameters);
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("imprimir() " + ex.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public String imprimirTodos() {
        String ruta = "/resources/reportes/alertas/alertas.jasper";
        HashMap parameters = new HashMap();
        gestorImpresion.imprimir(alertasFacade.getAlertasList(), ruta, parameters);
        return null;
    }

    @Override
    public Integer contador() {
        return alertasFacade.count();
    }

    @Override
    public String elementoSeleccionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String habilitarConsultar() {
        desactivar = true;
        alertas.setNombrecultivo("");
        this.nuevoregistro = false;
        return "";
    }

    @Override
    public Integer getIdSiguiente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onPointSelect(PointSelectEvent event) {
        System.out.println("onPointSelect");
        try {
            LatLng latlng = event.getLatLng();
            alertas.setLatitud(latlng.getLat());

            alertas.setLongitud(String.valueOf(latlng.getLng()));
//            System.out.println(";atitud "+ alertas.getLatitud() + "longitud: " +alertas.getLongitud());
//            JSFUtil.addWarningMessage(";atitud "+ alertas.getLatitud() + "longitud: " +alertas.getLongitud());
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("Error " + ex.getLocalizedMessage());
        }

        // addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
    }

//    public void notificarPUSH() {
//
//        String summary = "Nuevo Elemento";
//        String detail = "Se agrego a la lista";
//        String CHANNEL = "/notify";
//
//        EventBus eventBus = EventBusFactory.getDefault().eventBus();
//        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail)));
//    }
    public void handleFileUpload(FileUploadEvent event) {
        try {

            //   UploadedFile file = event.getFile();
            System.out.println(" file " + file.getFileName());
//application code
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

                }
            }

        } catch (Exception e) {
            JSFUtil.addErrorMessage("handleFileUpload()" + e.getLocalizedMessage());
        }

    }
    
    public void notificarPUSH() {
        try {
            String summary = "Nuevo Elemento";
        String detail = "Se agrego a la lista";
        String CHANNEL = "/notify";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail)));
            System.out.println("notificarPUSH()");
        } catch (Exception e) {
             JSFUtil.addErrorMessage("notificarPUSH()" + e.getLocalizedMessage());
        }
        
    }
}
