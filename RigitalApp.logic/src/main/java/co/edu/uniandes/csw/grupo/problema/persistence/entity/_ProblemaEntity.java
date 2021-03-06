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

package co.edu.uniandes.csw.grupo.problema.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ProblemaEntity {

	@Id
	@GeneratedValue(generator = "Problema")
	private Long id;
	private String name;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Date fechaDeOcurrencia;
	private Long empleadoId;
	private Long sqldevId;
	private Long mysqlId;
	private Long paginawebId;
	private Long wikiId;
	private Long maquinavirtualId;
	private Long unidadderedId;
	private Long repositorioId;
	private Long contenedorwebId;
	private Long softwaresalasId;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	public Date getFechaDeOcurrencia(){
		return fechaDeOcurrencia;
	}
	
	public void setFechaDeOcurrencia(Date fechaDeOcurrencia){
		this.fechaDeOcurrencia = fechaDeOcurrencia;
	}
	public Long getEmpleadoId(){
		return empleadoId;
	}
	
	public void setEmpleadoId(Long empleadoId){
		this.empleadoId = empleadoId;
	}
	public Long getSqldevId(){
		return sqldevId;
	}
	
	public void setSqldevId(Long sqldevId){
		this.sqldevId = sqldevId;
	}
	public Long getMysqlId(){
		return mysqlId;
	}
	
	public void setMysqlId(Long mysqlId){
		this.mysqlId = mysqlId;
	}
	public Long getPaginawebId(){
		return paginawebId;
	}
	
	public void setPaginawebId(Long paginawebId){
		this.paginawebId = paginawebId;
	}
	public Long getWikiId(){
		return wikiId;
	}
	
	public void setWikiId(Long wikiId){
		this.wikiId = wikiId;
	}
	public Long getMaquinavirtualId(){
		return maquinavirtualId;
	}
	
	public void setMaquinavirtualId(Long maquinavirtualId){
		this.maquinavirtualId = maquinavirtualId;
	}
	public Long getUnidadderedId(){
		return unidadderedId;
	}
	
	public void setUnidadderedId(Long unidadderedId){
		this.unidadderedId = unidadderedId;
	}
	public Long getRepositorioId(){
		return repositorioId;
	}
	
	public void setRepositorioId(Long repositorioId){
		this.repositorioId = repositorioId;
	}
	public Long getContenedorwebId(){
		return contenedorwebId;
	}
	
	public void setContenedorwebId(Long contenedorwebId){
		this.contenedorwebId = contenedorwebId;
	}
	public Long getSoftwaresalasId(){
		return softwaresalasId;
	}
	
	public void setSoftwaresalasId(Long softwaresalasId){
		this.softwaresalasId = softwaresalasId;
	}
}