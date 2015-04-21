/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author avbravo
 */
@Named(value = "draggableMarkersView")
@ViewScoped
public class DraggableMarkersView implements Serializable {
  
    private MapModel draggableModel;
     
    private Marker marker;
     
    @PostConstruct
    public void init() {
        draggableModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(36.879466, 30.667648);
        LatLng coord2 = new LatLng(36.883707, 30.689216);
        LatLng coord3 = new LatLng(36.879703, 30.706707);
        LatLng coord4 = new LatLng(36.885233, 30.702323);
          
        //Draggable
        draggableModel.addOverlay(new Marker(coord1, "Konyaalti"));
        draggableModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
        draggableModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
        draggableModel.addOverlay(new Marker(coord4, "Kaleici"));
          
        for(Marker premarker : draggableModel.getMarkers()) {
            premarker.setDraggable(true);
        }
    }
      
    public MapModel getDraggableModel() {
        return draggableModel;
    }
      
    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }
     }