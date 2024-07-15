package com.alura.literatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.literatura.model.Libro;
import com.alura.literatura.model.Autor;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.repository.AutorRepository;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			mostrarMenu();
			int opcion = scanner.nextInt();
			scanner.nextLine();  // Consumir la nueva línea
			switch (opcion) {
				case 1:
					System.out.println("Ingrese el título del libro:");
					String titulo = scanner.nextLine();
					buscarLibroPorTitulo(titulo);
					break;
				case 2:
					listarLibros();
					break;
				case 3:
					System.out.println("Ingrese el idioma:");
					String idioma = scanner.nextLine();
					listarLibrosPorIdioma(idioma);
					break;
				case 4:
					listarAutores();
					break;
				case 5:
					System.out.println("Ingrese el año:");
					int ano = scanner.nextInt();
					listarAutoresVivosEnAno(ano);
					break;
				case 0:
					System.exit(0);
					break;
				default:
					System.out.println("Opción no válida. Intente nuevamente.");
			}
		}
	}

	private void mostrarMenu() {
		System.out.println("Seleccione una opción:");
		System.out.println("1. Buscar libro por título");
		System.out.println("2. Listar todos los libros");
		System.out.println("3. Listar libros por idioma");
		System.out.println("4. Listar todos los autores");
		System.out.println("5. Listar autores vivos en un determinado año");
		System.out.println("0. Salir");
	}

	private void buscarLibroPorTitulo(String titulo) {
		List<Libro> librosEncontrados = libroRepository.findByTituloContainingIgnoreCase(titulo);
		if (!librosEncontrados.isEmpty()) {
			System.out.println("Libros encontrados con título que contiene '" + titulo + "':");
			librosEncontrados.forEach(System.out::println);
		} else {
			System.out.println("No se encontraron libros con el título proporcionado.");
		}
	}

	private void listarLibros() {
		List<Libro> libros = libroRepository.findAll();
		System.out.println("Lista de libros:");
		libros.forEach(System.out::println);
	}

	private void listarLibrosPorIdioma(String idioma) {
		List<Libro> libros = libroRepository.findByIdioma(idioma);
		System.out.println("Lista de libros en idioma " + idioma + ":");
		libros.forEach(System.out::println);
	}

	private void listarAutores() {
		List<Autor> autores = autorRepository.findAll();
		System.out.println("Lista de autores:");
		autores.forEach(System.out::println);
	}

	private void listarAutoresVivosEnAno(int ano) {
		List<Autor> autores = autorRepository.findAutoresVivosEnAnio(ano);
		if (!autores.isEmpty()) {
			System.out.println("Autores vivos en el año " + ano + ":");
			autores.forEach(System.out::println);
		} else {
			System.out.println("No se encontraron autores vivos en el año " + ano + ".");
		}
	}
}
