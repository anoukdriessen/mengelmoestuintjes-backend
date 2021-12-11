package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }
    public int newPost(Post toAdd) {
        toAdd.setCreated(new Date());
        Post newPost = postRepository.save(toAdd);
        return newPost.getId();
    }
    public void updatePost(int id, Post modified) {
        Post toModify = postRepository.findById(id).orElse(null);

        boolean titleNotEmpty = !modified.getTitle().isEmpty();
        boolean descriptionNotEmpty = !modified.getTitle().isEmpty();
        boolean modifed = false;

        // title
        if (titleNotEmpty) {
            toModify.setTitle(modified.getTitle());
            modifed = true;
        }
        // description
        if (descriptionNotEmpty) {
            toModify.setDescription(modified.getDescription());
            modifed = true;
        }
        // if something is modified set modified date
        if (modifed) { toModify.setModified(new Date()); }

        postRepository.save(toModify);
    }
    public void updatePartOfPost(int id, Post modified) {
        Post toModify = postRepository.findById(id).orElse(null);

        boolean titleNotEmpty = !modified.getTitle().isEmpty() && modified.getTitle() != null;
        boolean descriptionNotEmpty = !modified.getDescription().isEmpty() && modified.getTitle() != null;
        boolean modifed = false;

        // title
        if (titleNotEmpty) {
            toModify.setTitle(modified.getTitle());
            modifed = true;
        }
        // description
        if (descriptionNotEmpty) {
            toModify.setDescription(modified.getDescription());
            modifed = true;
        }
        // if something is modified set modified date
        if (modifed) { toModify.setModified(new Date()); }

        postRepository.save(toModify);
    }
}
