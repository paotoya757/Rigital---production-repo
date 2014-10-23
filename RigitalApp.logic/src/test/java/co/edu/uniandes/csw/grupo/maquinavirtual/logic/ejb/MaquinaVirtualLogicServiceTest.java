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

package co.edu.uniandes.csw.grupo.maquinavirtual.logic.ejb;

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


import co.edu.uniandes.csw.grupo.maquinavirtual.logic.dto.MaquinaVirtualPageDTO;
import co.edu.uniandes.csw.grupo.maquinavirtual.logic.dto.MaquinaVirtualDTO;
import co.edu.uniandes.csw.grupo.maquinavirtual.logic.api.IMaquinaVirtualLogicService;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.MaquinaVirtualPersistence;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.api.IMaquinaVirtualPersistence;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.entity.MaquinaVirtualEntity;
import co.edu.uniandes.csw.grupo.maquinavirtual.persistence.converter.MaquinaVirtualConverter;
import static co.edu.uniandes.csw.grupo.util._TestUtil.*;

@RunWith(Arquillian.class)
public class MaquinaVirtualLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(MaquinaVirtualLogicService.class.getPackage())
				.addPackage(IMaquinaVirtualLogicService.class.getPackage())
				.addPackage(MaquinaVirtualPersistence.class.getPackage())
				.addPackage(MaquinaVirtualEntity.class.getPackage())
				.addPackage(IMaquinaVirtualPersistence.class.getPackage())
                .addPackage(MaquinaVirtualDTO.class.getPackage())
                .addPackage(MaquinaVirtualConverter.class.getPackage())
                .addPackage(MaquinaVirtualEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IMaquinaVirtualLogicService maquinaVirtualLogicService;
	
	@Inject
	private IMaquinaVirtualPersistence maquinaVirtualPersistence;	

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
		List<MaquinaVirtualDTO> dtos=maquinaVirtualPersistence.getMaquinaVirtuals();
		for(MaquinaVirtualDTO dto:dtos){
			maquinaVirtualPersistence.deleteMaquinaVirtual(dto.getId());
		}
	}

	private List<MaquinaVirtualDTO> data=new ArrayList<MaquinaVirtualDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			MaquinaVirtualDTO pdto=new MaquinaVirtualDTO();
			pdto.setIps(generateRandom(String.class));
			pdto.setServiciosAsociados(generateRandom(String.class));
			pdto.setVersion(generateRandom(String.class));
			pdto.setSistemaOperativo(generateRandom(String.class));
			pdto.setNumDiscos(generateRandom(Integer.class));
			pdto.setCapacidadDiscos(generateRandom(Long.class));
			pdto.setRam(generateRandom(String.class));
			pdto.setNumCores(generateRandom(Integer.class));
			pdto.setFechaVencimiento(generateRandomDate());
			pdto.setName(generateRandom(String.class));
			pdto.setDescripcion(generateRandom(String.class));
			pdto.setProposito(generateRandom(String.class));
			pdto.setFechaCreacion(generateRandomDate());
			pdto.setDestruido(generateRandom(Boolean.class));
			pdto.setCaracteristicas(generateRandom(String.class));
			pdto.setEncargadoId(generateRandom(Long.class));
			pdto=maquinaVirtualPersistence.createMaquinaVirtual(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createMaquinaVirtualTest(){
		MaquinaVirtualDTO ldto=new MaquinaVirtualDTO();
		ldto.setIps(generateRandom(String.class));
		ldto.setServiciosAsociados(generateRandom(String.class));
		ldto.setVersion(generateRandom(String.class));
		ldto.setSistemaOperativo(generateRandom(String.class));
		ldto.setNumDiscos(generateRandom(Integer.class));
		ldto.setCapacidadDiscos(generateRandom(Long.class));
		ldto.setRam(generateRandom(String.class));
		ldto.setNumCores(generateRandom(Integer.class));
		ldto.setFechaVencimiento(generateRandomDate());
		ldto.setName(generateRandom(String.class));
		ldto.setDescripcion(generateRandom(String.class));
		ldto.setProposito(generateRandom(String.class));
		ldto.setFechaCreacion(generateRandomDate());
		ldto.setDestruido(generateRandom(Boolean.class));
		ldto.setCaracteristicas(generateRandom(String.class));
		ldto.setEncargadoId(generateRandom(Long.class));
		
		
		MaquinaVirtualDTO result=maquinaVirtualLogicService.createMaquinaVirtual(ldto);
		
		Assert.assertNotNull(result);
		
		MaquinaVirtualDTO pdto=maquinaVirtualPersistence.getMaquinaVirtual(result.getId());
		
		Assert.assertEquals(ldto.getIps(), pdto.getIps());	
		Assert.assertEquals(ldto.getServiciosAsociados(), pdto.getServiciosAsociados());	
		Assert.assertEquals(ldto.getVersion(), pdto.getVersion());	
		Assert.assertEquals(ldto.getSistemaOperativo(), pdto.getSistemaOperativo());	
		Assert.assertEquals(ldto.getNumDiscos(), pdto.getNumDiscos());	
		Assert.assertEquals(ldto.getCapacidadDiscos(), pdto.getCapacidadDiscos());	
		Assert.assertEquals(ldto.getRam(), pdto.getRam());	
		Assert.assertEquals(ldto.getNumCores(), pdto.getNumCores());	
		Assert.assertEquals(ldto.getFechaVencimiento(), pdto.getFechaVencimiento());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getDescripcion(), pdto.getDescripcion());	
		Assert.assertEquals(ldto.getProposito(), pdto.getProposito());	
		Assert.assertEquals(ldto.getFechaCreacion(), pdto.getFechaCreacion());	
		Assert.assertEquals(ldto.getDestruido(), pdto.getDestruido());	
		Assert.assertEquals(ldto.getCaracteristicas(), pdto.getCaracteristicas());	
		Assert.assertEquals(ldto.getEncargadoId(), pdto.getEncargadoId());	
	}
	
	@Test
	public void getMaquinaVirtualsTest(){
		List<MaquinaVirtualDTO> list=maquinaVirtualLogicService.getMaquinaVirtuals();
		Assert.assertEquals(list.size(), data.size());
        for(MaquinaVirtualDTO ldto:list){
        	boolean found=false;
            for(MaquinaVirtualDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getMaquinaVirtualTest(){
		MaquinaVirtualDTO pdto=data.get(0);
		MaquinaVirtualDTO ldto=maquinaVirtualLogicService.getMaquinaVirtual(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getIps(), ldto.getIps());
		Assert.assertEquals(pdto.getServiciosAsociados(), ldto.getServiciosAsociados());
		Assert.assertEquals(pdto.getVersion(), ldto.getVersion());
		Assert.assertEquals(pdto.getSistemaOperativo(), ldto.getSistemaOperativo());
		Assert.assertEquals(pdto.getNumDiscos(), ldto.getNumDiscos());
		Assert.assertEquals(pdto.getCapacidadDiscos(), ldto.getCapacidadDiscos());
		Assert.assertEquals(pdto.getRam(), ldto.getRam());
		Assert.assertEquals(pdto.getNumCores(), ldto.getNumCores());
		Assert.assertEquals(pdto.getFechaVencimiento(), ldto.getFechaVencimiento());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getDescripcion(), ldto.getDescripcion());
		Assert.assertEquals(pdto.getProposito(), ldto.getProposito());
		Assert.assertEquals(pdto.getFechaCreacion(), ldto.getFechaCreacion());
		Assert.assertEquals(pdto.getDestruido(), ldto.getDestruido());
		Assert.assertEquals(pdto.getCaracteristicas(), ldto.getCaracteristicas());
		Assert.assertEquals(pdto.getEncargadoId(), ldto.getEncargadoId());
        
	}
	
	@Test
	public void deleteMaquinaVirtualTest(){
		MaquinaVirtualDTO pdto=data.get(0);
		maquinaVirtualLogicService.deleteMaquinaVirtual(pdto.getId());
        MaquinaVirtualDTO deleted=maquinaVirtualPersistence.getMaquinaVirtual(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateMaquinaVirtualTest(){
		MaquinaVirtualDTO pdto=data.get(0);
		
		MaquinaVirtualDTO ldto=new MaquinaVirtualDTO();
		ldto.setId(pdto.getId());
		ldto.setIps(generateRandom(String.class));
		ldto.setServiciosAsociados(generateRandom(String.class));
		ldto.setVersion(generateRandom(String.class));
		ldto.setSistemaOperativo(generateRandom(String.class));
		ldto.setNumDiscos(generateRandom(Integer.class));
		ldto.setCapacidadDiscos(generateRandom(Long.class));
		ldto.setRam(generateRandom(String.class));
		ldto.setNumCores(generateRandom(Integer.class));
		ldto.setFechaVencimiento(generateRandomDate());
		ldto.setName(generateRandom(String.class));
		ldto.setDescripcion(generateRandom(String.class));
		ldto.setProposito(generateRandom(String.class));
		ldto.setFechaCreacion(generateRandomDate());
		ldto.setDestruido(generateRandom(Boolean.class));
		ldto.setCaracteristicas(generateRandom(String.class));
		ldto.setEncargadoId(generateRandom(Long.class));
		
		
		maquinaVirtualLogicService.updateMaquinaVirtual(ldto);
		
		
		MaquinaVirtualDTO resp=maquinaVirtualPersistence.getMaquinaVirtual(pdto.getId());
		
		Assert.assertEquals(ldto.getIps(), resp.getIps());	
		Assert.assertEquals(ldto.getServiciosAsociados(), resp.getServiciosAsociados());	
		Assert.assertEquals(ldto.getVersion(), resp.getVersion());	
		Assert.assertEquals(ldto.getSistemaOperativo(), resp.getSistemaOperativo());	
		Assert.assertEquals(ldto.getNumDiscos(), resp.getNumDiscos());	
		Assert.assertEquals(ldto.getCapacidadDiscos(), resp.getCapacidadDiscos());	
		Assert.assertEquals(ldto.getRam(), resp.getRam());	
		Assert.assertEquals(ldto.getNumCores(), resp.getNumCores());	
		Assert.assertEquals(ldto.getFechaVencimiento(), resp.getFechaVencimiento());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(ldto.getProposito(), resp.getProposito());	
		Assert.assertEquals(ldto.getFechaCreacion(), resp.getFechaCreacion());	
		Assert.assertEquals(ldto.getDestruido(), resp.getDestruido());	
		Assert.assertEquals(ldto.getCaracteristicas(), resp.getCaracteristicas());	
		Assert.assertEquals(ldto.getEncargadoId(), resp.getEncargadoId());	
	}
	
	@Test
	public void getMaquinaVirtualPaginationTest(){
		
		MaquinaVirtualPageDTO dto1=maquinaVirtualLogicService.getMaquinaVirtuals(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		MaquinaVirtualPageDTO dto2=maquinaVirtualLogicService.getMaquinaVirtuals(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(MaquinaVirtualDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(MaquinaVirtualDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(MaquinaVirtualDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(MaquinaVirtualDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        MaquinaVirtualPageDTO dto3=maquinaVirtualLogicService.getMaquinaVirtuals(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(MaquinaVirtualDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(MaquinaVirtualDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}