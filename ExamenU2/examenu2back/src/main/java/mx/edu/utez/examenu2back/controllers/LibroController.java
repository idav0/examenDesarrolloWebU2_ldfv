package mx.edu.utez.examenu2back.controllers;


import mx.edu.utez.examenu2back.controllers.dto.LibroDTO;
import mx.edu.utez.examenu2back.models.Libro;
import mx.edu.utez.examenu2back.services.LibroService;
import mx.edu.utez.examenu2back.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api-examenu2/libro")
@CrossOrigin({"*"})
public class LibroController {


    @Autowired
    private LibroService libroService;


    @GetMapping("/")
    public ResponseEntity<Response<List<Libro>>> getAll() {
        return new ResponseEntity<>(
                this.libroService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/fechaPublicacion")
    public ResponseEntity<Response<List<Libro>>> getByOrderByFechaPublicacionDesc() {
        return new ResponseEntity<>(
                this.libroService.getByOrderByFechaPublicacionDesc(),
                HttpStatus.OK
        );
    }

    @GetMapping("/autor")
    public ResponseEntity<Response<List<Libro>>> getByOrderByAutorDesc() {
        return new ResponseEntity<>(
                this.libroService.getByOrderByAutorDesc(),
                HttpStatus.OK
        );
    }

    @GetMapping("/imagen")
    public ResponseEntity<Response<List<Libro>>> getByImagenIsNotNull() {
        return new ResponseEntity<>(
                this.libroService.getByImagenIsNotNull(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Libro>> create(@RequestBody LibroDTO libro) {
        return new ResponseEntity<>(
                this.libroService.create(libro.getLibro()),
                HttpStatus.OK
        );
    }

    @PostMapping("/no-image")
    public ResponseEntity<Response<Libro>> createWithoutImage(@RequestBody LibroDTO libro) {
        return new ResponseEntity<>(
                this.libroService.create(libro.getLibroWithoutImage()),
                HttpStatus.OK
        );
    }

    @PutMapping("/")
    public ResponseEntity<Response<Libro>> update(@RequestBody LibroDTO libro) {
        return new ResponseEntity<>(
                this.libroService.update(libro.getLibro()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Libro>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(
                this.libroService.delete(id),
                HttpStatus.OK
        );
    }

}
