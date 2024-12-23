
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PlaneTest.
 *
 * @author  Miguel
 * @version 6-10-21
 */
public class PlaneTest
{
    /**
     * Pruebas del constructor por defecto
     * 
     * Piloto = null
     * Identificador 'A'
     * Combustible 0
     */
    @Test
    public void testPlaneWithoutParams()
    {
        Plane plane1 = new Plane();
        
        assertEquals(Plane.DEFAULT_IDENTIFIER, plane1.getIdentifier());
        assertEquals(Plane.DEFAULT_FUEL, plane1.getFuel());
        assertEquals(Plane.DEFAULT_PILOT, plane1.getPilot());
        
        assertEquals(Plane.MIN_X_POS, plane1.getXPos());
        assertEquals(Plane.MIN_Y_POS, plane1.getYPos());
        
        assertEquals(Plane.MIN_X_SPEED, plane1.getXSpeed());
        assertEquals(Plane.MIN_Y_SPEED, plane1.getYSpeed());
    }
    
    /*
     * Pruebas del constructor con parámetro piloto
     * 1- Piloto cualquiera
     * 2- null en lugar de un piloto
     */
    /**
     * 1-Piloto cualquiera
     */
    @Test
    public void testPlaneWithPilot()
    {
        Person pilot = new Person();
        Plane plane1 = new Plane(pilot); 
        
        assertEquals(pilot, plane1.getPilot());
        assertEquals(Plane.DEFAULT_IDENTIFIER, plane1.getIdentifier());
        assertEquals(Plane.DEFAULT_FUEL,plane1.getFuel());
    }

    /**
     * 2-Recibo null
     */
    @Test
    public void testPlaneWithoutPilot()
    {
        Plane plane1 = new Plane(null);
        
        assertEquals(null, plane1.getPilot());
        assertEquals(Plane.DEFAULT_IDENTIFIER, plane1.getIdentifier());
        assertEquals(Plane.DEFAULT_FUEL,plane1.getFuel());
    }
    
    /**
     * Prueba del constructor Plane con pilot1 como único parámetro
     */
    @Test
    public void testConstructorWithOnlyPilot()
    {
        Person pilot1 = new Person();
        Plane plane = new Plane(pilot1);
        
        assertEquals(pilot1,plane.getPilot());
    }
    
    /**
     * Prueba del constructor Plane con Identifier como único parámetro
     */
    @Test
    public void testConstructorWithOnlyIdentifier()
    {
        Plane plane = new Plane('Z');
        
        assertEquals('Z',plane.getIdentifier());
    }
    
    /**
     * Prueba del constructor Plane con Fuel como único parámetro
     */
    @Test
    public void testConstructorWithOnlyFuel()
    {
        Plane plane = new Plane(50);
        
        assertEquals(50,plane.getFuel());
    }
    
    /*
     * Pruebas del método accelerate con distintas condiciones
     * 1-Que la velocidad tanto de x como de y esté dentro de sus intervalos
     * 2-Que al menos una de las dos velocidades esté por encima del intervalo (velocidad x)
     * 3-Que al menos una de las dos velocidades esté por encima del intervalo (velocidad y)
     * 4-Que al menos una de las dos velocidades esté por debajo del intervalo (velocidad x)
     * 5-Que al menos una de las dos velocidades esté por debajo del intervalo (velocidad y)
     * 6-Que ambas velocidades estén fuera de sus intervalos
     */
    /**
     * Prueba 1 del método accelerate
     * Que la velocidad tanto de x como de y esté dentro de sus intervalos
     */
    @Test
    public void testAccelerateWithCorrectSpeeds()
    {
        Plane plane1 = new Plane(1,1);
        
        plane1.accelerate(1,1);
        
        assertEquals(1,plane1.getXSpeed());
        assertEquals(1,plane1.getYSpeed());
    }
    
    /**
     * Prueba 2 del método accelerate
     * Que al menos una de las dos velocidades esté por encima del intervalo (velocidad x)
     */
    @Test
    public void testAccelerateWithHigherXSpeed()
    {
        Plane plane1 = new Plane();
        
        plane1.accelerate(2,1);
        
        assertEquals(-1,plane1.getXSpeed());
        assertEquals(-1,plane1.getYSpeed());
    }
    
    /**
     * Prueba 3 del método accelerate
     * Que al menos una de las dos velocidades esté por encima del intervalo (velocidad y)
     */
    @Test
    public void testAccelerateWithHigherYSpeed()
    {
        Plane plane1 = new Plane();
        
        plane1.accelerate(1,2);
        
        assertEquals(-1,plane1.getXSpeed());
        assertEquals(-1,plane1.getYSpeed());
    }
    
    /**
     * Prueba 4 del método accelerate
     * Que al menos una de las dos velocidades esté por debajo del intervalo (velocidad x)
     */
    @Test
    public void testAccelerateWithLowerXSpeed()
    {
        Plane plane1 = new Plane();
        
        plane1.accelerate(-2,1);
        
        assertEquals(-1,plane1.getXSpeed());
        assertEquals(-1,plane1.getYSpeed());
    }
    
