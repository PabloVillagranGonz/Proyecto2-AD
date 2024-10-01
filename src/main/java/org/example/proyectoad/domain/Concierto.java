package org.example.proyectoad.domain;

public class Concierto {
    private int id;
    private String presupuesto;
    private String nombre;
    private String descripcion;
    private String tipo;

    public Concierto() {

    }

    public Concierto(int id, String presupuesto, String nombre, String descripcion, String tipo) {
        this.id = id;
        this.presupuesto = presupuesto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }
    public Concierto(String presupuesto, String nombre, String descripcion, String tipo) {
        this.presupuesto = presupuesto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", presupuesto=" + presupuesto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
