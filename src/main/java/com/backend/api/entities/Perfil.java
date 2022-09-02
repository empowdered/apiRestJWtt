package com.backend.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "perfil")
@NamedQueries({
	@NamedQuery(name = "Perfil.getAll", 
			query = "SELECT p FROM Perfil p"),
	@NamedQuery(name = "Perfil.countAll", 
			query = "SELECT count(p.id) FROM Perfil p ")
	
	
})
public class Perfil implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
