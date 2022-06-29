// Boletin 1 Diseño Software
// Ejercicio 3: Relojes
// Mario Lopez Cea (mario.lopez)
// Marta Martin de Argenta Hernandez (marta.martindeargenta)
// Grupo 4.2

package e3;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clock {

    enum Period {AM, PM} //El tipo period es un enumerado que incluye los valores AM y PM

    int hours;
    int minutes;
    int seconds;
    Period period;


    public Clock(int hours, int minutes, int seconds) {
        super(); // Llamada al superconstructor de Java
        if(hours<=12){ // En el objeto guardamos las horas en formato 12h
            this.hours= hours;
            period = Period.AM;
        } else{
            this.hours = hours-12;
            period = Period.PM;
        }
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Clock(int hours, int minutes, int seconds, Period period) {
        super(); //Llamada al superconstructor de Java
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.period = period;
    }


    public int getHours24() {
        if(period==Period.AM){
            if(hours == 12){ //si son las 12 AM entonces, son las 0 en formato 24h
                return 0;
            }else{
                return hours;
            }

        }  //Tenemos en cuenta que en el objeto almacenamos en formato es 12h
        else{
            if(hours==12){ //si son las 12 PM entonces son las 12 en formato 24h
                return hours;
            }else
            {
                return hours+12;
            }

        }
    }

    public int getHours12() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }


    public int getSeconds() {
        return seconds;
    }

    public Period getPeriod() {
        return period;
    }

    public String printHour24() {

        String time;

        String horas, minutos, segundos;
        horas = String.format("%02d", getHours24());
        minutos = String.format("%02d", getMinutes());
        segundos = String.format("%02d", getSeconds());

        time = String.format("%s:%s:%s", horas, minutos, segundos);
        return time;
    }

    public String printHour12() {

            String time;

            String horas, minutos, segundos;
            horas = String.format("%02d", getHours12());
            minutos = String.format("%02d", getMinutes());
            segundos = String.format("%02d", getSeconds());


            time = String.format("%s:%s:%s %s", horas, minutos, segundos, getPeriod());
            return time;
    }

    public Clock(String s) {

        int espacios = 0; //Contador del numero de espacios que hay en el string
        for (int i = 0; i < s.length(); i++) { //Recorre el string convirtiendolo en array de caracteres
            if (s.charAt(i) == ' ') {
                espacios++; //El contador aumenta cada vez que se encuentre con un espacio
            }
        }
        if (espacios > 1) { //El unico caso con espacio es formato 12h que tiene solo uno, si tiene mas da error
            throw new IllegalArgumentException("The string is not a valid hour");
        } else {
            //Inicializacion de variables como nulas
            String hora = null;
            String minuto = null;
            String segundo = null;
            Period p_cons = null;

            if (espacios == 0) { //Si es formato 24h
                // Patrón para validar el formato 24h
                Pattern pattern = Pattern.compile("[0-9]"+"[0-9]" +":"+"[0-9]"+"[0-9]"+ ":"+"[0-9]"+"[0-9]");
                Matcher m = pattern.matcher(s);
                boolean b = m.matches();
                if(!b){
                    throw new IllegalArgumentException("The string is not a valid hour"); //Si no cumple el patron lanzamos excepcion
                }else{
                    String[] parts = s.split(":"); //Divide el string en partes cada vez que haya :
                    hora = parts[0];
                    minuto = parts[1];
                    segundo = parts[2];
                }
            } else if (espacios == 1) { //Si es formato 12h
                // Patrón para validar el formato 24h
                Pattern pattern = Pattern.compile("[0-9]"+"[0-9]" +":"+"[0-9]"+"[0-9]"+ ":"+"[0-9]"+"[0-9]"+" "+"([AP][M])");
                Matcher m = pattern.matcher(s);
                boolean b = m.matches();
                if(!b){
                    throw new IllegalArgumentException("The string is not a valid hour"); //Si no cumple el patron lanzamos excepcion
                }else {

                    String[] dividir = s.split(" "); //Separa el Period del resto
                    String tiempo = dividir[0];
                    String mediodia = dividir[1];
                    String[] parts = tiempo.split(":"); //Dividimos horas, minutos y segundos cuando haya :
                    hora = parts[0];
                    minuto = parts[1];
                    segundo = parts[2];
                    String AMPM = mediodia;

                    //Convertir string a period
                    if (AMPM.equals("AM")) {
                        p_cons = Period.AM;
                    } else if (AMPM.equals("PM")) {
                        p_cons = Period.PM;
                    } else { //Si no es AM ni PM da error
                        throw new IllegalArgumentException("The string is not a valid hour");
                    }
                }
            }
            if (hora == null || minuto == null || segundo == null) { //Si algun valor permanece nulo da error
                throw new IllegalArgumentException("The string is not a valid hour");
            } else {
                //Convertirmos los strings a tipo int para almacenarlos
                int hora2 = Integer.parseInt(hora);
                int minuto2 = Integer.parseInt(minuto);
                int segundo2 = Integer.parseInt(segundo);
                if (hora2 > 23 || minuto2 > 59 || segundo2 > 59) { //Comprobamos que son valores validos
                    throw new IllegalArgumentException("The string is not a valid hour");
                } else {
                    if (p_cons == null) { //Si es formato 24h
                        if (hora2<=12) { //Convertimos a formato 12h
                            this.hours = hora2;
                            this.period = Period.AM;
                        } else{
                            this.hours = hora2-12;
                            this.period = Period.PM;
                        }
                        this.minutes = minuto2;
                        this.seconds = segundo2;

                    } else { //Si es formato 12h
                        this.hours = hora2;
                        this.minutes = minuto2;
                        this.seconds = segundo2;
                        this.period = p_cons;

                    }
                }
            }
        }
    }


    //Como almacenamos en formato 12h, hacemos el hashCode y el equals con formato 12h
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //Si son el mismo objeto devuelve true
        if (o == null || getClass() != o.getClass()) return false; //Si no es el mismo tipo de clase devuelve false
        Clock clock = (Clock) o; //Creamos un objeto clock con los valores de o
        return  this.getHours12() == clock.getHours12() &&
                this.getMinutes() == clock.getMinutes() &&
                this.getSeconds() == clock.getSeconds() &&
                this.getPeriod() == clock.getPeriod();
    }

    @Override
    public int hashCode(){
        int result;
        result=getHours12();
        result= 31* result + getMinutes();
        result= 31*result + getSeconds();
        result = 31*result + period.hashCode();
        return result;
    }
}
