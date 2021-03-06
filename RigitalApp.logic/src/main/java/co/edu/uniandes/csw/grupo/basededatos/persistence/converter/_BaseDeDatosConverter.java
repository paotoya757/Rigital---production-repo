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

package co.edu.uniandes.csw.grupo.basededatos.persistence.converter;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


import co.edu.uniandes.csw.grupo.basededatos.logic.dto.BaseDeDatosDTO;
import co.edu.uniandes.csw.grupo.basededatos.persistence.entity.BaseDeDatosEntity;

public abstract class _BaseDeDatosConverter {

 
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static BaseDeDatosDTO entity2PersistenceDTO(BaseDeDatosEntity entity){
		if (entity != null) {
			BaseDeDatosDTO dto = new BaseDeDatosDTO();
					dto.setServidor(entity.getServidor());
					dto.setId(entity.getId());
					dto.setName(entity.getName());
					dto.setDescripcion(entity.getDescripcion());
					dto.setProposito(entity.getProposito());
 
			    if(entity.getFechaCreacion() != null){
					dto.setFechaCreacion(DATE_FORMAT.format(entity.getFechaCreacion()));
				}	
					dto.setDestruido(entity.getDestruido());
					dto.setCaracteristicas(entity.getCaracteristicas());
					dto.setPgwebId(entity.getPgwebId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static BaseDeDatosEntity persistenceDTO2Entity(BaseDeDatosDTO dto){
		if(dto!=null){
			BaseDeDatosEntity entity=new BaseDeDatosEntity();
					entity.setServidor(dto.getServidor());
			
					entity.setId(dto.getId());
			
					entity.setName(dto.getName());
			
					entity.setDescripcion(dto.getDescripcion());
			
					entity.setProposito(dto.getProposito());
			
 
			      try{ 
			        if(dto.getFechaCreacion() != null){
						entity.setFechaCreacion(DATE_FORMAT.parse(dto.getFechaCreacion()));
					}
				  } catch (Exception ex) {
                        Logger.getLogger(_BaseDeDatosConverter.class.getName()).log(Level.SEVERE, null, ex);
                  }	
			
					entity.setDestruido(dto.getDestruido());
			
					entity.setCaracteristicas(dto.getCaracteristicas());
			
					entity.setPgwebId(dto.getPgwebId());
			
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<BaseDeDatosDTO> entity2PersistenceDTOList(List<BaseDeDatosEntity> entities){
		List<BaseDeDatosDTO> dtos=new ArrayList<BaseDeDatosDTO>();
		for(BaseDeDatosEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<BaseDeDatosEntity> persistenceDTO2EntityList(List<BaseDeDatosDTO> dtos){
		List<BaseDeDatosEntity> entities=new ArrayList<BaseDeDatosEntity>();
		for(BaseDeDatosDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}