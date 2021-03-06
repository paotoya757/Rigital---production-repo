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

package co.edu.uniandes.csw.grupo.sqldev.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.grupo.sqldev.logic.dto.SQLDevDTO;
import co.edu.uniandes.csw.grupo.sqldev.logic.dto.SQLDevPageDTO;
import co.edu.uniandes.csw.grupo.sqldev.persistence.api._ISQLDevPersistence;
import co.edu.uniandes.csw.grupo.sqldev.persistence.converter.SQLDevConverter;
import co.edu.uniandes.csw.grupo.sqldev.persistence.entity.SQLDevEntity;

public abstract class _SQLDevPersistence implements _ISQLDevPersistence {

  	@PersistenceContext(unitName="RigitalAppPU")
 
	protected EntityManager entityManager;
	
	public SQLDevDTO createSQLDev(SQLDevDTO sQLDev) {
		SQLDevEntity entity=SQLDevConverter.persistenceDTO2Entity(sQLDev);
		entityManager.persist(entity);
		return SQLDevConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<SQLDevDTO> getSQLDevs() {
		Query q = entityManager.createQuery("select u from SQLDevEntity u");
		return SQLDevConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public SQLDevPageDTO getSQLDevs(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from SQLDevEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from SQLDevEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		SQLDevPageDTO response = new SQLDevPageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(SQLDevConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public SQLDevDTO getSQLDev(Long id) {
		return SQLDevConverter.entity2PersistenceDTO(entityManager.find(SQLDevEntity.class, id));
	}

	public void deleteSQLDev(Long id) {
		SQLDevEntity entity=entityManager.find(SQLDevEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateSQLDev(SQLDevDTO detail) {
		SQLDevEntity entity=entityManager.merge(SQLDevConverter.persistenceDTO2Entity(detail));
		SQLDevConverter.entity2PersistenceDTO(entity);
	}

}