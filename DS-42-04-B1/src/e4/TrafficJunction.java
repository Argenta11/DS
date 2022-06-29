// Boletin 1 Diseño Software
// Ejercicio 4: Semaforos y cruces
// Mario Lopez Cea (mario.lopez)
// Marta Martin de Argenta Hernandez (marta.martindeargenta)
// Grupo 4.2

package e4;

public class TrafficJunction {

    //Atributos
    boolean active;

    // creamos un array donde almacenaremos los 4 semaforos para recorrer de forma mas sencilla
    TrafficLight[] listTraffic = new TrafficLight[4];


    //Metodo constructor
    public TrafficJunction() {
        // Creamos los 4 semaforos y los inializamos
        TrafficLight north = new TrafficLight(Colours.GREEN, State.OFF, 0, "NORTH"); //north
        TrafficLight south = new TrafficLight(Colours.RED, State.OFF, 44, "SOUTH"); //south
        TrafficLight east = new TrafficLight(Colours.RED, State.OFF, 22, "EAST"); //east
        TrafficLight west = new TrafficLight(Colours.RED, State.OFF, 0, "WEST"); //west

        //asignamos a cada posicion del array un semaforo
        this.listTraffic[0] = north;
        this.listTraffic[1] = south;
        this.listTraffic[2] = east;
        this.listTraffic[3] = west;

        /*inicializamos los contadores en diferentes puntos para que cada color comprenda una franja de tiempo:
         * Green (0-15) ambos incluidos Amber(0, 5) ambos incluidos Red (0, 66).
         * Entre Green y Amber hacen un total de 22 ciclos (16 + 6), por lo que Red estara un total de 22*3 = 66 ciclos
         * ya que hay 3 semaforos en rojo a la vez. Cuando lleguena  66 se restauraran a 0 */

        // Inicializamos los semaforos en Red en diferentes ciclos separados entre si por 22 ciclos (lo que tarda Green+Amber)


        this.active = false;


    }


    public void timesGoesBy() {

        for (int i = 0; i < 4; i++) {

            listTraffic[i].counter++; //incrementamos el contador
            if (listTraffic[i].state == State.OFF) { //si esta en el estado OFF podremos cambiar el color

                if ((listTraffic[i].position == Colours.GREEN) && (listTraffic[i].counter == 16)) {
                    //Si al sumar un segundo pasa al ciclo 16 de Green entonces realmenete es el ciclo 0 de Amber
                    listTraffic[i].setPosition(Colours.AMBER);
                    listTraffic[i].setCounter(0);

                }
                if ((listTraffic[i].position == Colours.AMBER) && (listTraffic[i].counter == 6)) {
                    //si al sumar un segundo pasamos al ciclo 6 de amber, entonces realmente es el 0 de Red
                    listTraffic[i].setPosition(Colours.RED);
                    listTraffic[i].setCounter(0);

                }
                if ((listTraffic[i].position == Colours.RED) && (listTraffic[i].counter == 66)) {
                    //Si al sumar un segundo pasamos al ciclo 66 de Red, entonces realmente es el ciclo 0 de Green
                    listTraffic[i].setPosition(Colours.GREEN);
                    listTraffic[i].setCounter(0);
                }
            }
        }
    }


    public void amberJunction(boolean active) {
        if (!active) { //si active es falso reiniciamos los semaforos:
            listTraffic[0].setPosition(Colours.GREEN); //north
            listTraffic[0].setState(State.OFF);
            listTraffic[0].setCounter(0);

            listTraffic[1].setPosition(Colours.RED); //south
            listTraffic[1].setState(State.OFF);
            listTraffic[1].setCounter(44);

            listTraffic[2].setPosition(Colours.RED); //east
            listTraffic[2].setState(State.OFF);
            listTraffic[2].setCounter(22);

            listTraffic[3].setPosition(Colours.RED); //west
            listTraffic[3].setState(State.OFF);
            listTraffic[3].setCounter(0);


        } else { //ponemos todos los semaforos en ambar intermitente indefinidamente

            for (int i = 0; i < 4; i++) {
                listTraffic[i].setPosition(Colours.AMBER);
                listTraffic[i].setState(State.ON);
                listTraffic[i].setCounter(0);
            }

        }

    }


    @Override
    public String toString() {

        String[] resultado = new String[4]; //array donde almacenamos el string para cada semaforo
        for (int i = 0; i < 4; i++) { //para cada uno de los colores

            if (listTraffic[i].position == Colours.GREEN) {
                String patron = "[%s: %s %d]"; //si esta en verde imprimiremos el nombre, color y contador
                resultado[i]=String.format(patron, listTraffic[i].name, listTraffic[i].position, listTraffic[i].counter);
            }else if (listTraffic[i].position == Colours.RED) {
                String patron = "[%s: %s]"; //si esta en rojo imprimiremos el nombre y color
                resultado[i]=String.format(patron, listTraffic[i].name, listTraffic[i].position);
            }else{
                if (listTraffic[i].state == State.OFF){
                    String patron = "[%s: %s %s %d]"; //si esta en ambar estatico (OFF) imprimiremos el nombre, color, posicion y contador
                    resultado[i]=String.format(patron, listTraffic[i].name, listTraffic[i].position, listTraffic[i].state, listTraffic[i].counter);
                }else{
                    String patron = "[%s: %s %s]"; // si esta en ambar parpadeante (ON) imprimremos el nombre, color y estado
                    resultado[i]=String.format(patron, listTraffic[i].name, listTraffic[i].position, listTraffic[i].state);
                }
            }

        }

        String patron_final ="%s%s%s%s"; //el patron final sera juntar cada string de cada semaforo


        //devolvemos el string con los formatos de cada uno de los semaforos
        return String.format(patron_final, resultado[0], resultado[1], resultado[2], resultado[3]);


    }


}


