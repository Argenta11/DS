package e2;

import java.util.Iterator;

public class MatrixAddition {

    public static Matrix sumaMatrices(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.numFilas(), m1.numColumnas(), m1.isRec());

        Iterator<Integer> iterador1 = m1.iterator();
        Iterator<Integer> iterador2 = m2.iterator();

        if(m1.numColumnas()!=m2.numColumnas() || m1.numFilas()!=m2.numFilas()){
            throw new ArithmeticException();
        }if(m1.isRec()!=m2.isRec()){
            throw new IllegalArgumentException("Diferentes formas de recorrer la matriz");
        }else{
            int i=0;
            int j=0;
            int a, b;
            while(iterador1.hasNext()){
                a=iterador1.next();
                b=iterador2.next();
                m3.setValor(i, j, a+b);

                if(m1.isRec()){
                    if(j+1==m3.numColumnas()){//si nos encontramos en el ultmimo elemento de la fila
                        j = 0;
                        i = i+1;
                    }else{
                        j=j+1;
                    }

                }else{
                    if(i+1==m3.numFilas()){//si nos encontramos en el ultmimo elemento de la fila
                        i = 0;
                        j = j+1;
                    }else{
                        i=i+1;
                    }
                }

            }
        }
        return m3;
    }

}
