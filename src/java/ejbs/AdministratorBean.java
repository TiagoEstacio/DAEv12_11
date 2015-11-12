
package ejbs;

import dtos.AdministratorDTO;
import entities.Administrator;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;


@Stateless
public class AdministratorBean {

    @PersistenceContext
    EntityManager em;
    
    public void createAdministrator (String username, String password, String name, String email)throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            List<Administrator> administrators = (List<Administrator>) em.createNamedQuery("getAllAdministrators").getResultList();
            for (Administrator a : administrators){
                if (username.equals(a.getUserName())){
                    throw new EntityAlreadyExistsException("A administrator with that username already exists.");  
                }
            }
            Administrator admin = new Administrator (username, password, name, email);
            em.persist(admin);
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
           throw new EJBException(e.getMessage());
        }
    }
    
    public List<AdministratorDTO> getAllAdministrators() {
        try {
            List<Administrator> administrators = (List<Administrator>) em.createNamedQuery("getAllAdministrators").getResultList();
            return administratorsToDTOs(administrators);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Administrator getAdministrator(String username) {
        try {
            Administrator administrator = new Administrator();
            List<Administrator> administrators = (List<Administrator>) em.createNamedQuery("getAllAdministrators").getResultList();
            for (Administrator a : administrators){
                if (username.equals(a.getUserName())){
                    administrator = a;
                    break;
                }
            }
            return administrator;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void updateAdministrator (Long id, String username, String password, String name, String email)throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            Administrator administrator = em.find(Administrator.class, id);
            if (administrator == null){
                throw new EntityDoesNotExistsException("There is no administrator with that id.");
            }
            List<Administrator> administrators = (List<Administrator>) em.createNamedQuery("getAllAdministrators").getResultList();
            for (Administrator a : administrators){
                if (username.equals(a.getUserName())){
                    throw new EntityAlreadyExistsException("A administrator with that username already exists.");  
                }
            }
            administrator.setUsername(username);
            administrator.setPassword(password);
            administrator.setName(name);
            administrator.setEmail(email);
            em.merge(administrator);   
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
     }

    public void removeAdministrator(Long id) throws EntityDoesNotExistsException{
        try {
            Administrator administrator = em.find(Administrator.class, id);
            if (administrator == null) {
                throw new EntityDoesNotExistsException("There is no administrator with that id.");
            }
            em.remove(administrator);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        } 
     }
    
    AdministratorDTO administratorToDTO(Administrator administrator) {
        return new AdministratorDTO(
                administrator.getId(),
                administrator.getUserName(),
                null,
                administrator.getName(),
                administrator.getEmail());
    }

    List<AdministratorDTO> administratorsToDTOs(List<Administrator> administrators) {
        List<AdministratorDTO> dtos = new ArrayList<>();
        for (Administrator a : administrators) {
            dtos.add(administratorToDTO(a));
        }
        return dtos;
    }
}