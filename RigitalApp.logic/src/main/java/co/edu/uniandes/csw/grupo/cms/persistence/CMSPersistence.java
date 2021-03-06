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

package co.edu.uniandes.csw.grupo.cms.persistence;

import co.edu.uniandes.csw.grupo.cms.logic.dto.CMSPageDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.grupo.cms.persistence.api.ICMSPersistence;
import co.edu.uniandes.csw.grupo.cms.persistence.converter.CMSConverter;
import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaPageDTO;
import co.edu.uniandes.csw.grupo.problema.persistence.converter.ProblemaConverter;
import javax.ejb.LocalBean;
import javax.persistence.Query;

@Default
@Stateless 
@LocalBean
public class CMSPersistence extends _CMSPersistence  implements ICMSPersistence {

    @SuppressWarnings("unchecked")
    public CMSPageDTO getCMSsByParameters(String name,String version)
    {
        Query count = entityManager.createQuery("select count(u) from CMSEntity u");
        Long regCount = 0L;
        regCount = Long.parseLong(count.getSingleResult().toString());
                
        String sql = "SELECT u FROM CMSEntity u WHERE";
        if(!name.isEmpty())
            sql += " AND u.name like :name";
        if(!version.isEmpty())
            sql += " AND u.version like :version";
        
        // Se limpia la sentencia sql
        
        if(sql.endsWith("WHERE"))
            sql = sql.replace("WHERE", "");
        if(sql.contains("WHERE AND"))
            sql = sql.replace("WHERE AND", "WHERE");
        
        System.out.println(sql);
        
        Query q = entityManager.createQuery(sql);
        
        if(!name.isEmpty())
            q.setParameter("name", "%"+name+"%");
        if(!version.isEmpty())
            q.setParameter("descripcion", "%"+version+"%");        
        
        CMSPageDTO response = new CMSPageDTO();
        response.setTotalRecords(regCount);
        response.setRecords(CMSConverter.entity2PersistenceDTOList(q.getResultList()));
        return response;
        
            
    }
 
      public void desactivarRecurso(Long cms) {
        Query query= entityManager.createQuery("UPDATE CMSEntity c SET c.destruido='false' WHERE c.id = :cms");
        query.setParameter("cms", cms);
        query.executeUpdate();
    }
}