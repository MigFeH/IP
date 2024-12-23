//import java.util.Random; //con esto importamos de la biblioteca java el método random
/**
 * Clase Person define una persona con nombre y edad.
 * Se define los métodos set y get para cada atributo
 *Para la sesión 1
 * 
 * @author (Miguel) 
 * @version 15-9-2021
 */
public class Person
{
    //def. de atributos constantes
    public static final int MIN_AGE = 0;            //edad de nacimiento de una persona en años
    public static final int MAX_AGE = 120;

    public static final int ADULTHOOD_AGE = 18;     //edad adulta de una persona
    public static final int RETIREMENT_AGE = 65;    //edad de jubilación de una persona

    public static final boolean GENDER_FEMALE = false;
    public static final boolean GENDER_MALE = true;

    public static final String DEFAULT_NAME = "Fernando"; //valor de nombre por defecto

    public static final String DEFAULT_SURNAME = "Alonso";
    public static final int DEFAULT_AGE = 40;
    public static final boolean DEFAULT_GENDER = GENDER_MALE;

    //variable de una instancia
    private String name;        //nombre de la persona
    private int age;            //edad de la persona
    private String surname;     //apellido de la persona
    private boolean gender;     //género de la persona
    // private int criticalAge;
    // private String hashCode;
    // private String stage;
    // private String discount;       //el descuento del precio de los productos de la tienda
    /**
     * Constructor con valores por defecto
     */
    public Person()
    {
        //name = "Fernando";
        setName(DEFAULT_NAME);
        setAge(DEFAULT_AGE);    //un parámetro puede ser una llamada al método
        setSurname (DEFAULT_SURNAME);
        setGender (GENDER_MALE);   //Se asignara true como masculino, y false como femenino

    }

    // /**
    // * @return número aleatorio para la edad rango [0,120]
    // */
    // private int randomAge(){
    // Random ageGenerator = new Random(); //declara un objeto llamado ageGenerator de tipo random
    // //cuando usamos una clase de java, hay que importarla al principio del código
    // return ageGenerator.nextInt(120); //genera un aleatorio entre 0 (inclusive) y 120 (exclusive)
    // }

    /**
     * Constructor con parámetro la edad, el resto de datos los valores por defecto
     * @param age, edad de la persona
     */
    public Person(int age)
    {
        //name = "Fernando";
        //setName("Fernando");
        this();
        setAge(age);
        //setSurname ("Alonso");
        //setGender (true); //Se asignara true como masculino, y false como femenino

    }
    
    /**
     * Constructor con parámetro la edad, el nombre, el apellido y el género
     * @param age,name,surname y gender 
    */
    public Person(int age,String name,String surname,boolean gender)
    {
        setAge(age);
        setName(name);
        setSurname(surname);
        setGender(gender);
    }

    /**
     * Modifica el valor del atributo name
     * 
     * @param  newName   nuevo valor para name
     *
     */
    public void setName(String name) //los Strings son objetos. Dentro de una variable como name, ésta contiene un String que es un OBJETO, por tanto, está la referencia (un número correspondiente a una dirección de memoria)
    //por tanto name es una referencia
    //cuando se crea un objeto, se le pone null hasta que le pongamos, por ejemplo, setName (por ser tipo String)

    {
        //checkParam(name != null, "Esperaba una cadena"); //Revisa que el valor de name sea distinto de null
        this.name = name;
    }

    /**
     * Devuelve el valor del atributo name
     *
     * 
     * @return     el valor del atributo name, de tipo String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Modifica el valor del atributo age
     * 
     * @param  newAge   nuevo valor para age
     *
     */
    public void setAge(int age)
    {
        checkParam(age >= MIN_AGE, "La edad no puede ser negativa" );
        checkParam(age < MAX_AGE, "La edad debe ser menor que 120"); 
        this.age = age;     //this se refiere al atributo (cuando el nombre del parámetro es igual al del atributo)
    }

    /**
     * Comprueba si la condición es correcta. Si no lo es, lanza excepción
     *
     * @param condition condición a comprobar
     * 
     */
    private void checkParam(boolean condition)
    {
        if (! condition ) {
            throw new IllegalArgumentException("Parámetro incorrecto");
        }
    }

