
import java.util.ArrayList.*; 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LandingGearTest.
 *
 * @author  Miguel
 * @version 3-11-21
 */
public class LandingGearTest
{
    private Wheel leftNoseWheel, rightNoseWheel, leftLeftWheel, leftRightWheel, rightLeftWheel, rightRightWheel;
    private WheelStrut nose, left, right;
    private LandingGear landingGear;
    @BeforeEach //(Ejecuta una configuración antes del test (se ejecuta el setUp))
    public void setUp(){
        leftNoseWheel = new Wheel();
        rightNoseWheel = new Wheel();
        
        leftLeftWheel = new Wheel();
        rightLeftWheel = new Wheel();
        
        leftRightWheel = new Wheel();
        rightRightWheel = new Wheel();
    }
    
    /**
     * Prueba del constructor sin parámetros
     * Crea un tren de aterrizaje con palanca down y tres puntales
     */
    @Test
    public void testLandingGearWithoutParam(){
        LandingGear landingGear1 = new LandingGear();
        
        assertEquals(landingGear1.LEVER_DOWN, landingGear1.getLever());
        
        assertNotNull(landingGear1.getLeft());
        assertTrue(landingGear1.getLeft().isDeployed());
        
        assertNotNull(landingGear1.getRight());
        assertTrue(landingGear1.getRight().isDeployed());
        
        assertNotNull(landingGear1.getNose());
        assertTrue(landingGear1.getNose().isDeployed());
        //comprueba que no sean null (que existan)
    }
    
    /**
     * Prueba del constructor con parámetros
     * Crea un tren de aterrizaje con una palanca down
     * y tres puntales como parámetros (izquierdo, derecho y en el morro)
     */
    @Test
    public void testLandingGearWithParam(){
        WheelStrut left = new WheelStrut();
        WheelStrut right = new WheelStrut();
        WheelStrut nose = new WheelStrut();
        LandingGear landingGear1 = new LandingGear(left,right,nose);
        
        assertNotNull(landingGear1.getLeft());
        assertNotNull(landingGear1.getRight());
        assertNotNull(landingGear1.getNose());
        assertEquals(LandingGear.LEVER_DOWN, landingGear1.getLever());
    }
    
    /**
     * Pruebas del constructor con 3 parámetros. Deja la palanca baja y puntales desplegados
     * 1-Todos los parámetros son correctos
     */
    @Test
    public void testLandingGearAllRight(){
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[0] = (rightOk);
        
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        LandingGear landingGear1 = new LandingGear(left,right,nose);
        landingGear1.moveLever(LandingGear.LEVER_DOWN);
        
        assertEquals(LandingGear.LEVER_DOWN, landingGear1.getLever());
        
        assertNotNull(landingGear1.getLeft());
        assertTrue(landingGear1.getLeft().isDeployed());
        
        assertNotNull(landingGear1.getRight());
        assertTrue(landingGear1.getRight().isDeployed());
        
        assertNotNull(landingGear1.getNose());
        assertTrue(landingGear1.getNose().isDeployed());
    }
    
    /**
     * Pruebas del constructor con tres parámetros
     * 2-Primero null y el resto bien
     */
    @Test
    public void LandingGearLeftNull(){
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[0] = (rightOk);
        
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        try{
            LandingGear landingGear = new LandingGear(null,right,nose);
            fail("Esperaba un salto de excepción");
        } catch (IllegalArgumentException e){
            assert(true);
        }
    }
    
    /**
     * Pruebas del constructor con tres parámetros
     * 3-Segundo null y el resto bien
     */
    @Test
    public void LandingGearRightNull(){
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[0] = (rightOk);
        
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        try{
            LandingGear landingGear = new LandingGear(left,null,nose);
            fail("Esperaba un salto de excepción");
        } catch (IllegalArgumentException e){
            assert(true);
        }
    }
    
    /**
     * Pruebas del constructor con tres parámetros
     * 4-Tercero null y el resto bien
     */
    @Test
    public void LandingGearNoseNull(){
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[1] = (rightOk);
        
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        try{
            LandingGear landingGear = new LandingGear(left,right,null);
            fail("Esperaba un salto de excepción");
        } catch (IllegalArgumentException e){
            assert(true);
        }
    }
    
    /**
     * Pruebas del constructor con tres parámetros
     * 5-Todos null
     */
    @Test
    public void LandingGearAllNull(){
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[1] = (rightOk);
        
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        try{
            LandingGear landingGear = new LandingGear(null,null,null);
            fail("Esperaba un salto de excepción");
        } catch (IllegalArgumentException e){
            assert(true);
        }
    }
    
