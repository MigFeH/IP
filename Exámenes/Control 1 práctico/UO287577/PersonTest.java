

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PersonTest.
 *
 * @author  Miguel
 * @version 20-10-21
 */
public class PersonTest
{
    /*
     * Pruebas para setAge
     * Casos de uso:
     * 1- Llamo a setAge con valor dentro de los límites
     * 2- Llamo a setAge con valor debajo del límite inferior
     * 3- Llamo a setAge con valor superior al límite superior
     * 4- Llamo a setAge con valor en límite inferior
     * 5- Llamo a setAge con valor en límite superior
     * 
     */
    /*
     * Pruebas de setAge
     * 1- Llamo a setAge con valor dentro de los límites
     */
    @Test
    public void testSetAgeInsideLimits() {
        Person person1 = new Person ();
        person1.setAge(25);
        assertEquals(25,person1.getAge());
    }
    
    /*
     * Pruebas de setAge
     * 2- Llamo a setAge con valor debajo del límite inferior
     */
    @Test
    public void testSetAgeBelowLimits() {
        Person person1 = new Person ();
        try {
            person1.setAge(Person.MIN_AGE -1);
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals ("La edad no puede ser negativa", e.getMessage());
        }
    }
    
        /*
     * Pruebas de setAge
     * 3- Llamo a setAge con valor superior al límite superior
     */
    @Test
    public void testSetAgeUpperLimits() {
        Person person1 = new Person ();
        try {
            person1.setAge(Person.MAX_AGE +1);
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals ("La edad debe ser menor que 120", e.getMessage());
        }
    }
    
        /*
     * Pruebas de setAge
     * 4- Llamo a setAge con valor en límite inferior
     */
    @Test
    public void testSetAgeOnLowerLimits() {
        Person person1 = new Person ();
        try {
            person1.setAge(-13);
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals (40,person1.getAge());
        }
    }
    
        /*
     * Pruebas de setAge
     * 5- Llamo a setAge con valor en límite superior
     */
    @Test
    public void testSetAgeOnHigherLimits() {
        Person person1 = new Person ();
        try {
            person1.setAge(Person.MAX_AGE);
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals (40,person1.getAge());
        }
    }
    
    /*
     * Pruebas de getHashCode
     */
    @Test
    public void testGetHashCode() {
        Person person1 = new Person ();
        assertEquals ("40-FE-ALON-ADULT", person1.getHashCode());
    }
    
    /*
     * Pruebas de getDiscount con distintas edades
     * 1-Edad menor de 12 años exclusive
     * 2-Edad entre los 12 años y la mayoría de edad exclusive
     * 3-Edad entre la mayoría de edad y la jubilación exclusive
     * 4-Edad situada en la de jubilación inclusive hasta la edad máxima
     */
    @Test
    public void testGetDiscountMinorOf12(){ //para menores de 12
        Person person1 = new Person(11);
        assertEquals (15, person1.getDiscount());
    }
    
    /*
     * Prueba 2 de getDiscount
     */
    @Test
    public void testGetDiscountMinorOf18(){ //para menores de la mayoría de edad y mayores de 12
        Person person1 = new Person(14);
        assertEquals(5,person1.getDiscount());
    }
    
    /*
     * Prueba 3 de getDiscount
     */
    @Test
    public void testGetDiscountMinorOf65(){ //para menores de la edad de jubilación y mayores de edad
        Person person1 = new Person(20);
        assertEquals(0,person1.getDiscount());
    }
    
    /*
     * Prueba 4 de getDiscount
     */
    @Test
    public void testGetDiscountMinorOfMaxAge(){ //para los jubilados hasta la edad máxima
        Person person1 = new Person(70);
        assertEquals(10,person1.getDiscount());
    }
}