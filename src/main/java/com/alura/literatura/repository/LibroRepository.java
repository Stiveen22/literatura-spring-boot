package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByIdioma(String idioma);

    @Query("SELECT COUNT(l) FROM Libro l WHERE l.idioma = :idioma")
    int countLibrosByLanguage(@Param("idioma") String idioma);
}
