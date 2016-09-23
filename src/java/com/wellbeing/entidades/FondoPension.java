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
@Table(name = "fondopensiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FondoPension.findAll", query = "SELECT f FROM FondoPension f"),
    @NamedQuery(name = "FondoPension.findByIdFondo", query = "SELECT f FROM FondoPension f WHERE f.idFondo = :idFondo"),
    @NamedQuery(name = "FondoPension.findByNombreFondo", query = "SELECT f FROM FondoPension f WHERE f.nombreFondo = :nombreFondo")})
public class FondoPension implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFondo")
    private Integer idFondo;
    @Size(max = 100)
    @Column(name = "nombreFondo")
    private String nombreFondo;
    @OneToMany(mappedBy = "idFondo")
    private List<HistorialPension> historialPensionList;
    @OneToMany(mappedBy = "idFondo")
    private List<DatoEmpleado> datoEmpleadoList;

    public FondoPension() {
    }

    public FondoPension(Integer idFondo) {
        this.idFondo = idFondo;
    }

    public Integer getIdFondo() {
        return idFondo;
    }

    public void setIdFondo(Integer idFondo) {
        this.idFondo = idFondo;
    }

    public String getNombreFondo() {
        return nombreFondo;
    }

    public void setNombreFondo(String nombreFondo) {
        this.nombreFondo = nombreFondo;
    }

    @XmlTransient
    public List<HistorialPension> getHistorialPensionList() {
        return historialPensionList;
    }

    public void setHistorialPensionList(List<HistorialPension> historialPensionList) {
        this.historialPensionList = historialPensionList;
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
        hash += (idFondo != null ? idFondo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FondoPension)) {
            return false;
        }
        FondoPension other = (FondoPension) object;
        if ((this.idFondo == null && other.idFondo != null) || (this.idFondo != null && !this.idFondo.equals(other.idFondo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.FondoPension[ idFondo=" + idFondo + " ]";
    }
    
}
