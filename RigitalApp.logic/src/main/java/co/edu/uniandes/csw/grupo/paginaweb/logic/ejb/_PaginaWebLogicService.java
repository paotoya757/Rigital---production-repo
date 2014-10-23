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

package co.edu.uniandes.csw.grupo.paginaweb.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.grupo.paginaweb.logic.dto.PaginaWebDTO;
import co.edu.uniandes.csw.grupo.paginaweb.logic.dto.PaginaWebPageDTO;
import co.edu.uniandes.csw.grupo.paginaweb.logic.api._IPaginaWebLogicService;
import co.edu.uniandes.csw.grupo.paginaweb.persistence.api.IPaginaWebPersistence;

public abstract class _PaginaWebLogicService implements _IPaginaWebLogicService {

	@Inject
	protected IPaginaWebPersistence persistance;

	public PaginaWebDTO createPaginaWeb(PaginaWebDTO paginaWeb){
		return persistance.createPaginaWeb( paginaWeb); 
    }

	public List<PaginaWebDTO> getPaginaWebs(){
		return persistance.getPaginaWebs(); 
	}

	public PaginaWebPageDTO getPaginaWebs(Integer page, Integer maxRecords){
		return persistance.getPaginaWebs(page, maxRecords); 
	}

	public PaginaWebDTO getPaginaWeb(Long id){
		return persistance.getPaginaWeb(id); 
	}

	public void deletePaginaWeb(Long id){
	    persistance.deletePaginaWeb(id); 
	}

	public void updatePaginaWeb(PaginaWebDTO paginaWeb){
	    persistance.updatePaginaWeb(paginaWeb); 
	}	
}