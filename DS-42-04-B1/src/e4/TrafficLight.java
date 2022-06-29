// Boletin 1 Diseño Software
// Ejercicio 4: Semaforos y cruces
// Mario Lopez Cea (mario.lopez)
// Marta Martin de Argenta Hernandez (marta.martindeargenta)
// Grupo 4.2

package e4;

public class TrafficLight {

    //Atributos
    Colours position; // posicion del semaforo (rojo, ambar o verde)
    State state; // estado del semaforo (on, off)
    int counter; //contador del semaforo
    String name; //nombre del semaforo

    //Setters
    public void setPosition(Colours position) {
        this.position = position;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Metodo constructor
    public TrafficLight (Colours position, State state, int counter, String name) {
        this.position=position;
        this.state=state;
        this.counter=counter;
        this.name=name;
    }

}
