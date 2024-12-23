

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class WheelTest.
 *
 * @author  Miguel
 * @version 20-10-21 
 */
public class WheelTest
{
    /**
     * Pruebas del constructor sin parámetros
     * Asigna presión standard y presión máxima standard
     */
    @Test
    public void testWheelWithoutParams(){
        Wheel wheel1 = new Wheel();
        assertEquals(Wheel.STANDARD_PRESSURE,wheel1.getPressure());
        assertEquals(Wheel.STANDARD_MAX_PRESSURE,wheel1.getMaxPressure());
    }
    
    /**
     * Pruebas del constructor con parámetros
     */
    @Test
    public void testWheelWithParams(){
        Wheel wheel1 = new Wheel(900,800);
        assertEquals(900,wheel1.getMaxPressure());
        assertEquals(800,wheel1.getPressure());
    }
    
    /**
     * Pruebas del método test
     */
    @Test
    public void testTestMethodWithLowerPressure(){
        Wheel wheel1 = new Wheel(900,800);
        assertEquals(true,wheel1.test());
    }
}
