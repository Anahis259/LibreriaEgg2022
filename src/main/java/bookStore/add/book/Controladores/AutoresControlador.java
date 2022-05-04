package bookStore.add.book.Controladores;
import bookStore.add.book.Entidades.Autor;
import bookStore.add.book.Repositorio.AutorRepositori;
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

@Controller
@RequestMapping("/AdmAutores")
public class AutoresControlador {
    @Autowired 
    private AutorServicio autorServicio;  
    @Autowired
    private EditorialServicio editorialServicio; 
    @Autowired
    private LibroServicio libroServicio; 
    
    @GetMapping("/RegistroAutor")
    public String registroAutor(){
        return "RegistroAutor.html";
    }
    
    
     @PostMapping("/Registrar")
    public String RegistroAutor(ModelMap modelo, @RequestParam String nombre){
        System.out.println("nombre"+nombre);
        try{
            autorServicio.registrar(nombre);
            
        }catch(ErrorLibreriaServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            
            return "RegistroAutor";
        
        }
        modelo.put("titulo", "Bienvenidos a la libreria Aurora");
        modelo.put("descripcion", "Autor(ar) registrado con exito!");
        return "exito";
    }
    @GetMapping("/consultaAutor")
    public String consultaAutor (ModelMap modelo){
        List<Autor> Autores =  autorServicio.listar();
        modelo.addAttribute("Autores", Autores);
        return "ListaAutor.html";   
}    
    @GetMapping("/Autor{id}")
    public String eliminar(ModelMap modelo,@PathVariable("id") String id) throws ErrorLibreriaServicio{
        
        try{
            autorServicio.eliminar(id);
            
        } catch (ErrorLibreriaServicio ex){
            modelo.put("error", ex.getMessage()); 
            return "ListaAutor";
        }
        modelo.put("titulo", "Registro deshabilitado con exito!");
        modelo.put("descripcion", "Bien hecho!");
        return "exito";
    }
    
    @GetMapping("alta/Autor{id}")
    public String habilitar(ModelMap modelo,@PathVariable("id") String id) throws ErrorLibreriaServicio{
        
        try{
            autorServicio.habilitar(id);
            
        } catch (ErrorLibreriaServicio ex){
            modelo.put("error", ex.getMessage()); 
            return "ListaAutor";
        }
        modelo.put("titulo", "Registro habilitado con exito!");
        modelo.put("descripcion", "Bien hecho!");
        return "exito";
    }
@GetMapping("/editarAutor/{id}")
    public String editar(@PathVariable("id") String id, ModelMap modelo){
        Autor autor = autorServicio.buscarAutorPorId(id);
        modelo.addAttribute("autor", autor);
        modelo.addAttribute("nombre", autor.getNombre());
        
        return "ModificacionAutor.html";
    
    }
    @PostMapping("/modificacionAutor")
    public String modificaAutor(ModelMap modelo,@RequestParam(required = false) String id, @RequestParam(required = false) String nombre ) throws ErrorLibreriaServicio{
        
        Autor autor = autorServicio.buscarAutorPorId(id);
        try{
            autorServicio.modificar(id, nombre);
    }catch (ErrorLibreriaServicio ex){
        modelo.put("autor", autor);
        
        modelo.addAttribute("error", ex.getMessage());
        return "redirect:/editarAutor/{id}";
    }
        modelo.put("titulo", "Autor MODIFICADO CON EXITO.");
        modelo.put("descripcion", "BIEN HECHO.");
        return "exito";
    }    
    
     
    
     
    
    
}
