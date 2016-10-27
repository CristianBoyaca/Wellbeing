/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.DatoEmpleado;
import com.wellbeing.entidades.Observacion;
import com.wellbeing.entidades.Solicitud;
import com.wellbeing.entidades.Usuario;
import com.wellbeing.entidades.Vacacion;
import com.wellbeing.facade.SolicitudFacade;
import com.wellbeing.facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author cristian
 */
@Named(value = "solicitudControlador")
@SessionScoped
public class SolicitudControlador implements Serializable {

    @EJB
    SolicitudFacade solicitudFacade;
    ObservacionControlador observacion;
    private Solicitud solicitud;
    private List<Solicitud> solicitudes;
    private PieChartModel pieChartModel;
    private Integer contador1;
    private Integer contador2;
    private Integer contador3;
    private Integer tipo;
    private boolean validador;
    private Date fecha;
    private Vacacion vacacion;
    private DatoEmpleadoControlador datoEmpleadoControlador;
    private List<DatoEmpleado> datoEmpleados;
    private CorreoControlador correoControlador;
    private UsuarioFacade usuarioControlador;

   

    
    @PostConstruct
    public void init() {
        solicitud = new Solicitud();
        observacion=new ObservacionControlador();
        fecha=new Date();
        vacacion = new Vacacion();
        datoEmpleadoControlador=new DatoEmpleadoControlador();
        usuarioControlador= new UsuarioFacade();
    }

    public Vacacion getVacacion() {
        return vacacion;
    }

