// Boletin 1 Diseño Software
// Ejercicio 1: Mezclas de Strings
// Mario Lopez Cea (mario.lopez)
// Marta Martin de Argenta Hernandez (marta.martindeargenta)
// Grupo 4.2

package e1;


public class StringUtilities {

    public static boolean isValidMix(String a, String b, String c) {
        //almacenamos los strings en arrays para poder recorrerlos
        char[] array = a.toCharArray();
        char[] array2 = b.toCharArray();
        char[] array3 = c.toCharArray();
        if (array3.length == array.length + array2.length) {
            int j = 0;
            int i = 0;
            int k = 0;
            while (i < array3.length && j < array.length) {
                if (array[j] != array3[i]) { //si no coinciden las letras, avanzamos una posicion en el array3
                    i++;
                } else { //si coinciden buscamos la siguiente letra del array en el array3
                    j++;
                }
            }
            i = 0;
            while (i < array3.length && k < array2.length) {
                if (array2[k] != array3[i]) { //si no coinciden las letras, avanzamos una posicion en el array3
                    i++;
                } else { //si coinciden buscamos la siguiente letra del array2 en el array3
                    k++;
                }
            }
            return j == array.length && k == array2.length;
        } else { //si la longitud del array3 no coincide con la suma de array y array2 entonces no puede ser valido
            return false;
        }
    }

    public static String generateRandomValidMix(String a, String b) {

        String c; //string donde almacenaremmos el resultado
        int largo = a.length() + b.length(); //longitud maxima del string
        char[] arrayA = a.toCharArray(); //convertimos los string en arrays
        char[] arrayB = b.toCharArray();
        char[] arrayC = new char[largo]; //creamos un nuevo array
        int i = 0, j = 0; //contadores
        int valor;


        while (i < a.length() && j < b.length()) { //seguimos en el bucle mientras no copiemos un string completo


            valor = (int) Math.floor(Math.random() * 2);//le asiganamos a valor 1 o 0 de forma aleatoria

            if (valor == 1) { // si el valor es 1 copiamos en C el caracter de posicion i de A
                arrayC[i + j] = arrayA[i];
                i++;

            } else { //si el valor es 0 copiamos en C el caracter de posicion j de B
                arrayC[i + j] = arrayB[j];
                j++;
            }

        }
        //cuando salimos del bucle es porque ya hemos copiado uno de los dos strings entero en C

        if (i == a.length()) {//si ya hemos copiado el string A, falta por copiar B
            System.arraycopy(arrayB, j, arrayC, i + j, b.length() - j);
            //copia en arrayC los caracteres de b que quedan por añadir

        } else if (j == b.length()) { // si ya hemos copiado B falta por copiar A
            System.arraycopy(arrayA, i, arrayC, i + j, a.length() - i);
            //copia en arrayC los caracteres de a que quedan por añadir
        }

        c = String.valueOf(arrayC); //pasamos los caracteres de arrayc a String

        return c;


    }

}







