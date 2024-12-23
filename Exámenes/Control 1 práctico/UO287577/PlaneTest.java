
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
    /*
     * Pruebas del constructor por defecto
     * Piloto = null
     * Identificador 'A'
     * Combustible 0
     */
    @Test
    public void testPlaneWithoutParams(){
        Plane plane1 = new Plane(); //el new Plane llama al constructor. con el Plane plane1 creo un objeto llamado plane1 de la clase plane
        assertEquals(Plane.DEFAULT_IDENTIFIER, plane1.getIdentifier());
        assertEquals(Plane.DEFAULT_FUEL, plane1.getFuel());
        assertEquals(Plane.DEFAULT_PILOT, plane1.getPilot());
    }

    /*
     * Pruebas del constructor con parámetro piloto
     * 1- Piloto cualquiera
     * 2- null en lugar de un piloto
     */

    // /**
     // * 1-Piloto cualquiera
     // */
    // @Test
    // public void testPlaneWithPilot(){
        // Person pilot = new Person();     //llamada al constructor
        // Plane plane1 = new Plane(pilot); //forma de llamar al constructor con un parámetro
        // assertEquals(pilot, plane1.getPilot()); //un assertequals por cada atributo
        // assertEquals(Plane.DEFAULT_IDENTIFIER, plane1.getIdentifier());
        // assertEquals(Plane.DEFAULT_FUEL,plane1.getFuel());
    // }

    // /**
     // * 2- recibo null
     // */
    // @Test
    // public void testPlaneWithoutPilot(){
        // //Person pilot = new Person(); //no necesito piloto al tener éste valor nulo    //llamada al constructor
        // Plane plane1 = new Plane(null); //forma de llamar al constructor con un parámetro
        // assertEquals(null, plane1.getPilot()); //un assertequals por cada atributo
        // assertEquals(Plane.DEFAULT_IDENTIFIER, plane1.getIdentifier());
        // assertEquals(Plane.DEFAULT_FUEL,plane1.getFuel());
    // }
    
    /**
     * Prueba del constructor Plane con pilot1 como único parámetro
     */
    @Test
    public void testConstructorWithOnlyPilot(){
        Person pilot1 = new Person(40,"FERNANDO","Alonso",true);
        Plane plane = new Plane(pilot1);
        assertEquals(pilot1,plane.getPilot());
    }
    
    /**
     * Prueba del constructor Plane con Identifier como único parámetro
     */
    @Test
    public void testConstructorWithOnlyIdentifier(){
        Plane plane=new Plane('Z');
        assertEquals('Z',plane.getIdentifier());
    }
    
    /**
     * Prueba del constructor Plane con Fuel como único parámetro
     */
    @Test
    public void testConstructorWithOnlyFuel(){
        Plane plane=new Plane(50);
        assertEquals(50,plane.getFuel());
    }
    
    /*
     * Pruebas para setPilot con distintas condiciones:
     * 
     * 1-Que el piloto sea hombre, mayor de 30 y menor de 50 años (sin incluir) (true)
     * 2-Que el piloto sea hombre y menor de 30 años 
     * 3-Que el piloto sea hombre y mayor de 50 años
     * 4-Que el piloto sea mujer sin ser mayor de edad
     * 5-Que el piloto sea mujer, sea mayor de edad y no esté jubilada (true)
     * 6-Que el piloto sea mujer y esté jubilada
     * 7-Que el piloto sea null (true)
     */
    @Test
    public void testSetPilotBeingMaleWith40Years(){ //1-Que el piloto sea hombre, mayor de 30 y menor de 50 años (sin incluir)
        Person pilot1=new Person(40,"Fernando","Alonso",true);
        Plane plane1=new Plane(pilot1);
        assertEquals(true, plane1.canBePilot());
    }
    
    @Test
    public void testSetPilotBeingMaleWith20Years(){ //2-Que el piloto sea hombre y menor de 30 años
        try {
            Person pilot1 = new Person(20,"Fernando","Alonso",true);
            Plane plane1 = new Plane(pilot1);
            plane1.canBePilot();
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("Esta persona no puede ser piloto",e.getMessage());
        }
    }
    
    @Test
    public void testSetPilotBeingMaleWith70Years(){ //3-Que el piloto sea hombre y mayor de 50 años        Person pilot1=new Person();
        try {
            Person pilot1=new Person(70,"Fernando","Alonso",true);
            Plane plane1=new Plane(pilot1);
            plane1.canBePilot();
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("Esta persona no puede ser piloto",e.getMessage());
        }
    }
    
    @Test
    public void testSetPilotBeingFemaleWith10Years(){ //4-Que el piloto sea mujer sin ser mayor de edad       Person pilot1=new Person();
        try {
            Person pilot1=new Person(10,"María","Fernández",false);
            Plane plane1=new Plane(pilot1);
            pilot1.getAge();
            pilot1.getGender();
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("Esta persona no puede ser piloto",e.getMessage());
        }
    }
    
    @Test
    public void testSetPilotBeingFemaleWith20Years(){ //5-Que el piloto sea mujer, sea mayor de edad y no esté jubilada (true)       Person pilot1=new Person();
        Person pilot1=new Person(20,"María","Fernández",false);
        Plane plane1=new Plane(pilot1);
        assertEquals(true, plane1.canBePilot());
    }
    
    @Test
    public void testSetPilotBeingFemaleWith70Years(){ //6-Que el piloto sea mujer y esté jubilada       Person pilot1=new Person();
        try {
            Person pilot1=new Person(70,"María","Fernández",false);
            Plane plane1=new Plane(pilot1);
            plane1.canBePilot();
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("Esta persona no puede ser piloto",e.getMessage());
        }
    }
    
    @Test
    public void testSetPilotWithoutPilot(){ //7-Que el piloto sea null       Person pilot1=new Person();
        Person pilot1=new Person(20,null,"Fernández",false);
        Plane plane1=new Plane(pilot1);
        plane1.canBePilot();
        assertEquals(true, plane1.canBePilot());
    }
}
