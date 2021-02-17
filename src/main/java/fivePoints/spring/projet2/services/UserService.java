package fivePoints.spring.projet2.services;

import fivePoints.spring.projet2.exceptions.ResourceNotFoundException;
import fivePoints.spring.projet2.models.User;
import fivePoints.spring.projet2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(User user) {
        userRepository.save(user);
        return "User added successfully";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(int id) {
        Optional<User> userData =  userRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

//    public User updateUserByID(int id, User user) {
//        User existingUser = userRepository.findById(id).orElse(null);
//        if(existingUser != null)
//        {
//            existingUser.setFirstName(user.getFirstName());
//            existingUser.setLastName(user.getLastName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
//        }
//        return userRepository.save(existingUser);
//    }

    public String updateUserByID(int id, User user)
    {
        Optional<User> userData = this.userRepository.findById(id);
        if (userData.isPresent()) {
            User existingUser = userData.orElse(null);
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            // save existingUser in the database
            this.userRepository.save(existingUser);
            // return statement
            return "User updated successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }


    public String deleteUserByID(int id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return "User deleted successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
