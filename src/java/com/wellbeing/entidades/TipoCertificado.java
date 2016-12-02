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
@Table(name = "tiposcertificados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCertificado.findAll", query = "SELECT t FROM TipoCertificado t"),
    @NamedQuery(name = "TipoCertificado.findByIdTipoCertificados", query = "SELECT t FROM TipoCertificado t WHERE t.idTipoCertificados = :idTipoCertificados"),
    @NamedQuery(name = "TipoCertificado.findByDescripcion", query = "SELECT t FROM TipoCertificado t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoCertificado.findByDisponible", query = "SELECT t FROM TipoCertificado t WHERE t.disponible = :disponible")})
public class TipoCertificado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoCertificados")
    private Integer idTipoCertificados;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "disponible")
    private Boolean disponible;
    @OneToMany(mappedBy = "tipoCertificado")
    private List<Certificado> certificadoList;

    public TipoCertificado() {
    }

    public TipoCertificado(Integer idTipoCertificados) {
        this.idTipoCertificados = idTipoCertificados;
    }

    public Integer getIdTipoCertificados() {
        return idTipoCertificados;
    }

    public void setIdTipoCertificados(Integer idTipoCertificados) {
        this.idTipoCertificados = idTipoCertificados;
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
    public List<Certificado> getCertificadoList() {
        return certificadoList;
    }

    public void setCertificadoList(List<Certificado> certificadoList) {
        this.certificadoList = certificadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCertificados != null ? idTipoCertificados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCertificado)) {
            return false;
        }
        TipoCertificado other = (TipoCertificado) object;
        if ((this.idTipoCertificados == null && other.idTipoCertificados != null) || (this.idTipoCertificados != null && !this.idTipoCertificados.equals(other.idTipoCertificados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.TipoCertificado[ idTipoCertificados=" + idTipoCertificados + " ]";
    }

}
