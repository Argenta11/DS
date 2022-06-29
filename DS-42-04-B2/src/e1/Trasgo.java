package e1;

public class Trasgo extends Bestia{

    public Trasgo(String nombre,int vida , int resistencia) {
        setNombre(nombre);
        setVida(vida);
        setResistencia(resistencia);
    }

    public void ataque(Heroe h, int dado){

        if(dado>h.getResistencia()){
            h.setVida(h.getVida()-(dado-h.getResistencia()));
        }

    }

}
