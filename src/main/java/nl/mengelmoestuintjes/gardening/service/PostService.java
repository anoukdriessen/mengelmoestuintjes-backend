package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.dto.PostRequestDto;
import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Iterable<Post> getAllPosts(String author) {
        if (!author.isBlank()) {
            return postRepository.findAllByAuthor(author);
        }
        return postRepository.findAll();

    }

    public Post getPostById(int id) {
        Optional<Post> toFind = postRepository.findById(id);

        // check if post exists
        if (toFind.isPresent()) {
            return toFind.get();
        } else {
            // post does not exists
            throw new RecordNotFoundException("ID does not exists, post not found");
        }

    }
    public void deletePost(int id) {
        Optional<Post> toFind = postRepository.findById(id);

        // check if post exists
        if (toFind.isPresent()) {
            postRepository.deleteById(id);
        } else {
            // post does not exists
            throw new RecordNotFoundException("ID does not exists, post not found");
        }
    }
    public int newPost(PostRequestDto toAdd) {
        //String title, String description, String author
        Post post = new Post(toAdd.getTitle(), toAdd.getDescription(), toAdd.getAuthor());

        toAdd.setCreated(new Date());
        Post newPost = postRepository.save(post);
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