    /**
     * Prueba 5 del método accelerate
     * Que al menos una de las dos velocidades esté por debajo del intervalo (velocidad y)
     */
    @Test
    public void testAccelerateWithLowerYSpeed()
    {
        Plane plane1 = new Plane();
        
        plane1.accelerate(1,-2);
        
        assertEquals(-1,plane1.getXSpeed()); 
        assertEquals(-1,plane1.getYSpeed());
    }
    
    /**
     * Prueba 6 del método accelerate
     * Que ambas velocidades estén fuera de sus intervalos
     */
    @Test
    public void testAccelerateWithIcorrectSpeeds(){
        Plane plane1 = new Plane();
        
        plane1.accelerate(-2,-2);
        
        assertEquals(-1,plane1.getXSpeed());
        assertEquals(-1,plane1.getYSpeed());
    }
    
    /*
     * Pruebas del método fly con distintas condiciones:
     * 1-Que la posición x y la posición y sean correctas (true)
     * 2-Que la posición x sea correcta y la posición y no sea correcta (excepción)
     * 3-Que la posición x no sea correcta y la posición y sea correcta (excepción)
     * 4-Que la posición x y la posición y sean incorrectas (excepción)
     */
    /**
     * Prueba 1 del método fly
     * Que la posición x y la posición y sean correctas (true)
     */
    @Test
    public void testFlyWithCorrectParams()
    {
        Plane pl1 = new Plane(4,4);
        
        assertTrue(pl1.fly());
    }
    
    /**
     * Prueba 2 del método fly
     * Que la posición x sea correcta y la posición y no sea correcta (excepción)
     */
    @Test
    public void testFlyWithWrongYPosition(){
        try{
            Plane pl1 = new Plane(4,-1);
            pl1.fly();
            fail("Esperaba salto de excepción");
        }catch(IllegalArgumentException e){
            assertEquals("La nueva altura no puede ser inferior a la mínima permitida de 0", e.getMessage());
        }
    }
    
    /**
     * Prueba 3 del método fly
     * Que la posición x no sea correcta y la posición y sea correcta (excepción)
     */
    @Test
    public void testFlyWithWrongXPosition(){
        try{
            Plane pl1 = new Plane(-1,4);
            pl1.fly();
            fail("Esperaba salto de excepción");
        }catch(IllegalArgumentException e){
            assertEquals("La nueva coordenada no puede ser inferior a la mínima permitida de 0", e.getMessage());
        }
    }
    
    /**
     * Prueba 4 del método fly
     * Que la posición x y la posición y sean incorrectas (excepción)
     */
    @Test
    public void testFlyWithIncorrectParams(){
        try{
            Plane pl1 = new Plane(-1,-1);
            pl1.fly();
            fail("Esperaba salto de excepción");
        }catch(IllegalArgumentException e){
            assertEquals("La nueva coordenada no puede ser inferior a la mínima permitida de 0", e.getMessage());
        }
    }
    
    /*
     * Pruebas del método toString con dos distintos casos
     * 1-Con piloto asignado
     * 2-Sin piloto asignado
     */
    /**
     * Prueba 1 del método toString
     * Con piloto asignado
     */
    @Test
    public void testToStringWithPilot(){
        Person pilot1 = new Person();
        Plane pl1 = new Plane(5,5,pilot1);
        
        String data = pl1.getIdentifier() + "-" + pl1.getFuel() + "-" + 
            pilot1.getHashCode() + "-" + "(" + pl1.getXPos() + "," + pl1.getYPos() 
            + ")" + "-" + "(" + pl1.getXSpeed() + "," + pl1.getYSpeed() + ")";
        
        assertEquals(data, pl1.toString());
    }
    
    /**
     * Prueba 2 del método toString
     * Sin piloto asignado
     */
    @Test
    public void testToStringWithNonPilot(){
        Person pilot1 = new Person();
        
        Plane pl1 = new Plane(5,5);
        
        assertEquals("A-50-NO PILOT-(5,5)-(1,1)",pl1.toString());
    }
    
    
    @Test
     public void testFlyRight2()
    { // caso 1: se crea un avión con combustible
        Person person1 = new Person();
        Plane plane1 = new Plane(person1,'A',Plane.MIN_FUEL + 10);
        
        assertEquals(0,plane1.getXPos());
        
        assertEquals(0,plane1.getYPos());
        
        
        plane1.accelerate(1, 1);
        
        
        assertEquals(true, plane1.fly());
        
        assertEquals(1,plane1.getXPos());
        
        assertEquals(1,plane1.getYPos());
        
        assertEquals(true, plane1.fly());
        
        assertEquals(2,plane1.getXPos());
        
        assertEquals(2,plane1.getYPos());
        
        assertEquals(8,plane1.getFuel());
    }
     
    @Test
     public void testToStringWithoutPilot()
     { //CASO 2 creo un avión sin piloto
         Plane plane1 = new Plane();
         assertEquals(Plane.DEFAULT_IDENTIFIER + "-" + Plane.DEFAULT_FUEL
                 + "-NO PILOT" + "-(" + plane1.getXPos() + "," + plane1.getYPos() + ")" + "-(" + plane1.getXSpeed()
                 + "," + plane1.getYSpeed() + ")" ,plane1.toString());
    }
}
