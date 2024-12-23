
import java.util.ArrayList.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class WheelStrutTest.
 *
 * @author  Miguel
 * @version 27-10-21
 */
public class WheelStrutTest
{
    /**
     * Pruebas para el constructor sin parámetros
     * Creará un puntal desplegado y una lista de tres ruedas con presión máxima y
     * actual igual a BOEING_737_PRESSURE
     */
    @Test
    public void testWheelStrutWithoutParams()
    {
        WheelStrut wheelStrut1 = new WheelStrut();
        
        assertTrue(wheelStrut1.isDeployed());
        
        assertNotNull(wheelStrut1.getWheels()); //compruebo que existe la lista de ruedas
        assertEquals(WheelStrut.DEFAULT_NUMBER_OF_WHEELS, wheelStrut1.getWheels().length); //compruebo que el número de ruedas es el por defecto
        
        assertEquals(WheelStrut.BOEING_737_PRESSURE, wheelStrut1.getWheels()[0].getPressure());
        assertEquals(WheelStrut.BOEING_737_PRESSURE, wheelStrut1.getWheels()[0].getMaxPressure());
        //Si accedo directamente a las pruebas:
        for (int i = 0; i < wheelStrut1.getWheels().length; i++) //mejor sería wheelStrut1.length, pero habría que hacer el método size para que devuelva el número de elementos en la lista
        {
            assertEquals(WheelStrut.BOEING_737_PRESSURE, wheelStrut1.getWheel(i).getPressure());
            assertEquals(WheelStrut.BOEING_737_PRESSURE, wheelStrut1.getWheel(i).getMaxPressure());
        }        
    }
    
    /**
     * Pruebas del constructor con el parámero numberOfWheels
     */
    @Test
    public void testWheelStrutWithParams()
    {
        WheelStrut wh1 = new WheelStrut(2);
        
        assertTrue(wh1.isDeployed());
        
        assertNotNull(wh1.getWheels()); //comprobación de que la lista existe
        assertEquals(2, wh1.getWheels().length);    //comprobación de que el número de elementos coincida con ell tamaño de la lista creada
        
        //comprobación de los valores de los elementos de la lista
        for (int i = 0; i < wh1.getWheels().length; i++)
        {
            assertEquals(WheelStrut.BOEING_737_PRESSURE, wh1.getWheel(i).getPressure());
            assertEquals(WheelStrut.BOEING_737_PRESSURE, wh1.getWheel(i).getMaxPressure());
        }
    }
    
    /*
     * Pruebas del método test
     * Casos:
     * 1-Ambas ruedas con presión correcta -> true
     * 2-Ambas ruedas con presión incorrecta -> false
     * 3-Rueda izquierda con presión incorrecta y derecha correcta -> false
     * 4-Rueda derecha con presión incorrecta e izquierda correcta -> false
    */
    /**
     * Pruebas del método test
     * Caso 3: Rueda izquierda con presión incorrecta y derecha correcta -> false
     */
    @Test
    public void testTestLeftWrongRightOK(){     
        //Situación inicial: puntal con rueda izquierda mal y derecha bien
        Wheel leftWrong = new Wheel(5000,1000);
        Wheel rightOk = new Wheel(5000,5000);
        
        Wheel[] wheels = new Wheel[2];
        
        wheels[0] = (leftWrong);
        wheels[1] = (rightOk);
        
        WheelStrut wheelStrut1 = new WheelStrut(true, wheels);
        
        //Llamar al método a probar:  ----- assertTrue(wheelStrut.test())
        boolean result = wheelStrut1.test();
        
        //Comprobar resultados:
        assertEquals(true, result); //Es lo mismo assertTrue(result);
    }
    
    /**
     * Pruebas del método test
     * Caso 1: Ambas ruedas con presión correcta -> true
     */
    @Test
    public void testTestAllOK(){
        Wheel left = new Wheel(5000,5000);
        Wheel right = new Wheel(5000,5000);
        
        Wheel[] wheels = new Wheel[2];
        
        wheels[0] = (left);
        wheels[1] = (right);
        
        WheelStrut wheelStrut1 = new WheelStrut(true, wheels);
        
        assertEquals(true, wheelStrut1.test());
    }
    
