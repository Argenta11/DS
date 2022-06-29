package e1;

public abstract class Personaje {
    private String nombre;
    private int vida;
    private int resistencia;

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
}
