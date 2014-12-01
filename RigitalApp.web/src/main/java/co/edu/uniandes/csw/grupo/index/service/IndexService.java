/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupo.index.service;

import co.edu.uniandes.csw.grupo.index.logic.api.IIndexLogicService;
import co.edu.uniandes.csw.grupo.index.logic.ejb.IndexLogicService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.swing.Timer;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Johnathan Salamanca
 */
@Path("/Index")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
public class IndexService {
    
    @Inject
    protected IIndexLogicService indexLogicService;
    
    @Context
    UriInfo uriInfo;
    
    @Path("/Nuevo")
    @PUT
    public void correos(String datos) {
       
        indexLogicService.iniciar();
    }
    
}
