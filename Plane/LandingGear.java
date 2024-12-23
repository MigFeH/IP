import java.util.ArrayList;
/**
 * Tren de aterrizaje del avión con palanca, puntales izquierdo, derecho y morro
 * 
 * @author Miguel 
 * @version 3-11-21
 */
public class LandingGear
{
    //constantes
    public static final boolean LEVER_UP = true;
    public static final boolean LEVER_DOWN = false;
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";
    public static final String ON = "ON";
    public static final String OFF = "OFF";
    public static final String FAIL = "FAIL";
    public static final String OK = "OK";
    public static final String PRESS = "PRESS";
    
    //variables de instancia
    private boolean lever;  //palanca para mover los puntales del tren de aterrizaje
    private WheelStrut left;    //puntal izquierdo
    private WheelStrut right;   //puntal derecho
    private WheelStrut nose;    //puntal del morro
    /**
     * Constructor de la clase LandingGear con lever down y tres puntales
     */
    public LandingGear()
    {
        Wheel[] wheelsNose = new Wheel[2];
        Wheel[] wheelsLeft = new Wheel[4];
        Wheel[] wheelsRight = new Wheel[4];

        Wheel leftNose = new Wheel();
        Wheel rightNose = new Wheel();
        
        wheelsNose[0] = (leftNose);
        wheelsNose[1] = (rightNose);
        
        for (int i = 0; i < 4; i++){
            wheelsLeft[i] = (new Wheel());
            wheelsRight[i] = (new Wheel());
        }
        
        WheelStrut left = new WheelStrut(false, wheelsLeft);
        WheelStrut right = new WheelStrut(false, wheelsRight);
        WheelStrut nose = new WheelStrut(false, wheelsNose);
        
        setLeft(left);
        setRight(right);
        setNose(nose);
        
        moveLever(LEVER_DOWN);
    }
    
    /**
     * Constructor de la clase LandingGear con los puntales como parámetros
     * 
     * @param left
     * @param right
     * @param nose
     * 
     * Palanca en posición baja y los tres puntales desplegados
     */
    public LandingGear(WheelStrut left, WheelStrut right, WheelStrut nose)
    {
        this();
        setLeft(left);
        setRight(right);
        setNose(nose);
        
        moveLever(LEVER_DOWN);
    }
    
    /**
     * Modifica el valor del atributo lever
     * 
     * @param nuevo valor
     */
    private void setLever (boolean lever){
        this.lever = lever;
    }
    
    /**
     * Modifica el valor del atributo left
     * 
     * @param nuevo valor
     */
    private void setLeft (WheelStrut left){
        checkParam(left != null,"Esperaba puntal left en lugar de null");
        this.left = left;
    }
    
    /**
     * Modifica el valor del atributo right
     * 
     * @param nuevo valor
     */
    private void setRight (WheelStrut right){
        checkParam(right != null,"Esperaba puntal right en lugar de null");
        this.right = right;
    }
    
    /**
     * Modifica el valor del atributo nose
     * 
     * @param nuevo valor
     */
    private void setNose (WheelStrut nose){
        checkParam(nose != null,"Esperaba puntal nose en lugar de null");
        this.nose = nose;
    }
    
    /**
     * Retorna el valor de lever
     * 
     * @return valor de la palanca
     */
    public boolean getLever(){
        return lever;    
    }
    
    /**
     * Retorna el valor de left
     * 
     * @return valor del puntal izquierdo
     */
    public WheelStrut getLeft(){
        return left;    
    }
    
    /**
     * Retorna el valor de right
     * 
     * @return valor del puntal derecho
     */
    public WheelStrut getRight(){
        return right;    
    }
    
    /**
     * Retorna el valor de nose
     * 
     * @return valor del morro
     */
    public WheelStrut getNose(){
        return nose;    
    }
    
    /**
     * Mueve la palanca a LEVER_UP o LEVER_DOWN
     * y repliega o despliega los puntales
     * 
     * @param action LEVER_UP o LEVER_DOWN
     */
    public void moveLever(boolean action){
        setLever(action);
        if (action == LEVER_DOWN){    //si la mueve abajo, lo despliega
            left.deploy();
            right.deploy();
            nose.deploy();
        } else {    //sino, lo repliega
            left.retract();
            right.retract();
            nose.retract();
        }
    }
    
