package bookStore.add.book.Servicios;
import bookStore.add.book.Entidades.Autor;
import bookStore.add.book.Repositorio.AutorRepositori;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class AutorServicio{
    @Autowired
    private AutorRepositori autorRepositorio;
    
    
    @Transactional
    public void registrar(String nombre) throws ErrorLibreriaServicio{
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(Boolean.TRUE);
        autorRepositorio.save(autor);
    } 
    
            
    
    @Transactional
    public void modificar(String nombre, String idLibro) throws ErrorLibreriaServicio{
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(nombre);
        
        if (respuesta.isPresent()){
            Autor autor = respuesta.get();
            if (autor.getNombre().toUpperCase().equals(nombre)){
                autor.setNombre(nombre);
                autorRepositorio.save(autor);
            }else{
                throw new ErrorLibreriaServicio("No coincide con un id registrado");
            }
        }else{
            throw new ErrorLibreriaServicio("No se encuentra el id solicitado");
        }
             
    }
    
    public void eliminar( String id) throws ErrorLibreriaServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if (respuesta.isPresent()){
            Autor autor = autorRepositorio.findById(id).get();
                autor.setAlta(Boolean.FALSE);
                autorRepositorio.save(autor);
            }else{
                throw new ErrorLibreriaServicio("No coincide con un id registrado");
            }    
    }
    public void habilitar(String id) throws ErrorLibreriaServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if (respuesta.isPresent()){
            Autor autor = autorRepositorio.findById(id).get();
                autor.setAlta(Boolean.TRUE);
                autorRepositorio.save(autor);
            }else{
                throw new ErrorLibreriaServicio("No se encuentra el id solicitado para eliminar");
            }
            
        }
          
    
    
    
    public void validar (String nombre) throws ErrorLibreriaServicio{
        if (nombre == null || nombre.isEmpty()){
            throw new ErrorLibreriaServicio("El nombre no puede ser nulo o vacio.");
        }
    }
    @Transactional
    public List<Autor> listar() {
         return autorRepositorio.listar();
        
    }
    public Autor buscarPorNombre(String nombre) {
        return  autorRepositorio.buscarAutorPorNombre(nombre);
    }
    public Autor buscarAutorPorId(String id){
        return autorRepositorio.buscarAutorPorId(id);
    }
}

