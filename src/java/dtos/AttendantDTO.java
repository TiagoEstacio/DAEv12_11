
package dtos;

import entities.Event;
import java.io.Serializable;
import java.util.List;

public class AttendantDTO extends UserDTO implements Serializable {
      
    public AttendantDTO() {
    }    
    
    public AttendantDTO(Long id, String username, String password, String name, String email) {
        super(id, username, password, name, email);   
    }
    
    @Override
    public void reset() {
        super.reset();
    }
   
}