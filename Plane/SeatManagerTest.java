import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
/**
 * The test class SeatManagerTest.
 *
 * @author Miguel
 * @version 24-11-21
 */
public class SeatManagerTest
{
    /*
     * Pruebas del constructor con dos parámetros
     * número de asientos es primera y en clase turista
     * Crea una matriz de asientos con 6 columnas y la suma de los de primera
     * y turista como filas
     * 
     * Casos de uso:
     * 1-Ambos parámetros dentro de los límites
     * 2-Ambos parámetros estén en límite inferior
     * 3-Ambos parámetros estén en límite superior
     * 4-First fuera de límite por debajo y standard correcto
     * 5-Standard fuera del límite por debajo y first correcto
     * 6-First fuera de límite por encima y standard correcto
     * 7-Standard fuera del límite por encima y first correcto
     */
    /**
     * Prueba 1 del constructor con dos parámetros
     * Ambos parámetros dentro de los límites
     */
    @Test
    public void testSeatManagerBothOk()
    {
        //creación de los parámetros
        int firstRows = (SeatManager.MIN_FIRST_ROWS + SeatManager.MAX_FIRST_ROWS) / 2;
        int standardRows = (SeatManager.MIN_STANDARD_ROWS + SeatManager.MAX_STANDARD_ROWS) / 2;
        
        //creación de un objeto de la clase SeatManager
        SeatManager sm = new SeatManager(firstRows, standardRows);
        
        //comprobación de que el objeto existe
        assertNotNull(sm);
        
        //comprobación de resultados
        assertEquals(firstRows, sm.getFirstRows());
        assertEquals(standardRows, sm.getStandardRows());
        
        //comprobación de que la matiz existe
        assertNotNull(sm.getSeats());
        
        //comprobación de que la matriz tiene el número de filas correctas
        assertEquals(firstRows + standardRows, sm.getSeats().length);
        
        //comprobación de que la matriz tiene el número de columnas correctas
        assertEquals(SeatManager.COLUMNS, sm.getSeats()[0].length);
    }
    
    /**
     * Prueba 2 del constructor con dos parámetros
     * Ambos parámetros estén en límite inferior
     */
    @Test
    public void testSeatManagerBothOnLowerLimits()
    {
        int firstRows = SeatManager.MIN_FIRST_ROWS;
        int standardRows = SeatManager.MIN_STANDARD_ROWS;
        
        //creación de un objeto de la clase SeatManager
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        //comprobación de que el objeto existe
        assertNotNull(sm);
        
        //comprobación de resultados
        assertEquals(3, sm.getFirstRows());
        assertEquals(3, sm.getStandardRows());
        
        //comprobación de que la matiz existe
        assertNotNull(sm.getSeats());
        
        //comprobación de que la matriz tiene el número de filas correctas (firstRows + standardRows)
        assertEquals((sm.getFirstRows()) + (sm.getStandardRows()), sm.getSeats().length);
        
        //comprobación de que la matriz tiene el número de columnas correctas (SeatManager.COLUMNS)
        assertEquals(SeatManager.COLUMNS, sm.getSeats()[0].length);
    }
    
    /**
     * Prueba 3 del constructor con dos parámetros
     * Ambos parámetros estén en límite superior
     */
    @Test
    public void testSeatManagerBothOnHigherLimits()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        //creación de un objeto de la clase SeatManager
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        //comprobación de que el objeto existe
        assertNotNull(sm);
        
        //comprobación de resultados
        assertEquals(SeatManager.MAX_FIRST_ROWS, sm.getFirstRows());
        assertEquals(SeatManager.MAX_STANDARD_ROWS, sm.getStandardRows());
        
        //comprobación de que la matiz existe
        assertNotNull(sm.getSeats());
        
        //comprobación de que la matriz tiene el número de filas correctas (firstRows + standardRows)
        assertEquals((sm.getFirstRows()) + (sm.getStandardRows()), sm.getSeats().length);
        
