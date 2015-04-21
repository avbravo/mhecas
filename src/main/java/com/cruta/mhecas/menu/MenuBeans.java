/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.menu;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@SessionScoped

public class MenuBeans implements Serializable {

    private static final long serialVersionUID = 1L;

//      @Inject    MenuElemento b = false;
    /*
     barra de menu
     */
    private Boolean barraAdministracion = false;
    private Boolean barraReportes = false;
    private Boolean barraRegistros = false;
    private Boolean barraPagos = false;
    private Boolean barraContabilidad = false;

    /*
     elementos
     */
    @Inject
    MenuElemento actividades;

    @Inject
    MenuElemento actividadesimpuestos;

    @Inject
    MenuElemento bicicleta;

    @Inject
    MenuElemento bicicletareportes;

    @Inject
    MenuElemento bovedas;

    @Inject
    MenuElemento cierreanual;

    @Inject
    MenuElemento cierreanualvigenciasexpiradas;

    @Inject
    MenuElemento cierremensual;

    @Inject
    MenuElemento cierremorosidadmensual;

    @Inject
    MenuElemento cierremorosidadmensualanual;

    @Inject
    MenuElemento cierrerecargomensual;

    @Inject
    MenuElemento comercios;

    @Inject
    MenuElemento comerciosbajaliberacion;

    @Inject
    MenuElemento comercioscertificacion;

    @Inject
    MenuElemento comerciosporactividad;

    @Inject
    MenuElemento comerciosinscripcion;

    @Inject
    MenuElemento contribuyentes;

    @Inject
    MenuElemento corregimientos;

    @Inject
    MenuElemento entidad;

    @Inject
    MenuElemento entidades;

    @Inject
    MenuElemento exoneraciones;

    @Inject
    MenuElemento grupousuarios;

    @Inject
    MenuElemento historial;

    @Inject
    MenuElemento impuestos;

    @Inject
    MenuElemento impuestosubgrupo;
@Inject
 MenuElemento informeingresos;
    @Inject
    MenuElemento integridadnumeracion;

    @Inject
    MenuElemento juzgado;

    @Inject
    MenuElemento mensajeria;

    @Inject
    MenuElemento monitor;

    @Inject
    MenuElemento morosidad;
    @Inject
    MenuElemento morosidadinicial;
    @Inject
    MenuElemento municipios;

    @Inject
    MenuElemento nivel1;

    @Inject
    MenuElemento nivel2;

    @Inject
    MenuElemento nivel3;

    @Inject
    MenuElemento nivel4;

    @Inject
    MenuElemento nivel5;

    @Inject
    MenuElemento nivel6;

    @Inject
    MenuElemento numeracion;

    @Inject
    MenuElemento numeracionpazysalvo;
    
    @Inject
    MenuElemento pagos;

    @Inject
    MenuElemento pagosanuales;

    @Inject
    MenuElemento pagoseventuales;

    @Inject
    MenuElemento pagosmensuales;

    @Inject
    MenuElemento pazysalvo;
    @Inject
    MenuElemento provincias;

    @Inject
    MenuElemento porcentajemorosidad;

    @Inject
    MenuElemento recibos;

    @Inject
    MenuElemento recibosanulados;

    @Inject
    MenuElemento recibosdetalles;

    @Inject
    MenuElemento setup;

    @Inject
    MenuElemento tesorerosauxiliares;

    @Inject
    MenuElemento tesorerosauxiliaresporcentajes;

    @Inject
    MenuElemento tipovehiculos;

    @Inject
    MenuElemento usuarios;

    @Inject
    MenuElemento vehiculos;

    @Inject
    MenuElemento vehiculosplaca;

    @Inject
    MenuElemento vehiculoscertificacion;

    @Inject
    MenuElemento vehiculosbajaliberacion;
    @Inject
    MenuElemento vehiculosentidad;

    @Inject
    MenuElemento vehiculosfideicomiso;

    @Inject
    MenuElemento vehiculoshipoteca;

    @Inject
    MenuElemento vehiculoshipotecaliberar;

    @Inject
    MenuElemento vehiculossecuestro;

    @Inject
    MenuElemento vehiculostraspaso;

    @Inject
    MenuElemento vehiculostraspasohistorial;

    public MenuBeans() {
    }

