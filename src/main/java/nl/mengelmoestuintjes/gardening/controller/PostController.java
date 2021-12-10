package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.User;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    private List<Post> posts;
    /*
    Post = title, description, User author, Date created, Date modified
    */

    // constructor
    public PostController() {
        posts = new ArrayList<>();

//        User anouk = new User();
//        anouk.setName("Anouk");

//        User kevin = new User();
//        kevin.setName("Kevin");

        Post post1 = new Post();
        post1.setTitle("Mijn eerste plantje");
        post1.setDescription("Het eerste plantje dat ik gezaaid heb is een goudsbloem");
//        post1.setAuthor(anouk);
        post1.setCreated(new Date());

        Post post2 = new Post();
        post2.setTitle("Wie zaait zal oogsten");
        post2.setDescription("Ik heb een hele hoop zaad en te weinig ruimte om het te plaatsen");
//        post2.setAuthor(kevin);
        post2.setCreated(new Date());

        Post post3 = new Post();
        post3.setTitle("Mijn eerste plantje");
        post3.setDescription("Het eerste plantje dat ik gezaaid heb is een goudsbloem");
//        post3.setAuthor(kevin);
        post3.setCreated(new Date());

        Post post4 = new Post();
        post4.setTitle("Wie zaait zal oogsten");
        post4.setDescription("Ik heb een hele hoop zaad en te weinig ruimte om het te plaatsen");
//        post4.setAuthor(anouk);
        post4.setCreated(new Date());

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
    }

    // GET
    @GetMapping(value = "/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return this.posts;
    }

    @GetMapping(value = "/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPostById(@PathVariable int id) {
        return this.posts.get(id);
    }

    @DeleteMapping(value = "/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePostById(@PathVariable int id) {
        String deleted = "nothing";

        if (this.getPostById(id) != null) {
            deleted = this.getPostById(id).toString();
            this.posts.remove(id);
        }

        return deleted;
    }

    @PostMapping(value = "/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post newPost(@RequestBody Post postToAdd) {
        posts.add(postToAdd);

        return postToAdd;
    }

    @PutMapping(value = "/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Post updatePost(@PathVariable("id") int id, @RequestBody Post modifiedPost) {
        Post post = posts.get(id);
        // TODO check if not empty
        post.setTitle(modifiedPost.getTitle());
        post.setDescription(modifiedPost.getDescription());
        post.setModified(new Date());

        posts.set(id, post);

        return post;
    }
}
