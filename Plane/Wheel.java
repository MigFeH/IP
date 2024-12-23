
/**
 * Definición de las ruedas del avión
 * 
 * @author Miguel 
 * @version 20-10-21
 */
public class Wheel
{
    // Constantes estáticas para la clase
    public static final int THRESHOLD = 85;     // umbral, porcentaje sobre la presión máxima por encima del cual la rueda está operativa

    public static final double STANDARD_MAX_PRESSURE = 1000;
    public static final double STANDARD_PRESSURE = 900;

    private double pressure;       // presión actual que tiene la rueda, de tipo double
    private double maxPressure;    // presión máxima que soporta la rueda, de tipo double

    /**
     * Constructor para crear rueda con valores standard
     */
    public Wheel()
    {
        setMaxPressure(STANDARD_MAX_PRESSURE);
        setPressure(STANDARD_PRESSURE);
    }

    /**
     * Constructor para crear la rueda con las presión que se desee
     * 
     * @param pressión máxima
     * @param presión actual
     */
    public Wheel(double maxPressure,double pressure)
    {
        this(); //para cuando se aumente el número de atributos de la clase
        setMaxPressure(maxPressure);
        setPressure(pressure);
    }

    /**
     * Método que modifica la presión máxima de la rueda
     * 
     * @param  nuevo valor para la presión máxima (>=0), de tipo double  
     *  
     */
    private void setMaxPressure(double maxPressure)
    {
        checkParam(maxPressure >= 0,"Presión máxima debe ser positiva o 0");
        this.maxPressure = maxPressure;
    }

    /**
     * Método que modifica la presión actual
     * 
     * @param  nuevo valor para la presión actual entre [0,maxPressure], de tipo double
     */
    private void setPressure(double newPressure)
    {
        checkParam(newPressure >= 0 && newPressure <= getMaxPressure(),"El valor de la presión debe estar entre el 0 y la presión máxima");
        pressure = newPressure;
    }

    /**
     * Método que devuelve la presión máxima
     * 
     * @return presión máxima que puede soportar la rueda, de tipo double
     */
    public double getMaxPressure()
    {
        return maxPressure;
    }

    /**
    /**
     * Método que devuelve la presión actual
     * 
     * @return presión actual que puede soportar la rueda, de tipo double
     */
    public double getPressure()
    {
        return pressure;
    }

    /**
     * Método qee comprueba si una rueda está o no operativa
     * Está operativa si su presión actual es mayor o igual que el 85% del la presión máxima
     * 
     * @return true si la rueda está operativa y false si no lo es
     */
    public boolean test()
    {
        // if (getPressure() >= THRESHOLD /100 *getMaxPressure()){
        // return true;
        // } else{
        // return false;
        // }

        //Alternativa:
        return getPressure() >= THRESHOLD / 100 * getMaxPressure();
    }

    /**
     * Devuelve el estado de la rueda con el siguiente formato:
     * MaxP: 20700,0 Mb - Pressure: 19300,0 Mb - Percentage: 93,24 - Test: true
     * 
     * @return estado de la rueda con el formato anterior
     */
    public String toString()
    {
        // usaremos  String.format para formatear números decimales
        String data = String.format("MaxP: %.1f Mb - Pressure: %.1f Mb - Percentage: %.2f - Test: %b",
                getMaxPressure(),
                getPressure(),
                percentage(),
                test());
        //el % es el formato(float=f;boolean=b;etc); el .1 y/o .2 indica el número de decimales
        //después hacemos como un toString normal
        return data;
    }

    /**
     * Imprime los datos de la rueda en el siguiente formato
     * 
     * Max Pressure....... 34500,0 Mb
     * Current Pressure... 32000,0 Mb (92,75%)
     * Test............... OK (FAIL, si falló el test).
     * 
     */
    public void print()
    {
        System.out.println(String.format("Max Pressure....... %.1f Mb",getMaxPressure()));
        System.out.println(String.format("Current Pressure... %.1f Mb (%.2f)",getPressure(), percentage()));
        System.out.println(String.format("Test............... %s",testToString()));
        //int=%d; String=%s
    }
    
    //****** métodos auxiliares
    /**
     * Valida el parámetro. Si no se cumple la condición
     * lanza IllegalArgumentException con mensaje recibido
     * @param condición a validar
     * @param mensaje causa del error
     */
    private void checkParam(boolean condition, String msg){
        if (!condition){
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Calcula el porcentaje de presión respecto a la presión máxima
     * @return porcentaje
     */
    private double percentage(){
        double percentage;
        percentage = pressure / maxPressure * 100;
        return percentage;
    }
    
    /**
     * Retorna el porcentaje de presión respecto a la presión máxima
     * @return porcentaje
     */
    public double getPercentage(){
        double percentage;
        percentage = pressure / maxPressure * 100;
        return percentage;
    }

    /**
     * @return Devuelve OK o FAIL en función del resultado del test
     */
    private String testToString(){
        if (test()){    //es lo mismo que test()==true
            return "OK";
        } else{
            return "FAIL";
        }
    }
}