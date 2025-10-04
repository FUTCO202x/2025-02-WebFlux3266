package com.Taller.CrudBasics.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.Taller.CrudBasics.models.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class ControlAPI {
	
	private ArrayList<Usuario> usuarios = new ArrayList<>();	
	
	
	@GetMapping("/")
	public List<Usuario> VerTodos() {
		return usuarios;
	}
	
	@PostMapping("/add")
	public String Agregar(@RequestBody Usuario entity) {
		usuarios.add(entity);
		return entity.getNombre()+" ha sido agregado.";
	}
	
	@PostMapping("/addVarius")
	public String AgregarVarios(@RequestBody List<Usuario> entityVarius) {
		usuarios.addAll(entityVarius);
		return entityVarius.size() + " se han agregado.";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminarUsuario(@PathVariable long id) {
		for (Usuario user : usuarios) {
			if (user.getId()==id) {
				usuarios.remove(user);
				return "El usuario ha sido eliminado";
			}
		}
		return "El usuario no ha sido eliminado";
	}
	
	
	@PostMapping("/search/{id}")
	public Usuario verPorId(@PathVariable long id) {
		for (Usuario user : usuarios) {
			if (user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	@GetMapping("/param")
	public String verNombreUsuario(@RequestParam String nombre,@RequestParam String apellido) {
		return "NombreCompleto: "+nombre+" "+apellido;
	}
	
	
	
}
