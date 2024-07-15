package com.alura.literatura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alura.literatura.client.GutendexClient;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.repository.AutorRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/buscar")
    public List<Libro> buscarLibroPorTitulo(@RequestParam String query) {
        return libroRepository.findByTituloContainingIgnoreCase(query);
    }

    @GetMapping
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/idioma")
    public int contarLibrosPorIdioma(@RequestParam String idioma) {
        return libroRepository.countLibrosByLanguage(idioma);
    }
}
