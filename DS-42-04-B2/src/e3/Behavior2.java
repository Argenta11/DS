package e3;

public class Behavior2 implements Behavior{

    public GunslingerAction action(Gunslinger g){
        int size=g.getRivalActions().size(); //tamaño de la lista
        if(size==0){ //si es la primera ronda
            return GunslingerAction.RELOAD;

        }else if(size==1){ //si es la segunda ronda
            return GunslingerAction.SHOOT;
        }else if(size==2){ //si es la tercera ronda
            return GunslingerAction.PROTECT;
        }

        GunslingerAction accion1 = g.getRivalActions().get(size-1); //ultima accion del rival
        GunslingerAction accion2 = g.getRivalActions().get(size-2); //penultima accion


        if(g.getLoads()==5){
            return GunslingerAction.MACHINE_GUN;
        } else if(g.getLoads()==0){
            if(g.getRivalLoads()>0){
                return GunslingerAction.PROTECT;
            }else {
                return GunslingerAction.RELOAD;
            }

        }else if(g.getLoads()==1 && g.getRivalLoads()==0){
            return GunslingerAction.SHOOT;
        }else if (g.getRivalLoads() == 4 && g.getLoads()!=0){
            return GunslingerAction.SHOOT;
        }else{
            if(accion1==GunslingerAction.PROTECT){
                if(accion2==GunslingerAction.RELOAD){
                    return GunslingerAction.PROTECT;
                }else if(accion2==GunslingerAction.SHOOT && g.getLoads()>0){
                    return GunslingerAction.SHOOT;
                }else{
                    return GunslingerAction.RELOAD;
                }

            }else if(accion1==GunslingerAction.RELOAD){
                if(accion2==GunslingerAction.SHOOT && g.getLoads()>0){
                    return GunslingerAction.SHOOT;
                }else{
                    return GunslingerAction.PROTECT;
                }

            }else{
                if(accion2==GunslingerAction.RELOAD){
                    return GunslingerAction.PROTECT;
                }else if(g.getLoads()>0 && accion2==GunslingerAction.SHOOT){
                    return GunslingerAction.SHOOT;
                }else{
                    return GunslingerAction.RELOAD;
                }
            }
        }


    }

}
