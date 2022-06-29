package e2;

abstract class GrupoTrabajo {
    private final String nombre;

    public GrupoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    abstract int horasInvertidas();
    abstract double costeTotal();
    abstract String print(int i);
    abstract void finalizarJornada(GrupoTrabajo g, int hours);
    abstract boolean exists(GrupoTrabajo g);
    abstract String print_list();

    public void anadir(GrupoTrabajo lugar, GrupoTrabajo objeto){
        throw new UnsupportedOperationException();
    }

    public void quitar(GrupoTrabajo lugar, GrupoTrabajo objeto){
        throw new UnsupportedOperationException();
    }
}
