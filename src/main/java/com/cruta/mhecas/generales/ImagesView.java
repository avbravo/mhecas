/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
public class ImagesView implements Serializable{

    /**
     * Creates a new instance of ImagesView
     */
    public ImagesView() {
    }
    private List<String> images;
     
    @PostConstruct
    public void init() {
         images = new ArrayList<String>();
        for (int i = 1; i <= 3; i++) {
           images.add("galeria" + i + ".jpg");    
        }

    }
 
    public List<String> getImages() {
        return images;
    }
    
}
