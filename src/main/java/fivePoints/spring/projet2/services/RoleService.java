package fivePoints.spring.projet2.services;


import fivePoints.spring.projet2.exceptions.ResourceNotFoundException;
import fivePoints.spring.projet2.models.Poste;
import fivePoints.spring.projet2.models.Role;
import fivePoints.spring.projet2.models.User;
import fivePoints.spring.projet2.repositories.PosteRepository;
import fivePoints.spring.projet2.repositories.RoleRepository;
import fivePoints.spring.projet2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.roleRepository = repository;
    }

    @Autowired
    UserRepository userRepository;

    public List<Role> getAllRole(){
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

    public String affectUserRole(int idUser,int idRole) {
        Optional<Role> role1 =  roleRepository.findById(idRole);
        Optional<User> user1= userRepository.findById(idUser);

        if (role1.isPresent() && user1.isPresent()) {
            Role existingRole = role1.orElse(null);
            User existingUser = user1.orElse(null);
            System.out.println(existingRole.getId());
            System.out.println(existingUser.getId());

//            Set<Role> roles = new HashSet<>();
//            roles.add(existingRole);
//
//            existingUser.setRoles(existingUser.getRoles().);
//
//            this.userRepository.save(existingUser);
            return "Role affected to user successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
