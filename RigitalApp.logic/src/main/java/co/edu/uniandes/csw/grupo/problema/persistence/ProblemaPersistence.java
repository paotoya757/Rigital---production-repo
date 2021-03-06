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

package co.edu.uniandes.csw.grupo.problema.persistence;

import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaPageDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.grupo.problema.persistence.api.IProblemaPersistence;
import co.edu.uniandes.csw.grupo.problema.persistence.converter.ProblemaConverter;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.persistence.Query;

@Default
@Stateless 
@LocalBean
public class ProblemaPersistence extends _ProblemaPersistence  implements IProblemaPersistence {

    @SuppressWarnings("unchecked")
    public ProblemaPageDTO getProblemasByParameters(String name,String descripcion,String empleadoId,String sqldevId,String mysqlId,String paginawebId,String wikiId,String maquinavirtualId,String unidadderedId,String repositorioId,String contenedorwebId,String softwaresalasId,String fechaDeOcurrencia1,String fechaOcurrencia2)
    {
        Query count = entityManager.createQuery("select count(u) from ProblemaEntity u");
        Long regCount = 0L;
        regCount = Long.parseLong(count.getSingleResult().toString());
                
        String sql = "SELECT u FROM ProblemaEntity u WHERE";
        if(!name.isEmpty())
            sql += " AND u.name like :name";
        if(!descripcion.isEmpty())
            sql += " AND u.descripcion like :descripcion";
        if(!empleadoId.isEmpty())
            sql += " AND u.empleadoId = :empleadoId";
        if(!sqldevId.isEmpty())
            sql  += " AND u.sqldevId = :sqldevId";
        if(!mysqlId.isEmpty())
            sql += " AND u.mysqlId = :mysqlId";
        if(!paginawebId.isEmpty())
            sql += " AND  u.paginawebId = :paginawebId";
        if(!wikiId.isEmpty())
            sql += " AND  u.wikiId = :wikiId";
        if(!maquinavirtualId.isEmpty())
            sql += " AND  u.maquinavirtualId = :maquinavirtualId";
        if(!unidadderedId.isEmpty())
            sql += " AND  u.unidadderedId = :unidadderedId";
        if(!repositorioId.isEmpty())
            sql += " AND  u.repositorioId = :repositorioId";
        if(!contenedorwebId.isEmpty())
            sql += " AND  u.paginawebId = :contenedorwebId";
        if(!softwaresalasId.isEmpty())
            sql += " AND  u.softwaresalasId = :softwaresalasId";
        
        if(!fechaDeOcurrencia1.isEmpty() && !fechaOcurrencia2.isEmpty())
            sql += " AND u.fechaDeOcurrencia BETWEEN :fechaDeOcurrencia1 and :fechaOcurrencia2";

        
        // Se limpia la sentencia sql
        
        if(sql.endsWith("WHERE"))
            sql = sql.replace("WHERE", "");
        if(sql.contains("WHERE AND"))
            sql = sql.replace("WHERE AND", "WHERE");
        
        System.out.println(sql);
        
        Query q = entityManager.createQuery(sql);
        
        if(!name.isEmpty())
            q.setParameter("name", "%"+name+"%");
        if(!descripcion.isEmpty())
            q.setParameter("descripcion", "%"+descripcion+"%");
        if(!empleadoId.isEmpty())
            q.setParameter("empleadoId", Long.parseLong(empleadoId));
        if(!sqldevId.isEmpty())
            q.setParameter("sqldevId", Long.parseLong(sqldevId));
        if(!mysqlId.isEmpty())
            q.setParameter("mysqlId", Long.parseLong(mysqlId));
        if(!paginawebId.isEmpty())
            q.setParameter("paginawebId", Long.parseLong(paginawebId));
        if(!wikiId.isEmpty())
            q.setParameter("wikiId", Long.parseLong(wikiId));
        if(!maquinavirtualId.isEmpty())
            q.setParameter("maquinavirtualId", Long.parseLong(maquinavirtualId));
        if(!unidadderedId.isEmpty())
            q.setParameter("unidadderedId", Long.parseLong(unidadderedId));
        if(!repositorioId.isEmpty())
            q.setParameter("repositorioId", Long.parseLong(repositorioId));
        if(!contenedorwebId.isEmpty())
            q.setParameter("contenedorwebId", Long.parseLong(contenedorwebId));
        if(!softwaresalasId.isEmpty())
            q.setParameter("softwaresalasId", Long.parseLong(softwaresalasId));
        
        if(!fechaDeOcurrencia1.isEmpty() && !fechaOcurrencia2.isEmpty())
        {
            String[] a1 = fechaDeOcurrencia1.split("-");
            String[] a2 = fechaOcurrencia2.split("-");
            
            Date fecha1 = new Date(Integer.parseInt(a1[0])-1900, Integer.parseInt(a1[1]),Integer.parseInt(a1[2]));
            Date fecha2 = new Date(Integer.parseInt(a2[0])-1900, Integer.parseInt(a2[1]),Integer.parseInt(a2[2]));
             
            q.setParameter("fechaDeOcurrencia1", fecha1);
            q.setParameter("fechaOcurrencia2", fecha2);            
        }
        
        
        ProblemaPageDTO response = new ProblemaPageDTO();
        response.setTotalRecords(regCount);
        response.setRecords(ProblemaConverter.entity2PersistenceDTOList(q.getResultList()));
        return response;
        
            
    }
    
    
}