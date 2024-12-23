import java.util.Random;
/**
 * Clase Person define una persona con nombre y edad
 * 
 * @author Miguel
 * @version 15-9-2021
 */
public class Person
{
    //constantes//
    public static final int MIN_AGE = 0;    //edad mínima de una persona
    public static final int MAX_AGE = 120; //edad máxima de una persona

    public static final int ADULTHOOD_AGE = 18;     //edad adulta de una persona
    public static final int RETIREMENT_AGE = 65;    //edad de jubilación de una persona

    public static final boolean GENDER_FEMALE = false;  //género femenino de una persona asignado a false
    public static final boolean GENDER_MALE = true;     //género masculino de una persona asignado a true

    public static final String DEFAULT_NAME = "Fernando";   //nombre por defecto de una persona
    public static final String DEFAULT_SURNAME = "Alonso"; //apellido por defecto de una persona

    public static final int DEFAULT_AGE = 40;   //edad por defecto de una persona
    
    public static final boolean DEFAULT_GENDER = GENDER_MALE;   //género masculino por defecto de una persona
    
    public static final String FEMALE_NAMES[] = {"Mary","Laura","Andrea","Mariana"}; //array constante de nombres de mujeres
    public static final String MALE_NAMES[] = {"Miguel","Daniel","Luisma","Néstor"}; //array constante de nombres de hombres
    
    public static final String SURNAMES[] = {"Fernández","Begega","Huerta","Díaz"}; //array constante de apellidos
    
    
    //variables de instancia//
    private String name;        //nombre de una persona
    private int age;            //edad de una persona
    private String surname;     //apellido de una persona
    private boolean gender;     //género de una persona
    /**
     * Constructor de la clase Person sin parámetros
     */
    public Person()
    {
        //creación del objeto de la clase Random
        Random rd = new Random();
        
        //asignación del género aleatoriamente
        setGender(rd.nextBoolean());
        
        //asignación de nombres aleatorios a partir del resultado de gender
        if(getGender()){
            setName(MALE_NAMES[rd.nextInt(MALE_NAMES.length)]);
        } else{
            setName(FEMALE_NAMES[rd.nextInt(FEMALE_NAMES.length)]);
        }
        
        //asignación de apellido aleatoriamente
        setSurname(SURNAMES[rd.nextInt(SURNAMES.length)]);
        
        //asignación de edad aleatoriamente
        setAge(rd.nextInt(MAX_AGE));
    }
    
    /**
     * Constructor de la clase Person que recibe el género
     * El resto de los datos se quedan con el valor por defecto
     * 
     * @param gender, GENDER_MALE o GENDER_FEMALE
     */
    public Person(boolean gender)
    {
        this();
        setGender(gender);
        setName(gender); //asigna un nombre dado el género que reciba
    }
    
    /**
     * Constructor de la clase Person con la edad como parámetro
     * 
     * @param age, edad de la persona
     */
    public Person(int age)
    {
        this();
        setAge(age);
    }
    
    //métodos//

    /**
     * Modifica el valor del atributo name
     * 
     * @param name, nombre a establecer para la persona
     */
    private void setName(String name)
    {
        checkParam(name != null, "Esperaba una cadena");
        this.name = name;
    }
    
    /**
     * Asigna un nombre en función del género recibido
     * 
     * @param gender, el género de la persona 
     */
    private void setName(boolean gender)
    {
        Random rd = new Random();
        if(gender == GENDER_MALE)
        {
            setName(MALE_NAMES[rd.nextInt(MALE_NAMES.length)]);
        } else{
            setName(FEMALE_NAMES[rd.nextInt(FEMALE_NAMES.length)]);
        }
    }

    /**
     * Retorna el valor del atributo name
     * 
     * @return el nombre de la persona
     */
    public String getName()
    {
        return name;
    }

