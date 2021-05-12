package com.co.api.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.LogManager;

import com.co.api.entity.CarritoEntity;
import com.co.api.service.CarritoService;
import com.co.api.utils.RoutersConstans;

@CrossOrigin
@RestController
@RequestMapping(value = RoutersConstans.GET_MAPPING_BASE)
public class CarritoController {

	static Logger log = LogManager.getLogger(CarritoController.class.getName());
	
	@Autowired
	private CarritoService carritoService;
	
	@PostMapping(value = RoutersConstans.MAPPING_POST_ADD_PRODUCTS, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addProductsToShoppingCarr(
			@RequestBody String trama
			) {
    	log.info("Se entra al CarritoController para consumir el metodo addProductsToShoppingCarr con la trama = " + trama);
		return carritoService.addProductsToShoppingCarr(trama);
	}
	
	@GetMapping(value = RoutersConstans.MAPPING_GET_BY_PARAMS, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CarritoEntity> findAllShoppingCarr(){
    	log.info("Se entra al CarritoController para consumir el metodo findAllShoppingCarr");
		return carritoService.getAllShoppingCarr();
	}
	
	@DeleteMapping(value = RoutersConstans.DELETE_SHOPPING_CAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteShoppingCarr() {
    	log.info("Se entra al CarritoController para consumir el metodo deleteShoppingCarr");
		return carritoService.deleteShoppingCarr();
	}	
	
	@PutMapping(value = RoutersConstans.FIANLLY_BUY, produces = MediaType.APPLICATION_JSON_VALUE)
	public String completeBuy(
			@RequestBody String trama
			) {
    	log.info("Se entra al CarritoController para consumir el metodo completeBuy con la trama = " + trama);
		return carritoService.completeBuy(trama);
	}
	

}
