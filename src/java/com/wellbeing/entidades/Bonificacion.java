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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "bonificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bonificacion.findAll", query = "SELECT b FROM Bonificacion b"),
    @NamedQuery(name = "Bonificacion.findByIdBonificacion", query = "SELECT b FROM Bonificacion b WHERE b.idBonificacion = :idBonificacion"),
    @NamedQuery(name = "Bonificacion.findByFechaEntrega", query = "SELECT b FROM Bonificacion b WHERE b.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Bonificacion.findByCantidad", query = "SELECT b FROM Bonificacion b WHERE b.cantidad = :cantidad"),
    @NamedQuery(name = "Bonificacion.findByValor", query = "SELECT b FROM Bonificacion b WHERE b.valor = :valor")})
public class Bonificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBonificacion")
    private Integer idBonificacion;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "valor")
    private Integer valor;
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @ManyToOne
    private DatoEmpleado identificacion;
    @JoinColumn(name = "idSolicitud", referencedColumnName = "idSolicitud")
    @ManyToOne
    private Solicitud idSolicitud;
    @JoinColumn(name = "tipoBonificacion", referencedColumnName = "idTipoBonificacion")
    @ManyToOne
    private TipoBonificacion tipoBonificacion;

    public Bonificacion() {
    }

    public Bonificacion(Integer idBonificacion) {
        this.idBonificacion = idBonificacion;
    }

    public Integer getIdBonificacion() {
        return idBonificacion;
    }

    public void setIdBonificacion(Integer idBonificacion) {
        this.idBonificacion = idBonificacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
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

    public TipoBonificacion getTipoBonificacion() {
        return tipoBonificacion;
    }

    public void setTipoBonificacion(TipoBonificacion tipoBonificacion) {
        this.tipoBonificacion = tipoBonificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBonificacion != null ? idBonificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bonificacion)) {
            return false;
        }
        Bonificacion other = (Bonificacion) object;
        if ((this.idBonificacion == null && other.idBonificacion != null) || (this.idBonificacion != null && !this.idBonificacion.equals(other.idBonificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wellbeing.entidades.Bonificacion[ idBonificacion=" + idBonificacion + " ]";
    }
    
}
