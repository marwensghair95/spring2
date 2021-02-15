package fivePoints.spring.projet2.controllers;


import fivePoints.spring.projet2.models.Role;
import fivePoints.spring.projet2.payload.responses.MessageResponse;
import fivePoints.spring.projet2.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService)
    {
        this.roleService = roleService;
    }

    @GetMapping("/allRole")
    public ResponseEntity<List<Role>> allRole() {
        List<Role> listUsers = this.roleService.getAllPost();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }


    @PostMapping("/addRole")
    public ResponseEntity<MessageResponse> newPosts(@RequestBody Role newRole) {
        String message = roleService.savenewRole(newRole);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }

    @GetMapping("/getRole/{id}")
    public ResponseEntity<Role> getRole(@PathVariable(value="id") int id) {
        Role role = roleService.getPostById(id);
        if(role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(role);
    }

    @PutMapping("/update/{id}")
    Role upateRole(@RequestBody Role newPost,@PathVariable Integer id){
        return this.roleService.update(newPost,id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(value="id") int id) {
        String message = roleService.delete(id);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
}
