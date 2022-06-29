package e1;

public class Program implements Mode {

    private static final Program instancia = new Program();

    private Program() {

    }

    public static Program getInstancia() {return instancia;}

    public String  stringTemperatura(Termostato t){

        if(t.getCurrentTemperature()<t.getLimit()){ //si la temperatura esta pord ebajo del limite
            t.setState(State.ON); //encendemos la calefaccion
            return t.getCurrentTemperature() + " Modo Program (a " + t.getLimit() + " grados) - calefaccion encendida.";
        }else{
            //si no la apagamos
            t.setState(State.OFF);
            return t.getCurrentTemperature() + " Modo Program (a " + t.getLimit() + " grados) - calefaccion apagada.";
        }

    }


    @Override
    public String screenInfo(Termostato t){
        return t.getCurrentTemperature() + " " + t.getState() + " P " + t.getLimit() + "\n";
    }



    public void apagar(Termostato t) {
        t.setMode(Off.getInstancia());
        t.setState(State.OFF);
        t.historialAdd("Se activa el modo Off");
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
        //no podemos pasar de program a timer

    }


}
