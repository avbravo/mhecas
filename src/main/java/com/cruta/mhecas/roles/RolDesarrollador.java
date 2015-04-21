/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.roles;


import com.cruta.mhecas.menu.MenuBeans;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@javax.enterprise.context.RequestScoped
public class RolDesarrollador {

    @Inject
    MenuBeans menuBeans;

    /**
     * Creates a new instance of RolAdministrador
     */
    public RolDesarrollador() {
    }

    public void activar() {
        /*
         *menu       
         */
        menuBeans.setBarraAdministracion(true);
        menuBeans.setBarraReportes(true);
        menuBeans.setBarraRegistros(true);
        menuBeans.setBarraPagos(true);
        menuBeans.setBarraContabilidad(true);
        /*
        actividades
        */
        menuBeans.getActividades().habilitar(Boolean.TRUE);
        menuBeans.getActividadesimpuestos().habilitar(Boolean.TRUE);
        
        /*
         bicicletas
         */
        menuBeans.getBicicleta().habilitar(Boolean.TRUE);

        /*
         cierreanual
         */
        menuBeans.getCierreanual().habilitar(Boolean.TRUE);

        /*
         Cierreanualvigenciasexpiradas
         */
        menuBeans.getCierreanualvigenciasexpiradas().habilitar(Boolean.TRUE);

        /*
         Cierremensual
         */
        menuBeans.getCierremensual().habilitar(Boolean.TRUE);

        /*
         Cierremorosidadmensual
         */
        menuBeans.getCierremorosidadmensual().habilitar(Boolean.TRUE);

        /*
         Cierremorosidadmensualanual
         */
        menuBeans.getCierremorosidadmensualanual().habilitar(Boolean.TRUE);

        /*
         Cierrerecargomensual
         */
        menuBeans.getCierrerecargomensual().habilitar(Boolean.TRUE);

        /*
         Comercios
         */
        menuBeans.getComercios().habilitar(Boolean.TRUE);

        /*
         Comerciosbajaliberacion
         */
        menuBeans.getComerciosbajaliberacion().habilitar(Boolean.TRUE);

        /*
         Comerciosinscripcion
         */
        menuBeans.getComerciosinscripcion().habilitar(Boolean.TRUE);

        /*
         Contribuyentes
         */
        menuBeans.getContribuyentes().habilitar(Boolean.TRUE);

        /*
         Corregimientos
         */
        menuBeans.getCorregimientos().habilitar(Boolean.TRUE);

        /*
         Entidad
         */
        menuBeans.getEntidad().habilitar(Boolean.TRUE);

        /*
         Exoneraciones
         */
        menuBeans.getExoneraciones().habilitar(Boolean.TRUE);

        /*
         Grupousuarios
         */
        menuBeans.getGrupousuarios().habilitar(Boolean.TRUE);

        /*
         Historial
         */
        menuBeans.getHistorial().habilitar(Boolean.TRUE);

        /*
         Impuestos
         */
        menuBeans.getImpuestos().habilitar(Boolean.TRUE);

        /*
         Impuestosactividades
         */


//        Impuestosubgrupo
        menuBeans.getImpuestosubgrupo().habilitar(Boolean.TRUE);
   menuBeans.getIntegridadnumeracion().habilitar(Boolean.TRUE);
        
        menuBeans.getInformeingresos().habilitar(Boolean.TRUE);
//        Juzgado
        menuBeans.getJuzgado().habilitar(Boolean.TRUE);
         menuBeans.getMensajeria().habilitar(Boolean.TRUE);
        
                menuBeans.getMonitor().habilitar(Boolean.TRUE);


//        Morosidad
        menuBeans.getMorosidad().habilitar(Boolean.TRUE);
  //        Morosidadinicial
        menuBeans.getMorosidadinicial().habilitar(Boolean.TRUE);
//        Municipios
        menuBeans.getMunicipios().habilitar(Boolean.TRUE);

//        Nivel1
        menuBeans.getNivel1().habilitar(Boolean.TRUE);

//        Nivel2
        menuBeans.getNivel2().habilitar(Boolean.TRUE);

        //        Nivel3    
        menuBeans.getNivel3().habilitar(Boolean.TRUE);

        //        Nivel4
        menuBeans.getNivel4().habilitar(Boolean.TRUE);

        //        Nivel5
        menuBeans.getNivel5().habilitar(Boolean.TRUE);

        //        Nivel6
        menuBeans.getNivel6().habilitar(Boolean.TRUE);
             menuBeans.getNumeracion().habilitar(Boolean.TRUE);
        menuBeans.getNumeracionpazysalvo().habilitar(Boolean.TRUE);
        menuBeans.getPagos().habilitar(Boolean.TRUE);

//        Pagosanuales
        menuBeans.getPagosanuales().habilitar(Boolean.TRUE);
              // pagos eventuales
        menuBeans.getPagoseventuales().habilitar(Boolean.TRUE);

//        Pagosmensuales
        menuBeans.getPagosmensuales().habilitar(Boolean.TRUE);
//        Pazysalvo
        menuBeans.getPazysalvo().habilitar(Boolean.TRUE);
//        Provincias
        menuBeans.getProvincias().habilitar(Boolean.TRUE);
          //        Porcentajemorosidad
        
        menuBeans.getPorcentajemorosidad().habilitar(Boolean.TRUE);

//        Recibos
        menuBeans.getRecibos().habilitar(Boolean.TRUE);

//        Recibosanulados
        menuBeans.getRecibosanulados().habilitar(Boolean.TRUE);

//        Recibosdetalles
        menuBeans.getRecibosdetalles().habilitar(Boolean.TRUE);
        //setup
          menuBeans.getSetup().habilitar(Boolean.TRUE);

//        Tesorerosauxiliares
        menuBeans.getTesorerosauxiliares().habilitar(Boolean.TRUE);

//        Tesorerosauxiliaresporcentajes
        menuBeans.getTesorerosauxiliaresporcentajes().habilitar(Boolean.TRUE);



//        Tipovehiculos
        menuBeans.getTipovehiculos().habilitar(Boolean.TRUE);

//        Usuarios
        menuBeans.getUsuarios().habilitar(Boolean.TRUE);

//        Vehiculos
        menuBeans.getVehiculos().habilitar(Boolean.TRUE);
        //        Vehiculosbajaliberacion
        menuBeans.getVehiculosbajaliberacion().habilitar(Boolean.TRUE);

//        Vehiculosentidad
        menuBeans.getVehiculosentidad().habilitar(Boolean.TRUE);

//        Vehiculosfideicomiso
        menuBeans.getVehiculosfideicomiso().habilitar(Boolean.TRUE);

//        Vehiculoshipoteca
        menuBeans.getVehiculoshipoteca().habilitar(Boolean.TRUE);

//        Vehiculossecuestro
        menuBeans.getVehiculossecuestro().habilitar(Boolean.TRUE);

//        Vehiculostraspaso
        menuBeans.getVehiculostraspaso().habilitar(Boolean.TRUE);

    }
}