        //comprobación de que la matriz tiene el número de columnas correctas (SeatManager.COLUMNS)
        assertEquals(SeatManager.COLUMNS, sm.getSeats()[0].length);
    }
    
    /**
     * Prueba 4 del constructor con dos parámetros
     * First fuera de límite por debajo y standard correcto
     */
    @Test
    public void testSeatManagerWithWrongLowerFirst()
    {
        try{
            int firstRows = SeatManager.MIN_FIRST_ROWS - 1;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación de un objeto de la clase SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            fail("Esperaba un salto de excepción");    
        } catch (IllegalArgumentException e) {
            assertEquals("Número de filas en clase primera incorrecto", e.getMessage());
        }
    }
    
    /**
     * Prueba 5 del constructor con dos parámetros
     * Standard fuera del límite por debajo y first correcto
     */
    @Test
    public void testSeatManagerWithWrongLowerStandard()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MIN_STANDARD_ROWS - 1;
        
            //creación de un objeto de la clase SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            fail("Esperaba un salto de excepción");    
        } catch (IllegalArgumentException e) {
            assertEquals("Número de filas en clase turista incorrecto", e.getMessage());
        }
    }
    
    /**
     * Prueba 6 del constructor con dos parámetros
     * First fuera de límite por encima y standard correcto
     */
    @Test
    public void testSeatManagerWithWrongHigherFirst()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS + 1;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación de un objeto de la clase SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            fail("Esperaba un salto de excepción");    
        } catch (IllegalArgumentException e) {
            assertEquals("Número de filas en clase primera incorrecto", e.getMessage());
        }
    }
    
    /**
     * Prueba 7 del constructor con dos parámetros
     * Standard fuera del límite por encima y first correcto
     */
    @Test
    public void testSeatManagerWithWrongHigherStandard()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS + 1;
        
            //creación de un objeto de la clase SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            fail("Esperaba un salto de excepción");    
        } catch (IllegalArgumentException e) {
            assertEquals("Número de filas en clase turista incorrecto", e.getMessage());
        }
    }
    
    /*
     * Pruebas del método bookSeat con distintas condiciones:
     * 1-Que el pasajero sea null (excepción)
     * 2-Que el número de la fila en la que se le asigna el asiento al pasajero sea negativo (excepción)
     * 3-Que el número de la fila en la que se le asigna el asiento al pasajero esté fuera de rango (excepción)
     * 4-Que el número de la columna en la que se le asigna el asiento al pasajero sea negativo (excepción)
     * 5-Que el número de la columna en la que se le asigna el asiento al pasajero esté fuera de rango (excepción)
     * 6-Que el asiento al que se le va a asignar al pasajero esté ya ocupado (false)
     * 7-Que el asiento al que se le va a asignar al pasajero no esté ocupado (true)
     */
    /**
     * Prueba 1 del método bookSeat
     * Que el pasajero sea null (excepción)
     */
    @Test
    public void testBookSeatWithNullPassenger()
    {
        try {
            //creamos unos parámetros correctos para la matriz
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
            
            //creación del objeto persona
            Person person = null;
        
            //creamos un objeto SeatManager con los parámetros anteriores
            SeatManager sm = new SeatManager(firstRows,standardRows);
        
            //comprobación de que la matriz no sea null
            assertNotNull(sm.getSeats());
            
            //llamada al método
            sm.bookSeat(person,3,5);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Esperaba pasajero pero fue null", e.getMessage());
        }
    }
    
    /**
     * Prueba 2 del método bookSeat
     * Que el número de la fila en la que se le asigna el asiento al pasajero sea negativo (excepción)
     */
    @Test
    public void testBookSeatWithNegativeRow()
    {
        try {
            //creamos unos parámetros correctos para la matriz
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
            
            //creación del objeto persona
            Person person = new Person();
        
            //creamos un objeto SeatManager con los parámetros anteriores
            SeatManager sm = new SeatManager(firstRows,standardRows);
        
            //comprobación de que la matriz no sea null
            assertNotNull(sm.getSeats());
            
            //llamada al método
            sm.bookSeat(person,-3,5);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Número de fila fuera de límite", e.getMessage());
        }
    }
    
    /**
     * Prueba 3 del método bookSeat
     * Que el número de la fila en la que se le asigna el asiento al pasajero esté fuera de rango (excepción)
     */
    @Test
    public void testBookSeatWithIncorrectRow()
    {
        try {
            //creamos unos parámetros correctos para la matriz
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
            
            //creación del objeto persona
            Person person = new Person();
        
            //creamos un objeto SeatManager con los parámetros anteriores
            SeatManager sm = new SeatManager(firstRows,standardRows);
        
            //comprobación de que la matriz no sea null
            assertNotNull(sm.getSeats());
            
            //llamada al método
            sm.bookSeat(person,sm.getSeats().length + 1,5);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Número de fila fuera de límite", e.getMessage());
        }
    }
    
    /**
     * Prueba 4 del método bookSeat
     * Que el número de la columna en la que se le asigna el asiento al pasajero sea negativo (excepción)
     */
    @Test
    public void testBookSeatWithNegativeColumn()
    {
        try {
            //creamos unos parámetros correctos para la matriz
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
            
            //creación del objeto persona
            Person person = new Person();
        
            //creamos un objeto SeatManager con los parámetros anteriores
            SeatManager sm = new SeatManager(firstRows,standardRows);
        
            //comprobación de que la matriz no sea null
            assertNotNull(sm.getSeats());
            
            //llamada al método
            sm.bookSeat(person,5,-5);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Número de columna fuera de límite", e.getMessage());
        }
    }
    
    /**
     * Prueba 5 del método bookSeat
     * Que el número de la columna en la que se le asigna el asiento al pasajero esté fuera de rango (excepción)
     */
    @Test
    public void testBookSeatWithIncorrectColumn()
    {
        try {
            //creamos unos parámetros correctos para la matriz
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
            
            //creación del objeto persona
            Person person = new Person();
        
            //creamos un objeto SeatManager con los parámetros anteriores
            SeatManager sm = new SeatManager(firstRows,standardRows);
        
            //comprobación de que la matriz no sea null
            assertNotNull(sm.getSeats());
            
            //llamada al método
            sm.bookSeat(person,5,sm.getSeats().length + 1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Número de columna fuera de límite", e.getMessage());
        }
    }
    
    /**
     * Prueba 6 del método bookSeat
     * Que el asiento al que se le va a asignar al pasajero esté ya ocupado (false)
     */
    @Test
    public void testBookSeatWithOcuped()
    {
        //creamos unos parámetros correctos para la matriz
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
          
        //creación de objetos persona
        Person person1 = new Person();
        Person person2 = new Person();
          
        //creamos un objeto SeatManager con los parámetros anteriores
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        //comprobación de que la matriz no sea null
        assertNotNull(sm.getSeats());
            
        //asignamos a una posición de la matriz el objeto person1
        sm.bookSeat(person1,5,5);
            
        //comprobación de resultados
        assertFalse(sm.bookSeat(person2,5,5));
    }
    
    /**
     * Prueba 7 del método bookSeat
     * Que el asiento al que se le va a asignar al pasajero no esté ocupado (true)
     */
    @Test
    public void testBookSeatWithFreeSeat()
    {
        //creamos unos parámetros correctos para la matriz
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
            
        //creación del objeto persona
        Person person1 = new Person();
            
        //creamos un objeto SeatManager con los parámetros anteriores
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        //comprobación de que la matriz no sea null
        assertNotNull(sm.getSeats());
            
        //comprobación de resultados
        assertTrue(sm.bookSeat(person1,5,5));
    }
    
    /*
     * Pruebas del método releaseSeat con distintas condiciones
     * 1-Que tanto la fila como la columna que se introducen como parámetros, sean válidos
     * 2-Que tanto la fila como la columna que se introducen como parámetros, sean negativos (excepción)
     * 3-Que tanto la fila como la columna que se introducen como parámetros, estén por encima de los límites (excepción)
     * 4-Que la fila que se introduce como parámetro sea negativa y la columna sea válida (excepción)
     * 5-Que la fila que se introduce como parámetro esté por encima de los límites y la columna sea válida (excepción)
     * 6-Que la columna que se introduce como parámetro sea negativa y la fila sea válida (excepción)
     * 7-Que la columna que se introduce como parámetro esté por encima de los límites y la fila sea válida (excepción)
     */
    /**
     * Prueba 1 del método releaseSeat
     * Que tanto la fila como la columna que se introducen como parámetros, sean válidos
     */
    @Test
    public void testReleaseSeatWithCorrectParams()
    {
        //creación de los parámetros del constructor
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        //creación del objeto SeatManager
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        //comprobación de resultados
        assertEquals(sm.getSeat(3,3),sm.releaseSeat(3,3));
    }
    
    /**
     * Prueba 2 del método releaseSeat
     * Que tanto la fila como la columna que se introducen como parámetros, sean negativos (excepción)
     */
    @Test
    public void testReleaseSeatWithNegativeParams()
    {
        try{
            //creación de los parámetros del constructor
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación del objeto SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            //llamada al método
            sm.releaseSeat(-2,-2);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila fuera de límites",e.getMessage());
        }
    }
    
    /**
     * Prueba 3 del método releaseSeat
     * Que tanto la fila como la columna que se introducen como parámetros, estén por encima de los límites (excepción)
     */
    @Test
    public void testReleaseSeatWithTooHighParams()
    {
        try{
            //creación de los parámetros del constructor
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación del objeto SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            //llamada al método
            sm.releaseSeat(100,100);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila fuera de límites",e.getMessage());
        }
    }
    
    /**
     * Prueba 4 del método releaseSeat
     * Que la fila que se introduce como parámetro sea negativa y la columna sea válida (excepción)
     */
    @Test
    public void testReleaseSeatWithNegativeRow()
    {
        try{
            //creación de los parámetros del constructor
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación del objeto SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            //llamada al método
            sm.releaseSeat(-5,3);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila fuera de límites",e.getMessage());
        }
    }
    
    /**
     * Prueba 5 del método releaseSeat
     * Que la fila que se introduce como parámetro esté por encima de los límites y la columna sea válida (excepción)
     */
    @Test
    public void testReleaseSeatWithTooHighRow()
    {
        try{
            //creación de los parámetros del constructor
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación del objeto SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            //llamada al método
            sm.releaseSeat(100,3);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila fuera de límites",e.getMessage());
        }
    }
    
    /**
     * Prueba 6 del método releaseSeat
     * Que la columna que se introduce como parámetro sea negativa y la fila sea válida (excepción)
     */
    @Test
    public void testReleaseSeatWithNegativeColumn()
    {
        try{
            //creación de los parámetros del constructor
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación del objeto SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            //llamada al método
            sm.releaseSeat(3,-3);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Columna fuera de límites",e.getMessage());
        }
    }
    
    /**
     * Prueba 7 del método releaseSeat
     * Que la columna que se introduce como parámetro esté por encima de los límites y la fila sea válida (excepción)
     */
    @Test
    public void testReleaseSeatWithTooHighColumn()
    {
        try{
            //creación de los parámetros del constructor
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación del objeto SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            //llamada al método
            sm.releaseSeat(3,300);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Columna fuera de límites",e.getMessage());
        }
    }
        
    /*
     * Pruebas del método oldestPassenger con distintas condiciones
     * 1-Que no haya pasajeros (null)
     * 2-Que haya pasajero con la mayor edad
     * 3-Que haya varios pasajeros
     * 4-Que haya varios pasageros con la misma edad (devuelve el primer pasajero con más edad)
     */
    /**
     * Prueba 1 del método oldestPassenger 
     * Que no haya pasajeros (null)
     */
    @Test
    public void testOldestPassengerWithoutPassengers()
    {
        //creación de los parámetros 
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        //creación del objeto SeatManager
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        //comprobación de resultados
        assertNull(sm.oldestPassenger());
    }
    
    /**
     * Prueba 2 del método oldestPassenger 
     * Que haya pasajero con la mayor edad
     */
    @Test
    public void testOldestPassengerWithOldestPassenger()
    {
        //creación de los parámetros 
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        //creación del objeto SeatManager
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                sm.getSeats()[i][j] = new Person();
            }
        }
        
        //comprobación de resultados
        assertNotNull(sm.oldestPassenger());
    }
    
    /**
     * Prueba 3 del método oldestPassenger 
     * Que haya varios pasajeros
     */
    @Test
    public void testOldestPassengerWithManyPassengers()
    {
        //creación del objeto SeatManager
        SeatManager sm = new SeatManager(3,4);
        
        //creación de los objetos persona a introducir en la matriz como elementos de ella
        sm.bookSeat(new Person(10),1,1);
        sm.bookSeat(new Person(20),2,2);
        sm.bookSeat(new Person(18),3,3);
        
        //comprobación de resultados
        assertEquals(20,sm.oldestPassenger().getAge());
    }
    
    /**
     * Prueba 4 del método oldestPassenger 
     * Que haya varios pasajeros con la misma edad (devuelve el primer pasajero con más edad)
     */
    @Test
    public void testOldestPassengerWithManyPassengersWithSameAge()
    {
        //creación del objeto SeatManager
        SeatManager sm = new SeatManager(3,4);
        
        //creación de los objeto persona
        Person p1 = new Person(20);
        Person p2 = new Person(20);
        Person p3 = new Person(20);
        
        //introducción en la matriz de los elementos persona
        sm.bookSeat(p1,1,1);
        sm.bookSeat(p2,2,2);
        sm.bookSeat(p3,3,3);
        
        //comprobación de resultados
        assertEquals(20,sm.oldestPassenger().getAge());
    }
    
    /*
     * Pruebas del método numberOfFreeSeats con distintas condiciones
     * 1-Que no haya asientos libres (contador = 0)
     * 2-Que haya asientos libres (contados > 0)
     * 3-Que la fila a revisar el número de asientos libres sea negativa
     * 4-Que la fila a revisar el número de asientos libres esté por encima de los límites de la matriz
     */
    /**
     * Prueba 1 del método numberOfFreeSeats
     * Que no haya asientos libres (contador = 0)
     */
    @Test
    public void testNumberOfFreeSeatsWitoutFree()
    {
        //creación de los parámetros 
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        //creación del objeto SeatManager
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        //añadimos elementos a la matriz bidimensional
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[0].length; j++)
            {
                Person aux = new Person();
                sm.getSeats()[i][j] = aux;
            }
        }
        
        //comprobación de resultados
        assertEquals(0,sm.numberOfFreeSeats(2));
    }
    
    /**
     * Prueba 2 del método numberOfFreeSeats
     * Que haya asientos libres (contados > 0)
     */
    @Test
    public void testNumberOfFreeSeats()
    {
        //creación de los parámetros 
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        //creación del objeto SeatManager
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        //comprobación de resultados
        assertEquals(6,sm.numberOfFreeSeats(2));
    }
    
    /**
     * Prueba 3 del método numberOfFreeSeats
     * Que la fila a revisar el número de asientos libres sea negativa
     */
    @Test
    public void testNumberOfFreeSeatsInNegativeRow()
    {
        try{
            //creación de los parámetros 
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación del objeto SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
        
            //llamada al método
            sm.numberOfFreeSeats(-1);
            
            fail("Esperaba salto de excepción");
        }catch (IllegalArgumentException e){
            assertEquals("Filas fuera de límite",e.getMessage());
        }
    }
    
    /**
     * Prueba 4 del método numberOfFreeSeats
     * Que la fila a revisar el número de asientos libres esté por encima de los límites de la matriz
     */
    @Test
    public void testNumberOfFreeSeatsInTooHighRow()
    {
        try{
            //creación de los parámetros 
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            //creación del objeto SeatManager
            SeatManager sm = new SeatManager(firstRows,standardRows);
        
            //llamada al método
            sm.numberOfFreeSeats(200000);
            
            fail("Esperaba salto de excepción");
        }catch (IllegalArgumentException e){
            assertEquals("Filas fuera de límite",e.getMessage());
        }
    }
    
    /**
     * Prueba del método print
     * Matriz con asientos libres y ocupados
     */
    @Test
    public void testPrint()
    {
        SeatManager sm = new SeatManager(5,7);
        sm.releaseSeat(0,0);
        sm.releaseSeat(1,1);
        sm.print();
    }
    
    /*
     * Pruebas del método removeMales con distintas condiciones
     * 1-Que la lista que recibe como parámetro sea null (excepción)
     * 2-Que la lista que recibe como parámetro sea de elementos null (excepción)
     * 3-Que no haya hombres en la lista parámetro (retornaría 0)
     * 4-Que haya hombres en la lista parámetro (retornaría el número de hombres eliminados)
     */
    /**
     * Prueba 1 del método removeMales
     * Que la lista que recibe como parámetro sea null (excepción)
     */
    @Test
    public void testRemoveMalesWithNullParam()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
        
            sm.removeMales(null);
            
            fail("Esperaba salto de excepción");
        } catch(IllegalArgumentException e){
            assertEquals("Esperaba lista y fue null",e.getMessage());
        }
    }
    
    /**
     * Prueba 2 del método removeMales
     * Que la lista que recibe como parámetro sea de elementos null (excepción)
     */
    @Test
    public void testRemoveMalesWithNullElements()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            ArrayList<Person> list = new ArrayList();
            
            for(int i = 0; i < sm.getSeats().length; i++)
            {
                for(int j = 0; j < sm.getSeats()[i].length; j++)
                {
                    list.add(null);
                }
            }
            
            sm.removeMales(list);
            
            fail("Esperaba salto de excepción");
        } catch(IllegalArgumentException e){
            assertEquals("No debe haber elementos null en la lista",e.getMessage());
        }
    }
    
    /**
     * Prueba 3 del método removeMales
     * Que no haya hombres en la lista parámetro (retornaría 0)
     */
    @Test
    public void testRemoveMalesWithFemaleElements()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        ArrayList<Person> list = new ArrayList();
        
        Person p1 = new Person(Person.GENDER_FEMALE);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                list.add(p1);
            }
        }
        
        assertEquals(0,sm.removeMales(list));
    }
    
    /**
     * Prueba 4 del método removeMales
     * Que haya hombres en la lista parámetro (se hará con 7 hombres)
     */
    @Test
    public void testRemoveMalesWithGenderElements()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        ArrayList<Person> list = new ArrayList();
        
        for(int i = 0; i < 7; i++)
        {
            Person p1 = new Person(Person.GENDER_MALE);
            list.add(p1);
        }
        
        assertEquals(7,sm.removeMales(list));
    }
    
    /*
     * Pruebas del método childrenPassengers con distintas condiciones
     * 1-Que no haya niños en el avión (retorna una matriz vacía)
     * 2-Que haya niños en el avión
     */
    /**
     * Prueba 1 del método childrenPassengers
     * Que no haya niños en el avión (retorna una matriz vacía)
     */
    @Test
    public void testChildrenPassengersWithoutChildrens()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                Person p1 = new Person((Person.ADULTHOOD_AGE) + 1);
                sm.getSeats()[i][j] = p1;
            }
        }
        
        ArrayList<Person> aux = new ArrayList();
        
        assertEquals(aux,sm.childrenPassengers());
    }
    
    /**
     * Prueba 2 del método childrenPassengers
     * Que haya niños en el avión
     */
    @Test
    public void testChildrenPassengersWithChildrens()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                Person p1 = new Person((Person.ADULTHOOD_AGE) - 1);
                sm.getSeats()[i][j] = p1;
            }
        }
        
        assertNotNull(sm.childrenPassengers());
    }
    
    /*
     * Pruebas del método getNumPax con distintas condiciones
     * 1-Que se elija el área 1
     * 2-Que se elija el área 2
     * 3-Que se elija el área 3
     */
    /**
     * Prueba 1 del método getNumPax
     * Que se elija el área 1
     */
    @Test
    public void testGetNumPaxWithArea1()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                Person p1 = new Person();
                sm.getSeats()[i][j] = p1;
            }
        }
        
        byte area = 1;
        
        assertEquals(66,sm.getNumPax(area));
    }
    
    /**
     * Prueba 2 del método getNumPax
     * Que se elija el área 2
     */
    @Test
    public void testGetNumPaxWithArea2()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                Person p1 = new Person();
                sm.getSeats()[i][j] = p1;
            }
        }
        
        byte area = 2;
        
        assertEquals(180,sm.getNumPax(area));
    }
    
    /**
     * Prueba 3 del método getNumPax
     * Que se elija el área 3
     */
    @Test
    public void testGetNumPaxWithArea3()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                Person p1 = new Person();
                sm.getSeats()[i][j] = p1;
            }
        }
        
        byte area = 3;
        
        assertEquals(246,sm.getNumPax(area));
    }
    
    /*
     * Pruebas del método getNumPaxBySection con distintas condiciones
     * 1-Que la fila del primer elemento sea negativa y lo demás correcto (excepción)
     * 2-Que la columna del primer elemento sea negativa y lo demás correcto (excepción)
     * 3-Que la fila del segundo elemento sea negativa y lo demás correcto (excepción)
     * 4-Que la columna del segundo elemento sea negativa y lo demás correcto (excepción)
     * 5-Que la fila del primer elemento sea mayor que la fila del segundo elemento (excepción)
     * 6-Que la columna del primer elemento sea mayor que la columna del segundo elemento (excepción)
     * 7-Que la fila del primer elemento esté fuera de los límites (excepción)
     * 8-Que la columna del primer elemento esté fuera de los límites (excepción)
     * 9-Que la fila del segundo elemento esté fuera de los límites (excepción)
     * 10-Que la columna del segundo elemento esté fuera de los límites (excepción)
     * 11-Que todos los parámetros sean válidos
     */
    /**
     * Prueba 1 del método getNumPaxBySection
     * Que la fila del primer elemento sea negativa y lo demás correcto (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithNegativeRow1()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(-4,0,3,2);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila 1 no válida", e.getMessage());
        }
    }
    
    /**
     * Prueba 2 del método getNumPaxBySection
     * Que la columna del primer elemento sea negativa y lo demás correcto (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithNegativeColumn1()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(0,-4,1,1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Columna 1 no válida", e.getMessage());
        }
    }
    
    /**
     * Prueba 3 del método getNumPaxBySection
     * Que la fila del segundo elemento sea negativa y lo demás correcto (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithNegativeRow2()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(0,0,-1,1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila 1 no válida", e.getMessage()); /*el error salta en el checkParam de la fila 1 al ser mayor que fila 2 (por ser negativa y la fila 1 no), 
            así que diremos que el mensaje de la excepción es el de la fila 1*/
        }
    }
    
    /**
     * Prueba 4 del método getNumPaxBySection
     * Que la columna del segundo elemento sea negativa y lo demás correcto (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithNegativeColumn2()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(0,0,1,-1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Columna 1 no válida", e.getMessage()); /*el error salta en el checkParam de la columna 1 al ser mayor que columna 2 (por ser negativa y la columna 1 no), 
            así que diremos que el mensaje de la excepción es el de la columna 1*/
        }
    }
    
    /**
     * Prueba 5 del método getNumPaxBySection
     * Que la fila del primer elemento sea mayor que la fila del segundo elemento (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithRow1MoreHigherThanRow2()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(2,0,1,1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila 1 no válida", e.getMessage());
        }
    }
    
    /**
     * Prueba 6 del método getNumPaxBySection
     * Que la columna del primer elemento sea mayor que la columna del segundo elemento (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithColumn1MoreHigherThanColumn2()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(0,2,1,1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Columna 1 no válida", e.getMessage());
        }
    }
    
    /**
     * Prueba 7 del método getNumPaxBySection
     * Que la fila del primer elemento esté fuera de los límites (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithTooHighRow1()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(999,0,1,1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila 1 no válida", e.getMessage());
        }
    }
    
    /**
     * Prueba 8 del método getNumPaxBySection
     * Que la columna del primer elemento esté fuera de los límites (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithTooHighColumn1()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(0,999,1,1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Columna 1 no válida", e.getMessage());
        }
    }
    
    /**
     * Prueba 9 del método getNumPaxBySection
     * Que la fila del segundo elemento esté fuera de los límites (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithTooHighRow2()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(0,0,999,1);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Fila 2 no válida", e.getMessage());
        }
    }
    
    /**
     * Prueba 10 del método getNumPaxBySection
     * Que la columna del segundo elemento esté fuera de los límites (excepción)
     */
    @Test
    public void testGetNumPaxBySectionWithTooHighColumn2()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getNumPaxBySection(0,0,1,999);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Columna 2 no válida", e.getMessage());
        }
    }
    
    /**
     * Prueba 11 del método getNumPaxBySection
     * Que todos los parámetros sean válidos
     */
    @Test
    public void testGetNumPaxBySectionWithNormalParams()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                Person p1 = new Person();
                sm.getSeats()[i][j] = p1;
            }
        }
        
        int row1 = 0;
        int column1 = 0;
        
        int row2 = 1;
        int column2 = 1;
        
        assertEquals(4,sm.getNumPaxBySection(row1,column1,row2,column2));
    }
    
    /*
     * Pruebas del método loadPax con distintas condiciones
     * 1-Que el número de pasajeros exceda la capacidad máxima de asientos del avión (excepción)
     * 2-Que el número de pasajeros sea negativo (excepción)
     * 3-Que el número de pasajeros sea menor que la capacidad máxima del avión
     * 4-Que el número de pasajeros sea el máximo
     */
    /**
     * Prueba 1 del método loadPax
     * Que el número de pasajeros exceda la capacidad máxima de asientos del avión (excepción)
     */
    @Test
    public void testLoadPaxWithExcesivePassengers()
    {
        try{
            SeatManager sm = new SeatManager(4,5); //el número de asientos es de 54
            
            sm.loadPax(60);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("Demasiados pasajeros para el avión",e.getMessage());
        }
    }
    
    /**
     * Prueba 2 del método loadPax
     * Que el número de pasajeros sea negativo (excepción)
     */
    @Test
    public void testLoadPaxWithNegativePassengers()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.loadPax(-5);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("El número de pasajeros no puede ser negativo",e.getMessage());
        }
    }
    
    /**
     * Prueba 3 del método loadPax
     * Que el número de pasajeros sea menor que la capacidad máxima del avión
     */
    @Test
    public void testLoadPaxWithNormalNumberOfPassengers()
    {
        SeatManager sm = new SeatManager(4,5); //número de asientos totales = 54
        
        Person p1 = new Person();
        Person p2 = new Person();
        
        sm.bookSeat(p1,0,0); //reserva de un sitio para un pasajero
        sm.bookSeat(p2,1,1); //reserva de un sitio para un pasajero
        
        sm.loadPax(2);
        
        assertEquals(50, sm.numberOfFreeSeats());
        assertEquals(p1, sm.getSeat(0,0));
        assertEquals(p2, sm.getSeat(1,1));
        
        //comprobación de que despues de p1, se añadió una persona
        assertNotNull(sm.getSeat(0,1));
        assertNotNull(sm.getSeat(0,2));
    }
    
    /**
     * Prueba 4 del método loadPax
     * Que el número de pasajeros sea el máximo
     */
    @Test
    public void testLoadPaxWithMuchPax()
    {
        SeatManager sm = new SeatManager(4,5); //número de asientos totales = 54
        
        Person p1 = new Person();
        Person p2 = new Person();
        
        sm.bookSeat(p1,0,0); //reserva de un sitio para un pasajero
        sm.bookSeat(p2,1,1); //reserva de un sitio para un pasajero
        
        sm.loadPax(54); //quiero cargar más pasajeros que asientos hay libres (52 libres)
        
        assertEquals(0, sm.numberOfFreeSeats());
        assertEquals(p1, sm.getSeat(0,0));
        assertEquals(p2, sm.getSeat(1,1));
    }
    
    /*
     * Pruebas del método getYoungestPeople con distintas condiciones
     * 1-Que no haya pasajeros (excepción)
     * 2-Que haya pasajeros
     */
    /**
     * Prueba 1 del método getYoungestPeople
     * Que no haya pasajeros (excepción)
     */
    @Test
    public void testGetYoungestPeopleWithoutPassengers()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.getYoungestPeople();
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e){
            assertEquals("No hay pasajeros en el avión",e.getMessage());
        }
    }
    
    /**
     * Prueba 2 del método getYoungestPeople
     * Que haya pasajeros
     */
    @Test
    public void testGetYoungestPeopleWithPassengers()
    {
        int firstRows = SeatManager.MAX_FIRST_ROWS;
        int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
        SeatManager sm = new SeatManager(firstRows,standardRows);
        
        for(int i = 0; i < sm.getSeats().length; i++)
        {
            for(int j = 0; j < sm.getSeats()[i].length; j++)
            {
                Person p1 = new Person();
                sm.getSeats()[i][j] = p1;
            }
        }
        
        assertNotNull(sm.getYoungestPeople());
    }
    
    /*
     * Pruebas del método loadPassengers con distintas condiciones
     */
    /**
     * Prueba del método loadPassengers
     * Que el número de pasajeros sea negativo (excepción)
     */
    @Test
    public void testLoadPassengersWithNegativeNumberOfPassengers()
    {
        try{
            int firstRows = SeatManager.MAX_FIRST_ROWS;
            int standardRows = SeatManager.MAX_STANDARD_ROWS;
        
            SeatManager sm = new SeatManager(firstRows,standardRows);
            
            sm.loadPassengers(-3);
            
            fail("Esperaba salto de excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El número de pasajeros no puede ser negativo", e.getMessage());
        }
    }
}
