package com.cruta.mhecas.interfaces;

/**
 *
 * @author avbravo
 */
public interface IController {

    public String buscar();

    public String prepararNew();

    public String save();

    public String edit();

    public String delete();

    public String imprimir();

    public String imprimirTodos();

    public Integer contador();

    public String elementoSeleccionado();

    public String habilitarConsultar();

    public Integer getIdSiguiente();
   //genera el id del siguiente numero para llaves autoincrementables

}