    /**
     * Modifica el valor del atributo age
     * 
     * @param age, edad a establecer en la persona
     */
    private void setAge(int age)
    {
        checkParam(age >= MIN_AGE, "La edad no puede ser negativa" );
        checkParam(age < MAX_AGE, "La edad debe ser menor que 120"); 
        this.age = age;
    }

    /**
     * Retorna el valor almacenado en el atributo age
     * 
     * @return el valor del atributo age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Modifica el valor del atributo surname
     * 
     * @param surname, nuevo apellido para la persona
     */
    private void setSurname(String surname)
    {
        checkParam(surname != null, "Esperaba una cadena");
        this.surname = surname;
    }

    /**
     * Retorna el valor almacenado en el atributo surname 
     *
     * @return el valor almacenado en el atributo surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Modifica el valor del atributo gender
     * 
     * @param gender, nuevo valor para gender
     */
    private void setGender (boolean gender)
    {
        this.gender = gender;
    }

    /**
     * Retorna el valor almacenado en el atributo gender 
     *
     * @return el género de la persona
     */
    public boolean getGender()
    {
        return gender;
    }

    /**
     * Imprime por pantalla nombre y edad de la persona 
     * con el siguiente formato:
     * 
     * "Me llamo Fernando y tengo 40 años"
     * 
     * Además imprime una línea con todos los datos de la persona
     * Datos de la persona: FERNANDO-Alonso-40-MASCULINO (mismo formato de toString)
     */
    public void print()
    {
        System.out.println("Me llamo " + getName() + " y tengo " + getAge() + " años");
        System.out.println("Y el año que viene tendré " + (age + 1) + " años");

        System.out.println("Datos de la persona: " + this.toString() ); //es quivalente con y sin el this
    }

    /**
     * Devuelve los datos de la persona con el siguiente formato:
     * 
     * nombre(en mayúsculas)-apellido-edad-MASCULINO (o FEMENINO)
     * 
     * EJEMPLO:
     * FERNANDO-Alonso-40-MASCULINO 
     * 
     * @return cadena con los datos de la persona en el formato anterior
     */
    public String toString()
    {
        String result = name.toUpperCase() + "-" + this.getSurname() + "-" 
            + this.getAge() + "-" + genderToString();
        return result;
    }

    /**
     * El método getCriticalAge comprobará la edad de la persona 
     * e indicará:
     * 
     * Si es menor de edad, los años que le quedan para alcanzar los 18 años; 
     * 
     * Si esa persona tiene entre 18 y 64 años, indicará los años que le quedan
     * para jubilarse (sobre los 65 años);
     * 
     * Si esa persona ya se ha jubilado, indicará hace cuanto que se ha jubilado
     * 
     * @return los años que correspondería a cada situación de las condiciones anteriores
     */
    public int getCriticalAge()
    {
        int discount = 0;
        
        if (age < ADULTHOOD_AGE) { 
            discount = (ADULTHOOD_AGE - age); //si es menor de edad... 
        }
        
        if (ADULTHOOD_AGE <= age) {
            discount = (age - RETIREMENT_AGE); //si es mayor de edad y no está jubilado...
        }
        
        if (age >= RETIREMENT_AGE) {
            discount = (age - RETIREMENT_AGE); //si ya está jubilado...
        }
        
        return discount;
    }
    
    /**
     * El método getHashCode devolverá un código de tipo String a partir
     * de los valores de los atributos con el formato:
     * 
     * Edad - Primeras dos letras del nombre en mayúsculas - primeras cuatro 
     * letras de los apellidos en mayúsculas - el texto "CHILD","ADULT" o "RETIRED",
     * dependiendo de la edad de la persona;
     *
     * @return una cadena de mensaje
     */
    public String getHashCode()
    {
        String result = getAge() + "-" + shortName().toUpperCase() + "-" 
            + shortSurname().toUpperCase() + "-" + stage() ;
        return result;
    }
    
