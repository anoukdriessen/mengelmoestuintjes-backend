//package nl.mengelmoestuintjes.gardening.service;
//
//import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
//import nl.mengelmoestuintjes.gardening.model.posts.Post;
//import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;
//import nl.mengelmoestuintjes.gardening.repository.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//public class PostService {
//    private static final String NOT_FOUND = "Post not found";
//    private final PostRepository postRepository;
//
//    @Autowired
//    public PostService( PostRepository postRepository ) {
//        this.postRepository = postRepository;
//    }
//
//    // Create
//    public Post newPost( Post toAdd ) {
//        return postRepository.save( toAdd );
//    }
//
//    // Read
//    public Iterable<Post> getAllPosts(String author, boolean visible, PostCategory category) {
//        if ( !author.isBlank() && visible ) {
//            return postRepository.findAllByAuthorAndVisible(author, visible);
//        } else if ( !Objects.isNull(category) && visible ){
//            return postRepository.findAllByCategoryAndVisible(category, visible);
//        } else if ( visible ) {
//            return postRepository.findByVisibleTrue();
//        } else if ( !author.isBlank() ) {
//            return postRepository.findAllByAuthor(author);
//        } else if ( !Objects.isNull(category) ) {
//            return postRepository.findAllByCategory(category);
//        } else {
//            return postRepository.findAll();
//        }
//    }
//    public Post getPostById(int id) {
//        Optional<Post> toFind = postRepository.findById(id);
//
//        if (toFind.isPresent()) { // check if post exists
//            return toFind.get();
//        } else {  // post does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//    public Post getRandom() {
//        long bound = postRepository.count();
//        Random r = new Random();
//        int id = r.nextInt( (int) bound );
//        return getPostById( id );
//    }
//
//    // Update
//    public void updatePost(int id, Post modified) {
//        Post toModify = postRepository.findById(id).orElse(null);
//
//        if (toModify != null) {
//            boolean titleNotEmpty = !modified.getTitle().isEmpty();
//            boolean categoryNotEmpty = modified.getCategory() != null;
//            boolean descriptionNotEmpty = !modified.getTitle().isEmpty();
//            boolean imageNotEmpty = !modified.getImageUrl().isEmpty();
//            boolean authorNotEmpty = !modified.getAuthor().isEmpty();
//            boolean visibleChanged = modified.isVisible() != toModify.isVisible();
//
//            boolean isModifed = false;
//
//            if (titleNotEmpty) {
//                toModify.setTitle(modified.getTitle());
//                isModifed = true;
//            }
//            if (categoryNotEmpty) {
//                toModify.setCategory(modified.getCategory());
//                isModifed = true;
//            }
//            if (descriptionNotEmpty) {
//                toModify.setDescription(modified.getDescription());
//                isModifed = true;
//            }
//            if (imageNotEmpty) {
//                toModify.setImageUrl(modified.getImageUrl());
//                isModifed = true;
//            }
//            if (authorNotEmpty) {
//                toModify.setAuthor(modified.getAuthor());
//                isModifed = true;
//            }
//            if (visibleChanged) {
//                toModify.setVisible(modified.isVisible());
//                isModifed = true;
//            }
//
//            // if something is modified set modified date
//            if (isModifed) { toModify.setModified(new Date()); }
//            postRepository.save(toModify);
//        } else {
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public Post delete( int id ) {
//        Optional<Post> toFind = postRepository.findById( id );
//        if (toFind.isPresent()) { // check if post exists
//            Post toDelete = toFind.get();
//            postRepository.delete( toDelete );
//            return toDelete;
//        } else { // post does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//}
