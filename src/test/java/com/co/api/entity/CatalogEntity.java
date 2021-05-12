package com.co.api.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


class CatalogEntity {

private Long id;
	
	private static final String NOMBRE = "Motorola one fusion";
	private static final String MARCA = "Motorola";
	private static final Long PRECIO = 2000L;
	private static final Long STOCK = 50L;
	private static final String ESTADO = "Nuevo";
	private static final Long DESCUENTO = 10L;
	
	@Test
	public void getCatalogEntityWithConstructor() {
		CatalogoEntity catalog = new CatalogoEntity(NOMBRE, MARCA, PRECIO, STOCK, ESTADO, DESCUENTO);
		assertNotNull(catalog);

    	assertEquals(catalog.getNombre(), NOMBRE);
    	assertEquals(catalog.getMarca(), MARCA);
    	assertEquals(catalog.getPrecio(), PRECIO);
    	assertEquals(catalog.getStock(), STOCK);
    	assertEquals(catalog.getEstado(), ESTADO);
    	assertEquals(catalog.getDescuento(), DESCUENTO);
	}

}
