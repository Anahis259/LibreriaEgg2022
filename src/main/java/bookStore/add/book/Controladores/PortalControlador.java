
package bookStore.add.book.Controladores;
import bookStore.add.book.Servicios.EditorialServicio;
import bookStore.add.book.Servicios.ErrorLibreriaServicio;
import bookStore.add.book.Servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired 
    private LibroServicio libroServicio;
    @Autowired
    private EditorialServicio editorialServicio;  
    
    
    @GetMapping("/index")
    public String index(){
        return "index.html";
    }
    
    
    @GetMapping("/RegistroEditorial")
    public String RegistroEditorial(){
        return "RegistroEditorial.html";
    }
    @GetMapping("/exito")
    public String exito(){
        return "exito.html";
    }
    
    
    @PostMapping("/RegistraEditorial")
    public String RegistraEditorial(ModelMap modelo, @RequestParam String nombre, boolean alta)throws ErrorLibreriaServicio{
        System.out.println("nombre"+nombre);
        try{
            editorialServicio.registrar(nombre, alta);
            modelo.put("exito", "Registro exitoso");
        }catch(ErrorLibreriaServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            
            return "RegistroEditorial";
        
        }
        modelo.put("titulo", "Bienvenidos a la libreria Aurora");
        modelo.put("descripcion", "Editorial registrada con exito!");
        return "exito";
    }
    
    
    @RequestMapping("/accessdenied")
        public ModelAndView accessdenied() {
            return new ModelAndView("accessdenied");
        }

}

