import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class FlySimulatorTest.
 *
 * @author  Miguel
 * @version 4-12-20
 */
public class FlySimulatorTest
{
    /*
     * Pruebas de addPlane
     * 1- Añadimos un avión que no existe en posición libre
     * 
     * 2- Añadimos un avión con identificador que ya existe
     * 3- Añadimos avión en posición ya ocupada
     * 4- Añadimos null
     * 
     */
   
    /*
     * Pruebas de animate
     * General-  Varios aviones vuelan varias veces hasta que chocan
     *    - Son 4 aviones
     *    - Son 3 aviones
     */
    @Test
    public void testFlySymulatorCollision4Planes() {
        FlySimulator flySimulator = new FlySimulator();
        
        flySimulator.addPlane(new Plane('A',100, 0,0,1,1));
        flySimulator.addPlane(new Plane('B',100, 0,10,1,-1));
        flySimulator.addPlane(new Plane('C',100, 10,0,-1,1));
        flySimulator.addPlane(new Plane('D',100, 10,10,-1,-1));
        
        flySimulator.printMap();
        
        for (int i = 1; i <= 5; i++) {
            //assertEquals(4, flySimulator.getPlanes().length);
            flySimulator.animate();            
        }
        //assertEquals(0, flySimulator.getPlanes().length);
        
        //assertEquals(FlySimulator.COLLISION, flySimulator.getPosition(5,5));
        for (int i = 0; i < Plane.MAX_X_POS + 1; i++){
            for (int j = 0; j < Plane.MAX_Y_POS + 1; j++){
                if (i!= 5 && j != 5){
                    //assertEquals(FlySimulator.FREE,flySimulator.getPosition(i,j));
                }
            }
        }
    }
    
    @Test
    public void testFlySymulatorCollision3Planes() {
        FlySimulator flySimulator = new FlySimulator();        
         
        flySimulator.addPlane(new Plane('E',100, 0,10,0,-1));
        flySimulator.addPlane(new Plane('F',100, 0,4,0,1));
        flySimulator.addPlane(new Plane('G',100, 3,7,-1,0));
        flySimulator.printMap();
        
        for (int i = 1; i <= 3; i++) {
            //assertEquals(3, flySimulator.getPlanes().length);
            flySimulator.animate();
        }
        //assertEquals(0, flySimulator.getPlanes().length);
        
        //assertEquals(FlySimulator.COLLISION, flySimulator.getPosition(0,7));
        for (int i = 0; i < Plane.MAX_X_POS + 1; i++){
            for (int j = 0; j < Plane.MAX_Y_POS + 1; j++){
                if (i!= 0 && j != 7){
                    //assertEquals(FlySimulator.FREE,flySimulator.getPosition(i,j));
                }
            }
        }
    }       
}