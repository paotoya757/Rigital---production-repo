
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

package co.edu.uniandes.csw.grupo.repositorio.persistence;

import co.edu.uniandes.csw.grupo.encargado.master.persistence.entity.EncargadorepositorioEntity;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.grupo.repositorio.logic.dto.RepositorioPageDTO;
import co.edu.uniandes.csw.grupo.repositorio.logic.dto.RepositorioDTO;
import co.edu.uniandes.csw.grupo.repositorio.persistence.api.IRepositorioPersistence;
import co.edu.uniandes.csw.grupo.repositorio.persistence.entity.RepositorioEntity;
import co.edu.uniandes.csw.grupo.repositorio.persistence.converter.RepositorioConverter;
import static co.edu.uniandes.csw.grupo.util._TestUtil.*;

@RunWith(Arquillian.class)
public class RepositorioPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(RepositorioPersistence.class.getPackage())
				.addPackage(RepositorioEntity.class.getPackage())
				.addPackage(IRepositorioPersistence.class.getPackage())
                .addPackage(RepositorioDTO.class.getPackage())
                .addPackage(RepositorioConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IRepositorioPersistence repositorioPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from RepositorioEntity").executeUpdate();
	}

	private List<RepositorioEntity> data=new ArrayList<RepositorioEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			RepositorioEntity entity=new RepositorioEntity();
			entity.setTipo(generateRandom(String.class));
			entity.setServidor(generateRandom(String.class));
			entity.setDestino(generateRandom(String.class));
			entity.setTipoAcceso(generateRandom(String.class));
			entity.setUbicacionDelServidor(generateRandom(String.class));
			entity.setUrl(generateRandom(String.class));
			entity.setFechaVencimiento(generateRandom(Date.class));
			entity.setName(generateRandom(String.class));
			entity.setDescripcion(generateRandom(String.class));
			entity.setProposito(generateRandom(String.class));
			entity.setFechaCreacion(generateRandom(Date.class));
			entity.setDestruido(generateRandom(Boolean.class));
			entity.setCaracteristicas(generateRandom(String.class));
			entity.setEncargadoId(generateRandom(Long.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createRepositorioTest(){
		RepositorioDTO dto=new RepositorioDTO();
		dto.setTipo(generateRandom(String.class));
		dto.setServidor(generateRandom(String.class));
		dto.setDestino(generateRandom(String.class));
		dto.setTipoAcceso(generateRandom(String.class));
		dto.setUbicacionDelServidor(generateRandom(String.class));
		dto.setUrl(generateRandom(String.class));
                dto.setFechaVencimiento(generateRandomDate());
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setProposito(generateRandom(String.class));
                dto.setFechaCreacion(generateRandomDate());
		dto.setDestruido(generateRandom(Boolean.class));
		dto.setCaracteristicas(generateRandom(String.class));
		dto.setEncargadoId(generateRandom(Long.class));
		
		RepositorioDTO result=repositorioPersistence.createRepositorio(dto);
		
		Assert.assertNotNull(result);
		
		RepositorioEntity entity=em.find(RepositorioEntity.class, result.getId());
		
		Assert.assertEquals(dto.getTipo(), entity.getTipo());
		Assert.assertEquals(dto.getServidor(), entity.getServidor());
		Assert.assertEquals(dto.getDestino(), entity.getDestino());
		Assert.assertEquals(dto.getTipoAcceso(), entity.getTipoAcceso());
		Assert.assertEquals(dto.getUbicacionDelServidor(), entity.getUbicacionDelServidor());
		Assert.assertEquals(dto.getUrl(), entity.getUrl());
                Assert.assertEquals(parseDate(dto.getFechaVencimiento()), entity.getFechaVencimiento());	
		Assert.assertEquals(dto.getName(), entity.getName());
		Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());
		Assert.assertEquals(dto.getProposito(), entity.getProposito());
                Assert.assertEquals(parseDate(dto.getFechaCreacion()), entity.getFechaCreacion());	
		Assert.assertEquals(dto.getDestruido(), entity.getDestruido());
		Assert.assertEquals(dto.getCaracteristicas(), entity.getCaracteristicas());
		Assert.assertEquals(dto.getEncargadoId(), entity.getEncargadoId());
	}
        
        @Test
	public void agregarEncargadoRepositorioTest1(){
		RepositorioDTO dto=new RepositorioDTO();
		dto.setTipo(generateRandom(String.class));
		dto.setServidor(generateRandom(String.class));
		dto.setDestino(generateRandom(String.class));
		dto.setTipoAcceso(generateRandom(String.class));
		dto.setUbicacionDelServidor(generateRandom(String.class));
		dto.setUrl(generateRandom(String.class));
                dto.setFechaVencimiento(generateRandomDate());
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setProposito(generateRandom(String.class));
                dto.setFechaCreacion(generateRandomDate());
		dto.setDestruido(generateRandom(Boolean.class));
		dto.setCaracteristicas(generateRandom(String.class));
		dto.setEncargadoId(generateRandom(Long.class));
		
		RepositorioDTO result=repositorioPersistence.createRepositorio(dto);
		
		Assert.assertNotNull(result);
		
		RepositorioEntity entity=em.find(RepositorioEntity.class, result.getId());
		
		Assert.assertEquals(dto.getTipo(), entity.getTipo());
		Assert.assertEquals(dto.getServidor(), entity.getServidor());
		Assert.assertEquals(dto.getDestino(), entity.getDestino());
		Assert.assertEquals(dto.getTipoAcceso(), entity.getTipoAcceso());
		Assert.assertEquals(dto.getUbicacionDelServidor(), entity.getUbicacionDelServidor());
		Assert.assertEquals(dto.getUrl(), entity.getUrl());
                Assert.assertEquals(parseDate(dto.getFechaVencimiento()), entity.getFechaVencimiento());	
		Assert.assertEquals(dto.getName(), entity.getName());
		Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());
		Assert.assertEquals(dto.getProposito(), entity.getProposito());
                Assert.assertEquals(parseDate(dto.getFechaCreacion()), entity.getFechaCreacion());	
		Assert.assertEquals(dto.getDestruido(), entity.getDestruido());
		Assert.assertEquals(dto.getCaracteristicas(), entity.getCaracteristicas());
		Assert.assertEquals(dto.getEncargadoId(), entity.getEncargadoId());
                
                //Probar que exista el entity Encargadowiki
                EncargadorepositorioEntity encargado1 = em.find(EncargadorepositorioEntity.class,dto.getEncargadoId());
                EncargadorepositorioEntity encargado2 = em.find(EncargadorepositorioEntity.class,dto.getId());
            
                Assert.assertNotNull(encargado1);
                Assert.assertNotNull(encargado2);
	}
        
        @Test
	public void agregarEncargadoRepositorioTest2(){
		RepositorioDTO dto=new RepositorioDTO();
		dto.setTipo(generateRandom(String.class));
		dto.setServidor(generateRandom(String.class));
		dto.setDestino(generateRandom(String.class));
		dto.setTipoAcceso(generateRandom(String.class));
		dto.setUbicacionDelServidor(generateRandom(String.class));
		dto.setUrl(generateRandom(String.class));
                dto.setFechaVencimiento(generateRandomDate());
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setProposito(generateRandom(String.class));
                dto.setFechaCreacion(generateRandomDate());
		dto.setDestruido(generateRandom(Boolean.class));
		dto.setCaracteristicas(generateRandom(String.class));
		//dto.setEncargadoId(generateRandom(Long.class));
		
		RepositorioDTO result=repositorioPersistence.createRepositorio(dto);
		
		Assert.assertNotNull(result);
		
		RepositorioEntity entity=em.find(RepositorioEntity.class, result.getId());
		
		Assert.assertEquals(dto.getTipo(), entity.getTipo());
		Assert.assertEquals(dto.getServidor(), entity.getServidor());
		Assert.assertEquals(dto.getDestino(), entity.getDestino());
		Assert.assertEquals(dto.getTipoAcceso(), entity.getTipoAcceso());
		Assert.assertEquals(dto.getUbicacionDelServidor(), entity.getUbicacionDelServidor());
		Assert.assertEquals(dto.getUrl(), entity.getUrl());
                Assert.assertEquals(parseDate(dto.getFechaVencimiento()), entity.getFechaVencimiento());	
		Assert.assertEquals(dto.getName(), entity.getName());
		Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());
		Assert.assertEquals(dto.getProposito(), entity.getProposito());
                Assert.assertEquals(parseDate(dto.getFechaCreacion()), entity.getFechaCreacion());	
		Assert.assertEquals(dto.getDestruido(), entity.getDestruido());
		Assert.assertEquals(dto.getCaracteristicas(), entity.getCaracteristicas());
		//Assert.assertEquals(dto.getEncargadoId(), entity.getEncargadoId());
                
                //Probar que NO exista el entity Encargadowiki
                EncargadorepositorioEntity encargado1 = em.find(EncargadorepositorioEntity.class,dto.getEncargadoId());
                EncargadorepositorioEntity encargado2 = em.find(EncargadorepositorioEntity.class,dto.getId());
            
                Assert.assertNull(encargado1);
                Assert.assertNull(encargado2);
	}
	
	@Test
	public void getRepositoriosTest(){
		List<RepositorioDTO> list=repositorioPersistence.getRepositorios();
		Assert.assertEquals(list.size(), data.size());
        for(RepositorioDTO dto:list){
        	boolean found=false;
            for(RepositorioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getRepositorioTest(){
		RepositorioEntity entity=data.get(0);
		RepositorioDTO dto=repositorioPersistence.getRepositorio(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getTipo(), dto.getTipo());
		Assert.assertEquals(entity.getServidor(), dto.getServidor());
		Assert.assertEquals(entity.getDestino(), dto.getDestino());
		Assert.assertEquals(entity.getTipoAcceso(), dto.getTipoAcceso());
		Assert.assertEquals(entity.getUbicacionDelServidor(), dto.getUbicacionDelServidor());
		Assert.assertEquals(entity.getUrl(), dto.getUrl());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getDescripcion(), dto.getDescripcion());
		Assert.assertEquals(entity.getProposito(), dto.getProposito());
		Assert.assertEquals(entity.getDestruido(), dto.getDestruido());
		Assert.assertEquals(entity.getCaracteristicas(), dto.getCaracteristicas());
		Assert.assertEquals(entity.getEncargadoId(), dto.getEncargadoId());
        
	}
	
	@Test
	public void deleteRepositorioTest(){
		RepositorioEntity entity=data.get(0);
		repositorioPersistence.deleteRepositorio(entity.getId());
        RepositorioEntity deleted=em.find(RepositorioEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateRepositorioTest(){
		RepositorioEntity entity=data.get(0);
		
		RepositorioDTO dto=new RepositorioDTO();
		dto.setId(entity.getId());
		dto.setTipo(generateRandom(String.class));
		dto.setServidor(generateRandom(String.class));
		dto.setDestino(generateRandom(String.class));
		dto.setTipoAcceso(generateRandom(String.class));
		dto.setUbicacionDelServidor(generateRandom(String.class));
		dto.setUrl(generateRandom(String.class));
dto.setFechaVencimiento(generateRandomDate());
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setProposito(generateRandom(String.class));
dto.setFechaCreacion(generateRandomDate());
		dto.setDestruido(generateRandom(Boolean.class));
		dto.setCaracteristicas(generateRandom(String.class));
		dto.setEncargadoId(generateRandom(Long.class));
		
		
		repositorioPersistence.updateRepositorio(dto);
		
		
		RepositorioEntity resp=em.find(RepositorioEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getTipo(), resp.getTipo());	
		Assert.assertEquals(dto.getServidor(), resp.getServidor());	
		Assert.assertEquals(dto.getDestino(), resp.getDestino());	
		Assert.assertEquals(dto.getTipoAcceso(), resp.getTipoAcceso());	
		Assert.assertEquals(dto.getUbicacionDelServidor(), resp.getUbicacionDelServidor());	
		Assert.assertEquals(dto.getUrl(), resp.getUrl());	
Assert.assertEquals(parseDate(dto.getFechaVencimiento()), resp.getFechaVencimiento());
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(dto.getProposito(), resp.getProposito());	
Assert.assertEquals(parseDate(dto.getFechaCreacion()), resp.getFechaCreacion());
		Assert.assertEquals(dto.getDestruido(), resp.getDestruido());	
		Assert.assertEquals(dto.getCaracteristicas(), resp.getCaracteristicas());	
		Assert.assertEquals(dto.getEncargadoId(), resp.getEncargadoId());	
	}
	
	@Test
	public void getRepositorioPaginationTest(){
		//Page 1
		RepositorioPageDTO dto1=repositorioPersistence.getRepositorios(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        RepositorioPageDTO dto2=repositorioPersistence.getRepositorios(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(RepositorioDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(RepositorioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(RepositorioDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(RepositorioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
        }
	
