package com.co.api.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.LogManager;

import com.co.api.entity.CatalogoEntity;
import com.co.api.service.CatalogoService;
import com.co.api.utils.RoutersConstans;

@CrossOrigin
@RestController
@RequestMapping(value = RoutersConstans.MAPPING_CATALOGO_BAE)
public class CatalogoController {

	static Logger log = LogManager.getLogger(CatalogoController.class.getName());
	
	@Autowired
	private CatalogoService catalogoService;
	

	@GetMapping(value = RoutersConstans.MAPPING_GET_BY_PARAMS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CatalogoEntity>> findByPage(
			@RequestParam(value = "name",  required = false) String name,
			@RequestParam(value = "min_range",  required = false) Long rangeMin,
			@RequestParam(value = "max_range",  required = false) Long rangeMax,
			@RequestParam(value = "brand",  required = false) String brand,
			@RequestParam(value = "page", required = true) int pageIndex,
			@RequestParam(value = "size", required = true) int pageSize
			) {
    	log.info("Se entra al CatalogoController para consumir el metodo findByPage con los siguientes parametros:"
    			+ " name = " + name + ", rangeMin = " + rangeMin + ", rangeMax = " + rangeMax + ", brand = " + brand + ", pageIndex = " + pageIndex);
		List<CatalogoEntity> list = catalogoService.findByParams(name, rangeMin, rangeMax, brand, pageIndex, pageSize);
		return new ResponseEntity<List<CatalogoEntity>>(list, new HttpHeaders(), HttpStatus.OK);
				
	}

}
