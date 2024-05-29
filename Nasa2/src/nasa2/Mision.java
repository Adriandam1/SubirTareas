/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nasa2;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Adrian
 */

public class Mision implements Serializable {
    private String nombre;
    private Date fechaLanzamiento;
    private String objetivos;
    private String naveEspacial;
    private List<String> miembrosTripulacion;

    public Mision(String nombre, Date fechaLanzamiento, String objetivos, String naveEspacial, List<String> miembrosTripulacion) {
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.objetivos = objetivos;
        this.naveEspacial = naveEspacial;
        this.miembrosTripulacion = miembrosTripulacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public String getNaveEspacial() {
        return naveEspacial;
    }

    public List<String> getMiembrosTripulacion() {
        return miembrosTripulacion;
    }
}

