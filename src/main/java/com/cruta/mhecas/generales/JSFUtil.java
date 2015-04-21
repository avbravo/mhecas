/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cruta.mhecas.generales;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class JSFUtil {

    private static final Logger LOG = Logger.getLogger(JSFUtil.class.getName());

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "­­­");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }
    /*
     * logout
     */

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/index";
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void testMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
                msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static void addWarningMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, ""));
    }

    public static void addFatalMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, ""));
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName,
            Converter converter, UIComponent component) {
        String theId = JSFUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static void infoDialog(String titulo, String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo,
                texto);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public static void warningDialog(String titulo, String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,
                texto);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public static void fatalDialog(String titulo, String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo,
                texto);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public static void errorDialog(String titulo, String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                titulo, texto);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public static java.sql.Date converterDate(java.util.Date fecha) {
        try {
            long lfecha = fecha.getTime();
            java.sql.Date dtfecha = new java.sql.Date(lfecha);
            return dtfecha;
        } catch (Exception e) {
            addErrorMessage("converterDate() " + e.getLocalizedMessage());
        }
        return null;
    }

    public static java.util.Date getFechaActual() {
        java.util.Calendar ca = java.util.Calendar.getInstance();
        java.sql.Date mydate = new java.sql.Date(ca.getTimeInMillis());
        return new java.sql.Date(mydate.getTime());

    }
public static java.sql.Time getHoraActual(){
    java.util.Calendar cal = Calendar.getInstance();
java.util.Date utilDate = new java.util.Date(); // your util date
cal.setTime(utilDate);
   
java.sql.Time sqlDate = new java.sql.Time(cal.getTime().getTime());
        return sqlDate;
}

public static Integer getEdad(Date fecha){
    LocalDateTime ahora = LocalDateTime.now();
   
    Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
       return  LocalDateTime.now().minusYears(anio).minusMonths(mes).minusDays(dia).getYear();
       
}
    public static Integer getAnioActual() {
        java.util.Calendar ca = java.util.Calendar.getInstance();
        java.sql.Date mydate = new java.sql.Date(ca.getTimeInMillis());
        return ca.get(Calendar.YEAR);
    }

    public static Integer getMesActual() {
        java.util.Calendar ca = java.util.Calendar.getInstance();
        java.sql.Date mydate = new java.sql.Date(ca.getTimeInMillis());
        return ca.get(Calendar.MONTH);
    }

    public static Integer getMesDeUnaFecha(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return mes;
    }

    public static Integer getAnioDeUnaFecha(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return anio;
    }

    public static Integer getDiaDeUnaFecha(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return dia;
    }

    public static Integer getDiaActual() {
        java.util.Calendar ca = java.util.Calendar.getInstance();
        java.sql.Date mydate = new java.sql.Date(ca.getTimeInMillis());
        return ca.get(Calendar.DATE);
    }

    
    
    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static String getComponentMessages(String clientComponent, String defaultMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc).findComponent(clientComponent);
        if (component instanceof UIInput) {
            UIInput inputComponent = (UIInput) component;
            if (inputComponent.isValid()) {
                return defaultMessage;
            } else {
                Iterator<FacesMessage> iter = fc.getMessages(inputComponent.getClientId());
                if (iter.hasNext()) {
                    return iter.next().getDetail();
                }
            }
        }
        return "";
    }

    public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }

    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // Esta función permite generar una serie de caracteres al azar de manera 
    // única, creando la posibilidad que los mismos sean usados a modo de 
    // identificadores para tablas, eventos, códigos o documentos
    /**
     * Genera una serie de caracteres aleatorios
     * @return 
     */
    public static String generateUniqueID() {
        String strValue = "";
        UUID idUnique = UUID.randomUUID();
        strValue = idUnique.toString();
        return strValue.toUpperCase();
    }

    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // Esta función nos permite estructurar el número de identidad personal de 
    // un cliente o funcionario con base en el formato establecido de máximo 20 
    // caracteres tal que la estrutura se complete com valores ceros determinados
    // por la función refillString() tal que se use el máximo de caracteres 
    // definidos a nivel de la BD y de esa forma se normalicen el formato en que 
    // se guardan las cédulas
    public static String formatearCedula(String cedula) {
        try {

            String[] personalID = new String[50];
            int i = 0;
            StringTokenizer tokens = new StringTokenizer(cedula, "-");
            while (tokens.hasMoreTokens()) {
                personalID[i] = tokens.nextToken();
                i++;
            }
            LOG.info("descompuesta ");
            return formatPersonalID(personalID[0],
                    personalID[1],
                    personalID[2]);
        } catch (Exception e) {
            addErrorMessage("formatearCedula()" + e.getLocalizedMessage());
        }
        return cedula;
    }

    public static String formatPersonalID(String cedDigit, String cedTome, String cedFolium) {
        return refillString(cedDigit, 2, false) + "-"
                + refillString(cedTome, 6, false) + "-"
                + refillString(cedFolium, 7, false);
    }

    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // Esta funcion rellena con ceros los campos de tipo compuestos por fracciones 
    // de caracteres como por ejemplo, los números de cuenta, cédula y otros. 
    // La funcion recibe como parámetro la cadena o fracción a rellenar, cuantos 
    // caracteres deben completarse con ceros y en que lado deben estructurarse 
    // con relación a la cadena original (derecha o izquierda)
    public static String refillString(String strValue, int length, boolean right) { // length = 2 right = true
        String filler = "";
        if (strValue.length() < length) { // La cadena es menor a lo que queremos ?
            for (int i = 0; i < length - strValue.length(); i++) { // Crea los espacios
                filler += "0"; // Completa com ceros 
            }
            if (right) {
                strValue = strValue + filler;
            } // Ajusta a la derecha
            else {
                strValue = filler + strValue;
            } // Ajusta a la izquierda
        }
        return strValue.toUpperCase();
    }

    /*
     convierte a md5 el password
     */
    public static String md5(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(password.getBytes());
        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        //algoritmo y arreglo md5
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0" + Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        //clave encriptada
        return h.toString();
    }

    public static Integer contadorGuiones(String texto) {
        Integer contador = 0;
        try {

            for (int i = 0; i < texto.length(); i++) {

                if (texto.charAt(i) == '-') {
                    contador++;
                }
            }
        } catch (Exception e) {
            addErrorMessage("numeroCaracteres() " + e.getLocalizedMessage());
        }
        return contador;
    }

    /*
     devuelve el path 
     */
    public static String getPath() {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                    .getExternalContext().getContext();
            return ctx.getRealPath("/");

        } catch (Exception e) {

            addErrorMessage("getPath() " + e.getLocalizedMessage());
        }
        return null;

    }

    /*
     devuelve un hashmap con la ruta de fotos clinicas y el url para las imagenes
     */
    public static HashMap<String, String> getMapPathFotosUsuarios() {
        try {
            HashMap<String, String> map = new HashMap<String, String>();
           
            String path = getPath() + "resources/fotos/";
            map.put("path", path);
            map.put("url", "/resources/fotos/");
            return map;
        } catch (Exception e) {

            addErrorMessage(" getMapPathFotosUsuarios() " + e.getLocalizedMessage());
        }
        return null;

    }

     /*
     devuelve un hashmap con la ruta de fotos clinicas y el url para las imagenes
     */
    public static String getPathFotosUsuarios() {
        try {             
        
            String path = getPath() + "resources/fotos/";
 return path;
        } catch (Exception e) {

            addErrorMessage("getPathFotosUsuarios() " + e.getLocalizedMessage());
        }
        return null;

    }
    
     public static String getPathFotosPlagas() {
        try {             
        
            String path = getPath() + "resources/fotos/";
 return path;
        } catch (Exception e) {

            addErrorMessage("getPathFotosPlagas() " + e.getLocalizedMessage());
        }
        return null;

    }
     public static String getPathFotosAlertas() {
        try {             
        
            String path = getPath() + "resources/fotos/";
 return path;
        } catch (Exception e) {

            addErrorMessage("getPathFotosAlertas() " + e.getLocalizedMessage());
        }
        return null;

    }
          public static String getPathFotosCultivos() {
        try {             
        
            String path = getPath() + "resources/fotos/";
 return path;
        } catch (Exception e) {

            addErrorMessage("getPathFotosCultivos() " + e.getLocalizedMessage());
        }
        return null;

    }
             public static String getPathFotosMisCultivos() {
        try {             
        
            String path = getPath() + "resources/fotos/";
 return path;
        } catch (Exception e) {

            addErrorMessage("getPathFotosCultivos() " + e.getLocalizedMessage());
        }
        return null;

    }
    /*
     copia un archivo generalmente cuando se usa el fileupload
     fileName: nombre del archivo a copiar
     in: Es el InputStream
     destination: ruta donde se guardara el archivo
  
     */
    public static Boolean copyFile(String fileName, InputStream in, String destination) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            return true;
        } catch (IOException e) {
            JSFUtil.addErrorMessage("copyFile() " + e.getLocalizedMessage());
        }
        return false;
    }
    /**
    genera id
    * 
     * @return returna un randomUUID automatico
    */
    public static String getUUID() {
     
        UUID uuid = UUID.randomUUID();
        return  uuid.toString().toLowerCase();
    
    }
    /**
     * getExtension()
     * 
     * @param texto
     * @return la extension de un nombre de archivo
     */
    public static String getExtension(String texto){
        try {
             return texto.substring(texto.indexOf("."),texto.length());
        } catch (Exception e) {
             JSFUtil.addErrorMessage("getExtension() " + e.getLocalizedMessage());
        }
        return "";
    }
}
