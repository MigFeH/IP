import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatrixTest
{    
    /**
     * Default constructor for test class MatrixTest
     */
    public MatrixTest()
    {
    
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    
    }
    
    @Test
    public void testConstructorIntParameter()
    {
        //creación de un objeto de la clase Matrix
        Matrix matrix = new Matrix(2);
        
        //comprobación de que matrix se ha creado (que no sea null)
        assertNotNull(matrix.returnMatrix());
    }
    
    @Test
    public void testConstructorArrayParameter()
    {
        //creación de un objeto de la clase Matrix
        Matrix matrix = new Matrix(new int[][]{{1, 0}, {0, 1}});
        
        //creación de un Array bidimensional con los elementos resultantes de la creación del objeto matrix
        int[][] expected = { {1, 0}, {0, 1} };
        
        //comprobación de resultados
        assertArrayEquals(expected, matrix.returnMatrix());
    }
    
    @Test
    public void testGetAverage()
    {
        //creación de un objeto de la clase Matrix
        Matrix matrix = new Matrix(new int[][]{{1, 0}, {0, 1}});
        
        //llamada al método y comprobación de resultados
        assertEquals(0, matrix.getAverage());
    }

    @Test
    public void testAddByColumns()
    {
        //creación de un objeto de la clase Matrix
        Matrix matrix = new Matrix(new int[][]{{1, 0}, {0, 1}});
        
        //creación de un Array bidimensional con los elementos resultantes de la ejecucuión del método
        int[]expected = {1,1};
        
        //comprobación de resultados
        assertArrayEquals(expected, matrix.addByColumns());
    }    

    @Test
    public void testSwapColumns()
    {
        //creación de un objeto de la clase Matrix
        Matrix matrix = new Matrix(new int[][]{{1, 0}, {0, 1}});
        
        //llamada al método
        matrix.swapColumns();
        
        //creación de un Array bidimensional con los elementos resultantes de la ejecucuión del método
        int[][] expected = { {0, 1}, {1, 0} };
        
        //comprobación de resultados
        assertArrayEquals(expected, matrix.returnMatrix());
    }

    @Test
    public void testRotateMatrix()
    {
        //creación de un objeto de la clase Matrix
        Matrix matrix = new Matrix(new int[][]{{1, 0}, {0, 1}});
        
        //llamada al método
        matrix.rotateMatrix();
        
        //creación de un Array bidimensional con los elementos resultantes de la ejecucuión del método
        int[][] expected = { {0,1},{1,0} };
        
        //comprobación de resultados
        assertArrayEquals(expected, matrix.returnMatrix());
    }    
    
    @Test
    public void testGetMaxAdjacentValue()
    {
        //creación de un objeto de la clase Matrix
        Matrix matrix = new Matrix(new int[][]{{1, 0}, {0, 1}});
        
        //comprobación de resultados y llamada al método
        assertEquals(0, matrix.getMaxAdjacentValue(0,0));
    }   

    @Test
    public void testSmoothMatrix()
    {
        //creación de un objeto de la clase Matrix
        Matrix matrix = new Matrix(new int[][]{{1, 0}, {0, 1}});
        
        //llamada al método
        matrix.smoothMatrix();
        
        //creación de un Array bidimensional con los elementos resultantes de la ejecucuión del método
        int[][] expected = { {1, 0},{0, 1} };
        
        //comprobación de resultados
        assertArrayEquals(expected, matrix.returnMatrix());
    }
}