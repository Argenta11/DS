package e3;

public class Behavior1 implements Behavior{

    public GunslingerAction action(Gunslinger g){
        int size=g.getRivalActions().size(); //tamaño de la lista


        if(size==0){//si es la primera ronda
            return GunslingerAction.RELOAD;
        }else if(size==1){ //para la segunda ronda nos protegemos
            return GunslingerAction.PROTECT;
        }else if(size==2 && g.getRivalActions().get(1)==GunslingerAction.SHOOT){
            //si en la segunda ronda disparo, en la tercera disparamos nosotros
            return GunslingerAction.SHOOT;
        }

        GunslingerAction accion1 = g.getRivalActions().get(size-1); //ultima accion del rival
        GunslingerAction accion2 = g.getRivalActions().get(size-2); //penultima accion

        if(g.getLoads()==4){ //si tengo 4 cargas
            return GunslingerAction.RELOAD;
        }else if(g.getLoads()==5){ //si tengo 5 cargas
            return GunslingerAction.MACHINE_GUN;
        }else if(g.getRivalLoads()==4 || g.getRivalLoads()==0){
            //si esta a punto de matarnos o el no nos puede matar lo intentamos matar
            if(g.getLoads()>0){
                return GunslingerAction.SHOOT;
            }else{
                return GunslingerAction.RELOAD;
            }

        }else{
            if(accion1 == GunslingerAction.PROTECT){
                if(accion2 == GunslingerAction.SHOOT && g.getLoads()>0){ //si antes de protegerse disparo y tengo balas
                    return GunslingerAction.SHOOT;
                }else{
                    return GunslingerAction.RELOAD;
                }

            }else if(accion1 == GunslingerAction.RELOAD){
                if(g.getLoads()>0 && (accion2 == GunslingerAction.RELOAD || accion2 == GunslingerAction.PROTECT)) {
                    return GunslingerAction.SHOOT;
                }else{
                    return GunslingerAction.RELOAD;
                }

            }else{
                if(g.getRivalLoads()>=2){
                    return GunslingerAction.PROTECT;
                }else{
                    if(accion2 == GunslingerAction.SHOOT){
                        return GunslingerAction.RELOAD;
                    }else{
                        return GunslingerAction.PROTECT;
                    }
                }
            }
        }
    }
}
