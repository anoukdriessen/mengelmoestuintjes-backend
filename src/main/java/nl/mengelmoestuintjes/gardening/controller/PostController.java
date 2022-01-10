package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.dto.request.PostRequest;
import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.service.PostService;
import nl.mengelmoestuintjes.gardening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/berichten")
@CrossOrigin
public class PostController {
    private PostService service;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.service = postService;
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public Post newPost(
            @RequestParam(value = "username") String username,
            @RequestBody PostRequest request
    ) {
        try {
            User author = userService.getUser(username);
            request.setAuthor(author);
            return service.create( request );
        } catch (BadRequestException e) {
            throw new BadRequestException( "cannot create post" );
        }
    }
    @PostMapping(value = "/{username}/{id}")
    public String addAuthor(
            @PathVariable("username") String username,
            @PathVariable("id") long id
    ) {
        try {
            Post post = service.getPost(id);
            User author = userService.getUser(username);
            return service.addAuthor(author, post);
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }

    // READ
    @GetMapping
    public Iterable<Post> getAllPosts(
            @RequestParam(name = "title", defaultValue = "", required = false) String title,
            @RequestParam(name = "published", required = false) boolean published,
            @RequestParam(name = "category", required = false) PostCategory category,
            @RequestParam(name = "summary", defaultValue = "", required = false) String summary
    ){
        return service.getAll(title, published, category, summary);
    }
    @GetMapping("/{id}")
    public Post getPostById(
            @PathVariable( "id" ) long id
    ){
        return service.getPost(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(
            @PathVariable("id") long id,
            @RequestBody PostRequest request
    ) {
        return ResponseEntity.ok().body(service.updatePost(id, request));
    }
    @PutMapping("/{id}/{published}")
    public ResponseEntity<Object> setPostToPublished(
            @PathVariable("id") long id,
            @PathVariable("published") boolean published
    ) {
        return ResponseEntity.ok().body(service.setPublished(id, published));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable("id") long id
    ) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}
