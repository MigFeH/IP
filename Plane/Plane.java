
/**
 * Clase Plane, describe un avión con piloto, identificador y combustible que podrá volar
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

    public static final int MAX_X_POS = 10;     //Posición máxima del avión en el eje de coordenadas de las x
    public static final int MIN_X_POS = 0;      //Posición mínima del avión en el eje de coordenadas de las x

    public static final int MAX_Y_POS = 10;     //Posición máxima del avión en el eje de coordenadas de las y
    public static final int MIN_Y_POS = 0;      //Posición mínima del avión en el eje de coordenadas de las y

    public static final int MAX_X_SPEED = 1;    //Velocidad máxima del avión en el eje de coordenadas de las x
    public static final int MIN_X_SPEED = -1;   //Velocidad mínima del avión en el eje de coordenadas de las x

    public static final int MAX_Y_SPEED = 1;    //Velocidad máxima del avión en el eje de coordenadas de las y
    public static final int MIN_Y_SPEED = -1;   //Velocidad mínima del avión en el eje de coordenadas de las y

    private Person pilot;       //piloto para el avión 
    //atributos en privado

    private char identifier;    //identificador para el avión entre 'A' y 'Z'
    //el char sirve para referenciar un único carácter

    private int fuel;           //combustible para el avión [0,5000]

    private int xPos;           //Posición del avión en el eje de las x 
    private int yPos;           //Posición del avión en el eje de las y 

    private int xSpeed;         //Velocidad del avión en el eje de las x
    private int ySpeed;         //Velocidad del avión en el eje de las y
    
    private LandingGear landingGear;
    /**
     * Constructor for objects of class Plane
     */
    public Plane()
    {
        setPilot(DEFAULT_PILOT);
        setIdentifier(DEFAULT_IDENTIFIER);
        setFuel(DEFAULT_FUEL);
        
        setXPos(MIN_X_POS);
        setYPos(MIN_Y_POS);
        
        setXSpeed(MIN_X_SPEED);
        setYSpeed(MIN_Y_SPEED);
        
        setLandingGear(new LandingGear());
    }
    
    /**
     * Constructor de la clase Plane con la posición del avión como parámetros
     * 
     * @param xPos, posición del avión en el eje x
     * @param yPos, posición del avión en el eje y
     */
    public Plane(int xPos, int yPos)
    {
        this();
        
        setXPos(xPos);
        setYPos(yPos);
        
        setXSpeed(MAX_X_SPEED);
        setYSpeed(MAX_Y_SPEED);
        
        setFuel(50);
    }
    
    /**
     * Constructor de la clase Plane con la posición del avión y pilot (de la clase Person) como parámetros
     * 
     * @param xPos, posición del avión en el eje x
     * @param yPos, posición del avión en el eje y
     * @param pilot, piloto del avión
     */
    public Plane(int xPos, int yPos, Person pilot)
    {
        this();
        
        setXPos(xPos);
        setYPos(yPos);
        
        setPilot(pilot);
        
        setXSpeed(MAX_X_SPEED);
        setYSpeed(MAX_Y_SPEED);
        
        setFuel(50);
    }
    
    /**
     * Constructor de la clase Plane con solo identificador como único parámetro
     * 
     * @param identifier, identificador del avión
     */
    public Plane(char identifier)
    {
        this();
        setIdentifier(identifier);
    }

    /**
     * Constructor de la clase Plane con solo pilot (de la clase person) como único parámetro
     * 
     * @param pilot, el piloto del avión
     */
    public Plane(Person pilot)
    {
        this();
        setPilot(pilot);
    }

    /**
     * Constructor de la clase Plane con solo fuel como único parámetro
     * 
     * @param fuel, el combustible del avión
     */
    public Plane(int fuel)
    {
        this();
        setFuel(fuel);
    }

    /**
     * Constructor de la clase Plane con tres parámetros
     * 
     * @param pilot, el piloto del avión
     * @param identifier, el identificador del avión
     * @param fuel, el combustible del avión
     */
    public Plane(Person pilot,char identifier,int fuel)
    {
        this();
        setPilot(pilot);
        setIdentifier(identifier);
        setFuel(fuel);
    }
    
    /**
     * Constructor de la clase Plane con Identificador,fuel,xPos,yPos,xSpeed,ySpeed como parámetros
     * 
     * @param identifier, identificador 
     * @param fuel, combustible
     * @param xPos, posición en el eje x 
     * @param yPos, posición en el eje y
     * @param xSpeed, velocidad en el eje x
     * @param ySpeed, velocidad en el eje y
     */
    public Plane(char identifier, int fuel, int xPos, int yPos, int xSpeed, int ySpeed)
    {
        this();
        setIdentifier(identifier);
        
        setFuel(fuel);
        
        setXPos(xPos);
        setYPos(yPos);
        
        setXSpeed(xSpeed);
        setYSpeed(ySpeed);
    }
    
    /**
     * Modifica el valor del atributo pilot
     * 
     * @param  pilot, nuevo piloto para el avión 
     */
    private void setPilot(Person pilot)
    {
        this.pilot = pilot; 
    }

    private void checkParam(boolean condition, String msg) 
    {
        if (! condition) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Modifica el valor del atributo identifier
     * 
     * @param  identifier, nuevo identificador entre 'A' y 'Z'
     */
    private void setIdentifier(char identifier)
    {
        checkParam(identifier >= 'A' && identifier <= 'Z', "Carácter debe estar entre A y Z");
        this.identifier = identifier; 
    }

    /**
     * Modifica el valor del atributo fuel
     * 
     * @param  fuel, nuevo combustible entre [0,5000] 
     */
    private void setFuel(int fuel)
    {
        checkParam(fuel >= MIN_FUEL && fuel <= MAX_FUEL,"Caracter debe estar comprendido entre 0 y 5000" );
        this.fuel = fuel; 
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
     * Modifica el valor del atributo landingGear
     * 
     * @param  newLandingGear
     */
    private void setLandingGear(LandingGear newLg)  

    {
        checkParam(newLg != null, "Esperaba un tren de aterrizaje");
        newLg = landingGear; 
    }
    
    /**
     * Retorna landingGear
     * 
     * @return landingGear
     */
    public LandingGear getLandingGear(){
        return landingGear;
    }

    /**
     * Permite volar el avión si tiene combustible 
     * decrementando en 1 el combustible
     * 
     * @return true si ha podido volar y false si no
     */
    public boolean fly() {
        checkParam(xPos <= MAX_X_POS, "La nueva coordenada no puede ser superior a la máxima permitida de 10");
        checkParam(xPos >= MIN_X_POS, "La nueva coordenada no puede ser inferior a la mínima permitida de 0");
        
        checkParam(yPos <= MAX_Y_POS, "La nueva altura no puede ser superior a la máxima permitida de 10");
        checkParam(yPos >= MIN_Y_POS, "La nueva altura no puede ser inferior a la mínima permitida de 0");
        
        if(getFuel() > MIN_FUEL) {
            setFuel(getFuel() - 1);
            if((getXPos() < MAX_X_POS && getXPos() >= MIN_X_POS) 
                && (getYPos() < MAX_Y_POS && getYPos() >= MIN_Y_POS))
            {
                this.xPos = xPos + getXSpeed();     
                this.yPos = yPos + getYSpeed();
                return true;
            } 
            else{
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * El método toString devolverá los valores de las
     * propiedades siguientes separadas por guiones:
     * 
     * identifier
     * fuel
     * 
     * Si el avión no tiene asignado piloto, la cadena “NO PILOT”. En
     * caso contrario, la cadena que devuelve el método getHashCode()
     * de la clase Person.
     * 
     * xPosition
     * yPosition
     * 
     * xSpeed
     * ySpeed 
     *
     * Ejemplo: A-300-NO PILOT-(3,5)-(-1,0)
     *
     * @return el resultado del toString
     */
    public String toString()
    {
        if(getPilot() == null){
            String result = getIdentifier() 
                            + "-" + getFuel() + "-" + "NO PILOT" + "-" + "(" +
                            getXPos() + "," + getYPos() + ")" + "-" + "(" +
                            getXSpeed() + "," + getYSpeed() + ")";
            return result;
        } else{
            String result = getIdentifier() + "-" + getFuel() + "-" + pilot.getHashCode()
                            + "-" + "(" +
                            getXPos() + "," + getYPos() + ")" + "-" + "(" +
                            getXSpeed() + "," + getYSpeed() + ")";
            return result;
        }
    }

    /**
     * Modifica el valor del atributo xPos de tipo int
     *
     * @param la posición del avión en el eje de coordenadas x
     */
    private void setXPos(int xPos)
    {
        checkParam(xPos <= MAX_X_POS, "La nueva coordenada no puede ser superior a la máxima permitida de 10");
        checkParam(xPos >= MIN_X_POS, "La nueva coordenada no puede ser inferior a la mínima permitida de 0");
        this.xPos = xPos;
    }

    /**
     * Devuelve el valor del atributo xPos de tipo int
     *
     * @return el valor de xPos
     */
    public int getXPos()
    {
        return xPos;
    }

    /**
     * Modifica el valor del atributo yPos de tipo int
     *
     * @param la posición del avión en el eje de coordenadas y
     */
    private void setYPos(int yPos)
    {
        checkParam(yPos <= MAX_Y_POS, "La nueva altura no puede ser superior a la máxima permitida de 10");
        checkParam(yPos >= MIN_Y_POS, "La nueva altura no puede ser inferior a la mínima permitida de 0");
        this.yPos = yPos;
    }

    /**
     * Devuelve el valor del atributo yPos de tipo int
     *
     * @return el valor de yPos
     */
    public int getYPos()
    {
        return yPos;
    }

    /**
     * Modifica el valor del atributo xSpeed de tipo int
     *
     * @param la velocidad del avión en el eje de coordenadas de las x
     */
    private void setXSpeed(int xSpeed)
    {
        checkParam(xSpeed <= MAX_X_SPEED, "La nueva velocidad horizontal no puede ser superior a la máxima permitida de 1");
        checkParam(xSpeed >= MIN_X_SPEED, "La nueva velocidad horizontal no puede ser inferior a la mínima permitida de -1");
        this.xSpeed = xSpeed;
    }

    /**
     * Devuelve el valor del atributo xSpeed de tipo int
     *
     * @return el valor de xSpeed
     */
    public int getXSpeed()
    {
        return xSpeed;
    }

    /**
     * Modifica el valor del atributo ySpeed de tipo int
     *
     * @param la velocidad del avión en el eje de coordenadas de las y
     */
    private void setYSpeed(int ySpeed)
    {
        checkParam(ySpeed <= MAX_Y_SPEED, "La nueva velocidad vertical no puede ser superior a la máxima permitida de 1");
        checkParam(ySpeed >= MIN_Y_SPEED, "La nueva velocidad vertical no puede ser inferior a la mínima permitida de -1");
        this.ySpeed = ySpeed;
    }

    /**
     * Devuelve el valor del atributo ySpeed de tipo int
     *
     * @return el valor de ySpeed
     */
    public int getYSpeed()
    {
        return ySpeed;
    }

    /**
     * El método accelerate le asigna al atributo xSpeed e ySpeed
     * nuevos valores para modificar la velocidad relativa del avión.
     * Si uno de los parámetros introducidos no son correctos,
     * no se cambian los atributos
     *
     * @param  la velocidad del avión tanto horizontal como verticalmene
     */
    public void accelerate(int xSpeed,int ySpeed)
    {
        if (areParametersOK(xSpeed, ySpeed)){
            this.xSpeed = xSpeed;
            this.ySpeed = ySpeed; 
        }
    }
    
    private boolean areParametersOK(int xSpeed, int ySpeed) {
        return (xSpeed >= MIN_X_SPEED && xSpeed <= MAX_X_SPEED)
            && (ySpeed >= MIN_Y_SPEED && ySpeed <= MAX_Y_SPEED);
    }
}