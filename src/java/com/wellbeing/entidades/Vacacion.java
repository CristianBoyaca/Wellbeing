/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "vacaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacacion.findAll", query = "SELECT v FROM Vacacion v"),
    @NamedQuery(name = "Vacacion.findByIdVacaciones", query = "SELECT v FROM Vacacion v WHERE v.idVacaciones = :idVacaciones"),
    @NamedQuery(name = "Vacacion.findByFechaInicial", query = "SELECT v FROM Vacacion v WHERE v.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "Vacacion.findByFechaFinal", query = "SELECT v FROM Vacacion v WHERE v.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Vacacion.findByPeriodo", query = "SELECT v FROM Vacacion v WHERE v.periodo = :periodo"),
    @NamedQuery(name = "Vacacion.findByEstadoPeriodo", query = "SELECT v FROM Vacacion v WHERE v.estadoPeriodo = :estadoPeriodo")})
public class Vacacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVacaciones")
    private Integer idVacaciones;
    @Column(name = "fechaInicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fechaFinal")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Size(max = 2)
    @Column(name = "periodo")
    private String periodo;
    @Size(max = 10)
    @Column(name = "estadoPeriodo")
    private String estadoPeriodo;
     @Column(name = "diasAcumulados")
    private Integer diasAcumulados;
    @Column(name = "diasDisfrutados")
    private Integer diasDisfrutados;
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @ManyToOne
    private DatoEmpleado identificacion;
    @JoinColumn(name = "idSolicitud", referencedColumnName = "idSolicitud")
    @ManyToOne
    private Solicitud idSolicitud;

    public Vacacion() {
    }

    public Vacacion(Integer idVacaciones) {
        this.idVacaciones = idVacaciones;
    }

    public Integer getIdVacaciones() {
        return idVacaciones;
    }

    public void setIdVacaciones(Integer idVacaciones) {
        this.idVacaciones = idVacaciones;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEstadoPeriodo() {
        return estadoPeriodo;
    }

    public void setEstadoPeriodo(String estadoPeriodo) {
        this.estadoPeriodo = estadoPeriodo;
    }
    
        public Integer getDiasAcumulados() {
        return diasAcumulados;
    }

    public void setDiasAcumulados(Integer diasAcumulados) {
        this.diasAcumulados = diasAcumulados;
    }

    public Integer getDiasDisfrutados() {
        return diasDisfrutados;
    }

    public void setDiasDisfrutados(Integer diasDisfrutados) {
        this.diasDisfrutados = diasDisfrutados;
    }

    public DatoEmpleado getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(DatoEmpleado identificacion) {
        this.identificacion = identificacion;
    }

    public Solicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacaciones != null ? idVacaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacacion)) {
            return false;
        }
        Vacacion other = (Vacacion) object;
        if ((this.idVacaciones == null && other.idVacaciones != null) || (this.idVacaciones != null && !this.idVacaciones.equals(other.idVacaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.Vacacion[ idVacaciones=" + idVacaciones + " ]";
    }
  
}
