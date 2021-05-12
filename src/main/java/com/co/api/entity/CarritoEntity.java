package com.co.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARRITO")
public class CarritoEntity {

	@Id
	private Long id;
	
    @JoinColumn(name = "id_catalogo", referencedColumnName = "id")
    @ManyToOne
    private CatalogoEntity catalogoEntity;
    
    @Column(name = "cantidad")
    private Long cantidad;
    
	public CarritoEntity() {
		super();
	}

	public CarritoEntity(Long id, CatalogoEntity catalogoEntity, Long cantidad) {
		this.id = id;
		this.catalogoEntity = catalogoEntity;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CatalogoEntity getCatalogoEntity() {
		return catalogoEntity;
	}

	public void setCatalogoEntity(CatalogoEntity catalogoEntity) {
		this.catalogoEntity = catalogoEntity;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
    
    

}
