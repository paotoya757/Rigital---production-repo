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

package co.edu.uniandes.csw.grupo.sqldev.master.persistence.api;

import co.edu.uniandes.csw.grupo.sqldev.master.persistence.entity.SQLDevestudiantesConAccesoEntity;
import co.edu.uniandes.csw.grupo.estudiante.logic.dto.EstudianteDTO;
import co.edu.uniandes.csw.grupo.sqldev.master.persistence.entity.SQLDevproblemaEntity;
import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaDTO;
import co.edu.uniandes.csw.grupo.sqldev.master.logic.dto.SQLDevMasterDTO;
import java.util.List;

public interface _ISQLDevMasterPersistence {

	public SQLDevMasterDTO getSQLDev(Long sqldevId);
	
	
    public SQLDevestudiantesConAccesoEntity createSQLDevestudiantesConAccesoEntity(SQLDevestudiantesConAccesoEntity entity);
    public void deleteSQLDevestudiantesConAccesoEntity(Long sQLDevId, Long estudiantesConAccesoId);
    public List<EstudianteDTO> getSQLDevestudiantesConAccesoEntityList(Long sQLDevId);
    public SQLDevproblemaEntity createSQLDevproblemaEntity(SQLDevproblemaEntity entity);
    public void deleteSQLDevproblemaEntity(Long sQLDevId, Long problemaId);
    public List<ProblemaDTO> getSQLDevproblemaEntityList(Long sQLDevId);
   
  
}
