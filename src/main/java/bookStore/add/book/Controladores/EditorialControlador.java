
package bookStore.add.book.Controladores;

import bookStore.add.book.Entidades.Editorial;
import bookStore.add.book.Servicios.EditorialServicio;
import bookStore.add.book.Servicios.ErrorLibreriaServicio;
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
@RequestMapping("/AdmEditoriales")
public class EditorialControlador {
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/consultaEditorial")
    public String consultaEditorial(ModelMap modelo){
        List<Editorial> Editoriales =  editorialServicio.listar();
        modelo.addAttribute("Editoriales", Editoriales);
        return "ConsultaEditorial.html";
    
    }
    @GetMapping("/baja/Editorial{id}")
    public String bajaEditorial(ModelMap modelo,@PathVariable("id") String id){
        try{
            editorialServicio.eliminar(id);
        
        }catch (ErrorLibreriaServicio ex){
            modelo.put("error", ex.getMessage());
            return "ConsultaEditorial";
        
        }
        modelo.put("titulo", "EDITORIAL DESHABILITADO CON EXITO.");
        modelo.put("descripcion", "BIEN HECHO.");
        
        
        return "exito";
            
}    
    @GetMapping("/alta/Editorial{id}")
    public String altaEditorial(ModelMap modelo,@PathVariable("id") String id){
        try{
            editorialServicio.habilitar(id);
        
        }catch (ErrorLibreriaServicio ex){
            modelo.put("error", ex.getMessage());
            return "ConsultaEditorial";
        
        }
        modelo.put("titulo", "EDITORIAL HABILITADO CON EXITO.");
        modelo.put("descripcion", "BIEN HECHO.");
        return "exito";
            
}
@GetMapping("/editarEditorial/{id}")
    public String editar(@PathVariable("id") String id, ModelMap modelo){
        Editorial editorial = editorialServicio.buscarPorId(id);
        modelo.addAttribute("editorial", editorial);
        modelo.addAttribute("editorial", editorial.getNombre());
        
        return "ModificacionEditorial.html";
    
    }
    
    @PostMapping("/modificacionEditorial")
    public String modificaEditorial(ModelMap modelo,@RequestParam(required = false) String id, @RequestParam(required = false) String nombre ) throws ErrorLibreriaServicio{
        
        Editorial editorial = editorialServicio.buscarPorId(id);
        try{
            editorialServicio.modificar(id, nombre);
    }catch (ErrorLibreriaServicio ex){
        modelo.put("editorial", editorial);
        
        modelo.addAttribute("error", ex.getMessage());
        return "redirect:/editarEditorial/{id}";
    }
        modelo.put("titulo", "Editorial MODIFICADO CON EXITO.");
        modelo.put("descripcion", "BIEN HECHO.");
        return "exito";
    }    
     
}
