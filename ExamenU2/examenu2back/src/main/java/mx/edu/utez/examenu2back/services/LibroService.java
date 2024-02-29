package mx.edu.utez.examenu2back.services;

import mx.edu.utez.examenu2back.models.Libro;
import mx.edu.utez.examenu2back.models.LibroRepository;
import mx.edu.utez.examenu2back.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional

public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Transactional(readOnly = true)
    public Response<List<Libro>> getAll() {
        return new Response<>(
                this.libroRepository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<List<Libro>> getByOrderByFechaPublicacionDesc() {
        return new Response<>(
                this.libroRepository.findAllByOrderByFechaPublicacionDesc(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<List<Libro>> getByOrderByAutorDesc() {
        return new Response<>(
                this.libroRepository.findAllByOrderByAutorDesc(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<List<Libro>> getByImagenIsNotNull() {
        return new Response<>(
                this.libroRepository.findByImagenIsNotNull(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Libro> create(Libro libro) {
        return new Response<>(
                this.libroRepository.saveAndFlush(libro),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Libro> update(Libro libro) {
        if (this.libroRepository.existsById(libro.getId())) {
            return new Response<>(
                    this.libroRepository.saveAndFlush(libro),
                    false,
                    200,
                    "OK"
            );
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "No existe el registro"
            );
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Libro> delete(Long id) {
        if (this.libroRepository.existsById(id)) {
            this.libroRepository.deleteById(id);
            return new Response<>(
                    null,
                    false,
                    200,
                    "OK"
            );
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "No existe el registro"
            );
        }
    }


}
