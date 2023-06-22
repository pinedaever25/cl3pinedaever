package com.cibertec.edu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cibertec.edu.models.Productos;

public interface ProductoDao extends CrudRepository<Productos,Long>{
	
	public List<Productos> findAll();

}
