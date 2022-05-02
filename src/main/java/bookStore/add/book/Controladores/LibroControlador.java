
package bookStore.add.book.Controladores;
import bookStore.add.book.Entidades.Autor;
import bookStore.add.book.Entidades.Editorial;
import bookStore.add.book.Entidades.Libro;
import bookStore.add.book.Servicios.AutorServicio;
import bookStore.add.book.Servicios.EditorialServicio;
import bookStore.add.book.Servicios.ErrorLibreriaServicio;
import bookStore.add.book.Servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
@RequestMapping("/AdmLibros")
public class LibroControlador {
    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;  
    
    
    @GetMapping("/RegistroLibro")
    public String RegistroLibro(){
        return "RegistroLibro.html";
    }
    
    @PostMapping("/RegistraLibro")
    public String RegistraLibro(ModelMap modelo, @RequestParam String titulo,@RequestParam(required = false) Integer anio,@RequestParam(required = false) Integer ejemplares,@RequestParam String editorial,@RequestParam String autor){
        System.out.println("titulo"+ titulo);
        Autor autor1 = autorServicio.buscarPorNombre(autor);
        Editorial editorial1 =  editorialServicio.buscarPorNombre(editorial);
        
        try{
            libroServicio.registrar(titulo, anio, ejemplares, editorial1, autor1, true);
            modelo.put("exito", "Registro exitoso");
        }catch(ErrorLibreriaServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("titulo", titulo);
            modelo.put("anio", anio);
            modelo.put("ejemplares ", ejemplares);
            modelo.put("editorial", editorial);
            modelo.put("autor", autor);
            return "RegistroLibro";
        }
        modelo.put("titulo", "Bienvenidos a la libreria Aurora");
        modelo.put("descripcion", "Libro registrado con exito!");
        return "exito";
        
    }
    @GetMapping("/consultaLibro")
    public String consultaLibro(ModelMap modelo){
        List<Libro> Libros =  libroServicio.listar();
        modelo.addAttribute("Libros", Libros);
        return "ConsultaLibro.html";
    
    }
    @GetMapping("/editarLibro/{isbn}")
    public String editar(@PathVariable("isbn") String isbn, ModelMap modelo,RedirectAttributes ra){
        Libro libro = libroServicio.buscarPorIsbn(isbn);
        List<Libro> Libros =  libroServicio.listar();
        modelo.addAttribute("isbn", isbn);
        modelo.addAttribute("libro", libro);
        modelo.addAttribute("titulo", libro.getTitulo());
        modelo.addAttribute("anio", libro.getAnio());
        modelo.addAttribute("ejemplares", libro.getEjemplares());
        modelo.addAttribute("autores", libro.getAutor());
        modelo.addAttribute("editoriales", libro.getEditorial());
        modelo.addAttribute("Libros", Libros);
        return "ModificacionLibro.html";
    
    }
    @PostMapping("/modificacionLibro")
    public String modificaLibro(ModelMap modelo,@RequestParam(required = false) String isbn, @RequestParam(required = false) String titulo,@RequestParam(required = false) Integer anio,@RequestParam(required = false) Integer ejemplares,@RequestParam(required = false) String editorial,@RequestParam(required = false) String autor) throws ErrorLibreriaServicio{
        Editorial editorial1 = editorialServicio.buscarPorNombre(editorial);
        Autor autor1 = autorServicio.buscarPorNombre(autor);
        Libro libro = libroServicio.buscarPorIsbn(isbn);
        try{
            libroServicio.mofificar(titulo, anio, ejemplares, editorial1, autor1, true);
    }catch (ErrorLibreriaServicio ex){
        modelo.put("libro", libro);
        modelo.addAttribute("isbn", isbn);
        modelo.addAttribute("error", ex.getMessage());
        return "redirect:/editarLibro/{isbn}";
    }
        modelo.put("titulo", "LIBRO MODIFICADO CON EXITO.");
        modelo.put("descripcion", "BIEN HECHO.");
        return "index";
    }
    
    /*@GetMapping("/libro{isbn}")
    public String editarLibro(@PathVariable("isbn") String isbn,RedirectAttributes ra, ModelMap modelo){
        Libro libro = libroServicio.buscarPorIsbn(isbn);
        modelo.put("isbn", libro.getIsbn());
        modelo.put("titulo", libro.getTitulo());
        modelo.put("anio", libro.getAnio());
        modelo.put("autores", autorServicio.listar());
        modelo.put("editoriales", editorialServicio.listar());
        modelo.put("ejemplares", libro.getEjemplares());
        modelo.put("Alta", libro.isAlta());
        return "/consultaLibro";
     }
    
    
    
    */
    
    @GetMapping("/baja/Libro{isbn}")
    public String bajaEditorial(ModelMap modelo,@PathVariable("isbn") String isbn){
        try{
            libroServicio.deshabilitar(isbn);
        
        }catch (ErrorLibreriaServicio ex){
            modelo.put("error", ex.getMessage());
            return "ConsultaLibro";
        
        }
        modelo.put("titulo", "LIBRO DESHABILITADO CON EXITO.");
        modelo.put("descripcion", "BIEN HECHO.");
        return "exito";
            
}    
    @GetMapping("/alta/Libro{isbn}")
    public String altaEditorial(ModelMap modelo,@PathVariable("isbn") String isbn){
        try{
            libroServicio.habilitar(isbn);
        
        }catch (ErrorLibreriaServicio ex){
            modelo.put("error", ex.getMessage());
            return "ConsultaLibro";
        
        }
        modelo.put("titulo", "LIBRO HABILITADO CON EXITO.");
        modelo.put("descripcion", "BIEN HECHO.");
        return "exito";
            
}    
 @RequestMapping("/accessdenied")
        public ModelAndView accessdenied() {
            return new ModelAndView("accessdenied");
        }
 
}
