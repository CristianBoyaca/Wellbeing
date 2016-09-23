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
@Table(name = "tiposbonificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBonificacion.findAll", query = "SELECT t FROM TipoBonificacion t"),
    @NamedQuery(name = "TipoBonificacion.findByIdTipoBonificacion", query = "SELECT t FROM TipoBonificacion t WHERE t.idTipoBonificacion = :idTipoBonificacion"),
    @NamedQuery(name = "TipoBonificacion.findByDescripcion", query = "SELECT t FROM TipoBonificacion t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoBonificacion.findByDisponible", query = "SELECT t FROM TipoBonificacion t WHERE t.disponible = :disponible")})
public class TipoBonificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoBonificacion")
    private Integer idTipoBonificacion;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "disponible")
    private Boolean disponible;
    @OneToMany(mappedBy = "tipoBonificacion")
    private List<Bonificacion> bonificacionList;

    public TipoBonificacion() {
    }

    public TipoBonificacion(Integer idTipoBonificacion) {
        this.idTipoBonificacion = idTipoBonificacion;
    }

    public Integer getIdTipoBonificacion() {
        return idTipoBonificacion;
    }

    public void setIdTipoBonificacion(Integer idTipoBonificacion) {
        this.idTipoBonificacion = idTipoBonificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @XmlTransient
    public List<Bonificacion> getBonificacionList() {
        return bonificacionList;
    }

    public void setBonificacionList(List<Bonificacion> bonificacionList) {
        this.bonificacionList = bonificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoBonificacion != null ? idTipoBonificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBonificacion)) {
            return false;
        }
        TipoBonificacion other = (TipoBonificacion) object;
        if ((this.idTipoBonificacion == null && other.idTipoBonificacion != null) || (this.idTipoBonificacion != null && !this.idTipoBonificacion.equals(other.idTipoBonificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.TipoBonificacion[ idTipoBonificacion=" + idTipoBonificacion + " ]";
    }
    
}
