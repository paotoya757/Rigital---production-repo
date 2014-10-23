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

package co.edu.uniandes.csw.grupo.maquinavirtual.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.grupo.maquinavirtual.logic.dto.MaquinaVirtualDTO;
import co.edu.uniandes.csw.grupo.maquinavirtual.logic.dto.MaquinaVirtualPageDTO;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.api._IMaquinaVirtualPersistence;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.converter.MaquinaVirtualConverter;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.entity.MaquinaVirtualEntity;

public abstract class _MaquinaVirtualPersistence implements _IMaquinaVirtualPersistence {

  	@PersistenceContext(unitName="RigitalAppPU")
 
	protected EntityManager entityManager;
	
	public MaquinaVirtualDTO createMaquinaVirtual(MaquinaVirtualDTO maquinaVirtual) {
		MaquinaVirtualEntity entity=MaquinaVirtualConverter.persistenceDTO2Entity(maquinaVirtual);
		entityManager.persist(entity);
		return MaquinaVirtualConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<MaquinaVirtualDTO> getMaquinaVirtuals() {
		Query q = entityManager.createQuery("select u from MaquinaVirtualEntity u");
		return MaquinaVirtualConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public MaquinaVirtualPageDTO getMaquinaVirtuals(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from MaquinaVirtualEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from MaquinaVirtualEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		MaquinaVirtualPageDTO response = new MaquinaVirtualPageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(MaquinaVirtualConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public MaquinaVirtualDTO getMaquinaVirtual(Long id) {
		return MaquinaVirtualConverter.entity2PersistenceDTO(entityManager.find(MaquinaVirtualEntity.class, id));
	}

	public void deleteMaquinaVirtual(Long id) {
		MaquinaVirtualEntity entity=entityManager.find(MaquinaVirtualEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateMaquinaVirtual(MaquinaVirtualDTO detail) {
		MaquinaVirtualEntity entity=entityManager.merge(MaquinaVirtualConverter.persistenceDTO2Entity(detail));
		MaquinaVirtualConverter.entity2PersistenceDTO(entity);
	}

}