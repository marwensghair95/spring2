package fivePoints.spring.projet2.controllers;

import fivePoints.spring.projet2.models.User;
import fivePoints.spring.projet2.payload.responses.MessageResponse;
import fivePoints.spring.projet2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<MessageResponse> addUser(@RequestBody User user) {
        String message = userService.addUser(user);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
    @PutMapping ("/affectDetails/{idUser}/{idDetail}")
    public ResponseEntity<MessageResponse> affectUserDetails(@PathVariable (value="idUser") int idUser,@PathVariable (value="idDetail") int idDetail) {
        String message = userService.affectUserDetails(idUser,idDetail);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable(value="id") int id) {
//        System.out.println(id);
        User user = userService.getUserByID(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<MessageResponse> updateUserByID(@PathVariable(value="id") int id, @RequestBody User user) {
        String message =  userService.updateUserByID(id, user);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageResponse> deleteUserByID(@PathVariable(value="id") int user) {
        String message = userService.deleteUserByID(user);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
}
