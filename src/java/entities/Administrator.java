
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * criar, consultar/listar, atualizar e remover um utilizador (administrador,
 * responsável ou participante), para que possa manter atualizada a lista de
 * utilizadores da aplicação empresarial;
 *
 * criar, consultar/listar, atualizar e remover eventos de calendário (aulas,
 * reuniões, seminários, etc.), para que possa manter atualizada a lista de
 * eventos onde ocorrerá uma marcação/verificação de presenças;
 *
 * criar, consultar/listar, atualizar e remover um utilizador responsável por
 * determinado evento (exemplo: evento1 - aula de DAE T, quartas-feiras,
 * 18h-20h, sala A.S.2.12, semanas 38:51, 1:2, responsável:
 * ricardo.martinho@ipleiria.pt);
 *
 * Administrator/Responsavel criar, consultar/listar, e remover um ou mais
 * utilizadores participantes em determinado evento (exemplo: remover utilizador
 * participante externo1@email.com do evento1; adicionar lista de utilizadores
 * participantes via copy/paste dos seus ids ao evento1; adicionar lista de
 * utilizadores participantes via outra classificação predefinida, como por
 * exemplo, todos os participantes classificados como inscritos à UC de DAE),
 * para que possa manter atualizada a lista de participantes de determinado
 * evento;
 *
 */
@Entity
//@Table(name = "ADMINISTRATORS")
@NamedQueries({
    @NamedQuery(
            name = "getAllAdministrators",
            query = "SELECT adm FROM Administrator adm ORDER BY adm.id"
    )
})
public class Administrator extends User implements Serializable {

    //private List<User> users;
    //private List<Event> events;

    public Administrator() {
        //this.users = new LinkedList<>();
        //this.events = new LinkedList<>();
    }

    public Administrator(String username, String password, String name, String email) {
        super(username, password, name, email);
        //this.users = new LinkedList<>();
        //this.events = new LinkedList<>();
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }
//
//    public void addUser(User user) {
//        try {
//            if (!users.contains(user)) {
//                users.add(user);
//            }
//        } catch (Exception ex) {
//            throw new EJBException(ex.getMessage());
//        }
//    }
//
//    public void removeUser(User user) {
//        try {
//            if (users.contains(user)) {
//                users.remove(user);
//            }
//        } catch (Exception ex) {
//            throw new EJBException(ex.getMessage());
//        }
//    }
//
//    public int getNumberOfUsers() {
//        return this.users.size();
//    }
//
//    public void addEvent(Event event) {
//        try {
//            if (!events.contains(event)) {
//                events.add(event);
//            }
//        } catch (Exception ex) {
//            throw new EJBException(ex.getMessage());
//        }
//    }
//
//    public void removeEvent(Event event) {
//        try {
//            if (events.contains(event)) {
//                events.remove(event);
//            }
//        } catch (Exception ex) {
//            throw new EJBException(ex.getMessage());
//        }
//    }
//
//    public int getNumberOfEvents() {
//        return this.events.size();
//    }

    @Override
    public String toString() {
        return "entities.Administrator[id=" + id + "]: " + name;
    }

}
