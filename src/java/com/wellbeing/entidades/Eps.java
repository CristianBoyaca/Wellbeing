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
@Table(name = "eps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eps.findAll", query = "SELECT e FROM Eps e"),
    @NamedQuery(name = "Eps.findByIdEps", query = "SELECT e FROM Eps e WHERE e.idEps = :idEps"),
    @NamedQuery(name = "Eps.findByNombre", query = "SELECT e FROM Eps e WHERE e.nombre = :nombre")})
public class Eps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEps")
    private Integer idEps;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idEps")
    private List<HistorialEps> historialEpsList;
    @OneToMany(mappedBy = "eps")
    private List<DatoEmpleado> datoEmpleadoList;

    public Eps() {
    }

    public Eps(Integer idEps) {
        this.idEps = idEps;
    }

    public Integer getIdEps() {
        return idEps;
    }

    public void setIdEps(Integer idEps) {
        this.idEps = idEps;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<HistorialEps> getHistorialEpsList() {
        return historialEpsList;
    }

    public void setHistorialEpsList(List<HistorialEps> historialEpsList) {
        this.historialEpsList = historialEpsList;
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
        hash += (idEps != null ? idEps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eps)) {
            return false;
        }
        Eps other = (Eps) object;
        if ((this.idEps == null && other.idEps != null) || (this.idEps != null && !this.idEps.equals(other.idEps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.Eps[ idEps=" + idEps + " ]";
    }
    
}
