package e1;

public class Humano extends Heroe{

    public Humano(String nombre,int vida , int resistencia) {
        setNombre(nombre);
        setVida(vida);
        setResistencia(resistencia);
    }


    public void ataque(Bestia b, int dado){
        if(dado>b.getResistencia()){
            b.setVida(b.getVida()-(dado-b.getResistencia()));
        }

    }

}