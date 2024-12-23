import java.util.Random; //importa la clase Random del paquete util de java
/**
 * La clase matrix representa una matriz bidimensional con una dimensión
 * máxima de 20x20
 * 
 * @author Miguel 
 * @version 22-11-21
 */
public class Matrix
{
    //constantes de la clase//
    public static final int MAX_ROWS = 20; //número máximo de filas de matrix
    public static final int MAX_COLUMNS = 20; //número máximo de columnas de matrix

    //variables de instancia//
    private int[][] matrix; //declaración de la matriz bidimensional

    //CONSTRUCTORES//
    /**
     * Constructor de la clase Matrix con un parámetro entero
     * Creará una matriz cuadrada de dimensión n
     * 
     * @param n, la dimensión de la matriz cuadrada (bidimensional)
     */
    public Matrix(int n)
    {
        //chequeo de parámetros
        checkParam(n > 0, "La dimensión de la matriz bidimensional no puede ser negativa ni 0");
        
        //creación de una referencia de un objeto de la clase random
        Random rd = new Random(); 
        
        //creación de la matriz bidimensional cuadrada con dimensión n
        matrix = new int[n][n];
        
        //operación que se encarga de rellenar matrix con elementos enteros aleatorios
        for(int i = 0; i < matrix.length; i++) //recorrido de las filas
        {
            for(int j = 0; j < matrix[i].length; j++) //recorrido de las columnas
            {
                /*asignación a cada hueco de la matriz de un número aleatorio
                comprendido entre el 0 y el 255 */
                matrix[i][j] = rd.nextInt(256); 
            }
        }
    }

    /**
     * Constructor de la clase matrix con un parámetro array (matriz bidimensional)
     * 
     * Crea una matriz copia a partir de la recibida como parámetro, si la matriz no es cuadrada o sobrepasa las dimensiones máximas, 
     * se lanzará la excepción: “The matrix must be square”
     * 
     * Se usará este constructor para la realización de las pruebas unitarias
     * 
     * @param copy, la matriz bidimensional cuadrada que se copiará
     */
    public Matrix(int[][] copy)
    {
        //comprobación de que la matriz parámetro es distinta de null (que exista)
        checkParam(copy != null, "Matriz bidimensional no válida");

        //comprobación de que la matriz parámetro no tenga una dimensión mayor a la máxima permitida:
        checkParam(copy.length <= MAX_ROWS, "The matrix must be square");
        for(int i = 0; i < copy.length; i++)
        {
            checkParam(copy[i].length <= MAX_COLUMNS, "The matrix must be square");
        }

        //comprobación de que la matriz parámetro sea cuadrada:
        for(int i = 0; i < copy.length; i++)
        {
            checkParam(copy.length == copy[i].length, "The matrix must be square");
        }

        //creación de la matriz copia de la matriz pasada como parámetro (usamos las dimensiones de la del parámetro para matrix)
        matrix = new int[copy.length][copy[0].length];
        
        //operación que se encarga de rellenar matrix con los elementos del Array bidimensional recibido como parámetro
        for(int i = 0; i < copy.length; i++) //recorrido por filas
        {
            for(int j = 0; j < copy[i].length; j++) //recorrido por columnas
            {
                matrix[i][j] = copy[i][j]; //asignación de los elementos de la matriz parámetro en matrix
            }
        }
    }

    //MÉTODOS//

    /**
     * El método getAverage devolverá la media aritmética de todos los elementos de una matriz
     * 
     * @return la media aritmética
     */
    public int getAverage()
    {
        int data = 0; //numerador de la división del cálculo de la media aritmética
        int den = 0; //denominador de la división del cálculo de la media aritmética
        for(int i = 0; i < matrix.length; i++) //recorrido por filas
        {
            for(int j = 0; j < matrix[i].length; j++) //recorrido por columnas
            {
                data = data + matrix[i][j]; //suma de los elementos de matrix
                den = den + 1; //contador que se incrementa en una unidad por cada elemento que haya en matrix
            }
        }
        return data / den; //retorno de la media aritmética
    }

