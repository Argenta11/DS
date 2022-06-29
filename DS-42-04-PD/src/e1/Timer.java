package e1;

public class Timer implements Mode {

    private static final Timer instancia = new Timer();

    private Timer(){
    }

    public static Timer getInstancia() {return instancia;}

    @Override
    public String  stringTemperatura(Termostato t){

        int new_time;
        //la temperatura se toma cada 5 min, por lo que cuando se vuelve a tomar restamos 5 min al tiempo
        new_time = t.getTime() - 5;

        if(new_time<=0){//si se acabo el tiempo
            t.setTime(0);
            t.setMode(Off.getInstancia()); //Pasamos a estado Off
            t.setState(State.OFF);
            return "Se desactiva el modo Timer";
        }else{ //si no se acabo el tiempo

            t.setTime(new_time); //actualizamos el tiempo restante
            t.setState(State.ON); //mantenemos la calefaccion encendida
            return t.getCurrentTemperature() + " Modo Timer (faltan " + t.getTime() + " minutos) - calefaccion encendida.";
        }



    }


    @Override
    public String screenInfo(Termostato t){
        return  t.getCurrentTemperature()+ " " + t.getState() + " T " + t.getTime() + "\n";
    }




    public void apagar(Termostato t) {
        t.setMode(Off.getInstancia());
        t.setState(State.OFF);
        t.historialAdd("Se activa el modo Off");
    }

    public void programar(Termostato t, double limit) {
        //no podemos pasar de temporizador a timer

    }

    public void encendida(Termostato t) {
        t.setMode(Manual.getInstancia());
        t.setState(State.ON);
        t.historialAdd("Se activa el modo Manual");

    }

    public void termporizador(Termostato t, int time) {
        t.setMode(Timer.getInstancia());
        t.setState(State.ON);
        t.setTime(time);
        t.historialAdd("Se activa el modo Timer " + t.getTime() + " minutos");

    }


}
