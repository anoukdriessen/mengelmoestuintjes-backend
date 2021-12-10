package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.repository.PostRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(postRepository.findById());
    }


//    @DeleteMapping(value = "/posts/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public String deletePostById(@PathVariable int id) {
//        String deleted = "nothing";
//
//        if (this.getPostById(id) != null) {
//            deleted = this.getPostById(id).toString();
//            this.posts.remove(id);
//        }
//
//        return deleted;
//    }
//
//    @PostMapping(value = "/posts")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Post newPost(@RequestBody Post postToAdd) {
//        posts.add(postToAdd);
//
//        return postToAdd;
//    }
//
//    @PutMapping(value = "/posts/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Post updatePost(@PathVariable("id") int id, @RequestBody Post modifiedPost) {
//        Post post = posts.get(id);
//        // TODO check if not empty
//        post.setTitle(modifiedPost.getTitle());
//        post.setDescription(modifiedPost.getDescription());
//        post.setModified(new Date());
//
//        posts.set(id, post);
//
//        return post;
//    }
}
