package e1;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {

    Elfo e1 = new Elfo("elfo 1", 200, 40);
    Elfo e2 = new Elfo("elfo 2", 440, 10);
    Hobbit h1 = new Hobbit("hobbit 1", 100, 20);
    Hobbit h2 = new Hobbit("hobbit 2", 160, 30);
    Humano hm1 = new Humano("humano 1", 300, 20);
    Orco o1 = new Orco("orco 1", 600, 40);
    Orco o2 = new Orco("orco 2", 300, 30);
    Trasgo t1 = new Trasgo("trasgo 1", 100, 40);
    Trasgo t2 = new Trasgo("trasgo 2", 200, 30);

    @Test
    void tirarDadoTest(){

        //comprobamos que los el rango del dado es correcto para cada tipo de personaje
        for(int i=0; i<5;i++){
            assertTrue(e1.tirarDado() <= 100);
            assertTrue(e2.tirarDado() <= 100);
            assertTrue(h1.tirarDado() <= 100);
            assertTrue(h2.tirarDado() <= 100);
            assertTrue(hm1.tirarDado() <= 100);
            assertTrue(o1.tirarDado() <= 90);
            assertTrue(o2.tirarDado() <= 90);
            assertTrue(t1.tirarDado() <= 90);
            assertTrue(t2.tirarDado() <= 90);
        }

        Random rnd_b1 = new Random();
        Random rnd_b2 = new Random();
        Random rnd_h1 = new Random();
        Random rnd_h2 = new Random();

        //inicializamos con el mismo valor de semilla
        rnd_b1.setSeed(63);
        rnd_b2.setSeed(63);
        rnd_h1.setSeed(20);
        rnd_h2.setSeed(20);

        //comprobamos que la secuencia para una semilla es siempre la misma
        for(int i=0; i<7; i++){
            //comparamos los valores al tirar el dado trucado dos personajes
            assertEquals(t2.tirarDado(rnd_b1), o1.tirarDado(rnd_b2));
            assertEquals(h1.tirarDado(rnd_h1), e2.tirarDado(rnd_h2));
        }


    }

    @Test
    void getterTest(){
        assertEquals(200, e1.getVida());
        assertEquals(40, e1.getResistencia());
        assertEquals("elfo 1", e1.getNombre());
        assertEquals(300, o2.getVida());
        assertEquals(30, o2.getResistencia());
        assertEquals("orco 2", o2.getNombre());
    }

    @Test
    void setterTest(){
        Hobbit h = new Hobbit("hobbit", 150, 50);
        Trasgo t = new Trasgo("trasgo", 220, 20);
        h.setResistencia(25);
        h.setVida(300);
        h.setNombre("hobbit pequeño");

        t.setResistencia(10);
        t.setVida(200);
        t.setNombre("trasgo fuerte");


        assertEquals(300, h.getVida());
        assertEquals(25, h.getResistencia());
        assertEquals("hobbit pequeño", h.getNombre());
        assertEquals(200, t.getVida());
        assertEquals(10, t.getResistencia());
        assertEquals("trasgo fuerte", t.getNombre());
    }




    @Test
    void toStringTest(){//comprobamos si imrpime lo que tiene que imprimir
        assertEquals("elfo 1(Energy=200)", e1.toString());
        assertEquals("elfo 2(Energy=440)", e2.toString());
        assertEquals("hobbit 1(Energy=100)", h1.toString());
        assertEquals("hobbit 2(Energy=160)", h2.toString());
        assertEquals("humano 1(Energy=300)", hm1.toString());
        assertEquals("orco 1(Energy=600)", o1.toString());
        assertEquals("orco 2(Energy=300)", o2.toString());
        assertEquals("trasgo 1(Energy=100)", t1.toString());
        assertEquals("trasgo 2(Energy=200)", t2.toString());

    }

    @Test
    void ataqueTest(){
        //hacemps un ataque y comparamos la vida que tendria que quedarle con la que le queda

        e1.ataque(o1, 60);
        /*esperamo la vida de antes (600) menos el valor del ataque, que es el dado(60) menos la resistencia(40) y menos 10
         * por ser un orco al que ataca el elfo  */
        assertEquals(600-((60-40)+10), o1.getVida());

        e2.ataque(t2, 40);
        assertEquals(200-(40-30), t2.getVida());

        h1.ataque(t1, 70);
        assertEquals(100-(70-5-40), t1.getVida());//le restamos 5 a mayores por q los hobbits pierden 5 al luchar contra trasgos

        hm1.ataque(o2, 86);
        assertEquals(300-(86-30), o2.getVida());

        o1.ataque(h2, 79);
        assertEquals(160-(79-30*0.9), h2.getVida());

        o2.ataque(e1, 27);
        assertEquals(200, e1.getVida()); //como el dado es menor a la resistencia no le resta nada

        t1.ataque(e2, 40);
        assertEquals(440-(40-10), e2.getVida());

        t2.ataque(h1, 85);
        assertEquals(100-(85-20), h1.getVida());

    }

    @Test
    void juegoTest(){

        Juego j1 = new Juego();

        assertEquals(j1.ejercito_h.size(), 0);
        assertEquals(j1.ejercito_b.size(), 0);

        j1.addHeroe(e1);
        j1.addBestias(o1);
        assertEquals(j1.ejercito_h.size(), 1);
        assertEquals(j1.ejercito_b.size(), 1);
        //los de posicion 1 son los que acabamos de añadir
        assertEquals(j1.ejercito_h.get(0), e1);
        assertEquals(j1.ejercito_b.get(0), o1);
        //añadimos heroes
        j1.addHeroe(e2);
        j1.addHeroe(h1);
        j1.addHeroe(h2);
        j1.addHeroe(hm1);
        //añadimos bestias
        j1.addBestias(o2);
        j1.addBestias(t1);
        j1.addBestias(t2);
        assertEquals(j1.ejercito_h.size(), 5);
        assertEquals(j1.ejercito_b.size(), 4);
        //los ultimos en la lista son los ultimos añadidos
        assertEquals(j1.ejercito_h.get(4), hm1);
        assertEquals(j1.ejercito_b.get(3), t2);
    }
    @Test
    void batallaDadoAleatorioTest(){
        Juego j1 = new Juego();
        //añadimos heroes
        j1.addHeroe(e1);
        j1.addHeroe(e2);
        j1.addHeroe(h1);
        j1.addHeroe(h2);
        j1.addHeroe(hm1);
        //añadimos bestias
        j1.addBestias(o1);
        j1.addBestias(o2);
        j1.addBestias(t1);
        j1.batalla();

        Juego j2 = new Juego();
        //restablecemos los valores
        Elfo e1 = new Elfo("elfo 1", 200, 40);
        Elfo e2 = new Elfo("elfo 2", 440, 10);
        Hobbit h1 = new Hobbit("hobbit 1", 100, 20);
        Hobbit h2 = new Hobbit("hobbit 2", 160, 30);
        Humano hm1 = new Humano("humano 1", 300, 20);
        Orco o1 = new Orco("orco 1", 600, 40);
        Orco o2 = new Orco("orco 2", 300, 30);
        Trasgo t1 = new Trasgo("trasgo 1", 100, 40);
        Trasgo t2 = new Trasgo("trasgo 2", 200, 30);
        //añadimos heroes
        j2.addHeroe(hm1);
        j2.addHeroe(h1);
        //añadimos bestias
        j2.addBestias(o1);
        j2.addBestias(o2);
        j2.addBestias(t1);
        j2.addBestias(t2);
        j2.batalla();
    }

    @Test
    void batallaDadoTrucadoTest(){

        Juego j1 = new Juego();
        //añadimos heroes
        j1.addHeroe(e1);
        j1.addHeroe(e2);
        j1.addHeroe(h1);
        j1.addHeroe(h2);
        j1.addHeroe(hm1);
        //añadimos bestias
        j1.addBestias(o1);
        j1.addBestias(o2);
        j1.addBestias(t1);
        j1.addBestias(t2);
        j1.batalla(30);

        Juego j2 = new Juego();
        //restablecemos los valores
        Elfo e1 = new Elfo("elfo 1", 200, 40);
        Elfo e2 = new Elfo("elfo 2", 440, 10);
        Hobbit h1 = new Hobbit("hobbit 1", 100, 20);
        Hobbit h2 = new Hobbit("hobbit 2", 160, 30);
        Humano hm1 = new Humano("humano 1", 300, 20);
        Orco o1 = new Orco("orco 1", 600, 40);
        Orco o2 = new Orco("orco 2", 300, 30);
        Trasgo t1 = new Trasgo("trasgo 1", 100, 40);
        Trasgo t2 = new Trasgo("trasgo 2", 200, 30);

        //añadimos heroes
        j2.addHeroe(e1);
        j2.addHeroe(e2);
        j2.addHeroe(h1);
        j2.addHeroe(h2);
        j2.addHeroe(hm1);
        //añadimos bestias
        j2.addBestias(o1);
        j2.addBestias(t1);
        j2.batalla(30);


    }

}
