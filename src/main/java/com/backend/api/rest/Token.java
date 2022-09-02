package com.backend.api.rest;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.backend.api.dao.PropiedadDAO;
import com.backend.api.app.UsuarioAPP;
import com.backend.api.constantes.Constantes;
import com.backend.api.dto.UsuarioDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


public class Token {
	
	
	private static Logger log = Logger.getLogger(Token.class);
	
	@Inject
	private PropiedadDAO propiedadDAO;
	
	@Inject
	private UsuarioAPP usuarioAPP;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
    @Path("token")
	public Response token(@QueryParam("email") String emailUsuario, @QueryParam("clave") String clave){
		String email = emailUsuario.toLowerCase();
		log.info("Se realiza llamado al endpoint /usuario/token");
		log.info("El email " + email +" solicita un token utilizando su información de inicio de sesión");
		UsuarioDTO pj = usuarioAPP.loginByEmail(email,clave);
		if (pj != null) {
			if (pj.isActivo()) {
				String token = Jwts
						.builder()
						.setId(propiedadDAO.getPropiedad(Constantes.TOKEN_ID).getValue())
						.setSubject(pj.getNumeroDocumento()+"%"+pj.getEmail())
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + (60*1000*Integer.parseInt(propiedadDAO.getPropiedad(Constantes.TOKEN_TIMER).getValue()))))
						.signWith(SignatureAlgorithm.HS512,	propiedadDAO.getPropiedad(Constantes.TOKEN_SECRET).getValue().getBytes()).compact();
					log.info("El email " + email + " obtuvo su token");
				return Response.ok("{ \"token\" : \""+token+"\" }").build();
			}else{
				log.info("El email " + email + " no pudo obtener un token, la cuenta se encuentra inactiva.");
				return Response.ok("{ \"token\" : \""+"cuenta inactiva"+"\" }").build();
			}	
		}else{
				log.info("El email " + email + " no pudo obtener un token, las credenciales recibidas no son correctas.");
			return Response.ok("{ \"token\" : \""+"credenciales incorrectas"+"\" }").build();
		}
		
	}
}
