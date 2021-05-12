package com.co.api.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.co.api.dao.CatalogoDao;
import com.co.api.daoImpl.CatalogoDaoImpl;
import com.co.api.entity.CatalogoEntity;

class CatalogoServiceImplTest {

	@InjectMocks
	private CatalogoServiceImpl catalogoServiceImpl;
	
	@Mock
	private CatalogoDao catalogoDao;

	@Mock
	private CatalogoDaoImpl catalogoDaoImpl;
	
	private static final Long ID = 1L;

	private List<CatalogoEntity> catalogList;
	private Long stock;
	private CatalogoEntity catalogo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		catalogo = new CatalogoEntity();
		catalogo = getCatalog();
		catalogList = new ArrayList<CatalogoEntity>();
		stock = 4L;
		catalogList.add(getCatalog());
		Mockito.when(catalogoDaoImpl.findByName("Motorola one fusion")).thenReturn(catalogList);
		Mockito.when(catalogoDaoImpl.findByRange(100L, 200L)).thenReturn(catalogList);
		Mockito.when(catalogoDaoImpl.findByBrand("Motorola")).thenReturn(catalogList);
		
		Mockito.when(catalogoDaoImpl.getStock(ID)).thenReturn(stock);
		
		Mockito.when(catalogoDaoImpl.getCatalogoEntityById(ID)).thenReturn(catalogo);
		
	}
	
	@Test
	public void getfindByParamsByName() {
		List<CatalogoEntity> response = catalogoServiceImpl.findByParams("Motorola one fusion", null, null, null, 1, 10);
		assertEquals(response.get(0).getNombre(), catalogList.get(0).getNombre());
	}

	@Test
	public void getfindByParamsByRange() {
		List<CatalogoEntity> response = catalogoServiceImpl.findByParams(null, 100L, 200L, null, 1, 10);
		assertEquals(response.get(0).getNombre(), catalogList.get(0).getNombre());
	}

	@Test
	public void getfindByParamsByBrand() {
		List<CatalogoEntity> response = catalogoServiceImpl.findByParams(null, null, null, "Motorola", 1, 10);
		assertEquals(response.get(0).getNombre(), catalogList.get(0).getNombre());
	}
	
	@Test
	public void getStokTest() {
		Long response = catalogoServiceImpl.getStok(ID);
		assertEquals(response, stock);
	}
	
	@Test
	public void getCatalogoTest() {
		CatalogoEntity response = catalogoServiceImpl.getCatalogo(ID);
		assertEquals(response.getNombre(), catalogo.getNombre());
	}
	
	public CatalogoEntity getCatalog(){
		return new CatalogoEntity("Motorola one fusion", "Motorola", 200L, 10L, "Nuevo", 10L);
	}

}
