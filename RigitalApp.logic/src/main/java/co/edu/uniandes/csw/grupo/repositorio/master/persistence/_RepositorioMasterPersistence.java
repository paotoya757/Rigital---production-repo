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

package co.edu.uniandes.csw.grupo.repositorio.master.persistence;
import co.edu.uniandes.csw.grupo.estudiante.logic.dto.EstudianteDTO;
import co.edu.uniandes.csw.grupo.repositorio.master.persistence.entity.RepositoriousuariosQueAccedenEntity;
import co.edu.uniandes.csw.grupo.estudiante.persistence.converter.EstudianteConverter;
import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaDTO;
import co.edu.uniandes.csw.grupo.repositorio.master.persistence.entity.RepositorioproblemasEntity;
import co.edu.uniandes.csw.grupo.problema.persistence.converter.ProblemaConverter;
import co.edu.uniandes.csw.grupo.repositorio.logic.dto.RepositorioDTO;
import co.edu.uniandes.csw.grupo.repositorio.master.logic.dto.RepositorioMasterDTO;
import co.edu.uniandes.csw.grupo.repositorio.master.persistence.api._IRepositorioMasterPersistence;
import co.edu.uniandes.csw.grupo.repositorio.persistence.api.IRepositorioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _RepositorioMasterPersistence implements _IRepositorioMasterPersistence {

  	@PersistenceContext(unitName="RigitalAppPU")
 
    protected EntityManager entityManager;
    
    @Inject
    protected IRepositorioPersistence repositorioPersistence;  

    public RepositorioMasterDTO getRepositorio(Long repositorioId) {
        RepositorioMasterDTO repositorioMasterDTO = new RepositorioMasterDTO();
        RepositorioDTO repositorio = repositorioPersistence.getRepositorio(repositorioId);
        repositorioMasterDTO.setRepositorioEntity(repositorio);
        repositorioMasterDTO.setListusuariosQueAcceden(getRepositoriousuariosQueAccedenEntityList(repositorioId));
        repositorioMasterDTO.setListproblemas(getRepositorioproblemasEntityList(repositorioId));
        return repositorioMasterDTO;
    }

    public RepositoriousuariosQueAccedenEntity createRepositoriousuariosQueAccedenEntity(RepositoriousuariosQueAccedenEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteRepositoriousuariosQueAccedenEntity(Long repositorioId, Long usuariosQueAccedenId) {
        Query q = entityManager.createNamedQuery("RepositoriousuariosQueAccedenEntity.deleteRepositoriousuariosQueAccedenEntity");
        q.setParameter("repositorioId", repositorioId);
        q.setParameter("usuariosQueAccedenId", usuariosQueAccedenId);
        q.executeUpdate();
    }

    public List<EstudianteDTO> getRepositoriousuariosQueAccedenEntityList(Long repositorioId) {
        ArrayList<EstudianteDTO> resp = new ArrayList<EstudianteDTO>();
        Query q = entityManager.createNamedQuery("RepositoriousuariosQueAccedenEntity.getByMasterId");
        q.setParameter("repositorioId",repositorioId);
        List<RepositoriousuariosQueAccedenEntity> qResult =  q.getResultList();
        for (RepositoriousuariosQueAccedenEntity entity : qResult) { 
            if(entity.getUsuariosQueAccedenIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(EstudianteConverter.entity2PersistenceDTO(entity.getUsuariosQueAccedenIdEntity()));
        }
        return resp;
    }
    public RepositorioproblemasEntity createRepositorioproblemasEntity(RepositorioproblemasEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteRepositorioproblemasEntity(Long repositorioId, Long problemasId) {
        Query q = entityManager.createNamedQuery("RepositorioproblemasEntity.deleteRepositorioproblemasEntity");
        q.setParameter("repositorioId", repositorioId);
        q.setParameter("problemasId", problemasId);
        q.executeUpdate();
    }

    public List<ProblemaDTO> getRepositorioproblemasEntityList(Long repositorioId) {
        ArrayList<ProblemaDTO> resp = new ArrayList<ProblemaDTO>();
        Query q = entityManager.createNamedQuery("RepositorioproblemasEntity.getByMasterId");
        q.setParameter("repositorioId",repositorioId);
        List<RepositorioproblemasEntity> qResult =  q.getResultList();
        for (RepositorioproblemasEntity entity : qResult) { 
            if(entity.getProblemasIdEntity()==null){
                entityManager.refresh(entity);
            }
            resp.add(ProblemaConverter.entity2PersistenceDTO(entity.getProblemasIdEntity()));
        }
        return resp;
    }

}