    /**
     * Pruebas del método test
     * Caso 2: Ambas ruedas con presión incorrecta -> false
     */
    @Test
    public void testTestAllWrong(){
        Wheel leftWrong = new Wheel(5000,1000);
        Wheel rightWrong = new Wheel(5000,1000);
        
        Wheel[] wheels = new Wheel[2];
        
        wheels[0] = (leftWrong);
        wheels[1] = (rightWrong);
        
        WheelStrut wheelStrut1 = new WheelStrut(true, wheels);
        
        assertEquals(true,wheelStrut1.test());
    }
    
    /**
     * Pruebas del método test
     * Caso 4: Rueda derecha con presión incorrecta e izquierda correcta -> false
     */
    @Test
    public void testTestRightWrongLeftOK(){
        Wheel leftOk = new Wheel(5000,5000);
        Wheel rightWrong = new Wheel(5000,1000);
        
        Wheel[] wheels = new Wheel[2];
        
        wheels[0] = (leftOk);
        wheels[1] = (rightWrong);
        
        WheelStrut wheelStrut1 = new WheelStrut(true, wheels);
        
        assertEquals(true,wheelStrut1.test());
    }
    
    /*
     * Pruebas de deploy
     * 1- Estando replegado, lo despliega
     * 2- Estando desplegado, lo deja como está
     */
    /**
     * Prueba 1 del método deploy
     */
    @Test
    public void testDeployWhenRetracted(){
        //situación inicial
        Wheel right = new Wheel();
        Wheel left = new Wheel();
        
        Wheel[] wheels = new Wheel[2];
        
        wheels[0] = (left);
        wheels[1] = (right);
        
        WheelStrut wheelStrut1 = new WheelStrut(!WheelStrut.IS_DEPLOYED, wheels);
        
        //llamada al método
        wheelStrut1.deploy();
        
        //comprobación de resultado
        assertEquals(WheelStrut.IS_DEPLOYED, wheelStrut1.isDeployed());
    }
    
    /**
     * Prueba 2 del método deploy
     */
    @Test
    public void testDeployWhenDeployed(){
        Wheel right = new Wheel();
        Wheel left = new Wheel();
        
        Wheel[] wheels = new Wheel[2];
        
        wheels[0] = (left);
        wheels[1] = (right);
        
        WheelStrut wheelStrut1 = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        wheelStrut1.deploy();
        assertEquals(WheelStrut.IS_DEPLOYED, wheelStrut1.isDeployed());
    }
    
    /*
     * Pruebas retracted
     * 1- Estando replegado, lo deja como está
     * 2- Estando desplegado, lo repliega
     */
    /**
     * Prueba 1 del método retracted
     */
    @Test
    public void testRetractedWhenRetracted(){
        Wheel right = new Wheel();
        Wheel left = new Wheel();
        
        Wheel[] wheels = new Wheel[2];
        
        wheels[0] = (left);
        wheels[1] = (right);
        
        WheelStrut wheelStrut1 = new WheelStrut(!WheelStrut.IS_DEPLOYED, wheels);
        
        wheelStrut1.retract();
        assertEquals(!WheelStrut.IS_DEPLOYED,wheelStrut1.isDeployed());
    }
    
    /**
     * Prueba 2 del método retracted
     */
    @Test
    public void testRetractedWhenDeployed(){
        Wheel right = new Wheel();
        Wheel left = new Wheel();
        
        Wheel[] wheels = new Wheel[2];
        
        wheels[0] = (left);
        wheels[1] = (right);
        
        WheelStrut wheelStrut1 = new WheelStrut(WheelStrut.IS_DEPLOYED, wheels);
        
        wheelStrut1.retract();
        
        assertEquals(!WheelStrut.IS_DEPLOYED,wheelStrut1.isDeployed());
    }   
}
