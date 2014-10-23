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

package co.edu.uniandes.csw.grupo.wiki.master.logic.ejb;

import co.edu.uniandes.csw.grupo.estudiante.logic.dto.EstudianteDTO;
import co.edu.uniandes.csw.grupo.estudiante.persistence.api.IEstudiantePersistence;
import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaDTO;
import co.edu.uniandes.csw.grupo.problema.persistence.api.IProblemaPersistence;
import co.edu.uniandes.csw.grupo.wiki.logic.dto.WikiDTO;
import co.edu.uniandes.csw.grupo.wiki.master.logic.api._IWikiMasterLogicService;
import co.edu.uniandes.csw.grupo.wiki.master.logic.dto.WikiMasterDTO;
import co.edu.uniandes.csw.grupo.wiki.master.persistence.api.IWikiMasterPersistence;
import co.edu.uniandes.csw.grupo.wiki.master.persistence.entity.WikidueniosEntity;
import co.edu.uniandes.csw.grupo.wiki.master.persistence.entity.WikiproblemaEntity;
import co.edu.uniandes.csw.grupo.wiki.persistence.api.IWikiPersistence;
import javax.inject.Inject;

public abstract class _WikiMasterLogicService implements _IWikiMasterLogicService {

    @Inject
    protected IWikiPersistence wikiPersistance;
    @Inject
    protected IWikiMasterPersistence wikiMasterPersistance;
    @Inject
    protected IProblemaPersistence problemaPersistance;
    @Inject
    protected IEstudiantePersistence estudiantePersistance;

    public WikiMasterDTO createMasterWiki(WikiMasterDTO wiki) {
        WikiDTO persistedWikiDTO = wikiPersistance.createWiki(wiki.getWikiEntity());
        if (wiki.getCreateduenios() != null) {
            for (EstudianteDTO estudianteDTO : wiki.getCreateduenios()) {
                EstudianteDTO createdEstudianteDTO = estudiantePersistance.createEstudiante(estudianteDTO);
                WikidueniosEntity wikiEstudianteEntity = new WikidueniosEntity(persistedWikiDTO.getId(), createdEstudianteDTO.getId());
                wikiMasterPersistance.createWikidueniosEntity(wikiEstudianteEntity);
            }
        }
        if (wiki.getCreateproblema() != null) {
            for (ProblemaDTO problemaDTO : wiki.getCreateproblema()) {
                ProblemaDTO createdProblemaDTO = problemaPersistance.createProblema(problemaDTO);
                WikiproblemaEntity wikiProblemaEntity = new WikiproblemaEntity(persistedWikiDTO.getId(), createdProblemaDTO.getId());
                wikiMasterPersistance.createWikiproblemaEntity(wikiProblemaEntity);
            }
        }
        // update estudiante
        if (wiki.getUpdateduenios() != null) {
            for (EstudianteDTO estudianteDTO : wiki.getUpdateduenios()) {
                estudiantePersistance.updateEstudiante(estudianteDTO);
                WikidueniosEntity wikiEstudianteEntity = new WikidueniosEntity(persistedWikiDTO.getId(), estudianteDTO.getId());
                wikiMasterPersistance.createWikidueniosEntity(wikiEstudianteEntity);
            }
        }
        // update problema
        if (wiki.getUpdateproblema() != null) {
            for (ProblemaDTO problemaDTO : wiki.getUpdateproblema()) {
                problemaPersistance.updateProblema(problemaDTO);
                WikiproblemaEntity wikiProblemaEntity = new WikiproblemaEntity(persistedWikiDTO.getId(), problemaDTO.getId());
                wikiMasterPersistance.createWikiproblemaEntity(wikiProblemaEntity);
            }
        }
        return wiki;
    }

    public WikiMasterDTO getMasterWiki(Long id) {
        return wikiMasterPersistance.getWiki(id);
    }

    public void deleteMasterWiki(Long id) {
        wikiPersistance.deleteWiki(id);
    }

    public void updateMasterWiki(WikiMasterDTO wiki) {
        wikiPersistance.updateWiki(wiki.getWikiEntity());

        //---- FOR RELATIONSHIP
        // delete estudiante
        if (wiki.getDeleteduenios() != null) {
            for (EstudianteDTO estudianteDTO : wiki.getDeleteduenios()) {
                wikiMasterPersistance.deleteWikidueniosEntity(wiki.getWikiEntity().getId(), estudianteDTO.getId());
            }
        }
        // persist new estudiante
        if (wiki.getCreateduenios() != null) {
            for (EstudianteDTO estudianteDTO : wiki.getCreateduenios()) {
                WikidueniosEntity wikiEstudianteEntity = new WikidueniosEntity(wiki.getWikiEntity().getId(), estudianteDTO.getId());
                wikiMasterPersistance.createWikidueniosEntity(wikiEstudianteEntity);
            }
        }
        // update estudiante
        if (wiki.getUpdateduenios() != null) {
            for (EstudianteDTO estudianteDTO : wiki.getUpdateduenios()) {
                wikiMasterPersistance.deleteWikidueniosEntity(wiki.getWikiEntity().getId(), estudianteDTO.getId());
                estudiantePersistance.updateEstudiante(estudianteDTO);
                WikidueniosEntity wikiEstudianteEntity = new WikidueniosEntity(wiki.getId(), estudianteDTO.getId());
                wikiMasterPersistance.createWikidueniosEntity(wikiEstudianteEntity);
                
            }
        }
        // delete problema
        if (wiki.getDeleteproblema() != null) {
            for (ProblemaDTO problemaDTO : wiki.getDeleteproblema()) {
                wikiMasterPersistance.deleteWikiproblemaEntity(wiki.getWikiEntity().getId(), problemaDTO.getId());
            }
        }
        // persist new problema
        if (wiki.getCreateproblema() != null) {
            for (ProblemaDTO problemaDTO : wiki.getCreateproblema()) {
                WikiproblemaEntity wikiProblemaEntity = new WikiproblemaEntity(wiki.getWikiEntity().getId(), problemaDTO.getId());
                wikiMasterPersistance.createWikiproblemaEntity(wikiProblemaEntity);
            }
        }
        // update problema
        if (wiki.getUpdateproblema() != null) {
            for (ProblemaDTO problemaDTO : wiki.getUpdateproblema()) {
                wikiMasterPersistance.deleteWikiproblemaEntity(wiki.getWikiEntity().getId(), problemaDTO.getId());
                problemaPersistance.updateProblema(problemaDTO);
                WikiproblemaEntity wikiProblemaEntity = new WikiproblemaEntity(wiki.getId(), problemaDTO.getId());
                wikiMasterPersistance.createWikiproblemaEntity(wikiProblemaEntity);
                
            }
        }
    }
}
