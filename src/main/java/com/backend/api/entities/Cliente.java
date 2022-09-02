package com.backend.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente")
@NamedQueries({
	@NamedQuery(name = "Cliente.getAll",
				query = "SELECT c FROM Cliente c " ),
	@NamedQuery(name = "Cliente.getByIdCliente",
				query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
	@NamedQuery(name = "Cliente.deleteByIdCliente",
				query = "DELETE from Cliente where idCliente = :idCliente")
	
})

public class Cliente {

	@Id
	@GeneratedValue(generator = "seq_cliente", strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "api", nullable = false)
	private String api;
	
	@Column(name = "id_empresa", nullable = false)
	private String idEmpresa;
	
	@Column(name = "id_cliente", nullable = false)
	private String idCliente;
	
	@Column(name = "firma_servicio", nullable = false)
	private String firmaServicio;
	
	@Column(name = "nombre_descripcion", nullable = false)
	private String nombreDescripcion;

	@Column(name = "activo", nullable = false)
	private Boolean activo;
	
	@Column(name = "usuario_login", nullable = true)
	private String usuarioLogin;
	
	
	@Column(name = "identificador_cliente", nullable = true)
	private String identificadorCliente;
	
	@Column(name = "cargar_doc")
	private Boolean cargarDoc;
	
	@Column(name = "invitar_firmar")
	private Boolean invitarFirmar;
	
	@Column(name = "compartir_rrss")
	private Boolean compartirRrss;
	
	@Column(name = "compartir_correo")
	private Boolean compartirCorreo;
	
	//genera el campo que almacena la url del esigner
	@Column(name = "usuario_password", nullable = true)
	private String usuarioPassword;
	
	//permiete almacenar la url del webservice que necesita utilizar para consultar por los certificados
	@Column(name = "url_esigner", nullable = true)
	private String urlEsigner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getNombreDescripcion() {
		return nombreDescripcion;
	}

	public void setNombreDescripcion(String nombreDescripcion) {
		this.nombreDescripcion = nombreDescripcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getFirmaServicio() {
		return firmaServicio;
	}

	public void setFirmaServicio(String firmaServicio) {
		this.firmaServicio = firmaServicio;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getIdentificadorCliente() {
		return identificadorCliente;
	}

	public void setIdentificadorCliente(String identificadorCliente) {
		this.identificadorCliente = identificadorCliente;
	}

	public Boolean getCargarDoc() {
		return cargarDoc;
	}

	public void setCargarDoc(Boolean cargarDoc) {
		this.cargarDoc = cargarDoc;
	}

	public Boolean getInvitarFirmar() {
		return invitarFirmar;
	}

	public void setInvitarFirmar(Boolean invitarFirmar) {
		this.invitarFirmar = invitarFirmar;
	}

	public Boolean getCompartirRrss() {
		return compartirRrss;
	}

	public void setCompartirRrss(Boolean compartirRrss) {
		this.compartirRrss = compartirRrss;
	}

	public Boolean getCompartirCorreo() {
		return compartirCorreo;
	}

	public void setCompartirCorreo(Boolean compartirCorreo) {
		this.compartirCorreo = compartirCorreo;
	}

	public String getUsuarioPassword() {
		return usuarioPassword;
	}

	public void setUsuarioPassword(String usuarioPassword) {
		this.usuarioPassword = usuarioPassword;
	}

	public String getUrlEsigner() {
		return urlEsigner;
	}

	public void setUrlEsigner(String urlEsigner) {
		this.urlEsigner = urlEsigner;
	}
	
	

}
