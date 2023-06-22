package com.cibertec.edu.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.edu.models.Productos;
import com.cibertec.edu.services.ProductoServiceImpl;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(value = "/producto")
public class ProductoController {
	@Autowired
	private ProductoServiceImpl productoService;
	
	@GetMapping({"/registro","/","","/home"})
	public String registro(Model model) {
		model.addAttribute("titulo", "Reporte de Productos");
		return "registro";
	}
	
	@ModelAttribute("productos")
	public List<Productos> obtenerProductos(){
		List<Productos> productos = productoService.getAllProductos();
		return productos;
	}
	
	@GetMapping(value = "/reporte", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> reporteProductos() throws IOException, JRException {
		try {
			InputStream report = this.productoService.getReportProductos();
			byte[] data = report.readAllBytes();
			report.close();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_PDF);
			header.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"reporte_estudiantes.pdf\"");
			header.setContentLength(data.length);
			
			return new ResponseEntity<byte[]>(data, header, HttpStatus.CREATED);
			
		} catch (Exception e) {
			throw new RuntimeException("IOError retornando archivo");
			
			
		}
	}

}