    public void habilitarTodo(Boolean activo) {
        barraReportes = activo;
        barraAdministracion = activo;
        barraRegistros = activo;
        barraPagos = activo;
        barraContabilidad = activo;

//
        actividades.habilitar(activo);
        actividadesimpuestos.habilitar(activo);

        bicicleta.habilitar(activo);

        bovedas.habilitar(activo);

        cierreanual.habilitar(activo);

        cierreanualvigenciasexpiradas.habilitar(activo);

        cierremensual.habilitar(activo);

        cierremorosidadmensual.habilitar(activo);

        cierremorosidadmensualanual.habilitar(activo);

        cierrerecargomensual.habilitar(activo);

        comercios.habilitar(activo);

        comerciosbajaliberacion.habilitar(activo);

        comercioscertificacion.habilitar(activo);

        comerciosinscripcion.habilitar(activo);

        comerciosporactividad.habilitar(activo);

        contribuyentes.habilitar(activo);

        corregimientos.habilitar(activo);

        exoneraciones.habilitar(activo);

        entidad.habilitar(activo);

        entidades.habilitar(activo);

        grupousuarios.habilitar(activo);

        historial.habilitar(activo);

        impuestos.habilitar(activo);

        impuestosubgrupo.habilitar(activo);
informeingresos.habilitar(activo);
        integridadnumeracion.habilitar(activo);

        juzgado.habilitar(activo);
mensajeria.habilitar(activo);
        monitor.habilitar(activo);

        morosidad.habilitar(activo);
        morosidadinicial.habilitar(activo);
        municipios.habilitar(activo);

        nivel1.habilitar(activo);

        nivel2.habilitar(activo);

        nivel3.habilitar(activo);

        nivel4.habilitar(activo);

        nivel5.habilitar(activo);

        nivel6.habilitar(activo);
        numeracion.habilitar(activo);
        numeracionpazysalvo.habilitar(activo);

        pagos.habilitar(activo);
        pagosanuales.habilitar(activo);
        pagoseventuales.habilitar(activo);
        pagosmensuales.habilitar(activo);

        pazysalvo.habilitar(activo);
        provincias.habilitar(activo);

        porcentajemorosidad.habilitar(activo);

        recibos.habilitar(activo);

        recibosanulados.habilitar(activo);

        recibosdetalles.habilitar(activo);
        setup.habilitar(activo);

        tesorerosauxiliares.habilitar(activo);

        tesorerosauxiliaresporcentajes.habilitar(activo);

        tipovehiculos.habilitar(activo);

        usuarios.habilitar(activo);

        vehiculos.habilitar(activo);

        vehiculosplaca.habilitar(activo);

        vehiculoscertificacion.habilitar(activo);

        vehiculosbajaliberacion.habilitar(activo);

        vehiculosentidad.habilitar(activo);

        vehiculosfideicomiso.habilitar(activo);

        vehiculoshipoteca.habilitar(activo);

        vehiculoshipotecaliberar.habilitar(activo);

        vehiculossecuestro.habilitar(activo);

        vehiculostraspaso.habilitar(activo);

        vehiculostraspasohistorial.habilitar(activo);

    }

    public MenuElemento getMensajeria() {
        return mensajeria;
    }

    public void setMensajeria(MenuElemento mensajeria) {
        this.mensajeria = mensajeria;
    }

    
    
    public MenuElemento getNumeracionpazysalvo() {
        return numeracionpazysalvo;
    }

    public void setNumeracionpazysalvo(MenuElemento numeracionpazysalvo) {
        this.numeracionpazysalvo = numeracionpazysalvo;
    }

    
    
    
    public Boolean getBarraAdministracion() {
        return barraAdministracion;
    }

    public void setBarraAdministracion(Boolean barraAdministracion) {
        this.barraAdministracion = barraAdministracion;
    }

    public Boolean getBarraReportes() {
        return barraReportes;
    }

    public void setBarraReportes(Boolean barraReportes) {
        this.barraReportes = barraReportes;
    }

    public Boolean getBarraRegistros() {
        return barraRegistros;
    }

    public void setBarraRegistros(Boolean barraRegistros) {
        this.barraRegistros = barraRegistros;
    }

    public Boolean getBarraPagos() {
        return barraPagos;
    }

    public void setBarraPagos(Boolean barraPagos) {
        this.barraPagos = barraPagos;
    }

    public Boolean getBarraContabilidad() {
        return barraContabilidad;
    }

    public void setBarraContabilidad(Boolean barraContabilidad) {
        this.barraContabilidad = barraContabilidad;
    }

