package com.alura.literatura.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public int contarLibrosPorIdioma(String idioma) {
        return libroRepository.countLibrosByLanguage(idioma);
    }
}
