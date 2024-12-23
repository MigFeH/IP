import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * Gestiona los asientos del avión
 * 
 * @author Miguel
 * @version 24-11-21
 */
public class SeatManager
{
    //constantes//
    public static final int MIN_FIRST_ROWS = 3; //número mínimo de filas de primera clase en el avión
    public static final int MIN_STANDARD_ROWS = 3; //número mínimo de filas de clase turista en el avión

    public static final int MAX_FIRST_ROWS = 10; //número máximo de filas de primera clase en el avión
    public static final int MAX_STANDARD_ROWS = 40; //número máximo de filas de clase turista en el avión

    public static final int COLUMNS = 6; //número de columnas de asientos del avión
    
    public static final int FIRST_CLASS = 1; //área número 1 de tipo de asientos del avión
    public static final int STANDARD_CLASS = 2; //área número 2 de tipo de asientos del avión
    public static final int ALL_CLASS = 3; //área número 3 de tipo de asientos del avión
    
    
    //variables de instancia//
    private int firstRows; //número de filas en primera
    private int standardRows; //número de filas en clase turista
    private Person[][] seats; //array bidimensional para la matriz de asientos, contiene los pasajeros

    /**
     * Constructor con parámetros de la clase SeatManager
     */
    public SeatManager(int first, int standard)
    {
        setFirstRows(first);
        setStandardRows(standard);
        setSeats(new Person[firstRows + standardRows][COLUMNS]);
    }

    //métodos//

    /**
     * Modifica el valor del atributo firstRows
     * 
     * @param first, número de filas en primera
     */
    private void setFirstRows(int first)
    {
        checkParam(first >= MIN_FIRST_ROWS && first <= MAX_FIRST_ROWS, "Número de filas en clase primera incorrecto");
        this.firstRows = first;
    }

    /**
     * Modifica el valor del atributo standardRows
     * 
     * @param standard, número de filas en clase turista
     */
    private void setStandardRows(int standard)
    {
        checkParam(standard >= MIN_STANDARD_ROWS && standard <= MAX_STANDARD_ROWS, "Número de filas en clase turista incorrecto");
        this.standardRows = standard;
    }

    /**
     * Modifica el valor del atributo seats (crea una matriz con x filas y columnas)
     * 
     * @param seats, nueva matriz de asientos 
     */
    private void setSeats(Person[][] seats)
    {
        checkParam(seats != null, "Null en lugar de matriz de pasajeros");

        checkParam(seats.length >= MIN_FIRST_ROWS + MIN_STANDARD_ROWS 
            && seats.length <= MAX_FIRST_ROWS + MAX_STANDARD_ROWS, "Número de filas de la matriz incorrecta");

        checkParam(seats[0].length == COLUMNS, "Número de asientos por fila incorrectos");

        this.seats = seats;
    }

    /**
     * Retorna el valor almacenado en el atributo firstRows
     * 
     * @return el valor almacenado en el atributo
     */
    public int getFirstRows()
    {
        return firstRows;
    }

    /**
     * Retorna el valor almacenado en el atributo standardRows
     * 
     * @return el valor almacenado en el atributo
     */
    public int getStandardRows()
    {
        return standardRows;
    }

    /**
     * Retorna la persona sentada (en la matriz bidimensional seats)
     * mediante una posicion en las filas y en las columnas como parámetros
     * 
     * @param row, número de fila del asiento de la persona a buscar
     * @param column, número de columna del asiento de la persona a buscar
     * @return la persona que ocupa el asiento seleccionado
     */
    public Person getSeat(int row,int column)
    {
        checkParam(row >= 0 && row < seats.length, "Número de filas de la matriz incorrecta");
        checkParam(column >= 0 && column < seats[0].length, "Número de asientos por fila incorrectos");

        return seats[row][column];
    }
    
    /**
     * Retorna el número de columnas de la matriz
     * 
     * @return el número de columnas de la matriz
     */
    public int getColumns()
    {
        return seats[0].length;
    }
    