    /**
     * Comprueba si la condición es correcta. Si no lo es, lanza excepción
     * con el mensaje recibido como parámetro
     * @param condition condición a comprobar
     * @param msg, mensaje para la excepción
     * 
     */
    private void checkParam(boolean condition, String msg)
    {
        if (! condition ) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Devuelve el valor del atributo age
     *
     * 
     * @return     el valor del atributo age, de tipo int
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Modifica el valor del atributo surname
     * 
     * @param  newSurname nuevo valor para surname, de tipo String
     *
     */
    public void setSurname(String surname)
    {
        checkParam(surname != null, "Esperaba una cadena");
        this.surname = surname;
    }

    /**
     * Devuelve el valor del atributo surname 
     *
     * 
     * @return     el valor del atributo surname, de tipo String
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Modifica el valor del atributo gender
     * 
     * @param  newGender nuevo valor para gender, de tipo boolean
     *
     */
    public void setGender (boolean gender)
    {
        this.gender = gender;
    }

    /**
     * Devuelve el valor del atributo gender 
     *
     * 
     * @return     el valor del atributo gender, de tipo boolean
     */
    public boolean getGender()
    {
        return gender;
    }

    /**
     * Imprime por pantalla nombre y edad de la persona con el siguiente
     * formato
     * "Me llamo Fernando y tengo 40 años"
     * Además imprime una línea con todos los datos de la persona
     * Datos de la persona: FERNANDO-Alonso-40-MASCULINO (mismo formato de toString)
     *
     */
    public void print()
    {
        System.out.println("Me llamo " + getName() + " y tengo " + getAge() + " años");
        System.out.println("Y el año que viene tendré " + (age + 1) + " años");

        System.out.println("Datos de la persona: " + this.toString() ); //es quivalente con y sin el this
    }

    /**
     * Devuelve los datos de la persona con el siguiente formato:
     * nombre(en mayúsculas)-apellido-edad-MASCULINO (o FEMENINO)
     * EJEMPLO
     * FERNANDO-Alonso-40-MASCULINO
     * 
     * 
     * @return     cadena con los datos de la persona en el formato anterior
     */
    public String toString()
    {
        String result = name.toUpperCase() + "-" + this.getSurname() + "-" + this.getAge() + "-" + "-" + genderToString();
        return result;
    }

    /**
     * El método getCriticalAge comprobará la edad de la persona 
     * e indicará si es menor de edad, los años que le quedan 
     * para alcanzar los 18 años; si esa persona tiene entre 18 y 64 años,
     * indicará los años que le quedan para jubilarse (sobre los 65 años);
     * y si esa persona ya se ha jubilado, indicará hace cuanto que se ha jubilado.
     * 
     * @return  los años que correspondería a cada situación de las condiciones antes mencionadas
     */
    public int getCriticalAge()
    {
        int result = age;
        if (age < ADULTHOOD_AGE) { 
            result = (ADULTHOOD_AGE - age); //si es menor de edad, que reste la mayoría de edad menos la edad de la persona 
        }
        if (ADULTHOOD_AGE<= age) {
            result = (age - RETIREMENT_AGE); //si es mayor de edad y no está jubilado (65 años), que reste la edad de jubilación menos la edad de la persona
        }
        if (age >= RETIREMENT_AGE) {
             result = (age - RETIREMENT_AGE); //si ya está jubilado, que le reste a la edad que la persona tiene, la edad de jubilación (65 años)
        }
        return result;
    }

    /**
     * Devuelve "MASCULINO" o "FEMENINO" según sea el género de la persona
     * @return cadena con mensaje "MASCULINO" o "FEMENINO"
     */
    private String genderToString() {
        if (gender == GENDER_FEMALE) {
            return "FEMENINO";
        } else {
            return "MASCULINO";
        }
    }

    // /**
    // * El método getHashCode devolverá un código de tipo String a partir
    // * de los valores de los atributos con el formato:
    // * 
    // * Edad - NOMBRE - longitud del nombre - APELLIDO - longitud del apellido
    // *
    // * @return una cadena de mensaje
    // */
    // public String getHashCode()
    // {
    // String result = getAge() + "-" + name.toUpperCase() + "-" 
    // + lengthOfName() + "-" + surname.toUpperCase() + "-"
    // + lengthOfSurname();

    // return result;
    // }

    /**
     * El método getHashCode devolverá un código de tipo String a partir
     * de los valores de los atributos con el formato:
     * 
     * Edad - Primeras dos letras del nombre en mayúsculas - primeras cuatro letras de los apellidos en 
     * mayúsculas - el texto "CHILD","ADULT" o "RETIRED", dependiendo de la edad de la persona;
     * esto último será hecho a partir de un nuevo método
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
     * Asigna a la persona el texto "CHILD" o "ADULT" o "RETIRED" en función
     * de la edad que tenga
     *
     * @return la stage en la que la persona se encuentra
     */
    private String stage()
    {
        String result;
        if(getAge() < ADULTHOOD_AGE) {
            result= "CHILD";
        } else if (getAge() >= ADULTHOOD_AGE){
            result = "ADULT";
        } else {
            result = "RETIRED";
        }
        return result;
    }

    /**
     * el método lengthOfName, de la clase String, se encargará de contar los caracteres
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
     * Devuelve true si el nombre que recibe como parámetro 
     * es igual al nombre de la persona
     * @param nombre a comparar
     * @return true si coincide con el propio
     */
    public boolean isYourName(String nameToCompare) {
        //el metodo recibe un nombre y lo compara con el atributo name
        //si son iguales, devuelve true, sino false

        // if (getName().equals(nameToCompare)){     //comparación de dos nombres. En los números enteros, si es comparación, no se usa el == (sirve cuando es un número) en vez del =
        // // para la comparación de dos nombres usamos el equals
        // return true;
        // } else {
        // return false;
        // }

        //Alternativa:
        return getName().equals(nameToCompare);
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
     * Devuelve el valor de discount, de tipo String
     *
     * @return el porcentaje del descuento que recibe la persona
     */
    public int getDiscount()
    {
        int discount = 0;
        if(getAge() >= RETIREMENT_AGE){ //si la edad de la persona es mayor o igual a la edad de jubilación...
            discount = 10;
        } else if(getAge() < ADULTHOOD_AGE && getAge() >= 12){ //si la edad de la persona es menor que la edad de la mayoría de edad y es mayor o igual a los 12 años...
            discount = 5;
        } else if(getAge() < 12){ /*si la edad de la persona es menor que 12...*/
            discount = 15;
        }
        return discount;
    }
    
    
}