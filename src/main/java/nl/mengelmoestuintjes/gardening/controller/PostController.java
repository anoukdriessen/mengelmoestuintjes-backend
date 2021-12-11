package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.repository.PostRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;
    
    // GET
    @GetMapping(value = "/posts")
    public ResponseEntity<Object> getAllPosts() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable int id) {
        return ResponseEntity.ok(postRepository.findById(id));
    }

    // DELETE
    @DeleteMapping(value = "/posts/{id}")
    public ResponseEntity<Object> deletePostById(@PathVariable("id") int id) {
        postRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // POST
    @PostMapping(value = "/posts")
    public ResponseEntity<Object> newPost(@RequestBody Post postToAdd) {
        postToAdd.setCreated(new Date());

        Post newPost = postRepository.save(postToAdd);
        int newId = newPost.getId();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        System.out.println("ik ben hier");
        return ResponseEntity.created(location).build();
    }

    // PUT
    @PutMapping(value = "/posts/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable("id") int id, @RequestBody Post modifiedPost) {
        Post toModify = postRepository.findById(id).orElse(null);

        boolean titleNotEmpty = !modifiedPost.getTitle().isEmpty();
        boolean descriptionNotEmpty = !modifiedPost.getTitle().isEmpty();
        boolean modifed = false;

        // title
        if (titleNotEmpty) {
            toModify.setTitle(modifiedPost.getTitle());
            modifed = true;
        }
        // description
        if (descriptionNotEmpty) {
            toModify.setDescription(modifiedPost.getDescription());
            modifed = true;
        }
        // if something is modified set modified date
        if (modifed) { toModify.setModified(new Date()); }

        postRepository.save(toModify);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/posts/{id}")
    public ResponseEntity<Object> updatePartOfPost(@PathVariable("id") int id, @RequestBody Post modifiedPost) {
        Post toModify = postRepository.findById(id).orElse(null);

        boolean titleNotEmpty = !modifiedPost.getTitle().isEmpty() && modifiedPost.getTitle() != null;
        boolean descriptionNotEmpty = !modifiedPost.getDescription().isEmpty() && modifiedPost.getTitle() != null;
        boolean modifed = false;

        // title
        if (titleNotEmpty) {
            toModify.setTitle(modifiedPost.getTitle());
            modifed = true;
        }
        // description
        if (descriptionNotEmpty) {
            toModify.setDescription(modifiedPost.getDescription());
            modifed = true;
        }
        // if something is modified set modified date
        if (modifed) { toModify.setModified(new Date()); }

        postRepository.save(toModify);

        return ResponseEntity.noContent().build();
    }
}
