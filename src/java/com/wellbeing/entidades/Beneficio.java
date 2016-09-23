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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "beneficios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beneficio.findAll", query = "SELECT b FROM Beneficio b"),
    @NamedQuery(name = "Beneficio.findByIdBeneficios", query = "SELECT b FROM Beneficio b WHERE b.idBeneficios = :idBeneficios"),
    @NamedQuery(name = "Beneficio.findByFechaRecibido", query = "SELECT b FROM Beneficio b WHERE b.fechaRecibido = :fechaRecibido")})
public class Beneficio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBeneficios")
    private Integer idBeneficios;
    @Column(name = "fechaRecibido")
    @Temporal(TemporalType.DATE)
    private Date fechaRecibido;
    @Lob
    @Column(name = "imgSoporte")
    private byte[] imgSoporte;
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @ManyToOne
    private DatoEmpleado identificacion;
    @JoinColumn(name = "idSolicitud", referencedColumnName = "idSolicitud")
    @ManyToOne
    private Solicitud idSolicitud;
    @JoinColumn(name = "tipoBeneficio", referencedColumnName = "idTipoBeneficio")
    @ManyToOne
    private TipoBeneficio tipoBeneficio;

    public Beneficio() {
    }

    public Beneficio(Integer idBeneficios) {
        this.idBeneficios = idBeneficios;
    }

    public Integer getIdBeneficios() {
        return idBeneficios;
    }

    public void setIdBeneficios(Integer idBeneficios) {
        this.idBeneficios = idBeneficios;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
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

    public Solicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public TipoBeneficio getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBeneficios != null ? idBeneficios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beneficio)) {
            return false;
        }
        Beneficio other = (Beneficio) object;
        if ((this.idBeneficios == null && other.idBeneficios != null) || (this.idBeneficios != null && !this.idBeneficios.equals(other.idBeneficios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.Beneficio[ idBeneficios=" + idBeneficios + " ]";
    }
    
}
