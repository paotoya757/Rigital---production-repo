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
public class EstudianteTest {
    
    private static WebDriver driver;
        // url en el cual se aloja la página web (en este caso localhost:8080)
        private static String baseUrl;
// variable que indica si varios alert consecutivos (alert javascript) se tomarán
        private static boolean acceptNextAlert = true;
        private static StringBuffer verificationErrors = new StringBuffer();
       /*La anotación ‘@BeforeClass’ indica lo que se debe ejecutar ANTES de correr el archivo de pruebas. Este método instancia un nuevo driver firefox (causando la apertura de una ventana física de firefox).*/
        @BeforeClass
        public static void setUp() throws Exception {
            driver = new FirefoxDriver();
        // se define el url base del proyecto web
            baseUrl = "http://localhost:8080";
        /* Indica cuanto se espera para la respuesta de cualquier comando realizado hacia el navegador*/
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);         
        }
        @Before
        public void setUpUrl() {
        driver.get(baseUrl + "/RigitalApp.web/estudiante.html");
        }
         @Test
    public void testCrearEstudiante() throws Exception {
 
        driver.findElement(By.xpath("//button[contains(@id,'button-create')]")).click();
         
        Thread.sleep(2000);
 
        driver.findElement(By.id("name")).clear();

        driver.findElement(By.id("name")).sendKeys("Estudiante1");
        
        Thread.sleep(2000);
 
        driver.findElement(By.id("login")).clear();
        driver.findElement(By.id("login")).sendKeys("Login1");

        Thread.sleep(2000);
        
        driver.findElement(By.id("tipoAcceso")).clear();
        driver.findElement(By.id("tipoAcceso")).sendKeys("Tipo de acceso normal");

        Thread.sleep(2000);
         
        driver.findElement(By.xpath("//button[contains(@id,'button-save')]")).click();
 
        /**
         * Comando que duerme el thread para esperar el efecto de slide down que
         * abre la lista
         */
        Thread.sleep(2000);
        /**
         * Comando que obtiene el div azul de creación exitosa. Si se obtiene,
         * la prueba va bien, si no, saldrá un error y la prueba quedará como
         * fállida.
         */
        WebElement dialog = driver.findElement(By.xpath("//div[contains(@style,'display: block;')]"));
        /**
         * Comando que obtiene la tabla con el elemento que se creó
         * anteriormente.
         */
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
        boolean sucess = false;
        /**
         * Se itera sobre los elementos de la tabla para ver si el nuevo
         * elemento creado está en la lista
         */
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            System.out.println("nombre: "+ elems.get(1).getText() + " fecha: " + elems.get(2).getText());
            if (elems.get(0).getText().equals("Estudiante1") && elems.get(1).getText().equals("Login1")) {
                /**
                 * si se encuentra la fila, la variable 'fail' pasa a true,
                 * indicando que el elemento creado esta en la lista.
                 */
                sucess = true;                
            }
 
        }
        /**
         * la prueba es exitosa si se encontró el dialogo de creación exitosa y
         * el nuevo elemento está en la lista.
         */
        assertTrue(dialog != null && sucess);
    }
    
    @AfterClass
        public static void tearDown() throws Exception {                       
            
            // Se selecciona la tabla para eliminar todos sus elementos
           List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
                
        /**
         * Se itera sobre los elementos de la tabla para eliminarlos
         */
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            elems.get(2).findElement(By.linkText("Eliminar")).click();             
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
