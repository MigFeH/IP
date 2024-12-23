

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PersonTest.
 *
 * @author  Miguel
 * @version 15-9-21
 */
public class PersonTest
{    
    /**
     * Pruebas de getHashCode
     */
    @Test
    public void testGetHashCode() 
    {
        Person person1 = new Person();
        
        String data = person1.getAge() + "-" + person1.getShortName().toUpperCase() 
            + "-" + person1.getShortSurname().toUpperCase() + "-" + person1.getStage();
        
        assertEquals(data, person1.getHashCode());
    }
}