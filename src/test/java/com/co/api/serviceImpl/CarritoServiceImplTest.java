package com.co.api.serviceImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.co.api.daoImpl.CarritoDaoImpl;
import com.co.api.entity.CarritoEntity;
import com.co.api.entity.CatalogoEntity;

class CarritoServiceImplTest {

	@InjectMocks
	private CarritoServiceImpl carritoServiceImpl;
	
	@Mock
	private CarritoDaoImpl carritoDaoImpl;

	@Mock
	private CatalogoServiceImpl catalogoServiceImpl;
	
	private static final Long AMOUNT = 5L;
	private static final Long STOCK = 8L;
	private static final Long ID = 2L;
	private static final String ADD_PRODUCTS_SHOPPING_CARR_RESPONSE = "Producto agregado al carrito de compras con exito";
	private static final boolean CHECK_STOCK_RESPONSE = true;
	private static final String DELETE_SHOPPING_CARR_RESPONSE = "Se han eliminado con exito los productos del carrito de compras.";
	private static final String COMPLETE_DELETE_BUY_RESPONSE =  "La compra se ha realizado exitosamente.";
	
	private String addProductsTrama;
	private String completeBuyTrama;
	private List<CarritoEntity> shoppingCarrList;
	private CatalogoEntity catalogo;
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		addProductsTrama = getAddProductsTrama();
		completeBuyTrama = getCompleteBuyTrama();
		shoppingCarrList = new ArrayList<CarritoEntity>();

		catalogo = getCatalog();
		shoppingCarrList.add(getCarritoEntity());
		
		Mockito.when(catalogoServiceImpl.getStok(ID)).thenReturn(STOCK);
		Mockito.when(carritoDaoImpl.findAll()).thenReturn(shoppingCarrList);
	}
	
	@Test
	public void addProductsToShoppingCarrTest() {
		String response = carritoServiceImpl.addProductsToShoppingCarr(addProductsTrama);
		
		assertEquals(response, ADD_PRODUCTS_SHOPPING_CARR_RESPONSE);
	}
	
	@Test
	public void checkStockTest() {
		boolean response = carritoServiceImpl.checkStock(AMOUNT, STOCK);
		
		assertEquals(response, CHECK_STOCK_RESPONSE);		
	}
	
	@Test
	public void getAllShoppingCarrTest() {
		List<CarritoEntity> response = carritoServiceImpl.getAllShoppingCarr();
		
		assertEquals(response.get(0).getId(), shoppingCarrList.get(0).getId());
	}
	
	@Test
	public void deleteShoppingCarrTest() {
		String response = carritoServiceImpl.deleteShoppingCarr();
		
		assertEquals(response, DELETE_SHOPPING_CARR_RESPONSE);
	}
	
	@Test
	public void completeBuyTest() {
		String response = carritoServiceImpl.completeBuy(completeBuyTrama);
		
		assertEquals(response, COMPLETE_DELETE_BUY_RESPONSE);
	}
	
	public String getAddProductsTrama() {
		return "{\r\n"
				+ "    \"product_code\" : 2,\r\n"
				+ "    \"product_amount\": 5\r\n"
				+ "}";
	}

	public String getCompleteBuyTrama() {
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
	
	public CatalogoEntity getCatalog(){
		return new CatalogoEntity("Motorola one fusion", "Motorola", 200L, 10L, "Nuevo", 10L);
	}
	
	public CarritoEntity getCarritoEntity() {
		return new CarritoEntity(1L, catalogo, 10L);
	}

}