    public void setVacacion(Vacacion vacacion) {
        this.vacacion = vacacion;
    }



    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }
        
    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    
     public List<DatoEmpleado> getDatoEmpleados() {
        return datoEmpleados;
    }

    public void setDatoEmpleados(List<DatoEmpleado> datoEmpleados) {
        this.datoEmpleados = datoEmpleados;
    }
    
    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public boolean isValidador() {
        return validador;
    }

    public void setValidador(boolean validador) {
        this.validador = validador;
    }

    public List<Solicitud> buscarPorUsuario() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            solicitudes = solicitudFacade.buscarPorUsuario(u.getIdUsuario());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return solicitudes;
    }

    public String buscarPorSolicitud(int idSolicitud) {

        this.solicitud = (Solicitud) solicitudFacade.buscarPorSolicitud(idSolicitud);    
        if(this.solicitud.getTipoSolicitud().getIdTipoSolicitud()==3){
        this.vacacion=(Vacacion)this.solicitud.getVacacionList().get(0);}
        return "";
    }

    public void listar() {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        solicitudes = solicitudFacade.buscarSolicitud(u.getIdUsuario());
        if (tipo == 1) {
            graficarBeneficios(solicitudes);
        }
        if (tipo == 2) {
            graficarVacaciones(solicitudes);
        }
        if (tipo == 3) {
            graficarBonificaciones(solicitudes);
        }
    }

    public void graficarBeneficios(List<Solicitud> solicitudes) {
        pieChartModel = new PieChartModel();
        contador1 = 0;
        contador2 = 0;
        contador3 = 0;
        validador = false;
        for (Solicitud sol : solicitudes) {

            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Beneficios") && sol.getEstado().equalsIgnoreCase("En proceso")) {
                contador1++;
                pieChartModel.set("En proceso", contador1);
                validador = true;
            }
            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Beneficios") && sol.getEstado().equalsIgnoreCase("Rechazado")) {
                contador2++;
                pieChartModel.set("Denegadas", contador2);
                validador = true;
            }
            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Beneficios") && sol.getEstado().equalsIgnoreCase("Aceptado")) {
                contador3++;
                pieChartModel.set("Aprobadas", contador3);
                validador = true;
            }

        }
        pieChartModel.setTitle("Beneficios");
        pieChartModel.setLegendPosition("e");
        pieChartModel.setFill(false);
        pieChartModel.setShowDataLabels(true);
        pieChartModel.setDiameter(150);
    }

    public void graficarVacaciones(List<Solicitud> solicitudes) {
        pieChartModel = new PieChartModel();
        contador1 = 0;
        contador2 = 0;
        contador3 = 0;
        validador = false;
        for (Solicitud sol : solicitudes) {

            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Vacaciones") && sol.getEstado().equalsIgnoreCase("En proceso")) {
                contador1++;
                pieChartModel.set("En proceso", contador1);
                validador = true;
            }
            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Vacaciones") && sol.getEstado().equalsIgnoreCase("Rechazado")) {
                contador2++;
                pieChartModel.set("Denegadas", contador2);
                validador = true;
            }
            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Vacaciones") && sol.getEstado().equalsIgnoreCase("Aceptado")) {
                contador3++;
                pieChartModel.set("Aprobadas", contador3);
                validador = true;
            }

        }
        pieChartModel.setTitle("Vacaciones");
        pieChartModel.setLegendPosition("e");
        pieChartModel.setFill(false);
        pieChartModel.setShowDataLabels(true);
        pieChartModel.setDiameter(150);
    }

    public void graficarBonificaciones(List<Solicitud> solicitudes) {
        pieChartModel = new PieChartModel();
        contador1 = 0;
        contador2 = 0;
        contador3 = 0;
        validador = false;
        for (Solicitud sol : solicitudes) {

            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Bonificaciones") && sol.getEstado().equalsIgnoreCase("En proceso")) {
                contador1++;
                pieChartModel.set("En proceso", contador1);
                validador = true;
            }
            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Bonificaciones") && sol.getEstado().equalsIgnoreCase("Rechazado")) {
                contador2++;
                pieChartModel.set("Denegadas", contador2);
                validador = true;
            }
            if (sol.getTipoSolicitud().getTipoSolicitud().equals("Bonificaciones") && sol.getEstado().equalsIgnoreCase("Aceptado")) {
                contador3++;
                pieChartModel.set("Aprobadas", contador3);
                validador = true;
            }

        }
        pieChartModel.setTitle("Bonificaciones");
        pieChartModel.setLegendPosition("e");
        pieChartModel.setFill(false);
        pieChartModel.setShowDataLabels(true);
        pieChartModel.setDiameter(150);
    }

    public String actualizarDecision(Observacion obs,DatoEmpleado dt) {
        
        
        String correoJefe="";
        String correoRadicador="";
        try {
           //datoEmpleados.add((DatoEmpleado) datoEmpleadoControlador.buscarInformacionPorUsuario(solicitud.getUsuarioAsignado()));
            obs.setFecha(fecha);
            //obs.setIdentificacion(dt);
            obs.setIdSolicitud(this.solicitud);
            List<Observacion> o;
            o=(List<Observacion>)this.solicitud.getObservacionList();
            o.add(obs);
            String estadoSol="";
            if(solicitud.getDecision().equals("Aceptado")){
            estadoSol="Aprobadas";
            }else{ 
            estadoSol="Denegadas";
            }
              List<String>usuarioCorreo=null;
              correoJefe=(String)usuarioControlador.consultarCorreo(solicitud.getUsuarioAsignado());
              correoRadicador=(String)usuarioControlador.consultarCorreo(solicitud.getUsuarioRadicador().getIdUsuario());
            //  usuarioCorreo.add(correoJefe);
            //  usuarioCorreo.add(correoRadicador);
           // String contenido="";
            //contenido = correoControlador.getCorreo().agregarHtml("/com/wellbeing/util/formatos/notificacionCreacion.xhtml");
            //correoControlador.notificacionMasiva(usuarioCorreo, contenido);
            solicitud.setEstado(estadoSol);
            solicitud.setUsuarioAsignado(solicitud.getUsuarioRadicador().getIdUsuario());
            solicitud.setObservacionList(o);
            solicitudFacade.edit(solicitud);
            solicitudFacade.actualizarIdentficacionObservacion(solicitud.getIdentificacion().getIdentificacion());
           // solicitudFacade.actualizarDecision(solicitud.getIdSolicitud(), solicitud.getDecision(),estadoSol,solicitud.getUsuarioRadicador().getIdUsuario());
            
            validador=true;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error en ejecucion del metodo"+correoJefe+"--"+correoRadicador, "No se pudo actualizar correctamente el registro"));
        }
        return "";
    }
    
    
    public void cambiarValidador() {
        validador = false;
    }
}
