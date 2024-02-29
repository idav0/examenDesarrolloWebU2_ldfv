package mx.edu.utez.examenu2back.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findAllByOrderByFechaPublicacionDesc();

    List<Libro> findAllByOrderByAutorDesc();

    List<Libro> findByImagenIsNotNull();



}