    public MenuElemento getActividades() {
        return actividades;
    }

    public void setActividades(MenuElemento actividades) {
        this.actividades = actividades;
    }

    public MenuElemento getActividadesimpuestos() {
        return actividadesimpuestos;
    }

    public void setActividadesimpuestos(MenuElemento actividadesimpuestos) {
        this.actividadesimpuestos = actividadesimpuestos;
    }

    public MenuElemento getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(MenuElemento bicicleta) {
        this.bicicleta = bicicleta;
    }

    public MenuElemento getBicicletareportes() {
        return bicicletareportes;
    }

    public void setBicicletareportes(MenuElemento bicicletareportes) {
        this.bicicletareportes = bicicletareportes;
    }

    public MenuElemento getBovedas() {
        return bovedas;
    }

    public void setBovedas(MenuElemento bovedas) {
        this.bovedas = bovedas;
    }

    public MenuElemento getCierreanual() {
        return cierreanual;
    }

    public void setCierreanual(MenuElemento cierreanual) {
        this.cierreanual = cierreanual;
    }

    public MenuElemento getCierreanualvigenciasexpiradas() {
        return cierreanualvigenciasexpiradas;
    }

    public void setCierreanualvigenciasexpiradas(MenuElemento cierreanualvigenciasexpiradas) {
        this.cierreanualvigenciasexpiradas = cierreanualvigenciasexpiradas;
    }

    public MenuElemento getCierremensual() {
        return cierremensual;
    }

    public void setCierremensual(MenuElemento cierremensual) {
        this.cierremensual = cierremensual;
    }

    public MenuElemento getCierremorosidadmensual() {
        return cierremorosidadmensual;
    }

    public void setCierremorosidadmensual(MenuElemento cierremorosidadmensual) {
        this.cierremorosidadmensual = cierremorosidadmensual;
    }

    public MenuElemento getCierremorosidadmensualanual() {
        return cierremorosidadmensualanual;
    }

    public void setCierremorosidadmensualanual(MenuElemento cierremorosidadmensualanual) {
        this.cierremorosidadmensualanual = cierremorosidadmensualanual;
    }

    public MenuElemento getCierrerecargomensual() {
        return cierrerecargomensual;
    }

    public void setCierrerecargomensual(MenuElemento cierrerecargomensual) {
        this.cierrerecargomensual = cierrerecargomensual;
    }

    public MenuElemento getComercios() {
        return comercios;
    }

    public void setComercios(MenuElemento comercios) {
        this.comercios = comercios;
    }

    public MenuElemento getComerciosbajaliberacion() {
        return comerciosbajaliberacion;
    }

    public void setComerciosbajaliberacion(MenuElemento comerciosbajaliberacion) {
        this.comerciosbajaliberacion = comerciosbajaliberacion;
    }

    public MenuElemento getComercioscertificacion() {
        return comercioscertificacion;
    }

    public void setComercioscertificacion(MenuElemento comercioscertificacion) {
        this.comercioscertificacion = comercioscertificacion;
    }

    public MenuElemento getComerciosporactividad() {
        return comerciosporactividad;
    }

    public void setComerciosporactividad(MenuElemento comerciosporactividad) {
        this.comerciosporactividad = comerciosporactividad;
    }

    public MenuElemento getComerciosinscripcion() {
        return comerciosinscripcion;
    }

    public void setComerciosinscripcion(MenuElemento comerciosinscripcion) {
        this.comerciosinscripcion = comerciosinscripcion;
    }

    public MenuElemento getContribuyentes() {
        return contribuyentes;
    }

    public void setContribuyentes(MenuElemento contribuyentes) {
        this.contribuyentes = contribuyentes;
    }

    public MenuElemento getCorregimientos() {
        return corregimientos;
    }

    public void setCorregimientos(MenuElemento corregimientos) {
        this.corregimientos = corregimientos;
    }

    public MenuElemento getEntidad() {
        return entidad;
    }

    public void setEntidad(MenuElemento entidad) {
        this.entidad = entidad;
    }

    public MenuElemento getEntidades() {
        return entidades;
    }

    public void setEntidades(MenuElemento entidades) {
        this.entidades = entidades;
    }

    public MenuElemento getExoneraciones() {
        return exoneraciones;
    }

    public void setExoneraciones(MenuElemento exoneraciones) {
        this.exoneraciones = exoneraciones;
    }

