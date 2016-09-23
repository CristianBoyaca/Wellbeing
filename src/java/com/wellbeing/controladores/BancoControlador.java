/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.controladores.util.JsfUtil;
import com.wellbeing.entidades.Banco;
import com.wellbeing.facade.BancoFacade;
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
@Named(value = "bancoControlador")
@SessionScoped
public class BancoControlador implements Serializable {

    /**
     *
     * @author cristian
     */

    @EJB
    private BancoFacade bancofacade;
    private Banco banco;
    private int selectedItemBanco;

    @PostConstruct
    public void init() {
        banco = new Banco();
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public List<Banco> listarBancos() {
        return bancofacade.findAll();
    }

    public Banco getSelected() {
        if (banco == null) {
            banco = new Banco();
            selectedItemBanco = -1;
        }
        return banco;
    }

    

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(bancofacade.findAll(), false);
    }

    public Banco getBanco(java.lang.Integer id) {
        return bancofacade.find(id);
    }

    @FacesConverter(forClass = Banco.class)
    public static class BancoControladorConvertidor implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BancoControlador controlador = (BancoControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bancoControlador");
            return controlador.getBanco(getKey(value));
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
            if (object instanceof Banco) {
                Banco o = (Banco) object;
                return getStringKey(o.getIdBanco());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Banco.class.getName());
            }
        }

    }

}
