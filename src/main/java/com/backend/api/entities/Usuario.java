/*
 * 
 */
package com.backend.api.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;


@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")

@NamedQueries({
	@NamedQuery(name = "Usuario.loginByDocumento",
				query = "SELECT u FROM Usuario u WHERE u.numeroDocumento =:documento" ),
	@NamedQuery(name = "Usuario.loginByEmail",
				query = "SELECT u FROM Usuario u WHERE lower(u.email) =lower(:email)" ),
	@NamedQuery(name = "Usuario.getByDocumento",
				query = "SELECT u FROM Usuario u WHERE u.numeroDocumento=:documento" ),
	@NamedQuery(name = "Usuario.getByDocumentoLike",
				query = "SELECT u.numeroDocumento FROM Usuario u WHERE u.numeroDocumento LIKE :documento" ),
	@NamedQuery(name = "Usuario.getByEmailIn",
				query = "SELECT u.numeroDocumento FROM Usuario u WHERE u.email IN (:emails)"),
	@NamedQuery(name = "Usuario.getByEmail",
				query = "SELECT u FROM Usuario u WHERE lower(u.email)=lower(:email)" ),
	@NamedQuery(name = "Usuario.deleteByEmail",
				query = "DELETE from Usuario where email = :email" ),
	@NamedQuery(name = "Usuario.deleteById",
				query = "DELETE from Usuario where id = :id"),
	@NamedQuery(name = "Usuario.findByIdClienteAndEmail",
				query = "select u from Usuario u join u.clientes c where lower(u.email) = lower(:email) and c.id = :cliente")
})

@SqlResultSetMapping(name="updateResultUsuario", columns = { @ColumnResult(name = "count")})


@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "updatePrevisionUsuario",
            query   =   "UPDATE Usuario SET id_aseguradora= ? WHERE id= ?"
            ,resultSetMapping = "updateResultUsuario"
    ),
    
    @NamedNativeQuery(
            name    =   "savePasswordUsuario",
            query   =   "UPDATE Usuario SET clave= ? WHERE email= ?"
    ),
    @NamedNativeQuery(
            name    =   "updatePassword",
            query   =   "UPDATE Usuario SET clave = PGP_SYM_ENCRYPT(?, 'demojwt'), cambiar_contrasena = ? WHERE email = ?"
            ,resultSetMapping = "updateResultUsuario"
    ),
    @NamedNativeQuery(name = "activarCuenta",
	query = "UPDATE Usuario SET cuenta_activa= 1  WHERE numero_documento= ?" ),
    
    @NamedNativeQuery(name = "agregarPerfil",
	query = "INSERT INTO usuario_perfil (usuario_id,perfil_id) VALUES(?,?)" ),
    
    @NamedNativeQuery(name = "quitarPerfil",
    query = "DELETE FROM usuario_perfil where usuario_id = ? and perfil_id = ?"
    ),
    @NamedNativeQuery(name = "countById",
    query = "SELECT count(*) FROM Usuario where id = ?"),
    @NamedNativeQuery(name = "countPerfilById",
    query = "select count(*) from usuario_perfil where usuario_id = ? and perfil_id = ?"),
    @NamedNativeQuery(name = "countIfQuedanPerfiles",
    query = "select count(*) from usuario_perfil where usuario_id = ? and perfil_id <> ?"),
    @NamedNativeQuery(name = "findUsuarioByClienteAndMailAndRut", 
    query = "select u.* from usuario u inner join usuario_cliente uc on u.id = uc.usuario_id inner join cliente c on uc.cliente_id = c.id where LOWER(u.email) = LOWER(?) and u.numero_documento = ? and c.id = ?"),
    @NamedNativeQuery(name = "findIdUsuarioByClienteAndMail", 
    query = "select u.id from usuario u inner join usuario_cliente uc on u.id = uc.usuario_id inner join cliente c on uc.cliente_id = c.id where LOWER(u.email) = LOWER(?) and c.id = ?")
})


public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ColumnTransformer(read = "pgp_sym_decrypt(clave::bytea, 'pruebaJwt')", write = "pgp_sym_encrypt(?, 'pruebaJwt')")
	private String clave;

	@Column(name = "fecha_creacion", nullable = false)
	private Timestamp fechaCreacion;

	@Column(name = "activo", nullable = false)
	private boolean activo;
	
	@Column(name = "intentos", nullable = false)
	private Integer intentos;

	@Column(name = "id_tipo_documento", nullable = false)
	private Long idTipoDocumento;
	
	@Column(name = "numero_documento", nullable=false)
	private String numeroDocumento;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "nombres")
	private String nombres;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_cliente", joinColumns={@JoinColumn(name="usuario_id")}, inverseJoinColumns={@JoinColumn(name="cliente_id")})
    private Set<Cliente>  clientes =new HashSet<Cliente>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_perfil", joinColumns={@JoinColumn(name="usuario_id")}, inverseJoinColumns={@JoinColumn(name="perfil_id")})
    private Set<Perfil>  perfiles =new HashSet<Perfil>();
	
	@Column(name = "cambiar_contrasena")
	private Boolean cambiarContrasena;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Set<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Boolean getCambiarContrasena() {
		return cambiarContrasena;
	}

	public void setCambiarContrasena(Boolean cambiarContrasena) {
		this.cambiarContrasena = cambiarContrasena;
	}
	

	
}
