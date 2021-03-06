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

<<<<<<< .mine
*/

package co.edu.uniandes.csw.grupo.mysql.persistence;

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

import co.edu.uniandes.csw.grupo.encargado.master.persistence.entity.EncargadomySQLEntity;
import co.edu.uniandes.csw.grupo.mysql.logic.dto.MySQLPageDTO;
import co.edu.uniandes.csw.grupo.mysql.logic.dto.MySQLDTO;
import co.edu.uniandes.csw.grupo.mysql.persistence.api.IMySQLPersistence;
import co.edu.uniandes.csw.grupo.mysql.persistence.entity.MySQLEntity;
import co.edu.uniandes.csw.grupo.mysql.persistence.converter.MySQLConverter;
import static co.edu.uniandes.csw.grupo.util._TestUtil.*;

@RunWith(Arquillian.class)
public class MySQLPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(MySQLPersistence.class.getPackage())
				.addPackage(MySQLEntity.class.getPackage())
				.addPackage(IMySQLPersistence.class.getPackage())
                .addPackage(MySQLDTO.class.getPackage())
                .addPackage(MySQLConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IMySQLPersistence mySQLPersistence;

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
		em.createQuery("delete from MySQLEntity").executeUpdate();
	}

	private List<MySQLEntity> data=new ArrayList<MySQLEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			MySQLEntity entity=new MySQLEntity();
			entity.setDescripcionDestino(generateRandom(String.class));
			entity.setServidor(generateRandom(String.class));
			entity.setName(generateRandom(String.class));
			entity.setDescripcion(generateRandom(String.class));
			entity.setProposito(generateRandom(String.class));
			entity.setFechaCreacion(generateRandom(Date.class));
			entity.setDestruido(generateRandom(Boolean.class));
			entity.setCaracteristicas(generateRandom(String.class));
			entity.setPgwebId(generateRandom(Long.class));
			entity.setPaginawebId(generateRandom(Long.class));
			entity.setEncargadoId(generateRandom(Long.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createMySQLTest(){
		MySQLDTO dto=new MySQLDTO();
		dto.setDescripcionDestino(generateRandom(String.class));
		dto.setServidor(generateRandom(String.class));
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setProposito(generateRandom(String.class));
dto.setFechaCreacion(generateRandomDate());
		dto.setDestruido(generateRandom(Boolean.class));
		dto.setCaracteristicas(generateRandom(String.class));
		dto.setPgwebId(generateRandom(Long.class));
		dto.setPaginawebId(generateRandom(Long.class));
		dto.setEncargadoId(generateRandom(Long.class));
		
		MySQLDTO result=mySQLPersistence.createMySQL(dto);
		
		Assert.assertNotNull(result);
		
		MySQLEntity entity=em.find(MySQLEntity.class, result.getId());
		
		Assert.assertEquals(dto.getDescripcionDestino(), entity.getDescripcionDestino());
		Assert.assertEquals(dto.getServidor(), entity.getServidor());
		Assert.assertEquals(dto.getName(), entity.getName());
		Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());
		Assert.assertEquals(dto.getProposito(), entity.getProposito());
Assert.assertEquals(parseDate(dto.getFechaCreacion()), entity.getFechaCreacion());	
		Assert.assertEquals(dto.getDestruido(), entity.getDestruido());
		Assert.assertEquals(dto.getCaracteristicas(), entity.getCaracteristicas());
		Assert.assertEquals(dto.getPgwebId(), entity.getPgwebId());
		Assert.assertEquals(dto.getPaginawebId(), entity.getPaginawebId());
		Assert.assertEquals(dto.getEncargadoId(), entity.getEncargadoId());
	}
	
	@Test
	public void getMySQLsTest(){
		List<MySQLDTO> list=mySQLPersistence.getMySQLs();
		Assert.assertEquals(list.size(), data.size());
        for(MySQLDTO dto:list){
        	boolean found=false;
            for(MySQLEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getMySQLTest(){
		MySQLEntity entity=data.get(0);
		MySQLDTO dto=mySQLPersistence.getMySQL(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getDescripcionDestino(), dto.getDescripcionDestino());
		Assert.assertEquals(entity.getServidor(), dto.getServidor());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getDescripcion(), dto.getDescripcion());
		Assert.assertEquals(entity.getProposito(), dto.getProposito());
		Assert.assertEquals(entity.getDestruido(), dto.getDestruido());
		Assert.assertEquals(entity.getCaracteristicas(), dto.getCaracteristicas());
		Assert.assertEquals(entity.getPgwebId(), dto.getPgwebId());
		Assert.assertEquals(entity.getPaginawebId(), dto.getPaginawebId());
		Assert.assertEquals(entity.getEncargadoId(), dto.getEncargadoId());
        
	}
	
	@Test
	public void deleteMySQLTest(){
		MySQLEntity entity=data.get(0);
		mySQLPersistence.deleteMySQL(entity.getId());
        MySQLEntity deleted=em.find(MySQLEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateMySQLTest(){
		MySQLEntity entity=data.get(0);
		
		MySQLDTO dto=new MySQLDTO();
		dto.setId(entity.getId());
		dto.setDescripcionDestino(generateRandom(String.class));
		dto.setServidor(generateRandom(String.class));
		dto.setName(generateRandom(String.class));
		dto.setDescripcion(generateRandom(String.class));
		dto.setProposito(generateRandom(String.class));
dto.setFechaCreacion(generateRandomDate());
		dto.setDestruido(generateRandom(Boolean.class));
		dto.setCaracteristicas(generateRandom(String.class));
		dto.setPgwebId(generateRandom(Long.class));
		dto.setPaginawebId(generateRandom(Long.class));
		dto.setEncargadoId(generateRandom(Long.class));
		
		
		mySQLPersistence.updateMySQL(dto);
		
		
		MySQLEntity resp=em.find(MySQLEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getDescripcionDestino(), resp.getDescripcionDestino());	
		Assert.assertEquals(dto.getServidor(), resp.getServidor());	
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getDescripcion(), resp.getDescripcion());	
		Assert.assertEquals(dto.getProposito(), resp.getProposito());	
Assert.assertEquals(parseDate(dto.getFechaCreacion()), resp.getFechaCreacion());
		Assert.assertEquals(dto.getDestruido(), resp.getDestruido());	
		Assert.assertEquals(dto.getCaracteristicas(), resp.getCaracteristicas());	
		Assert.assertEquals(dto.getPgwebId(), resp.getPgwebId());	
		Assert.assertEquals(dto.getPaginawebId(), resp.getPaginawebId());	
		Assert.assertEquals(dto.getEncargadoId(), resp.getEncargadoId());	
	}
	
	@Test
	public void getMySQLPaginationTest(){
		//Page 1
		MySQLPageDTO dto1=mySQLPersistence.getMySQLs(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        MySQLPageDTO dto2=mySQLPersistence.getMySQLs(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(MySQLDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(MySQLEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(MySQLDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(MySQLEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
        @Test
        public void desactivarRecursoTest(){
            for(int i=0;i<data.size();i++)
            {
                MySQLEntity sql= data.get(i);
                if(sql.getDestruido())
                {
                    Long idSql= sql.getId();
                    mySQLPersistence.desactivarRecurso(idSql);
                    Assert.assertFalse(sql.getDestruido());
                }
            }
        }
        @Test
        public void agregarEncargadoMySQLTest1(){
            MySQLDTO dto=new MySQLDTO();
            dto.setDescripcionDestino(generateRandom(String.class));
            dto.setServidor(generateRandom(String.class));
            dto.setName(generateRandom(String.class));
            dto.setDescripcion(generateRandom(String.class));
            dto.setProposito(generateRandom(String.class));
            dto.setFechaCreacion(generateRandomDate());
            dto.setDestruido(generateRandom(Boolean.class));
            dto.setCaracteristicas(generateRandom(String.class));
            dto.setPgwebId(generateRandom(Long.class));
            dto.setPaginawebId(generateRandom(Long.class));
            dto.setEncargadoId(generateRandom(Long.class));
		
            MySQLDTO result=mySQLPersistence.createMySQL(dto);
		
            Assert.assertNotNull(result);
		
            MySQLEntity entity=em.find(MySQLEntity.class, result.getId());
		
            Assert.assertEquals(dto.getDescripcionDestino(), entity.getDescripcionDestino());
            Assert.assertEquals(dto.getServidor(), entity.getServidor());
            Assert.assertEquals(dto.getName(), entity.getName());
            Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals(dto.getProposito(), entity.getProposito());
            Assert.assertEquals(parseDate(dto.getFechaCreacion()), entity.getFechaCreacion());	
            Assert.assertEquals(dto.getDestruido(), entity.getDestruido());
            Assert.assertEquals(dto.getCaracteristicas(), entity.getCaracteristicas());
            Assert.assertEquals(dto.getPgwebId(), entity.getPgwebId());
            Assert.assertEquals(dto.getPaginawebId(), entity.getPaginawebId());
            Assert.assertEquals(dto.getEncargadoId(), entity.getEncargadoId());
            
            //Probar que exista el entity Encargadowiki
            EncargadomySQLEntity encargado1 = em.find(EncargadomySQLEntity.class,dto.getEncargadoId());
            EncargadomySQLEntity encargado2 = em.find(EncargadomySQLEntity.class,dto.getId());
            
            Assert.assertNotNull(encargado1);
            Assert.assertNotNull(encargado2);
        }
        
        @Test
        public void agregarEncargadoMySQLTest2(){
            
            MySQLDTO dto=new MySQLDTO();
            dto.setDescripcionDestino(generateRandom(String.class));
            dto.setServidor(generateRandom(String.class));
            dto.setName(generateRandom(String.class));
            dto.setDescripcion(generateRandom(String.class));
            dto.setProposito(generateRandom(String.class));
            dto.setFechaCreacion(generateRandomDate());
            dto.setDestruido(generateRandom(Boolean.class));
            dto.setCaracteristicas(generateRandom(String.class));
            dto.setPgwebId(generateRandom(Long.class));
            dto.setPaginawebId(generateRandom(Long.class));
            //dto.setEncargadoId(generateRandom(Long.class));
		
            MySQLDTO result=mySQLPersistence.createMySQL(dto);
		
            Assert.assertNotNull(result);
		
            MySQLEntity entity=em.find(MySQLEntity.class, result.getId());
		
            Assert.assertEquals(dto.getDescripcionDestino(), entity.getDescripcionDestino());
            Assert.assertEquals(dto.getServidor(), entity.getServidor());
            Assert.assertEquals(dto.getName(), entity.getName());
            Assert.assertEquals(dto.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals(dto.getProposito(), entity.getProposito());
            Assert.assertEquals(parseDate(dto.getFechaCreacion()), entity.getFechaCreacion());	
            Assert.assertEquals(dto.getDestruido(), entity.getDestruido());
            Assert.assertEquals(dto.getCaracteristicas(), entity.getCaracteristicas());
            Assert.assertEquals(dto.getPgwebId(), entity.getPgwebId());
            Assert.assertEquals(dto.getPaginawebId(), entity.getPaginawebId());
            //Assert.assertEquals(dto.getEncargadoId(), entity.getEncargadoId());
            
            //Probar que NO exista el entity Encargadowiki
            EncargadomySQLEntity encargado1 = em.find(EncargadomySQLEntity.class,dto.getEncargadoId());
            EncargadomySQLEntity encargado2 = em.find(EncargadomySQLEntity.class,dto.getId());
            
            Assert.assertNull(encargado1);
            Assert.assertNull(encargado2);
        }
        
        @Test
        public void busquedasTest()
        {
            // Primero se prueba que se pueda buscar por activos e inactivos
                
            MySQLPageDTO wpdto = mySQLPersistence.getMySQLsByParameters("", "", "", "", "", "", "", "", "", "", "", "1");
            Assert.assertNotNull(wpdto);

            List<MySQLDTO> lista = wpdto.getRecords();
            int contActivos = 0;
            List<Integer> posicionesActivos = new ArrayList<Integer>();
            List<Integer> posicionesInactivos = new ArrayList<Integer>();
            
            for(int i = 0; i < data.size(); i++)
            {
                if(!data.get(i).getDestruido())
                {
                    posicionesActivos.add(i);
                    contActivos++;
                }
                else
                    posicionesInactivos.add(i);
            }
            
            Assert.assertEquals(lista.size(), contActivos);
            
            for(int i = 0; i < contActivos; i++)
            {
                MySQLEntity entityP=data.get(posicionesActivos.get(i));
                MySQLDTO entity = MySQLConverter.entity2PersistenceDTO(entityP);
                MySQLDTO primer = lista.get(i);            

                Assert.assertEquals(entity.getDescripcionDestino(),primer.getDescripcionDestino());
                Assert.assertEquals(entity.getServidor(), primer.getServidor());
                Assert.assertEquals(entity.getName(), primer.getName());
                Assert.assertEquals(entity.getCaracteristicas(),primer.getCaracteristicas());
                Assert.assertEquals(entity.getDescripcion(),primer.getDescripcion());
                Assert.assertEquals(entity.getProposito(),primer.getProposito());
                Assert.assertEquals(entity.getPgwebId(),primer.getPgwebId());                
                Assert.assertEquals(entity.getPaginawebId(), primer.getPaginawebId());
                Assert.assertEquals(entity.getDestruido(),primer.getDestruido());
                Assert.assertEquals(entity.getEncargadoId(),primer.getEncargadoId());
                Assert.assertEquals(entity.getFechaCreacion(),primer.getFechaCreacion());
            }
            
            String fecha1 = (data.get(0).getFechaCreacion().getDate()) + "-" + (data.get(0).getFechaCreacion().getMonth()+1) + "-" + (data.get(0).getFechaCreacion().getYear()+1900-1);
            String fecha2 = (data.get(0).getFechaCreacion().getDate()) + "-" + (data.get(0).getFechaCreacion().getMonth()+1) + "-" + (data.get(0).getFechaCreacion().getYear()+1900+1);
            
            wpdto = mySQLPersistence.getMySQLsByParameters(data.get(0).getDescripcionDestino(), data.get(0).getServidor(), data.get(0).getName(), data.get(0).getDescripcion(), data.get(0).getProposito(), data.get(0).getCaracteristicas(), "" + data.get(0).getPgwebId(), "" + data.get(0).getPaginawebId(), "" + data.get(0).getEncargadoId(), fecha1, fecha2, (data.get(0).getDestruido())?"0":"1");

            lista = wpdto.getRecords();
            
            Assert.assertEquals(lista.size(), 1);
            
            Assert.assertEquals(lista.get(0).getName(), data.get(0).getName());
            
        }
}
