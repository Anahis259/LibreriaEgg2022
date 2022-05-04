package bookStore.add.book.Servicios;
import bookStore.add.book.Entidades.Autor;
import bookStore.add.book.Entidades.Editorial;
import bookStore.add.book.Entidades.Libro;
import bookStore.add.book.Repositorio.LibroRepositori;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositori libroRepositorio;
    
    @Transactional
    public void registrar(String nombre, Integer anio, Integer TotalEjemplares, Editorial editorial, Autor autor, boolean alta) throws ErrorLibreriaServicio{
         validar(nombre, anio, TotalEjemplares, editorial, autor);
        Libro libro = new Libro(); 
        libro.setTitulo(nombre);
        libro.setAnio(anio);
        libro.setEditorial(editorial);
        libro.setAutor(autor);
        libro.setEjemplares(TotalEjemplares);
        libro.setAlta(Boolean.TRUE);
        libroRepositorio.save(libro);
        
    }
      @Transactional
    public void mofificar(String isbn, String nombre, Integer anio, Integer ejemplares, Editorial editorial, Autor autor, boolean alta) throws ErrorLibreriaServicio{
        validar(nombre, anio, ejemplares, editorial, autor);
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        if(respuesta.isPresent()){
            Libro libro = respuesta.get(); 
                libro.setTitulo(nombre);
                libro.setAnio(anio);
                libro.setEditorial(editorial);
                libro.setAutor(autor);
                libro.setEjemplares(ejemplares);
                libro.setAlta(Boolean.TRUE);
                libroRepositorio.save(libro);
        }else{
            throw new ErrorLibreriaServicio("No se encuentra el id solicitado");
        }
    }
    
    public void deshabilitar (String isbn)throws ErrorLibreriaServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        
        if(respuesta.isPresent()){
            Libro libro = libroRepositorio.findById(isbn).get(); 
            
                libro.setAlta(Boolean.FALSE);
                libroRepositorio.save(libro);
            
        }else{
            throw new ErrorLibreriaServicio("No se encuentra el id solicitado");
        }
    }
    public void habilitar(String isbn) throws ErrorLibreriaServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        
        if (respuesta.isPresent()){
            Libro libro = libroRepositorio.findById(isbn).get();
           
                libro.setAlta(Boolean.TRUE);
                libroRepositorio.save(libro);
        }else{
            throw new ErrorLibreriaServicio("No se encuentra el id solicitado para eliminar");
        }
          
    }
    public void validar(String titulo, Integer anio, Integer ejemplares, Editorial editorial, Autor autor) throws ErrorLibreriaServicio{
        if (titulo == null || titulo.isEmpty()){
            throw new ErrorLibreriaServicio("El nombre no puede ser nulo ni puede estar vacio");
        }
        if (anio == null || anio <= 1800 ){
            throw new ErrorLibreriaServicio("Debe tener el año de reproduccion.");
        }
        if (ejemplares == null || ejemplares < 1){
            throw new ErrorLibreriaServicio("Debe poseer una cantidad mayor a 1 ejemplares");
        }
        if(editorial == null){
            throw new ErrorLibreriaServicio("Debe poseer una editorial registrads");
        }
        if(autor == null){
            throw new ErrorLibreriaServicio("Debe poseer un autor registrads");
        }
    }
    public Libro buscarPorIsbn(String isbn){
        return libroRepositorio.BuscarLibroPorIsbn(isbn);
    
    }
    public List<Libro> listar(){
        return libroRepositorio.Listar();
    
    }    
}

