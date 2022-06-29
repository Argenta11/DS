package e1;

import java.util.Random;

abstract class Heroe extends Personaje implements Dado {


    //poner metodos comunes a todos los tipos de heroes para que los hereden

    public abstract void ataque(Bestia b, int dado);

    @Override
    public int tirarDado() {
        int dado, dado2;
        dado = (int) Math.floor(Math.random()*100)+1;
        dado2 = (int) Math.floor(Math.random()*100)+1;
        if (dado2>dado){
            dado=dado2;
        }
        return dado;
    }


    public int tirarDado(Random rnd){
        return (int)(rnd.nextDouble() * 101.0);
    }


    public String toString() {
        return getNombre() + "(Energy=" + getVida() + ")";
    }





}


