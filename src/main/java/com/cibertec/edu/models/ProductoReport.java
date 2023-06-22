package com.cibertec.edu.models;

import java.util.ArrayList;
import java.util.List;

public class ProductoReport {
	private List<Productos> productosList;
	
	
	public ProductoReport() {
		super();
		this.productosList = new ArrayList<>();
		
	}


	public List<Productos> getProductosList() {
		return productosList;
	}


	public void setProductosList(List<Productos> productosList) {
		this.productosList = productosList;
	}
	
	
	

}
