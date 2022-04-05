package model;

public class Persona {
    private String nombre;
    private String poblacion;
    private Integer edad;

    public Persona(String nombre, String poblacion, Integer edad) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s. Poblacion: %s. Edad: %d", nombre, poblacion, edad);
    }
}