    public MenuElemento getGrupousuarios() {
        return grupousuarios;
    }

    public void setGrupousuarios(MenuElemento grupousuarios) {
        this.grupousuarios = grupousuarios;
    }

    public MenuElemento getHistorial() {
        return historial;
    }

    public void setHistorial(MenuElemento historial) {
        this.historial = historial;
    }

    public MenuElemento getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(MenuElemento impuestos) {
        this.impuestos = impuestos;
    }

    public MenuElemento getImpuestosubgrupo() {
        return impuestosubgrupo;
    }

    public void setImpuestosubgrupo(MenuElemento impuestosubgrupo) {
        this.impuestosubgrupo = impuestosubgrupo;
    }

    public MenuElemento getInformeingresos() {
        return informeingresos;
    }

    public void setInformeingresos(MenuElemento informeingresos) {
        this.informeingresos = informeingresos;
    }

    public MenuElemento getIntegridadnumeracion() {
        return integridadnumeracion;
    }

    public void setIntegridadnumeracion(MenuElemento integridadnumeracion) {
        this.integridadnumeracion = integridadnumeracion;
    }

    public MenuElemento getJuzgado() {
        return juzgado;
    }

    public void setJuzgado(MenuElemento juzgado) {
        this.juzgado = juzgado;
    }

    public MenuElemento getMonitor() {
        return monitor;
    }

    public void setMonitor(MenuElemento monitor) {
        this.monitor = monitor;
    }

    public MenuElemento getMorosidad() {
        return morosidad;
    }

    public void setMorosidad(MenuElemento morosidad) {
        this.morosidad = morosidad;
    }

    public MenuElemento getMorosidadinicial() {
        return morosidadinicial;
    }

    public void setMorosidadinicial(MenuElemento morosidadinicial) {
        this.morosidadinicial = morosidadinicial;
    }

    public MenuElemento getMunicipios() {
        return municipios;
    }

    public void setMunicipios(MenuElemento municipios) {
        this.municipios = municipios;
    }

    public MenuElemento getNivel1() {
        return nivel1;
    }

    public void setNivel1(MenuElemento nivel1) {
        this.nivel1 = nivel1;
    }

    public MenuElemento getNivel2() {
        return nivel2;
    }

    public void setNivel2(MenuElemento nivel2) {
        this.nivel2 = nivel2;
    }

    public MenuElemento getNivel3() {
        return nivel3;
    }

    public void setNivel3(MenuElemento nivel3) {
        this.nivel3 = nivel3;
    }

    public MenuElemento getNivel4() {
        return nivel4;
    }

    public void setNivel4(MenuElemento nivel4) {
        this.nivel4 = nivel4;
    }

    public MenuElemento getNivel5() {
        return nivel5;
    }

    public void setNivel5(MenuElemento nivel5) {
        this.nivel5 = nivel5;
    }

    public MenuElemento getNivel6() {
        return nivel6;
    }

    public void setNivel6(MenuElemento nivel6) {
        this.nivel6 = nivel6;
    }

    public MenuElemento getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(MenuElemento numeracion) {
        this.numeracion = numeracion;
    }

    public MenuElemento getPagos() {
        return pagos;
    }

    public void setPagos(MenuElemento pagos) {
        this.pagos = pagos;
    }

    public MenuElemento getPagosanuales() {
        return pagosanuales;
    }

    public void setPagosanuales(MenuElemento pagosanuales) {
        this.pagosanuales = pagosanuales;
    }

    public MenuElemento getPagoseventuales() {
        return pagoseventuales;
    }

    public void setPagoseventuales(MenuElemento pagoseventuales) {
        this.pagoseventuales = pagoseventuales;
    }

    public MenuElemento getPagosmensuales() {
        return pagosmensuales;
    }

    public void setPagosmensuales(MenuElemento pagosmensuales) {
        this.pagosmensuales = pagosmensuales;
    }

    public MenuElemento getPazysalvo() {
        return pazysalvo;
    }

    public void setPazysalvo(MenuElemento pazysalvo) {
        this.pazysalvo = pazysalvo;
    }

    public MenuElemento getProvincias() {
        return provincias;
    }

    public void setProvincias(MenuElemento provincias) {
        this.provincias = provincias;
    }

    public MenuElemento getPorcentajemorosidad() {
        return porcentajemorosidad;
    }

    public void setPorcentajemorosidad(MenuElemento porcentajemorosidad) {
        this.porcentajemorosidad = porcentajemorosidad;
    }

