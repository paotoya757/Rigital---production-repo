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

package co.edu.uniandes.csw.grupo.repositorio.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.grupo.repositorio.logic.dto.RepositorioDTO;
import co.edu.uniandes.csw.grupo.repositorio.logic.dto.RepositorioPageDTO;
import co.edu.uniandes.csw.grupo.repositorio.logic.api._IRepositorioLogicService;
import co.edu.uniandes.csw.grupo.repositorio.persistence.api.IRepositorioPersistence;

public abstract class _RepositorioLogicService implements _IRepositorioLogicService {

	@Inject
	protected IRepositorioPersistence persistance;

	public RepositorioDTO createRepositorio(RepositorioDTO repositorio){
		return persistance.createRepositorio( repositorio); 
    }

	public List<RepositorioDTO> getRepositorios(){
		return persistance.getRepositorios(); 
	}

	public RepositorioPageDTO getRepositorios(Integer page, Integer maxRecords){
		return persistance.getRepositorios(page, maxRecords); 
	}

	public RepositorioDTO getRepositorio(Long id){
		return persistance.getRepositorio(id); 
	}

	public void deleteRepositorio(Long id){
	    persistance.deleteRepositorio(id); 
	}

	public void updateRepositorio(RepositorioDTO repositorio){
	    persistance.updateRepositorio(repositorio); 
	}	
}