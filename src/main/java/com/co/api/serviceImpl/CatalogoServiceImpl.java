package com.co.api.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.apache.log4j.LogManager;

import com.co.api.dao.CatalogoDao;
import com.co.api.daoImpl.CatalogoDaoImpl;
import com.co.api.entity.CatalogoEntity;
import com.co.api.service.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService{

	static Logger log = LogManager.getLogger(CatalogoServiceImpl.class.getName());
	 
	@Autowired
	private CatalogoDao catalogoDao;
	
	@Autowired 
	private CatalogoDaoImpl catalogoDaoImpl;

	@Override
	public List<CatalogoEntity> findByParams(String name, Long rangeMin, Long rangeMax, String brand, int pageIndex,
			int pageSize) {
    	log.info("Se entra  al CatalogoServiceImpl para consumir el metodo findByParams con los siguientes parametros:"
    			+ " name = " + name + ", rangeMin = " + rangeMin + ", rangeMax = " + rangeMax + ", brand = " + brand + ", pageIndex = " + pageIndex);
		List<CatalogoEntity> list = new ArrayList<CatalogoEntity>();
		try {
			if(!name.equals("")) {
				log.info("se entra a realizar la consulta por nombre, el parametro name es: " + name);
				list = catalogoDaoImpl.findByName(name);
			}else if(rangeMin != null && rangeMax != null) {
				log.info("se entra a realizar la consulta por rango de precios, el parametro rangeMin es: " + rangeMin + ", el parametro rangeMax es: " + rangeMax);
				list = catalogoDaoImpl.findByRange(rangeMin, rangeMax);
			}else if(brand.equals("")) {
				log.info("se entra a realizar la consulta por marca, el parametro brand es: " + brand);
				list = catalogoDaoImpl.findByBrand(brand);
			}else {
				log.info("No se envian parametros de consulta, por lo tanto se realiza la consulta por defecto con la pagina: " + pageIndex + " y tamaño: " + pageSize);
				PageRequest paging = PageRequest.of(pageIndex, pageSize);
				Page<CatalogoEntity> pageResult = catalogoDao.findAll(paging);
				if(pageResult.hasContent()) {
					return pageResult.getContent();
				}else {
					return new ArrayList<CatalogoEntity>();
				}
			}
		} catch (Exception e) {
	    	log.info("ha ocurrido un error al consumir el metodo findByParams de CatalogoServiceImpl con los siguientes parametros: "
	    			+ " name = " + name + ", rangeMin = " + rangeMin + ", rangeMax = " + rangeMax 
	    			+ ", brand = " + brand + ", pageIndex = " + pageIndex
	    			+ "/n, el error es: " + e.getMessage());
		}
    	log.info("Se encuentran " + list.size() + " registros en la tabla catalogo");
		return list;
	}

	@Override
	public Long getStok(Long id) {
		log.info("se entra a CatalogoServiceImpl para consumir el metodo getStok con el siguiente id: " + id);
		Long stock = 0L;
		try {
			stock = catalogoDaoImpl.getStock(id);
		} catch (Exception e) {
			log.info("ocurrio un error en el metodo getStok, el error es:  " + e.getMessage());
		}
		log.info("la cantidad en stock para el producto con el id: " + id + ", es: " + stock);
		return stock;
	}

	@Override
	public CatalogoEntity getCatalogo(Long id) {
		log.info("se entra a CatalogoServiceImpl para consumir el metodo getCatalogo con el siguiente id: " + id);
		CatalogoEntity catalogo = new CatalogoEntity();
		try {
			catalogo = catalogoDaoImpl.getCatalogoEntityById(id);
		} catch (Exception e) {
			log.info("ocurrio un error en el metodo getCatalogo, el error es:  " + e.getMessage());
		}
    	log.info("el producto encontrado con el id " + id + ", es: " + catalogo.getNombre());
		return catalogo;
	}

	@Override
	public void updateCatalogo(Long id, Long amount) {
    	log.info("Se entra al CatalogoServiceImpl para consumir el metodo updateCatalogo con el id = " + id + 
    			" y la cantidad a descontar es de: " + amount);
    	try {
			catalogoDaoImpl.updateCatalogo(id, amount);
		} catch (Exception e) {
			log.info("ocurrio un error en el metodo updateCatalogo, el error es:  " + e.getMessage());
		}
		
	}

}
