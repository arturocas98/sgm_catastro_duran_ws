package com.sgm.catastro.duran.resources;


import com.sgm.catastro.duran.entities.ImagenPredio;
import com.sgm.catastro.duran.services.ImagenPredioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ws-sgm/api")
public class ImagenPredioResource {

    private ImagenPredioService services;

    @RequestMapping(value = "imagen/save", method = RequestMethod.POST)
    public ResponseEntity<ImagenPredio> savePersona(@RequestBody ImagenPredio data) {
        return new ResponseEntity<>(services.save(data), HttpStatus.OK);
    }

    @RequestMapping(value = "imagen/find", method = RequestMethod.GET)
    public ResponseEntity<ImagenPredio> find(@Valid ImagenPredio data) {
        return new ResponseEntity<>(services.findOne(data), HttpStatus.OK);
    }

    @RequestMapping(value = "imagenes/find", method = RequestMethod.GET)
    public ResponseEntity<List<ImagenPredio>> find(@Valid ImagenPredio data, Pageable pageable) {
        Map<String, List> map = services.findAll(data,pageable);
        List<String> pages = map.get("pages");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("totalPages", pages.get(0));
        responseHeaders.add("rootSize", pages.get(1));
        return new ResponseEntity<>(map.get("result"), responseHeaders, HttpStatus.OK);
    }

}


