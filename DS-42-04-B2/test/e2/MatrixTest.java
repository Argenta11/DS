package e2;

import org.junit.jupiter.api.Test;


import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    int[][] a1= {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}};

    int[][] a2 = {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12}};


    private final Matrix m1=new Matrix(a1, true);
    private final Matrix m2=new Matrix(a2, false);
    private final Matrix m3=new Matrix(5, 2, true);



    @Test
    void constructorsTest(){

        int[][] a_1= {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        int[][] a_2= {{1, 2, 3},
                {4, 5},
                {7}};

        Matrix m_1=new Matrix(a_1, true);
        Matrix m_2=new Matrix(5, 2, true);
        //si no es rectangular lanza una excepcion
        assertThrows(IllegalArgumentException.class, () -> new Matrix(a_2, true));


        for(int i = 0; i<5; i++){
            for(int j=0; j<2;j++){
                assertEquals(0, m_2.getValor(i, j)); //comprobamos q son ceros
            }
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                assertEquals(a_1[i][j], m_1.getValor(i, j)); //comprobamos q coinciden
            }
        }

    }

    @Test
    void lengthTest(){
        assertEquals(3, m1.numFilas());
        assertEquals(3, m1.numColumnas());
        assertEquals(5, m3.numFilas());
        assertEquals(2, m3.numColumnas());
        assertEquals(4, m2.numFilas());
        assertEquals(3, m2.numColumnas());

    }


    @Test
    void leerElementoTest(){
        int[][] a1= {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};


        for(int i = 0; i<5; i++){
            for(int j=0; j<2;j++){
                assertEquals(0, m3.getValor(i, j)); //comprobamos q son ceros
            }
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                assertEquals(a1[i][j], m1.getValor(i, j)); //comprobamos q coinciden
            }
        }

        //comprobamos que lanza excepcion cuando las posiciones no son validas
        assertThrows(IllegalArgumentException.class, () -> m3.getValor(3, 4));
        assertThrows(IllegalArgumentException.class, () -> m3.getValor(8, 1));
        assertThrows(IllegalArgumentException.class, () -> m3.getValor(8, 4));
    }

    @Test
    void modificarElementoTest(){
        m1.setValor(0, 0, 10);
        assertEquals(10, m1.getValor(0, 0));
        m1.setValor(0, 0, 1);
        assertEquals(1, m1.getValor(0, 0));

        for(int i=0; i<5; i++){
            for(int j=0; j<2; j++){
                m3.setValor(i, j, 3); //ponemos todos los elementos a 3
            }
        }
        for(int i=0; i<5; i++){
            for(int j=0; j<2; j++){
                assertEquals(3, m3.getValor(i, j)); //comprobamos q se cambiaron los valores a 3
            }
        }

        //comprobamos que lanza excepcion cuando las posiciones no son validas
        assertThrows(IllegalArgumentException.class, () -> m3.setValor(3, 4, 10));
        assertThrows(IllegalArgumentException.class, () -> m3.setValor(8, 1, 10));

    }

    @Test
    void copiaMatrizTest(){
        int[][] a =m1.clonarMatriz();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                assertEquals(m1.getValor(i, j), a[i][j]); //comprobamos q los valores coinciden
            }
        }

    }

    @Test
    void copiaFilaTest(){

        int[] a =m1.clonarFila(0);
        int[] b ={1, 2, 3};
        int[] c ={4, 5, 6};
        int[] d ={7, 8, 9};
        assertArrayEquals(b, a);

        a=m1.clonarFila(1);
        assertArrayEquals(c, a);

        a=m1.clonarFila(2);
        assertArrayEquals(d, a);

        //comprobamos que lanza excepcion cuando las posiciones no son validas
        assertThrows(IllegalArgumentException.class, () -> m3.clonarFila(9));
        assertThrows(IllegalArgumentException.class, () -> m3.clonarFila(8));

    }

    @Test
    void copiaColumnaTest(){

        int[] a =m1.clonarColumna(0);
        int[] b ={1, 4, 7};
        int[] c ={2, 5, 8};
        int[] d ={3, 6, 9};
        assertArrayEquals(b, a);

        a=m1.clonarColumna(1);
        assertArrayEquals(c, a);

        a=m1.clonarColumna(2);
        assertArrayEquals(d, a);


        //comprobamos que lanza excepcion cuando las posiciones no son validas
        assertThrows(IllegalArgumentException.class, () -> m3.clonarColumna(4));
        assertThrows(IllegalArgumentException.class, () -> m3.clonarColumna(6));
    }


    @Test
    void stringTest(){
        assertEquals("[1, 2, 3]\n[4, 5, 6]\n[7, 8, 9]\n", m1.matrizString());
        assertEquals("[0, 0]\n[0, 0]\n[0, 0]\n[0, 0]\n[0, 0]\n", m3.matrizString());
    }





    @Test
    void RowColumIteratorTest(){
        //creamos un iterador para la matriz m1
        RowColumIterator i1 = new RowColumIterator(0, -1, m1);
        //creamos un iterador para la matriz m2 pero q los indices apunten al ultimo elemento
        RowColumIterator i2 = new RowColumIterator(3, 2, m2);

        //comprobamos que el constructor asigna los valores correctos
        assertEquals(i1.getI(), 0);
        assertEquals(i1.getJ(), -1);
        assertEquals(i1.getM(), m1);
        assertEquals(i2.getI(), 3);
        assertEquals(i2.getJ(), 2);
        assertEquals(i2.getM(), m2);

        //test hasNext()
        assertTrue(i1.hasNext());
        assertFalse(i2.hasNext()); //i2 apunta al ultimo elemento, por lo q devolvera false

        //test next()
        int[] array={1, 2, 3, 4, 5, 6, 7, 8, 9};//valores de la matriz m1 en formato row-colum
        int valor;
        for(int i=0; i<9; i++){
            valor= i1.next();
            assertEquals(array[i], valor);
        }

        assertThrows(NoSuchElementException.class, () -> i1.next());
        assertThrows(NoSuchElementException.class, () -> i2.next());
        assertThrows(UnsupportedOperationException.class, () -> i1.remove());
        assertThrows(UnsupportedOperationException.class, () -> i2.remove());

    }


    @Test
    void ColumRowIteratorTest(){
        //creamos un iterador para la matriz m1
        ColumRowIterator i1 = new ColumRowIterator(-1, 0, m1);
        //creamos un iterador para la matriz m2 pero q los indices apunten al ultimo elemento
        ColumRowIterator i2 = new ColumRowIterator(3, 2, m2);

        //comprobamos que el constructor asigna los valores correctos
        assertEquals(i1.getI(), -1);
        assertEquals(i1.getJ(), 0);
        assertEquals(i1.getM(), m1);
        assertEquals(i2.getI(), 3);
        assertEquals(i2.getJ(), 2);
        assertEquals(i2.getM(), m2);

        //test hasNext()
        assertTrue(i1.hasNext());
        assertFalse(i2.hasNext()); //i2 apunta al ultimo elemento, por lo q devolvera false

        //test next()
        int[] array={1, 4, 7, 2, 5, 8, 3, 6, 9};//valores de la matriz m1 en formato row-colum
        int valor;
        for(int i=0; i<9; i++){
            valor= i1.next();
            assertEquals(array[i], valor);
        }

        //como no hay siguiente lanza excepcion
        assertThrows(NoSuchElementException.class, () -> i1.next());
        assertThrows(NoSuchElementException.class, () -> i2.next());
        assertThrows(UnsupportedOperationException.class, () -> i1.remove());
        assertThrows(UnsupportedOperationException.class, () -> i2.remove());

    }


    @Test
    void sumarMatricesTest(){
        int[][] matriz_m5= {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        int[][] matriz_m6= {{6, 2, 7},
                {5, 2, 1},
                {9, 6, 4}};

        int[][] matriz_m7= {{9, 2, 1, 4},
                {2, 3, 6, 7},
                {7, 5, 9, 0}};

        int[][] matriz_m8= {{4, 6, 2, 3},
                {1, 5, 9, 6},
                {2, 1, 3, 1}};

        //array recorrido row-colum
        int[] array_m5={1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] array_m6={6, 2, 7, 5, 2, 1, 9, 6, 4};
        //array recorrido colum-row
        int[] array_m7={9, 2, 7, 2, 3, 5, 1, 6, 9, 4, 7, 0};
        int[] array_m8={4, 1, 2, 6, 5, 1, 2, 9, 3, 3, 6, 1};

        Matrix m4 = new Matrix(3, 3, false);
        //matrices con recorrido row-colum
        Matrix m5 = new Matrix(matriz_m5, true);
        Matrix m6 = new Matrix(matriz_m6, true);
        //matrices con recorrido colum-row
        Matrix m7 = new Matrix(matriz_m7, false);
        Matrix m8 = new Matrix(matriz_m8, false);

        Matrix resul1 = new Matrix(3, 3, true);
        Matrix resul2 = new Matrix(3, 4, false);
        //intentamos sumar matrices de dimensiones diferentes
        assertThrows(ArithmeticException.class, () -> MatrixAddition.sumaMatrices(m1, m2));

        //intentamos sumar matrices con valores de rec diferentes
        assertThrows(IllegalArgumentException.class, () -> MatrixAddition.sumaMatrices(m1, m4));


        //suma con iterador row-colum de matrices 3x3
        resul1=MatrixAddition.sumaMatrices(m5, m6);
        Iterator i1;
        i1 = resul1.iterator();
        int k;
        for(int i=0; i<9; i++){
            k =  (int)i1.next();
            assertEquals(array_m5[i]+array_m6[i],k);
        }


        //suma con iterador colum-row de matrices 3x4
        resul2=MatrixAddition.sumaMatrices(m7, m8);
        Iterator i2;
        i2 = resul2.iterator();

        for(int i=0; i<12; i++){
            k =  (int)i2.next();
            assertEquals(array_m7[i]+array_m8[i],k);
        }




    }



}
