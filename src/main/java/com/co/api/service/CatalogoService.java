package com.co.api.service;

import java.util.List;

import com.co.api.entity.CatalogoEntity;

public interface CatalogoService {

	public List<CatalogoEntity> findByParams(String name, Long rangeMin, Long rangeMax, String brand,
			int pageIndex, int pageSize);
	
	public Long getStok(Long id);
	
	public CatalogoEntity getCatalogo(Long id);
	
	public void updateCatalogo(Long id, Long amount);

}
