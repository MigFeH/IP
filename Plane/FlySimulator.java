import java.util.ArrayList;
import java.util.Iterator;
/**
 * Simulador de vuelo para los aviones.
 * 
 * @author Miguel
 * @version 15-12-21
 */
public class FlySimulator
{
    public static final char FREE = '~';  // la virgulilla indica posición libre (sin avión)
    public static final char COLLISION = '%';  // indica colisión 
    private static final String IDS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";  // posibles identificadores para avión
    
    // Lista de Aviones (planeList) y mapa de aviones en el radar (map)
    private ArrayList<Plane> planeList; //lista de aviones
    private char[][] map;  //representa el radar. Contiene:
                                //identificador del avión en las posiciones ocupadas por avión
                                // '~' en las posiciones libres
                                // '%' en las posiciones en que hubo colisión

    /**
     * Constructor 
     * Crea la lista y el mapa
     * Rellena el mapa con virgulillas (posiciones libres)
     */
    public FlySimulator()
    {
        planeList = new ArrayList<Plane>();
        map = new char[Plane.MAX_X_POS + 1][Plane.MAX_Y_POS + 1];
        
        resetMap();
        // inicialmente el mapa está vacío, sin aviones, lo rellena de ~
    }
    
    private void resetMap()
    {
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                map[i][j] = FREE;
            }
        }
    }

    /**
     * Añade un avión al sistema de control
     * 
     * No serán válidos y provocarán excepción 
     *      aviones que estén localizados en posiciones del mapa ya ocupadas, 
     *      ni aviones con el un identificador que ya existe. 
     * 
     * Se añade el avión a la lista de a viones 
     * y se modifica el mapa para que aparezca el identificador del avión
     * 
     * @param plane, avión a añadir
     * 
     */
    public void addPlane(Plane plane)
    {
        checkParam(plane != null, "Esperaba avión y fue null");
        checkParam(isFreePosition(plane),"La posición ya está ocupada");
        checkParam(! hasSameIdentifier(plane), "Ya existe un avión con el mismo identificador");
       
        int row = plane.getXPos();
        int column = plane.getYPos();
        
        planeList.add(plane);
        map[row][column] = plane.getIdentifier();
    }
    
    /**
     * @return true si hay un avión en la lista con el mismo identificador que el 
     * avión recibido como parámetro
     * @param plane 
     */
    private boolean hasSameIdentifier(Plane plane)
    {
        for(int i = 0; i < planeList.size(); i++)
        {
            if(planeList.get(i).getIdentifier() == plane.getIdentifier())
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Comprueba si la posición en la matriz correspondiente 
     * a la posición del avión que recibe, está vacía
     * @param plane
     * @return true si está libre
     */
    private boolean isFreePosition(Plane plane)
    {
       return map[plane.getXPos()][plane.getYPos()] == FREE;
    }
    
    /**
     * Imprime el mapa (radar) con todos los aviones
     * Se imprie el identificador o la virgulilla
     */
    public void printMap()
    {
        System.out.println("-------------- Radar--------");
        printHeader();
        for (int i=0; i < map.length; i++) {
            System.out.print(i + "  ");
            for (int j=0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    private void printHeader()
    {
       System.out.print("   ");
       for (int j=0; j < map[0].length; j++) {
           System.out.print(j +  " ");
        }
       System.out.println();
    }
    
    /**
     * Simula el movimiento de todos los aviones una posición
     * Implica volar los aviones y actualizar el mapa
     */
    public void animate()
    {
        flyPlanes();        
        updateMap();
        removeAndPrintCollisions();
        printMap(); 
    }
    
    /**
     * Hace que todos los aviones de la lista aviones vuelen
     */
    private void flyPlanes(){
        for(Plane plane: planeList)
        {
            plane.fly();
        }
        
        // for(int i = 0; i < planeList.size(); i++)
        // {
            // planeList.get(i).fly();
        // }
    }
    
    /**
     * Actualiza el mapa del radar
     *      Inicializa el mapa a vacío 
     *      Recorre lista de aviones
     *          Si está vacía la posición del avión, se asignará el identificador del avión de la lista
     *          Si ya está ocupada se asignará colisión 
     *          
     */
    private void updateMap()
    {
        resetMap();
        for(int i = 0; i < planeList.size(); i++)
        {
            Plane plane = planeList.get(i); //en planeList saco el avión
            if(map[plane.getXPos()][plane.getYPos()] == FREE) //si en la posición del avión está libre dicha posición...
            {
                map[plane.getXPos()][plane.getYPos()] = plane.getIdentifier();
            }else{
                map[plane.getXPos()][plane.getYPos()] = COLLISION;
            }
        }
    }
    
    /**
     * 
     * Borra e imprime los aviones que colisionan 
     * (Los que tienen igual posición y en matriz se indica %)
     *  Recorre matriz y si hay un % 
     *      busca todos los aviones con esa posición 
     *          Los borra y los guarda en una lista
     *      Imprime la lista de borrados para esa posición
     * 
     */
    private void removeAndPrintCollisions () 
    {        
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                if(map[i][j] == COLLISION)
                {
                    ArrayList<Plane> removed = removePlanesInPosition(i,j);
                    printCollision(removed);
                }
            }
        }
    }
       
        
    /**
     * Elimina de la lista de aviones todos los que tengan posición row column
     * Y los devuelve en una lista
     * @param row, fila en la matriz
     * @param column, columna en la matriz
     * @return ArrayList con aviones borrados
     */
    private ArrayList<Plane> removePlanesInPosition(int row, int column) 
    {
        ArrayList<Plane> collisioned = new ArrayList<Plane>();
        Iterator<Plane> iteratorPlaneList = planeList.iterator();
        while(iteratorPlaneList.hasNext())
        {
            Plane plane = iteratorPlaneList.next();
            if(plane.getXPos() == row && plane.getYPos() == column)
            {
                collisioned.add(plane);
                iteratorPlaneList.remove();
            }
        }
        return collisioned;
        
        
        
        
        
        // ArrayList<Plane> removedPlanes = new ArrayList();
        // for(Plane plane: planeList)
        // {
            // if(plane.getXPos() == row && plane.getYPos() == column)
            // {
                // removedPlanes.add(plane);
                // planeList.remove(plane);
            // }
        // }
        // return removedPlanes;
    }
    
     /**
     * @param ArrayList<Plane> lista de aviones a imprimir
     * 
     * Imprime los datos (toString) de todos los aviones de la lista 
     */
    private void printCollision(ArrayList<Plane> collisionedPlanes) 
    {
        System.out.println("-------Colisión---------");
        for(Plane plane: collisionedPlanes)
        {
            System.out.println(plane.toString());
        }
    }           
        
    /**
     * Si no se cumple la condición lanza RuntimeException con el mensaje indicado
     * @param condition
     * @param msg mensaje
     */
    private void checkParam(boolean condition, String msg)
    {
        if (condition == false) {
            throw new RuntimeException(msg);
        }
    }
    
    /**
     * Devuelve <<UNA COPIA>> de la lista de aviones
     * @return array con la lista de aviones
     */
    public Plane[] getPlanes()
    {
        Plane[] copy = new Plane[planeList.size()];
        for(int i = 0; i < planeList.size(); i++)
        {
            copy[i] = planeList.get(i);
        }
        return copy;
    }
    
    /**
     * Devuelve el contenido de una posición del mapa
     * @return identificador, % o ~ 
     */
    public char getPosition(int row, int column)
    {
        checkParam(row >= 0 && row < map.length, "Fila no válida");
        checkParam(column >= 0 && column < map[0].length, "Columna no válida");
        if(map[row][column] == FREE)
        {
            return FREE;
        }else{
            return COLLISION;
        }
    }
}
