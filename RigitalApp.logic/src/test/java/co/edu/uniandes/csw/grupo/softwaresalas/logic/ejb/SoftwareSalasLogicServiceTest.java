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

package co.edu.uniandes.csw.grupo.softwaresalas.logic.ejb;

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


import co.edu.uniandes.csw.grupo.softwaresalas.logic.dto.SoftwareSalasPageDTO;
import co.edu.uniandes.csw.grupo.softwaresalas.logic.dto.SoftwareSalasDTO;
import co.edu.uniandes.csw.grupo.softwaresalas.logic.api.ISoftwareSalasLogicService;
import co.edu.uniandes.csw.grupo.softwaresalas.persistence.SoftwareSalasPersistence;
import co.edu.uniandes.csw.grupo.softwaresalas.persistence.api.ISoftwareSalasPersistence;
import co.edu.uniandes.csw.grupo.softwaresalas.persistence.entity.SoftwareSalasEntity;
import co.edu.uniandes.csw.grupo.softwaresalas.persistence.converter.SoftwareSalasConverter;
import static co.edu.uniandes.csw.grupo.util._TestUtil.*;

@RunWith(Arquillian.class)
public class SoftwareSalasLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(SoftwareSalasLogicService.class.getPackage())
				.addPackage(ISoftwareSalasLogicService.class.getPackage())
				.addPackage(SoftwareSalasPersistence.class.getPackage())
				.addPackage(SoftwareSalasEntity.class.getPackage())
				.addPackage(ISoftwareSalasPersistence.class.getPackage())
                .addPackage(SoftwareSalasDTO.class.getPackage())
                .addPackage(SoftwareSalasConverter.class.getPackage())
                .addPackage(SoftwareSalasEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ISoftwareSalasLogicService softwareSalasLogicService;
	
	@Inject
	private ISoftwareSalasPersistence softwareSalasPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<SoftwareSalasDTO> dtos=softwareSalasPersistence.getSoftwareSalass();
		for(SoftwareSalasDTO dto:dtos){
			softwareSalasPersistence.deleteSoftwareSalas(dto.getId());
		}
	}

	private List<SoftwareSalasDTO> data=new ArrayList<SoftwareSalasDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			SoftwareSalasDTO pdto=new SoftwareSalasDTO();
			pdto.setTipoMaquina(generateRandom(String.class));
			pdto.setSoftware(generateRandom(String.class));
			pdto.setVersion(generateRandom(String.class));
			pdto.setSolicitante(generateRandom(String.class));
			pdto.setNumeroMaquina(generateRandom(String.class));
			pdto.setName(generateRandom(String.class));
			pdto.setDescripcion(generateRandom(String.class));
			pdto.setProposito(generateRandom(String.class));
			pdto.setFechaCreacion(generateRandomDate());
			pdto.setDestruido(generateRandom(Boolean.class));
			pdto.setCaracteristicas(generateRandom(String.class));
			pdto.setWorkstationId(generateRandom(Long.class));
			pdto=softwareSalasPersistence.createSoftwareSalas(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createSoftwareSalasTest(){
		SoftwareSalasDTO ldto=new SoftwareSalasDTO();
		ldto.setTipoMaquina(generateRandom(String.class));
		ldto.setSoftware(generateRandom(String.class));
		ldto.setVersion(generateRandom(String.class));
		ldto.setSolicitante(generateRandom(String.class));
		ldto.setNumeroMaquina(generateRandom(String.class));
		ldto.setName(generateRandom(String.class));
		ldto.setDescripcion(generateRandom(String.class));
		ldto.setProposito(generateRandom(String.class));
		ldto.setFechaCreacion(generateRandomDate());
		ldto.setDestruido(generateRandom(Boolean.class));
		ldto.setCaracteristicas(generateRandom(String.class));
		ldto.setWorkstationId(generateRandom(Long.class));
		
		
		SoftwareSalasDTO result=softwareSalasLogicService.createSoftwareSalas(ldto);
		
		Assert.assertNotNull(result);
		
		SoftwareSalasDTO pdto=softwareSalasPersistence.getSoftwareSalas(result.getId());
		
		Assert.assertEquals(ldto.getTipoMaquina(), pdto.getTipoMaquina());	
		Assert.assertEquals(ldto.getSoftware(), pdto.getSoftware());	
		Assert.assertEquals(ldto.getVersion(), pdto.getVersion());	
		Assert.assertEquals(ldto.getSolicitante(), pdto.getSolicitante());	
		Assert.assertEquals(ldto.getNumeroMaquina(), pdto.getNumeroMaquina());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getDescripcion(), pdto.getDescripcion());	
		Assert.assertEquals(ldto.getProposito(), pdto.getProposito());	
		Assert.assertEquals(ldto.getFechaCreacion(), pdto.getFechaCreacion());	
		Assert.assertEquals(ldto.getDestruido(), pdto.getDestruido());	
		Assert.assertEquals(ldto.getCaracteristicas(), pdto.getCaracteristicas());	
		Assert.assertEquals(ldto.getWorkstationId(), pdto.getWorkstationId());	
	}
	
	@Test
	public void getSoftwareSalassTest(){
		List<SoftwareSalasDTO> list=softwareSalasLogicService.getSoftwareSalass();
		Assert.assertEquals(list.size(), data.size());
        for(SoftwareSalasDTO ldto:list){
        	boolean found=false;
            for(SoftwareSalasDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getSoftwareSalasTest(){
		SoftwareSalasDTO pdto=data.get(0);
		SoftwareSalasDTO ldto=softwareSalasLogicService.getSoftwareSalas(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getTipoMaquina(), ldto.getTipoMaquina());
		Assert.assertEquals(pdto.getSoftware(), ldto.getSoftware());
		Assert.assertEquals(pdto.getVersion(), ldto.getVersion());
		Assert.assertEquals(pdto.getSolicitante(), ldto.getSolicitante());
		Assert.assertEquals(pdto.getNumeroMaquina(), ldto.getNumeroMaquina());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getDescripcion(), ldto.getDescripcion());
		Assert.assertEquals(pdto.getProposito(), ldto.getProposito());
		Assert.assertEquals(pdto.getFechaCreacion(), ldto.getFechaCreacion());
		Assert.assertEquals(pdto.getDestruido(), ldto.getDestruido());
		Assert.assertEquals(pdto.getCaracteristicas(), ldto.getCaracteristicas());
		Assert.assertEquals(pdto.getWorkstationId(), ldto.getWorkstationId());
        
	}
	
	@Test
	public void deleteSoftwareSalasTest(){
		SoftwareSalasDTO pdto=data.get(0);
		softwareSalasLogicService.deleteSoftwareSalas(pdto.getId());
        SoftwareSalasDTO deleted=softwareSalasPersistence.getSoftwareSalas(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateSoftwareSalasTest(){
		SoftwareSalasDTO pdto=data.get(0);
		
		SoftwareSalasDTO ldto=new SoftwareSalasDTO();
		ldto.setId(pdto.getId());
		ldto.setTipoMaquina(generateRandom(String.class));
		ldto.setSoftware(generateRandom(String.class));
		ldto.setVersion(generateRandom(String.class));
		ldto.setSolicitante(generateRandom(String.class));
		ldto.setNumeroMaquina(generateRandom(String.class));
		ldto.setName(generateRandom(String.class));
		ldto.setDescripcion(generateRandom(String.class));
		ldto.setProposito(generateRandom(String.class));
		ldto.setFechaCreacion(generateRandomDate());
		ldto.setDestruido(generateRandom(Boolean.class));
		ldto.setCaracteristicas(generateRandom(String.class));
		ldto.setWorkstationId(generateRandom(Long.class));
		
		
		softwareSalasLogicService.updateSoftwareSalas(ldto);
		
		
		SoftwareSalasDTO resp=softwareSalasPersistence.getSoftwareSalas(pdto.getId());
		
		Assert.assertEquals(ldto.getTipoMaquina(), resp.getTipoMaquina());	
		Assert.assertEquals(ldto.getSoftware(), resp.getSoftware());	
		Assert.assertEquals(ldto.getVersion(), resp.getVersion());	
		Assert.assertEquals(ldto.getSolicitante(), resp.getSolicitante());	
		Assert.assertEquals(ldto.getNumeroMaquina(), resp.getNumeroMaquina());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(ldto.getProposito(), resp.getProposito());	
		Assert.assertEquals(ldto.getFechaCreacion(), resp.getFechaCreacion());	
		Assert.assertEquals(ldto.getDestruido(), resp.getDestruido());	
		Assert.assertEquals(ldto.getCaracteristicas(), resp.getCaracteristicas());	
		Assert.assertEquals(ldto.getWorkstationId(), resp.getWorkstationId());	
	}
	
	@Test
	public void getSoftwareSalasPaginationTest(){
		
		SoftwareSalasPageDTO dto1=softwareSalasLogicService.getSoftwareSalass(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		SoftwareSalasPageDTO dto2=softwareSalasLogicService.getSoftwareSalass(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(SoftwareSalasDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(SoftwareSalasDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(SoftwareSalasDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(SoftwareSalasDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        SoftwareSalasPageDTO dto3=softwareSalasLogicService.getSoftwareSalass(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(SoftwareSalasDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(SoftwareSalasDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}