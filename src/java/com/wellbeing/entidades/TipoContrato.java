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
@Table(name = "tiposcontrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContrato.findAll", query = "SELECT t FROM TipoContrato t"),
    @NamedQuery(name = "TipoContrato.findByIdTipocontrato", query = "SELECT t FROM TipoContrato t WHERE t.idTipocontrato = :idTipocontrato"),
    @NamedQuery(name = "TipoContrato.findByNombreContrato", query = "SELECT t FROM TipoContrato t WHERE t.nombreContrato = :nombreContrato")})
public class TipoContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipocontrato")
    private Integer idTipocontrato;
    @Size(max = 50)
    @Column(name = "NombreContrato")
    private String nombreContrato;
    @OneToMany(mappedBy = "tipoContrato")
    private List<DatoEmpleado> datoEmpleadoList;

    public TipoContrato() {
    }

    public TipoContrato(Integer idTipocontrato) {
        this.idTipocontrato = idTipocontrato;
    }

    public Integer getIdTipocontrato() {
        return idTipocontrato;
    }

    public void setIdTipocontrato(Integer idTipocontrato) {
        this.idTipocontrato = idTipocontrato;
    }

    public String getNombreContrato() {
        return nombreContrato;
    }

    public void setNombreContrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
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
        hash += (idTipocontrato != null ? idTipocontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoContrato)) {
            return false;
        }
        TipoContrato other = (TipoContrato) object;
        if ((this.idTipocontrato == null && other.idTipocontrato != null) || (this.idTipocontrato != null && !this.idTipocontrato.equals(other.idTipocontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.TipoContrato[ idTipocontrato=" + idTipocontrato + " ]";
    }
    
}
