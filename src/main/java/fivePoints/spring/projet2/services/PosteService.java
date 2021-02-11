package fivePoints.spring.projet2.services;

import fivePoints.spring.projet2.models.Poste;
import fivePoints.spring.projet2.repositories.PosteRepository;
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

    public List<Poste> getAllPost(){
        return postRepository.findAll();
    }
    public String saveNewPost(Poste newPost){
        postRepository.save(newPost);
        return "Post added successfully";
    }
    public Poste getPostById(Integer id){
        return postRepository.findById(id).get();
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
}
