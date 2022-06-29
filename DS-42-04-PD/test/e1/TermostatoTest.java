package e1;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class TermostatoTest {



    @Test
    void modeTest(){
        Termostato t = new Termostato();

        Manual manual = Manual.getInstancia();
        Off off = Off.getInstancia();
        Timer timer = Timer.getInstancia();
        Program program = Program.getInstancia();
        //comprobamos que inicialmenete el termosato esta en apagado (OFF) y en modo Off

        assertEquals(off, t.getMode());
        assertEquals(State.OFF, t.getState());

        t.newTemperature(20);
        assertEquals(20, t.getCurrentTemperature());

        t.encendida();
        assertEquals(manual, t.getMode());
        assertEquals(State.ON, t.getState());
        t.newTemperature(23);
        assertEquals(23, t.getCurrentTemperature());
        t.encendida(); //ya estaba encendida, por lo que no pasa nada
        assertEquals(manual, t.getMode());
        assertEquals(State.ON, t.getState());


        t.temporizador(20);
        assertEquals(timer, t.getMode());
        assertEquals(State.ON, t.getState());
        t.newTemperature(23);
        assertEquals(timer, t.getMode());
        assertEquals(State.ON, t.getState());
        t.programar(23.4); //no se puede cambiar a programar sin pasar por Off, por lo que seguira el timer
        assertEquals(timer, t.getMode());
        assertEquals(State.ON, t.getState());
        t.newTemperature(26);
        t.newTemperature(22);
        t.newTemperature(24);
        assertEquals(off, t.getMode());
        assertEquals(State.OFF, t.getState());

        t.programar(23);
        assertEquals(23, t.getLimit());
        //tiene como limite 23
        t.newTemperature(26);
        assertEquals(program, t.getMode());
        assertEquals(State.OFF, t.getState());
        t.newTemperature(21);
        assertEquals(program, t.getMode());
        assertEquals(State.ON, t.getState());
        t.newTemperature(22);
        assertEquals(program, t.getMode());
        assertEquals(State.ON, t.getState());
        t.programar(25); //se sobrescribe
        assertEquals(25, t.getLimit());
        t.newTemperature(24);
        assertEquals(program, t.getMode());
        assertEquals(State.ON, t.getState());
        t.newTemperature(26);
        assertEquals(program, t.getMode());
        assertEquals(State.OFF, t.getState());

        t.temporizador(20);
        //seguimos estando en modo program, por q para pasar a timer primero hay que pasar por Off
        assertEquals(program, t.getMode());
        assertEquals(State.OFF, t.getState());
        t.apagar();
        assertEquals(off, t.getMode());
        assertEquals(State.OFF, t.getState());
        t.apagar(); //ya estaba apagado, por lo que no hace nada
        assertEquals(off, t.getMode());


        t.temporizador(30);
        assertEquals(timer, t.getMode());
        assertEquals(State.ON, t.getState());
        t.temporizador(20); //se sobrescribe
        assertEquals(20, t.getTime());
        assertEquals(timer, t.getMode());
        assertEquals(State.ON, t.getState());
        t.apagar();
        assertEquals(off, t.getMode());
        assertEquals(State.OFF, t.getState());

    }

    @Test
    void screenInfoTest(){
        Termostato t = new Termostato();
        t.encendida();
        t.newTemperature(24.6);
        assertEquals("24.6 ON M\n", t.screenInfo());

        t.programar(23);
        t.newTemperature(23.7);
        assertEquals("23.7 OFF P 23.0\n", t.screenInfo());
        t.newTemperature(22.5);
        assertEquals("22.5 ON P 23.0\n", t.screenInfo());

        t.apagar();
        t.newTemperature(25.4);
        assertEquals("25.4 OFF O\n", t.screenInfo());

        t.temporizador(25);
        assertEquals("25.4 ON T 25\n", t.screenInfo());

    }

    @Test
    void historialTest(){
        Termostato t = new Termostato();

        t.newTemperature(20);
        assertEquals("20.0 Modo Off - calefaccion apagada.", t.getHistorial().get(0));

        t.encendida();
        assertEquals("Se activa el modo Manual", t.getHistorial().get(1));
        t.newTemperature(21);
        assertEquals("21.0 Modo Manual - calefaccion encendida.", t.getHistorial().get(2));

        t.temporizador(9);
        assertEquals("Se activa el modo Timer 9 minutos", t.getHistorial().get(3));
        t.newTemperature(22);
        assertEquals("22.0 Modo Timer (faltan 4 minutos) - calefaccion encendida.", t.getHistorial().get(4));
        t.newTemperature(23);
        assertEquals("Se desactiva el modo Timer", t.getHistorial().get(5));

        t.programar(23);
        assertEquals("Se activa el modo Program a 23.0 grados", t.getHistorial().get(6));
        t.newTemperature(24);
        assertEquals("24.0 Modo Program (a 23.0 grados) - calefaccion apagada.", t.getHistorial().get(7));
        t.newTemperature(22);
        assertEquals("22.0 Modo Program (a 23.0 grados) - calefaccion encendida.", t.getHistorial().get(8));


        t.apagar();
        assertEquals("Se activa el modo Off", t.getHistorial().get(9));
        t.newTemperature(21);
        assertEquals("21.0 Modo Off - calefaccion apagada.", t.getHistorial().get(10));


        //Comprobamos que devuelve el String correspondiente
        String historial;
        historial="20.0 Modo Off - calefaccion apagada.\n" +
                "Se activa el modo Manual\n" +
                "21.0 Modo Manual - calefaccion encendida.\n" +
                "Se activa el modo Timer 9 minutos\n" +
                "22.0 Modo Timer (faltan 4 minutos) - calefaccion encendida.\n" +
                "Se desactiva el modo Timer\n" +
                "Se activa el modo Program a 23.0 grados\n" +
                "24.0 Modo Program (a 23.0 grados) - calefaccion apagada.\n" +
                "22.0 Modo Program (a 23.0 grados) - calefaccion encendida.\n" +
                "Se activa el modo Off\n" +
                "21.0 Modo Off - calefaccion apagada.\n";

        assertEquals(historial, t.printHistorial());

    }

    @Test
    void funcionamientoTest(){

        //Ejecutamos el termostato de forma completa y sacamos por pantalla los valores
        Termostato t = new Termostato();
        t.newTemperature(19);
        t.encendida();
        System.out.println( t.screenInfo());
        t.newTemperature(20);
        t.newTemperature(21);
        t.programar(24);
        t.newTemperature(22);
        t.newTemperature(25);
        System.out.println( t.screenInfo());
        t.newTemperature(24);
        t.newTemperature(22);
        t.newTemperature(23);
        System.out.println( t.screenInfo());
        t.encendida();
        t.newTemperature(24);
        t.newTemperature(25);
        t.apagar();
        t.temporizador(10);
        System.out.println( t.screenInfo());
        t.newTemperature(25);
        System.out.println( t.screenInfo());
        t.newTemperature(26);
        System.out.println( t.screenInfo());
        t.newTemperature(24);
        t.newTemperature(23);
        t.newTemperature(23);
        t.programar(22);
        t.newTemperature(22);
        t.newTemperature(25);
        t.newTemperature(26);
        t.apagar();
        t.newTemperature(23);
        t.newTemperature(22);
        t.apagar(); //no pasa nada
        t.newTemperature(21);
        t.newTemperature(20);
        t.temporizador(30);
        t.newTemperature(21);
        t.encendida();
        t.newTemperature(23.5);
        t.apagar();

        System.out.println(t.printHistorial());


    }



}