    /**
     * El método addByColumns devolverá un vector con la suma de los elementos por columnas
     * Consiste en sumar los elementos de cada columna almacenándolos en un vectór
     * 
     * @return result (en forma de un vector), el resultado de la suma de los elementos de cada columna
     */
    public int[] addByColumns()
    {
        int[] result = new int[matrix.length]; //creación de una variable local con dimensión igual a la de matrix (matrix es cuadrada)
        for(int i = 0; i < matrix.length; i++) //recorrido por filas
        {
            int data = 0; //variable local que almacenará la suma de los elementos de cada columna y que se inicializa a 0 cada vez que pasamos a una nueva columna
            for(int j = 0; j < matrix[i].length; j++) //recorrido por columnas
            {
                data = data + matrix[j][i]; //suma de los elementos de las columnas correspondientes
                result[i] = data; //asignación del resultado de la ejecución de la orden anterior
            }
        }
        return result; //retorno del Array unidimensional con la suma de los elementos de cada columna
    }

    /**
     * El método swapColumns cambiará los elementos de las columnas de manera que se intercambien
     * las columnas a elegir a modo: 0-1,1-2,2-3,etc
     * 
     * En caso de que el número de columnas sea impar, la última columna será la que se quede como está
     */
    public void swapColumns()
    {                       
        //creación de un vector fila con la dimensión del número de filas de matrix
        int[] aux1 = new int[matrix[0].length];
        
        //asignación al vector fila de los elementos de la primera columna de matrix
        for(int i = 0; i < matrix.length; i++) //recorrido por filas
        {
            aux1[i] = matrix[i][0]; //guarda los elementos de la primera columna de matrix en la variable aux1
        }
        
        //operación del intercambio de columnas
        if(matrix[0].length % 2 == 0) //si el número de columnas de matrix es par...
        {
            for(int i = 0; i < matrix.length; i++) //recorrido por filas
            {
                for(int j = 1; j < matrix[i].length; j++) //recorrido por columnas
                {
                    matrix[i][j - 1] = matrix[i][j]; //copia la segunda columna y la almacena en la primera columna de matrix
                }
            }

            for(int i = 0; i < matrix.length; i++) //recorrido por filas
            {
                matrix[i][matrix.length - 1] = aux1[i]; //los elementos de aux1 se guardan en la última columna de matrix
            }
        }else{ //si el número de columnas de matrix es impar...
            for(int i = 0; i < matrix.length; i++) //recorrido por filas
            {
                for(int j = 1; j < matrix[i].length; j++) //recorrido por columnas
                {
                    matrix[i][j - 1] = matrix[i][j]; //copia la segunda columna y la almacena en la primera columna de matrix
                }
            }

            for(int i = 0; i < matrix.length; i++) //recorrido por filas
            {
                matrix[i][matrix.length - 2] = aux1[i]; //los elementos de aux1 se guardan en la última columna de matrix
            }
        }
    }

    /**
     * El método rotateMatrix girará la matriz 90º hacia la izquierda
     * 
     * Los elementos de la última columna, se situarán de tal forma que la última columna sea la primera fila y viceversa 
     */
    public void rotateMatrix()
    {
        //creación de una primera matriz bidimensional con las mismas dimensiones que matrix
        int[][] aux1 = new int [matrix.length][matrix[0].length];
        
        //operación de rotación de matrix
        for(int i = 0; i < matrix.length; i++) //recorrido por filas
        {
            for(int j = 0; j < matrix[i].length; j++) //recorrido por columnas
            {
                aux1[i][j] = matrix[j][getMatrixLength()-(i + 1)]; //asigna a la variable aux1 en la posición i,j los valores correspondientes
            }
        }
        matrix = aux1; //asignación a matrix de la matriz resultante (aux1)
    }

