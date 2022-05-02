package bookStore.add.book.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Autor  implements Serializable{
    
    /*@GeneratedValue(strategy=GenerationType.AUTO)*/
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id; 
    private String nombre; 
    private boolean Alta; 

    public Autor() {
    }

    public Autor(String id, String nombre, boolean Alta) {
        this.id = id;
        this.nombre = nombre;
        this.Alta = Alta;
    }

    public String getId() {
        return id;
    }

    public boolean isAlta() {
        return Alta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAlta(boolean Alta) {
        this.Alta = Alta;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre=" + nombre + ", Alta=" + Alta + '}';
    }


    
}