    /**
     * El método test devolverá true si el método test
     * de todos los puntales devuelve true, y false, en otro caso
     * 
     * @return true o false en función de los tests de los puntales
     */
    public boolean test(){
        // if (left.test() && right.test() && nose.test()){
            // return true;
        // } else{
            // return false;
        // }
        return left.test() && right.test() && nose.test();
    }
    
    /**
     * El método print imprimirá:
     * 
     * Posición de la palanca.
     * Resultado de ejecutar el test sobre el tren de aterrizaje.
     * FAIL si el método test() del puntal devuelve false; OK en caso contrario
     * ON si el puntal está desplegado; OFF en otro caso (para cada puntal).
     * Las etiquetas a usar serán declaradas como constantes.
     * 
     * Con un aspecto final como el que sigue:
     * Lever.............. DOWN (o UP)
     * Test............... FAIL
     *    ON
     *  OFF ON
     */
    public void print(){
       System.out.println("Lever.............." + leverStatus());
       System.out.println("Test..............." + testToString());
       System.out.println("   " + noseTestToString());
       System.out.print(leftTestToString());
       System.out.print("   " + rightTestToString());
       System.out.println();    //doble salto de línea por si hay alguna doble
       System.out.println();    //ejecución y se separen los dos resultados 
    }
    
    /**
     * El método toString devolverá el estado de todo el sistema
     * con el siguiente formato:
     * 
     * Estado de la palanca (DOWN / UP)
     * Estado del tren de aterrizaje (OK / FAILURE)
     * Estado de cada puntal (strut) 
     * (ON = deployed / OFF = retracted / PRESS = al menos una rueda 
     * no tiene suficiente presión)
     * 
     * Ejemplos:
     * Lever: UP Status: OK Nose: ON Left: ON Right: ON
     * Lever: DOWN Status: FAILURE Nose: OFF Left: OFF Right: OFF
     */
    public String toString(){
        String data = ("Lever: " + leverStatus() + "    " +
                        "Status: " + testToString() + "    " +
                        "Nose: " + noseTestToString() + "    " + 
                        "Left: " + leftTestToString() + "    " +
                        "Right: " + rightTestToString());
        return data;
        
        // String data = String.format("Lever: %s  Status: %s  Nose: %s    Left: %s    Right: %s",
                                    // leverStatus(),
                                    // testToString(),
                                    // noseTestToString(),
                                    // leftTestToString(),
                                    // rightTestToString());
        // return data;
        
        //los string.format se les escribe al principio la cadena de formatos
        //y luego los métodos 
    }
    
    
    // MÉTODOS AUXILIARES //
    
    /**
     * El método checkParam evaluará una condición y si ésta
     * no se cumple, lanza una excepción con un mensaje
     * 
     * @param la condición a evaluar
     * @param el mensaje de la excepción
     */
    private void checkParam (boolean condition, String msg){
        if (!condition){
            throw new IllegalArgumentException(msg);
        }
    }
    
    private String wheelStrutStateAndTest(WheelStrut wheelStrut){
        String result = "";
        if(wheelStrut.test()){
            if (wheelStrut.isDeployed()){
                result = ON;
            } else{
                result = OFF;
            }
        }else {
            result = PRESS;
        }
        return result;
    }
    
    /**
     * El método leverStatus devuelve un String en función
     * del estado de la palanca
     * 
     * UP cuando sea true
     * DOWN cuando sea false
     * 
     * @return UP o DOWN
     */
    private String leverStatus(){
        if (getLever()){
            return UP;
        } else{
            return DOWN;
        }
    }
    
    /**
     * El método testToString devolverá OK o FAILURE en función
     * del resultado del test
     * 
     * @return OK o FAILURE
     */
    private String testToString(){
        if (test()){
            return OK;
        }else{
            return FAIL;
        }
    }
    
    /**
     * El método leftTestToString devolverá OK u OFF en función
     * del resultado del puntal izquierdo
     * 
     * @return ON u OFF
     */
    private String leftTestToString(){
        if (left.test()){
            return ON;
        }else{
            return OFF;
        }
    }
    
    /**
     * El método rightTestToString devolverá OK u OFF en función
     * del resultado del puntal derecho
     * 
     * @return ON u OFF
     */
    private String rightTestToString(){
        if (right.test()){
            return ON;
        }else{
            return OFF;
        }
    }
    
    /**
     * El método noseTestToString devolverá OK u OFF en función
     * del resultado del puntal del morro
     * 
     * @return ON u OFF
     */
    private String noseTestToString(){
        if (nose.test()){
            return ON;
        }else{
            return OFF;
        }
    }
}