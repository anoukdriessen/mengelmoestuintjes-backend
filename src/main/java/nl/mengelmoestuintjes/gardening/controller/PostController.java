package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts")
    public ResponseEntity<Object> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable int id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping(value = "/posts/{id}")
    public ResponseEntity<Object> deletePostById(@PathVariable("id") int id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/posts")
    public ResponseEntity<Object> newPost(@RequestBody Post toAdd) {
        int newId = postService.newPost(toAdd);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/posts/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable("id") int id, @RequestBody Post modifiedPost) {
        postService.updatePost(id, modifiedPost);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/posts/{id}")
    public ResponseEntity<Object> updatePartOfPost(@PathVariable("id") int id, @RequestBody Post modifiedPost) {
        postService.updatePost(id, modifiedPost);
        return ResponseEntity.noContent().build();
    }
}
