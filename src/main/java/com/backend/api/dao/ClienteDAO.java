package com.backend.api.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.backend.api.entities.Cliente;


@Stateless
public class ClienteDAO {

	@PersistenceContext(name="pruebaJwt")
	private EntityManager em;
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long guardarCliente(Cliente cliente) throws Exception{
		em.persist(cliente);
		em.flush();
		return cliente.getId();
	}
	
	public Cliente getById(Long id){
		return em.find(Cliente.class, id);
	}
	
	public List<Cliente> getAll(){
		Query query=em.createNamedQuery("Cliente.getAll");
		return query.getResultList();			
	}
	
	public Cliente getByIdCliente(String idCliente) {
		Query query = em.createNamedQuery("Cliente.getByIdCliente");
		query.setParameter("idCliente", idCliente);
		return (Cliente)query.getSingleResult();
	}
		
	public boolean deleteById(String idCliente) throws Exception{
		boolean eliminado = false;
		Query query = em.createNamedQuery("Cliente.deleteByIdCliente");
		query.setParameter("idCliente", idCliente);
		int executeUpdate = query.executeUpdate();
		if(executeUpdate == 1){
			eliminado = true;
		}else {
			eliminado = false;
		}
		return eliminado;
	}
	
}
