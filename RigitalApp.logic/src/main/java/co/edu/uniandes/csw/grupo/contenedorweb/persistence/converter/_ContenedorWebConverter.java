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

package co.edu.uniandes.csw.grupo.contenedorweb.persistence.converter;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


import co.edu.uniandes.csw.grupo.contenedorweb.logic.dto.ContenedorWebDTO;
import co.edu.uniandes.csw.grupo.contenedorweb.persistence.entity.ContenedorWebEntity;

public abstract class _ContenedorWebConverter {

 
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static ContenedorWebDTO entity2PersistenceDTO(ContenedorWebEntity entity){
		if (entity != null) {
			ContenedorWebDTO dto = new ContenedorWebDTO();
					dto.setPuertos(entity.getPuertos());
					dto.setServidor(entity.getServidor());
					dto.setUrl(entity.getUrl());
 
			    if(entity.getFechaVencimiento() != null){
					dto.setFechaVencimiento(DATE_FORMAT.format(entity.getFechaVencimiento()));
				}	
					dto.setId(entity.getId());
					dto.setName(entity.getName());
					dto.setDescripcion(entity.getDescripcion());
					dto.setProposito(entity.getProposito());
 
			    if(entity.getFechaCreacion() != null){
					dto.setFechaCreacion(DATE_FORMAT.format(entity.getFechaCreacion()));
				}	
					dto.setDestruido(entity.getDestruido());
					dto.setCaracteristicas(entity.getCaracteristicas());
					dto.setEncargadoId(entity.getEncargadoId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ContenedorWebEntity persistenceDTO2Entity(ContenedorWebDTO dto){
		if(dto!=null){
			ContenedorWebEntity entity=new ContenedorWebEntity();
					entity.setPuertos(dto.getPuertos());
			
					entity.setServidor(dto.getServidor());
			
					entity.setUrl(dto.getUrl());
			
 
			      try{ 
			        if(dto.getFechaVencimiento() != null){
						entity.setFechaVencimiento(DATE_FORMAT.parse(dto.getFechaVencimiento()));
					}
				  } catch (Exception ex) {
                        Logger.getLogger(_ContenedorWebConverter.class.getName()).log(Level.SEVERE, null, ex);
                  }	
			
					entity.setId(dto.getId());
			
					entity.setName(dto.getName());
			
					entity.setDescripcion(dto.getDescripcion());
			
					entity.setProposito(dto.getProposito());
			
 
			      try{ 
			        if(dto.getFechaCreacion() != null){
						entity.setFechaCreacion(DATE_FORMAT.parse(dto.getFechaCreacion()));
					}
				  } catch (Exception ex) {
                        Logger.getLogger(_ContenedorWebConverter.class.getName()).log(Level.SEVERE, null, ex);
                  }	
			
					entity.setDestruido(dto.getDestruido());
			
					entity.setCaracteristicas(dto.getCaracteristicas());
			
					entity.setEncargadoId(dto.getEncargadoId());
			
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<ContenedorWebDTO> entity2PersistenceDTOList(List<ContenedorWebEntity> entities){
		List<ContenedorWebDTO> dtos=new ArrayList<ContenedorWebDTO>();
		for(ContenedorWebEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ContenedorWebEntity> persistenceDTO2EntityList(List<ContenedorWebDTO> dtos){
		List<ContenedorWebEntity> entities=new ArrayList<ContenedorWebEntity>();
		for(ContenedorWebDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}