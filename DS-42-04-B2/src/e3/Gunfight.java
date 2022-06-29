package e3;

import java.util.ArrayList;

public class Gunfight {


    public void duel(Gunslinger g1, Gunslinger g2){

        //Inicializamos a 0 para que se inicie con la partida anterior
        g1.setLoads(0);
        g1.setLoads_rival(0);
        g1.setAcciones(new ArrayList<>());

        g2.setLoads(0);
        g2.setLoads_rival(0);
        g2.setAcciones(new ArrayList<>());

        int i=0;
        Gunslinger winner = null;
        while(i<50){
            System.out.println("Round " + (i+1) + "---------------------------------");
            GunslingerAction g1_action = g1.action();
            GunslingerAction g2_action = g2.action();
            if((g1_action==GunslingerAction.SHOOT && g1.getLoads()<1) || (g1_action==GunslingerAction.MACHINE_GUN && g1.getLoads()<5)){
                g1_action=GunslingerAction.RELOAD;
            }else if((g2_action==GunslingerAction.SHOOT && g2.getLoads()<1) || (g2_action==GunslingerAction.MACHINE_GUN && g2.getLoads()<5)){
                g2_action=GunslingerAction.RELOAD;
            }
            System.out.println("Gunslinger 1: " + g1_action);
            System.out.println("Gunslinger 2: " + g2_action);


            if(g1_action==GunslingerAction.MACHINE_GUN){
                if(g2_action==GunslingerAction.MACHINE_GUN){
                    System.out.println("Empate");
                }else{
                    //gana g1
                    winner=g1;
                    break;
                }

            }else if(g2_action==GunslingerAction.MACHINE_GUN){
                //gana g2
                winner=g2;
                break;

            }else if(g1_action==GunslingerAction.PROTECT){
                if(g2_action==GunslingerAction.RELOAD){
                    g2.setLoads(g2.getLoads()+1);
                    g1.setLoads_rival(g2.getLoads());
                }if(g2_action==GunslingerAction.SHOOT){
                    g2.setLoads(g2.getLoads()-1);
                    g1.setLoads_rival(g2.getLoads());
                }
                //les comunicamos la ultima accion del rival
                g1.rivalAction(g2_action);
                g2.rivalAction(g1_action);

            }else if(g1_action==GunslingerAction.RELOAD){
                if(g2_action==GunslingerAction.RELOAD){
                    g2.setLoads(g2.getLoads()+1);
                    g1.setLoads_rival(g2.getLoads());
                }else if(g2_action==GunslingerAction.SHOOT){
                    //g2 gana
                    winner=g2;
                    break;
                }
                g1.setLoads(g1.getLoads()+1);
                g2.setLoads_rival(g1.getLoads());
                g1.rivalAction(g2_action);
                g2.rivalAction(g1_action);

            }else if(g1_action==GunslingerAction.SHOOT){
                if(g2_action==GunslingerAction.RELOAD){
                    //g1 gana
                    winner=g1;
                    break;
                }if(g2_action==GunslingerAction.SHOOT){
                    g2.setLoads(g2.getLoads()-1);
                    g1.setLoads_rival(g2.getLoads());
                }
                g1.setLoads(g1.getLoads()-1);
                g2.setLoads_rival(g1.getLoads());
                g1.rivalAction(g2_action);
                g2.rivalAction(g1_action);

            }
            System.out.println("The duel continues...");
            i++;
        }

        System.out.println("\nThe duel has ended");
        if(i>=50){
            System.out.println("\nThere has been a draw");
        }else{
            if(winner==g1){
                System.out.println("\nWinner: GUNSLINGER 1");
            }else{
                System.out.println("\nWinner: GUNSLINGER 2");
            }
        }

    }


}
