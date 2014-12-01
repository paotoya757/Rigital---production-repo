/* ========================================================================
 * Copyright 2014 grupo
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 grupo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201410152247

*/

package co.edu.uniandes.csw.grupo.empleado.service;

import co.edu.uniandes.csw.grupo.empleado.logic.dto.EmpleadoDTO;
import co.edu.uniandes.csw.grupo.empleado.logic.dto.EmpleadoPageDTO;
import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("/Empleado")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpleadoService extends _EmpleadoService {

    @Context
    UriInfo uriInfo;
    
    @GET
    @Override
    public EmpleadoPageDTO getEmpleados(@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords){
        System.out.println("Acceso!!!!!!!!!!!!!!!!!!");
		return super.getEmpleados(page, maxRecords);
    }
    
    @GET
    @Path("/Search")
    public EmpleadoPageDTO searchEmpleados(@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords){
        
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        
        System.out.println(queryParams.keySet());
        System.out.println(queryParams.values());
        
        // Estos atributos podr�n ser vacios o tener valores
        // Si estan vac�os no se incluir�n en la b�squeda
        
        String password = queryParams.getFirst("password");
        String name = queryParams.getFirst("name");
        String login = queryParams.getFirst("login");          
        
        if(name == null)
            name = "";
        if(password == null)
            password = "";
        if(login == null)
            login = "";              
        
        
        return empleadoLogicService.getEmpleadosByParameters(name, password, login);
    }
    
    /**
     * Servicio que retorna un texto con el id de un empleado basado en su nombre
     * este servicio lo utiliza el sistema para poder buscar en su
     * base de datos.
     *
     * @param name el nombre del empleado
     * @return texto con el id
     */
    @GET
    @Path("services/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmpleadoId(@PathParam("name") String name) {
        return "" + empleadoLogicService.getEmpleadoId(name).getId();
    }
 
    /**
     * Servicio que retorna un empleado dado su nombre
     *
     * @param name el nombre del empleado
     * @return json con el empleado
     */
    @GET
    @Path("servicejson/{name}")
    public EmpleadoDTO getEmpleadobyName(@PathParam("name") String name) {
        return empleadoLogicService.getEmpleadoId(name);
    }
    
    
    /**
     * Servicio que ofrece el html con el nobre de usuario y el link de cierre
     * de sesion
     *
     * @param req Contexto del usuario que llama al servicio de donde se
     * obtienen los datos de sesion
     */
    @GET
    @Path("session/userstack")
    public String getLogedUserStack(@Context HttpServletRequest req) {
        return "<b>" + req.getRemoteUser() + "</b></br><a href=\"webresources/Empleado/session/logout\">Log Out</a>";
    }
 
    /**
     * Servicio que deslogea al usuario que lo invoca, y luego lo redirige a la
     * p�gina de logout.html
     *
     * @param req Contexto del usuario que llama al servicio de donde se
     * obtienen o modifican los datos de sesion
     * @param res Contexto del usuario en la respuesta del servicio
     */
    @GET
    @Path("session/logout")
    public String logout(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            //Se invalida la sesi�n del usuario que llama al servicio
            req.getSession().invalidate();
            //Se le indica redirigirse a la p�gina de logout
            res.sendRedirect(req.getContextPath() + "/logout.html");
        } catch (IOException ex) {
            
        }
        return null;
    }
 
 
 
    /**
     * Servicio que retruna el nombre del usuario autenticado. Si no lo est�
     * retorna null
     *
     * @param req Contexto del usuario que llama al servicio de donde se
     * obtienen los datos de sesion
     */
    @GET
    @Path("session/user")
    public String getLogedUser(@Context HttpServletRequest req) {
        return req.getRemoteUser();
    }

}