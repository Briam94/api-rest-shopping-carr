package com.co.api.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class ShoppingCarrEntityTest {


	private static final Long ID = 1L;
    private static final Long CANTIDAD = 5L;
    
    private CatalogoEntity catalogoEntity;
    
    @Before
    public void setUp() {
    	catalogoEntity = getCatalogoEntity();
    }
    
    @Test
    public void getCarritoEntityWithConstructor() {
    	CarritoEntity shoppingCarr = new CarritoEntity(ID, catalogoEntity, CANTIDAD);
    	assertNotNull(shoppingCarr);

    	assertEquals(shoppingCarr.getId(), ID);
    	assertEquals(shoppingCarr.getCatalogoEntity(), catalogoEntity);
    	assertEquals(shoppingCarr.getCantidad(), CANTIDAD);
    }
    
    @Test
    public void getCarritoEntityNoConstructor() {
    	CarritoEntity shoppingCarr = new CarritoEntity();
    	shoppingCarr.setId(ID);
    	shoppingCarr.setCatalogoEntity(catalogoEntity);
    	shoppingCarr.setCantidad(CANTIDAD);
    	assertNotNull(shoppingCarr);

    	assertEquals(shoppingCarr.getId(), ID);
    	assertEquals(shoppingCarr.getCatalogoEntity(), catalogoEntity);
    	assertEquals(shoppingCarr.getCantidad(), CANTIDAD);
    }
    
    public CatalogoEntity getCatalogoEntity() {
    	return new CatalogoEntity("motorola one fusion", "motorola",1500L, 20L, "Nuevo", 20L);
    }

}
