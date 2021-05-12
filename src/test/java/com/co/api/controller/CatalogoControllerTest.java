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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.co.api.entity.CatalogoEntity;
import com.co.api.service.CatalogoService;

class CatalogoControllerTest {

	@InjectMocks
	private CatalogoController catalogoController;
	
	@Mock
	private CatalogoService catalogoService;
	
	private List<CatalogoEntity> catalogList;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		catalogList = new ArrayList<CatalogoEntity>();
		catalogList.add(getCatalogList());
		Mockito.when(catalogoService.findByParams("Motorola one fusion", 100L, 200L, "Motorola", 1, 10))
			.thenReturn(catalogList);
		
	}
	
	@Test
	public void getFindByPage() {
		ResponseEntity<List<CatalogoEntity>> response = 
				catalogoController.findByPage("Motorola one fusion", 100L, 200L, "Motorola", 1, 10);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	public CatalogoEntity getCatalogList(){
		return new CatalogoEntity("Motorola one fusion", "Motorola", 200L, 10L, "Nuevo", 10L);
	}

}
