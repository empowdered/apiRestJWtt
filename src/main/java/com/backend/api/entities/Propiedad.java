package com.backend.api.entities;

import java.io.Serializable;

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
@Table(name = "propiedad")
@SequenceGenerator(name = "seq_propiedad", sequenceName = "seq_propiedad")
@NamedQueries({
	@NamedQuery(name = "Propiedad.getPropiedadByKey", 
			query = "SELECT p FROM Propiedad p WHERE p.key = :key"),
	@NamedQuery(name = "Property.getPropiedades", 
			query = "SELECT p FROM Propiedad p")

})
public class Propiedad implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String key;
	private String value;
	@Id
	@GeneratedValue(generator = "seq_propiedad", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "key", nullable = false)
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Column(name = "value", nullable = false, length=10485760)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
