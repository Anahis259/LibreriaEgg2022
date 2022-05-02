
package bookStore.add.book.Repositorio;


import bookStore.add.book.Entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibreriaRepositori extends JpaRepository<Editorial, String>{
     @Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
     public Editorial BuscarEditorialPorNombre(@Param ("nombre")String nombre);
     
     
     
     @Query("SELECT c FROM Editorial c WHERE c.id = :id")
     public Editorial BuscarEditorialPorId(@Param ("id")String id);
     
     @Query("SELECT c FROM Editorial c WHERE c.Alta IS TRUE OR c.Alta IS FALSE")
    public List<Editorial> listar();
}

