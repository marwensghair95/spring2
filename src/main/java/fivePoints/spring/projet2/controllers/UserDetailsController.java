package fivePoints.spring.projet2.controllers;

import fivePoints.spring.projet2.models.UserDetails;
import fivePoints.spring.projet2.responses.MessageResponse;
import fivePoints.spring.projet2.services.UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "/usersDetails")
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/addUser")
    public ResponseEntity<MessageResponse> addUser(@RequestBody UserDetails userDetails) {
        String message = userDetailsService.addUser(userDetails);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        List<UserDetails> listUsers = this.userDetailsService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getUserByID(@PathVariable(value="id") int id) {
        UserDetails userDetails = userDetailsService.getUserByID(id);
        if(userDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userDetails);
    }

    @PutMapping("/{id}")
    public UserDetails updateUserByID(@PathVariable(value="id") int id, @RequestBody UserDetails userDetails) {
        return userDetailsService.updateUserByID(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserByID(@PathVariable(value="id") int userDetails) {
        String message = userDetailsService.deleteUserByID(userDetails);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
}
