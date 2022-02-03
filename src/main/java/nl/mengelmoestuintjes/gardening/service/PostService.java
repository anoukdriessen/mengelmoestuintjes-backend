package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.PostNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.PostRequest;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.PostCategory;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository repository;

    @Autowired
    public PostService( PostRepository postRepository ) {
        this.repository = postRepository;
    }

    // CREATE
    public Long create( PostRequest toAdd ) {
        Post newPost = new Post();

        newPost.setId(toAdd.getId());
        newPost.setAuthor(toAdd.getAuthor() );
        newPost.setTitle( toAdd.getTitle() );
        newPost.setSummary( toAdd.getSummary() );
        newPost.setDescription( toAdd.getDescription() );
        newPost.setImage( toAdd.getImage() );
        newPost.setCategory( toAdd.getCategory() );
        newPost.setPublished( toAdd.isPublished() );
        newPost.setCreated( LocalDateTime.now() );

        Post p = repository.save(newPost);
        return p.getId();
    }

    // READ
    public Iterable<Post> getAll(
            String title, boolean published, PostCategory category, String summary) {
        boolean hasTitle = !title.isBlank();
        boolean hasCategory = !Objects.isNull( category );
        boolean hasSummary = !summary.isBlank();

        if (published && hasTitle) return repository.findAllByTitleContainingAndPublished(title, published);
        if (published && hasCategory) return repository.findAllByCategoryAndPublished( category, published);

        if (hasSummary) return repository.findAllBySummaryContainingAndPublishedTrue(summary);
        if (published) return repository.findByPublished( published);
        if (hasCategory) return repository.findAllByCategory( category );

        return repository.findAll();
    }
    public Post getPost(long id) {
        Optional<Post> toFind = repository.findById( id );
        boolean postFound = toFind.isPresent();
        if ( postFound ) return toFind.get();
        throw new PostNotFoundException(id);
    }
    public Iterable<Post> getTop4Posts(boolean published, PostCategory category) {
        boolean hasCategory = !Objects.isNull( category );
        if (published && hasCategory) {
            return repository.findTop4ByCategoryAndPublished( category, published);
        } else {
            throw new BadRequestException("params missing or incorrect");
        }
    }

    // UPDATE
    public String addAuthor(User user, Post post) {
        post.setAuthor( user );
        repository.save( post );
        return post.getTitle() + " written by " + user.getUsername();
    }
    public byte[] addPostImage(Long postId, MultipartFile file) throws IOException {
        Post post = getPost( postId );
        String fileName = file.getOriginalFilename();
        byte[] data = file.getBytes();
        post.setImage(data);
        return repository.save(post).getImage();
    }
    public String updatePost(long id, PostRequest modified) {
        Post post = getPost(id);

        post.setTitle(modified.getTitle());
        post.setSummary(modified.getSummary());
        post.setDescription(modified.getDescription());
        post.setImage(modified.getImage());
        post.setCategory(modified.getCategory());
        post.setPublished(modified.isPublished());
        post.setModified();

        repository.save(post);
        return post.getTitle() + " has been updated";
    }
    public String setPublished(long id, boolean published) {
        Post post = getPost(id);
        post.setPublished(published);
        post.setModified();

        repository.save(post);
        return post.getTitle() + " is now published=" + published;
    }

    // DELETE
    public String delete(long id) {
        Post p = getPost(id);
        repository.deleteById(id);
        return p.getTitle() + " is deleted";
    }
}