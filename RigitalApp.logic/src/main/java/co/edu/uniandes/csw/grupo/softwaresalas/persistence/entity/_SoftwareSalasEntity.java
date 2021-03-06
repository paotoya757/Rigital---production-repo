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

package co.edu.uniandes.csw.grupo.softwaresalas.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _SoftwareSalasEntity {

	private String tipoMaquina;
	private String software;
	private String version;
	private String solicitante;
	private String numeroMaquina;
	@Id
	@GeneratedValue(generator = "SoftwareSalas")
	private Long id;
	private String name;
	private String descripcion;
	private String proposito;
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	private Boolean destruido;
	private String caracteristicas;
	private Long workstationId;

	public String getTipoMaquina(){
		return tipoMaquina;
	}
	
	public void setTipoMaquina(String tipoMaquina){
		this.tipoMaquina = tipoMaquina;
	}
	public String getSoftware(){
		return software;
	}
	
	public void setSoftware(String software){
		this.software = software;
	}
	public String getVersion(){
		return version;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	public String getSolicitante(){
		return solicitante;
	}
	
	public void setSolicitante(String solicitante){
		this.solicitante = solicitante;
	}
	public String getNumeroMaquina(){
		return numeroMaquina;
	}
	
	public void setNumeroMaquina(String numeroMaquina){
		this.numeroMaquina = numeroMaquina;
	}
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
	public String getProposito(){
		return proposito;
	}
	
	public void setProposito(String proposito){
		this.proposito = proposito;
	}
	public Date getFechaCreacion(){
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}
	public Boolean getDestruido(){
		return destruido;
	}
	
	public void setDestruido(Boolean destruido){
		this.destruido = destruido;
	}
	public String getCaracteristicas(){
		return caracteristicas;
	}
	
	public void setCaracteristicas(String caracteristicas){
		this.caracteristicas = caracteristicas;
	}
	public Long getWorkstationId(){
		return workstationId;
	}
	
	public void setWorkstationId(Long workstationId){
		this.workstationId = workstationId;
	}
}