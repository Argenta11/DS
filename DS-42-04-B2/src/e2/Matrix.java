package e2;

import java.util.Arrays;
import java.util.Iterator;


public class Matrix implements Iterable<Integer>{

    private int[][] matriz;
    private boolean rec; //si rec = true se recorre filas-columnas, si no columnas-filas

    public boolean isRec() {
        return rec;
    }


    public Matrix(int[][] matriz, boolean rec) { //constructor a traves de una martiz bidimensional
        if (!esRectangular(matriz)) {
            throw new IllegalArgumentException("No es rectangular");
        }
        this.matriz = matriz;
        this.rec = rec;
    }

    public Matrix(int i, int j, boolean rec) { //constructor por numero de filas y columnas
        matriz = new int[i][j];
        for (int k = 0; k < matriz.length; k++) { //ponemos cada fila a 0
            Arrays.fill(matriz[k], 0);
        }
        this.rec = rec;


    }

    private boolean esRectangular(int[][] matriz) { //comprueba si es rectangular
        int len_i = matriz.length; //numero de filas
        int len_j = matriz[0].length; //numero de columnas
        for (int k = 0; k < len_i; k++) {
            if (len_j != matriz[k].length) { //comprobamos si es rectangular
                return false;
            }
        }
        return true;
    }


    public int numFilas() {
        return this.matriz.length;
    }

    public int numColumnas() {
        return matriz[0].length;
    }

    public void setValor(int i, int j, int valor) { //cambia el valor a una posicion
        if (i >= numFilas()) {
            throw new IllegalArgumentException("Numero de fila inexistente");
        } else if (j >= numColumnas()) {
            throw new IllegalArgumentException("Numero de columna inexistente");
        } else {
            this.matriz[i][j] = valor;
        }
    }

    public int getValor(int i, int j) { //devuelve el valor de una posicion
        if (i>=numFilas()&&j>=numColumnas()){
            throw new IllegalArgumentException("Numero de fila y numero de columna inexistentes");
        }else {
            if (i >= numFilas()) {
                throw new IllegalArgumentException("Numero de fila inexistente");
            } else if (j >= numColumnas()) {
                throw new IllegalArgumentException("Numero de columna inexistente");
            } else {
                return this.matriz[i][j];
            }
        }
    }

    public int[][] clonarMatriz() {
        return matriz;
    } //devuelve la matriz en formato bidimensional

    public int[] clonarFila(int i) { //devuleve una fila
        if (i >= numFilas()) {
            throw new IllegalArgumentException("Numero de fila inexistente");
        } else {
            return matriz[i];
        }
    }


    public int[] clonarColumna(int j) { //devuelbe una columna
        if (j >= numColumnas()) {
            throw new IllegalArgumentException("Numero de fila inexistente");
        } else {
            int[] columna = new int[numFilas()];
            for (int i = 0; i < numFilas(); i++) {
                columna[i] = matriz[i][j];
            }
            return columna;
        }
    }


    public String matrizString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numFilas(); i++) {
            sb.append(Arrays.toString(matriz[i]));
            sb.append("\n");
        }
        return sb.toString();

    }


    //devuelve un objeto de tipo iterador que recorre filas y luego columnas
    private RowColumIterator rowColumnIterator() {
        //lo inicializamos una posicion antes, para que al hacer next empiece devolviendo el primer valor
        return new RowColumIterator(0, -1, this);

    }

    //devuelve un objeto de tipo iterador que recorre columnas y luego filas
    private ColumRowIterator columnRowIterator() {
        //lo inicializamos una posicion antes, para que al hacer next empiece devolviendo el primer valor
        return new ColumRowIterator(-1, 0, this);

    }


    @Override
    public Iterator<Integer> iterator() {
        //segun un valor llama a un iterator fila-columna, o al iterator columna-fila
        if (rec) {//si rec es true
            return rowColumnIterator();
        } else {
            return columnRowIterator();
        }

    }



}

