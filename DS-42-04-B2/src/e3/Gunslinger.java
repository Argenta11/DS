package e3;


import java.util.ArrayList;
import java.util.List;

public class Gunslinger {

    private List<GunslingerAction> acciones; //lista de acciones del contrincante
    private Behavior behavior;
    private int loads;
    private int loads_rival; //lo va actualizando el metodo duelo



    public Gunslinger(Behavior behavior) {
        this.acciones = new ArrayList<>();
        this.behavior = behavior;
        this.loads = 0;
        this.loads_rival = 0;
    }

    public void setAcciones(List<GunslingerAction> acciones) {
        this.acciones = acciones;
    }

    public void setLoads(int loads) {
        this.loads = loads;
    }

    public void setLoads_rival(int loads_rival) {
        this.loads_rival = loads_rival;
    }



    public Behavior getBehavior() {
        return behavior;
    }



    public GunslingerAction action(){

        //llamado por gunfidht decide que accion realizar y se la comunica a gunfight
        //utiliza el metodo de behavior para obtener la accion a realizar
        GunslingerAction action;
        action=behavior.action(this);


        return action;
    }

    public int getLoads(){
        return loads;
        //devuelve el numero de cargas
    }

    public void rivalAction(GunslingerAction action){
        //usado por Gunfight para que guarde la ultima accion del contrincante
        acciones.add(action);
    }


    public List<GunslingerAction> getRivalActions(){
        //devuelve la lista de acciones q tiene el pistolero sobre el rival
        return acciones;
    }


    public int getRivalLoads(){
        //devuelve las cargas del rival
        return loads_rival;
        /*NO SE SI SE PUEDE HACER DE OTRA MANERA QUE NO SEA DEPENDIENDO DE UN ATRIBUTO
         * QUE SEA ACCEDIENDO DIERECTAMENTE A OBJEDO DEL CONTRINCANTE*/
    }


    public void setBehavior(Behavior behavior){
        //establece el la estrategia que va a seguir
        this.behavior=behavior;

    }






}
