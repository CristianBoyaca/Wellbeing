/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.Certificado;
import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Usuario;
import com.wellbeing.facade.CertificadoFacade;
import com.wellbeing.facade.DatoEmpleadoFacade;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.StreamedContent;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author cristian
 */
@Named
@SessionScoped
public class CertificadoControlador implements Serializable {

    @EJB
    private CertificadoFacade certificadoFacade;
    private Certificado certificado;
    @EJB
    private DatoEmpleadoFacade datoEmpleadoFacade;
    private DatoEmpleado datoEmpleado;
    private List<DatoEmpleado> lista;
    private UploadedFile archivo;
    private StreamedContent streamedContent;
    private StreamedContent archivo1;

    @PostConstruct
    public void init() {
        certificado = new Certificado();
        streamedContent = generarCertificadoLaboral();
        datoEmpleado = new DatoEmpleado();

    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public DatoEmpleado getDatoEmpleado() {
        return datoEmpleado;
    }

    public void setDatoEmpleado(DatoEmpleado datoEmpleado) {
        this.datoEmpleado = datoEmpleado;
    }

    public List<DatoEmpleado> getLista() {
        return lista;
    }

    public void setLista(List<DatoEmpleado> lista) {
        this.lista = lista;
    }

    public StreamedContent getArchivo1() {
        return archivo1;
    }

    public void setArchivo1(StreamedContent archivo1) {
        this.archivo1 = archivo1;
    }

    public void registrarCertificado() {
        InputStream fi;
        try {
            if (archivo.getSize() > 0) {
                fi = archivo.getInputstream();
                // archivo.getContentType();
                // creamos el buffer
                byte[] buffer = new byte[(int) archivo.getSize()];
                // Leer al buffer
                int readers = fi.read(buffer);

                certificado.setImgCertificado(buffer);
            }
            certificadoFacade.create(certificado);
            certificado.setImgCertificado(null);
            Integer tipoSolicitud = 4;
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            String documento = "";
            documento += datoEmpleadoFacade.buscarDocumento(u.getIdUsuario());
            certificadoFacade.registrarSolicitudCertificado(documento, tipoSolicitud);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Certificado", "Se ha registrado correctamente su solicitud "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar correctamente su solicitud de certificado"));
        }
    }

    public StreamedContent generarCertificadoLaboral() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StreamedContent p = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, Object> parametros = new HashMap<>();
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        lista = datoEmpleadoFacade.buscarInformacionPersonal(u.getIdUsuario());
        datoEmpleado = lista.get(0);
        String mes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Date fecha = new Date();
        parametros.put("nombre", datoEmpleado.getPrimerNombre() + " " + datoEmpleado.getSegundoNombre() + " " + datoEmpleado.getPrimerApellido() + " " + datoEmpleado.getSegundoApellido());
        parametros.put("tipo", datoEmpleado.getTipoDocumento().getTipoDocumento());
        parametros.put("numero", datoEmpleado.getIdentificacion());
        parametros.put("diaT", String.valueOf(datoEmpleado.getFechaIngreso().getDay()));
        parametros.put("mesT", mes[datoEmpleado.getFechaIngreso().getMonth()]);
        parametros.put("anioT", String.valueOf(datoEmpleado.getFechaIngreso().getYear() + 1900));
        parametros.put("cargo", datoEmpleado.getCargo().getNombreCargo());
        parametros.put("dia", String.valueOf(fecha.getDate()));
        parametros.put("mes", mes[fecha.getMonth()]);
        parametros.put("anio", String.valueOf(fecha.getYear() + 1900));
        String path = fc.getExternalContext().getRealPath("./certificado/certificadoLaboral.jasper");
        File archivo = new File(path);
        try {
            byte[] bytes = JasperRunManager.runReportToPdf(archivo.getPath(), parametros, new JREmptyDataSource());
            out.write(bytes, 0, bytes.length);
            p = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "application/pdf");
            out.close();
            fc.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Certificado> listarCertificados() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return certificadoFacade.listarCertificados(usuario.getIdUsuario());
    }

    public void descargarCertificado(Integer idCertificado) {
        archivo1 = certificadoFacade.descargarCertificado(idCertificado);
        certificado.setIdCertificado(0);
    }

    public void verCertificado(Integer idCertificado) {
        certificadoFacade.ver(idCertificado);
        certificado.setIdCertificado(0);
    }

}
