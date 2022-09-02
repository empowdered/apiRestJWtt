package com.backend.api.dao;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.backend.api.entities.Usuario;


public class UsuarioDAO {
	private static Logger log = Logger.getLogger(UsuarioDAO.class);
	@PersistenceContext(name = "pruebaJwt")

	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario loginByEmail(String email) {
		try {
			return (Usuario) em.createNamedQuery("Usuario.loginByEmail", Usuario.class).setParameter("email", email)
					.getSingleResult();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
