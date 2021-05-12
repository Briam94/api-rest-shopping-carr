package com.co.api.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.api.dao.CarritoDao;
import com.co.api.daoImpl.CarritoDaoImpl;
import com.co.api.entity.CarritoEntity;
import com.co.api.entity.CatalogoEntity;
import com.co.api.service.CarritoService;
import org.apache.log4j.LogManager;

@Service
public class CarritoServiceImpl implements CarritoService{

	 static Logger log = LogManager.getLogger(CarritoServiceImpl.class.getName());
	
	@Autowired
	private CarritoDao carritoDao;
	
	@Autowired
	private CarritoDaoImpl carritoDaoImpl;
	
	@Autowired 
	private CatalogoServiceImpl catalogoServiceImpl;
	
	
	@Override
	public String addProductsToShoppingCarr(String trama) {
    	log.info("Se entra al CarritoServiceImpl para consumir el metodo addProductsToShoppingCarr con la trama = " + trama);
    	JSONObject jsonObject = new JSONObject(trama);
    	Long id = jsonObject.getLong("product_code");
    	Long amount = jsonObject.getLong("product_amount");
    	Long stock = catalogoServiceImpl.getStok(id);
    	if(checkStock(amount, stock)) {
        	log.info("la cantidad en stock del producto con el id  " + id + " es mayor al solicitado");
    		CatalogoEntity catalogo = catalogoServiceImpl.getCatalogo(id);
    		CarritoEntity carrito = new CarritoEntity(null, catalogo, amount);
    		carritoDao.save(carrito);
        	log.info("Se almacena un nuevo registro en la tabla CARRITO");
    		return "Producto agregado al carrito de compras con exito";
    	}
    	log.info("La cantidad solicitada supera la cantidad en stock del producto con id " + id);
		return "La cantidad solicitada supera la cantidad en stock";
	}
	
	
	public boolean checkStock(Long amount, Long stock) {
    	log.info("Se compara la cantidad en stock " + stock + " con la cantidad solicitada " + amount);
		if(amount > stock) {
			log.info("La cantidad en stock supera la cantidad solicitada"); 
			return false;			
		}
		else {
			log.info("La cantidad solicitada supera la cantidad en stock");
			return true;
		}
	}


	@Override
	public List<CarritoEntity> getAllShoppingCarr() {
    	log.info("Se entra al CarritoServiceImpl para consumir el metodo getAllShoppingCarr");
		List<CarritoEntity> listShoppingCarr = new ArrayList<CarritoEntity>();
		try {
			listShoppingCarr = carritoDaoImpl.findAll();
			log.info("Se encuentran " + listShoppingCarr.size() + " registros en la tabla carrito");
		} catch (Exception e) {
			log.info("Ocurrio un error al consultar todos los registros del carrito de comprar,"
					+ " el error es: " + e.getMessage());
		}
		return listShoppingCarr;
	}


	@Override
	public String deleteShoppingCarr() {
		String respuesta = "";
    	log.info("Se entra al CarritoServiceImpl para consumir el metodo deleteShoppingCarr");
		try {
			carritoDao.deleteAll();
			log.info("Se han eliminado con exito los registros de la tabla carrito");
			respuesta =  "Se han eliminado con exito los productos del carrito de compras.";
		} catch (Exception e) {
			log.info("Ocurrio un error al eliminar todos los registros del carrito de compras,"
					+ " el error es: " + e.getMessage());
			respuesta =  "Ocurrio un error al eliminar todos los registros del carrito de compras.";
		}
		return respuesta;
	}


	@Override
	public String completeBuy(String trama) {
		String respuesta = "";
    	log.info("Se entra al CarritoServiceImpl para consumir el metodo completeBuy con la trama: " + trama);
    	JSONObject jsonObject = new JSONObject(trama);
    	JSONArray jsonArray = jsonObject.getJSONArray("shopping_products");
    	try {
			for (int i = 0; i < jsonArray.length(); i++) {
				Long id = jsonArray.getJSONObject(i).getLong("product_code");
				Long amount = jsonArray.getJSONObject(i).getLong("amount");
		    	log.info("El producto a actualizar en la tabla catalogo tiene el id: " + id + ", la cantidad "
		    			+ "a descontar del stock de este producto es de: " + amount);
				catalogoServiceImpl.updateCatalogo(id, amount);
			}
			respuesta =  "La compra se ha realizado exitosamente.";
		} catch (JSONException e) {
			log.info("Ocurrio un error al actualizar los productos de la compra"
					+ " el error es: " + e.getMessage());
			respuesta =  "Ha ocurrido un error al completar la compra.";
		}
    	log.info("la respuesta dada por CarritoServiceImpl con el metodo completeBuy es: " + respuesta);
		return respuesta;
	}


}