    /*
     * Pruebas del método moveLever en distintas condiciones:
     * 1-Acción Down cuando el tren está desplegado -> todo queda como está
     * 2-Acción Down cuando el tren está replegado -> se despliega y baja la palanca
     * 3-Acción Up cuando el tren está desplegado -> se repliega y sube la palanca
     * 4-Acción Up cuando el tren está replegado -> todo queda como está
     */
    /**
     * Prueba 1 de moveLever
     * Acción Down cuando el tren está desplegado -> todo queda como está
     */
    @Test 
    public void testMoveLeverDownWhenDeployed(){
        //situación inicial
        LandingGear landingGear1 = new LandingGear();   //me lo crea desplegado
        
        //lamada al método con el caso 1
        landingGear1.moveLever(LandingGear.LEVER_DOWN);
                
        
        
        //comprobación de resultados
        assertEquals(LandingGear.LEVER_DOWN, landingGear1.getLever());
        assertEquals(WheelStrut.IS_DEPLOYED, landingGear1.getLeft().isDeployed());
        assertEquals(WheelStrut.IS_DEPLOYED, landingGear1.getRight().isDeployed());
        assertEquals(WheelStrut.IS_DEPLOYED, landingGear1.getNose().isDeployed());
    }
    
    /**
     * Prueba 2 de moveLever
     * Acción Down cuando el tren está replegado -> se despliega y baja la palanca
     */
    @Test 
    public void testMoveLeverDownWhenRetracted(){
        //situación inicial
        LandingGear landingGear1 = new LandingGear();   //me lo crea desplegado
        landingGear1.moveLever(LandingGear.LEVER_UP); //lo retrae
        
        //lamada al método con el caso 2
        landingGear1.moveLever(LandingGear.LEVER_DOWN);
                
        
        
        //comprobación de resultados
        assertEquals(LandingGear.LEVER_DOWN, landingGear1.getLever());
        assertEquals(WheelStrut.IS_DEPLOYED, landingGear1.getLeft().isDeployed());
        assertEquals(WheelStrut.IS_DEPLOYED, landingGear1.getRight().isDeployed());
        assertEquals(WheelStrut.IS_DEPLOYED, landingGear1.getNose().isDeployed());
    }
    
    /**
     * Prueba 3 de moveLever
     * Acción Up cuando el tren está desplegado -> se repliega y sube la palanca
     */
    @Test
    public void testMoveLeverUpWhenDeployed(){
        LandingGear landingGear1 = new LandingGear();   //me lo crea desplegado
        
        landingGear1.moveLever(LandingGear.LEVER_UP);   //me lo repliega y sube palanca
        
        assertEquals(LandingGear.LEVER_UP, landingGear1.getLever());
        assertEquals(!WheelStrut.IS_DEPLOYED, landingGear1.getLeft().isDeployed());
        assertEquals(!WheelStrut.IS_DEPLOYED, landingGear1.getRight().isDeployed());
        assertEquals(!WheelStrut.IS_DEPLOYED, landingGear1.getNose().isDeployed());
    }
    
    /**
     * Prueba 4 de moveLever
     * Acción Up cuando el tren está replegado -> todo queda como está
     */
    @Test
    public void testMoveLeverUpWhenRetracted(){
        LandingGear landingGear1 = new LandingGear();   //me lo crea desplegado
        
        landingGear1.moveLever(LandingGear.LEVER_UP);   //mueve y deja la palanca arriba
        
        assertEquals(LandingGear.LEVER_UP, landingGear1.getLever());
        assertEquals(!WheelStrut.IS_DEPLOYED, landingGear1.getLeft().isDeployed());
        assertEquals(!WheelStrut.IS_DEPLOYED, landingGear1.getRight().isDeployed());
        assertEquals(!WheelStrut.IS_DEPLOYED, landingGear1.getNose().isDeployed());
    }
    
