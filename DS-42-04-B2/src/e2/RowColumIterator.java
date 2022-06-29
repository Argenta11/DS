package e2;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RowColumIterator implements Iterator<Integer> {


    private int i; //contador de fila
    private int j; //contador de columna
    private Matrix m; //matriz a iterar

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Matrix getM() {
        return m;
    }

    public RowColumIterator(int i, int j, Matrix m) {
        this.i = i;
        this.j = j;
        this.m = m;
    }

    @Override
    public boolean hasNext() { //indica si existe o no elemento siguiente
        if (i + 1 >= m.numFilas() && j + 1 >= m.numColumnas()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Integer next() { //devuelve el elemento siguiente
        if(!hasNext()){
            throw new NoSuchElementException("No existe siguiente");
        }else{
            if(j+1==m.numColumnas()){//si nos encontramos en el ultmimo elemento de la fila
                this.j = 0;
                this.i = i+1;
            }else{
                this.j=j+1;

            }
        }
        return m.getValor(i, j);
    }



    @Override
    public void remove() {
        throw new UnsupportedOperationException("No se puede usar le metodo remove");
    }




}
