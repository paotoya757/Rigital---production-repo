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

package co.edu.uniandes.csw.grupo.maquinavirtual.master.logic.dto;

import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaDTO;
import co.edu.uniandes.csw.grupo.maquinavirtual.logic.dto.MaquinaVirtualDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public abstract class _MaquinaVirtualMasterDTO {

 
    protected MaquinaVirtualDTO maquinavirtualEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public MaquinaVirtualDTO getMaquinaVirtualEntity() {
        return maquinavirtualEntity;
    }

    public void setMaquinaVirtualEntity(MaquinaVirtualDTO maquinavirtualEntity) {
        this.maquinavirtualEntity = maquinavirtualEntity;
    }
    
    public List<ProblemaDTO> createproblema;
    public List<ProblemaDTO> updateproblema;
    public List<ProblemaDTO> deleteproblema;
    public List<ProblemaDTO> listproblema;	
	
	
	
    public List<ProblemaDTO> getCreateproblema(){ return createproblema; };
    public void setCreateproblema(List<ProblemaDTO> createproblema){ this.createproblema=createproblema; };
    public List<ProblemaDTO> getUpdateproblema(){ return updateproblema; };
    public void setUpdateproblema(List<ProblemaDTO> updateproblema){ this.updateproblema=updateproblema; };
    public List<ProblemaDTO> getDeleteproblema(){ return deleteproblema; };
    public void setDeleteproblema(List<ProblemaDTO> deleteproblema){ this.deleteproblema=deleteproblema; };
    public List<ProblemaDTO> getListproblema(){ return listproblema; };
    public void setListproblema(List<ProblemaDTO> listproblema){ this.listproblema=listproblema; };	
	
	
}

