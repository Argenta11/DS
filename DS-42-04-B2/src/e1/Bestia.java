package e1;

import java.util.Random;

abstract class Bestia extends Personaje implements Dado {


    //poner metodos comunes a todos los tipos de heroes para que los hereden

    public abstract void ataque(Heroe h, int dado);
    @Override
    public int tirarDado() {
        int dado;
        dado= (int) Math.floor(Math.random()*90)+1;
        return dado;
    }


    public int tirarDado(Random rnd){
        return (int)(rnd.nextDouble() * 91.0);
    }

    public String toString() {
        return getNombre() + "(Energy=" + getVida() + ")";
    }
}
