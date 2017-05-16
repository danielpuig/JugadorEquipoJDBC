/*
 * Entity Equipo
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author danielpuig
 */
public class Equipo {
    
    private String nombre;
    private String localidad;
    private LocalDate fechaCreacion;

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(String nombre, String localidad, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
}
