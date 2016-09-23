/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.Documento;
import com.wellbeing.facade.DocumentoFacade;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author cristian
 */
@Named(value = "documentoControlador")
@SessionScoped
public class DocumentoControlador implements Serializable {

    @EJB
    private DocumentoFacade documentoFacade;
    private Documento documento;
    private List<Documento> documentos;
    private UploadedFile archivo;
    private StreamedContent archivo1;

    @PostConstruct
    public void init() {
        documento = new Documento();
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public StreamedContent getArchivo1() {
        return archivo1;
    }

    public void setArchivo1(StreamedContent archivo1) {
        this.archivo1 = archivo1;
    }
    
    

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public void registrarDocumento() {
        InputStream fi;
        try {
            if (archivo.getSize() > 0) {
                fi = archivo.getInputstream();
                // archivo.getContentType();
                // creamos el buffer
                byte[] buffer = new byte[(int) archivo.getSize()];
                // Leer al buffer
                int readers = fi.read(buffer);
                documento.setImagen(buffer);
            }
            documentoFacade.create(documento);
            documento.setImagen(null);
             documento.setNombre("");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Documento", "Se ha registrado correctamente su archivo "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar correctamente su archivo"));
        }
    }

    public List<Documento> listarDocumentos() {
        String documento = "";
        documento += this.documento.getIdentificacion();
       documentos = documentoFacade.buscarDocumentos(documento);
        return documentos;
    }
    
    public void descargarDocumento(Integer idDocumento){
    archivo1=documentoFacade.descargarDocumento(idDocumento);
    documento.setNombre("");
    }
    public void ver(Integer idDocumento){
    documentoFacade.ver(idDocumento);
    documento.setNombre("");
    }
}
