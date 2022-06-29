package e3;

import java.util.Random;

public class Behavior3 implements Behavior{


    public GunslingerAction action(Gunslinger g){
        //El comportamiento es aleatorio

        GunslingerAction action;
        do{ //seguimos en el bucle si sale shoot y no tenemos balas o si sale Machine_gun y no tenemos 5 balas
            int pick = new Random().nextInt(GunslingerAction.values().length);
            action = GunslingerAction.values()[pick];
        } while((action==GunslingerAction.SHOOT && g.getLoads()<1) || action==GunslingerAction. MACHINE_GUN && g.getLoads()<5);

        return action;
    }
}
