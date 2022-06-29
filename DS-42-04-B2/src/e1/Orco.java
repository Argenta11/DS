package e1;

public class Orco extends Bestia{


    public Orco(String nombre,int vida , int resistencia) {
        setNombre(nombre);
        setVida(vida);
        setResistencia(resistencia);
    }

    public void ataque(Heroe h, int dado){
        if(dado>0.9*h.getResistencia()){
            h.setVida(h.getVida() - ( dado - (int) (0.9*h.getResistencia()) ) );
        }

    }
}