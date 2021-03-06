/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.RigitalApp.test;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author estudiante
 */
public class WorkstationTest {
    
    private static WebDriver driver;
    
    private static String baseUrl;

    private static boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();

        baseUrl = "http://localhost:8080";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);         
    }
    @Before
    public void setUpUrl() {
        driver.get(baseUrl + "/RigitalApp.web/workstation.html");
    }
     @Test
    public void testCrearMaquina() throws Exception {
 
        driver.findElement(By.xpath("//button[contains(@id,'button-create')]")).click();
         
        Thread.sleep(2000);
 
        driver.findElement(By.id("name")).clear();
               
        driver.findElement(By.id("name")).sendKeys("BI-DB2IM");
        
        Thread.sleep(2000);
        
        driver.findElement(By.id("duenio")).clear();
        driver.findElement(By.id("duenio")).sendKeys("Diego Roa");

        Thread.sleep(2000);
                
        driver.findElement(By.id("destino")).clear();
        driver.findElement(By.id("destino")).sendKeys("Waira 1 y Waira 2");
        
        Thread.sleep(2000);         
        
        driver.findElement(By.id("sistemaOperativo")).clear();
        driver.findElement(By.id("sistemaOperativo")).sendKeys("Windows Server 2008 R2");
 
        driver.findElement(By.xpath("//button[contains(@id,'button-save')]")).click();
 

        Thread.sleep(2000);
        
        WebElement dialog = driver.findElement(By.xpath("//div[contains(@style,'display: block;')]"));
        
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
        boolean sucess = false;
        System.out.println(table);
        
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            
            if (elems.get(1).getText().equals("BI-DB2IM") && elems.get(2).getText().equals("Diego Roa")) {
                
                sucess = true;                
            }
 
        }
        System.out.println(sucess);
        
        assertTrue(dialog != null && sucess);
    }
        
     @Test
    public void testBuscarMaquina() throws Exception {
 
        
        driver.findElement(By.xpath("//button[contains(@id,'button-search')]")).click();
 
        Thread.sleep(2000);
 
        driver.findElement(By.id("btn_name")).click();
        
        Thread.sleep(2000);
        
        driver.findElement(By.id("name")).clear();
        
        driver.findElement(By.id("name")).sendKeys("DB2IM");
        
        Thread.sleep(2000);
 
        
        driver.findElement(By.xpath("//button[contains(@id,'button-exec-search')]")).click();
 
        
        Thread.sleep(2000);
        
        
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
        boolean success = true;
        
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));             
            success = success && elems.get(1).getText().contains("DB2IM");                             
        }
        
        assertTrue(success);
    }        
        
        // La anotaci�n �@AfterClass� indica lo que se debe ejecutar DESPU�S de ejecutar
        // el archivo de pruebas. Este m�todo cierra la ventana de firefox
        // abierta por @BeforeClass que se utiliz� para la prueba.
        @AfterClass
        public static void tearDown() throws Exception {                       
            
            driver.get(baseUrl+"/RigitalApp.web/workstation.html");
            // Se selecciona la tabla para eliminar todos sus elementos
           List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
                
        /**
         * Se itera sobre los elementos de la tabla para eliminarlos
         */
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            elems.get(3).findElement(By.linkText("Eliminar")).click();             
            assertTrue(true);
        }
        
            // Se cierra el navegador.
            driver.quit();
           // Se verifica que se haya cerrado efectivamente el navegador.
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }
}
