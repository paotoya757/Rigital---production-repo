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

package co.edu.uniandes.csw.grupo.wiki.master.persistence;
import co.edu.uniandes.csw.grupo.estudiante.logic.dto.EstudianteDTO;
import co.edu.uniandes.csw.grupo.wiki.master.persistence.entity.WikidueniosEntity;
import co.edu.uniandes.csw.grupo.estudiante.persistence.converter.EstudianteConverter;
import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaDTO;
import co.edu.uniandes.csw.grupo.wiki.master.persistence.entity.WikiproblemaEntity;
import co.edu.uniandes.csw.grupo.problema.persistence.converter.ProblemaConverter;
import co.edu.uniandes.csw.grupo.wiki.logic.dto.WikiDTO;
import co.edu.uniandes.csw.grupo.wiki.master.logic.dto.WikiMasterDTO;
import co.edu.uniandes.csw.grupo.wiki.master.persistence.api._IWikiMasterPersistence;
import co.edu.uniandes.csw.grupo.wiki.persistence.api.IWikiPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _WikiMasterPersistence implements _IWikiMasterPersistence {

  	@PersistenceContext(unitName="RigitalAppPU")
 
    protected EntityManager entityManager;
    
    @Inject
    protected IWikiPersistence wikiPersistence;  

    public WikiMasterDTO getWiki(Long wikiId) {
        WikiMasterDTO wikiMasterDTO = new WikiMasterDTO();
        WikiDTO wiki = wikiPersistence.getWiki(wikiId);
        wikiMasterDTO.setWikiEntity(wiki);
        wikiMasterDTO.setListduenios(getWikidueniosEntityList(wikiId));
        wikiMasterDTO.setListproblema(getWikiproblemaEntityList(wikiId));
        return wikiMasterDTO;
    }

    public WikidueniosEntity createWikidueniosEntity(WikidueniosEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteWikidueniosEntity(Long wikiId, Long dueniosId) {
        Query q = entityManager.createNamedQuery("WikidueniosEntity.deleteWikidueniosEntity");
        q.setParameter("wikiId", wikiId);
        q.setParameter("dueniosId", dueniosId);
        q.executeUpdate();
    }

    public List<EstudianteDTO> getWikidueniosEntityList(Long wikiId) {
        ArrayList<EstudianteDTO> resp = new ArrayList<EstudianteDTO>();
        Query q = entityManager.createNamedQuery("WikidueniosEntity.getByMasterId");
        q.setParameter("wikiId",wikiId);
        List<WikidueniosEntity> qResult =  q.getResultList();
        for (WikidueniosEntity entity : qResult) { 
            if(entity.getDueniosIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(EstudianteConverter.entity2PersistenceDTO(entity.getDueniosIdEntity()));
        }
        return resp;
    }
    public WikiproblemaEntity createWikiproblemaEntity(WikiproblemaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteWikiproblemaEntity(Long wikiId, Long problemaId) {
        Query q = entityManager.createNamedQuery("WikiproblemaEntity.deleteWikiproblemaEntity");
        q.setParameter("wikiId", wikiId);
        q.setParameter("problemaId", problemaId);
        q.executeUpdate();
    }

    public List<ProblemaDTO> getWikiproblemaEntityList(Long wikiId) {
        ArrayList<ProblemaDTO> resp = new ArrayList<ProblemaDTO>();
        Query q = entityManager.createNamedQuery("WikiproblemaEntity.getByMasterId");
        q.setParameter("wikiId",wikiId);
        List<WikiproblemaEntity> qResult =  q.getResultList();
        for (WikiproblemaEntity entity : qResult) { 
            if(entity.getProblemaIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(ProblemaConverter.entity2PersistenceDTO(entity.getProblemaIdEntity()));
        }
        return resp;
    }

}