    public MenuElemento getRecibos() {
        return recibos;
    }

    public void setRecibos(MenuElemento recibos) {
        this.recibos = recibos;
    }

    public MenuElemento getRecibosanulados() {
        return recibosanulados;
    }

    public void setRecibosanulados(MenuElemento recibosanulados) {
        this.recibosanulados = recibosanulados;
    }

    public MenuElemento getRecibosdetalles() {
        return recibosdetalles;
    }

    public void setRecibosdetalles(MenuElemento recibosdetalles) {
        this.recibosdetalles = recibosdetalles;
    }

    public MenuElemento getSetup() {
        return setup;
    }

    public void setSetup(MenuElemento setup) {
        this.setup = setup;
    }

    public MenuElemento getTesorerosauxiliares() {
        return tesorerosauxiliares;
    }

    public void setTesorerosauxiliares(MenuElemento tesorerosauxiliares) {
        this.tesorerosauxiliares = tesorerosauxiliares;
    }

    public MenuElemento getTesorerosauxiliaresporcentajes() {
        return tesorerosauxiliaresporcentajes;
    }

    public void setTesorerosauxiliaresporcentajes(MenuElemento tesorerosauxiliaresporcentajes) {
        this.tesorerosauxiliaresporcentajes = tesorerosauxiliaresporcentajes;
    }

    public MenuElemento getTipovehiculos() {
        return tipovehiculos;
    }

    public void setTipovehiculos(MenuElemento tipovehiculos) {
        this.tipovehiculos = tipovehiculos;
    }

    public MenuElemento getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(MenuElemento usuarios) {
        this.usuarios = usuarios;
    }

    public MenuElemento getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(MenuElemento vehiculos) {
        this.vehiculos = vehiculos;
    }

    public MenuElemento getVehiculosplaca() {
        return vehiculosplaca;
    }

    public void setVehiculosplaca(MenuElemento vehiculosplaca) {
        this.vehiculosplaca = vehiculosplaca;
    }

    public MenuElemento getVehiculoscertificacion() {
        return vehiculoscertificacion;
    }

    public void setVehiculoscertificacion(MenuElemento vehiculoscertificacion) {
        this.vehiculoscertificacion = vehiculoscertificacion;
    }

    public MenuElemento getVehiculosbajaliberacion() {
        return vehiculosbajaliberacion;
    }

    public void setVehiculosbajaliberacion(MenuElemento vehiculosbajaliberacion) {
        this.vehiculosbajaliberacion = vehiculosbajaliberacion;
    }

    public MenuElemento getVehiculosentidad() {
        return vehiculosentidad;
    }

    public void setVehiculosentidad(MenuElemento vehiculosentidad) {
        this.vehiculosentidad = vehiculosentidad;
    }

    public MenuElemento getVehiculosfideicomiso() {
        return vehiculosfideicomiso;
    }

    public void setVehiculosfideicomiso(MenuElemento vehiculosfideicomiso) {
        this.vehiculosfideicomiso = vehiculosfideicomiso;
    }

    public MenuElemento getVehiculoshipoteca() {
        return vehiculoshipoteca;
    }

    public void setVehiculoshipoteca(MenuElemento vehiculoshipoteca) {
        this.vehiculoshipoteca = vehiculoshipoteca;
    }

    public MenuElemento getVehiculoshipotecaliberar() {
        return vehiculoshipotecaliberar;
    }

    public void setVehiculoshipotecaliberar(MenuElemento vehiculoshipotecaliberar) {
        this.vehiculoshipotecaliberar = vehiculoshipotecaliberar;
    }

    public MenuElemento getVehiculossecuestro() {
        return vehiculossecuestro;
    }

    public void setVehiculossecuestro(MenuElemento vehiculossecuestro) {
        this.vehiculossecuestro = vehiculossecuestro;
    }

    public MenuElemento getVehiculostraspaso() {
        return vehiculostraspaso;
    }

    public void setVehiculostraspaso(MenuElemento vehiculostraspaso) {
        this.vehiculostraspaso = vehiculostraspaso;
    }

    public MenuElemento getVehiculostraspasohistorial() {
        return vehiculostraspasohistorial;
    }

    public void setVehiculostraspasohistorial(MenuElemento vehiculostraspasohistorial) {
        this.vehiculostraspasohistorial = vehiculostraspasohistorial;
    }

}
