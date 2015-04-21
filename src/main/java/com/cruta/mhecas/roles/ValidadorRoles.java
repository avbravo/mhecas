/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and f>open the template in the editor.
 */
package com.cruta.mhecas.roles;


import com.cruta.mhecas.generales.JSFUtil;
import com.cruta.mhecas.generales.ResourcesFiles;
import com.cruta.mhecas.menu.MenuBeans;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class ValidadorRoles {

    @Inject
    MenuBeans menuBeans;
    @Inject
    ResourcesFiles rf;
    @Inject
    RolAdministrador rolAdministrador;
   
    @Inject
    RolDesarrollador rolDesarrollador;
  

    /**
     * Creates a new instance of ValidadorRoles
     */
    public ValidadorRoles() {
    }

    public Boolean validarRoles(String rolvalidacion) {

        Boolean ok = Boolean.TRUE;
        try {
            switch (rolvalidacion) {
                case "administrador":
                    rolAdministrador.activar();
                    break;
              
                
            
                    case "desarrollador":
                    rolDesarrollador.activar();
                    break;
            
                default:
                    menuBeans.habilitarTodo(false);
                    ok = Boolean.FALSE;
                    JSFUtil.warningDialog(rf.getMensajeArb("warning.title"),
                            rf.getMensajeArb("info.sinrolasignado"));
            }
        } catch (Exception e) {
            JSFUtil.addErrorMessage("validarRoles() " + e.getLocalizedMessage());
        }
        return ok;
    }
}
