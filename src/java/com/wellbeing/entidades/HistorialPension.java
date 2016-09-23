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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "historialespensiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialPension.findAll", query = "SELECT h FROM HistorialPension h"),
    @NamedQuery(name = "HistorialPension.findByIdHistorialPensiones", query = "SELECT h FROM HistorialPension h WHERE h.idHistorialPensiones = :idHistorialPensiones"),
    @NamedQuery(name = "HistorialPension.findByFechaIngreso", query = "SELECT h FROM HistorialPension h WHERE h.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "HistorialPension.findByFechaSalida", query = "SELECT h FROM HistorialPension h WHERE h.fechaSalida = :fechaSalida")})
public class HistorialPension implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHistorialPensiones")
    private Integer idHistorialPensiones;
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "fechaSalida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @ManyToOne
    private DatoEmpleado identificacion;
    @JoinColumn(name = "idFondo", referencedColumnName = "idFondo")
    @ManyToOne
    private FondoPension idFondo;

    public HistorialPension() {
    }

    public HistorialPension(Integer idHistorialPensiones) {
        this.idHistorialPensiones = idHistorialPensiones;
    }

    public Integer getIdHistorialPensiones() {
        return idHistorialPensiones;
    }

    public void setIdHistorialPensiones(Integer idHistorialPensiones) {
        this.idHistorialPensiones = idHistorialPensiones;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public DatoEmpleado getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(DatoEmpleado identificacion) {
        this.identificacion = identificacion;
    }

    public FondoPension getIdFondo() {
        return idFondo;
    }

    public void setIdFondo(FondoPension idFondo) {
        this.idFondo = idFondo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialPensiones != null ? idHistorialPensiones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialPension)) {
            return false;
        }
        HistorialPension other = (HistorialPension) object;
        if ((this.idHistorialPensiones == null && other.idHistorialPensiones != null) || (this.idHistorialPensiones != null && !this.idHistorialPensiones.equals(other.idHistorialPensiones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.HistorialPension[ idHistorialPensiones=" + idHistorialPensiones + " ]";
    }
    
}
