package com.backend.api.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.backend.api.entities.Perfil;

public class PerfilDAO {
@PersistenceContext(name = "demoJwt")
	
	private EntityManager em;

	public List<Perfil> findAll(){
		Query query=em.createNamedQuery("Perfil.getAll");
		return query.getResultList();			
	}
	
	public Perfil getById(Long id){
		return em.find(Perfil.class, id);
	}
}
