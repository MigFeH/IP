
import java.util.ArrayList;
/**
 * Puntal para sujetar dos ruedas
 * Contiene valor para indicar si está o no desplegado
 * 
 * @author Miguel 
 * @version 27-10-21
 */
public class WheelStrut
{
    //Constantes
    public static final double BOEING_737_PRESSURE = 1739; //Mb de presión
    public static final boolean IS_DEPLOYED = true;
    
    public static final int DEFAULT_NUMBER_OF_WHEELS = 3;
    
    // instance variables
    private boolean deployed;
    // private Wheel leftWheel;
    // private Wheel rightWheel;
    private Wheel[] wheels;
    /**
     * Constructor de la clase WheelStrut sin parámetros
     */
    public WheelStrut()
    {
        setDeployed(IS_DEPLOYED);
        // wheels = new ArrayList<Wheel>();
        setWheels(DEFAULT_NUMBER_OF_WHEELS);
    }
    
    /**
     * Constructor de la clase WheelStrut que reciba el número de ruedas
     * 
     * @param numberOfWheels, número de ruedas del puntal creado
     */
    public WheelStrut(int numberOfWheels)
    {
        this();
        setDeployed(IS_DEPLOYED);
        setWheels(numberOfWheels);  //setWheels con nº de elementos como parámetro
    }

    /**
     * Constructor de la clase WheelStrut que reciba deployed y wheels
     * 
     * @param deployed
     * @param la lista wheels
     */
    public WheelStrut(boolean deployed, Wheel[] wheels)
    {
        this();
        setDeployed(deployed);
        setWheels(wheels);  //setWheels con una lista como parámetro
    }
    
    /**
     * Asigna a wheels el valor del parámetro que recibe
     * 
     * @param wheels nuevo valor para el atributo wheels
     */
    private void setWheels(Wheel[] wheels)
    {
        checkParam(wheels != null, "Esperaba lista y fue null");
        checkParam(wheels.length > 0, "El número de ruedas debe ser mayor que 0");
        //size es un método de la lista
        this.wheels = wheels;
    }
    
    /**
     * Crea una lista con tantas ruedas como reciba como parámetro
     * 
     * @param numberOfWheels, número de ruedas de la lista
     */
    private void setWheels(int numberOfWheels)
    {
        checkParam(numberOfWheels > 0, "El número de ruedas debe ser mayor que 0");
        wheels = new Wheel[numberOfWheels]; //crea un objeto wheels que es una referencia a una lista vacía (por ahora)
        for (int i = 0; i < numberOfWheels; i++){
            wheels[i] = new Wheel(BOEING_737_PRESSURE, BOEING_737_PRESSURE); //añade una rueda a la lista
        }
    }
    
    /**
     * Retorna la lista entera
     * 
     * @return wheels, lista de ruedas
     */
    public Wheel[] getWheels() //es de tipo ArrayList porque devuelve la LISTA
    {
        return wheels;    
    }
    
    /**
     * Devuelve un elemento de la lista (una rueda), cuya posición recibe como parámetro
     * 
     * @param posición en la lista
     * @return wheel, rueda localizada dentro de la lista en la posición recibida
     */
    public Wheel getWheel(int index) //es de tipo Wheel porque devuelve el ELEMENTO
    {
        checkParam(index < wheels.length && index >= 0, "Índice fuera de límites");
        //el índice debe ser menor que el tamaño de la lista y mayor o igual que 0
        return wheels[index];
    }
    
    /**
     * Asigna valor al atributo deployed
     * 
     * @param deployed true si está desplegado
     */
    private void setDeployed(boolean deployed)
    {
        this.deployed = deployed;
    }

    /**
     * Retorna el valor del atributo deployed
     * 
     * @return el valor de deployed
     */
    public boolean isDeployed() //Al ser booleano, en vez de poner get, ponemos is
    {
        return deployed;
    }

    /**
     * Método que comprueba si ambas ruedas están operativas
     * (ambas pasan sus test)
     * 
     * @return retorna true si el puntal está operativo y false si no lo está
     */
    public boolean test(){
        for (int i = 0; i < wheels.length; i++)
        {
            if (wheels[i].test() == false){ //saca el elemento de la lista en la posición i
                return false;
            }
        }
        return true;
    }

    /**
     * El método print imprimirá:
     * El valor de la propiedad deployed
     * El valor de retorno del método test()
     * El resultado de la ejecución del método print() sobre cada rueda.
     *
     * Con un aspecto final como el que sigue:
     * 
     * RETRACTED (o DEPLOYED)
     * Test............... FALSE.
     *
     * LEFT Wheel
     * Max Pressure....... 34500,0 Mb.
     * Current Pressure... 32000,0 Mb (92,75%)
     * Test............... OK (FAIL, si falló el test).
     * 
     * RIGHT Wheel
     * Max Pressure....... 34500,0 Mb.
     * Current Pressure... 5205,0 Mb (15,08%)
     * Test............... OK (FAIL, si falló el test).
     *
     */
    public void print()
    {        
        System.out.println(deployedToString());
        System.out.println("Test..............." + testToString());
        System.out.println();
        System.out.printf("LEFT Wheel%n");
        
        System.out.println(String.format("Max Pressure....... %.1f Mb", getWheel(0).getMaxPressure()));
        System.out.println(String.format("Current Pressure... %.1f Mb (%.2f)" ,getWheel(0).getPressure(), getWheel(0).getPercentage()));
        System.out.println(String.format("Test............... %s", getWheel(0).getMaxPressure()));
        
        System.out.println();
        System.out.printf("RIGHT Wheel%n");
        
        System.out.println(String.format("Max Pressure....... %.1f Mb", getWheel(1).getMaxPressure()));
        System.out.println(String.format("Current Pressure... %.1f Mb (%.2f)" ,getWheel(1).getPressure(), getWheel(1).getPercentage()));
        System.out.println(String.format("Test............... %s", getWheel(1).getMaxPressure()));
        
        //el printf equivale a String.format; se pone el % con la inicial del tipo al que queremos formatear y luego parametros => %i%s,param de i,param de s
        //el %n equivale a salto de línea
    }
    
     /**
     * Validación de parámetro. Salta excepción si no cumple parámetro
     * 
     * @param la condición
     * @param mensaje si no cumple condición
     */
    private void checkParam(boolean condition, String msg){
        if (!condition){
            throw new IllegalArgumentException(msg);
        }
    }
    
    /**
     * Despliega el puntal
     */
    public void deploy() {
        setDeployed(IS_DEPLOYED);
    }
    
    /**
     * Repliega el puntal
     */
    public void retract(){
        setDeployed(! IS_DEPLOYED);
    }
    
    /**
     * Devuelve RETRACTED o DEPLOYED para cuando deploy sea false o true respectivamente
     * 
     * @return RETRACTED o DEPLOYED
     */
    private String deployedToString()
    {
        if (isDeployed()){
            return "DEPLOYED";
        }else{
            return "RETRACTED";
        }
    }
    
    /**
     * Devuelve TRUE o FALSE en función del resultado del método test
     * 
     * @return TRUE o FALSE
     */
    private String testToString()
    {
        if (test()){
            return "TRUE";
        }else{
            return "FALSE";
        }
    }
}
