package e1;

import java.util.ArrayList;
import java.util.List;

public class Termostato {

    private double currentTemperature;
    private int time;
    private double limit;
    private Mode mode;
    private State state;
    private final List<String> historial; //almacenamos los string




    public Termostato() {
        this.currentTemperature = 0;
        this.historial = new ArrayList<>();
        this.mode = Off.getInstancia();
        this.state= State.OFF;


    }


    public int getTime() {
        return time;
    }

    public double getLimit() {
        return limit;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public Mode getMode() {
        return mode;
    }

    public List<String> getHistorial() {
        return historial;
    }

    public State getState() {
        return state;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void newTemperature(double currentTemperature) {

        String add;
        this.setCurrentTemperature(currentTemperature); //actualizamos la temperatura
        add=mode.stringTemperatura(this);
        historial.add(add); //almacenamos el string correspondiente

    }


    public void historialAdd(String cadena){
        historial.add(cadena);
    }


    void apagar(){
        this.getMode().apagar(this);

    }

    void encendida(){
        this.getMode().encendida(this);

    }

    void programar(double limit){
        this.getMode().programar(this, limit);

    }

    void temporizador(int time){
        this.getMode().termporizador(this, time);

    }

    String screenInfo() {
        return mode.screenInfo(this);
    }


    String printHistorial() {
        int size = this.getHistorial().size();
        List<String> hist = this.getHistorial();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < size; i++) {
            text.append(hist.get(i) + "\n");
        }
        return text.toString();
    }



}