    /*
     * Pruebas del método test en distintas condiciones:
     * 1-Que el test del puntal izquierdo de false y el resto true
     * 2-Que el test del puntal derecho de false y el resto true
     * 3-Que el test del puntal del morro de false y el resto true
     * 4-Que el test del puntal izquierdo y derecho de false, y el resto true
     * 5-Que el test del puntal derecho y el del morro de false, y el resto true
     * 6-Que el test del puntal izquierdo y el del morro de false, y el resto true
     * 7-Que los tests de los tres puntales de false
     * 8-Que los tests de los tres puntales de true (devuelve true)
     */
    /**
     * Prueba 1 de test
     * Que el test del puntal izquierdo de false y el resto true
     */
    @Test
    public void testTestWithOnlyFalseLeft(){
        //listas:
        Wheel[] wheelsLeft = new Wheel[2];
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[1] = (rightOk);
        
        //ruedas incorrectas para puntal izquierdo
        Wheel left1 = new Wheel(1000,100);
        Wheel right1 = new Wheel(1000,1000);
        
        //añadir ruedas incorrectas a la lista del puntal izquierdo
        wheelsLeft[0] = (left1);
        wheelsLeft[1] = (right1);
        
        //creación de los puntales a partir de las ruedas de las listas
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsLeft);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        
        //creación del tren de aterrizaje
        LandingGear landingGear1 = new LandingGear(left, right, nose);
        
        //llamada al método
        landingGear1.test();
        
