package fivePoints.spring.projet2.services;


import fivePoints.spring.projet2.models.Role;
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
        return "Post added successfully";
    }
    public Role getPostById(Integer id){
        return roleRepository.findById(id).get();
    }


    public String delete(Integer id) {
        roleRepository.deleteById(id);
        return "Post deleted sucessfully!";
    }
    public Role update(Role newRole, Integer id) {
        return roleRepository.findById(id)
                .map(post -> {
                    post.setName(newRole.getName());
                    return roleRepository.save(post);
                })
                .orElseGet(() -> {
//                    newUser.set(id);
                    return roleRepository.save(newRole);
                });
    }
}
