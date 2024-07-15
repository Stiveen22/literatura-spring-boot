package com.alura.literatura.controller;

import com.alura.literatura.model.Autor;
import com.alura.literatura.repository.AutorService;
import com.alura.literatura.repository.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @GetMapping("/contarLibrosPorIdioma")
    public String contarLibrosPorIdioma(@RequestParam String idioma) {
        int cantidadLibros = libroService.contarLibrosPorIdioma(idioma);
        return "Cantidad de libros en idioma '" + idioma + "': " + cantidadLibros;
    }

    @GetMapping("/listarAutoresVivosEnAnio")
    public String listarAutoresVivosEnAnio(@RequestParam int anio) {
        List<Autor> autores = autorService.listarAutoresVivosEnAnio(anio);

        // Manejo de caso vacío
        if (autores.isEmpty()) {
            return "No hay autores vivos en el año " + anio;
        }

        StringBuilder sb = new StringBuilder("Autores vivos en el año ").append(anio).append(":\n");
        for (Autor autor : autores) {
            sb.append(autor.getNombre()).append(" (").append(autor.getAnoNacimiento())
                    .append(" - ").append(autor.getAnoFallecimiento()).append(")\n");
        }
        return sb.toString();
    }
}
