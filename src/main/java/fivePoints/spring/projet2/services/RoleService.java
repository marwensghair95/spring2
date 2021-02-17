package fivePoints.spring.projet2.services;


import fivePoints.spring.projet2.exceptions.ResourceNotFoundException;
import fivePoints.spring.projet2.models.Role;
import fivePoints.spring.projet2.models.User;
import fivePoints.spring.projet2.repositories.PosteRepository;
import fivePoints.spring.projet2.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.roleRepository = repository;
    }

    public List<Role> getAllPost(){
        return roleRepository.findAll();
    }
    public String savenewRole(Role newRole){
        roleRepository.save(newRole);
        return "Role added successfully";
    }
    public Role getPostById(Integer id){
        Optional<Role> roleData = roleRepository.findById(id);
        return roleData.orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }


    public String delete(Integer id) {

        Optional<Role> existingRole = roleRepository.findById(id);
        if (existingRole.isPresent()) {
            roleRepository.delete(existingRole.get());
        return "Role deleted successfully!";
    } else {
        throw new ResourceNotFoundException("Role not found");
    }
    }

    public String update(Role newRole, Integer id) {
        Optional<Role> roleData = this.roleRepository.findById(id);
        if (roleData.isPresent()) {
            Role existingRole = roleData.orElse(null);
            existingRole.setName(newRole.getName());
            // save existingUser in the database
            this.roleRepository.save(existingRole);
            // return statement
            return "User updated successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
