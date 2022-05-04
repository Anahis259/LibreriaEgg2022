
package bookStore.add.book.Servicios;
import bookStore.add.book.Entidades.Editorial;
import bookStore.add.book.Repositorio.LibreriaRepositori;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    
    @Autowired
    private LibreriaRepositori editorialRepositorio;
      @Transactional
    public void registrar(String nombre, boolean alta) throws ErrorLibreriaServicio{
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);
        editorialRepositorio.save(editorial);
    } 
      @Transactional
    public void modificar(String id,String nombre) throws ErrorLibreriaServicio{
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()){
            Editorial editorial = respuesta.get();
                editorial.setNombre(nombre);
                editorialRepositorio.save(editorial);
            }else{
                throw new ErrorLibreriaServicio("No coincide con un id registrado");
        }
             
    }
    
    public void eliminar( String id) throws ErrorLibreriaServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()){
            Editorial editorial = editorialRepositorio.findById(id).get();
                editorial.setAlta(Boolean.FALSE);
                editorialRepositorio.save(editorial);
        }else{
            throw new ErrorLibreriaServicio("No se encuentra el id solicitado para eliminar");
        }
           
    }
    public void habilitar(String id) throws ErrorLibreriaServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()){
            Editorial editorial = editorialRepositorio.findById(id).get();
                editorial.setAlta(Boolean.TRUE);
                editorialRepositorio.save(editorial);
            
        }else{
            throw new ErrorLibreriaServicio("No se encuentra el id solicitado para eliminar");
        }
          
    }
    
    public void validar (String nombre) throws ErrorLibreriaServicio{
        if (nombre == null || nombre.isEmpty()){
            throw new ErrorLibreriaServicio("El nombre no puede ser nulo o vacio.");
        }
    }
    
    public Editorial buscarPorNombre(String nombre) {
        return editorialRepositorio.BuscarEditorialPorNombre(nombre);
    }
    public Editorial buscarPorId(String id){
        return editorialRepositorio.BuscarEditorialPorId(id);
    
    }
    public List<Editorial> listar(){
        return editorialRepositorio.listar();
    
    }
}
 
