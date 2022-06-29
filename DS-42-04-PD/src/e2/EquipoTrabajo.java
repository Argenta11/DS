package e2;

import java.util.ArrayList;
import java.util.List;

public class EquipoTrabajo extends GrupoTrabajo {
    private final List<GrupoTrabajo> grupoTrabajo;



    public EquipoTrabajo(String nombre) {
        super(nombre);
        this.grupoTrabajo = new ArrayList<>();

    }


    public List<GrupoTrabajo> getGrupoTrabajo() {
        return grupoTrabajo;
    }

    public void insertar(GrupoTrabajo g){
        if(!exists(g)){
            grupoTrabajo.add(g);
        }
    }

    public void eliminar(GrupoTrabajo g){
        grupoTrabajo.remove(g);
    }

    public void anadir(GrupoTrabajo lugar, GrupoTrabajo objeto){
        if(this.equals(lugar)){
            insertar(objeto);
        }else{
            for (GrupoTrabajo group : grupoTrabajo) {
                if(group.equals(lugar)){
                    group.anadir(lugar, objeto);
                }
            }
        }
    }


    public void quitar(GrupoTrabajo lugar, GrupoTrabajo objeto){
        if(this.equals(lugar)){
            grupoTrabajo.remove(objeto);
        }else{
            for (GrupoTrabajo group : grupoTrabajo) {
                if(group.equals(lugar)){
                    group.quitar(lugar, objeto);
                }
            }
        }
    }


    @Override
    public int horasInvertidas() {
        int cont = 0;
        for (GrupoTrabajo trabajador : grupoTrabajo) {
            cont = cont + trabajador.horasInvertidas();
        }
        return cont;
    }

    @Override
    public double costeTotal() {
        double cont = 0;
        for (GrupoTrabajo trabajador : grupoTrabajo) {
            cont = cont + trabajador.costeTotal();
        }
        return cont;
    }

    @Override
    public String print(int i) {
        StringBuilder text = new StringBuilder();
        for(int j =0; j<i; j++){
            text.append("\t");
        }
        text.append("Team " + this.getNombre() +": "+this.horasInvertidas()+" hours, " + this.costeTotal()+"€\n");
        i++;
        for (GrupoTrabajo trabajador : grupoTrabajo) {
            text.append(trabajador.print(i));
        }
        return text.toString();
    }


    public void finalizarJornada(GrupoTrabajo g, int hours){
        for (GrupoTrabajo group : grupoTrabajo) {
            group.finalizarJornada(g, hours);
        }

    }


    public boolean exists(GrupoTrabajo g){
        boolean resultado=false;
        if(this.equals(g)){ //si EquipoTrabajo g es este devolvemos true
            return true;
        }else{
            for (GrupoTrabajo group : grupoTrabajo) {
                //vamos recorriendo la lista y ejecutando el exist de cada elemento
                resultado = group.exists(g);
                //si alguno da true es por q exite y por lo tanto devemos salir del bucle y devolver true
                if(resultado){
                    break;
                }
            }
            //si se ha encontrado resultado valdra true, si no se encuentra valdra el valor por defecto false
            return resultado;
        }

    }


    /*Llamamos de forma recursiva a print_list sobre los elementos de la lista, de forma que si es un
     * trabajador se ejecutarala el print_list del trabajador imprimiendo el nombre y si es un EsquipoTrabajo
     * recorrera recursivamente su lista*/
    public String print_list(){
        StringBuilder text = new StringBuilder();
        for (GrupoTrabajo group : grupoTrabajo) {
            text.append(group.print_list());

        }
        return text.toString();
    }


}
