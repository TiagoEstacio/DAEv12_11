
package dtos;

import java.io.Serializable;

public class ManagerDTO extends UserDTO implements Serializable{
    
    public ManagerDTO() {
    }    
    
    public ManagerDTO(Long id, String username, String password, String name, String email) {
        super(id, username, password, name, email);
    }
    
    @Override
    public void reset() {
        super.reset();
    }
    
}
