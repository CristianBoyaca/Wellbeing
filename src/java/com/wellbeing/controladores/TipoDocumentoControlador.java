/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.TipoDocumento;
import com.wellbeing.facade.TipoDocumentoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named(value = "tipodocumentoControlador")
@SessionScoped
public class TipoDocumentoControlador implements Serializable {

    @EJB
    private TipoDocumentoFacade tipoDocumentoFacade;
    private TipoDocumento tipoDocumento;
    private int selectiTemlist;

    @PostConstruct
    public void init() {
        this.tipoDocumento = new TipoDocumento();
    }

    public TipoDocumentoFacade getTipoDocumentoFacade() {
        return tipoDocumentoFacade;
    }

    public void setTipoDocumentoFacade(TipoDocumentoFacade tipoDocumentoFacade) {
        this.tipoDocumentoFacade = tipoDocumentoFacade;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<TipoDocumento> listarTipoDocumentos() {

        return tipoDocumentoFacade.findAll();

    }

    public TipoDocumento getSelected() {
        if (tipoDocumento == null) {
            tipoDocumento = new TipoDocumento();
            selectiTemlist = -1;
        }
        return tipoDocumento;
    }

    public List<TipoDocumento> listarTipos() {
        return tipoDocumentoFacade.findAll();
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(tipoDocumentoFacade.findAll(), false);
    }

    public TipoDocumento getTipoDocumento(java.lang.Integer id) {
        return tipoDocumentoFacade.find(id);
    }

    @FacesConverter(forClass = TipoDocumento.class)
    public static class TipoDocumentoControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoDocumentoControlador controlador = (TipoDocumentoControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipodocumentoControlador");
            return controlador.getTipoDocumento(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TipoDocumento) {
                TipoDocumento o = (TipoDocumento) object;
                return getStringKey(o.getIdDocumento());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoDocumento.class.getName());
            }
        }

    }

}
