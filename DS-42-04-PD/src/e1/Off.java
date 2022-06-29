package e1;

public class Off implements Mode {

    private static final Off instancia = new Off();
    private Off(){}
    public static Off getInstancia() {return instancia;}

    @Override
    public String  stringTemperatura(Termostato t){

        t.setState(State.OFF);
        return t.getCurrentTemperature() + " Modo Off - calefaccion apagada.";

    }


    @Override
    public String screenInfo(Termostato t){
       return t.getCurrentTemperature() + " OFF O\n";
    }


    public void apagar(Termostato t) {
        //no podemos cambiar al modo en el que ya estamos
    }

    public void programar(Termostato t, double limit) {
        t.setMode(Program.getInstancia());
        t.setLimit(limit);
        t.historialAdd("Se activa el modo Program a " + t.getLimit() + " grados");

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
