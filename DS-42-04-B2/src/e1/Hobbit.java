package e1;

public class Hobbit extends Heroe{

    public Hobbit(String nombre,int vida , int resistencia) {
        setNombre(nombre);
        setVida(vida);
        setResistencia(resistencia);
    }

    public void ataque(Bestia b, int dado){

        if (b instanceof Trasgo){
            dado=dado-5;
        }
        if(dado>b.getResistencia()){
            b.setVida(b.getVida()-(dado-b.getResistencia()));
        }

    }

}
