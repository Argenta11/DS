package e1;

import java.util.ArrayList;
import java.util.Random;

public class Juego {

    //PODEMOS PONERLO FINAL PARA QUE NA VEZ INICIALIZADOS NO S EPUEDAN MODIFICAR
    public final ArrayList<Heroe> ejercito_h;
    public final ArrayList<Bestia> ejercito_b;

    public Juego(){
        this.ejercito_h = new ArrayList<>();
        this.ejercito_b = new ArrayList<>();

    }

    public void addHeroe(Heroe heroe){
        ejercito_h.add(heroe);
    }
    public void addBestias(Bestia bestia){
        ejercito_b.add(bestia);
    }
    private void deleteHeroe(int pos){
        ejercito_h.remove(pos);
    }
    private void deleteBestia(int pos){
        ejercito_b.remove(pos);
    }




    public void batalla(){
        int j=0;
        while(ejercito_b.size()!=0 && ejercito_h.size()!=0){
            j++;
            System.out.println("Turn " + j + ":");

            int i=0;
            int contador=0;
            int contador2=0;
            int diferencia=0;
            int diferencia2=0;
            int dado;



            while ((i < this.ejercito_b.size()) && (i < this.ejercito_h.size())) {
                for (Bestia bestia : ejercito_b) {
                    contador = contador + bestia.getVida();
                }
                for (Heroe heroe : ejercito_h) {
                    contador2 = contador2 + heroe.getVida();
                }
                if (contador > contador2) {
                    diferencia2 = contador - contador2;
                    diferencia2 = diferencia2 / 50;
                } else if (contador2 > contador) {
                    diferencia = contador2 - contador;
                    diferencia = diferencia / 50;
                }

                System.out.println("Fight between " + ejercito_h.get(i).toString() + " and " + ejercito_b.get(i).toString());
                //dado es el valor sacado al tirar el dado mas la diferencia

                dado=ejercito_h.get(i).tirarDado();
                if((dado + diferencia2)<=100){
                    dado=dado+diferencia2;
                }
                ejercito_h.get(i).ataque(ejercito_b.get(i), dado);

                dado=ejercito_b.get(i).tirarDado() ;
                if((dado + diferencia)<=90){
                    dado=dado+diferencia;
                }
                ejercito_b.get(i).ataque(ejercito_h.get(i), dado);

                i++;

            }


            for(int k=0; k<ejercito_h.size(); k++){
                if(ejercito_h.get(k).getVida()<=0){
                    System.out.println(ejercito_h.get(k).getClass().getSimpleName() + " " + ejercito_h.get(k).getNombre() + " dies");
                    deleteHeroe(k);
                }

            }

            for(int t=0; t<ejercito_b.size(); t++){
                if(ejercito_b.get(t).getVida()<=0){
                    System.out.println(ejercito_b.get(t).getClass().getSimpleName() + " "  + ejercito_b.get(t).getNombre() + " dies");
                    deleteBestia(t);
                }

            }

        }

        if(ejercito_b.size()==0){
            System.out.println("HEROES WIN !!");
        }
        if (ejercito_h.size()==0){
            System.out.println("BESTIAS WIN !!");
        }

    }




    public void batalla(long semilla){
        int j=0;
        while(ejercito_b.size()!=0 && ejercito_h.size()!=0){
            j++;
            System.out.println("Turn " + j + ":");

            int i=0;
            int dado;

            //inicializamos las semillas
            Random rnd_b = new Random();
            Random rnd_h = new Random();

            rnd_b.setSeed(semilla);
            rnd_h.setSeed(semilla);



            while ((i < this.ejercito_b.size()) && (i < this.ejercito_h.size())) {

                System.out.println("Fight between " + ejercito_h.get(i).toString() + " and " + ejercito_b.get(i).toString());


                //tiramos el dado y heroe ataca a bestia
                dado=ejercito_h.get(i).tirarDado(rnd_h) ;
                ejercito_h.get(i).ataque(ejercito_b.get(i), dado);

                //tiramos el dado y heroe ataca a bestia
                dado=ejercito_b.get(i).tirarDado(rnd_b) ;
                ejercito_b.get(i).ataque(ejercito_h.get(i), dado);

                i++;

            }

            for(int k=0; k<ejercito_h.size(); k++){
                if((ejercito_h.get(k).getVida())<=0){
                    System.out.println(ejercito_h.get(k).getClass().getSimpleName() + " " + ejercito_h.get(k).getNombre() + " dies");
                    deleteHeroe(k);
                }

            }
            for(int t=0; t<ejercito_b.size(); t++){
                if((ejercito_b.get(t).getVida())<=0){
                    System.out.println(ejercito_b.get(t).getClass().getSimpleName() + " "  + ejercito_b.get(t).getNombre() + " dies");
                    deleteBestia(t);
                }

            }


        }
        if(ejercito_b.size()==0){
            System.out.println("HEROES WIN !!");
        }else{
            System.out.println("BESTIAS WIN !!");
        }

    }

}