    /**
     * Retorna el número de filas de la matriz
     * 
     * @return el número de filas de la matriz
     */
    public int getRows()
    {
        return seats.length;
    }

    /**
     * Retorna la matriz bidimensional de asientos
     * 
     * @return la matriz bidimensional
     */
    public Person[][] getSeats()
    {
        return seats;
    }

    /**
     * El método bookSeat asigna a una persona al asiento cuya fila y columna recibe como parámetro 
     * 
     * @param passenger, pasajero que hace la reserva
     * @param row, número de fila de la reserva
     * @param column, número de columna de la reserva
     */
    public boolean bookSeat(Person passenger, int row, int column)
    {
        checkParam(passenger != null, "Esperaba pasajero pero fue null");

        checkParam(row >= 0, "Número de fila fuera de límite");
        checkParam(row < seats.length, "Número de fila fuera de límite");

        checkParam(column >= 0, "Número de columna fuera de límite");
        checkParam(column < COLUMNS, "Número de columna fuera de límite");

        if (seats[row][column] == null){
            seats[row][column] = passenger;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Libera un asiento del avión retornando la persona que lo ocupaba
     * 
     * @param row, fila donde se encuentra la posición a liberar
     * @param column, columna donde se encuentra la posición a liberar
     * 
     * @return person, persona liberada o null si no hay nadie
     */
    public Person releaseSeat(int row, int column)
    {
        checkParam(row >= 0 && row < seats.length, "Fila fuera de límites");
        checkParam(column >= 0 && column < COLUMNS, "Columna fuera de límites");

        Person result = seats[row][column];
        seats[row][column] = null;
        return result;
    }

    /**
     * El método oldestPassenger devolverá al pasajero de más edad sentado en 
     * el avión o null si el avión está vacío
     * 
     * @return el pasajero de más edad en el avión, o null si el avión está vacío
     */
    public Person oldestPassenger()
    {        
        Person oldestPassenger = null;
        int oldestAge = -1;
        for(int i = 0; i < seats.length; i++)
        {
            for(int j = 0; j < seats[i].length; j++)
            {
                if(seats[i][j] != null && seats[i][j].getAge() > oldestAge)
                {
                    oldestPassenger = seats[i][j];
                    oldestAge = oldestPassenger.getAge();
                }
            }
        }
        return oldestPassenger;
    }
    
    /**
     * El método numberOfFreeSeats devolverá el número de asientos libres 
     * en la fila del avión que se le pasa como parámetro
     * 
     * @param row, fila del avión a comprobar el número de asientos libres
     * @return el número de asientos libres en la fila indicada en el parámetro
     */
    public int numberOfFreeSeats(int row)
    {
        checkParam(row >= 0 && row < seats.length,"Filas fuera de límite");
        int cont = 0;
        for(int j = 0; j < seats[row].length; j++)
        {
            if(seats[row][j] == null)
            {
                cont = cont + 1;
            }
        }
        return cont;
    }
    
    /**
     * Calcula el número de asientos libres en toda la matriz
     */
    public int numberOfFreeSeats()
    {
        int freeSeats = 0;
        for(int i = 0; i < seats.length; i++)
        {
            // int freeSeatsInRow = numberOfFreeSeats(i);
            // freeSeats = freeSeats + freeSeatsInRow;
            freeSeats = freeSeats + numberOfFreeSeats(i);
        }       
        return freeSeats;
    }
    
    /**
     * El método print mostrará el estado de los asientos del avión
     * Una X para un adulto
     * Una C para un niño
     * Un ? para un asiento libre con el siguiente formato:
     * 
     *     0   1   2   3   4   5
     *     
     * 0   X   X   ?   ?   ?   X
     * 1   X   X   X   ?   ?   ?
     * 2   ?   X   ?   ?   ?   ?
     * 3   X   X   ?   X   X   X
     * 4   X   X   X   X   X   X
     * 5   X   X   ?   X   X   X
     * 6   X   X   X   X   X   X
     * 7   X   X   ?   ?   ?   X
     * ...
     */
    public void print()
    {
        System.out.print("  ");
        
        for (int j =  0; j < seats[0].length; j++) 
        {
            System.out.print(j + " ");
        }
        
        System.out.println(" ");
        for (int  i = 0; i < seats.length; i++) 
        {
            System.out.print(i + " ");
            for (int j = 0; j < seats[i].length;j++) 
            {
                if (seats[i][j] == null){
                    System.out.print('?' + " ");
                }else if (seats[i][j].getAge() >= Person.ADULTHOOD_AGE){
                    System.out.print('X' + " ");
                }else {
                    System.out.print('C' + " ");
                }
            }
            System.out.println(" ");
        }
        System.out.println();
    }
    
    /**
     * El método removeMales eliminará todos los hombres que hay en 
     * una lista recibida como parámetro
     * 
     * @param lista de personas
     * @return número de hombres eliminados de la lista
     */
    public int removeMales(ArrayList<Person> list)
    {
        checkParam(list != null,"Esperaba lista y fue null");
        checkParam(notNullInList(list), "No debe haber elementos null en la lista");
        //notNull devuelve true si no hay objetos null en la lista
        
        //creación del objeto iterador asignado al iterador de la lista
        Iterator<Person> iterator = list.iterator();
        
        //creación de un contador de hombres eliminados
        int removedMales = 0;
        
        //recorrido de la lista
        while(iterator.hasNext())    //mientras iterator tenga un siguiente elemento...
        {
            Person person = iterator.next(); //cogemos la persona situada en la posición i
            if(person.getGender() == person.GENDER_MALE) //si la persona es hombre...
            {
                iterator.remove();  //borramos a esa persona hombre
                removedMales++;
            }
        }
        return removedMales;
    }
    
    /**
     * El método removeMales eliminará todos los hombres que hay en 
     * una lista recibida como parámetro
     * 
     * @param lista de personas
     * @return número de hombres eliminados de la lista
     */
    public int removeMales2(ArrayList<Person> list)
    {
        //creación de un contador de hombres eliminados
        int removedMales = 0;
        
        //recorrido de la lista
        for(int i = 0; i < list.size(); i++)
        {
            Person person = list.get(i);            
            //list.get(0); //arrayList
            // list[0]; //array
            // list[0][0]; //matriz
            if(person.getGender() == person.GENDER_MALE) //si la persona es hombre...
            {
                list.remove(i);  //borramos a esa persona hombre
                removedMales++;
                i--; //para no saltar un elemento al borrarse en el ArrayList (se reorganizan los elementos)
            }
        }
        return removedMales;
    }
    
    /**
     * El método childrenPassengers retornará un ArrayList de todos los pasajeros
     * que sean niños
     * 
     * @return ArrayList<Person>, la lista con los elementos persona que sean niños
     */
    public ArrayList<Person> childrenPassengers()
    {
       ArrayList<Person> childrenList = new ArrayList<Person>();
       //para el caso en el que se retornase un Array en vez de ArrayList
       Person[] childrenList2 = new Person[seats.length * seats[0].length];
       int index = 0;
       
       for(int i = 0; i < seats.length; i++)
       {
           for(int j = 0; j < seats[i].length; j++)
           {
               if(seats[i][j] != null && seats[i][j].getAge() < Person.ADULTHOOD_AGE)
               {
                   //para el ArrayList
                   childrenList.add(seats[i][j]);
                   
                   //para el Array
                   childrenList2[index] = seats[i][j];
                   index++;
               }
           }
       }
       //return childrenList2;  //para el caso de devolver un Array en vez de un ArrayList
       return childrenList;
    }
    
    /**
     * El método getNumPax retornará el número de pasajeros sentados en un área del avión
     * Un área del avión puede ser primera clase, clase estándar o todo el avión
     * 
     * El parámetro área puede ser un valor 1, 2 ó 3 que se asocia con las constantes:
     * FIRST_CLASS = 1
     * STANDARD_CLASS = 2
     * ALL_CLASS = 3
     * 
     * @param area, el área del avión a revisar el número de pasajeros total
     * @return el número de pasajeros que se encuentran en el área a revisar
     */
    public int getNumPax(byte area)
    {
        checkParam(area >= FIRST_CLASS && ALL_CLASS >= area,"Área no válida");
        
        int data = 0;
        switch(area)
        {
            case 1:
                data = getNumPax(0,getFirstRows());
                break;
            
            case 2:
                data = getNumPax((getFirstRows())+1,getStandardRows());
                break;
            
            case 3:
                data = getNumPax(0,getStandardRows());
                break;
        }
        return data;
    }
    
    /**
     * El método getNumPaxBySection devolverá el número de pasajeros sentados en una sección del avión
     * Una sección se representa mediante la primera posición de una fila y la última posición de una fila
     * 
     * Por ejemplo:
     * La sección (0,0)–(3,5) abarca las filas 0, 1, 2 y 3 completas.
     * La sección (1,1)–(5,4) abarca las posiciones (1,1)(1,2)(1,3)(1,4) de la fila 1, (2,1)(2,2)(2,3)(2,4) de la fila 2
     * 
     * @param row1, fila del primer elemento
     * @param column1, columna del primer elemento
     * @param row2, fila del segundo elemento
     * @param colun2, columna del segundo elemento
     */
    public int getNumPaxBySection(int row1,int column1,int row2,int column2)
    {
        checkParam(row1 <= row2 && row1 >= 0 && row1 < seats.length, "Fila 1 no válida");
        checkParam(row2 >= row1 && row2 >= 0 && row2 < seats.length, "Fila 2 no válida");
        
        checkParam(column1 <= column2 && column1 >= 0 && column1 < seats[0].length, "Columna 1 no válida");
        checkParam(column2 >= column1 && column2 >= 0 && column2 < seats[0].length, "Columna 2 no válida");
        
        int cont = 0;
        for(int i = row1; i <= row2; i++)
        {
            for(int j = column1; j <= column2; j++)
            {
                if(seats[i][j] != null)
                {
                    cont++;
                }
            }
        }
        return cont;
    }
    
    /**
     * El método loadPax sentará en el avión a un número dado de pasajeros.
     * Se irán sentando en los asientos libres desde las primeras filas del avión
     * 
     * @param el número de pasajeros a sentar
     */
    public void loadPax(int paxNumber)
    {
        checkParam(paxNumber <= area(seats[0].length,seats.length),"Demasiados pasajeros para el avión");
        checkParam(paxNumber >= 0,"El número de pasajeros no puede ser negativo");
        
        if(numberOfFreeSeats() < paxNumber)
        {
            paxNumber = numberOfFreeSeats();
        }
        
        for(int i = 0; i < seats.length && paxNumber > 0; i++)
        {
            for(int j = 0; j < seats[i].length && paxNumber > 0; j++)
            {
                if(bookSeat(new Person(),i,j))
                {
                    paxNumber--;
                }
            }
        }
    }
    
    /**
     * El método getYoungestPeople devolverá un ArrayList que contenga a las personas de menor edad sentadas en el avión.
     * Es decir, las personas que tengan la edad menor a todas las sentadas en el avión
     * 
     * @return el ArrayList con las personas de menor edad del avión
     */
    public ArrayList<Person> getYoungestPeople()
    {
        checkParam(notNullInMatrix(seats), "No hay pasajeros en el avión");
        
        ArrayList<Person> list = new ArrayList();
        Person aux = new Person(Person.MAX_AGE - 1);
        int auxAge = aux.getAge();
        for(int i = 0; i < seats.length; i++)
        {
            for(int j = 0; j < seats[i].length; j++)
            {
                if(seats[i][j].getAge() < auxAge && seats[i][j].getAge() > 0)
                {
                    list.add(seats[i][j]);
                    auxAge = seats[i][j].getAge();
                }
            }
        }
        return list;
    }
    
    /**
     * El método loadPassengers asignará asientos automáticamente a un número de pasajeros.
     * 
     * El número máximo de pasajeros a asignar passengers es recibido como parámetro 
     * y es rechazado si supera la capacidad total del avión.
     * 
     * Si no supera la capacidad, los pasajeros a sentar se deben generar de manera aleatoria
     * 
     * @param el número máximo de pasageros a asignar
     */
    public void loadPassengers(int passengers)
    {
        checkParam(passengers > 0,"El número de pasajeros no puede ser negativo");
        
        if(passengers == area(seats[0].length,seats.length))
        {
            for(int i = 0; i < seats.length; i++)
            {
                for(int j = 0; j < passengers && j < seats.length; j++)
                {
                    Person passenger = new Person();
                    if(seats[i][j] == null)
                    {
                        seats[i][j] = passenger;
                    }
                }
            }
        } else if(passengers < area(seats[0].length,seats.length))
        {
            int people = new Random().nextInt(passengers + 1);
            load(people);
        }
    }
    
    /**
     * Rellena una matriz de personas
     */
    public void fillSeats()
    {
        for(int i = 0; i < seats.length; i++)
        {
            for(int j = 0; j < seats[i].length; j++)
            {
                seats[i][j] = new Person();
            }
        }
    }
    
    //métodos auxiliares//

    /**
     * El método checkParam comprueba una condición, y si es falsa, lanza una excepción
     * con un mensaje
     * 
     * @param condition, la condición a evaluar
     * @param msg, el mensaje de la excepción
     */
    private void checkParam(boolean condition, String msg)
    {
        if (!condition)
        {
            throw new IllegalArgumentException(msg);
        }
    }
    
    /**
     * Recorre los elementos de la lista y devuelve true si ninguno es null 
     * Sino devuelve false
     * 
     * @param list, lista a recorrer los elementos
     */
    private boolean notNullInList(ArrayList<Person> list)
    {
        boolean data = true;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i) != null)
            {
                data = true;
            } else{
                data = false;
            }
        }
        return data;
    }
    
    /**
     * Recorre los elementos de una matriz y devuelve true si ningún elemento es null 
     * Sino devuelve false
     * 
     * @param matrix, matriz a recorrer los elementos
     */
    private boolean notNullInMatrix(Person[][] matrix)
    {
        boolean data = true;
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                if(matrix[i][j] != null)
                {
                    data = true;
                } else{
                    data = false;
                }
            }
        }
        return data;
    }
    
    /**
     * El método auxiliar getNumPax dirá el número de pasajeros que hay entre dos filas
     * 
     * @param initialRow, la fila inicial a comprobar
     * @param finalRow, la fila final a comprobar
     * 
     * @return el número de pasajeros que hay entre las filas dadas como parámetros
     */
    private int getNumPax(int initialRow,int finalRow)
    {
        checkParam(initialRow >= 0 && initialRow < seats.length,"Fila inicial parámetro fuera de rango");
        checkParam(initialRow < finalRow,"El número de la fila inicial debe ser inferior al de la fila final");
        
        checkParam(finalRow >= 0 && finalRow < seats.length,"Fila final parámetro fuera de rango");
        checkParam(finalRow > initialRow,"El número de la fila final debe ser superior al de la fila inicial");
        
        int cont = 0;
        for(int i = initialRow; i <= finalRow; i++)
        {
            for(int j = 0; j < seats[i].length; j++)
            {
                if(seats[i][j] != null)
                {
                    cont++;
                }
            }
        }
        return cont;
    }
    
    /**
     * El método area calculará el área de una matriz
     * 
     * @param la matriz
     * @return el área de la matriz 
     */
    private int area(int base, int height)
    {
        return base * height;
    }
    
    /**
     * El método load sentará a unos pasajeros recibidos como parámetros en sitios aleatorios
     * 
     * @param, el número de pasajeros a sentar
     */
    private void load(int passengers)
    {
        int i = 0;
        int j = 0;
        while(i < seats.length)
        {
            while(j < seats[i].length)
            {
                if(seats[i][j] == null)
                {
                    Person p1 = new Person();
                    Random ranRow = new Random();
                    Random ranCol = new Random();
                    seats[ranRow.nextInt(seats.length)][ranCol.nextInt(seats[0].length)] = p1;
                }
                j++;
            }
            i++;
        }
    }
}