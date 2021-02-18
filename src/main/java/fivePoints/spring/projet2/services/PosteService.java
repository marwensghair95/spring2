package fivePoints.spring.projet2.services;

import fivePoints.spring.projet2.exceptions.ResourceNotFoundException;
import fivePoints.spring.projet2.models.Poste;
import fivePoints.spring.projet2.models.User;
import fivePoints.spring.projet2.models.UserDetails;
import fivePoints.spring.projet2.repositories.PosteRepository;
import fivePoints.spring.projet2.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@Service
public class PosteService {
    private final PosteRepository postRepository;

    @Autowired
    public PosteService(PosteRepository repository) {
        this.postRepository = repository;
    }
    @Autowired
    UserRepository userRepository;

    public List<Poste> getAllPost(){
        return postRepository.findAll();
    }
    public String saveNewPost(Poste newPost){
        postRepository.save(newPost);
        return "Post added successfully";
    }
    public Poste getPostById(Integer id){
        Optional<Poste> posteData =  postRepository.findById(id);
        return posteData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    public List<Poste> getByTitre(String title){
        return postRepository.getPostsByTitle(title);
    }
    public List<Poste> getByTitreAndDescription(String title, String description){
        return postRepository.getPostsByTitleAndDescription(title,description);
    }

    public String delete(Integer id) {
        postRepository.deleteById(id);
        return "Post deleted sucessfully!";
    }

    public Poste update(Poste newPost, Integer id) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setDescription(newPost.getDescription());
                    return postRepository.save(post);
                })
                .orElseGet(() -> {
//                    newUser.set(id);
                    return postRepository.save(newPost);
                });
    }

    public String affectUserPoste(int idUser,int idPoste) {
        Optional<Poste> poste1 =  postRepository.findById(idPoste);
        Optional<User> user1= userRepository.findById(idUser);

        if (poste1.isPresent() && user1.isPresent()) {
            Poste existingPoste = poste1.orElse(null);
            User existingUser = user1.orElse(null);
            existingPoste.setUser(existingUser); // Set child reference

            this.userRepository.save(existingUser);
            return "Poste affected to user successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
