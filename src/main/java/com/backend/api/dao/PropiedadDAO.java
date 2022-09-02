package com.backend.api.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.backend.api.dao.PropiedadDAO;
import com.backend.api.entities.Propiedad;

@Stateless
public class PropiedadDAO {
	@PersistenceContext(name = "demoJwt")
	private EntityManager em;
	private static Logger log = Logger.getLogger(PropiedadDAO.class);
	
	public Propiedad getPropiedad(String key){	
		try{
			Query query=em.createNamedQuery("Propiedad.getPropiedadByKey");
			query.setParameter("key", key);
			return (Propiedad) query.getSingleResult();
		}catch(Exception e){
			log.info("error obteniendo la propiedad: " + e.getMessage());
		}
		
		return null;
		
	}
}
