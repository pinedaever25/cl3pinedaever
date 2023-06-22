package com.cibertec.edu.services;

import java.io.InputStream;
import java.util.List;

import com.cibertec.edu.models.Productos;

public interface ProductoService {
	
	public List<Productos> getAllProductos();
	
	
	public InputStream getReportProductos() throws Exception;
	
	

}
