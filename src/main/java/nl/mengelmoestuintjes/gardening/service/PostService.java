package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.PostNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.PostRequest;
import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.repository.PostAuthorRepository;
import nl.mengelmoestuintjes.gardening.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository repository;

    @Autowired
    PostAuthorRepository relationRepository;

    @Autowired
    public PostService( PostRepository postRepository ) {
        this.repository = postRepository;
    }

    // CREATE
    public Post create( PostRequest toAdd ) {
        Post newPost = new Post();

        newPost.setId( toAdd.getId() );
        newPost.setAuthor(toAdd.getAuthor() );
        newPost.setTitle( toAdd.getTitle() );
        newPost.setSummary( toAdd.getSummary() );
        newPost.setDescription( toAdd.getDescription() );
        newPost.setImageUrl( toAdd.getImageUrl() );
        newPost.setCategory( toAdd.getCategory() );
        newPost.setPublished( toAdd.isPublished() );
        newPost.setCreated( LocalDateTime.now() );

        repository.save(newPost);
        return newPost;
    }

    // READ
    public Iterable<Post> getAll(
            String title, boolean published, PostCategory category, String summary) {
        boolean hasTitle = !title.isBlank();
        boolean hasCategory = !Objects.isNull( category );
        boolean hasSummary = !summary.isBlank();

        if (published && hasTitle) return repository.findAllByTitleContainingAndPublished(title, published);
        if (published && hasCategory) return repository.findAllByCategoryAndPublished( category, published );

        if (hasSummary) return repository.findAllBySummaryContainingAndPublishedTrue(summary);
        if (published) return repository.findByPublished( published);
        if (hasCategory) return repository.findAllByCategory( category );

        return repository.findAll();
    }
    public Post getPost(long id) {
        Optional<Post> toFind = repository.findById( id );
        boolean postFound = toFind.isPresent();
        if ( postFound ) {
            return toFind.get();
        } else {
            throw new PostNotFoundException(id);
        }
    }

    // UPDATE
    public String addAuthor(User user, Post post) {
        post.setAuthor( user );
        repository.save( post );
        return post.getTitle() + " written by " + user.getUsername();
    }
    public String updatePost(long id, PostRequest modified) {
        Post post = getPost(id);

        post.setTitle(modified.getTitle());
        post.setSummary(modified.getSummary());
        post.setDescription(modified.getDescription());
        post.setImageUrl(modified.getImageUrl());
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

/*
 *     public Post getRandom() {
 *         long bound = postRepository.count();
 *         Random r = new Random();
 *         int id = r.nextInt( (int) bound );
 *         return getPostById( id );
 *     }
 * */