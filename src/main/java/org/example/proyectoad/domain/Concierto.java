package org.example.proyectoad.domain;

public class Concierto {
    private int id;
    private String presupuesto;
    private String nombre;
    private String descripcion;

    public Concierto() {

    }

    public Concierto(int id, String presupuesto, String nombre, String descripcion) {
        this.id = id;
        this.presupuesto = presupuesto;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
