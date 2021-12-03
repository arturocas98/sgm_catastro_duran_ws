package com.sgm.catastro.duran.entities;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "imagen_predio")
public class ImagenPredio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;

    public ImagenPredio() {
    }

    public ImagenPredio(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Canton{" +
                "id=" + id +
                ", imagen='" + imagen + '\'';
    }
}
