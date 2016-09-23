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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "incapacidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incapacidad.findAll", query = "SELECT i FROM Incapacidad i"),
    @NamedQuery(name = "Incapacidad.findByIdIncapacidad", query = "SELECT i FROM Incapacidad i WHERE i.idIncapacidad = :idIncapacidad"),
    @NamedQuery(name = "Incapacidad.findByFechaInicio", query = "SELECT i FROM Incapacidad i WHERE i.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Incapacidad.findByFechaFin", query = "SELECT i FROM Incapacidad i WHERE i.fechaFin = :fechaFin")})
public class Incapacidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIncapacidad")
    private Integer idIncapacidad;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "imgSoporte")
    private byte[] imgSoporte;
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @ManyToOne
    private DatoEmpleado identificacion;

    public Incapacidad() {
    }

    public Incapacidad(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public Incapacidad(Integer idIncapacidad, byte[] imgSoporte) {
        this.idIncapacidad = idIncapacidad;
        this.imgSoporte = imgSoporte;
    }

    public Integer getIdIncapacidad() {
        return idIncapacidad;
    }

    public void setIdIncapacidad(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public byte[] getImgSoporte() {
        return imgSoporte;
    }

    public void setImgSoporte(byte[] imgSoporte) {
        this.imgSoporte = imgSoporte;
    }

    public DatoEmpleado getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(DatoEmpleado identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncapacidad != null ? idIncapacidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incapacidad)) {
            return false;
        }
        Incapacidad other = (Incapacidad) object;
        if ((this.idIncapacidad == null && other.idIncapacidad != null) || (this.idIncapacidad != null && !this.idIncapacidad.equals(other.idIncapacidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.Incapacidad[ idIncapacidad=" + idIncapacidad + " ]";
    }
    
}
