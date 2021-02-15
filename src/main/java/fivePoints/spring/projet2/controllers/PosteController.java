package fivePoints.spring.projet2.controllers;

import fivePoints.spring.projet2.models.Poste;
import fivePoints.spring.projet2.payload.responses.MessageResponse;
import fivePoints.spring.projet2.services.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PosteController {

    private final PosteService postService;

    @Autowired
    public PosteController(PosteService postService)
    {
        this.postService = postService;
    }

    @GetMapping("/allPost")
    List<Poste> all() {
        return  this.postService.getAllPost();
    }


    @PostMapping("/addPost")
    public ResponseEntity<MessageResponse> newPosts(@RequestBody Poste newPost) {
        String message = postService.saveNewPost(newPost);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }



    @GetMapping("/getByTitre/{titre}")
    List<Poste> getByTitre(@PathVariable String titre){
        return  this.postService.getByTitre(titre);
    }
    @GetMapping("/getByTitreAndDescription/{titre}/{description}")
    List<Poste> getByTitreAndDescription(@PathVariable String titre,@PathVariable String description){
        return  this.postService.getByTitreAndDescription(titre,description);
    }

    @GetMapping("/getPost/{id}")
    public ResponseEntity<Poste> getPost(@PathVariable(value="id") int id) {
        System.out.println(id);
        Poste post = postService.getPostById(id);
        if(post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(post);
    }


    @PutMapping("/update/{id}")
    Poste upatePost(@RequestBody Poste newPost,@PathVariable Integer id){
        return this.postService.update(newPost,id);
    }

    @DeleteMapping("/delete/{id}")
    void deletePost(@PathVariable Integer id) {
        this.postService.delete(id);
    }
}
