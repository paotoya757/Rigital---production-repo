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

package co.edu.uniandes.csw.grupo.wiki.persistence;

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


import co.edu.uniandes.csw.grupo.wiki.logic.dto.WikiPageDTO;
import co.edu.uniandes.csw.grupo.wiki.logic.dto.WikiDTO;
import co.edu.uniandes.csw.grupo.wiki.persistence.api.IWikiPersistence;
import co.edu.uniandes.csw.grupo.wiki.persistence.entity.WikiEntity;
import co.edu.uniandes.csw.grupo.wiki.persistence.converter.WikiConverter;
import static co.edu.uniandes.csw.grupo.util._TestUtil.*;

@RunWith(Arquillian.class)
public class WikiPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(WikiPersistence.class.getPackage())
				.addPackage(WikiEntity.class.getPackage())
				.addPackage(IWikiPersistence.class.getPackage())
                .addPackage(WikiDTO.class.getPackage())
                .addPackage(WikiConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IWikiPersistence wikiPersistence;

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
		em.createQuery("delete from WikiEntity").executeUpdate();
	}

	private List<WikiEntity> data=new ArrayList<WikiEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			WikiEntity entity=new WikiEntity();
			entity.setServidor_host(generateRandom(String.class));
			entity.setRutaServidor(generateRandom(String.class));
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
	public void createWikiTest(){
		WikiDTO dto=new WikiDTO();
		dto.setServidor_host(generateRandom(String.class));
		dto.setRutaServidor(generateRandom(String.class));
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setProposito(generateRandom(String.class));
dto.setFechaCreacion(generateRandomDate());
		dto.setDestruido(generateRandom(Boolean.class));
		dto.setCaracteristicas(generateRandom(String.class));
		dto.setEncargadoId(generateRandom(Long.class));
		
		WikiDTO result=wikiPersistence.createWiki(dto);
		
		Assert.assertNotNull(result);
		
		WikiEntity entity=em.find(WikiEntity.class, result.getId());
		
		Assert.assertEquals(dto.getServidor_host(), entity.getServidor_host());
		Assert.assertEquals(dto.getRutaServidor(), entity.getRutaServidor());
		Assert.assertEquals(dto.getName(), entity.getName());
		Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());
		Assert.assertEquals(dto.getProposito(), entity.getProposito());
Assert.assertEquals(parseDate(dto.getFechaCreacion()), entity.getFechaCreacion());	
		Assert.assertEquals(dto.getDestruido(), entity.getDestruido());
		Assert.assertEquals(dto.getCaracteristicas(), entity.getCaracteristicas());
		Assert.assertEquals(dto.getEncargadoId(), entity.getEncargadoId());
	}
	
	@Test
	public void getWikisTest(){
		List<WikiDTO> list=wikiPersistence.getWikis();
		Assert.assertEquals(list.size(), data.size());
        for(WikiDTO dto:list){
        	boolean found=false;
            for(WikiEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getWikiTest(){
		WikiEntity entity=data.get(0);
		WikiDTO dto=wikiPersistence.getWiki(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getServidor_host(), dto.getServidor_host());
		Assert.assertEquals(entity.getRutaServidor(), dto.getRutaServidor());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getDescripcion(), dto.getDescripcion());
		Assert.assertEquals(entity.getProposito(), dto.getProposito());
		Assert.assertEquals(entity.getDestruido(), dto.getDestruido());
		Assert.assertEquals(entity.getCaracteristicas(), dto.getCaracteristicas());
		Assert.assertEquals(entity.getEncargadoId(), dto.getEncargadoId());
        
	}
	
	@Test
	public void deleteWikiTest(){
		WikiEntity entity=data.get(0);
		wikiPersistence.deleteWiki(entity.getId());
        WikiEntity deleted=em.find(WikiEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateWikiTest(){
		WikiEntity entity=data.get(0);
		
		WikiDTO dto=new WikiDTO();
		dto.setId(entity.getId());
		dto.setServidor_host(generateRandom(String.class));
		dto.setRutaServidor(generateRandom(String.class));
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setProposito(generateRandom(String.class));
dto.setFechaCreacion(generateRandomDate());
		dto.setDestruido(generateRandom(Boolean.class));
		dto.setCaracteristicas(generateRandom(String.class));
		dto.setEncargadoId(generateRandom(Long.class));
		
		
		wikiPersistence.updateWiki(dto);
		
		
		WikiEntity resp=em.find(WikiEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getServidor_host(), resp.getServidor_host());	
		Assert.assertEquals(dto.getRutaServidor(), resp.getRutaServidor());	
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(dto.getProposito(), resp.getProposito());	
Assert.assertEquals(parseDate(dto.getFechaCreacion()), resp.getFechaCreacion());
		Assert.assertEquals(dto.getDestruido(), resp.getDestruido());	
		Assert.assertEquals(dto.getCaracteristicas(), resp.getCaracteristicas());	
		Assert.assertEquals(dto.getEncargadoId(), resp.getEncargadoId());	
	}
	
	@Test
	public void getWikiPaginationTest(){
		//Page 1
		WikiPageDTO dto1=wikiPersistence.getWikis(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        WikiPageDTO dto2=wikiPersistence.getWikis(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(WikiDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(WikiEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(WikiDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(WikiEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}