package fivePoints.spring.projet2.services;


import fivePoints.spring.projet2.models.UserDetails;
import fivePoints.spring.projet2.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {
    @Autowired
    UserDetailsRepository userDetailsRepository;

    public String addUser(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "User added successfully";
    }

    public List<UserDetails> getAllUsers() {
        return userDetailsRepository.findAll();
    }

    public UserDetails getUserByID(int id) {
        return userDetailsRepository.findById(id).get();
    }

    public UserDetails updateUserByID(int id, UserDetails userDetails) {
        UserDetails existingUser = userDetailsRepository.findById(id).orElse(null);
        if(existingUser != null)
        {
            existingUser.setAge(userDetails.getAge());
            existingUser.setBirthDate(userDetails.getBirthDate());
            existingUser.setGithubProfileLink(userDetails.getGithubProfileLink());
            existingUser.setLinkedinProfileLink(userDetails.getLinkedinProfileLink());
        }
        return userDetailsRepository.save(existingUser);
    }

    public String deleteUserByID(int id) {
        Optional<UserDetails> existingUser = userDetailsRepository.findById(id);
        if(existingUser.isPresent()) {
            userDetailsRepository.delete(existingUser.get());
            return "User is deleted by id "+ id;
        }
        throw new RuntimeException("User not found .");
    }
}
