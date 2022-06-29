package e1;

public class Elfo extends Heroe {



    public Elfo(String nombre,int vida , int resistencia) {
        setNombre(nombre);
        setVida(vida);
        setResistencia(resistencia);
    }

    public void ataque(Bestia b, int dado){

        if(b instanceof Orco){
            dado=dado+10;
        }
        if(dado>b.getResistencia()){
            b.setVida(b.getVida()-(dado-b.getResistencia()));
        }
    }

}
