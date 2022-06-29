// Boletin 1 Diseño Software
// Ejercicio 2: Codigo en un teclado alfanumerico
// Mario Lopez Cea (mario.lopez)
// Marta Martin de Argenta Hernandez (marta.martindeargenta)
// Grupo 4.2

package e2;

public class Code {

    public static boolean isKeypadValid (char [][] keypad ) {
        int j, i; // posiciones de la matriz
        int len_i, len_j; //dimensiones de la matriz
        char [] lista = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'f', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z' };
        int contador=0;



        if(keypad==null){
            return false;
        }

        len_i = keypad.length; //numero de filas
        len_j = keypad[0].length; //numero de columnas
        //comprobar que todas las columnas tiene el mismo numero de caracteres




        for(int k=0; k<len_i; k++){
            if(keypad[k]==null){ //comprobamos si algun array es nulo
                return false;
            }

            if(len_j!=keypad[k].length){ //comprobamos si es rectangular
                return false;
            }
        }



        if(keypad[0][0]!='1'){ //si el primer elemento es diferente a 1
            return false;


        }else if(len_i==1 && len_j==1){ //si es una matriz 1x1 y su unico valor es 1 es válida
            return true;


        } else if((len_i != 1) && (keypad[1][0]=='2')){ //primero columnas, luego filas
            //si len_i = 1 quiere decir que es una matriz fila y, por lo tanto, no se puede evaluar keypad[1][0]

            for(j=0; j<len_j; j++){ //recorremos las columanas
                for(i=0; i < len_i; i++){ //para cada columan recorremos las filas
                    if(keypad[i][j]!=lista[contador]){
                        //si la letra del teclado no coincide con la que le corresponde segun el orden
                        return false;
                    }
                    contador++;

                }
            }
            return true;

        }else if(keypad[0][1]=='2'){ //primero filas, luego columnas

            for(i=0; i<len_i; i++){ //recorremos las filas
                for(j=0; j < len_j; j++){ //para cada fila recorremos las columnas
                    if(keypad[i][j]!=lista[contador]){
                        //si la letra del teclado no coincide con la que le corresponde segun el orden
                        return false;
                    }
                    contador++;
                }
            }
            return true;

        }else{

            return false;
        }



    }

    public static boolean areMovementsValid ( String [] movements ) {
        int num_s, len_a; //numero de strings en el array, y longitud del array
        int i, j;
        char[] array; //array donde almacenamos el string para recorrerlo

        if(movements==null){ // si el array es nulo, entonces no es valido
            return false;
        }
        num_s = movements.length;

        for(i=0; i<num_s; i++){
            if(movements[i]==null){ //en caso de ser nulo se devuelve false
                return false;
            }else{
                array=movements[i].toCharArray(); //convertimos el string en un array
                len_a=array.length;

                for(j=0; j<len_a; j++){ //vamos caracter a caracter comparado si son U, D, L o R
                    switch (array[j]){
                        case 'U', 'D', 'L', 'R':
                            break;
                        default: //si no son coinceden con ninguno, el movimiento no es valido
                            return false;

                    }
                }
            }
        }
        return true;
    }

    public static String obtainCode ( char [][] keypad , String [] movements ) {

        int k, g;
        int i=0, j=0; //creamos las posiciones de la matriz y las inicializamos para que empiezen en el 1.
        int len_i, len_j; //dimensiones de la matriz
        int num_s, len_a;
        char[] array;
        StringBuilder clave = new StringBuilder();

        len_i=keypad.length; //numero de filas
        len_j=keypad[0].length; //numero de columnas
        num_s = movements.length; //numero de strings


        if(!isKeypadValid(keypad)){
            throw new IllegalArgumentException("The keypad is not valid");

        }else  if(!areMovementsValid(movements)){
            throw new IllegalArgumentException("The movements are not valid");

        }else{
            for(k=0; k<num_s; k++){ //recorremos el array de string
                array=movements[k].toCharArray(); //convertimos el string en un array para poder recorrerlo
                len_a=array.length;

                for(g=0; g<len_a; g++){ //recorremos el string que tenemso almacenado en el array

                    switch (array[g]){
                        case 'U':
                            /* si nos  encontramos en la fila de arriba del teclado no se actualizara la posicion
                            ya que no podemos subir mas */
                            if(i!=0){
                                i--;
                            }
                            break;

                        case 'D':
                            /* si nos  encontramos en la fila de abajo del teclado no se actualizara la posicion
                            ya que no podemos bajar mas */
                            if(i != len_i - 1){
                                i++;
                            }
                            break;

                        case 'L':
                            /* si nos  encontramos a la izquierda del teclado no se actualizara la posicion
                            ya que no podemos ir mas a la izquierda */
                            if(j!=0){
                                j--;
                            }
                            break;

                        case 'R':
                            /* si nos  encontramos a la derecha del teclado no se actualizara la posicion
                            ya que no podemos ir mas a la derecha*/
                            if(j != len_j - 1){
                                j++;
                            }
                            break;


                    }


                }

                System.out.println("Numero: " + keypad[i][j]);
                clave.append(keypad[i][j]);//almacenamos en el string el valor de la posicion (i, j)
            }

            return clave.toString(); //convertimos el StringBuildier en un String y lo devolvemos
        }

    }



}
