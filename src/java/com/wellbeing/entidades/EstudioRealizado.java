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
@Table(name = "estudiosrealizados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudioRealizado.findAll", query = "SELECT e FROM EstudioRealizado e"),
    @NamedQuery(name = "EstudioRealizado.findByIdEstudio", query = "SELECT e FROM EstudioRealizado e WHERE e.idEstudio = :idEstudio"),
    @NamedQuery(name = "EstudioRealizado.findByTituloObtenido", query = "SELECT e FROM EstudioRealizado e WHERE e.tituloObtenido = :tituloObtenido"),
    @NamedQuery(name = "EstudioRealizado.findByFechaInicio", query = "SELECT e FROM EstudioRealizado e WHERE e.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "EstudioRealizado.findByFechaFin", query = "SELECT e FROM EstudioRealizado e WHERE e.fechaFin = :fechaFin"),
    @NamedQuery(name = "EstudioRealizado.findByLugar", query = "SELECT e FROM EstudioRealizado e WHERE e.lugar = :lugar")})
public class EstudioRealizado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstudio")
    private Integer idEstudio;
    @Size(max = 50)
    @Column(name = "tituloObtenido")
    private String tituloObtenido;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 50)
    @Column(name = "lugar")
    private String lugar;
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @ManyToOne
    private DatoEmpleado identificacion;

    public EstudioRealizado() {
    }

    public EstudioRealizado(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getTituloObtenido() {
        return tituloObtenido;
    }

    public void setTituloObtenido(String tituloObtenido) {
        this.tituloObtenido = tituloObtenido;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
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
        hash += (idEstudio != null ? idEstudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudioRealizado)) {
            return false;
        }
        EstudioRealizado other = (EstudioRealizado) object;
        if ((this.idEstudio == null && other.idEstudio != null) || (this.idEstudio != null && !this.idEstudio.equals(other.idEstudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.EstudioRealizado[ idEstudio=" + idEstudio + " ]";
    }
    
}
