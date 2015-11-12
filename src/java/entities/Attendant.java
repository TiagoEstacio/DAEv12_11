
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJBException;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
*listar os meus eventos, para que possa estar a par dos mesmos (exemplo: listar as aulas do participante aluno1@my.ipleiria.pt);

*marcar presença em determinado evento que está a ocorrer (e cuja marcação de presença está aberta), para que fique
registado que estive presente nesse evento (exemplo: marcar presença no evento1: aula T
de DAE de 14.out.2015).
 */
@Entity
//@Table(name = "ATTENDANTS")
@NamedQueries({
    @NamedQuery(
        name="getAllAttendants",
        query="SELECT at FROM Attendant at ORDER BY at.id"
    )
})
public class Attendant extends User implements Serializable {
    
    @ManyToMany(mappedBy = "attendants")
    private List<Event> events;
    
    @ManyToMany(mappedBy = "attendants")
    private List<Category> categories;
    
    public Attendant() {
        this.events = new LinkedList<>();
        this.categories = new LinkedList<>();
    }
    
    public Attendant(String username, String password, String name, String email) {
        super(username, password, name, email);
        this.events = new LinkedList<>();
        this.categories = new LinkedList<>();
    }
    
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    public void addEvent(Event event){
        try {
            if (!events.contains(event)){
                events.add(event);
            }
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    public void removeEvent(Event event){
        try {
            if (events.contains(event)){
                events.remove(event);
            }
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    public int getNumberOfEvents(){
        return this.events.size();
    }
    
    public void addCategory(Category category){
        try {
            if (!categories.contains(category)){
                categories.add(category);
            }
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    public void removeCategory(Category category){
        try {
            if (categories.contains(category)){
                categories.remove(category);
            }
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    public int getNumberOfCategories(){
        return this.categories.size();
    }

    @Override
    public String toString() {
        return "entities.Attendant[id=" + id + "]: "+ name;
    }
    
}
