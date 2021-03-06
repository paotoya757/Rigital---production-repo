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

package co.edu.uniandes.csw.grupo.workstation.logic.ejb;

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


import co.edu.uniandes.csw.grupo.workstation.logic.dto.WorkstationPageDTO;
import co.edu.uniandes.csw.grupo.workstation.logic.dto.WorkstationDTO;
import co.edu.uniandes.csw.grupo.workstation.logic.api.IWorkstationLogicService;
import co.edu.uniandes.csw.grupo.workstation.persistence.WorkstationPersistence;
import co.edu.uniandes.csw.grupo.workstation.persistence.api.IWorkstationPersistence;
import co.edu.uniandes.csw.grupo.workstation.persistence.entity.WorkstationEntity;
import co.edu.uniandes.csw.grupo.workstation.persistence.converter.WorkstationConverter;
import static co.edu.uniandes.csw.grupo.util._TestUtil.*;

@RunWith(Arquillian.class)
public class WorkstationLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(WorkstationLogicService.class.getPackage())
				.addPackage(IWorkstationLogicService.class.getPackage())
				.addPackage(WorkstationPersistence.class.getPackage())
				.addPackage(WorkstationEntity.class.getPackage())
				.addPackage(IWorkstationPersistence.class.getPackage())
                .addPackage(WorkstationDTO.class.getPackage())
                .addPackage(WorkstationConverter.class.getPackage())
                .addPackage(WorkstationEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IWorkstationLogicService workstationLogicService;
	
	@Inject
	private IWorkstationPersistence workstationPersistence;	

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
		List<WorkstationDTO> dtos=workstationPersistence.getWorkstations();
		for(WorkstationDTO dto:dtos){
			workstationPersistence.deleteWorkstation(dto.getId());
		}
	}

	private List<WorkstationDTO> data=new ArrayList<WorkstationDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			WorkstationDTO pdto=new WorkstationDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setDuenio(generateRandom(String.class));
			pdto.setDestino(generateRandom(String.class));
			pdto.setSistemaOperativo(generateRandom(String.class));
			pdto=workstationPersistence.createWorkstation(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createWorkstationTest(){
		WorkstationDTO ldto=new WorkstationDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setDuenio(generateRandom(String.class));
		ldto.setDestino(generateRandom(String.class));
		ldto.setSistemaOperativo(generateRandom(String.class));
		
		
		WorkstationDTO result=workstationLogicService.createWorkstation(ldto);
		
		Assert.assertNotNull(result);
		
		WorkstationDTO pdto=workstationPersistence.getWorkstation(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getDuenio(), pdto.getDuenio());	
		Assert.assertEquals(ldto.getDestino(), pdto.getDestino());	
		Assert.assertEquals(ldto.getSistemaOperativo(), pdto.getSistemaOperativo());	
	}
	
	@Test
	public void getWorkstationsTest(){
		List<WorkstationDTO> list=workstationLogicService.getWorkstations();
		Assert.assertEquals(list.size(), data.size());
        for(WorkstationDTO ldto:list){
        	boolean found=false;
            for(WorkstationDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getWorkstationTest(){
		WorkstationDTO pdto=data.get(0);
		WorkstationDTO ldto=workstationLogicService.getWorkstation(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getDuenio(), ldto.getDuenio());
		Assert.assertEquals(pdto.getDestino(), ldto.getDestino());
		Assert.assertEquals(pdto.getSistemaOperativo(), ldto.getSistemaOperativo());
        
	}
	
	@Test
	public void deleteWorkstationTest(){
		WorkstationDTO pdto=data.get(0);
		workstationLogicService.deleteWorkstation(pdto.getId());
        WorkstationDTO deleted=workstationPersistence.getWorkstation(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateWorkstationTest(){
		WorkstationDTO pdto=data.get(0);
		
		WorkstationDTO ldto=new WorkstationDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setDuenio(generateRandom(String.class));
		ldto.setDestino(generateRandom(String.class));
		ldto.setSistemaOperativo(generateRandom(String.class));
		
		
		workstationLogicService.updateWorkstation(ldto);
		
		
		WorkstationDTO resp=workstationPersistence.getWorkstation(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getDuenio(), resp.getDuenio());	
		Assert.assertEquals(ldto.getDestino(), resp.getDestino());	
		Assert.assertEquals(ldto.getSistemaOperativo(), resp.getSistemaOperativo());	
	}
	
	@Test
	public void getWorkstationPaginationTest(){
		
		WorkstationPageDTO dto1=workstationLogicService.getWorkstations(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		WorkstationPageDTO dto2=workstationLogicService.getWorkstations(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(WorkstationDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(WorkstationDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(WorkstationDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(WorkstationDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        WorkstationPageDTO dto3=workstationLogicService.getWorkstations(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(WorkstationDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(WorkstationDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}