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

package co.edu.uniandes.csw.grupo.sqldev.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.grupo.sqldev.logic.dto.SQLDevDTO;
import co.edu.uniandes.csw.grupo.sqldev.logic.dto.SQLDevPageDTO;
import co.edu.uniandes.csw.grupo.sqldev.logic.api._ISQLDevLogicService;
import co.edu.uniandes.csw.grupo.sqldev.persistence.api.ISQLDevPersistence;

public abstract class _SQLDevLogicService implements _ISQLDevLogicService {

	@Inject
	protected ISQLDevPersistence persistance;

	public SQLDevDTO createSQLDev(SQLDevDTO sQLDev){
		return persistance.createSQLDev( sQLDev); 
    }

	public List<SQLDevDTO> getSQLDevs(){
		return persistance.getSQLDevs(); 
	}

	public SQLDevPageDTO getSQLDevs(Integer page, Integer maxRecords){
		return persistance.getSQLDevs(page, maxRecords); 
	}

	public SQLDevDTO getSQLDev(Long id){
		return persistance.getSQLDev(id); 
	}

	public void deleteSQLDev(Long id){
	    persistance.deleteSQLDev(id); 
	}

	public void updateSQLDev(SQLDevDTO sQLDev){
	    persistance.updateSQLDev(sQLDev); 
	}	
}