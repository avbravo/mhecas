package com.cruta.mhecas.interfaces;

import java.util.List;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author avbravo
 */
public interface ISearchController<T> {

    public String load();

    public String clear();

    public void iniciar();

    public void iniciar(String value);

    public String showAll();

    public String regresar();
    public String delete();

    public String regresarSinSeleccion();

    public String changeItems();

    public List<T> getItems();

    public List<T> getItemsEntity();

    public String changeItemsInverso();

    public List<T> getItemsCollection();

    public String imprimirTodos();
    
    //carga todos los registros e invoca imprimir
public String listar();
 public void onCellEdit(CellEditEvent event) ;
 
 //public String ordenar(); 
}
