package fivePoints.spring.projet2.services;


import fivePoints.spring.projet2.exceptions.ResourceNotFoundException;
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
        Optional<UserDetails>  userDetailData = userDetailsRepository.findById(id);
        return userDetailData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public String updateUserByID(int id, UserDetails userDetails) {
        Optional<UserDetails>  userDetailsData = userDetailsRepository.findById(id);
        if(userDetailsData.isPresent())
        {
            UserDetails existingUser = userDetailsData.orElse(null);
            existingUser.setAge(userDetails.getAge());
            existingUser.setBirthDate(userDetails.getBirthDate());
            existingUser.setGithubProfileLink(userDetails.getGithubProfileLink());
            existingUser.setLinkedinProfileLink(userDetails.getLinkedinProfileLink());
            this.userDetailsRepository.save(existingUser);
            return "User updated successfully!";
        }else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public String deleteUserByID(int id) {
        Optional<UserDetails> existingUser = userDetailsRepository.findById(id);
        if (existingUser.isPresent()) {
            userDetailsRepository.delete(existingUser.get());
            return "User deleted successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
