package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.dto.post.PostRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.post.PostResponseDto;
import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;
import nl.mengelmoestuintjes.gardening.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("posts")
@CrossOrigin
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create
    @PostMapping
    public PostResponseDto newPost( @Valid @RequestBody PostRequestDto toAdd ) {
        Post post = postService.newPost( toAdd.toPost() );
        return PostResponseDto.fromPost(post);
    }

    // Read
    @GetMapping
    public List<PostResponseDto> getAllPosts(
            @RequestParam(name = "author", defaultValue = "", required = false) String author,
            @RequestParam(name = "visible", required = false) boolean visible,
            @RequestParam(name = "category", defaultValue = "", required = false) PostCategory category
    ) {
        List<PostResponseDto> all = new ArrayList<>();
        Iterable<Post> posts = postService.getAllPosts(author, visible, category);

        for (Post p : posts) {
            all.add( PostResponseDto.fromPost( p ) );
        }
        return all;
    }

    @GetMapping(value = "/{id}")
    public PostResponseDto getPostById(@PathVariable( "id" ) int id) {
        Post post = postService.getPostById(id);
        return PostResponseDto.fromPost(post);
    }

    // Update
    @PutMapping(value = "/{id}")
    public PostResponseDto updatePost( @PathVariable( "id" ) int id, @RequestBody Post modifiedPost) {
        postService.updatePost(id, modifiedPost);
        return PostResponseDto.fromPost(modifiedPost);
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public void deletePostById(@PathVariable( "id" ) int id) {
        postService.deletePostById(id);
    }
}
