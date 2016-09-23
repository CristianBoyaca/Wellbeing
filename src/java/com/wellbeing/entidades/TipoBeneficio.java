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
@Table(name = "tiposbeneficios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBeneficio.findAll", query = "SELECT t FROM TipoBeneficio t"),
    @NamedQuery(name = "TipoBeneficio.findByIdTipoBeneficio", query = "SELECT t FROM TipoBeneficio t WHERE t.idTipoBeneficio = :idTipoBeneficio"),
    @NamedQuery(name = "TipoBeneficio.findByDescripcion", query = "SELECT t FROM TipoBeneficio t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoBeneficio.findByDisponible", query = "SELECT t FROM TipoBeneficio t WHERE t.disponible = :disponible")})
public class TipoBeneficio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoBeneficio")
    private Integer idTipoBeneficio;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "disponible")
    private Boolean disponible;
    @OneToMany(mappedBy = "tipoBeneficio")
    private List<Beneficio> beneficioList;

    public TipoBeneficio() {
    }

    public TipoBeneficio(Integer idTipoBeneficio) {
        this.idTipoBeneficio = idTipoBeneficio;
    }

    public Integer getIdTipoBeneficio() {
        return idTipoBeneficio;
    }

    public void setIdTipoBeneficio(Integer idTipoBeneficio) {
        this.idTipoBeneficio = idTipoBeneficio;
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
    public List<Beneficio> getBeneficioList() {
        return beneficioList;
    }

    public void setBeneficioList(List<Beneficio> beneficioList) {
        this.beneficioList = beneficioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoBeneficio != null ? idTipoBeneficio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBeneficio)) {
            return false;
        }
        TipoBeneficio other = (TipoBeneficio) object;
        if ((this.idTipoBeneficio == null && other.idTipoBeneficio != null) || (this.idTipoBeneficio != null && !this.idTipoBeneficio.equals(other.idTipoBeneficio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.TipoBeneficio[ idTipoBeneficio=" + idTipoBeneficio + " ]";
    }
    
}
