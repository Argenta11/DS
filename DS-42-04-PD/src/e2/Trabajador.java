package e2;

public class Trabajador extends GrupoTrabajo {

    private double costo;
    private int hora;


    public Trabajador(String nombre, double costo) {
        super(nombre);
        this.costo = costo;
        this.hora = 0;
    }


    public double getCosto() {
        return costo;
    }

    public int getHora() {
        return hora;
    }


    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int horasInvertidas(){
        return getHora();
    }
    public double costeTotal(){
        return ((double) getHora()) * getCosto();
    }
    public String print(int i){
        StringBuilder text = new StringBuilder();
        for(int j =0; j<i; j++){
            text.append("\t");
        }
        text.append("Worker " + getNombre() + ": " + horasInvertidas() + " hours, " + costeTotal() + "€\n" );
        return text.toString();
    }


    void finalizarJornada(GrupoTrabajo g, int hours){
        if(g.equals(this)){
            this.setHora(hours);
        }
    }


    public boolean exists(GrupoTrabajo g){
        if(g.equals(this)){ //si el trabajador g es este trabajador
            return true;
        }else{
            return false;
        }
    }

    public String print_list(){ //imprimirmos el nombre del trabajador
        return getNombre() + " ";
    }

}