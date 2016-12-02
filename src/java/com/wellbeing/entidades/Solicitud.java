/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wellbeing.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findByIdSolicitud", query = "SELECT s FROM Solicitud s WHERE s.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "Solicitud.findByFechaRadicacion", query = "SELECT s FROM Solicitud s WHERE s.fechaRadicacion = :fechaRadicacion"),
    @NamedQuery(name = "Solicitud.findByEstado", query = "SELECT s FROM Solicitud s WHERE s.estado = :estado"),
    @NamedQuery(name = "Solicitud.findByDecision", query = "SELECT s FROM Solicitud s WHERE s.decision = :decision"),
    @NamedQuery(name = "Solicitud.findByUsuarioAsignado", query = "SELECT s FROM Solicitud s WHERE s.usuarioAsignado = :usuarioAsignado")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSolicitud")
    private Integer idSolicitud;
    @Column(name = "fechaRadicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRadicacion;
    @Size(max = 10)
    @Column(name = "estado")
    private String estado;
    @Size(max = 50)
    @Column(name = "decision")
    private String decision;
    @Size(max = 20)
    @Column(name = "usuarioAsignado")
    private String usuarioAsignado;
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @ManyToOne
    private DatoEmpleado identificacion;
    @JoinColumn(name = "tipoSolicitud", referencedColumnName = "idTipoSolicitud")
    @ManyToOne
    private TipoSolicitud tipoSolicitud;
    @JoinColumn(name = "usuarioRadicador", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario usuarioRadicador;
    @OneToMany(mappedBy = "idSolicitud")
    private List<Certificado> certificadoList;
    @OneToMany(mappedBy = "idSolicitud")
    private List<Horario> horarioList;
    @OneToMany(mappedBy = "idSolicitud")
    private List<Bonificacion> bonificacionList;
    @OneToMany(mappedBy = "idSolicitud")
    private List<Observacion> observacionList;
    @OneToMany(mappedBy = "idSolicitud")
    private List<Vacacion> vacacionList;
    @OneToMany(mappedBy = "idSolicitud")
    private List<Beneficio> beneficioList;

    public Solicitud() {
    }

    public Solicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(String usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public DatoEmpleado getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(DatoEmpleado identificacion) {
        this.identificacion = identificacion;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Usuario getUsuarioRadicador() {
        return usuarioRadicador;
    }

    public void setUsuarioRadicador(Usuario usuarioRadicador) {
        this.usuarioRadicador = usuarioRadicador;
    }

    @XmlTransient
    public List<Certificado> getCertificadoList() {
        return certificadoList;
    }

    public void setCertificadoList(List<Certificado> certificadoList) {
        this.certificadoList = certificadoList;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    @XmlTransient
    public List<Bonificacion> getBonificacionList() {
        return bonificacionList;
    }

    public void setBonificacionList(List<Bonificacion> bonificacionList) {
        this.bonificacionList = bonificacionList;
    }

    @XmlTransient
    public List<Observacion> getObservacionList() {
        return observacionList;
    }

    public void setObservacionList(List<Observacion> observacionList) {
        this.observacionList = observacionList;
    }

    @XmlTransient
    public List<Vacacion> getVacacionList() {
        return vacacionList;
    }

    public void setVacacionList(List<Vacacion> vacacionList) {
        this.vacacionList = vacacionList;
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
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return "com.wellbeing.entidades.Solicitud[ idSolicitud=" + idSolicitud + " ]";
    }
    
}
