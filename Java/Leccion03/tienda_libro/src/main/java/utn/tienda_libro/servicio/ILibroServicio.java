package utn.tienda_libro.servicio;


import utn.tienda_libro.modelo.Libro;

import java.util.List;

public interface ILibroServicio {
    public List<Libro> listarLibros();

    public Libro buscarLibroPorId(Integer idLibro);

    public void guardarLibro(Libro Libro);

    public void eliminarLibro(Libro Libro);
}
