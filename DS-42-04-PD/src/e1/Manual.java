package e1;

public class Manual implements Mode {


    private static final Manual instancia = new Manual();
    private Manual(){}
    public static Manual getInstancia() {return instancia;}

    @Override
    public String  stringTemperatura(Termostato t){

        t.setState(State.ON);
        return t.getCurrentTemperature() + " Modo Manual - calefaccion encendida.";

    }


    @Override
    public String screenInfo(Termostato t){
        return t.getCurrentTemperature() + " ON M\n";
    }



    public void apagar(Termostato t){
        t.setMode(Off.getInstancia());
        t.setState(State.OFF);
        t.historialAdd("Se activa el modo Off");
    }

    public void programar(Termostato t, double limit){
        t.setMode(Program.getInstancia());
        t.setLimit(limit);
        t.historialAdd("Se activa el modo Program a " + t.getLimit() + " grados");
    }
    public void encendida(Termostato t){
        //no podemos cambiar al modo en el que ya estamos

    }
    public void termporizador(Termostato t, int time){
        t.setMode(Timer.getInstancia());
        t.setState(State.ON);
        t.setTime(time);
        t.historialAdd("Se activa el modo Timer " + t.getTime() + " minutos");

    }

}