    /**
     * Devuelve true si el nombre que recibe como parámetro 
     * es igual al nombre de la persona
     * 
     * @param nombre a comparar
     * @return true si coincide con el propio
     */
    public boolean isYourName(String nameToCompare) {
        // if (getName().equals(nameToCompare)){     //comparación de dos nombres. En los números enteros, si es comparación, no se usa el == (sirve cuando es un número) en vez del =
        // // para la comparación de dos nombres usamos el equals
        // return true;
        // } else {
        // return false;
        // }

        //Alternativa:
        return getName().equals(nameToCompare);
    }
    
    //métodos auxiliares//
    
    /**
     * Comprueba si la condición es correcta. 
     * Si no lo es, lanza excepción con el mensaje recibido como parámetro
     * 
     * @param condition condición a comprobar
     * @param msg, mensaje para la excepción
     */
    private void checkParam(boolean condition, String msg)
    {
        if (!condition ) {
            throw new IllegalArgumentException(msg);
        }
    }
    
    /**
     * Comprueba si la condición es correcta. Si no lo es, lanza excepción
     *
     * @param condition condición a comprobar
     */
    private void checkParam(boolean condition)
    {
        if (! condition ) {
            throw new IllegalArgumentException("Parámetro incorrecto");
        }
    }
    
    /**
     * Devuelve "MASCULINO" o "FEMENINO" según sea el género de la persona
     * 
     * @return cadena con mensaje "MASCULINO" o "FEMENINO"
     */
    private String genderToString() {
        if (gender == GENDER_FEMALE) {
            return "FEMENINO";
        } else {
            return "MASCULINO";
        }
    }

    /**
     * Asigna a la persona el texto "CHILD" o "ADULT" o "RETIRED" en función
     * de la edad que tenga
     *
     * @return la stage en la que la persona se encuentra
     */
    private String stage()
    {
        String stage;
        if(getAge() < ADULTHOOD_AGE) {
            stage ="CHILD";
        } else if (getAge() >= ADULTHOOD_AGE){
            stage ="ADULT";
        } else {
            stage="RETIRED";
        }
        return stage;
    }

    /**
     * El método getStage retorna el resultado del método stage
     * 
     * @return el resultado del método
     */
    public String getStage()
    {
        return this.stage();
    }
    
    /**
     * El método lengthOfName, de la clase String, se encargará de contar los caracteres
     * del atributo name
     *
     * @return  el número de caracteres del String name
     */
    private int lengthOfName()
    {
        int length = name.length();
        return length;
    }

    /**
     * el método lengthOfSurname, de la clase String, se encargará de contar los caracteres
     * del atributo surname
     *
     * @return  el número de caracteres del String surname
     */
    private int lengthOfSurname()
    {
        int length = surname.length();
        return length;
    }

    /**
     * Método para recortar un apellido para darlo con los primeros 4 caracteres,
     * en caso de tener un número menor a 4, devolver apellido sin recortar
     *
     * @return el apellido recortado o no recortado en función del número de caracteres
     */
    private String shortSurname()
    {
        if(lengthOfSurname() >= 4){
            return surname.substring(0,4);   
        } else{
            return surname;
        }
    }

    /**
     * Método para recortar un nombre para darlo con los primeros 2 caracteres,
     * en caso de tener un número menor a 2, devolver nombre sin recortar
     *
     * @return el nombre recortado o no recortado en función del número de caracteres 
     */
    private String shortName()
    {
        if(lengthOfName() >= 2){
            return name.substring(0,2);   
        } else{
            return name;
        }
    }
    
    /**
     * El método getShortName retorna el resultado del método shortName
     * 
     * @return el resultado del método
     */
    public String getShortName()
    {
        return this.shortName();
    }
    
    /**
     * El método getShortSurname retorna el resultado del método shortSurname
     * 
     * @return el resultado del método
     */
    public String getShortSurname()
    {
        return this.shortSurname();
    }
}