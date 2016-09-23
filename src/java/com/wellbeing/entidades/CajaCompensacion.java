/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "cajascompensacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajaCompensacion.findAll", query = "SELECT c FROM CajaCompensacion c"),
    @NamedQuery(name = "CajaCompensacion.findByIdCajaCompensacion", query = "SELECT c FROM CajaCompensacion c WHERE c.idCajaCompensacion = :idCajaCompensacion"),
    @NamedQuery(name = "CajaCompensacion.findByNombreCajaCompensacion", query = "SELECT c FROM CajaCompensacion c WHERE c.nombreCajaCompensacion = :nombreCajaCompensacion")})
public class CajaCompensacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCajaCompensacion")
    private Integer idCajaCompensacion;
    @Size(max = 50)
    @Column(name = "nombreCajaCompensacion")
    private String nombreCajaCompensacion;
    @OneToMany(mappedBy = "idCajaCompensacion")
    private List<HistorialCajaCompensacion> historialCajaCompensacionList;
    @OneToMany(mappedBy = "cajaCompensacion")
    private List<DatoEmpleado> datoEmpleadoList;

    public CajaCompensacion() {
    }

    public CajaCompensacion(Integer idCajaCompensacion) {
        this.idCajaCompensacion = idCajaCompensacion;
    }

    public Integer getIdCajaCompensacion() {
        return idCajaCompensacion;
    }

    public void setIdCajaCompensacion(Integer idCajaCompensacion) {
        this.idCajaCompensacion = idCajaCompensacion;
    }

    public String getNombreCajaCompensacion() {
        return nombreCajaCompensacion;
    }

    public void setNombreCajaCompensacion(String nombreCajaCompensacion) {
        this.nombreCajaCompensacion = nombreCajaCompensacion;
    }

    @XmlTransient
    public List<HistorialCajaCompensacion> getHistorialCajaCompensacionList() {
        return historialCajaCompensacionList;
    }

    public void setHistorialCajaCompensacionList(List<HistorialCajaCompensacion> historialCajaCompensacionList) {
        this.historialCajaCompensacionList = historialCajaCompensacionList;
    }

    @XmlTransient
    public List<DatoEmpleado> getDatoEmpleadoList() {
        return datoEmpleadoList;
    }

    public void setDatoEmpleadoList(List<DatoEmpleado> datoEmpleadoList) {
        this.datoEmpleadoList = datoEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCajaCompensacion != null ? idCajaCompensacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajaCompensacion)) {
            return false;
        }
        CajaCompensacion other = (CajaCompensacion) object;
        if ((this.idCajaCompensacion == null && other.idCajaCompensacion != null) || (this.idCajaCompensacion != null && !this.idCajaCompensacion.equals(other.idCajaCompensacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.CajaCompensacion[ idCajaCompensacion=" + idCajaCompensacion + " ]";
    }
    
}
