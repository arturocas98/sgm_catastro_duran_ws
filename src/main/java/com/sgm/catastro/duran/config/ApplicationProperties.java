package com.sgm.catastro.duran.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${SgmCatastroDuran.rutaImages}")
    private String rutaImages;

    public String getRutaImages() {
        return rutaImages;
    }

    public void setRutaImages(String rutaImages) {
        this.rutaImages = rutaImages;
    }
}
