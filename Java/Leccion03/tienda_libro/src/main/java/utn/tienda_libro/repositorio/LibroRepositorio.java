package utn.tienda_libro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.tienda_libro.modelo.Libro;

public interface LibroRepositorio extends JpaRepository <Libro, Integer> {
}
