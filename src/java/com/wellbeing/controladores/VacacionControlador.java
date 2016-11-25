/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.controladores;

import com.wellbeing.entidades.Usuario;
import com.wellbeing.entidades.Vacacion;
import com.wellbeing.facade.DatoEmpleadoFacade;
import com.wellbeing.facade.VacacionFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cristian
 */
@Named(value = "vacacionControlador")
@SessionScoped
public class VacacionControlador implements Serializable {

    @EJB
    private VacacionFacade vacacionFacade;
    private Vacacion vacacion;
    @EJB
    private DatoEmpleadoFacade datoEmpleadoFacade;

    @PostConstruct
    public void init() {
        vacacion = new Vacacion();
    }

    public Vacacion getVacacion() {
        return vacacion;
    }

    public void setVacacion(Vacacion vacacion) {
        this.vacacion = vacacion;
    }

    public void registrarVacaciones() {
        try {
            vacacion.setDiasDisfrutados(null);
            vacacionFacade.create(vacacion);
            String documento = "";
            documento += vacacion.getIdentificacion();
            Integer tipoSolicitud = 3;
            vacacionFacade.registrarSolicitudVacaciones(documento, tipoSolicitud);
            vacacion.setFechaInicial(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Vacaciones", "Se ha registrado correctamente su solicitud "));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar correctamente su solicitud de vacaciones"));
        }

    }

    public void calcularDiasVacaciones() throws ParseException {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        vacacion.setIdentificacion(datoEmpleadoFacade.buscarDocumento(u.getIdUsuario()));
        Date fecha = datoEmpleadoFacade.buscarFechaIngreso(u.getIdUsuario());
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        Integer dia = fecha.getDate();
        Integer mes = fecha.getMonth() + 1;
        Integer anio = fecha.getYear() + 1900;
        Calendar fechaInicio = new GregorianCalendar();
        fechaInicio.set(anio, mes, dia);

//fecha fin
        Calendar fechaFin = new GregorianCalendar();

        fechaFin.set(fechaFin.get(Calendar.YEAR), fechaFin.get(Calendar.MONTH) + 1, 30);

//restamos las fechas como se puede ver son de tipo Calendar,
//debemos obtener el valor long con getTime.getTime.
        cal.setTimeInMillis(fechaFin.getTime().getTime() - fechaInicio.getTime().getTime());

//la resta provoca que guardamos este valor en c,
//los milisegundos corresponde al tiempo en dias
//asi sabemos cuantos dias
        vacacion.setPeriodo(String.valueOf(cal.get(Calendar.DAY_OF_YEAR) * 15 / 365));
        vacacion.setEstadoPeriodo("Disponible");
        vacacion.setDiasAcumulados(0);
        vacacion.setDiasDisfrutados(0);
        if (vacacionFacade.buscarDias(vacacion.getIdentificacion().getIdentificacion()) != null) {
            if (vacacionFacade.buscarDias(vacacion.getIdentificacion().getIdentificacion()).getDiasAcumulados() != null) {
                vacacion.setDiasAcumulados(vacacionFacade.buscarDias(vacacion.getIdentificacion().getIdentificacion()).getDiasAcumulados());
            }

            if (vacacionFacade.buscarDias(vacacion.getIdentificacion().getIdentificacion()).getDiasDisfrutados() != null) {
                vacacion.setDiasDisfrutados(vacacionFacade.buscarDias(vacacion.getIdentificacion().getIdentificacion()).getDiasDisfrutados());
            }

            vacacion.setPeriodo(vacacionFacade.buscarDias(vacacion.getIdentificacion().getIdentificacion()).getPeriodo());
            vacacion.setPeriodo(String.valueOf(Integer.parseInt(vacacion.getPeriodo()) + vacacion.getDiasAcumulados()));
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
            String fecha1 = (getFechaActual().getYear() + 1900) + "/" + mes + "/" + dia;
            Date date = null;
            date = formato.parse(fecha1);
            if (vacacionFacade.buscarDias(vacacion.getIdentificacion().getIdentificacion(), date) == null) {
                vacacion.setPeriodo(String.valueOf((cal.get(Calendar.DAY_OF_YEAR) * 15 / 365)+ vacacion.getDiasAcumulados()));
            }

        }

    }

    public void vacacionPorSolicitu(int idSolilcitud) {

        vacacion = new Vacacion();
        vacacionFacade.buscarPorSolicitud(idSolilcitud);

    }

    public String fechaInicio() {
        Date date = new Date();
        return date.getYear() + 1900 + "-" + (date.getMonth() + 1) + "-" + (date.getDate() + 1);
    }

    public String fechaFinal() {
        Date date = new Date();
        if ((date.getMonth() + 6) > 12) {
            date.setMonth((date.getMonth() + 6) - 12);
            date.setYear(date.getYear() + 1900 + 1);
        } else {
            date.setMonth((date.getMonth() + 6));
            date.setYear(date.getYear() + 1900);
        }

        return date.getYear() + "-" + date.getMonth() + "-" + (date.getDate() + 1);
    }

    public Date getFechaActual() throws ParseException {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        ahora = formateador.parse(formateador.format(ahora));
        return ahora;
    }
}
