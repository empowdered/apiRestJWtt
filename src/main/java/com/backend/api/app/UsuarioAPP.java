package com.backend.api.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.backend.api.dto.UsuarioDTO;
import com.backend.api.entities.Cliente;
import com.backend.api.entities.Perfil;
import com.backend.api.entities.Usuario;
import com.backend.api.app.UsuarioAPP;
import com.backend.api.dao.UsuarioDAO;

@Stateless
public class UsuarioAPP {
	
	private static Logger log = Logger.getLogger(UsuarioAPP.class);
	
	@Inject
	private UsuarioDAO usuarioDao;
	public UsuarioDTO loginByEmail(String email, String clave){
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		Usuario pj = usuarioDao.loginByEmail(email);
		if (pj != null) {
			if (pj.getClave().equalsIgnoreCase(clave)) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				usuarioDTO.setActivo(pj.isActivo());
				usuarioDTO.setClave(null);
				usuarioDTO.setEmail(pj.getEmail());
				usuarioDTO.setFechaCreacion(sdf.format(new Date(pj.getFechaCreacion().getTime())));
				usuarioDTO.setId(pj.getId());
				usuarioDTO.setIdTipoDocumento(pj.getIdTipoDocumento());
				usuarioDTO.setIntentos(pj.getIntentos());
				usuarioDTO.setNombres(pj.getNombres());
				usuarioDTO.setNumeroDocumento(pj.getNumeroDocumento());
				usuarioDTO.setPerfiles(new ArrayList<Perfil>(pj.getPerfiles()));
				usuarioDTO.setClientes(new ArrayList<Cliente>(pj.getClientes()));
				
				return usuarioDTO;
			}
		}
		return null;	
	}
}
