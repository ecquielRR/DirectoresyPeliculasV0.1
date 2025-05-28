package com.EzequielRosales.controladores;
import java.util.HashMap;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorPeliculas {
	private static HashMap<String, String> listaPeliculas = new HashMap<String, String>();
	
	public ControladorPeliculas() {
		listaPeliculas.put("Winnie the Pooh", "Don Hall");	
		listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
		listaPeliculas.put("Tarzán", "Kevin Lima");		
		listaPeliculas.put("Mulán", "Barry Cook");
		listaPeliculas.put("Oliver", "Kevin Lima");	
		listaPeliculas.put("Big Hero 6", "Don Hall");	
	}
	
	@GetMapping("/peliculas")
	public String buscarTodasLasPeliculas() {
		String stringLista = "";
	       for(String titulo : listaPeliculas.keySet()){
	    	   stringLista += "Pelicula: " + titulo + " | " ;
	       }
	       return stringLista;
	}
	
	@GetMapping("/peliculas/{nombrePelicula}")
	public String buscarPeliculaPorNombre(@PathVariable String nombrePelicula) {
		for(String titulo : listaPeliculas.keySet()) {
			String director = "";
			director += listaPeliculas.get(titulo);
			if (titulo.equalsIgnoreCase(nombrePelicula)) {
				return "Pelicula: " + titulo + " Director: " + director;
			}
		}
		return "La película no se encuentra en nuestra lista.";
	}
	
	@GetMapping("/peliculas/director/{nombreDirector}")
	public String buscarPeliculaPorDirector(@PathVariable String nombreDirector) {
	    String impresion = "";
	    boolean encontrado = false;

	    for (String titulo : listaPeliculas.keySet()) {
	        String director = listaPeliculas.get(titulo);
	        if (director.equalsIgnoreCase(nombreDirector)) {
	            if (!encontrado) {
	                impresion += "Películas dirigidas por " + director + ": ";
	                encontrado = true;
	            }
	            impresion += titulo + " ";
	        }
	    }

	    if (encontrado) {
	        return impresion.trim();
	    } else {
	        return "No contamos con películas de ese director en nuestra lista.";
	    }
	}
}