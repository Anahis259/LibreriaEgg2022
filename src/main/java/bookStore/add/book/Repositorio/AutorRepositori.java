package bookStore.add.book.Repositorio;
import bookStore.add.book.Entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepositori extends JpaRepository<Autor, String> {
    @Query("SELECT c FROM Autor c WHERE c.nombre = :nombre")
    public Autor buscarAutorPorNombre(@Param ("nombre")String nombre);            
    
    @Query("SELECT c FROM Autor c WHERE c.id = :id")
    public Autor buscarAutorPorId(@Param ("id")String id);
    
    @Query("SELECT c FROM Autor c WHERE c.Alta IS TRUE OR c.Alta IS FALSE")
    public List<Autor> listar();
            
}

