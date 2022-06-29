package e2;

public class Proyecto {
    private final String name;
    private GrupoTrabajo grupo;

    public Proyecto(String name, GrupoTrabajo grupo) {
        this.name = name;
        this.grupo = grupo;
    }

    public String getName() {
        return name;
    }

    public GrupoTrabajo getGrupo() {
        return grupo;
    }

    public String print(){
        return grupo.print(0);
    }

    public boolean exists(GrupoTrabajo g) {
        return grupo.exists(g);
    }

    public String print_list(GrupoTrabajo g){
        StringBuilder text = new StringBuilder();
        if(exists(g)){
            text.append(grupo.print_list());
        }else{
            throw new UnsupportedOperationException("El trabajador no esta asociado a este proyecto");
        }

        return text.toString();
    }

    public void finalizarJornada(GrupoTrabajo g, int hours){
        grupo.finalizarJornada(g, hours);
    }

    //Añade en el EquipoTrabajo lugar el Trabajador/EquipoTrabajo objeto
    public void anadir(GrupoTrabajo lugar, GrupoTrabajo objeto){
        grupo.anadir(lugar, objeto);
    }

    //Quita en el EquipoTrabajo lugar el Trabajador/EquipoTrabajo objeto
    public void quitar(GrupoTrabajo lugar, GrupoTrabajo objeto){
        grupo.quitar(lugar, objeto);
    }



}


