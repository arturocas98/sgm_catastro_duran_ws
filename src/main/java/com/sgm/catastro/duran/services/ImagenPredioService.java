package com.sgm.catastro.duran.services;

import com.sgm.catastro.duran.config.ApplicationProperties;
import com.sgm.catastro.duran.entities.ImagenPredio;
import com.sgm.catastro.duran.repository.ImagenPredioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

public class ImagenPredioService {

    @Autowired
    private ImagenPredioRepository imagenPredioRepository;

    @Autowired
    private ApplicationProperties properties;


    public ImagenPredio findOne(ImagenPredio data) {

        ImagenPredio respta =  imagenPredioRepository.findOne(Example.of(data)).orElse(null);
        return respta;
    }

    public ImagenPredio save(ImagenPredio data) {
        ImagenPredio imagenPredioDb;
        if (data.getId() != null) {
            imagenPredioDb = findOne(new ImagenPredio(data.getId()));
        } else {
            imagenPredioDb = new ImagenPredio();
        }
        return saveUpdate(imagenPredioDb);
    }

    public ImagenPredio saveUpdate(ImagenPredio data) {
        return imagenPredioRepository.save(data);
    }


    public Map<String, List> findAll(ImagenPredio data, Pageable pageable) {

        System.out.println("data excel cliente vendedor:"+data.toString());

        Map<String, List> map = new HashMap<>();
        Page<ImagenPredio> result;
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        result = imagenPredioRepository.findAll(Example.of(data), pageable);
        List<String> pages = new ArrayList<>();
        pages.add(String.valueOf(result.getTotalPages()));
        pages.add(String.valueOf(imagenPredioRepository.count(Example.of(data))));
        map.put("result", result.getContent());
        map.put("pages", pages);
        return map;
    }

    public Map<String, List> find(ImagenPredio data, Pageable pageable) {
        Map<String, List> map = new HashMap<>();
        Page<ImagenPredio> result;
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny().withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();
        result = imagenPredioRepository.findAll(Example.of(data), pageable);
        List<String> pages = new ArrayList<>();
        pages.add(String.valueOf(result.getTotalPages()));
        pages.add(String.valueOf(imagenPredioRepository.count(Example.of(data))));
        map.put("result", result.getContent());
        map.put("pages", pages);
        return map;
    }

    public void saveImagen(String imagen64, String nameImage) {
        try {
            byte[] archive = Base64.getDecoder().decode(imagen64);
            OutputStream out = new FileOutputStream(properties.getRutaImages() + nameImage);
            out.write(archive);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
