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
public class WikiTest {
    // Es la instancia inicial del web driver que controlar� el navegador firefox
        private static WebDriver driver;
        // url en el cual se aloja la p�gina web (en este caso localhost:8080)
        private static String baseUrl;
// variable que indica si varios alert consecutivos (alert javascript) se tomar�n
        private static boolean acceptNextAlert = true;
        private static StringBuffer verificationErrors = new StringBuffer();
       /*La anotaci�n �@BeforeClass� indica lo que se debe ejecutar ANTES de correr el archivo de pruebas. Este m�todo instancia un nuevo driver firefox (causando la apertura de una ventana f�sica de firefox).*/
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
        driver.get(baseUrl + "/RigitalApp.web/wiki.html");
        }
         @Test
    public void testCrearWiki() throws Exception {
 
        /**
         * Comando que realiza click sobre el boton "create" del toolbar. La
         * funci�n 'find' encuentra el control y posteriormente hace clic en el
         * mismo. La forma en la que se busca el control es utilizando
         * expresiones xPath ya que los id de los mismos nunca son iguales (se
         * generan con junto con el valor de componentId que var�a).
         */
        driver.findElement(By.xpath("//button[contains(@id,'button-create')]")).click();
 
        /**
         * Comando que duerme el Thread del test por 2 segundos para dejar que
         * el efecto 'slide down' de backbone abra el formulario de createSport.
         */
        Thread.sleep(2000);
 
        /**
         * Comando que busca el elemento 'name' en el html actual.
         * Posteriormente limpia su contenido (comando clean).
         */
        driver.findElement(By.id("name")).clear();
        
        /**
         * Comando que simula la escritura de un valor en el elemento(sendKeys)
         * con el String de par�metro sobre // el elemento encontrado.
         */
        driver.findElement(By.id("name")).sendKeys("Wiki");
        
                Thread.sleep(2000);

 
        //Comandos para llenar el campo minAge
        driver.findElement(By.id("descripcion")).clear();
        driver.findElement(By.id("descripcion")).sendKeys("Se est� haciendo una prueba sobre wiki para ver si se crea bien");

                Thread.sleep(2000);

        /**
         * Comandos para llenar el campo maxAge
         */
        driver.findElement(By.id("fechaCreacion")).clear();
        driver.findElement(By.id("fechaCreacion")).sendKeys("22/11/2014");
        
        Thread.sleep(2000);
        
        driver.findElement(By.className("onoffswitch-label")).click();
        
        Thread.sleep(2000);
        
        driver.findElement(By.id("servidor_host")).clear();
        driver.findElement(By.id("servidor_host")).sendKeys("servidor");
        
        Thread.sleep(2000);
        
        driver.findElement(By.id("rutaServidor")).clear();
        driver.findElement(By.id("rutaServidor")).sendKeys("ruta del servidor");
        
        Thread.sleep(2000);
 
        /**
         * Comando que encuentra y hace clic sobre el boton "Save" del toolbar
         * (una vez mas encontrado por una expresi�n Xpath)
         */
        driver.findElement(By.xpath("//button[contains(@id,'button-save')]")).click();
 
        /**
         * Comando que duerme el thread para esperar el efecto de slide down que
         * abre la lista
         */
        Thread.sleep(2000);
        /**
         * Comando que obtiene el div azul de creaci�n exitosa. Si se obtiene,
         * la prueba va bien, si no, saldr� un error y la prueba quedar� como
         * f�llida.
         */
        WebElement dialog = driver.findElement(By.xpath("//div[contains(@style,'display: block;')]"));
        /**
         * Comando que obtiene la tabla con el elemento que se cre�
         * anteriormente.
         */
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
        boolean sucess = false;
        System.out.println(table);
        /**
         * Se itera sobre los elementos de la tabla para ver si el nuevo
         * elemento creado est� en la lista
         */
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            System.out.println("nombre: "+ elems.get(1).getText() + " fecha: " + elems.get(2).getText());
            if (elems.get(1).getText().equals("Wiki") && elems.get(2).getText().equals("22/11/2014")) {
                /**
                 * si se encuentra la fila, la variable 'fail' pasa a true,
                 * indicando que el elemento creado esta en la lista.
                 */
                sucess = true;                
            }
 
        }
        System.out.println(sucess);
        /**
         * la prueba es exitosa si se encontr� el dialogo de creaci�n exitosa y
         * el nuevo elemento est� en la lista.
         */
        assertTrue(dialog != null && sucess);
    }
    
    @Test
    public void testDesactivarWiki() throws Exception {
 
        Thread.sleep(2000);
        
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
        
        for(WebElement webElement: table){
            List<WebElement> elems= webElement.findElements(By.xpath("td"));
            if(elems.get(3).getText().equals("true")){
                elems.get(0).findElement(By.tagName("input")).click();                
            }                    
        }                    
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@id,'button-desactivar')]")).click();
                 
        Thread.sleep(2000);
        List<WebElement> table2 = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
        boolean def = true;
        for(WebElement webElement: table2){
            List<WebElement> elems= webElement.findElements(By.xpath("td"));
            String val= elems.get(3).getText();
            boolean temp= !val.equals("true");
            System.out.println(temp);
            def= def && temp;
        }
        Thread.sleep(4000);      
        assertTrue(def);
    }
    
     @Test
    public void testBuscarWiki() throws Exception {
 
        
        driver.findElement(By.xpath("//button[contains(@id,'button-search')]")).click();
 
        Thread.sleep(2000);
 
        driver.findElement(By.id("btn_name")).click();
        
        Thread.sleep(2000);
        
        driver.findElement(By.id("name")).clear();
        
        driver.findElement(By.id("name")).sendKeys("Wiki");
        
        Thread.sleep(2000);
 
        
        driver.findElement(By.xpath("//button[contains(@id,'button-exec-search')]")).click();
 
        
        Thread.sleep(2000);
        
        
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
        boolean success = true;
        
        /**
         * Se itera sobre los elementos de la tabla para ver si el nuevo
         * elemento creado est� en la lista
         */
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            System.out.println("nombre: "+ elems.get(1).getText() + " fecha: " + elems.get(2).getText());              
            success = success && elems.get(1).getText().equals("Wiki");                             
        }
        /**
         * la prueba es exitosa si se encontr� el dialogo de creaci�n exitosa y
         * el nuevo elemento est� en la lista.
         */
        assertTrue(success);
    }        
        
        // La anotaci�n �@AfterClass� indica lo que se debe ejecutar DESPU�S de ejecutar
        // el archivo de pruebas. Este m�todo cierra la ventana de firefox
        // abierta por @BeforeClass que se utiliz� para la prueba.
        @AfterClass
        public static void tearDown() throws Exception {                       
            
            // Se selecciona la tabla para eliminar todos sus elementos
           List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]/tbody/tr"));
                
        /**
         * Se itera sobre los elementos de la tabla para eliminarlos
         */
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
            elems.get(5).findElement(By.linkText("Eliminar")).click();             
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
