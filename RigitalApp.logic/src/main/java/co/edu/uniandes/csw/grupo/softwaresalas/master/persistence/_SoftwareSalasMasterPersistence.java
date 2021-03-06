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

package co.edu.uniandes.csw.grupo.softwaresalas.master.persistence;
import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaDTO;
import co.edu.uniandes.csw.grupo.softwaresalas.master.persistence.entity.SoftwareSalasproblemaEntity;
import co.edu.uniandes.csw.grupo.problema.persistence.converter.ProblemaConverter;
import co.edu.uniandes.csw.grupo.softwaresalas.logic.dto.SoftwareSalasDTO;
import co.edu.uniandes.csw.grupo.softwaresalas.master.logic.dto.SoftwareSalasMasterDTO;
import co.edu.uniandes.csw.grupo.softwaresalas.master.persistence.api._ISoftwareSalasMasterPersistence;
import co.edu.uniandes.csw.grupo.softwaresalas.persistence.api.ISoftwareSalasPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _SoftwareSalasMasterPersistence implements _ISoftwareSalasMasterPersistence {

  	@PersistenceContext(unitName="RigitalAppPU")
 
    protected EntityManager entityManager;
    
    @Inject
    protected ISoftwareSalasPersistence softwaresalasPersistence;  

    public SoftwareSalasMasterDTO getSoftwareSalas(Long softwaresalasId) {
        SoftwareSalasMasterDTO softwaresalasMasterDTO = new SoftwareSalasMasterDTO();
        SoftwareSalasDTO softwaresalas = softwaresalasPersistence.getSoftwareSalas(softwaresalasId);
        softwaresalasMasterDTO.setSoftwareSalasEntity(softwaresalas);
        softwaresalasMasterDTO.setListproblema(getSoftwareSalasproblemaEntityList(softwaresalasId));
        return softwaresalasMasterDTO;
    }

    public SoftwareSalasproblemaEntity createSoftwareSalasproblemaEntity(SoftwareSalasproblemaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteSoftwareSalasproblemaEntity(Long softwareSalasId, Long problemaId) {
        Query q = entityManager.createNamedQuery("SoftwareSalasproblemaEntity.deleteSoftwareSalasproblemaEntity");
        q.setParameter("softwareSalasId", softwareSalasId);
        q.setParameter("problemaId", problemaId);
        q.executeUpdate();
    }

    public List<ProblemaDTO> getSoftwareSalasproblemaEntityList(Long softwareSalasId) {
        ArrayList<ProblemaDTO> resp = new ArrayList<ProblemaDTO>();
        Query q = entityManager.createNamedQuery("SoftwareSalasproblemaEntity.getByMasterId");
        q.setParameter("softwareSalasId",softwareSalasId);
        List<SoftwareSalasproblemaEntity> qResult =  q.getResultList();
        for (SoftwareSalasproblemaEntity entity : qResult) { 
            if(entity.getProblemaIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(ProblemaConverter.entity2PersistenceDTO(entity.getProblemaIdEntity()));
        }
        return resp;
    }

}
