package e3;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class GunfightTest {
    Behavior1 b1 = new Behavior1();
    Behavior2 b2 = new Behavior2();
    Behavior3 b3 = new Behavior3();
    Gunslinger g1 = new Gunslinger(b1);
    Gunslinger g2 = new Gunslinger(b2);
    Gunslinger g3 = new Gunslinger(b3);
    Gunslinger g4 = new Gunslinger(b1);
    Gunfight f = new Gunfight();

    @Test void gunslingerTest(){
        List<GunslingerAction> list = new ArrayList<>();
        Gunslinger g = new Gunslinger(b1);

        assertEquals(b1, g.getBehavior());
        assertEquals(0, g.getLoads());
        assertEquals(0, g.getRivalLoads());
        assertEquals(list, g.getRivalActions());

        g.setLoads(2);
        g.setLoads_rival(3);
        g.rivalAction(GunslingerAction.PROTECT);
        list.add(GunslingerAction.PROTECT);
        g.rivalAction(GunslingerAction.RELOAD);
        list.add(GunslingerAction.RELOAD);
        g.rivalAction(GunslingerAction.SHOOT);
        list.add(GunslingerAction.SHOOT);
        g.rivalAction(GunslingerAction.MACHINE_GUN);
        list.add(GunslingerAction.MACHINE_GUN);
        g.setBehavior(b2);
        assertEquals(b2, g.getBehavior());
        assertEquals(2, g.getLoads());
        assertEquals(3, g.getRivalLoads());
        assertEquals(list, g.getRivalActions());

    }

    @Test
    public void setBehaviorTest(){
        g1.setBehavior (b1);
        assertEquals(g1.getBehavior(), b1);
    }
    @Test
    public void duelTest() {
        f.duel(g1, g2);
        f.duel(g3,g1);
        f.duel(g1,g4); //enfrentamos a dos con el mismo comportamiento
    }

    @Test
    public void behavior1Test(){
        Gunslinger p1 = new Gunslinger(b1);

        //en la primera ronda recarga
        assertEquals(GunslingerAction.RELOAD, b1.action(p1));
        p1.setLoads(p1.getLoads()+1);

        //en la segunda ronda nos protejemos
        p1.rivalAction(GunslingerAction.RELOAD);
        assertEquals(GunslingerAction.PROTECT, b1.action(p1));

        //si en la segunda ronda disparo, disparamos en la tercera
        p1.rivalAction(GunslingerAction.SHOOT);
        assertEquals(GunslingerAction.SHOOT, b1.action(p1));
        p1.setLoads(p1.getLoads()-1);

        /* para comprobar los comprtamiento que dependen de las dos ultimas acciones del rival seguimos la secuencia
         de ataques del rival spsssrrppr de esta forma se dan todos los casos posibles de behavior1 */

        p1.setLoads(p1.getLoads()+1);
        p1.rivalAction(GunslingerAction.PROTECT);
        assertEquals(GunslingerAction.SHOOT, b1.action(p1));


        p1.rivalAction(GunslingerAction.SHOOT);
        p1.setLoads_rival(1);
        assertEquals(GunslingerAction.PROTECT, b1.action(p1));

        p1.rivalAction(GunslingerAction.SHOOT);
        assertEquals(GunslingerAction.RELOAD, b1.action(p1));


        p1.rivalAction(GunslingerAction.SHOOT);
        p1.setLoads_rival(3);
        assertEquals(GunslingerAction.PROTECT, b1.action(p1));



        p1.rivalAction(GunslingerAction.RELOAD);
        assertEquals(GunslingerAction.RELOAD, b1.action(p1)); //como solo tiene una bala

        p1.rivalAction(GunslingerAction.RELOAD);
        assertEquals(GunslingerAction.SHOOT, b1.action(p1)); //como tiene balas dispara


        p1.rivalAction(GunslingerAction.PROTECT);
        assertEquals(GunslingerAction.RELOAD, b1.action(p1));


        p1.rivalAction(GunslingerAction.PROTECT);
        assertEquals(GunslingerAction.RELOAD, b1.action(p1));

        p1.setLoads(0);
        p1.rivalAction(GunslingerAction.RELOAD);
        assertEquals(GunslingerAction.RELOAD, b1.action(p1)); //como no hay balas recargamos


        //casos especificos
        p1.setLoads(4); //si tenemos 4 balas recargamos
        assertEquals(GunslingerAction.RELOAD, b1.action(p1));
        p1.setLoads(p1.getLoads()+1); //ahora tenemos 5 por lo que ametrallamos
        assertEquals(GunslingerAction.MACHINE_GUN, b1.action(p1));

        p1.setLoads_rival(4);
        p1.setLoads(0);
        //si el rival tiene 4 y no  tengo balas
        assertEquals(GunslingerAction.RELOAD, b1.action(p1));
        p1.setLoads(p1.getLoads()+1);
        //si el rival tiene 4 y tengo balas
        assertEquals(GunslingerAction.SHOOT, b1.action(p1));

    }


    @Test
    public void behavior2Test(){

        Gunslinger p2 = new Gunslinger(b2);
        //en la primera ronda recarga
        assertEquals(GunslingerAction.RELOAD, b2.action(p2));
        p2.setLoads(p2.getLoads()+1);

        //en la segunda ronda disparamos
        p2.rivalAction(GunslingerAction.RELOAD);
        assertEquals(GunslingerAction.SHOOT, b2.action(p2));

        //en la tercera ronda nos protegemos
        p2.rivalAction(GunslingerAction.SHOOT);
        assertEquals(GunslingerAction.PROTECT, b2.action(p2));
        p2.setLoads(p2.getLoads()-1);

        /* para comprobar los comprtamiento que dependen de las dos ultimas acciones del rival seguimos la secuencia
         de ataques del rival spsssrrpprs de esta forma se dan todos los casos posibles de behavior1 */

        p2.setLoads_rival(2);

        p2.setLoads(p2.getLoads()+1);
        p2.rivalAction(GunslingerAction.PROTECT);
        assertEquals(GunslingerAction.SHOOT, b2.action(p2));

        p2.rivalAction(GunslingerAction.SHOOT);
        assertEquals(GunslingerAction.RELOAD, b2.action(p2));

        p2.rivalAction(GunslingerAction.SHOOT);
        assertEquals(GunslingerAction.SHOOT, b2.action(p2)); //como tenemos balas disparamos

        p2.setLoads_rival(0);
        p2.setLoads(0);
        p2.rivalAction(GunslingerAction.SHOOT); //si no tenemos balas recargamos
        assertEquals(GunslingerAction.RELOAD, b2.action(p2));

        p2.setLoads(2);
        p2.rivalAction(GunslingerAction.RELOAD);
        assertEquals(GunslingerAction.SHOOT, b2.action(p2));

        p2.rivalAction(GunslingerAction.RELOAD);
        assertEquals(GunslingerAction.PROTECT, b2.action(p2));

        p2.rivalAction(GunslingerAction.PROTECT);
        assertEquals(GunslingerAction.PROTECT, b2.action(p2));

        p2.rivalAction(GunslingerAction.PROTECT);
        assertEquals(GunslingerAction.RELOAD, b2.action(p2));

        p2.rivalAction(GunslingerAction.RELOAD);
        assertEquals(GunslingerAction.PROTECT, b2.action(p2));

        p2.rivalAction(GunslingerAction.SHOOT);
        assertEquals(GunslingerAction.PROTECT, b2.action(p2));


        //casos especificos
        p2.setLoads(1);
        p2.setLoads_rival(0);
        assertEquals(GunslingerAction.SHOOT, b2.action(p2));
        p2.setLoads_rival(4);
        assertEquals(GunslingerAction.SHOOT, b2.action(p2));

        p2.setLoads(5);
        assertEquals(GunslingerAction.MACHINE_GUN, b2.action(p2));

        p2.setLoads(0);
        p2.setLoads_rival(4);
        assertEquals(GunslingerAction.PROTECT, b2.action(p2));
        p2.setLoads_rival(0);
        assertEquals(GunslingerAction.RELOAD, b2.action(p2));
    }

    @Test
    public void behavior3Test(){
        //el comportamiento de behavior3 es aleatorio
        Gunslinger p3 = new Gunslinger(b3);

        //comprobamos que si no tiene balas no dispara
        for(int i=0; i<100; i++){
            assertNotEquals(GunslingerAction.SHOOT, b3.action(p3));
            assertNotEquals(GunslingerAction.MACHINE_GUN, b3.action(p3));
        }

        //comprobamos que si no tiene 5 balas no ametralla, pero si puede disparar
        p3.setLoads(2);
        for(int i=0; i<100; i++){
            assertNotEquals(GunslingerAction.MACHINE_GUN, b3.action(p3));
        }


    }

}
