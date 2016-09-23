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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "datosempleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatoEmpleado.findAll", query = "SELECT d FROM DatoEmpleado d"),
    @NamedQuery(name = "DatoEmpleado.findByIdentificacion", query = "SELECT d FROM DatoEmpleado d WHERE d.identificacion = :identificacion"),
    @NamedQuery(name = "DatoEmpleado.findByPrimerNombre", query = "SELECT d FROM DatoEmpleado d WHERE d.primerNombre = :primerNombre"),
    @NamedQuery(name = "DatoEmpleado.findBySegundoNombre", query = "SELECT d FROM DatoEmpleado d WHERE d.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "DatoEmpleado.findByPrimerApellido", query = "SELECT d FROM DatoEmpleado d WHERE d.primerApellido = :primerApellido"),
    @NamedQuery(name = "DatoEmpleado.findBySegundoApellido", query = "SELECT d FROM DatoEmpleado d WHERE d.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "DatoEmpleado.findByFechaNacimiento", query = "SELECT d FROM DatoEmpleado d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "DatoEmpleado.findByDireccionResidencia", query = "SELECT d FROM DatoEmpleado d WHERE d.direccionResidencia = :direccionResidencia"),
    @NamedQuery(name = "DatoEmpleado.findByBarrio", query = "SELECT d FROM DatoEmpleado d WHERE d.barrio = :barrio"),
    @NamedQuery(name = "DatoEmpleado.findBySalario", query = "SELECT d FROM DatoEmpleado d WHERE d.salario = :salario"),
    @NamedQuery(name = "DatoEmpleado.findByEmailPersonal", query = "SELECT d FROM DatoEmpleado d WHERE d.emailPersonal = :emailPersonal"),
    @NamedQuery(name = "DatoEmpleado.findByEmailCorporativo", query = "SELECT d FROM DatoEmpleado d WHERE d.emailCorporativo = :emailCorporativo"),
    @NamedQuery(name = "DatoEmpleado.findByNumeroDeCuenta", query = "SELECT d FROM DatoEmpleado d WHERE d.numeroDeCuenta = :numeroDeCuenta"),
    @NamedQuery(name = "DatoEmpleado.findByTelefono", query = "SELECT d FROM DatoEmpleado d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DatoEmpleado.findByFechaIngreso", query = "SELECT d FROM DatoEmpleado d WHERE d.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "DatoEmpleado.findByJefeInmediato", query = "SELECT d FROM DatoEmpleado d WHERE d.jefeInmediato = :jefeInmediato")})
public class DatoEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "identificacion")
    private String identificacion;
    @Size(max = 50)
    @Column(name = "primerNombre")
    private String primerNombre;
    @Size(max = 50)
    @Column(name = "segundoNombre")
    private String segundoNombre;
    @Size(max = 50)
    @Column(name = "primerApellido")
    private String primerApellido;
    @Size(max = 50)
    @Column(name = "segundoApellido")
    private String segundoApellido;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 100)
    @Column(name = "direccionResidencia")
    private String direccionResidencia;
    @Size(max = 45)
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "salario")
    private Integer salario;
    @Size(max = 50)
    @Column(name = "emailPersonal")
    private String emailPersonal;
    @Size(max = 50)
    @Column(name = "emailCorporativo")
    private String emailCorporativo;
    @Size(max = 50)
    @Column(name = "numeroDeCuenta")
    private String numeroDeCuenta;
    @Size(max = 23)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Size(max = 50)
    @Column(name = "jefeInmediato")
    private String jefeInmediato;
    @OneToMany(mappedBy = "identificacion")
    private List<HistorialCajaCompensacion> historialCajaCompensacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dATOSEMPLEADOSidentificacion")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "identificacion")
    private List<Solicitud> solicitudList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "identificacion")
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "identificacion")
    private List<HistorialEps> historialEpsList;
    @OneToMany(mappedBy = "identificacion")
    private List<Incapacidad> incapacidadList;
    @OneToMany(mappedBy = "identificacion")
    private List<Horario> horarioList;
    @OneToMany(mappedBy = "identificacion")
    private List<HistorialPension> historialPensionList;
    @OneToMany(mappedBy = "identificacion")
    private List<Bonificacion> bonificacionList;
    @OneToMany(mappedBy = "identificacion")
    private List<EstudioRealizado> estudioRealizadoList;
    @OneToMany(mappedBy = "identificacion")
    private List<Observacion> observacionList;
    @OneToMany(mappedBy = "identificacion")
    private List<Vacacion> vacacionList;
    @JoinColumn(name = "eps", referencedColumnName = "idEps")
    @ManyToOne
    private Eps eps;
    @JoinColumn(name = "TipoContrato", referencedColumnName = "IdTipocontrato")
    @ManyToOne
    private TipoContrato tipoContrato;
    @JoinColumn(name = "area", referencedColumnName = "idArea")
    @ManyToOne
    private Area area;
    @JoinColumn(name = "idBanco", referencedColumnName = "idBanco")
    @ManyToOne
    private Banco idBanco;
    @JoinColumn(name = "cargo", referencedColumnName = "idCargo")
    @ManyToOne
    private Cargo cargo;
    @JoinColumn(name = "ciudadExpedicionDoc", referencedColumnName = "idCiudad")
    @ManyToOne
    private Ciudad ciudadExpedicionDoc;
    @JoinColumn(name = "idCiudad", referencedColumnName = "idCiudad")
    @ManyToOne
    private Ciudad idCiudad;
    @JoinColumn(name = "cajaCompensacion", referencedColumnName = "idCajaCompensacion")
    @ManyToOne
    private CajaCompensacion cajaCompensacion;
    @JoinColumn(name = "tipoDocumento", referencedColumnName = "idDocumento")
    @ManyToOne
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento")
    @ManyToOne
    private Departamento idDepartamento;
    @JoinColumn(name = "estadoCivil", referencedColumnName = "idEstadoCivil")
    @ManyToOne
    private EstadoCivil estadoCivil;
    @JoinColumn(name = "idFondo", referencedColumnName = "idFondo")
    @ManyToOne
    private FondoPension idFondo;
    @OneToMany(mappedBy = "identificacion")
    private List<Beneficio> beneficioList;

    public DatoEmpleado() {
    }

    public DatoEmpleado(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    @XmlTransient
    public List<HistorialCajaCompensacion> getHistorialCajaCompensacionList() {
        return historialCajaCompensacionList;
    }

    public void setHistorialCajaCompensacionList(List<HistorialCajaCompensacion> historialCajaCompensacionList) {
        this.historialCajaCompensacionList = historialCajaCompensacionList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    @XmlTransient
    public List<HistorialEps> getHistorialEpsList() {
        return historialEpsList;
    }

    public void setHistorialEpsList(List<HistorialEps> historialEpsList) {
        this.historialEpsList = historialEpsList;
    }

    @XmlTransient
    public List<Incapacidad> getIncapacidadList() {
        return incapacidadList;
    }

    public void setIncapacidadList(List<Incapacidad> incapacidadList) {
        this.incapacidadList = incapacidadList;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    @XmlTransient
    public List<HistorialPension> getHistorialPensionList() {
        return historialPensionList;
    }

    public void setHistorialPensionList(List<HistorialPension> historialPensionList) {
        this.historialPensionList = historialPensionList;
    }

    @XmlTransient
    public List<Bonificacion> getBonificacionList() {
        return bonificacionList;
    }

    public void setBonificacionList(List<Bonificacion> bonificacionList) {
        this.bonificacionList = bonificacionList;
    }

    @XmlTransient
    public List<EstudioRealizado> getEstudioRealizadoList() {
        return estudioRealizadoList;
    }

    public void setEstudioRealizadoList(List<EstudioRealizado> estudioRealizadoList) {
        this.estudioRealizadoList = estudioRealizadoList;
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

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Banco getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Banco idBanco) {
        this.idBanco = idBanco;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Ciudad getCiudadExpedicionDoc() {
        return ciudadExpedicionDoc;
    }

    public void setCiudadExpedicionDoc(Ciudad ciudadExpedicionDoc) {
        this.ciudadExpedicionDoc = ciudadExpedicionDoc;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public CajaCompensacion getCajaCompensacion() {
        return cajaCompensacion;
    }

    public void setCajaCompensacion(CajaCompensacion cajaCompensacion) {
        this.cajaCompensacion = cajaCompensacion;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public FondoPension getIdFondo() {
        return idFondo;
    }

    public void setIdFondo(FondoPension idFondo) {
        this.idFondo = idFondo;
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
        hash += (identificacion != null ? identificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoEmpleado)) {
            return false;
        }
        DatoEmpleado other = (DatoEmpleado) object;
        if ((this.identificacion == null && other.identificacion != null) || (this.identificacion != null && !this.identificacion.equals(other.identificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return identificacion;
    }
    
}
