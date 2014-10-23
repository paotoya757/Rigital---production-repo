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

package co.edu.uniandes.csw.grupo.problema.logic.ejb;

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


import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaPageDTO;
import co.edu.uniandes.csw.grupo.problema.logic.dto.ProblemaDTO;
import co.edu.uniandes.csw.grupo.problema.logic.api.IProblemaLogicService;
import co.edu.uniandes.csw.grupo.problema.persistence.ProblemaPersistence;
import co.edu.uniandes.csw.grupo.problema.persistence.api.IProblemaPersistence;
import co.edu.uniandes.csw.grupo.problema.persistence.entity.ProblemaEntity;
import co.edu.uniandes.csw.grupo.problema.persistence.converter.ProblemaConverter;
import static co.edu.uniandes.csw.grupo.util._TestUtil.*;

@RunWith(Arquillian.class)
public class ProblemaLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ProblemaLogicService.class.getPackage())
				.addPackage(IProblemaLogicService.class.getPackage())
				.addPackage(ProblemaPersistence.class.getPackage())
				.addPackage(ProblemaEntity.class.getPackage())
				.addPackage(IProblemaPersistence.class.getPackage())
                .addPackage(ProblemaDTO.class.getPackage())
                .addPackage(ProblemaConverter.class.getPackage())
                .addPackage(ProblemaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IProblemaLogicService problemaLogicService;
	
	@Inject
	private IProblemaPersistence problemaPersistence;	

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
		List<ProblemaDTO> dtos=problemaPersistence.getProblemas();
		for(ProblemaDTO dto:dtos){
			problemaPersistence.deleteProblema(dto.getId());
		}
	}

	private List<ProblemaDTO> data=new ArrayList<ProblemaDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ProblemaDTO pdto=new ProblemaDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setDescripcion(generateRandom(String.class));
			pdto.setFechaDeOcurrencia(generateRandomDate());
			pdto.setEmpleadoId(generateRandom(Long.class));
			pdto.setSqldevId(generateRandom(Long.class));
			pdto.setMysqlId(generateRandom(Long.class));
			pdto.setPaginawebId(generateRandom(Long.class));
			pdto.setWikiId(generateRandom(Long.class));
			pdto.setMaquinavirtualId(generateRandom(Long.class));
			pdto.setUnidadderedId(generateRandom(Long.class));
			pdto.setRepositorioId(generateRandom(Long.class));
			pdto.setContenedorwebId(generateRandom(Long.class));
			pdto.setSoftwaresalasId(generateRandom(Long.class));
			pdto=problemaPersistence.createProblema(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createProblemaTest(){
		ProblemaDTO ldto=new ProblemaDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setDescripcion(generateRandom(String.class));
		ldto.setFechaDeOcurrencia(generateRandomDate());
		ldto.setEmpleadoId(generateRandom(Long.class));
		ldto.setSqldevId(generateRandom(Long.class));
		ldto.setMysqlId(generateRandom(Long.class));
		ldto.setPaginawebId(generateRandom(Long.class));
		ldto.setWikiId(generateRandom(Long.class));
		ldto.setMaquinavirtualId(generateRandom(Long.class));
		ldto.setUnidadderedId(generateRandom(Long.class));
		ldto.setRepositorioId(generateRandom(Long.class));
		ldto.setContenedorwebId(generateRandom(Long.class));
		ldto.setSoftwaresalasId(generateRandom(Long.class));
		
		
		ProblemaDTO result=problemaLogicService.createProblema(ldto);
		
		Assert.assertNotNull(result);
		
		ProblemaDTO pdto=problemaPersistence.getProblema(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getDescripcion(), pdto.getDescripcion());	
		Assert.assertEquals(ldto.getFechaDeOcurrencia(), pdto.getFechaDeOcurrencia());	
		Assert.assertEquals(ldto.getEmpleadoId(), pdto.getEmpleadoId());	
		Assert.assertEquals(ldto.getSqldevId(), pdto.getSqldevId());	
		Assert.assertEquals(ldto.getMysqlId(), pdto.getMysqlId());	
		Assert.assertEquals(ldto.getPaginawebId(), pdto.getPaginawebId());	
		Assert.assertEquals(ldto.getWikiId(), pdto.getWikiId());	
		Assert.assertEquals(ldto.getMaquinavirtualId(), pdto.getMaquinavirtualId());	
		Assert.assertEquals(ldto.getUnidadderedId(), pdto.getUnidadderedId());	
		Assert.assertEquals(ldto.getRepositorioId(), pdto.getRepositorioId());	
		Assert.assertEquals(ldto.getContenedorwebId(), pdto.getContenedorwebId());	
		Assert.assertEquals(ldto.getSoftwaresalasId(), pdto.getSoftwaresalasId());	
	}
	
	@Test
	public void getProblemasTest(){
		List<ProblemaDTO> list=problemaLogicService.getProblemas();
		Assert.assertEquals(list.size(), data.size());
        for(ProblemaDTO ldto:list){
        	boolean found=false;
            for(ProblemaDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getProblemaTest(){
		ProblemaDTO pdto=data.get(0);
		ProblemaDTO ldto=problemaLogicService.getProblema(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getDescripcion(), ldto.getDescripcion());
		Assert.assertEquals(pdto.getFechaDeOcurrencia(), ldto.getFechaDeOcurrencia());
		Assert.assertEquals(pdto.getEmpleadoId(), ldto.getEmpleadoId());
		Assert.assertEquals(pdto.getSqldevId(), ldto.getSqldevId());
		Assert.assertEquals(pdto.getMysqlId(), ldto.getMysqlId());
		Assert.assertEquals(pdto.getPaginawebId(), ldto.getPaginawebId());
		Assert.assertEquals(pdto.getWikiId(), ldto.getWikiId());
		Assert.assertEquals(pdto.getMaquinavirtualId(), ldto.getMaquinavirtualId());
		Assert.assertEquals(pdto.getUnidadderedId(), ldto.getUnidadderedId());
		Assert.assertEquals(pdto.getRepositorioId(), ldto.getRepositorioId());
		Assert.assertEquals(pdto.getContenedorwebId(), ldto.getContenedorwebId());
		Assert.assertEquals(pdto.getSoftwaresalasId(), ldto.getSoftwaresalasId());
        
	}
	
	@Test
	public void deleteProblemaTest(){
		ProblemaDTO pdto=data.get(0);
		problemaLogicService.deleteProblema(pdto.getId());
        ProblemaDTO deleted=problemaPersistence.getProblema(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateProblemaTest(){
		ProblemaDTO pdto=data.get(0);
		
		ProblemaDTO ldto=new ProblemaDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setDescripcion(generateRandom(String.class));
		ldto.setFechaDeOcurrencia(generateRandomDate());
		ldto.setEmpleadoId(generateRandom(Long.class));
		ldto.setSqldevId(generateRandom(Long.class));
		ldto.setMysqlId(generateRandom(Long.class));
		ldto.setPaginawebId(generateRandom(Long.class));
		ldto.setWikiId(generateRandom(Long.class));
		ldto.setMaquinavirtualId(generateRandom(Long.class));
		ldto.setUnidadderedId(generateRandom(Long.class));
		ldto.setRepositorioId(generateRandom(Long.class));
		ldto.setContenedorwebId(generateRandom(Long.class));
		ldto.setSoftwaresalasId(generateRandom(Long.class));
		
		
		problemaLogicService.updateProblema(ldto);
		
		
		ProblemaDTO resp=problemaPersistence.getProblema(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(ldto.getFechaDeOcurrencia(), resp.getFechaDeOcurrencia());	
		Assert.assertEquals(ldto.getEmpleadoId(), resp.getEmpleadoId());	
		Assert.assertEquals(ldto.getSqldevId(), resp.getSqldevId());	
		Assert.assertEquals(ldto.getMysqlId(), resp.getMysqlId());	
		Assert.assertEquals(ldto.getPaginawebId(), resp.getPaginawebId());	
		Assert.assertEquals(ldto.getWikiId(), resp.getWikiId());	
		Assert.assertEquals(ldto.getMaquinavirtualId(), resp.getMaquinavirtualId());	
		Assert.assertEquals(ldto.getUnidadderedId(), resp.getUnidadderedId());	
		Assert.assertEquals(ldto.getRepositorioId(), resp.getRepositorioId());	
		Assert.assertEquals(ldto.getContenedorwebId(), resp.getContenedorwebId());	
		Assert.assertEquals(ldto.getSoftwaresalasId(), resp.getSoftwaresalasId());	
	}
	
	@Test
	public void getProblemaPaginationTest(){
		
		ProblemaPageDTO dto1=problemaLogicService.getProblemas(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		ProblemaPageDTO dto2=problemaLogicService.getProblemas(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(ProblemaDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(ProblemaDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(ProblemaDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(ProblemaDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        ProblemaPageDTO dto3=problemaLogicService.getProblemas(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(ProblemaDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(ProblemaDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}