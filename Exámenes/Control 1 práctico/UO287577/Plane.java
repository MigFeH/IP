
/**
 * Clase Plane, describe un avión con piloto, identificador 
 * y combustible que podrá volar
 * 
 * @author Miguel 
 * @version 6-10-21
 */
public class Plane
{
    public static final int MIN_FUEL = 0;
    public static final int MAX_FUEL = 5000;

    public static final Person DEFAULT_PILOT = null;
    public static final char DEFAULT_IDENTIFIER = 'A';
    public static final int DEFAULT_FUEL = 0;

    private Person pilot;       //piloto para el avión 
    //atributos en privado

    private char identifier;    //identificador para el avión entre 'A' y 'Z'
    //el char sirve para referenciar un único carácter

    private int fuel;           //combustible para el avión [0,5000]
    
    private boolean canBePilot;  //condición de si la persona puede ser piloto

    /**
     * Constructor for objects of class Plane
     */
    public Plane()
    {
        setPilot(DEFAULT_PILOT);
        setIdentifier(DEFAULT_IDENTIFIER);
        setFuel(DEFAULT_FUEL);

    }

    /**
     * Constructor de la clase Plane con solo identificador como único parámetro
     */
    public Plane(char identifier)
    {
        this();
        setIdentifier(identifier); //valor de identificador default
    }

    /**
     * Constructor de la clase Plane con solo pilot (de la clase person) como único parámetro
     */
    public Plane(Person pilot1)
    {
        this();
        setPilot(pilot); //valor de piloto default
    }

    /**
     * Constructor de la clase Plane con solo fuel como único parámetro
     */
    public Plane(int fuel)
    {
        this();
        setFuel(fuel); //valor de fuel default
    }

    // /**
    // * todos los valores por defecto excepto el piloto
    // * @param pilot piloto para el avión
    // */
    // public Plane(Person pilot) {
    // this(); //con el this ejecutamos el setidentifier y el setfuel del constructor plane al ser en ambos metodos comunes
    // setPilot(pilot); //y al ser el pilot el único distinto del constructor del plane, cambiamos su estado

    // }

    // /**
    // * Modifica el valor del atributo pilot
    // * 
    // * @param  pilot, nuevo piloto para el avión 
    // * 
    // */
    // private void setPilot(Person pilot)   //los set no devuelven nada
    // //ahora los set serán métodos pivados a menos que nos pidan lo contrario
    // //el parámetro es un objeto de otra clase
    // {
    // //checkParam(pilot != null, "Se ha recibido null"); //comprobación de que pilot tiene un nombre distinto de null
    // this.pilot = pilot; 
    // }

    private void checkParam(boolean condition, String msg) {
        if (!condition) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Modifica el valor del atributo identifier
     * 
     * @param  identifier, nuevo identificador entre 'A' y 'Z' 
     * 
     */
    private void setIdentifier(char identifier)   //los set no devuelven nada

    {
        checkParam(identifier >= 'A' && identifier <= 'Z', "Carácter debe estar entre A y Z");
        this.identifier = identifier; 
    }

    /**
     * Modifica el valor del atributo fuel
     * 
     * @param  fuel, nuevo combustible entre [0,5000] 
     * 
     */
    private void setFuel(int fuel)   //los set no devuelven nada

    {
        checkParam(fuel >= MIN_FUEL && fuel <= MAX_FUEL,"Caracter debe estar comprendido entre 0 y 5000" );
        this.fuel=fuel; 
    }

    /**
     * @return valor del atributo pilot
     */
    public Person getPilot(){
        return pilot;
    }

    /**
     * @return valor del atributo identifier
     */
    public char getIdentifier(){
        return identifier;
    }

    /**
     * @return valor del atributo fuel
     */
    public int getFuel(){
        return fuel;
    }

    /**
     * Permite volar el avión si tiene combustible 
     * decrementando en 1 el combustible
     * @return true si ha podido volar y false si no
     */
    public boolean fly() {
        if(getFuel() > MIN_FUEL) {
            setFuel(getFuel() -1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Modifica el valor del atributo pilot que hará que el su valor sea null
     * y así el avión no tendrá piloto
     * 
     * @param  pilot, nuevo piloto para el avión 
     * 
     */
    private void setPilot(Person pilot)   //los set no devuelven nada
    //ahora los set serán métodos pivados a menos que nos pidan lo contrario
    //el parámetro es un objeto de otra clase
    {
        checkParam(pilot == null,"Esta persona no puede ser piloto");
        checkParam(canBePilot(),"Esta persona no puede ser piloto");
        //comprobación de que el nombre de pilot es null y que dicho piloto puede serlo
        this.pilot = pilot; 
    }

    /**
     * El método canBePilot indicará si un piloto cumple las condiciones para serlo
     * Devolverá verdadero si la persona puede ser piloto, y devolverá falso si la persona
     * no puede ser piloto.
     * 
     * Los criterios para poder ser piloto son los siguientes:
     * 1-Los hombres pueden pilotar siendo mayores de 30 y menores de 50 años (sin incluir)
     * 2-Las mujeres pueden pilotar siendo mayores de edad y no estando jubiladas
     * 
     * 
     * @return true o false
     */
    public boolean canBePilot()
    {
        Person p1 = new Person();
        if (p1.GENDER_MALE && (p1.getAge() < 50 && p1.getAge() >= 30)){  /*si la persona es hombre y es menor de 50 años (sin incluir) y si la persona es mujer y su edad es menor que la de jubilación*/
            return true;
        }
        else if (p1.GENDER_FEMALE && (p1.getAge() < 65 && p1.getAge() > 18)){
            return true;
        }
        else{
            return false; 
        }
    }
}
