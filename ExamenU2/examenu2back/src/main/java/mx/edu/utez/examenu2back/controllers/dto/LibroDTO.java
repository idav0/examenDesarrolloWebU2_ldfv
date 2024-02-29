package mx.edu.utez.examenu2back.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.examenu2back.models.Libro;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LibroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private Date fecha_publicacion;
    private String imagen;

    public Libro getLibro() {
        return new Libro(
                this.id,
                this.titulo,
                this.autor,
                this.fecha_publicacion,
                this.imagen
        );
    }

    public Libro getLibroWithoutImage() {
        return new Libro(
                this.id,
                this.titulo,
                this.autor,
                this.fecha_publicacion,
                null
        );
    }


}
