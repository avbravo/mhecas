/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;


import com.cruta.mhecas.Usuarios;
import com.cruta.mhecas.ejb.UsuariosFacade;
import com.cruta.mhecas.menu.MenuBeans;
import com.cruta.mhecas.roles.ValidadorRoles;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author avbravo
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Inject
    UsuariosFacade usuariosFacade;
    Usuarios usuarios = new Usuarios();
    @Inject
    ResourcesFiles rf;
    @Inject
    MenuBeans menuBeans;
    @Inject
    ValidadorRoles validadorRoles;
//    @Setter
//            @Getter
    Boolean logeado = false;
    @Inject
    ManagementThemes managementThemes;
    String tema;
  
    List<String> listPendientes = new ArrayList<>();

    public List<String> getListPendientes() {
        return listPendientes;
    }

    public void setListPendientes(List<String> listPendientes) {
        this.listPendientes = listPendientes;
    }
    
    public String getTema() {
        return tema;
    }
    
    public void setTema(String tema) {
        this.tema = tema;
    }

//String ultimapagina;
    /**
     * Creates a new instance of LoginBean
     */
//    public String getUltimapagina() {
//        return ultimapagina;
//    }
//
//    public void setUltimapagina(String ultimapagina) {
//        this.ultimapagina = ultimapagina;
//    }
    public Usuarios getUsuarios() {
        return usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    public Boolean getLogeado() {
        return logeado;
    }
    
    public void setLogeado(Boolean logeado) {
        this.logeado = logeado;
    }
    
    @PostConstruct
    public void init() {
    }
    
    public LoginBean() {
    }
    
    public String navigator(){
	        // Assume an exception has been thrown by some business logic
	        System.out.println(10/0);
	        return "anonymousView";
	    }

    
    public String verificarLogin() {
        try {
      
            System.out.println("verificarLogin()");
            setLogeado(Boolean.FALSE);
            Usuarios u = usuariosFacade.find(usuarios.getEmail());
            if (u == null) {
                System.out.println("email");
                JSFUtil.addWarningMessage("Email no esta registrado");
                return null;
            }
         
            if (!u.getPassword().equals(usuarios.getPassword())) {
                System.out.println("password");
                JSFUtil.addSuccessMessage("Password no valido");
                return "";
            }
            usuarios = u;
                  
            setLogeado(Boolean.TRUE);
//            JSFUtil.addSuccessMessage("Bienvenido "+usuarios.getNombre());
//            if (validadorRoles.validarRoles(usuarios.getIdgrupousuario().getIdgrupousuario())) {
                //verifica los requisitos

//              return "menu.xhtml";
//            }
          return  "menu";
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e, "verificarLogin()");
        }
        return null;
    }
    
    
    public String home(){
        return "index";
    }
    public String logout() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
            String url = ("/hecas/faces/index.xhtml?faces-redirect=true");
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            try {
                ec.redirect(url);
            } catch (IOException ex) {
                JSFUtil.addErrorMessage(ex.getLocalizedMessage());
            }
//            return "/hecas/faces/index.xhtml?faces-redirect=true";
            return "index";
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e, "logout()");
        }
        return null;
    }
    
    public String irLogin() {
       // return "/index";
        return "/login";
    }
    
    public void irInicio() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url
                = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx,
                                "/index.xhtml"));
        try {
            extContext.redirect(url);
        } catch (IOException ioe) {
            JSFUtil.addErrorMessage(ioe.getLocalizedMessage().toString());
        }
    }
    
    public String cambiarContrasena() {
        try {
            usuariosFacade.edit(usuarios);
        JSFUtil.addSuccessMessage(rf.getMensajeArb("info.update"));
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getLocalizedMessage());
        }
        return null;
    }
    
}