    /**
     * El método getMaxAdjacentValue retornará el valor mayor adyacente a una posición dada. 
     * Cada posición tiene 8 adyacentes, exceptuando las posiciones de los extremos que tienen 5 adyacentes o de las esquinas que tienen 3 adyacentes.
     * 
     * @param row, la fila del elemento a revisar
     * @param column, la columna del elemento a revisar
     * 
     * @return el valor mayor adyacente
     */
    public int getMaxAdjacentValue(int row, int column)
    {
        //chequeo de parámetros
        checkParam(row >= 0 && row < matrix.length, "Fila fuera de rango");
        checkParam(column >= 0 && column < matrix[0].length, "Columna fuera de rango");
        
        //variable local con uso de almacenar el valor mayor adyacente de la posición, de matrix, dada como parámetros 
        int data = 0;
        
        //operación de búsqueda del mayor adyacente
        if((row > 0 && column > 0) && (row < matrix.length - 1 && column < matrix[0].length - 1)) //elemento interior 
        {
            for(int i = row - 1; i < row + 2; i++) //recorrido por filas
            {
                for(int j = column - 1; j < column + 2; j++) //recorrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }    
        }else if(row == 0 && column > 0 && column < matrix[0].length - 1) //elemento de la primera fila
        {
            for(int i = row; i < row + 2; i++) //recorrido por filas
            {
                for(int j = column - 1; j < column + 2; j++) //reocrrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }
        } else if(column == 0 && row > 0 && row < matrix.length - 1) //elemento de la primera columna
        {
            for(int i = row - 1; i < row + 2; i++) //recorrido por filas
            {
                for(int j = column; j < column + 2; j++) //recorrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }
        } else if(column == matrix[0].length - 1 && row < matrix.length - 1 && row > 0) //elemento de la última columna
        {
            for(int i = row - 1; i < row + 2; i++) //recorrido por filas
            {
                for(int j = column - 1; j < matrix[i].length; j++) //recorrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }
        } else if(row == matrix.length - 1 && column < matrix.length - 1 && column > 0) //elemento de la última fila
        {
            for(int i = row - 1; i < matrix.length; i++) //recorrido por filas
            {
                for(int j = column - 1; j < column + 2; j++) //recorrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }
        } else if(row == 0 && column == 0) //elemento de la esquina superior izquierda
        {
            for(int i = row; i < row + 2; i++) //recorrido por filas
            {
                for(int j = column; j < column + 2; j++) //recorrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }
        } else if(row == matrix.length - 1 && column == matrix[0].length - 1) //elemento de la esquina inferior derecha
        {
            for(int i = row - 1; i < matrix.length; i++) //recorrido por filas
            {
                for(int j = column - 1; j < matrix[i].length; j++) //recorrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }
        } else if(row == matrix.length - 1 && column == 0) //elemento de la esquina inferior izquierda
        {
            for(int i = row - 1; i < matrix.length; i++) //recorrido por filas
            {
                for(int j = column; j < column + 2; j++) //recorrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }
        } else if(row == 0 && column == matrix[0].length - 1) //elemento de la esquina superior derecha
        {
            for(int i = row; i < row + 2; i++) //recorrido por filas
            {
                for(int j = column - 1; j < matrix[0].length; j++) //recorrido por columnas
                {
                    if(matrix[i][j] != matrix[row][column] && matrix[i][j] > data) //si el elemento de matrix[i][j] es distinto del seleccionado con los parámetros, y dicho elemento es mayor que data...
                    {
                        data = matrix[i][j]; //asignación a data del mayor adyacente correspondiente
                    }
                }
            }
        }
        return data; //retorno del valor del máximo adyacente
    }

    /**
     * El método smoothMatrix retornará una matriz obtenida a partir del atributo, 
     * reemplazando cada elemento por la media aritmética de los vecinos (incluyendo el propio elemento) de la matriz original
     * 
     * @return la matriz obtenida a partir del atributo
     */
    public int[][] smoothMatrix()
    {
        //creación de la matriz que se retornará con las dimensiones de matrix
        int[][] result = new int[matrix.length][matrix[0].length];
        
        //asignación, en la matriz auxiliar result, de los elementos de matrix
        for(int i = 0; i < matrix.length; i++) //recorrido por filas
        {
            for(int j = 0; j < matrix[i].length; j++) //recorrido por columnas
            {
                result[i][j] = matrix[i][j]; //asignación de los elementos de matrix en la variable local result
            }
        }

        //asignación de los elementos de result
        for(int i = 0; i < matrix.length; i++) //recorrido de todas las filas de matrix
        {
            for(int j = 0; j < matrix[i].length; j++) //recorrido de todas las columnas de matrix
            {
                if(i == 0 && j == 0) //esquina superior izquierda
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = 0; a < 2; a++) //recorrido por filas
                    {
                        for(int b = 0; b < 2; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }

                if(i == 0 && j == matrix[0].length - 1) //esquina superior derecha
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = i; a < i + 2; a++) //recorrido por filas
                    {
                        for(int b = j - 1; b < matrix[a].length; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }

                if(i == matrix.length - 1 && j == 0) //esquina inferior izquierda
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = i - 1; a < matrix.length; a++) //recorrido por filas
                    {
                        for(int b = j; b < j + 2; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }

                if(i == matrix.length - 1 && j == matrix[0].length - 1) //esquina inferior derecha
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = i - 1; a < matrix.length; a++) //recorrido por filas
                    {
                        for(int b = j - 1; b < matrix[i].length; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }

                if(i == 0 && j > 0 && j < matrix[0].length - 1) //primera fila
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = i; a < i + 2; a++) //recorrido por filas
                    {
                        for(int b = j - 1; b < j + 2; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }

                if(i == matrix.length - 1 && j < matrix.length - 1 && j > 0) //última fila
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = i - 1; a < matrix.length; a++) //recorrido por filas
                    {
                        for(int b = j - 1; b < j + 2; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }

                if(j == 0 && i > 0 && i < matrix.length - 1) //primera columna
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = i - 1; a < i + 2; a++) //recorrido por filas
                    {
                        for(int b = j; b < j + 2; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }

                if(j == matrix[0].length - 1 && i < matrix.length - 1 && i > 0) //última columna
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = i - 1; a < i + 2; a++) //recorrido por filas
                    {
                        for(int b = j - 1; b < matrix[i].length; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }

                if((i > 0 && j > 0) && (i < matrix.length - 1 && j < matrix[0].length - 1)) //elemento interior 
                {
                    int num = 0; //variable local inicializada a 0 que será usada como el numerador de la media aritmética de los vecinos
                    int den = 0; //variable local inicializada a 0 que será usada como el denominador de la media aritmética de los vecinos
                    for(int a = i - 1; a < i + 2; a++) //recorrido por filas
                    {
                        for(int b = j - 1; b < j + 2; b++) //recorrido por columnas
                        {
                            num = num + matrix[a][b]; //suma de los elementos correspondientes
                            den = den + 1; //incrementación de la variable den por cada ejecución del bucle
                        }
                    }
                    int element = num / den; //cálculo de la media aritmética de los elementos, y almacenada en una variable local
                    result[i][j] = element; //asignación del valor de la ejecución anterior en la posición correspondiente de la matriz a retornar
                }       
            }
        }
        return result; //retorno de la matriz resultado de la ejecución del método
    }

    //MÉTODOS AUXILIARES//
    
    /**
     * El método checkParam comprobará una condición y si no se cumple, lanza una excepción
     * 
     * @param condition, la condición a evaluar
     * @param msg, el mensaje que saldrá con la excepción
     */
    private void checkParam(boolean condition, String msg)
    {
        if (!condition)
        {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * El método returnMatrix retornará la matriz bidimensional matrix
     * 
     * @return la matrix bidimensional
     */
    public int[][] returnMatrix()
    {
        return matrix;
    }

    /**
     * El método getMatrixLength retornará el tamaño de la matriz bidimensional (número de sus filas totales) 
     * 
     * @return el número de filas de matrix
     */
    private int getMatrixLength()
    {
        return matrix.length;
    }
}