        //comprobación de resultados
        assertEquals(left.test(),landingGear1.test());
        assertEquals(right.test(),landingGear1.test());
        assertEquals(nose.test(),landingGear1.test());
    }
    
    /**
     * Prueba 2 de test
     * Que el test del puntal derecho de false y el resto true
     */
    @Test
    public void testTestWithOnlyFalseRight(){
        //listas:
        Wheel[] wheelsRight = new Wheel[2];
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[1] = (rightOk);
        
        //ruedas incorrectas para puntal derecho
        Wheel left1 = new Wheel(1000,100);
        Wheel right1 = new Wheel(1000,1000);
        
        //añadir ruedas incorrectas a la lista del puntal derecho
        wheelsRight[0] = (left1);
        wheelsRight[1] = (right1);
        
        //creación de los puntales a partir de las ruedas de las listas
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsRight);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        
        //creación del tren de aterrizaje
        LandingGear landingGear1 = new LandingGear(left, right, nose);
        
        //llamada al método
        landingGear1.test();
        
        //comprobación de resultados
        assertEquals(left.test(),landingGear1.test());
        assertEquals(right.test(),landingGear1.test());
        assertEquals(nose.test(),landingGear1.test());
    }
    
    /**
     * Prueba 3 de test
     * Que el test del puntal del morro de false y el resto true
     */
    @Test
    public void testTestWithOnlyFalseNose(){        
        //listas:
        Wheel[] wheelsNose = new Wheel[2];
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[1] = (rightOk);
        
        //ruedas incorrectas para puntal frontal
        Wheel left1 = new Wheel(1000,100);
        Wheel right1 = new Wheel(1000,1000);
        
        //añadir ruedas incorrectas a la lista del puntal frontal
        wheelsNose[0] = (left1);
        wheelsNose[1] = (right1);
        
        //creación de los puntales a partir de las ruedas de las listas
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsNose);
        
        
        //creación del tren de aterrizaje
        LandingGear landingGear1 = new LandingGear(left, right, nose);
        
        //llamada al método
        landingGear1.test();
        
        //comprobación de resultados
        assertEquals(left.test(),landingGear1.test());
        assertEquals(right.test(),landingGear1.test());
        assertEquals(nose.test(),landingGear1.test());
    }
    
    /**
     * Prueba 4 de test
     * Que el test del puntal izquierdo y derecho de false, y el resto true
     */
    @Test
    public void testTestWithFalseLeftAndRight(){
        //listas:
        Wheel[] wheelsWrong = new Wheel[2];
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[1] = (rightOk);
        
        //ruedas incorrectas para los puntal incorrectos
        Wheel left1 = new Wheel(1000,100);
        Wheel right1 = new Wheel(1000,1000);
        
        //añadir ruedas incorrectas a la lista de los puntales incorrectos
        wheelsWrong[0] = (left1);
        wheelsWrong[1] = (right1);
        
        //creación de los puntales a partir de las ruedas de las listas
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsWrong);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsWrong);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        
        //creación del tren de aterrizaje
        LandingGear landingGear1 = new LandingGear(left, right, nose);
        
        //llamada al método
        landingGear1.test();
        
        //comprobación de resultados
        assertEquals(left.test(),landingGear1.test());
        assertEquals(right.test(),landingGear1.test());
        assertEquals(nose.test(),landingGear1.test());
    }
    
    /**
     * Prueba 5 de test
     * Que el test del puntal derecho y el del morro de false, y el resto true
     */
    @Test
    public void testTestWithFalseRightAndNose(){
        //listas:
        Wheel[] wheelsWrong = new Wheel[2];
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[1] = (rightOk);
        
        //ruedas incorrectas para los puntal incorrectos
        Wheel left1 = new Wheel(1000,100);
        Wheel right1 = new Wheel(1000,1000);
        
        //añadir ruedas incorrectas a la lista de los puntales incorrectos
        wheelsWrong[0] = (left1);
        wheelsWrong[1] = (right1);
        
        //creación de los puntales a partir de las ruedas de las listas
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsWrong);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        
        //creación del tren de aterrizaje
        LandingGear landingGear1 = new LandingGear(left, right, nose);
        
        //llamada al método
        landingGear1.test();
        
        //comprobación de resultados
        assertEquals(left.test(),landingGear1.test());
        assertEquals(right.test(),landingGear1.test());
        assertEquals(nose.test(),landingGear1.test());
    }
    
    /**
     * Prueba 6 de test
     * Que el test del puntal izquierdo y el del morro de false, y el resto true
     */
    @Test
    public void testTestWithFalseLeftAndNose(){
        //listas:
        Wheel[] wheelsWrong = new Wheel[2];
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para el puntal correcto
        Wheel leftOk = new Wheel(1000,1000);
        Wheel rightOk = new Wheel(1000,1000);
        
        //ruedas incorrectas para los puntal incorrectos
        Wheel left1 = new Wheel(1000,100);
        Wheel right1 = new Wheel(1000,1000);
        
        //añadir ruedas incorrectas a la lista de los puntales incorrectos
        wheelsWrong[0] = (left1);
        wheelsWrong[1] = (right1);
        
        //añadir ruedas correctas a su lista correspondiente
        wheels[0] = (leftOk);
        wheels[1] = (rightOk);
        
        //creación de los puntales a partir de las ruedas de las listas
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsWrong);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsWrong);
        
        
        //creación del tren de aterrizaje
        LandingGear landingGear1 = new LandingGear(left, right, nose);
        
        //llamada al método
        landingGear1.test();
        
        //comprobación de resultados
        assertEquals(left.test(),landingGear1.test());
        assertEquals(right.test(),landingGear1.test());
        assertEquals(nose.test(),landingGear1.test());
    }
    
    /**
     * Prueba 7 de test
     * Que los tests de los tres puntales de false
     */
    @Test
    public void testTestWithAllFalse(){
        //listas:
        Wheel[] wheelsWrong = new Wheel[2];
        
        //ruedas incorrectas para los puntal incorrectos
        Wheel left1 = new Wheel(1000,100);
        Wheel right1 = new Wheel(1000,1000);
        
        //añadir ruedas incorrectas a la lista de los puntales incorrectos
        wheelsWrong[0] = (left1);
        wheelsWrong[1] = (right1);
        
        //creación de los puntales a partir de las ruedas de las listas
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsWrong);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsWrong);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheelsWrong);
        
        
        //creación del tren de aterrizaje
        LandingGear landingGear1 = new LandingGear(left, right, nose);
        
        //llamada al método
        landingGear1.test();
        
        //comprobación de resultados
        assertEquals(left.test(),landingGear1.test());
        assertEquals(right.test(),landingGear1.test());
        assertEquals(nose.test(),landingGear1.test());
    }
    
    /**
     * Prueba 8 de test
     * Que los tests de los tres puntales de true (devuelve true)
     */
    @Test
    public void testTestWithAllTrue(){
        //listas:
        Wheel[] wheels = new Wheel[2];
        
        //ruedas correctas para los puntales
        Wheel left1 = new Wheel(1000,1000);
        Wheel right1 = new Wheel(1000,1000);
        
        //añadir ruedas a las listas de los puntales 
        wheels[0] = (left1);
        wheels[1] = (right1);
        
        //creación de los puntales a partir de las ruedas de las listas
        WheelStrut left = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut right = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        WheelStrut nose = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        
        //creación del tren de aterrizaje
        LandingGear landingGear1 = new LandingGear(left, right, nose);
        
        //llamada al método
        landingGear1.test();
        
        //comprobación de resultados
        assertEquals(left.test(),landingGear1.test());
        assertEquals(right.test(),landingGear1.test());
        assertEquals(nose.test(),landingGear1.test());       
    }
    
    
    /**
     * Pruebas del método toString 
     */
    @Test
    public void testToString(){
        LandingGear landingGear1 = new LandingGear();
        
        landingGear1.moveLever(LandingGear.LEVER_UP);
        
        assertEquals("Lever: UP    Status: OK    Nose: ON    Left: ON    Right: ON",
                        landingGear1.toString());
    }
}
