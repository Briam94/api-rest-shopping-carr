package com.co.api.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.co.api.entity.CarritoEntity;
import com.co.api.entity.CatalogoEntity;
import com.co.api.service.CarritoService;

class CarritoControllerTest {

	@InjectMocks
	private CarritoController carritoController;

	@Mock
	private CarritoService carritoService;
	
	private static final String POST_RESPONSE = "Producto agregado al carrito de compras con exito";
	private static final String DELETE_RESPONSE = "Se han eliminado con exito los productos del carrito de compras.";
	private static final String PUT_RESPONSE = "La compra se ha realizado exitosamente.";
	
	private String postTrama;
	private String putTrama;
	
	private List<CarritoEntity> shoppingCarrList;
	private CatalogoEntity catalogo;
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		postTrama = getPostTrama();
		putTrama = getPutTrama();
		
		shoppingCarrList = new ArrayList<CarritoEntity>();
		shoppingCarrList.add(getCarritoEntity());
		catalogo = getCatalog();
		
		Mockito.when(carritoService.addProductsToShoppingCarr(postTrama)).thenReturn(POST_RESPONSE);
		Mockito.when(carritoService.getAllShoppingCarr()).thenReturn(shoppingCarrList);
		Mockito.when(carritoService.deleteShoppingCarr()).thenReturn(DELETE_RESPONSE);
		Mockito.when(carritoService.completeBuy(postTrama)).thenReturn(PUT_RESPONSE);
	}
	
	@Test
	public void addProductsToShoppingCarrTest() {
		String response = carritoController.addProductsToShoppingCarr(postTrama);
		
		assertEquals(response, POST_RESPONSE);
	}
	
	@Test
	public void findAllShoppingCarrTest() {
		List<CarritoEntity> response = carritoController.findAllShoppingCarr();
		
		assertEquals(response.get(0).getId(), shoppingCarrList.get(0).getId());
	}
	
	@Test
	public void deleteShoppingCarrTest() {
		String response = carritoController.deleteShoppingCarr();

		assertEquals(response, DELETE_RESPONSE);
	}
	
	@Test
	public void completeBuyTest() {
		String response = carritoController.completeBuy(putTrama);
		
		assertEquals(response, PUT_RESPONSE);
	}
	
	public CarritoEntity getCarritoEntity() {
		return new CarritoEntity(1L, catalogo, 10L);
	}
	
	
	public CatalogoEntity getCatalog(){
		return new CatalogoEntity("Motorola one fusion", "Motorola", 200L, 10L, "Nuevo", 10L);
	}
	
	public String getPutTrama() {
		return "{\r\n"
				+ "    \"shopping_products\": [\r\n"
				+ "        {\r\n"
				+ "            \"product_code\": 1,\r\n"
				+ "            \"amount\": 3\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"product_code\": 5,\r\n"
				+ "            \"amount\": 3\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
	}
	
	public String getPostTrama() {
		return "{\r\n"
				+ "    \"product_code\" : 2,\r\n"
				+ "    \"product_amount\": 5\r\n"
				+ "}";
	}

}
