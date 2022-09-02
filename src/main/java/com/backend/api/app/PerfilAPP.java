package com.backend.api.app;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.backend.api.dao.PerfilDAO;
import com.backend.api.entities.Perfil;

@Stateless
public class PerfilAPP {
	@Inject
	private PerfilDAO perfilDao;
	
	public List<Perfil> findAll(){
		return  perfilDao.findAll();			
	}
	
	public Boolean saveById(List<Perfil> perfiles, Long id) {
		System.out.println("saveById APP");
		return true;
	}
}
