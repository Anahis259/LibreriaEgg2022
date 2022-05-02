
package bookStore.add.book.Repositorio;


import bookStore.add.book.Entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepositori extends JpaRepository<Libro, String>{
    @Query("SELECT c FROM Libro c WHERE c.titulo = :titulo")
    public Libro BuscarLibroPorNombre(@Param ("titulo")String titulo);
    
    @Query("SELECT c FROM Libro c WHERE c.isbn = :isbn")
    public Libro BuscarLibroPorIsbn(@Param ("isbn")String isbn);
    
    @Query("SELECT c FROM Libro c WHERE c.Alta IS TRUE OR c.Alta IS FALSE")
    public List<Libro> Listar();
}
