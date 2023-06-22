package com.cibertec.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.edu.models.Productos;
import com.cibertec.edu.repositories.ProductoDao;

public class MainController {
	@Autowired
	private ProductoDao productoRepository;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/registro")
	public String registrarProducto(@ModelAttribute("producto") Productos producto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "registro";
		} else {
			return "acceso_denegado";
		}
	}
	
	@PostMapping("/registro")
	public String registrarProducto(@Validated @ModelAttribute("producto") Productos producto, BindingResult binding) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			if(binding.hasErrors()) {
				return "registro";
			}
			
			productoRepository.save(producto);
			
			return "redirect:/";
			
		} else {
			return "acceso_denegado";
		}
	}

}
