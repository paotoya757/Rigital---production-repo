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

package co.edu.uniandes.csw.grupo.repositorio.persistence.converter;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


import co.edu.uniandes.csw.grupo.repositorio.logic.dto.RepositorioDTO;
import co.edu.uniandes.csw.grupo.repositorio.persistence.entity.RepositorioEntity;

public abstract class _RepositorioConverter {

 
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static RepositorioDTO entity2PersistenceDTO(RepositorioEntity entity){
		if (entity != null) {
			RepositorioDTO dto = new RepositorioDTO();
					dto.setTipo(entity.getTipo());
					dto.setServidor(entity.getServidor());
					dto.setDestino(entity.getDestino());
					dto.setTipoAcceso(entity.getTipoAcceso());
					dto.setUbicacionDelServidor(entity.getUbicacionDelServidor());
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
	
	public static RepositorioEntity persistenceDTO2Entity(RepositorioDTO dto){
		if(dto!=null){
			RepositorioEntity entity=new RepositorioEntity();
					entity.setTipo(dto.getTipo());
			
					entity.setServidor(dto.getServidor());
			
					entity.setDestino(dto.getDestino());
			
					entity.setTipoAcceso(dto.getTipoAcceso());
			
					entity.setUbicacionDelServidor(dto.getUbicacionDelServidor());
			
					entity.setUrl(dto.getUrl());
			
 
			      try{ 
			        if(dto.getFechaVencimiento() != null){
						entity.setFechaVencimiento(DATE_FORMAT.parse(dto.getFechaVencimiento()));
					}
				  } catch (Exception ex) {
                        Logger.getLogger(_RepositorioConverter.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(_RepositorioConverter.class.getName()).log(Level.SEVERE, null, ex);
                  }	
			
					entity.setDestruido(dto.getDestruido());
			
					entity.setCaracteristicas(dto.getCaracteristicas());
			
					entity.setEncargadoId(dto.getEncargadoId());
			
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<RepositorioDTO> entity2PersistenceDTOList(List<RepositorioEntity> entities){
		List<RepositorioDTO> dtos=new ArrayList<RepositorioDTO>();
		for(RepositorioEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<RepositorioEntity> persistenceDTO2EntityList(List<RepositorioDTO> dtos){
		List<RepositorioEntity> entities=new ArrayList<RepositorioEntity>();
		for(RepositorioDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}